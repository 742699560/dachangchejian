package cn.tedu.ttms.app.controller;

import cn.tedu.ttms.car.entity.CUser;
import cn.tedu.ttms.car.entity.UserCar;
import cn.tedu.ttms.car.service.CUserService;
import cn.tedu.ttms.car.service.UserCarService;
import cn.tedu.ttms.base.controller.BaseController;
import cn.tedu.ttms.common.exception.AppException;
import cn.tedu.ttms.common.util.ObjectUtil;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.system.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Map<String, Object> queryPayData() {
        UserCar param = new UserCar();
        param.setStatus(2);
        param.setInputStatus(2);
        param.setInputAjStatus(2);
        List<UserCar> list = userCarService.selectByAll(param);
        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", list);
        map.put("message", "查询成功!");
        return map;
    }

    @RequestMapping("/inputBeginData")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Map<String, Object> inputBeginData(@RequestParam Integer id, @RequestParam Integer userId,@RequestParam Integer type) {
        UserCar userCar = userCarService.selectByPrimaryKey(id);


        if (userId != userCar.getInputUserId() && type == 1 && userCar.getInputUserId() != null) {
            CUser cUser = cUserService.selectByPrimaryKey(userCar.getInputUserId());
            throw new AppException("该数据已被用户【" + cUser.getName() + "】环保录入中");
        }
        if (userId != userCar.getInputAjId() && type == 2 && userCar.getInputAjId() != null) {
            CUser cUser = cUserService.selectByPrimaryKey(userCar.getInputAjId());
            throw new AppException("该数据已被用户【" + cUser.getName() + "】安检录入中");
        }
        if(type == 1) {
            userCar.setInputStatus(2);
            userCar.setInputUserId(userId);
            userCar.setInputDate(new Date());
        }else if(type == 2){
            userCar.setInputAjId(userId);
            userCar.setInputAjStatus(2);
            userCar.setIntputAjDate(new Date());
        }
        userCarService.updateByPrimaryKey(userCar);
        switch (userCar.getCarColor()) {
            case "1":
                userCar.setCarColor("白");
                break;
            case "2":
                userCar.setCarColor("灰");
                break;
            case "3":
                userCar.setCarColor("黄");
                break;
            case "4":
                userCar.setCarColor("粉");
                break;
            case "5":
                userCar.setCarColor("红");
                break;
            case "6":
                userCar.setCarColor("紫");
                break;
            case "7":
                userCar.setCarColor("绿");
                break;
            case "8":
                userCar.setCarColor("蓝");
                break;
            case "9":
                userCar.setCarColor("棕");
                break;
            case "10":
                userCar.setCarColor("黑");
                break;
            default:
                userCar.setCarColor("其他");
                break;
        }
        switch (userCar.getCarDriveType()) {
            case "1":
                userCar.setCarDriveType("前驱");
                break;
            case "2":
                userCar.setCarDriveType("后驱");
                break;
            case "3":
                userCar.setCarDriveType("四驱");
                break;
            default:
                userCar.setCarDriveType("其他");
                break;
        }
        switch (userCar.getCarAtType()) {
            case "1":
                userCar.setCarAtType("手动");
                break;
            case "2":
                userCar.setCarAtType("自动");
                break;
            default:
                userCar.setCarAtType("其他");
                break;
        }
        userCar.setTimeSub(userCar.getId()+"");
        ObjectUtil.nullToEmpty(userCar);
        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", userCar);
        map.put("message", "查询成功!");
        return map;
    }


    @RequestMapping("/inputOverData")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Map<String, Object> inputOverData(@RequestParam Integer id, @RequestParam Integer userId,@RequestParam Integer type) {
        UserCar userCar = userCarService.selectByPrimaryKey(id);
        CUser cUser = cUserService.selectByPrimaryKey(userId);
        if (userId != userCar.getInputUserId() && type == 1)
            throw new AppException("当前数据正在被用户【" + cUser.getName() + "】环保录入中");
        if (userId != userCar.getInputAjId() && type == 2)
            throw new AppException("当前数据正在被用户【" + cUser.getName() + "】安检录入中");
        if(type == 1) {
            userCar.setInputStatus(3);
            userCar.setInputUserId(userId);
            userCar.setInputDate(new Date());
        }else if(type == 2){
            userCar.setInputAjId(userId);
            userCar.setInputAjStatus(3);
            userCar.setIntputAjDate(new Date());
        }

        userCarService.updateByPrimaryKey(userCar);

        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", userCar);
        map.put("message", "查询成功!");
        return map;
    }

    @RequestMapping("/queryDetail")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Map<String, Object> queryDetail(@RequestParam Integer id) {
        UserCar userCar = userCarService.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", userCar);
        map.put("message", "查询成功!");
        return map;
    }


    @RequestMapping("/queryInputData")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Map<String, Object> queryInputData(@RequestParam Integer userId,@RequestParam(defaultValue = "0") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam Integer type) {
         PageObject pageObject = new PageObject();
        pageObject.setStartIndex(pageNum);
        pageObject.setPageSize(pageSize);
        UserCar param = new UserCar();
        if(type == 1)
            param.setInputUserId(userId);
        else
            param.setInputAjId(userId);
        Map<String, Object> dataMap =userCarService.selectByAllByPage(param,pageObject);
        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", dataMap);
        map.put("message", "查询成功!");
        return map;
    }


    @RequestMapping("/appLogin")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Map<String, Object> appLogin(@RequestParam String account, @RequestParam String password) throws NoSuchAlgorithmException {
        CUser cUser = cUserService.selectByName(account);
        if (cUser == null)
            throw new AppException("当前账户不存在");
        if (!cUser.getPassword().equals(getMD5(password)))
            throw new AppException("密码不正确");
        Map<String, Object> map = new HashMap();
        map.put("state", 1);
        map.put("data", cUser);
        map.put("message", "查询成功!");
        return map;
    }

    public String getMD5(String str) throws NoSuchAlgorithmException {
        /** 创建MD5加密对象 */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        /** 进行加密 */
        md5.update(str.getBytes());
        /** 获取加密后的字节数组 */
        byte[] md5Bytes = md5.digest();
        String res = "";
        for (int i = 0; i < md5Bytes.length; i++) {
            int temp = md5Bytes[i] & 0xFF;
            if (temp <= 0XF) { // 转化成十六进制不够两位，前面加零
                res += "0";
            }
            res += Integer.toHexString(temp);
        }
        return res;
    }

}
