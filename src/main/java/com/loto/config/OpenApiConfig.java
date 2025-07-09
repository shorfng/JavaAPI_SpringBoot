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
        io.swagger.v3.oas.models.info.Contact contact = new io.swagger.v3.oas.models.info.Contact()
                .name("蓝田_Loto")
                .email("shorfng@126.com");

        return new OpenAPI()
                .info(new Info()
                        .title("JavaAPI_SpringBoot")
                        .description("Java接口项目模版_SpringBoot_适用于Java 8")
                        .contact(contact)
                        .version("1.0.0"));
    }
}
