package com.basis.strategy.sendStrategy;

import cn.hutool.core.util.StrUtil;
import com.basis.annotations.LoginTypeAnno;
import com.basis.common.ResponseCode;
import com.basis.common.Result;
import com.basis.exception.BusinessException;
import com.basis.model.vo.SendVo;
import com.basis.utils.RedisUtils;
import com.basis.utils.ThrowUtil;
import com.basis.utils.VerificationCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import static com.basis.model.constant.BasicConstant.EMAIL_CODE_PREFIX;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: DO AnyThing...
 */
@Service
@LoginTypeAnno("EMAIL_CODE")
public class EmailCodeStrategy implements SendCaptchaStrategy {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private VerificationCode verificationCode;

    @Value("${spring.mail.username}")
    private String username;

    @Resource
    private JavaMailSender mailSender;

    @Override
    public Result<String> send(SendVo vo) {
        ThrowUtil.throwIf(StrUtil.isEmpty(vo.getEmail()), new BusinessException(ResponseCode.EMAIL_NOT_ALLOW_EMPTY));
        // 判断 Redis 中是否已经存在验证码
        String cacheKey = StrUtil.join(EMAIL_CODE_PREFIX, vo.getEmail());
        String captcha = (String) redisUtils.getValue(cacheKey);
        // 判断是否不存在
        if (StrUtil.isEmpty(captcha)) {
            // 生成验证码 code
            captcha = verificationCode.VerificationCode(4);
            // 存入 Redis 中, 设置 5 分钟过期时间
            redisUtils.setValueTimeout(cacheKey, captcha, 60 * 5);
            // 创建邮件
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            try {
                mimeMessageHelper.setFrom(username);
                mimeMessageHelper.setTo(vo.getEmail());
                mimeMessageHelper.setSubject("API-Template");
                // 将验证码转换为数组
                mimeMessage.setContent(verificationCode.buildContent( String.valueOf(captcha.charAt(0)),
                        String.valueOf(captcha.charAt(1)), String.valueOf(captcha.charAt(2)), String.valueOf(captcha.charAt(3))), "text/html;charset=UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            mailSender.send(mimeMessage);
            return Result.success();
        }
        // 如果 Redis 中存在说明还未过期，则在前端禁用即可
        return Result.success();
    }
}
