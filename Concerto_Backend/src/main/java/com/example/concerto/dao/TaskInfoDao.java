package com.example.concerto.dao;

import com.example.concerto.pojo.Tag;
import com.example.concerto.pojo.Task;
import com.example.concerto.pojo.User;
import com.example.concerto.vo.PersonnelVo;
import com.example.concerto.vo.TaskCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/25 下午2:41
 */
@Mapper
public interface TaskInfoDao {

    /**
     * 获取本任务的所有tag
     * @param taskId
     * @return
     */
    Set<Tag> selectTagsByTaskId(Long taskId);

    /**
     * 获取本任务的所有参与者
     * @param taskId
     * @return
     */
    Set<PersonnelVo> selectParticipantsByTaskId(Long taskId);

    /**
     * 获取本任务的所有评论
     * @param taskId
     * @return
     */
    List<TaskCommentVo> selectCommentsByTaskId(Long taskId);

}
