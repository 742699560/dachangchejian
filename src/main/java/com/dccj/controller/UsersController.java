package com.dccj.controller;

import com.dccj.entity.Users;
import com.dccj.service.UsersService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @RequestMapping("/register")
    @ResponseBody
    public RespEntity register(Users users) {
        usersService.insertSelective(users);
        RespEntity respEntity = new RespEntity();
        respEntity.setData(users);
        return respEntity;
    }
}
