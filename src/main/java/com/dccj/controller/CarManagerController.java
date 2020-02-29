package com.dccj.controller;

import com.dccj.entity.UserCar;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cars")
public class CarManagerController extends BaseController {

    @RequestMapping("/queryPayData")
    @ResponseBody
    public RespEntity queryPayData(@RequestParam String userId) {
        UserCar param = new UserCar();
        param.setStatus(2);
        param.setInputStatus(2);
        param.setInputAjStatus(2);
        RespEntity respEntity= new RespEntity();
        return respEntity;
    }
}
