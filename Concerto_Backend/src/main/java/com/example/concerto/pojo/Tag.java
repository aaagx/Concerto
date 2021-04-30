package com.example.concerto.pojo;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:38
 */
@Slf4j
@Builder
@Data
@AllArgsConstructor
public class Tag {
    Long tagId;
    String tagContent;
    String tagColor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagId, tag.tagId) &&
                Objects.equals(tagContent, tag.tagContent) &&
                Objects.equals(tagColor, tag.tagColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagContent, tagColor);
    }



    public Tag() {
    }

    public Tag(String tagContent, String tagColor) {
        this.tagContent = tagContent;
        this.tagColor = tagColor;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagContent() {
        return tagContent;
    }

    public void setTagContent(String tagContent) {
        this.tagContent = tagContent;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }
}
