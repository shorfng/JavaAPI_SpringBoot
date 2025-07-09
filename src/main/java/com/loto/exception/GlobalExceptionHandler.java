package com.loto.exception;

import com.loto.common.response.ResponseResult;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 文件名：GlobalExceptionHandler.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：全局异常处理器<p>
 * 创建人：蓝田_Loto
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<?> handleException(Exception ex) {
        logger.error("发生未知异常", ex); // 打印所有未被捕获的异常
        return ResponseResult.failure(500, "系统异常");
    }

    @ExceptionHandler(PersistenceException.class)
    @ResponseBody
    public ResponseResult<?> handleMyBatisException(PersistenceException ex) {
        logger.error("MyBatis 操作异常", ex); // 单独处理 MyBatis 异常
        return ResponseResult.failure(60001, "数据库操作异常");
    }
}
