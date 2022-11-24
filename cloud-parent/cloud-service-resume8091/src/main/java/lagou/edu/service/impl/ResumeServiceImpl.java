package lagou.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.pojo.Resume;
import lagou.edu.mapper.ResumeMapper;
import lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cck
 * @date 2022/11/9 21:22
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeMapper resumeMapper;
    @Override
    public Resume findDefaultResumeByUserId(Long userId) {

        QueryWrapper<Resume> wrapper = new QueryWrapper<>();
        wrapper.eq("id",userId).eq("isDefault",1);
        return resumeMapper.selectOne(wrapper);
    }
}
