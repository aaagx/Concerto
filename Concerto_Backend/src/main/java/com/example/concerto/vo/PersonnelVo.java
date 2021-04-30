package com.example.concerto.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * personnelVo
 *
 * @Author: LETOO
 * @Date: 2021/4/28 23:23
 * @Version: 1.0
 **/
@Slf4j
@Builder
@Data
@AllArgsConstructor
public class PersonnelVo {
    private Long userId;
    private String userName;
    private String userEmail;
}
