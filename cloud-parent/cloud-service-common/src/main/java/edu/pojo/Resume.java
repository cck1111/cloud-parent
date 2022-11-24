package edu.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author cck
 * @date 2022/11/9 17:37
 */
@Data
@TableName("r_resume")
public class Resume {

    @TableId(type = IdType.AUTO)
    private Long id; // 主键
    private String sex; // 性别
    private String birthday; // 生日
    private String work_year; // 工作年限
    private String phone; // 手机号
    private String email; // 邮箱
    private String status; // 目前状态
    private String resumeName; // 简历名称
    private String name; // 姓名
    private String createTime; // 创建时间
    private String headPic; // 头像
    private Integer isDel; //是否删除 默认值0-未删除 1-已删除
    private String updateTime; // 简历更新时间
    private Long userId; // 用户ID
    private Integer isDefault; // 是否为默认简历 0-默认 1-非默认
    private String highestEducation; // 最高学历
    private Integer deliverNearByConfirm; // 投递附件简历确认 0-需要确认 1-不需要确认
    private Integer refuseCount; // 简历被拒绝次数
    private Integer markCanInterviewCount; //被标记为可面试次数
    private Integer haveNoticeInterCount; //已通知面试次数
    private String oneWord; // 一句话介绍自己
    private String liveCity; // 居住城市
    private Integer resumeScore; // 简历得分
    private Integer userIdentity; // 用户身份1-学生 2-工人
    private Integer isOpenResume; // 人才搜索-开放简历 0-关闭，1-打开，2-简历未达到投放标准被动关闭 3-从未设置过开放简历

}
