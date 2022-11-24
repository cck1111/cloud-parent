package com.lagou.edu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cck
 * @date 2022/11/16 19:03
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GetWayApplication9002 {

    public static void main(String[] args) {
        SpringApplication.run(GetWayApplication9002.class,args);
    }
}
