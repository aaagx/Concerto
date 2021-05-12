package com.example.concerto.service.impl;

import com.example.concerto.dao.TaskDao;
import com.example.concerto.fo.*;
import com.example.concerto.pojo.Tag;
import com.example.concerto.pojo.Task;
import com.example.concerto.pojo.User;
import com.example.concerto.service.TaskService;
import com.example.concerto.util.Factory;
import com.example.concerto.vo.TaskVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/28 上午11:06
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class TaskServiceImplTest {

    @Autowired
    TaskService taskService;

    @Test
    public void createTask() {
        AddTaskForm addTaskForm = new AddTaskForm();

        addTaskForm.setTaskVersionModifyUserId(1L);

        addTaskForm.setTaskTitle("text2");

        addTaskForm.setProjectId(1L);

        addTaskForm.setTaskPriority(0);

        Calendar begin = Calendar.getInstance();
        begin.set(2020,1,10);
        addTaskForm.setTaskStartTime(begin.getTime());

        Calendar end = Calendar.getInstance();
        end.set(2020,1,10);
        addTaskForm.setTaskEndTime(end.getTime());

        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("1254","#FFCCFF"));
        tags.add(new Tag("1223","#FFCCFF"));
        addTaskForm.setTags(tags);

        Set<User> users = new HashSet<>();
        users.add(Factory.getUserInstance(1L,""));
        users.add(Factory.getUserInstance(2L,""));
        addTaskForm.setParticipants(users);

        List<SubtaskForm> subtaskForms = new ArrayList<>();
        subtaskForms.add(Factory.getSubtask("sub2-1",users));
        subtaskForms.add(Factory.getSubtask("sub2-2",users));
        addTaskForm.setSubTasks(subtaskForms);

        taskService.createTask(addTaskForm);

    }

    @Test
    public void modifyTask() {
        ModifyTaskForm modifyTaskForm = new ModifyTaskForm();

        modifyTaskForm.setTaskId(22L);

        modifyTaskForm.setTaskVersion(3);

        modifyTaskForm.setTaskVersionModifyUserId(1L);

        modifyTaskForm.setTaskTitle("text2-2");

        //modifyTaskForm.setProjectId(1L);

        modifyTaskForm.setTaskPriority(1);

        modifyTaskForm.setTaskType(0);

        modifyTaskForm.setTaskVersionDescription("hahahhaha");

        Calendar begin = Calendar.getInstance();
        begin.set(2021,1,10);
        modifyTaskForm.setTaskStartTime(begin.getTime());

        Calendar end = Calendar.getInstance();
        end.set(2021,1,10);
        modifyTaskForm.setTaskEndTime(end.getTime());

        Set<Tag> deltags = new HashSet<>();
        deltags.add(new Tag("1254","#FFCCFF"));
        //deltags.add(new Tag("1223","#FFCCFF"));
        modifyTaskForm.setDelTags(deltags);

        Set<Tag> addtags = new HashSet<>();
        addtags.add(new Tag("11254","#FFCCFF"));
        addtags.add(new Tag("11223","#FFCCFF"));
        modifyTaskForm.setAddTags(addtags);

        Set<User> addusers = new HashSet<>();
        addusers.add(Factory.getUserInstance(3L,""));
        //addusers.add(Factory.getUserInstance(2L,""));
        modifyTaskForm.setAddParticipants(addusers);

        Set<User> delusers = new HashSet<>();
        //delusers.add(Factory.getUserInstance(3L,""));
        delusers.add(Factory.getUserInstance(2L,""));
        modifyTaskForm.setDelParticipants(delusers);

        taskService.modifyTask(modifyTaskForm);
    }

    @Test
    public void createMileStone(){
        MileStoneForm mileStoneForm = new MileStoneForm();

        mileStoneForm.setTaskTitle("里程碑2");

        mileStoneForm.setTaskVersionModifyUserId(1L);

        mileStoneForm.setProjectId(1L);


        Calendar begin = Calendar.getInstance();
        begin.set(2020,2,10);
        mileStoneForm.setTaskStartTime(begin.getTime());

        taskService.createMileStone(mileStoneForm);

    }
    @Test
    public void queryTask() {
        TaskVo task = taskService.queryTask(22L);
        System.out.println("fine");
    }

    @Test
    public void addSubtask() {
        SubtaskForm subtask = Factory.getSubtask("sub1-3", null);
        subtask.setParentTaskId(22L);
        taskService.addSubtask(subtask);
    }

    @Test
    public void modifySubtask(){
        ModifySubtaskForm modifySubtaskForm = new ModifySubtaskForm();
        modifySubtaskForm.setTaskId(20L);
        modifySubtaskForm.setTaskTitle("测试修改！");
        Set<User> users = new HashSet<>();
        users.add(Factory.getUserInstance(10L,"sarise"));
        users.add(Factory.getUserInstance(2L,"ss"));
        modifySubtaskForm.setParticipants(users);

        taskService.modifySubtask(modifySubtaskForm);

    }
    @Test
    public void changeTaskStatus() {
        taskService.changeTaskStatus(23L);
        //taskService.changeTaskStatus(15L);
    }

    @Autowired
    TaskDao taskDao;
    @Test
    public void tagTest()
    {
        Set<Tag> tagSet=new HashSet<>();
        List<Task> taskList=taskDao.getTasksByUserId(8);
        for(Task task:taskList)
        {
            Set<Tag> tempTagSet=task.getTags();
            tagSet.addAll(tempTagSet);
        }
    return;
    }
}