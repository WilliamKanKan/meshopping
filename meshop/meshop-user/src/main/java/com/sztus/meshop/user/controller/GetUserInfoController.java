package com.sztus.meshop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.user.service.GetUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class GetUserInfoController {
    @Autowired
    private GetUserInfoService getUserInfoService;
    @GetMapping(value = "/get-account")
    public JSONObject getInfo(HttpServletRequest request){
        String token = request.getHeader("Access-Token");
        return getUserInfoService.getAccount(token);
    }
    @GetMapping(value = "/get-authorization")
    public JSONObject getAuthorization(HttpServletRequest request){
        String token = request.getHeader("Access-Token");
        return getUserInfoService.getAuthorization(token);
    }
}
