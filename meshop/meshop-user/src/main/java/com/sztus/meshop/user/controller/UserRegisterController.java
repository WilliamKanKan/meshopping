package com.sztus.meshop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.service.UserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserRegisterController {
    @Autowired
    private UserRegisterService userRegisterService;
    @PostMapping("/register")
    public JSONObject userRegister(@RequestBody User user){
        return userRegisterService.register(user);
    }
}
