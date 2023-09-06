package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.user.object.domain.Role;
import com.sztus.meshop.user.repository.reader.RoleReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleSearchService {
    @Autowired
    private RoleReader roleReader;

    public JSONObject searchRoleForAll (Long pageId,Long pageSize){
        Page<Role> rolePage = new Page<>(pageId,pageSize);
        LambdaQueryChainWrapper<Role> roleLambdaQueryChainWrapper = new LambdaQueryChainWrapper<>(roleReader.getBaseMapper());
        Page<Role> page = roleLambdaQueryChainWrapper.page(rolePage);
        return JSON.parseObject(AjaxResult.success(page, CodeEnum.SUCCESS.getText()));
    }
}
