package com.loto;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.loto.**.mapper") // 扫描所有子包中的 mapper 包
@EnableMPP // 启用 MyBatis Plus - 复合主键功能
// 使用Tomcat启动，打war包使用
//public class JavaApiSpringBootApplication extends SpringBootServletInitializer {
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(JavaApiSpringBootApplication.class);  // 指定启动类
//    }

// 使用springboot启动
public class JavaApiSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaApiSpringBootApplication.class, args);
    }
}
