package com.nacos.service.impl;


import com.nacos.dao.UserMapper;
import com.nacos.model.User;
import com.nacos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: Springboot
 * @description: 用户业务处理
 * @author: DongLianPo
 * @create: 2019/04/29 16:29
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserMapper userMapper;


    @Override
    public User selectUserByUserNumber(User user) {
        return userMapper.selectUserByUserNumber(user);
    }



}
