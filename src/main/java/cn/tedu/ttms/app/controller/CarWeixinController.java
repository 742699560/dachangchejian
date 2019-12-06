package cn.tedu.ttms.app.controller;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.base.controller.BaseController;
import cn.tedu.ttms.common.exception.AppException;
import cn.tedu.ttms.common.web.PageObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/car/")
public class CarWeixinController extends BaseController {

    @RequestMapping("/queryList")
    @ResponseBody
    public Map<String, Object> queryList(HttpServletRequest req, @RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize) {
        Map<String, String> paramsMap = this.getParamMap(req);
        if (!paramsMap.containsKey("tid"))
            throw new AppException("请求参数错误");
        Integer tid = Integer.parseInt(paramsMap.get("tid"));
        PageObject pageObject = new PageObject();
        pageObject.setPageCurrent(pageNum);
        pageObject.setPageSize(pageSize);
        Attachement param = new Attachement();
        param.setTid(tid);
        Map<String, Object> dataMap = attachementService.findObjects(param, pageObject);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1);
        map.put("data", dataMap.get("rows"));
        map.put("message", "查询成功!");
        return map;
    }

}
