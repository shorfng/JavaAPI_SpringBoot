package com.loto.module.base.dto;

import lombok.Data;

/**
 * 文件名：PageParamDTO.java<p>
 * 创建时间：2025-07-11 17:11<p>
 * 功能描述：分页查询参数<p>
 * 创建人：蓝田_Loto
 */
@Data
public class PageParamDTO<T> {
    /**
     * 分页的当前页，默认为1
     */
    private Integer pageNum = 1;

    /**
     * 分页每页显示的记录数，默认为10
     */
    private Integer pageSize = 10;

    /**
     * 正序排序的字段名称，多个用英文逗号隔开
     */
    private String ascName;

    /**
     * 倒序排序的字段名称，多个用英文逗号隔开
     */
    private String descName;

    /**
     * 实体类参数
     */
    private T params;
}
