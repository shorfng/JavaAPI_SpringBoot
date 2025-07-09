package com.loto.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件名：RequestLogAspect.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：请求日志切面类，用于记录控制器方法的请求信息（请求方法、URL、参数、耗时和返回结果）<p>
 * 创建人：蓝田_Loto
 */
@Aspect
@Component
public class RequestLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

    /**
     * 记录请求日志的方法
     * 该方法环绕执行指定包下的所有控制器方法
     *
     * @param joinPoint 连接点对象，包含目标方法的信息
     * @return Object 目标方法的返回值
     * @throws Throwable 如果目标方法抛出异常，则重新抛出
     */
    @Around("execution(* com.loto.module..*Controller.*(..))")
    public Object logRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名并提取方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();

        // 获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

        // 提取请求的基本信息
        String url = request != null ? request.getRequestURL().toString() : "unknown";
        String httpMethod = request != null ? request.getMethod() : "unknown";

        // 获取方法调用参数
        Object[] args = joinPoint.getArgs();

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        try {
            // 如果是 downloadFile 方法，则不序列化参数和结果
            if ("downloadFile".equals(methodName)) {
                return proceedAndLogBasic(joinPoint, httpMethod, methodName, url, startTime);
            }

            // 非 downloadFile 方法正常记录日志
            ObjectMapper objectMapper = createObjectMapper();

            // 执行目标方法
            Object result = joinPoint.proceed();

            // 序列化参数和结果
            String argsJson = objectMapper.writeValueAsString(args);
            String resultJson = objectMapper.writeValueAsString(result);

            // 记录详细日志
            logDetailedRequest(httpMethod, methodName, url, argsJson, resultJson, System.currentTimeMillis() - startTime);

            return result;
        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("==> 接口异常: {}.{}", httpMethod, methodName, e);
            logger.error("==> 耗时: {} ms", duration);
            throw e;
        }
    }

    /**
     * 创建并配置 ObjectMapper 实例
     */
    private ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return objectMapper;
    }

    /**
     * 执行方法并记录基础日志（适用于 downloadFile）
     */
    private Object proceedAndLogBasic(ProceedingJoinPoint joinPoint, String httpMethod, String methodName, String url, long startTime) throws Throwable {
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;

        logger.info(
                "\n" +
                        "\n" +
                        "=========================================================================\n" +
                        "==> 接口访问: {}.{}\n" +
                        "==> 请求地址: {}\n" +
                        "==> 耗时: {} ms\n" +
                        "==> 文件下载接口，跳过参数与结果的日志记录\n" +
                        "=========================================================================\n",
                httpMethod, methodName, url, duration
        );

        return result;
    }

    /**
     * 记录详细的请求日志（适用于非 downloadFile 的接口）
     */
    private void logDetailedRequest(String httpMethod, String methodName, String url, String argsJson, String resultJson, long duration) {
        logger.info(
                "\n" +
                        "\n" +
                        "=========================================================================\n" +
                        "==> 接口访问: {}.{}\n" +
                        "==> 请求地址: {}\n" +
                        "==> 请求参数: {}\n" +
                        "==> 耗时: {} ms\n" +
                        "==> 返回结果: {}\n" +
                        "=========================================================================\n",
                httpMethod, methodName, url, argsJson, duration, resultJson
        );
    }
}
