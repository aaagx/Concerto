package com.example.concerto.dao;

import com.example.concerto.pojo.User;
import com.example.concerto.pojo.UserToken;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:37 2021/4/26
 * @Version: 1.0$
 */

@Mapper
public interface UserTokenDao {
    public int insertUserToken(UserToken userToken);
    public List<String> getTokenByUserId(long userId);
}
