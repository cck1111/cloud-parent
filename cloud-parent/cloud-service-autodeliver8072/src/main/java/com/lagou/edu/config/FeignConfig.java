package com.lagou.edu.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author cck
 * @date 2022/11/16 9:57
 */
@Configuration
public class FeignConfig {

    /**
     * Feign的日志级别(Feign请求过程信息)
     * NONE: 默认的，不显示任何日志 --- 性能最好
     * BASIC: 仅记录请求方法，URL，响应状态码以及执行时间  --- 生产问题追踪
     * HEADERS: 在BASIC级别的基础上，记录请求和响应的header
     * FULL: 记录请求和响应的header,body和元数据 --- 适用于开发及测试环境定位问题
     */
    @Bean
    Logger.Level feignLevel(){
        return Logger.Level.FULL;
    }
}
