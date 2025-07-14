package com.loto.module.order.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 文件名：BaseApiClient.java<p>
 * 创建时间：2025-06-16 13:05<p>
 * 功能描述：提供基础 API 客户端功能封装，支持 GET / POST 请求并解析响应。<p>
 * 创建人：蓝田_Loto
 */
public abstract class BaseApiClient {
    // 用于HTTP请求的WebClient实例，保证所有请求都是使用相同的配置和设置
    protected final WebClient webClient;

    // API的基础URL，所有请求都会基于这个URL进行构建
    protected final String baseUrl;

    // 用于JSON序列化和反序列化的ObjectMapper实例，确保数据在传输过程中的格式一致性
    protected final ObjectMapper objectMapper;

    protected BaseApiClient(WebClient webClient, String baseUrl, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.baseUrl = baseUrl;
        this.objectMapper = objectMapper;
    }

    /**
     * 发送 GET 请求，并将响应中的 body 字段反序列化为目标类型。
     *
     * @param path     请求路径（相对于 baseUrl）
     * @param bodyType 响应体的目标类型
     * @param <T>      泛型类型
     * @return 包含目标类型的 Mono 结果
     */
    protected <T> Mono<T> getWithWrapper(String path, Class<T> bodyType) {
        return webClient.get()
                .uri(baseUrl + path)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    try {
                        // 解析 JSON 响应
                        JsonNode rootNode = objectMapper.readTree(response);
                        JsonNode bodyNode = rootNode.path("body");  // 如果数据的node叫别的名字，需要修改
                        // 检查是否存在 body 字段
                        if (bodyNode.isMissingNode()) {
                            return Mono.error(new RuntimeException("API响应格式不正确，缺少 body 字段"));
                        }
                        // 将 body 内容转换为指定类型
                        T result = objectMapper.readValue(bodyNode.toString(), bodyType);
                        return Mono.just(result);
                    } catch (Exception e) {
                        // 捕获解析异常，返回统一错误信息
                        return Mono.error(new RuntimeException("API响应解析失败: " + e.getMessage()));
                    }
                });
    }

    /**
     * 发送 POST 请求，并将响应中的 body 字段反序列化为目标类型。
     *
     * @param path         请求路径（相对于 baseUrl）
     * @param requestBody  请求体对象
     * @param responseType 响应体的目标类型
     * @param <T>          泛型类型
     * @return 包含目标类型的 Mono 结果
     */
    protected <T> Mono<T> postWithWrapper(String path, Object requestBody, Class<T> responseType) {
        return webClient.post()
                .uri(baseUrl + path)
                .body(Mono.just(requestBody), requestBody.getClass())
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    try {
                        // 解析 JSON 响应
                        JsonNode rootNode = objectMapper.readTree(response);
                        JsonNode bodyNode = rootNode.path("body"); // 如果数据的node叫别的名字，需要修改
                        // 检查是否存在 body 字段
                        if (bodyNode.isMissingNode()) {
                            return Mono.error(new RuntimeException("API响应格式不正确"));
                        }
                        // 将 body 内容转换为指定类型
                        T result = objectMapper.readValue(bodyNode.toString(), responseType);
                        return Mono.just(result);
                    } catch (Exception e) {
                        // 捕获解析异常，返回统一错误信息
                        return Mono.error(new RuntimeException("API响应解析失败: " + e.getMessage()));
                    }
                });
    }
}
