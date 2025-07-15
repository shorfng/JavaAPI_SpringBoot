package com.loto.module.order.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loto.config.WebClientUrlConfig;
import com.loto.module.order.dto.request.OrderListPageRequest;
import com.loto.module.order.dto.response.OrderListPageResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * 文件名：OrderListByIdClient.java<p>
 * 创建时间：2025-06-13 15:07<p>
 * 功能描述：客户端 - 分页查询订单列表<p>
 * 创建人：蓝田_Loto
 */
@Component
public class OrderListPageClient extends BaseApiClient {
    /**
     * 构造函数
     *
     * @param webClient    用于HTTP请求的WebClient
     * @param objectMapper 用于JSON序列化和反序列化的ObjectMapper
     */
    public OrderListPageClient(WebClient webClient, ObjectMapper objectMapper, WebClientUrlConfig webClientUrlConfig) {
        super(webClient, webClientUrlConfig.ORDER_LIST_PAGE_URL, objectMapper);
    }

    /**
     * 分页查询订单列表
     *
     * @return 返回响应对象的Mono
     */
    public Mono<OrderListPageResponse> getOrderListPage(OrderListPageRequest request) {
        // 使用 POST 方法请求
        return postWithWrapper("", request, OrderListPageResponse.class);
    }
}
