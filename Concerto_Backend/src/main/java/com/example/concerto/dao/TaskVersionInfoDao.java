package com.example.concerto.dao;

import com.example.concerto.pojo.TaskVersionInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/26 下午4:29
 */

@Mapper
public interface TaskVersionInfoDao {

    /**
     * 获取任务某个版本的版本信息
     * 包括：任务ID 任务版本 修改描述 修改时间 修改人ID
     * @param taskId
     * @param taskVersion
     * @return
     */
    @Select("select * from task_version where task_id = #{taskId} and task_version = #{taskVersion}")
    TaskVersionInfo queryTaskVersionInfo(@Param("taskId") Long taskId, @Param("taskVersion") Integer taskVersion);

    /**
     * 包含任务所有的版本信息
     * @param taskId
     * @return
     */
    @Select("select * from task_version where task_id = #{taskId} ")
    List<TaskVersionInfo> queryTaskVersionInfoList(Long taskId);


}
