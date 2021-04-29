package com.example.concerto.dao;

import com.example.concerto.pojo.TaskVersion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/26 下午9:14
 */
@Mapper
public interface TaskVersionDao {

    /**
     * 将一个任务的版本插入数据库
     * @param taskVersion
     * @return
     */
    Long addTaskVerison(TaskVersion taskVersion);

    /**
     * 修改任务标题
     * 注：主要是给子任务用的:D
     * @param taskId
     * @param taskTitle
     */
    @Update("update task_version set task_title = #{taskTitle} where task_id = #{taskId}")
    void modifyTaskTitle(Long taskId,String taskTitle);

    /**
     * 删除某个任务，taskVersion之后的版本
     * @param taskId
     * @param taskVersion
     * @return
     */
    @Delete("delete from task_version where task_id = #{taskId} and task_version > #{taskVersion}")
    Integer deleteTaskVersionAfter(Long taskId,Integer taskVersion);

    /**
     * 删除任务的某个版本信息
     * @param taskId
     * @param taskVersion
     * @return
     */
    @Delete("delete from task_version where task_id = #{taskId} and task_version = #{taskVersion}")
    Integer deleteTaskVersion(Long taskId,Integer taskVersion);
}
