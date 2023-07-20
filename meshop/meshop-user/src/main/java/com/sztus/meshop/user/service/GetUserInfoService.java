package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.lib.cache.core.SimpleRedisRepository;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.lib.core.type.RedisKeyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserInfoService {
    @Autowired
    private SimpleRedisRepository simpleRedisRepository;

    public JSONObject getAccount(String token){
        String accountKey = simpleRedisRepository.generateKey(RedisKeyType.ACCOUNT, token);
        String accountStr = simpleRedisRepository.get(accountKey);
        if(accountStr != null){
            // 用于去掉当从redis中取数据时带的斜杆 /
            JSONObject jsonObjectAcc = (JSONObject) JSON.parse(accountStr);
            return JSONObject.parseObject(AjaxResult.success(jsonObjectAcc, CodeEnum.SUCCESS.getText()));
        }
        else {
            return JSON.parseObject(AjaxResult.failure("Token not found"));
        }

    }

    public JSONObject getAuthorization(String token){
        String authorizationKey = simpleRedisRepository.generateKey(RedisKeyType.AUTHORIZATION, token);
        String authorizationStr = simpleRedisRepository.get(authorizationKey);
        if(authorizationStr != null){
            // 用于去掉当从redis中取数据时带的斜杆 /
//            JSONObject jsonObjectAuthorization = (JSONObject) JSON.parse(authorizationStr);
            return JSONObject.parseObject(AjaxResult.success(authorizationStr, CodeEnum.SUCCESS.getText()));
        }
        else {
            return JSON.parseObject(AjaxResult.failure("Token not found"));
        }

    }


}
