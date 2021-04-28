package com.example.concerto.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:15 2021/4/28
 * @Version: 1.0$
 */

@Mapper
public interface UserProjectDao {
    @Insert("insert into user_project values(NULL,#{userId},#{projectId},#{userRole})")
    Long addUserProject(Long userId,Long projectId,int userRole);
    @Select("select count(*) from user_project where user_id=#{userId} and project_id=#{projectId} ")
    int checkUserProject(Long userId,Long projectId);
}
