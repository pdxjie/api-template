package com.basis.strategy.sendStrategy;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.basis.annotations.LoginTypeAnno;
import com.basis.common.ResponseCode;
import com.basis.common.Result;
import com.basis.exception.BusinessException;
import com.basis.model.vo.SendVo;
import com.basis.utils.RedisUtils;
import com.basis.utils.SmsProvider;
import com.basis.utils.ThrowUtil;
import com.basis.utils.VerificationCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.basis.model.constant.BasicConstant.PHONE_CODE_PREFIX;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: DO AnyThing...
 */
@Service
@LoginTypeAnno("PHONE_CODE")
public class PhoneCodeStrategy implements SendCaptchaStrategy {

    @Resource
    private RedisUtils redisUtils;

    @Resource
    private VerificationCode verificationCode;

    @Resource
    private SmsProvider smsProvider;

    @Override
    public Result<String> send(SendVo vo) {
        ThrowUtil.throwIf(StrUtil.isEmpty(vo.getPhone()), new BusinessException(ResponseCode.PHONE_NOT_ALLOW_EMPTY));
        // 判断 Redis 中是否已经存在验证码
        String cacheKey = StrUtil.join(PHONE_CODE_PREFIX, vo.getPhone());
        String captcha = (String) redisUtils.getValue(cacheKey);
        // 判断是否不存在
        if (StrUtil.isEmpty(captcha)) {
            // 生成验证码 code
            captcha = verificationCode.VerificationCode(4);
            // 存入 Redis 中, 设置 5 分钟过期时间
            redisUtils.setValueTimeout(cacheKey, captcha, 60 * 5);
            // 填充验证码
            String TemplateParam = "{\"code\":\"" + captcha + "\"}";
            try {
                // 传入手机号码及短信模板中的验证码占位符
                SendSmsResponse response = smsProvider.sendSms(vo.getPhone(), TemplateParam);
                // 判断是否发送成功
                if (response.getCode().equalsIgnoreCase("ok")) {
                    // 存入 Redis 中, 设置 5 分钟过期时间
                    redisUtils.setValueTimeout(cacheKey, captcha, 60 * 5);
                }
            } catch (ClientException e) {
                throw new RuntimeException(e);
            }
            return Result.success();
        }
        // 如果 Redis 中存在说明还未过期，则在前端禁用即可
        return Result.success();
    }
}
