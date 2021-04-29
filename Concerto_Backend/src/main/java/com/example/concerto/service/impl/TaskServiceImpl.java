package com.example.concerto.service.impl;

import com.example.concerto.dao.*;
import com.example.concerto.exception.CustomException;
import com.example.concerto.fo.*;
import com.example.concerto.pojo.*;
import com.example.concerto.service.TaskService;
import com.example.concerto.vo.SubtaskVo;
import com.example.concerto.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/27 下午4:22
 */

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDao taskDao;
    @Autowired
    TaskVersionDao taskVersionDao;

    @Autowired
    TagDao tagDao;

    @Autowired
    TaskTagDao taskTagDao;

    @Autowired
    UserTaskDao userTaskDao;

    @Override
    public Long createTask(AddTaskForm addTaskForm) {
        if (addTaskForm.getProjectId() == null){
            throw new CustomException(400,"项目id不可为空");
        }
        //通过taskForm获取相应的用于初始化的实体类型
        TaskPo initTask = addTaskForm.getTaskPo();
        TaskVersion initTaskVersion = addTaskForm.getTaskVersionObj();

        //添加task到task表中，获取taskId
        taskDao.addTaskPo(initTask);
        //将taskId注入到taskVersion中
        initTaskVersion.setTaskId(initTask.getTaskId());
        //taskVersion 加入到表中
        taskVersionDao.addTaskVerison(initTaskVersion);

        //设置tag
        Set<Tag> tags = addTaskForm.getTags();
        if (tags != null) {
            for (Tag tag : tags) {
                //查找tag看是否已经存在
                Tag tagInSql = tagDao.queryTag(tag);
                if (tagInSql != null) {//若存在，则建立关联
                    taskTagDao.addTaskTag(initTask.getTaskId(), tagInSql.getTagId());
                } else {//若不存在，则需要插入tag，然后建立关联
                    tagDao.addTag(tag);
                    taskTagDao.addTaskTag(initTask.getTaskId(), tag.getTagId());
                }
            }
        }

        //设置参与者
        Set<User> users = addTaskForm.getParticipants();
        if (users != null) {
            for (User user : users) {
                userTaskDao.addUserTask(user.getUserId(), initTask.getTaskId());
            }
        }

        //添加子任务
        List<SubtaskForm> subTasks = addTaskForm.getSubTasks();
        if (subTasks != null) {
            for (SubtaskForm subTask : subTasks) {
                subTask.setParentTaskId(initTask.getTaskId());
                subTask.setTaskVersionModifyUserId(initTaskVersion.getTaskVersionModifyUserId());
                addSubtask(subTask);
            }
        }

        return initTask.getTaskId();

    }

    @Override
    public Long createMileStone(MileStoneForm mileStoneForm) {
        if (mileStoneForm.getProjectId() == null){
            throw new CustomException(400,"项目id不可为空");
        }
        //通过MileStoneForm获取相应的用于初始化的实体类型
        TaskPo initTask = mileStoneForm.getTaskPo();
        TaskVersion initTaskVersion = mileStoneForm.getTaskVersionObj();

        //添加task到task表中，获取taskId
        taskDao.addTaskPo(initTask);
        //将taskId注入到taskVersion中
        initTaskVersion.setTaskId(initTask.getTaskId());
        //taskVersion 加入到表中
        taskVersionDao.addTaskVerison(initTaskVersion);

        return initTask.getTaskId();
    }


    @Override
    public Integer modifyTask(ModifyTaskForm modifyTaskForm) {
        Long taskId = modifyTaskForm.getTaskId();
        //版本比对
        Task task = taskDao.queryTaskBaseInfo(taskId);
        Integer taskVersionNum = task.getTaskVersion();
        //前端发送的版本参数即当前数据库中的版本，若不相同说明版本已经被更新
        if (taskVersionNum != modifyTaskForm.getTaskVersion()){
            throw new CustomException(400,"任务版本过时，请更新后重新请求");
        }else {
            taskVersionNum++;//更新版本号
        }

        //taskversion插入
        TaskVersion newTaskVersion = modifyTaskForm.getTaskVersionObj();
        newTaskVersion.setTaskVersion(taskVersionNum);
        taskVersionDao.addTaskVerison(newTaskVersion);

        //更新task版本
        taskDao.modifyTaskVersion(taskId,taskVersionNum);

        //新增tag
        Set<Tag> addTags = modifyTaskForm.getAddTags();
        if (addTags != null) {
            for (Tag tag : addTags) {
                //查找tag看是否已经存在
                Tag tagInSql = tagDao.queryTag(tag);
                if (tagInSql != null) {//若存在，则建立关联
                    taskTagDao.addTaskTag(taskId, tagInSql.getTagId());
                } else {//若不存在，则需要插入tag，然后建立关联
                    tagDao.addTag(tag);
                    taskTagDao.addTaskTag(taskId, tag.getTagId());
                }
            }
        }

        //删除tag
        Set<Tag> delTags = modifyTaskForm.getDelTags();
        if (delTags != null) {
            for (Tag tag : delTags) {
                //查找tagId
                Tag tagInSql = tagDao.queryTag(tag);
                //删除关联
                taskTagDao.deleteTaskTag(taskId, tagInSql.getTagId());
            }
        }

        //新增参与者
        Set<User> addUsers = modifyTaskForm.getAddParticipants();
        if (addUsers != null) {
            for (User user : addUsers) {
                userTaskDao.addUserTask(user.getUserId(), taskId);
            }
        }
        //删除参与者
        Set<User> delUsers = modifyTaskForm.getDelParticipants();
        if (delUsers != null) {
            for (User user : delUsers) {
                userTaskDao.deleteUserTask(user.getUserId(), taskId);
            }
        }

        return taskVersionNum;
    }

    @Override
    public TaskVo queryTask(Long taskId) {
        TaskVo taskVo = new TaskVo();
        //查询到基本信息
        Task task = taskDao.queryTaskBaseInfo(taskId);

        taskVo.setSimpleInfoByTask(task);

        //查询tag
        taskVo.setTags(taskTagDao.queryTagByTaskId(taskId));

        //查询参与者
        taskVo.setParticipants(userTaskDao.queryUserByTaskId(taskId));

        //查询子任务
        List<Task> subtasks = taskDao.querySubtaskByTaskId(taskId);
        List<SubtaskVo> subtaskVos = new ArrayList<>();
        //对每个子任务查询参与者
        for(Task subtask : subtasks){
            SubtaskVo subtaskVo = new SubtaskVo();
            subtaskVo.setSimpleInfoByTask(subtask);
            subtaskVo.setParticipants(userTaskDao.queryUserByTaskId(subtask.getTaskId()));
            subtaskVos.add(subtaskVo);
        }
        taskVo.setSubTasks(subtaskVos);

        return taskVo;
    }

    @Override
    public Long addSubtask(SubtaskForm subtaskForm) {
        if (subtaskForm.getParentTaskId()==null){
            throw new CustomException(400,"父任务id不可为空");
        }

        TaskPo task = subtaskForm.getTaskPo();
        taskDao.addTaskPo(task);

        TaskVersion taskVersion = subtaskForm.getTaskVersionObj();
        taskVersion.setTaskId(task.getTaskId());
        taskVersionDao.addTaskVerison(taskVersion);

        //设置参与者
        Set<User> users = subtaskForm.getParticipants();
        if (users != null) {
            for (User user : users) {
                userTaskDao.addUserTask(user.getUserId(), task.getTaskId());
            }
        }

        return task.getTaskId();
    }

    @Override
    public void modifySubtask(ModifySubtaskForm modifySubtaskForm) {
        Long taskId = modifySubtaskForm.getTaskId();
        String taskTitle = modifySubtaskForm.getTaskTitle();
        if (taskTitle!=null && !"".equals(taskTitle)) {
            taskVersionDao.modifyTaskTitle(taskId,taskTitle);
        }

        Set<User> participants = modifySubtaskForm.getParticipants();
        userTaskDao.deleteUserTaskByTaskId(taskId);
        if (participants != null) {
            for (User user : participants) {
                userTaskDao.addUserTask(user.getUserId(), taskId);
            }
        }
    }

    @Override
    public void changeTaskStatus(Long taskId) {
        Integer status = taskDao.getTaskStatus(taskId);
        if (status == 0){
            taskDao.modifyTaskStatus(taskId,1);
        }else {
            taskDao.modifyTaskStatus(taskId,0);
        }
    }
}
