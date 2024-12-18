
package com.basis.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/7
 * @Description: 微信 AccessToken 封装
 */
public enum WeChatTokenUtil {

    INSTANCE;

    private final Token token = new Token();
    private final AtomicLong tokenExpireTime = new AtomicLong(0L);

    public String getWechatToken(final String appid, final String secret) {
        if (System.currentTimeMillis() / 1000 >= tokenExpireTime.get()) {
            resetToken(appid, secret);
        }
        return token.getToken();
    }

    private void resetToken(final String appid, final String secret) {
        final JSONObject block = JSONObject.parseObject(getAccessToken(appid, secret));
        long timestamp = System.currentTimeMillis() / 1000;
        String accessToken = block.getString("access_token");
        int expiresIn = block.getInteger("expires_in");
        token.setToken(accessToken);
        tokenExpireTime.set(timestamp + expiresIn);
    }

    private String getAccessToken(final String appid, final String secret) {
        final String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        try {
            return WebClient.create()
                    .get().uri(accessTokenUrl)
                    .retrieve()
                    .bodyToMono(String.class).block();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();

        }
    }

    @Accessors(chain = true)
    @Data
    public static class Token {
        public String token;
    }
}
