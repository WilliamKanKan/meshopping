package com.sztus.meshop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.service.SendCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class SendCodeController {
    @Autowired
    private SendCodeService sendCodeService;

    @PostMapping(value = "/send-code")
    private JSONObject sendCode(@RequestBody User user){
        String telephone = user.getTelephone();
        String email = user.getEmail();
        return sendCodeService.codeSend(telephone, email);
    }

}
