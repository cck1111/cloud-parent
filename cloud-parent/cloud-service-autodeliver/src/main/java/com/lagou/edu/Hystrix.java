package com.lagou.edu;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author cck
 * @date 2022/11/14 15:07
 */
@RestController
@RequestMapping("/autodeliver")
public class Hystrix {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     *  @HystrixCommand 注解进行熔断控制
     *  测试 Hystrix 熔断
     * @return
     */
    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "findResumeOpenStateTimeout",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "2") , // 线程数
                    @HystrixProperty(name = "maxQueueSize",value = "20") // 等待队列长度
            },
            // commandProperties熔断的一些细节属性配置
            commandProperties = {
                    // 每个属性都是一个HystrixProperty
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "2000"),
                    // hystrix 高级配置，定制工作过程细节
                    // 统计时间窗口定义
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                    // 统计时间窗口内的最小请求数
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2"),
                    // 统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    // 自我修复时的活动窗口长度
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "3000")
            },
            fallbackMethod = "myFallBack"  // 退回方法
    )
    @GetMapping("/testHystrix/{userId}")
    public Integer testHystrix(@PathVariable Long userId) { // userId=2195320
        // 使用ribbon不需要我们自己获取服务实例然后选择一个那么去访问了（自己的负载均衡）  lagou-service-resume -> 指定服务名
        Integer forObject = restTemplate.getForObject("http://lagou-service-resume/resume/openstate/"+userId, Integer.class);
        System.out.println("所调用的服务提供者" + forObject);
        return forObject;
    }

    /**
     *  定义回退方法，返回预设默认值
     *  注意：该方法形参和返回值 与原始方法保持一致
     * @return
     */
    public Integer myFallBack(Long userId){
        return -12333;
    }

    /**
     *  1)服务提供者处理超时，熔断，返回错误信息
     *  2)有可能服务提供者出现异常直接抛出异常信息
     *
     *  以上信息，都会返回到消费者这里，很多时候消费者服务不希望把收到异常信息再抛到它的上有去
     *      用户微服务 - 注册微服务 - 优惠券微服务
     *          1 登记注册
     *          2 分发优惠券(并不是核心步骤)，这里如果调用优惠券服务返回异常信息或者是熔断后
     *   的错误信息，这些信息如果抛给用很不友好。
     *          此时，我们可以返回一个兜底数据，预设的默认默认值(服务降级)
     *
     */


    /**
     *  Hystrix 舱壁模式
     */

    @HystrixCommand(
            threadPoolKey = "findCangBiResumeOpenStateTimeout",  //
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "2"),
                    @HystrixProperty(name = "maxQueueSize",value = "20")
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="2000")
            }
    )
    @GetMapping("/checkCangBiStateTimeOut/{userId}")
    public Integer findCangBiResumeOpenStateTimeout(@PathVariable Long userId){
        String url = "http://lagou-service-resume/resume/openstate/" + userId;

        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    @HystrixCommand(
            threadPoolKey = "findCangBiResumeOpenStateTimeoutFallback", //
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "2"),
                    @HystrixProperty(name = "maxQueueSize",value = "20")
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="2000")
            },
            fallbackMethod = "myFallBack"   // 回退方法
    )
    @GetMapping("/checkCangBiStateTimeOutFallback/{userId}")
    public Integer findCangBiResumeOpenStateTimeoutFallback(@PathVariable Long userId){
        String url = "http://lagou-service-resume/resume/openstate/" + userId;

        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
}
