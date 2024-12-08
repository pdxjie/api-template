package com.basis.configuration;

import com.basis.model.enums.FileEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/8
 * @Description: 用于文件资源访问
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String path;

    /**
     * 添加处理器映射
     *
     * @param registry the registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations(
                        "file:" + path + FileEnum.USER_AVATAR.getDescription() + "/"
                        // ... 自定义文件夹名称
                );
    }
}
