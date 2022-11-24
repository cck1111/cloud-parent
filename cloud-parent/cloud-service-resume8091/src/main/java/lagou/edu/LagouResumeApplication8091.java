package lagou.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author cck
 * @date 2022/11/9 21:29
 */
@SpringBootApplication
@MapperScan("com.lagou.edu.mapper")
@EnableEurekaClient
public class LagouResumeApplication8091 {
    public static void main(String[] args) {

        SpringApplication.run(LagouResumeApplication8091.class, args);
    }

}
