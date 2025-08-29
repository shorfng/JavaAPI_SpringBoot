package com.loto.module.business.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 文件名：Business.java<p>
 * 创建时间：2025-08-27 09:48:44<p>
 * 功能描述：实体类 - 业务订单表<p>
 * 创建人：蓝田_Loto
 */
@Getter
@Setter
@ToString
@TableName("t_business")
@Schema(name = "Business", description = "业务订单表")
public class Business implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号(业务唯一标识)
     */
    @MppMultiId
    @Schema(description = "订单编号(业务唯一标识)")
    private String cOrderNo;

    /**
     * 业务类型(如:销售/采购/退货)
     */
    @MppMultiId
    @Schema(description = "业务类型(如:销售/采购/退货)")
    private String cBusinessType;

    /**
     * 区域代码(行政区划编码)
     */
    @MppMultiId
    @Schema(description = "区域代码(行政区划编码)")
    private String cRegionCode;

    /**
     * 客户ID
     */
    @Schema(description = "客户ID")
    private String cCustomerId;

    /**
     * 供应商ID
     */
    @Schema(description = "供应商ID")
    private String cSupplierId;

    /**
     * 产品编码
     */
    @Schema(description = "产品编码")
    private String cProductCode;

    /**
     * 数量
     */
    @Schema(description = "数量")
    private Integer cQuantity;

    /**
     * 单价
     */
    @Schema(description = "单价")
    private BigDecimal cUnitPrice;

    /**
     * 总金额
     */
    @Schema(description = "总金额")
    private BigDecimal cTotalAmount;

    /**
     * 币种
     */
    @Schema(description = "币种")
    private String cCurrency;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    private String cOrderStatus;

    /**
     * 支付方式
     */
    @Schema(description = "支付方式")
    private String cPaymentMethod;

    /**
     * 预计交付日期
     */
    @Schema(description = "预计交付日期")
    private Date cDeliveryDate;

    /**
     * 审批人
     */
    @Schema(description = "审批人")
    private String cApprover;

    /**
     * 审批时间
     */
    @Schema(description = "审批时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cApproveTime;

    /**
     * 审批状态(0-未审批,1-已通过,2-已拒绝)
     */
    @Schema(description = "审批状态(0-未审批,1-已通过,2-已拒绝)")
    private Byte cApproveStatus;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    private String cRemarks;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cCreateTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String cCreateUser;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cUpdateTime;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private String cUpdateUser;

    /**
     * 版本号(用于乐观锁)
     */
    @Schema(description = "版本号(用于乐观锁)")
    private Integer cVersion;

    /**
     * 是否删除(0-未删除,1-已删除)
     */
    @Schema(description = "是否删除(0-未删除,1-已删除)")
    private Boolean cIsDeleted;
}
