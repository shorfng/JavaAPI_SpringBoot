package com.loto.module.order.dto.request;
import lombok.Data;

/**
 * 文件名：OrderListByIdRequest.java<p>
 * 创建时间：2025-06-13 15:07<p>
 * 功能描述：请求DTO - 根据 id 查询订单列表<p>
 * 创建人：蓝田_Loto
 */
@Data
public class OrderListByIdRequest {
    private String id; // 订单id
}
