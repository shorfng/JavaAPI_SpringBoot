package com.loto.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

/**
 * 文件名：WebClientConfig.java<p>
 * 创建时间：2025-07-09 16:15<p>
 * 功能描述：用于定义 WebClient 和 ObjectMapper 的 Bean<p>
 * 创建人：蓝田_Loto
 */
@Configuration
public class WebClientConfig {
    // 定义 HTTP 请求超时时间常量，便于统一管理和修改
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    /**
     * 创建并配置 WebClient 实例
     *
     * @return 配置好的 WebClient 实例
     */
    @Bean
    public WebClient webClient() {
        // 创建 HttpClient 实例，并设置响应超时时间
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(REQUEST_TIMEOUT);

        // 构建 WebClient 实例，并设置默认请求头
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    /**
     * 创建并配置 ObjectMapper 实例
     *
     * @return 配置好的 ObjectMapper 实例
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 忽略未知字段
    }
}
