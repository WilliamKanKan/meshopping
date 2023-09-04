package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.lib.cache.core.SimpleRedisRepository;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.lib.core.type.RedisKeyType;
import com.sztus.meshop.user.object.response.PrivilegeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            List<PrivilegeDTO> privilegeDTOS = JSON.parseArray(authorizationStr, PrivilegeDTO.class);
            return JSONObject.parseObject(AjaxResult.success(privilegeDTOS, CodeEnum.SUCCESS.getText()));
        }
        else {
            return JSON.parseObject(AjaxResult.failure("Token not found"));
        }

    }


}
