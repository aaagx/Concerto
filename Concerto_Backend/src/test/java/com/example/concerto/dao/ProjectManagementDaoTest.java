package com.example.concerto.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * ProjectManagementDaoTest
 *
 * @Author: LETOO
 * @Date: 2021/4/29 16:56
 * @Version: 1.0
 **/
class ProjectManagementDaoTest {

    @Autowired
    ProjectManagementDao projectManagementDao;

    @Test
    void updateProject() {
    }

    @Test
    void getProjectAllMember() {
    }

    @Test
    void getProjectApplicant() {
    }

    @Test
    void updateUserRole() {
    }

    @Test
    void deleteUserProjectRecord() {
    }

    @Test
    void selectAllTaskID() {
        Long projectId = 1L;
        List<Long> longList = projectManagementDao.selectAllTaskID(projectId);
        //System.out.println(longList.get(0));
    }
}