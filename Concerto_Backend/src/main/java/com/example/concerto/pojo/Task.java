package com.example.concerto.pojo;

import com.example.concerto.utils.DatesUtils;
import com.example.concerto.vo.PersonnelVo;
import com.example.concerto.vo.TaskCommentVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.DelegatingServerHttpResponse;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author sarise
 * @version 2.0
 * @date 2021/4/20 下午7:20
 */
@Slf4j
@Builder
@Data
@AllArgsConstructor
public class Task implements  Comparable{
    /**
     * task
     */
    Long taskId ;//
    Long projectId;//
    Long parentTaskId; //-1
    Integer taskStatus ; //完成状态1 未完成0
    Integer taskVersion ;//

    /**
     * TaskDao ：queryTaskBaseInfo
     */
    String taskTitle ;//标题
    Integer taskType ; //2:里程碑 0:任务 1:子任务
    Integer taskPriority ; //0：普通 1：有点紧急又不太紧急 2：紧急
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    Date taskStartTime ;//
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    Date taskEndTime ;//

    int subTaskNum;  //任务总数
    int subTaskCompletedNum;  //已完成的任务数
    int taskDays; //任务剩余天数

    /**
     * task_tag     tag
     */
    Set<Tag> tags;
    /**
     * user_task    user
     */
    Set<PersonnelVo> participants;
    List<Task> subTasks;
    /**
     * task_comment     task
     */
    List<TaskCommentVo> comments;


    public Task(){}

    public void setTaskDays(){
        this.taskDays = DatesUtils.getTermDays2(new Date(), this.taskEndTime) ;
    }

    public void setSubTaskNum(){
        this.subTaskNum = this.subTasks.size();
    }

    public void setSubTaskCompletedNum(){
        int completedNum = 0;
        for (Task task : this.subTasks) {
            if(task.getTaskStatus() == 1){ //子任务已完成
                completedNum++;
            }
        }
        this.subTaskCompletedNum = completedNum;
    }


    //getter and setter
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<PersonnelVo> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<PersonnelVo> participants) {
        this.participants = participants;
    }

    public List<TaskCommentVo> getComments() {
        return comments;
    }

    public void setComments(List<TaskCommentVo> comments) {
        this.comments = comments;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(Integer taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Integer getTaskVersion() {
        return taskVersion;
    }

    public void setTaskVersion(Integer taskVersion) {
        this.taskVersion = taskVersion;
    }

    public int getValue()
    {
        Date today=new Date();

        int value=0;
        value+=taskStatus;
        value*=1000;
        if(taskPriority != null){
            value-=taskPriority;
        }
        value*=1000;
        if(taskEndTime != null){
            value+=Math.abs(taskEndTime.compareTo(today));
        }
        if(taskStartTime != null){
            value+=Math.abs(taskStartTime.compareTo(today));
        }
        return  value;
    }
    @Override
    public int compareTo(Object o) {
        if (o instanceof Task) {
            Task inputTask = (Task) o;
            if (this.getValue() > inputTask.getValue())
                return 1;
            else
                return -1;
        }
        return 0;
    }
}
