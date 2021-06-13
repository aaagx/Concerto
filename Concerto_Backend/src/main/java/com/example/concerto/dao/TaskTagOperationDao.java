package com.example.concerto.dao;

import com.example.concerto.pojo.TaskTagOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 20:30 2021/6/13
 * @Version: 1.0$
 */

@Mapper
public interface TaskTagOperationDao {
    //添加操作
    long addOpertaion(TaskTagOperation taskTagOperation);
    //删除某个版本之后的所有操作
    void deleteRollBackOperation(@Param("taskId") long taskId, @Param("taskVersion")int taskVersion);
    //获取某个版本之后的所有操作
    List<TaskTagOperation> getRollBackOperations(@Param("taskId") long taskId, @Param("taskVersion")int taskVersion);
}
