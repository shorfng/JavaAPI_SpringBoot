package com.loto.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件名：CorsConfig.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：跨域配置<p>
 * 创建人：蓝田_Loto
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Autowired
    private CorsProperties corsProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // 匹配路径
                .allowedOrigins(corsProperties.getAllowedOrigins().toArray(new String[0])) // 从配置文件中加载允许的源
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 支持的方法
                .allowedHeaders("*") // 所有头部
                .exposedHeaders("Authorization", "Content-Type") // 暴露给前端的 header
                .allowCredentials(true); // 是否允许发送 Cookie
    }

}
