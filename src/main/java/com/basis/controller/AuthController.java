package com.basis.controller;

import com.basis.common.Result;
import com.basis.model.vo.WeChatAuthLoginVo;
import com.basis.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/7
 * @Description: DO AnyThing...
 */
@Slf4j
@RestController
@Api(tags = "系统认证基础接口")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;


    @ApiOperation(value = "微信授权登录")
    @PostMapping(value = "/wechat/login", name = "微信授权登录", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> wechatAuthLogin(@RequestBody @Validated WeChatAuthLoginVo vo) {
        // 获取微信登录 token
        String token = userService.wechatAuthorizedLogin(vo.getCode());
        return Result.success(token);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping(value = "/logout", name = "退出登录", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> logout() {
        userService.logout();
        return Result.success();
    }

}
