# 创建数据库
CREATE DATABASE IF NOT EXISTS `JavaAPI_SpringBoot` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 选择数据库
USE `JavaAPI_SpringBoot`;

# 表
CREATE TABLE t_user
(
    -- 主键字段
    C_ID          VARCHAR(36) PRIMARY KEY COMMENT 'UUID主键',

    -- 字符串类型字段
    C_USERNAME    VARCHAR(50)  NOT NULL COMMENT '用户名',
    C_PASSWORD    VARCHAR(100) NOT NULL COMMENT '密码',
    C_EMAIL       VARCHAR(100) COMMENT '电子邮件',
    C_PHONE       VARCHAR(20) COMMENT '电话号码',
    C_ADDRESS     TEXT COMMENT '地址(长文本)',
    C_DESCRIPTION TINYTEXT COMMENT '简短描述',
    C_FULL_NAME   CHAR(100) COMMENT '全名(定长字符串)',

    -- 数值类型字段
    C_AGE         TINYINT UNSIGNED COMMENT '年龄',
    C_HEIGHT      FLOAT COMMENT '身高(米)',
    C_WEIGHT      DOUBLE COMMENT '体重(千克)',
    C_SALARY      DECIMAL(10, 2) COMMENT '薪水',
    C_LOGIN_COUNT INT                   DEFAULT 0 COMMENT '登录次数',
    C_BIG_NUMBER  BIGINT COMMENT '大整数',

    -- 日期时间类型字段
    C_BIRTHDAY    DATE COMMENT '生日',
    C_LOGIN_TIME  DATETIME COMMENT '最后登录时间',
    C_LAST_ACTIVE TIMESTAMP COMMENT '最后活跃时间戳',
    C_YEAR        YEAR COMMENT '入职年份',

    -- 二进制类型字段
    C_AVATAR      BLOB COMMENT '头像图片',
    C_SIGNATURE   LONGBLOB COMMENT '电子签名',

    -- 枚举和集合类型
    C_GENDER      ENUM ('男', '女', '其他') COMMENT '性别',
    C_HOBBIES     SET ('阅读', '运动', '音乐', '旅游') COMMENT '兴趣爱好',

    -- 布尔类型
    C_IS_ACTIVE   BOOLEAN               DEFAULT TRUE COMMENT '是否活跃',

    -- JSON类型
    C_EXTRA_INFO  JSON COMMENT '额外信息(JSON格式)',

    -- 系统字段
    C_CREATE_TIME DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    C_CREATE_USER VARCHAR(50)  NOT NULL COMMENT '创建人',
    C_UPDATE_TIME DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    C_UPDATE_USER VARCHAR(50) COMMENT '修改人',
    C_VERSION     INT                   DEFAULT 1 COMMENT '版本号(用于乐观锁)',
    C_IS_DELETED  TINYINT(1)            DEFAULT 0 COMMENT '是否删除(0-未删除,1-已删除)'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户表';

CREATE TABLE t_business
(
    -- 业务主键字段（复合主键）
    C_ORDER_NO       VARCHAR(30) NOT NULL COMMENT '订单编号(业务唯一标识)',
    C_BUSINESS_TYPE  VARCHAR(20) NOT NULL COMMENT '业务类型(如:销售/采购/退货)',
    C_REGION_CODE    CHAR(6)     NOT NULL COMMENT '区域代码(行政区划编码)',

    -- 业务相关字段
    C_CUSTOMER_ID    VARCHAR(36) COMMENT '客户ID',
    C_SUPPLIER_ID    VARCHAR(36) COMMENT '供应商ID',
    C_PRODUCT_CODE   VARCHAR(50) COMMENT '产品编码',
    C_QUANTITY       INT COMMENT '数量',
    C_UNIT_PRICE     DECIMAL(12, 2) COMMENT '单价',
    C_TOTAL_AMOUNT   DECIMAL(14, 2) COMMENT '总金额',
    C_CURRENCY       CHAR(3)              DEFAULT 'CNY' COMMENT '币种',
    C_ORDER_STATUS   VARCHAR(20) COMMENT '订单状态',
    C_PAYMENT_METHOD VARCHAR(20) COMMENT '支付方式',
    C_DELIVERY_DATE  DATE COMMENT '预计交付日期',

    -- 审批相关字段
    C_APPROVER       VARCHAR(50) COMMENT '审批人',
    C_APPROVE_TIME   DATETIME COMMENT '审批时间',
    C_APPROVE_STATUS TINYINT COMMENT '审批状态(0-未审批,1-已通过,2-已拒绝)',

    -- 备注信息
    C_REMARKS        TEXT COMMENT '备注信息',

    -- 系统字段
    C_CREATE_TIME    DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    C_CREATE_USER    VARCHAR(50) NOT NULL COMMENT '创建人',
    C_UPDATE_TIME    DATETIME ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    C_UPDATE_USER    VARCHAR(50) COMMENT '修改人',
    C_VERSION        INT                  DEFAULT 1 COMMENT '版本号(用于乐观锁)',
    C_IS_DELETED     TINYINT(1)           DEFAULT 0 COMMENT '是否删除(0-未删除,1-已删除)',

    -- 设置复合主键
    PRIMARY KEY (C_ORDER_NO, C_BUSINESS_TYPE, C_REGION_CODE)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='业务订单表';
