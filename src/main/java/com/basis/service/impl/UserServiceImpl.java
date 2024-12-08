package com.basis.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.basis.common.Result;
import com.basis.exception.BusinessException;
import com.basis.mapper.UserMapper;
import com.basis.model.entity.User;
import com.basis.model.vo.LoginVo;
import com.basis.model.vo.RegisterVo;
import com.basis.model.vo.SendVo;
import com.basis.service.IUserRoleService;
import com.basis.service.IUserService;
import com.basis.strategy.login.LoginStrategy;
import com.basis.strategy.login.LoginStrategyFactory;
import com.basis.strategy.sendStrategy.SendCaptchaStrategy;
import com.basis.strategy.sendStrategy.SendCaptchaStrategyFactory;
import com.basis.utils.PasswordUtils;
import com.basis.utils.ThrowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.basis.common.ResponseCode.USERNAME_OR_PASS_EMPTY;
import static com.basis.common.ResponseCode.USER_ALREADY_EXISTED;
import static com.basis.model.constant.BasicConstant.DEFAULT_NICK_NAME;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private LoginStrategyFactory strategyFactory;

    @Autowired
    private SendCaptchaStrategyFactory sendCaptchaStrategyFactory;

    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 退出登录
     */
    @Override
    public void logout() {
        // 判断是否已经登录
        if (StpUtil.isLogin()) {
            // 退出登录
            StpUtil.logout();
        }
    }

    /**
     * 登录操作
     * @param vo 请求体
     * @return
     */
    @Override
    public Result<?> login(LoginVo vo) {
        LoginStrategy strategy = strategyFactory.getStrategy(vo.getLoginType());
        return strategy.login(vo);
    }

    /**
     * 用户注册系统
     * @param vo 注册请求体
     * @return 操作结果
     */
    @Override
    public Result<?> register(RegisterVo vo) {
        // 校验参数
        ThrowUtil.throwIf(StrUtil.isEmpty(vo.getUsername()) || StrUtil.isEmpty(vo.getPassword()),
                new BusinessException(USERNAME_OR_PASS_EMPTY));
        // TODO 验证码校验（按需添加）
        // String code = (String) redisUtils.getValue(StrUtil.join(NORMAL_CODE_PREFIX, vo.getUsername()));
        // ThrowUtil.throwIf(Objects.isNull(code) || StrUtil.isEmpty(code) || !vo.getCode().equalsIgnoreCase(code),
        //         new BusinessException(CODE_NOT_CORRECT));
        // 根据用户名查询用户是否存在
        User one = getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, vo.getUsername()).last("LIMIT 1"));
        // 校验是否存在
        ThrowUtil.throwIf(Objects.nonNull(one), new BusinessException(USER_ALREADY_EXISTED));
        // 获取用户加密盐
        String salt = PasswordUtils.getSalt();
        // 加密密码
        String encode = PasswordUtils.encode(vo.getPassword(), salt);
        // 填充角色信息
        User user = new User();
        user.setPassword(encode);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setSalt(salt);
        user.setUserName(vo.getUsername());
        user.setIsDeleted(false);
        user.setNickName(DEFAULT_NICK_NAME);
        user.setSex(2); // 默认未知
        save(user);
        // 分配角色
        userRoleService.assignmentRole(user.getId());
        return Result.success();
    }

    /**
     * 发送验证码
     *
     * @param vo 发送验证码的请求体
     * @return 操作结果
     */
    @Override
    public Result<?> sendCaptcha(SendVo vo) {
        SendCaptchaStrategy strategy = sendCaptchaStrategyFactory.getStrategy(vo.getSendType());
        return strategy.send(vo);
    }
}
