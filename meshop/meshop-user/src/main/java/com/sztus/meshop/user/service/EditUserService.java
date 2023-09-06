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

import java.util.List;

@Service
@Slf4j
public class EditUserService {
    @Autowired
    private UserWriter userWriter;

    public JSONObject editUserById(Long id, User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getId, id);
        User newUser = userWriter.getOne(userLambdaQueryWrapper);

        if (newUser != null) {
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.notIn(User::getId, id);
            List<User> userList = userWriter.list(lambdaQueryWrapper);

            for (User existingUser : userList) {
                if (existingUser.getUsername().trim().equals(user.getUsername().trim())) {
                    return JSON.parseObject(AjaxResult.failure("用户名已存在！"));
                }
                if (existingUser.getTelephone().trim().equals(user.getTelephone().trim())) {
                    log.info(existingUser.getNickname());
                    log.info(user.getNickname());
                    return JSON.parseObject(AjaxResult.failure("手机号已存在！"));
                }
                if (existingUser.getNickname().trim().equals(user.getNickname().trim())) {
                    return JSON.parseObject(AjaxResult.failure("昵称已存在！"));
                }
                if (existingUser.getEmail().trim().equals(user.getEmail().trim())) {
                    return JSON.parseObject(AjaxResult.failure("邮箱已存在！"));
                }
            }

            newUser.setUsername(user.getUsername());
            newUser.setGender(user.getGender());
            newUser.setPosition(user.getPosition());
            newUser.setBirthday(user.getBirthday());
            newUser.setAvatarUrl(user.getAvatarUrl());
            newUser.setStatus(user.getStatus());
            newUser.setUpdatedAt(System.currentTimeMillis());
            newUser.setNickname(user.getNickname());

            boolean updateResult = userWriter.updateById(newUser);
            if (updateResult) {
                return JSON.parseObject(AjaxResult.success());
            } else {
                return JSON.parseObject(AjaxResult.failure("编辑操作未成功"));
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("用户不存在"));
        }
    }
}



