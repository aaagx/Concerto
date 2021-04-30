package com.example.concerto.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;


/**
 * TaskCommentVo
 *
 * @Author: LETOO
 * @Date: 2021/4/29 18:28
 * @Version: 1.0
 **/
@Slf4j
@Builder
@Data
@AllArgsConstructor
public class TaskCommentVo {
    private Long taskCommentId;
    private Long taskId;
    private Long taskCommentUserId;

    private String commentContent;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp commentTime;
    private String userName;
}
