package com.example.concerto;

import com.example.concerto.dao.ProjectManagementDao;
import com.example.concerto.dao.TaskInfoDao;
import com.example.concerto.pojo.Tag;
import com.example.concerto.vo.PersonnelVo;
import com.example.concerto.vo.TaskCommentVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class ConcertoApplicationTests {

    @Autowired
    ProjectManagementDao projectManagementDao;

    @Autowired
    TaskInfoDao taskInfoDao;

    @Test
    void contextLoads() {
    }

    @Test
    void selectAllTaskID() {
        Long projectId = 1L;
        List<Long> longList = projectManagementDao.selectAllTaskID(projectId);
        log.info("【re】： " + longList.toString());
    }

    @Test
    void selectTagsByTaskId(){
        Long taskId = 10L;
        Set<Tag> longList = taskInfoDao.selectTagsByTaskId(taskId);
        log.info("【re】： " + longList.toString());
    }

    @Test
    void selectParticipantsByTaskId(){
        Long taskId = 10L;
        Set<PersonnelVo> longList = taskInfoDao.selectParticipantsByTaskId(taskId);
        log.info("【re】： " + longList.toString());
    }

    @Test
    void selectCommentsByTaskId(){
        Long taskId = 10L;
        List<TaskCommentVo> longList = taskInfoDao.selectCommentsByTaskId(taskId);
        log.info("【re】： " + longList.toString());
    }

}
