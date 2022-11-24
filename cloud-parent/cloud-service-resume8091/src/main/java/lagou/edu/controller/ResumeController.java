package lagou.edu.controller;

import lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cck
 * @date 2022/11/9 21:27
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private ResumeService resumeService;

    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId) {
        //return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();

        return port;
    }

}
