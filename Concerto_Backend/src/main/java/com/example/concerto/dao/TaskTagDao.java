package com.example.concerto.dao;

import com.example.concerto.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/26 下午4:35
 */

@Mapper
public interface TaskTagDao {

    /**
     * 新增一个tag
     * tagContent tagColor
     * @param tag
     * @return
     */
    Long addTag(Tag tag);

    @Insert("insert into task_tag values (NULL,#{taskId},#{tagId})")
    Long addTaskTag(@Param("taskId") Long taskId,@Param("tagId") Long tagId);

    /**
     * 通过tagId taskId 删除task_tag中的记录
     * @param tagId
     * @return
     */
    @Delete("delete from task_tag where task_id = #{taskId} and tag_id = #{tagId}")
    Integer deleteTaskTag(@Param("taskId") Long taskId,@Param("tagId") Long tagId);

    /**
     * 通过tagId删除tag
     * @param tagId
     * @return
     */
    @Delete("delete from tag where tag_id = #{tagId}")
    Integer deleteTag(Long tagId);

    /**
     * 通过taskId搜索到tag列表
     * @param taskId
     * @return
     */
    @Select("select t.tag_id,t.tag_content,t.tag_color from task_tag tt left join tag t on tt.tag_id = t.tag_id where tt.task_id = #{taskId}")
    Set<Tag> queryTagByTaskId(Long taskId);

    /**
     * 通过 tag_color 和 tag_content 查询tag
     * 用于检验tag是否已经存在
     * 若存在则返回长度为1的List
     * @param tag
     * @return
     */
    @Select("select * from tag where tag_color = #{tagColor} and tag_content = #{tagContent}")
    List<Tag> tagCount(Tag tag);
}
