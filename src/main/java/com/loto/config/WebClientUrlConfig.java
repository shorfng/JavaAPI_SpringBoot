package com.loto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 文件名：WebClientUrlConfig.java<p>
 * 创建时间：2025-07-09 16:15<p>
 * 功能描述：调用接口的 URL 配置<p>
 * 创建人：蓝田_Loto
 */
@Component
public class WebClientUrlConfig {
    public final String ORDER_LIST_BY_ID_URL;   // 根据 id 查询订单列表
    public final String ORDER_LIST_PAGE_URL;    // 分页查询订单列表

    public WebClientUrlConfig(@Value("${web-client-url.order-list-by-id-url}") String orderListByIdUrl,
                              @Value("${web-client-url.order-list-page-url}") String orderListPageUrl) {
        this.ORDER_LIST_BY_ID_URL = orderListByIdUrl;
        this.ORDER_LIST_PAGE_URL = orderListPageUrl;
    }
}
