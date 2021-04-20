package com.example.concerto.dao;

import com.example.concerto.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午8:58
 */
@Mapper
public interface PaperDao {
    //模糊查询 用like对标题和概述进行查询
    public List<Paper> queryByKeyword(String keyword);

}

