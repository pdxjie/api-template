package com.basis.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.basis.common.Result;
import com.basis.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author IT 派同学
 * @since 2024-12-07
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/check")
    public Result<?> check() {
        log.info("login ---> {}", StpUtil.isLogin());
        log.info("result ----> {}", StpUtil.hasRole("SYS_ADMIN"));
        log.info("permission ----> {}", StpUtil.hasPermission("per:add"));
        log.info("permissions ----> {}", StpUtil.getPermissionList(1));
        return Result.success();
    }

}
