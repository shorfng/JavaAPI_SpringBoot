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
    C_GENDER      ENUM ('MALE', 'FEMALE', 'OTHER') COMMENT '性别',
    C_HOBBIES     SET ('READING', 'SPORTS', 'MUSIC', 'TRAVEL') COMMENT '兴趣爱好',

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
