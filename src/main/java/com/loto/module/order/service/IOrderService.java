package com.loto.module.order.service;

import com.loto.module.order.dto.request.OrderListByIdRequest;
import com.loto.module.order.dto.response.OrderListByIdResponse;
import reactor.core.publisher.Mono;

/**
 * 文件名：IOrderService.java<p>
 * 创建时间：2025-06-13 15:07<p>
 * 功能描述：服务接口 - 根据 id 查询订单列表<p>
 * 创建人：蓝田_Loto
 */
public interface IOrderService {
    /**
     * 订单管理 - 获取订单列表
     */
    Mono<OrderListByIdResponse>  getOrderListById(OrderListByIdRequest request);
}
