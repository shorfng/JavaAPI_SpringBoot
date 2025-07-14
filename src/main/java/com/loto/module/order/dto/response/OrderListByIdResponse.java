package com.loto.module.order.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 文件名：OrderListByIdResponse.java<p>
 * 创建时间：2025-06-13 15:07<p>
 * 功能描述：响应DTO - 根据 id 查询订单列表<p>
 * 创建人：蓝田_Loto
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)  // 忽略未知字段
public class OrderListByIdResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;
}
