package com.example.concerto.dao;

import com.example.concerto.pojo.User;
import com.example.concerto.vo.PersonnelVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;


import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/26 下午4:34
 */

@Mapper
public interface UserTaskDao {

    /**
     * 增加用户和任务的关联
     * @param userId
     * @param taskId
     * @return
     */
    @Insert("insert into user_task values(NULL,#{userId},#{taskId})")
    Long addUserTask(@Param("userId") Long userId,@Param("taskId") Long taskId);


    /**
     * 通过userId taskId 删除关联
     * @param userId
     * @param taskId
     */
    @Delete("delete from user_task where user_id = #{userId} and task_id = #{taskId}")
    void deleteUserTask(@Param("userId") Long userId,@Param("taskId") Long taskId);

    /**
     * 删除某个任务所有参与者
     * @param taskId
     */
    @Delete("delete from user_task where task_id = #{taskId}")
    void deleteUserTaskByTaskId(Long taskId);

    /**
     * 获取一个任务所有的参与者信息
     * 包括：用户id、邮箱、名称、手机号
     * @param taskId
     * @return
     */
    @Select("select u.user_id, u.user_email, u.user_name, u.user_phone " +
            "from user_task ut left join user u on ut.user_id = u.user_id where ut.task_id = #{taskId}")
    Set<User> queryUserByTaskId(Long taskId);

    @Select("select u.user_id, u.user_email, u.user_name " +
            "from user_task ut left join user u on ut.user_id = u.user_id where ut.task_id = #{taskId}")
    Set<PersonnelVo> queryPersonnelVoByTaskId(Long taskId);



}
