package com.example.banka;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableEurekaClient
@EnableDistributedTransaction
@EnableFeignClients
public class BankAApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAApplication.class, args);
    }


    /*
    *
    * 启动成功后, 到bank-a  Service 中制造一个异常,做测试.
    * 如果2个应用操作数据库完了之后, 报错了,2个库同时回滚
    *  制造异常,让程序回滚DB操作
    * throw new RuntimeException("error runtime");
    *  返回页面一个500错误, 2个数据库均未被更改
    *
    * */

}
