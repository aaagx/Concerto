package com.example.concerto.dao;

import com.example.concerto.pojo.UserAdvice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 20:24 2021/5/10
 * @Version:     1$
 */
 
 @Mapper
public interface UserAdviceDao {
    int insertAdvice (UserAdvice userAdvice);
}
