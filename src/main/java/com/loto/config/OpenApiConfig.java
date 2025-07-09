package com.loto.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件名：OpenApiConfig.java<p>
 * 创建时间：2025-07-09 16:15<p>
 * 功能描述：OpenApi接口信息配置<p>
 * 创建人：蓝田_Loto
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Java接口项目模版_SpringBoot_适用于Java 8")
                        .description("JavaAPI_SpringBoot")
                        .version("1.0.0"));
    }
}
