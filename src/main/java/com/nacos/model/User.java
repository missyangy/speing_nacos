package com.nacos.model;

import lombok.Data;
import lombok.ToString;

/**
 * @program: Springboot
 * @description: 用户表
 * @author: DongLianPo
 * @create: 2019/04/29 15:08
 **/
@Data
@ToString
public class User {
    /**
     * 用户id
     **/
    private Long id;

    /**
     * 用户名称
     **/
    private String userName;

    /**
     * 用户账户
     **/
    private String password;

    /**
     * 用户账户
     **/
    private String userNumber;

    /**
     * 用户状态
     **/
    private Integer status;

    /**
     * 创建时间
     **/
    private String createTime;

    /**
     * 更新时间
     **/
    private String modifyTime;

}
