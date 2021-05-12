package com.example.concerto.dao;

import com.example.concerto.pojo.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:09 2021/5/3
 * @Version: 1.0$
 */

@Mapper
public interface MessageDao {
        List<Message> getMesssageByUserId(long UserId);

        int insertMessage(Message message);

        int setMessage(long UserId);
}
