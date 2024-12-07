
package com.basis.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/7
 * @Description: 微信敏感词拦截模型
 */
@Data
@Accessors(chain = true)
public class WeChatMsgCheckVo {

    private String content;

    private Integer version = 2;

    private Integer scene = 2;

    private String openid;
}
