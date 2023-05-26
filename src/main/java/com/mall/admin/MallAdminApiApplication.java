package com.mall.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableFeignClients
@MapperScan(basePackages = "com/mall/admin/mapper")
@SpringBootApplication
@EnableSwagger2
public class MallAdminApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallAdminApiApplication.class, args);
    }

}
