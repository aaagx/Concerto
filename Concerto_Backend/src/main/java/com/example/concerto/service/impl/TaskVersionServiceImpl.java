package com.example.concerto.service.impl;

import com.example.concerto.dao.TaskVersionInfoDao;
import com.example.concerto.dao.UserDao;
import com.example.concerto.pojo.TaskVersionInfo;
import com.example.concerto.pojo.User;
import com.example.concerto.service.TaskVersionService;
import com.example.concerto.vo.PersonnelVo;
import com.example.concerto.vo.TaskVersionInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    UserDao userDao;
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

    @Override
    public void rollbackVersion(long taskId, long taskversionId) {

    }
}
