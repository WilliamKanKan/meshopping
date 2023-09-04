package com.sztus.meshop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.object.domain.UserAddress;
import com.sztus.meshop.user.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private GetUserInfoService getUserInfoService;
    @Autowired
    private SearchUserService searchUserService;
    @Autowired
    private EditUserService editUserService;
    @Autowired
    private DeleteUserService deleteUserService;
    @Autowired
    private DeleteUserAddressService deleteUserAddressService;
    @Autowired
    private EditUserAddressService editUserAddressService;
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
    @GetMapping(value = "/search/page/{pageId}")
    public JSONObject searchAllUser(@PathVariable("pageId") Long pageId,@RequestParam Long pageSize,@RequestParam(required = false) String nickname){
        return searchUserService.searchUserForAll(pageId, pageSize, nickname);

    }
    @PostMapping(value = "/edit/{id}")
    public JSONObject editUserById(@PathVariable("id") Long id,@RequestBody User user){
        return editUserService.editUserById(id,user);

    }
    @DeleteMapping(value = "/delete/{id}")
    public JSONObject deleteUserById(@PathVariable("id") Long id){
        return deleteUserService.deleteUserById(id);
    }
    @DeleteMapping(value = "/delete-address/{id}")
    public JSONObject deleteUserAddressById(@PathVariable("id") Long id){
        return deleteUserAddressService.deleteUserAddressById(id);
    }
    @PostMapping(value = "/edit-address/{id}")
    public JSONObject editUserAddressById(@PathVariable("id") Long id, @RequestBody UserAddress userAddress){
        return editUserAddressService.editUserAddressById(id,userAddress);
    }
}
