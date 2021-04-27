package com.example.concerto.dao;

import com.example.concerto.pojo.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/25 下午2:41
 */

@Mapper
public interface TaskDao {
    /**
     * 添加任务，状态和版本设置为0
     * 传入参数中的有效参数：projectId parentTaskId
     * 会把taskId注入到输入的参数中，进一步进行后面task_version中的插入操作
     * @param task
     * @return
     */
    Long addTask(Task task);

    /**
     * 获取任务完成状态
     * @param taskId
     * @return
     */
    @Select("select task_status from task where task_id = #{taskId}")
    Integer getTaskStatus(Long taskId);

    /**
     * 设置任务完成状态
     * @param taskId
     * @param status 0-未完成 1-已完成
     * @return
     */
    @Update("update task set task_status = #{status} where task_id = #{taskId}")
    Integer modifyTaskStatus(Long taskId,Integer status);

    /**
     * 修改任务版本号
     * 注：每次进行任务修改时，会在task_version里插入一条数据，然后更新task表的版本号
     * @param taskId
     * @param taskVersion
     */
    @Update("update task set task_version = #{taskVersion} where task_id = #{taskId}")
    void modifyTaskVersion(Long taskId,Integer taskVersion);


    /**
     * 获取任务的基本信息
     * 包括：id、标题、类型、优先级、开始时间、结束时间、完成状态、项目id、任务版本
     * @param taskId
     * @return
     */
    @Select("select tv.task_id, tv.task_title, tv.task_type, tv.task_priority, " +
            "tv.task_start_time, tv.task_end_time, " +
            "t.task_status, t.project_id,t.task_version " +
            "from task t join task_version tv on t.task_id = tv.task_id " +
            "where t.task_id = #{taskId} and t.task_version = tv.task_version ")
    Task queryTaskBaseInfo(Long taskId);

    /**
     * 通过任务id获取子任务基本信息的列表
     * 子任务基本信息包括：task_id，标题，类型，完成状态
     * @param parentTaskId
     * @return
     */
    @Select("select tv.task_id, tv.task_title, tv.task_type,  " +
            "t.task_status " +
            "from task t join task_version tv on t.task_id = tv.task_id " +
            "where t.parent_task_id = #{parentTaskId}")
    List<Task> querySubtaskByTaskId(Long parentTaskId);

}
