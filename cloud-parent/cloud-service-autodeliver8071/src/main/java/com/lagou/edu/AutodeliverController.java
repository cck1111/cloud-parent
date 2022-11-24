package com.lagou.edu;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author cck
 * @date 2022/11/10 15:17
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 1.获取Eureka中注册的user-service 实例列表
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("lagou-service-resume");
        // 2.获取实例(不考录负载，取第一个)
//        for (ServiceInstance serviceInstance : serviceInstances) {
//            Map<String, String> metadata = serviceInstance.getMetadata();
//        }
        ServiceInstance serviceInstance = serviceInstances.get(0);
        //3.根据实例的信息拼接请求地址
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://"+host+":"+port+"/resume/openstate/"+userId;
        //4.消费者直接调用提供者
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        System.out.println("======>>>调⽤简历微服务，获取到⽤户" + userId + "的默认简历当前状态为：" + forObject);
        return forObject;
    }

    /**
     *  测试 Ribbon负载均衡
     * @return
     */
    @GetMapping("/testRibbon")
    public Integer testRibbon() {
        Integer forObject = restTemplate.getForObject("http://lagou-service-resume/resume/openstate/2195320", Integer.class);
        System.out.println("所调用的服务提供者" + forObject);
        return forObject;
    }



}
