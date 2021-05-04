package com.example.concerto.dao;

import com.example.concerto.pojo.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:35 2021/4/28
 * @Version: 1.0$
 */
@Mapper
public interface ProjectDao {
    public long insertProject(Project project);


}
