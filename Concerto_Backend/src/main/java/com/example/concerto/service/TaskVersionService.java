package com.example.concerto.service;

import com.example.concerto.vo.TaskVersionInfoVo;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 19:33 2021/6/10
 * @Version: 1.0$
 */


public interface TaskVersionService {
    public List<TaskVersionInfoVo> getTaskVersionInfo(long taskId);
    public void rollbackVersion(long taskId,int taskversionId);
}
