package com.loto.module.order.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loto.config.WebClientUrlConfig;
import com.loto.module.order.dto.request.OrderListByIdRequest;
import com.loto.module.order.dto.response.OrderListByIdResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 文件名：OrderListByIdClient.java<p>
 * 创建时间：2025-06-13 15:07<p>
 * 功能描述：客户端 - 根据 id 查询订单列表<p>
 * 创建人：蓝田_Loto
 */
@Component
public class OrderListByIdClient extends BaseApiClient {
    /**
     * 构造函数
     *
     * @param webClient     用于HTTP请求的WebClient
     * @param objectMapper  用于JSON序列化和反序列化的ObjectMapper
     */
    public OrderListByIdClient(WebClient webClient, ObjectMapper objectMapper, WebClientUrlConfig webClientUrlConfig) {
        super(webClient, webClientUrlConfig.ORDER_LIST_BY_ID_URL, objectMapper);
    }

    /**
     * 根据 id 查询订单列表
     *
     * @return 返回响应对象的Mono
     */
    public Mono<OrderListByIdResponse> getOrderListById(OrderListByIdRequest request) {
        // 使用 get 方法请求
        return getWithWrapper("?id=" + request.getId(), OrderListByIdResponse.class);
    }
}
