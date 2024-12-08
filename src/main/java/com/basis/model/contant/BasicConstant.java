package com.basis.model.contant;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 基础常量值
 */
public interface BasicConstant {

    public static final String DEFAULT_NICK_NAME = "新用户";

    public static final String OPEN_ID = "OPEN_ID";

    public static final String ROLE = "ROLE";

    // 普通用户角色的 ID
    public static final Long DEFAULT_USER_ROLE_ID = 1L;

    // 手机号验证码缓存前缀
    public static final String PHONE_CODE_PREFIX = "phone_prefix_";

    // 邮箱验证码缓存前缀
    public static final String EMAIL_CODE_PREFIX = "email_prefix_";
}
