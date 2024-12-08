package com.basis.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 分页响应实体
 */
@Data
public class PageDto<T> {

    @ApiModelProperty(value = "总数")
    private Long total;

    @ApiModelProperty(value = "数据项")
    private List<T> items;
}
