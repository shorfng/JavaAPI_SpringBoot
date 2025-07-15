package com.loto.module.order.controller;

import com.loto.common.enums.ResultEnum;
import com.loto.common.response.ResponseResult;
import com.loto.module.order.dto.request.OrderListByIdRequest;
import com.loto.module.order.dto.request.OrderListPageRequest;
import com.loto.module.order.dto.response.OrderListByIdResponse;
import com.loto.module.order.dto.response.OrderListPageResponse;
import com.loto.module.order.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 文件名：OrderController.java<p>
 * 创建时间：2025-07-14 14:13<p>
 * 功能描述：控制层 - 订单管理<p>
 * 创建人：蓝田_Loto
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
@Tag(name = "订单管理")
public class OrderController {
    private final IOrderService orderService;

    /**
     * 订单管理 - 获取订单列表
     */
    @GetMapping("/getOrderListById/")
    @Operation(summary = "获取订单列表")
    public Mono<ResponseResult<OrderListByIdResponse>> getOrderListById(OrderListByIdRequest request) {
        return orderService.getOrderListById(request)
                .map(ResponseResult::success)
                .defaultIfEmpty(ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常"));
    }

    /**
     * 订单管理 - 分页查询订单列表
     */
    @PostMapping("/listPage/")
    @Operation(summary = "分页查询订单列表")
    public Mono<ResponseResult<OrderListPageResponse>> getOrderListPage(@RequestBody OrderListPageRequest request) {
        return orderService.getOrderListPage(request)
                .map(ResponseResult::success)
                .defaultIfEmpty(ResponseResult.failure(ResultEnum.BUSINESS_ERROR.getCode(), "业务异常"));
    }
}
