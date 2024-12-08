package com.basis.controller;

import com.basis.common.Result;
import com.basis.model.vo.LoginVo;
import com.basis.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @ApiOperation(value = "普通用户登录")
    @PostMapping(value = "/login", name = "普通用户登录", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> login(@RequestBody(required = false) LoginVo vo) {
        return userService.login(vo);
    }

    @ApiOperation(value = "退出登录")
    @PostMapping(value = "/logout", name = "退出登录", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<?> logout() {
        userService.logout();
        return Result.success();
    }

}
