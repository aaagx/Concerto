package com.example.concerto.dao;

import com.example.concerto.pojo.Project;
import com.example.concerto.vo.PersonnelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ProjectManagementDao
 *
 * @Author: LETOO
 * @Date: 2021/4/28 9:35
 * @Version: 1.0
 **/
@Mapper
public interface ProjectManagementDao {

    /**
     * 更新项目信息
     * @param project
     * @return
     */
    int updateProject(Project project);

    /**
     * 获取项目的全部人员（管理者、普通成员）
     * @param projectId
     * @return
     */
    List<PersonnelVo> getProjectAllMember(Long projectId);

    /**
     * 获取项目的申请人员
     * @param projectId
     * @return
     */
    List<PersonnelVo> getProjectApplicant(Long projectId);

    /**
     * 修改申请人员的身份（同意/接受）
     * @param projectId
     * @param userId
     * @param role
     * @return
     */
    int updateUserRole(@Param("projectId")Long projectId, @Param("userId")Long userId, @Param("role")Integer role);

    /**
     * 拒绝申请时，删除User_Project表的记录
     * @param projectId
     * @param userId
     * @return
     */
    int deleteUserProjectRecord(@Param("projectId")Long projectId, @Param("userId")Long userId);

    /**
     * 获取项目全部的任务id
     * @param projectId
     * @return
     */
    List<Long> selectAllTaskID(Long projectId);

}
