package com.loto.common.response;

import com.loto.common.enums.ResultEnum;
import lombok.Builder;
import lombok.Data;

/**
 * 文件名：ResponseResult.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：统一响应结果封装类，适用于 Spring MVC 接口返回<p>
 * 创建人：蓝田_Loto
 */
@Data
@Builder
public class ResponseResult<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 成功响应，携带数据和默认消息
     */
    public static <T> ResponseResult<T> success(T data) {
        return ResponseResult.<T>builder()
                .code(ResultEnum.SUCCESS.getCode())
                .message("操作成功")
                .data(data)
                .build();
    }

    /**
     * 成功响应，携带数据和自定义消息
     */
    public static <T> ResponseResult<T> success(T data, String message) {
        return ResponseResult.<T>builder()
                .code(ResultEnum.SUCCESS.getCode())
                .message(message)
                .data(data)
                .build();
    }

    /**
     * 成功响应，无数据
     */
    public static ResponseResult<Void> success() {
        return ResponseResult.<Void>builder()
                .code(ResultEnum.SUCCESS.getCode())
                .message("操作成功")
                .data(null)
                .build();
    }

    /**
     * 失败响应，使用 ResultEnum 枚举
     */
    public static ResponseResult<Void> failure(ResultEnum resultEnum) {
        return ResponseResult.<Void>builder()
                .code(resultEnum.getCode())
                .message(resultEnum.getMessage())
                .data(null)
                .build();
    }

    /**
     * 失败响应，自定义错误码和消息
     */
    public static <T> ResponseResult<T> failure(int code, String message) {
        return ResponseResult.<T>builder()
                .code(code)
                .message(message == null ? "未知错误" : message)
                .data(null)
                .build();
    }
}
