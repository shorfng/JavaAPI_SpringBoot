package com.loto.module.order.dto.request;
import lombok.Data;

/**
 * 文件名：OrderListPageRequest.java<p>
 * 创建时间：2025-06-13 15:07<p>
 * 功能描述：请求DTO - 分页查询订单列表<p>
 * 创建人：蓝田_Loto
 */
@Data
public class OrderListPageRequest {
    private String pageNum;  // 当前页
    private String pageSize; // 每页多少条
}
