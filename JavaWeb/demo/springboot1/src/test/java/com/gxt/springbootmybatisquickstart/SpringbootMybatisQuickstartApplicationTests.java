package com.gxt.springbootmybatisquickstart;

import com.gxt.springbootmybatisquickstart.mappqer.UserMapper;
import com.gxt.springbootmybatisquickstart.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sound.sampled.AudioFormat;
import java.util.List;

@SpringBootTest //整合单元测试
class SpringbootMybatisQuickstartApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
   public void testListUser(){
        List<User> userList = userMapper.list();
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
   }

}
