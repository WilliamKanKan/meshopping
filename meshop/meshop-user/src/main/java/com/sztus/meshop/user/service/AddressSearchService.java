package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.user.object.domain.UserAddress;
import com.sztus.meshop.user.repository.reader.UserAddressReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressSearchService {
    @Autowired
    private UserAddressReader userAddressReader;

    public JSONObject searchUserAddressByUserId(Long userId){
        LambdaQueryWrapper<UserAddress> userAddressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userAddressLambdaQueryWrapper.eq(UserAddress::getUserId,userId);
        List<UserAddress> userAddressList = userAddressReader.list(userAddressLambdaQueryWrapper);
        if(!userAddressList.isEmpty()){
            return JSON.parseObject(AjaxResult.success(userAddressList, CodeEnum.SUCCESS.getText()));

        }else {
            return JSON.parseObject(AjaxResult.failure("No data found"));
        }
    }
}
