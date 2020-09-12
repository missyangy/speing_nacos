package com.nacos.dao;

import com.nacos.model.User;
import org.springframework.stereotype.Repository;

/**
 * @program: Springboot
 * @description: 用户mapper
 * @author: DongLianPo
 * @create: 2019/04/29 15:36
 **/
@Repository(value = "userMapper")
public interface UserMapper  {

    /**
     * 根据用户账号进行查询用户信息
     *
     * @param user 用户数据
     * @return User
     */
    User selectUserByUserNumber(User user);


}
