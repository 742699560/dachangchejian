package com.dccj.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dccj.service.CarOrderService;
import com.dccj.uitl.DateUtils;
import com.dccj.uitl.JsonResult;
import com.dccj.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Resource
    private UserService userService;

    @Resource
    private CarOrderService carOrderService;

    /**
     * 首页控制器
     */
    @RequestMapping("indexUI.do")
    public String indexUI() {
        return "index";
    }

    /**
     * 获取用户可以加载的菜单列表
     */
    @RequestMapping("menuList")
    @ResponseBody
    public JsonResult menuList() {
        List<Map<String, Object>> list = userService.menuList();
        return new JsonResult(list);
    }


    @RequestMapping("index/statisticsTopNum")
    @ResponseBody
    public JsonResult statisticsTopNum() {
        List<Map> list = carOrderService.statisticsTopNum(DateUtils.getDateLong(new Date()));
        return new JsonResult(list.get(0));
    }


}
