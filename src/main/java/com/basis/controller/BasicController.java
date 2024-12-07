package com.basis.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.basis.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/7
 * @Description: DO AnyThing...
 */
@Slf4j
@RestController
@Api(tags = "通用基础接口")
@RequestMapping("/common")
public class BasicController {

    @ApiOperation(value = "登录功能")
    @GetMapping("/login")
    public Result<?> systemLogin() {
        String uuid = StrUtil.uuid();
        StpUtil.login(uuid);
        log.info("info....token {}", StpUtil.getTokenValue());
        return Result.success("登录成功");
    }

    @ApiOperation(value = "获取用户 Token 信息")
    @GetMapping("/token")
    public Result<?> systemToken() {
        log.info("info....token {}", StpUtil.getTokenValue());
        return Result.success(StpUtil.getTokenValue());
    }
}
