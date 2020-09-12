package com.nacos.controller;

import com.alibaba.fastjson.JSON;

import com.nacos.model.User;
import com.nacos.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Springboot
 * @description: 用户接口
 * @author: DongLianPo
 * @create: 2019/04/29 17:55
 **/
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;


    @RequestMapping("/query_user")
    @ResponseBody
    public String queryUser(User user) {
        LOGGER.info("queryUser is begin data:{}", user.toString());
        User users = userService.selectUserByUserNumber(user);
        return JSON.toJSONString(users);
    }




}
