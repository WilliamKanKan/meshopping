package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.repository.writer.UserWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteUserService {
    @Autowired
    private UserWriter userWriter;

    public JSONObject deleteUserById (Long id){
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getId,id);
        User user = userWriter.getOne(userLambdaQueryWrapper);
        if(user!=null){
            boolean deleteResult = userWriter.removeById(user);
            if(deleteResult){
                return JSON.parseObject(AjaxResult.success());
            }else {
                return JSON.parseObject(AjaxResult.failure());
            }
        }else {
            return JSON.parseObject(AjaxResult.failure("User not exists!"));
        }
    }

}
