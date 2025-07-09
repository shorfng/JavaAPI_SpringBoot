package com.loto.common.enums;

import lombok.Getter;

/**
 * 文件名：ResultEnum.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：统一返回结果状态码枚举<p>
 * 创建人：蓝田_Loto
 */
public enum ResultEnum {
    /* 成功类状态码 (2xx) */
    SUCCESS(200, "成功"),
    CREATED(201, "资源创建成功"),
    ACCEPTED(202, "请求已接受"),

    /* 客户端错误类 (4xx) */
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "未授权访问"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "HTTP方法不允许"),
    CONFLICT(409, "资源冲突"),

    /* 服务端错误类 (5xx) */
    INTERNAL_ERROR(500, "系统内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    GATEWAY_TIMEOUT(504, "网关超时"),

    /* 业务自定义状态码 (6xxxx) */
    BUSINESS_ERROR(60001, "业务异常"),
    DATA_VALIDATION_FAILED(60002, "数据校验失败"),
    DUPLICATE_OPERATION(60003, "重复操作");

    @Getter
    private final int code;

    @Getter
    private final String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
