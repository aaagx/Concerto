package com.example.concerto.service.impl;

import com.example.concerto.dao.*;
import com.example.concerto.exception.CustomException;
import com.example.concerto.pojo.TaskTagOperation;
import com.example.concerto.pojo.TaskVersionInfo;
import com.example.concerto.pojo.User;
import com.example.concerto.service.TaskVersionService;
import com.example.concerto.vo.PersonnelVo;
import com.example.concerto.vo.TaskVersionInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 19:55 2021/6/10
 * @Version: $
 */
@Service
public class TaskVersionServiceImpl implements TaskVersionService {
    @Autowired
    TaskVersionInfoDao taskVersionInfoDao;
    @Autowired
    TaskVersionDao taskVersionDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TaskDao taskDao;
    @Autowired
    TaskTagOperationDao taskTagOperationDao;
    @Autowired
    TaskTagDao taskTagDao;

    @Override
    public List<TaskVersionInfoVo> getTaskVersionInfo(long taskId) {
        List<TaskVersionInfo> taskVersionInfoList = taskVersionInfoDao.queryAllTaskVersionInfo(taskId);
        List<TaskVersionInfoVo> taskVersionInfoVos= new ArrayList<>();
        //将TaskVersionPO转换为VO
        for(TaskVersionInfo po:taskVersionInfoList)
        {
            TaskVersionInfoVo vo=new TaskVersionInfoVo();
            BeanUtils.copyProperties(po,vo);
            //根据ID搜索对应的对象
            User user=userDao.getUserById(po.getTaskVersionModifyUserId());
            PersonnelVo personnelVo= PersonnelVo.builder().build();
            BeanUtils.copyProperties(user,personnelVo);
            //将用户信息填充进VO
            vo.setModifyUser(personnelVo);
            taskVersionInfoVos.add(vo);
        }
        return taskVersionInfoVos;
    }
    @Transactional
    @Override
    public void rollbackVersion(long taskId, int taskversion) {
        TaskVersionInfo taskVersionInfo = taskVersionInfoDao.queryTaskVersionInfo(taskId, taskversion);
        if(taskVersionInfo!=null)
        {
            taskDao.modifyTaskVersion(taskId,taskversion);
            taskVersionDao.deleteTaskVersionAfter(taskId,taskversion);
            List<TaskTagOperation> rollBackOperations = taskTagOperationDao.getRollBackOperations(taskId,taskversion);
            if(!rollBackOperations.isEmpty())
            {
                for(TaskTagOperation taskTagOperation:rollBackOperations)
                {
                    if(taskTagOperation.getTaskOperationType()==1)
                    {
                        taskTagDao.deleteTaskTag(taskTagOperation.getTaskId(),taskTagOperation.getTagId());
                    }
                    else
                    {
                        taskTagDao.addTaskTag(taskTagOperation.getTaskId(),taskTagOperation.getTagId());
                    }
                }
            }
            taskTagOperationDao.deleteRollBackOperation(taskId,taskversion);
        }
        else
        {
            throw new CustomException(404,"版本不存在");
        }
    }
}
