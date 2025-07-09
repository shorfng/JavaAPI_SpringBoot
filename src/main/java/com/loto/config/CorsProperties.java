package com.loto.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文件名：CorsProperties.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：跨域配置<p>
 * 创建人：蓝田_Loto
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private List<String> allowedOrigins;

}
