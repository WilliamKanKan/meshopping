package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.user.object.domain.UserAddress;
import com.sztus.meshop.user.repository.writer.UserAddressWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserAddressService {
    @Autowired
    private UserAddressWriter userAddressWriter;

    public JSONObject deleteUserAddressById(Long id){
        LambdaQueryWrapper<UserAddress> userAddressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userAddressLambdaQueryWrapper.eq(UserAddress::getId,id);
        UserAddress userAddress = userAddressWriter.getOne(userAddressLambdaQueryWrapper);
        if(userAddress!=null){
            boolean deleteResult = userAddressWriter.removeById(userAddress);
            if(deleteResult){
                return JSON.parseObject(AjaxResult.success());
            }else {
                return JSON.parseObject(AjaxResult.failure());
            }
        }else {
            return JSON.parseObject(AjaxResult.failure("Address not found"));
        }
    }
}
