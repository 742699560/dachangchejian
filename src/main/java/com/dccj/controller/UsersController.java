package com.dccj.controller;

import com.dccj.entity.Users;
import com.dccj.exception.AppException;
import com.dccj.service.UsersService;
import org.apache.commons.lang3.StringUtils;
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

    @RequestMapping("/registerUsers")
    @ResponseBody
    public RespEntity registerUsers(Users users) {
        usersService.insertSelective(users);
        RespEntity respEntity = new RespEntity();
        respEntity.setData(users);
        return respEntity;
    }

    @RequestMapping("/queryUsers")
    @ResponseBody
    public RespEntity queryUsers(@RequestParam(required = false) String openId, @RequestParam(required = false) String unionId, @RequestParam(required = false) Integer id) {
        if (StringUtils.isEmpty(openId) && StringUtils.isEmpty(unionId) && id == null)
            throw new AppException("参数错误");
        Users users = usersService.selectByUnionIdAndOpenIdAndId(unionId, openId, id);
        RespEntity respEntity = new RespEntity();
        respEntity.setData(users);
        return respEntity;
    }

    @RequestMapping("/updateUsers")
    @ResponseBody
    public RespEntity updateUsers(Users users) {
        if (users.getId() == null)
            throw new AppException("参数错误");
        usersService.updateByPrimaryKeySelective(users);
        RespEntity respEntity = new RespEntity();
        respEntity.setData(users);
        return respEntity;
    }
}
