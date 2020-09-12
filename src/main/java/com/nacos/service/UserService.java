package com.nacos.service;


import com.nacos.model.User;

/**
 * @program: Springboot
 * @description: 用户信息
 * @author: DongLianPo
 * @create: 2019/04/29 16:28
 **/
public interface UserService {

    /**
     * 根据用户账号进行查询用户信息
     *
     * @param user 用户数据
     * @return User
     */
    User selectUserByUserNumber(User user);


}
