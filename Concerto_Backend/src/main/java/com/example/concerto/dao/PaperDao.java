package com.example.concerto.dao;


import com.example.concerto.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaperDao {
    //模糊查询 用like对标题和概述进行查询
    public List<Paper> queryByKeyword(String keyword);
//    通过关键词搜索获取相关的文章 分页
//    String keyword
//    int begin
//    int num




}
