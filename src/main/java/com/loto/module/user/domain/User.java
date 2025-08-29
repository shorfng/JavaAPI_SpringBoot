package com.loto.module.user.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 文件名：User.java<p>
 * 创建时间：2025-06-27 15:42<p>
 * 功能描述：实体类 - 用户表<p>
 * 创建人：蓝田_Loto
 */
@Data
@TableName("t_user")
@Schema(name = "User", description = "用户表")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * UUID主键 - 新增自动生成
     */
    @TableId(type = IdType.ASSIGN_UUID, value = "C_ID")
    @Schema(description = "UUID主键")
    private String cId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String cUsername;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String cPassword;

    /**
     * 电子邮件
     */
    @Schema(description = "电子邮件")
    private String cEmail;

    /**
     * 电话号码
     */
    @Schema(description = "电话号码")
    private String cPhone;

    /**
     * 地址(长文本)
     */
    @Schema(description = "地址(长文本)")
    private String cAddress;

    /**
     * 简短描述
     */
    @Schema(description = "简短描述")
    private String cDescription;

    /**
     * 全名(定长字符串)
     */
    @Schema(description = "全名(定长字符串)")
    private String cFullName;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    private Byte cAge;

    /**
     * 身高(米)
     */
    @Schema(description = "身高(米)")
    private Double cHeight;

    /**
     * 体重(千克)
     */
    @Schema(description = "体重(千克)")
    private Double cWeight;

    /**
     * 薪水
     */
    @Schema(description = "薪水")
    private BigDecimal cSalary;

    /**
     * 登录次数
     */
    @Schema(description = "登录次数")
    private Integer cLoginCount;

    /**
     * 大整数
     */
    @Schema(description = "大整数")
    private Long cBigNumber;

    /**
     * 生日
     */
    @Schema(description = "生日")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cBirthday;

    /**
     * 最后登录时间
     */
    @Schema(description = "最后登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cLoginTime;

    /**
     * 最后活跃时间戳
     */
    @Schema(description = "最后活跃时间戳")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cLastActive;

    /**
     * 入职年份（数据库类型 YEAR）
     */
    @Schema(description = "入职年份")
    private Integer cYear;

    /**
     * 头像图片
     */
    @Schema(description = "头像图片")
    private byte[] cAvatar;

    /**
     * 电子签名
     */
    @Schema(description = "电子签名")
    private byte[] cSignature;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private String cGender;

    /**
     * 兴趣爱好
     */
    @Schema(description = "兴趣爱好")
    private String cHobbies;

    /**
     * 是否活跃
     */
    @Schema(description = "是否活跃")
    private Boolean cIsActive;

    /**
     * 额外信息(JSON格式)
     */
    @Schema(description = "额外信息(JSON格式)")
    private String cExtraInfo;

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
