package com.example.concerto.dao;

import com.example.concerto.pojo.TaskVersion;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

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
