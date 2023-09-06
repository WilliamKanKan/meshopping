package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.user.object.domain.UserAddress;
import com.sztus.meshop.user.repository.writer.UserAddressWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EditUserAddressService {
    @Autowired
    private UserAddressWriter userAddressWriter;

    public JSONObject editUserAddressById(Long id,UserAddress userAddress){
        LambdaQueryWrapper<UserAddress> userAddressLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userAddressLambdaQueryWrapper.eq(UserAddress::getId,id);
        UserAddress newUserAddress = userAddressWriter.getOne(userAddressLambdaQueryWrapper);
        if(newUserAddress!=null){
          newUserAddress.setUsername(userAddress.getUsername());
          newUserAddress.setAddress(userAddress.getAddress());
          newUserAddress.setTelephone(userAddress.getTelephone());
          newUserAddress.setZipCode(userAddress.getZipCode());
          newUserAddress.setStatus(userAddress.getStatus());
          boolean updateResult = userAddressWriter.updateById(newUserAddress);
          if(updateResult){
              return JSON.parseObject(AjaxResult.success());
          }else {
              return JSON.parseObject(AjaxResult.failure());
          }
        }else {
            return JSON.parseObject(AjaxResult.failure("Address not exist"));
        }
    }
}
