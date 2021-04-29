package com.example.concerto.controller;

import com.example.concerto.fo.*;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.TaskService;
import com.example.concerto.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/29 上午9:34
 */

@RestController
@RequestMapping()
public class TaskController {
    @Autowired
    TaskService taskService;

    @PutMapping("task")
    public CommonResponse createTask(AddTaskForm addTaskForm){
        Long taskId = taskService.createTask(addTaskForm);
        return new CommonResponse(200,"创建任务成功",taskId);
    }

    //Long createMileStone(MileStoneForm mileStoneForm);
    @PutMapping("mileStone")
    public CommonResponse createMileStone(MileStoneForm mileStoneForm){
        Long taskId = taskService.createMileStone(mileStoneForm);
        return new CommonResponse(200,"创建里程碑成功",taskId);
    }

    //Integer modifyTask(ModifyTaskForm modifyTaskForm);
    @PostMapping("task")
    public CommonResponse modifyTask(ModifyTaskForm modifyTaskForm){
        Integer taskVersion = taskService.modifyTask(modifyTaskForm);
        return new CommonResponse(200,"修改任务成功",taskVersion);
    }

    //TaskVo queryTask(Long taskId);
    @GetMapping("task")
    public CommonResponse queryTask(Long taskId){
        TaskVo taskVo = taskService.queryTask(taskId);
        return new CommonResponse(200,"查询成功",taskVo);
    }

    //Long addSubtask(SubtaskForm subtaskForm);
    @PutMapping("subtask")
    public CommonResponse addSubtask(SubtaskForm subtaskForm){
        Long taskId = taskService.addSubtask(subtaskForm);
        return new CommonResponse(200,"添加子任务成功",taskId);
    }

    //void modifySubtask(ModifySubtaskForm modifySubtaskForm);
    @PostMapping("subtask")
    public CommonResponse modifySubtask(ModifySubtaskForm modifySubtaskForm){
        taskService.modifySubtask(modifySubtaskForm);
        return new CommonResponse(200,"修改子任务成功","");
    }

    //void changeTaskStatus(Long taskId);
    @PostMapping("task/status")
    public CommonResponse changeTaskStatus(Long taskId){
        taskService.changeTaskStatus(taskId);
        return new CommonResponse(200,"修改任务状态成功","");
    }
}
