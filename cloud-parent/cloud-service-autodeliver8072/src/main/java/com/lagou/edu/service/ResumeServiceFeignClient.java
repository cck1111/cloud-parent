package com.lagou.edu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @FeignClient 表明当前类是一个Feign客户端，value指定该客户端要请求的服务名称（登记到注册中心上的服务提供者的服务名称）
 * name: 调用的服务名称，和服务提供者yml文件中spring.application.name
 * @author cck
 * @date 2022/11/15 20:46
 */
@FeignClient(name = "lagou-service-resume",fallback = ResumeFallback.class,path = "/resume")
// 使用fallback的时候，类上的@RequestMapping的url前缀限定，改成配置在@FeignClient的path属性中
// @RequestMapping("/resume")
public interface ResumeServiceFeignClient {

    /**
     *  调用的请求路径
     * @param userId
     * @return
     */
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable(value = "userId") Long userId);

}
