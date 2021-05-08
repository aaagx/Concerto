package com.example.concerto.dao;

import com.example.concerto.pojo.Project;
import com.example.concerto.pojo.User;
import com.example.concerto.pojo.UserProject;
import com.example.concerto.pojo.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:15 2021/4/28
 * @Version: 1.0$
 */

@Mapper
public interface UserProjectDao {
    @Insert("insert into user_project values(NULL,#{user_id},#{project_id},#{user_role})")
    Long addUserProject(UserProject userProject);

    @Select("select count(*) from user_project where user_id=#{userId} and project_id=#{projectId} ")
    int checkUserProject(@Param("userId") Long userId,@Param("projectId") Long projectId);

    long getProjectManager(Long projectId);

    List<Project> getProjectsByUser(long UserId);

    Userinfo getAdminByProject(long projectId);
}
