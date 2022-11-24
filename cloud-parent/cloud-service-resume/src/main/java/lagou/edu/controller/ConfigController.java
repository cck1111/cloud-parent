package lagou.edu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @RefreshScope 使用到github 远程配置中心时 手动刷新需添加此注解
 * @author cck
 * @date 2022/11/17 16:01
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${test.value}")
    private String value;

    @GetMapping("/viewConfig")
    public String viewConfig(){
        return value;
    }
}
