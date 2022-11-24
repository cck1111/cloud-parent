package com.lagou.edu;

import com.lagou.edu.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cck
 * @date 2022/11/15 21:32
 */
@RestController
@RequestMapping("/autodeliver")
public class Feign {

    @Autowired
    private ResumeServiceFeignClient resumeServiceFeignClient;

    @GetMapping("/testFeign/{userId}")
    public Integer testFeign(@PathVariable(value = "userId") Long userId){

        return resumeServiceFeignClient.findDefaultResumeState(userId);
    }
}
