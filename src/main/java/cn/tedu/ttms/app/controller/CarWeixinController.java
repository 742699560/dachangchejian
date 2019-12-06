package cn.tedu.ttms.app.controller;

import cn.tedu.ttms.car.entity.CUser;
import cn.tedu.ttms.car.entity.UserCar;
import cn.tedu.ttms.car.service.CUserService;
import cn.tedu.ttms.car.service.UserCarService;
import cn.tedu.ttms.base.controller.BaseController;
import cn.tedu.ttms.common.exception.AppException;
import cn.tedu.ttms.system.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/app/car")
public class CarWeixinController extends BaseController {

    @Resource
    UserCarService userCarService;

    @Resource
    CUserService cUserService;

    @RequestMapping("/queryPayData")
    @ResponseBody
    public Map<String, Object> queryPayData() {
        UserCar param = new UserCar();
        param.setStatus(2);
        param.setInputStatus(1);
        List<UserCar> list = userCarService.selectByAll(param);
        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", list);
        map.put("message", "查询成功!");
        return map;
    }

    @RequestMapping("/inputBeginData")
    @ResponseBody
    public Map<String, Object> inputBeginData(@RequestParam Integer id, @RequestParam Integer userId) {
        UserCar userCar = userCarService.selectByPrimaryKey(id);
        if (userCar.getInputUserId() != null) {
            CUser cUser = cUserService.selectByPrimaryKey(userCar.getInputUserId());
            throw new AppException("该数据已被用户【" + cUser.getName() + "】拉取");
        }
        userCar.setInputStatus(2);
        userCar.setInputUserId(userId);
        userCar.setInputDate(new Date());
        userCarService.updateByPrimaryKey(userCar);
        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", userCar);
        map.put("message", "查询成功!");
        return map;
    }


    @RequestMapping("/inputOverData")
    @ResponseBody
    public Map<String, Object> inputOverData(@RequestParam Integer id, @RequestParam Integer userId) {
        UserCar userCar = userCarService.selectByPrimaryKey(id);
        CUser cUser = cUserService.selectByPrimaryKey(userId);
        if (userId != userCar.getInputUserId())
            throw new AppException("当前数据正在被用户【" + cUser.getName() + "】拉取录入中");
        userCar.setInputStatus(3);
        userCar.setInputUserId(userId);
        userCar.setInputDate(new Date());
        userCarService.updateByPrimaryKey(userCar);
        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", userCar);
        map.put("message", "查询成功!");
        return map;
    }

}
