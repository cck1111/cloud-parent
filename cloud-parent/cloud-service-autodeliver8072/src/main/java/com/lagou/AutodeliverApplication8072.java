package com.lagou;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @EnableDiscoveryClient // 开启服务发现   如果开启feign 不需要此注解
 * @EnableCircuitBreaker  // 开启熔断
 * @SpringCloudApplication  = @SpringBootApplication + @EnableDiscoveryClient + @EnableCircuitBreaker
 *
 * @author cck
 * @date 2022/11/10 15:21
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AutodeliverApplication8072 {

    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplication8072.class,args);
    }

    /** @LoadBalanced   ribbon负载均衡
     *  注入RestTemplate
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate getResTemplate(){
        return new RestTemplate();
    }

    /**
     *  在被检测的微服务中注册监控servlet 后期我们就是通过访问这个servlet来获取该服务的Hystrix监控数据的
     *  前提: 被监控的微服务需要引入springboot 的actuator功能
     */

    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> registrationBean = new ServletRegistrationBean<>(streamServlet);
        registrationBean.setLoadOnStartup(1);

        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
