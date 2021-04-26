package com.example.concerto.dao;

import com.example.concerto.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 14:59 2021/4/25
 * @Version: 1.0$
 */

@Mapper
public interface UserDao {

    public User getUserById(int id);
    public int insertUser(User user);
    public int getUserNumByEmail(String email);
    public int getUserNumByName(String name);
}
