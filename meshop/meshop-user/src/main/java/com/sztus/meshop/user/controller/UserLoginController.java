package com.sztus.meshop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
    @PostMapping(value = "/login")
    public JSONObject login(@RequestBody User user){
        return userLoginService.loginByEmail(user);
    }
    @PostMapping(value = "/login-phone")
    public JSONObject loginByPhone(@RequestBody JSONObject object){
        String telephone = object.getString("telephone");
        String verifyCode = object.getString("verifyCode");
        return userLoginService.loginByTelephone(telephone, verifyCode);
    }
}
