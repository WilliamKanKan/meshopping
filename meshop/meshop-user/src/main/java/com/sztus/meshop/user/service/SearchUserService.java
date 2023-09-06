package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.repository.reader.UserReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchUserService {
    @Autowired
    private UserReader userReader;

    public JSONObject searchUserForAll(Long pageId,Long pageSize,String nickname){
        Page<User> userPage = new Page<>(pageId,pageSize);
        LambdaQueryChainWrapper<User> lambdaQueryChainWrapper = new LambdaQueryChainWrapper<>(userReader.getBaseMapper());
        if(nickname!=null){
            lambdaQueryChainWrapper.like(User::getNickname,nickname);
        }
        Page<User> page = lambdaQueryChainWrapper.page(userPage);
        return JSONObject.parseObject(AjaxResult.success(page, CodeEnum.SUCCESS.getText()));
    }
}
