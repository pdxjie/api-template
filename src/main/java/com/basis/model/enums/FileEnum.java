package com.basis.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 文件夹名
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum FileEnum {

    // 头像
    USER_AVATAR("avatar"),
    ;

    // 文件夹描述
    String description;
}
