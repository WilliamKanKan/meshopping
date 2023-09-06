package com.sztus.meshop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class AdminLoginController {
    @Autowired
    private UserLoginService userLoginService;
    @PostMapping(value = "/admin")
    public JSONObject adminLogin(@RequestBody User user){
        if(user.getUsername().equals("william")){
            return userLoginService.loginByUsername(user);
        } else {
            return JSONObject.parseObject(AjaxResult.failure(CodeEnum.FAILURE.getText()));
        }

    }
}
