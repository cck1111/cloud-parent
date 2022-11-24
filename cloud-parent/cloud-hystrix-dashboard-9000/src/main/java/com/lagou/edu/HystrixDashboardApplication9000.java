package com.lagou.edu;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableHystrixDashboard 开去hystrix dashboard
 *
 * @author cck
 * @date 2022/11/14 16:47
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableHystrixDashboard
public class HystrixDashboardApplication9000 {

    public static void main(String[] args) {

        SpringApplication.run(HystrixDashboardApplication9000.class,args);
    }
}
