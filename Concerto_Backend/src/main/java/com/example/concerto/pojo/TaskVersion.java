package com.example.concerto.pojo;

import java.util.Date;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/26 下午9:00
 */
public class TaskVersion {
    Long taskVersionId ;
    Long taskId ;
    String taskVersionDescription ; //修改描述
    Date taskVersionModifyTime ; //修改时间
    Long taskVersionModifyUserId ; //修改人ID

    Integer taskVersion ;//任务版本
    String taskTitle ;//标题
    String taskType ; //里程碑 任务 子任务
    String taskPriority ;//非常紧急 紧急 普通
    Date taskStartTime ;
    Date taskEndTime ;

}
