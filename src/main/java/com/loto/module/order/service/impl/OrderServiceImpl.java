package com.loto.module.order.service.impl;

import com.loto.module.order.client.OrderListByIdClient;
import com.loto.module.order.dto.request.OrderListByIdRequest;
import com.loto.module.order.dto.response.OrderListByIdResponse;
import com.loto.module.order.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * 文件名：OrderServiceImpl.java<p>
 * 创建时间：2025-06-13 15:07<p>
 * 功能描述：服务层 - 根据 id 查询订单列表<p>
 * 创建人：蓝田_Loto
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderListByIdClient orderListByIdClient;

    /**
     * 订单管理 - 获取订单列表
     */
    @Override
    public Mono<OrderListByIdResponse> getOrderListById(OrderListByIdRequest request) {
        return orderListByIdClient.getOrderListById(request)
                .onErrorResume(e -> {
                    logger.error(" 获取订单信息失败，id={}", request.getId(), e);
                    return Mono.error(new RuntimeException("获取订单信息失败"));
                });
    }
}
