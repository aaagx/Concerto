package com.example.concerto.dao;

import com.example.concerto.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/27 下午10:20
 */
@Mapper
public interface TagDao {
    /**
     * 新增一个tag
     * tagContent tagColor
     * @param tag
     * @return
     */
    Long addTag(Tag tag);

    /**
     * 通过 tag_color 和 tag_content 查询tag
     * 用于检验tag是否已经存在
     * 若存在则返回长度为1的List
     * @param tag
     * @return
     */
    @Select("select * from tag where tag_color = #{tagColor} and tag_content = #{tagContent}")
    Tag queryTag(Tag tag);
}
