package com.lagou.edu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cck
 * @date 2022/11/16 21:06
 */
@RestController
@RequestMapping("/test")
public class testController {

    @GetMapping("/testGateWay")
    public int testGateWay(){
        return 1;
    }
}
