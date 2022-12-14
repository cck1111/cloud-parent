package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @EnableTurbine  开启聚合功能
 * @author cck
 * @date 2022/11/14 18:41
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableTurbine
public class HystrixTurbineApplication9001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixTurbineApplication9001.class,args);
    }
}
