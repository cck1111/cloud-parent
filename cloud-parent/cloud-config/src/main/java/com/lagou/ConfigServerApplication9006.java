package com.lagou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @EnableConfigServer 开启配置服务功能
 *
 * @author cck
 * @date 2022/11/17 15:09
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApplication9006 {

    public static void main(String[] args) {

        SpringApplication.run(ConfigServerApplication9006.class,args);
    }
}
