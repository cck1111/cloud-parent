package lagou.edu.service;


import edu.pojo.Resume;

/**
 * @author cck
 * @date 2022/11/9 21:22
 */
public interface ResumeService {

    Resume findDefaultResumeByUserId(Long userId);

}
