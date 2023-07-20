package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.user.object.domain.UserAddress;
import com.sztus.meshop.user.repository.mapper.UserAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    public JSONObject addUserAddress(UserAddress userAddress) {
        if (userAddress.getAddress() != null && !userAddress.getAddress().trim().isEmpty()) {
            if (userAddress.getZipCode() != null) {
                if(userAddress.getUsername() != null){
                    int row = userAddressMapper.insert(userAddress);
                    if(row > 0){
                        return JSONObject.parseObject(AjaxResult.success());
                    }
                    else {
                        return JSONObject.parseObject(AjaxResult.failure());
                    }
                }
                else {
                    return JSONObject.parseObject(AjaxResult.failure("name is empty"));
                }

            } else {
                return JSONObject.parseObject(AjaxResult.failure("Zip is empty"));
            }
        } else {
            return JSONObject.parseObject(AjaxResult.failure("Address is empty"));
        }

    }
}