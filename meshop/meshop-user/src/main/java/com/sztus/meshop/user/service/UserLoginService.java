package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.sztus.meshop.lib.cache.core.SimpleRedisRepository;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.lib.core.type.NumberCode;
import com.sztus.meshop.lib.core.type.RedisKeyType;
import com.sztus.meshop.lib.core.util.CreatePwUtil;
import com.sztus.meshop.user.object.domain.*;
import com.sztus.meshop.user.object.response.AccountDTO;
import com.sztus.meshop.user.object.response.PrivilegeDTO;
import com.sztus.meshop.user.repository.mapper.AccountMapper;
import com.sztus.meshop.user.repository.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserLoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private SimpleRedisRepository simpleRedisRepository;

    public JSONObject loginByUsername(User user) {
        AccountDTO accountDTO = new AccountDTO();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User existingUser = userMapper.selectOne(userLambdaQueryWrapper);
        if (existingUser != null) {
            LambdaQueryWrapper<Account> accountLambdaQueryWrapper = new LambdaQueryWrapper<>();
            accountLambdaQueryWrapper.eq(Account::getOpenId, existingUser.getOpenId());
            Account account = accountMapper.selectOne(accountLambdaQueryWrapper);
            if (account != null) {
                String codePassword = CreatePwUtil.hashPassword(user.getPassword(), account.getSalt());
                if (!account.getPassword().equals(codePassword)) {
                    return JSON.parseObject(AjaxResult.failure("Password is not matched"));
                } else {
                    loginSuccess(account, accountDTO, existingUser);
                    return JSONObject.parseObject(AjaxResult.success(account.getAccessToken(), CodeEnum.SUCCESS.getText()));
                }
            } else {
                return JSON.parseObject(AjaxResult.failure("Account not found"));
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("User not exist"));
        }
    }

    public JSONObject loginByTelephone(String telephone, String verifyCode) {
        AccountDTO accountDTO = new AccountDTO();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getTelephone, telephone);
        User existingUser = userMapper.selectOne(userLambdaQueryWrapper);
        if (existingUser != null) {
            LambdaQueryWrapper<Account> accountLambdaQueryWrapper = new LambdaQueryWrapper<>();
            accountLambdaQueryWrapper.eq(Account::getOpenId, existingUser.getOpenId());
            Account account = accountMapper.selectOne(accountLambdaQueryWrapper);
            if (account != null) {
                String verifyCodeKey = simpleRedisRepository.generateKey(RedisKeyType.VERIFY_CODE, telephone);
                String code = simpleRedisRepository.get(verifyCodeKey);
                if (!verifyCode.equals(code)) {
                    return JSON.parseObject(AjaxResult.failure("Code is incorrect or expired"));
                } else {
                    loginSuccess(account, accountDTO, existingUser);
                    return JSONObject.parseObject(AjaxResult.success(account.getAccessToken(), CodeEnum.SUCCESS.getText()));
                }
            } else {
                return JSON.parseObject(AjaxResult.failure("Account not found"));
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("User not exist"));
        }
    }

    public void loginSuccess(Account account, AccountDTO accountDTO, User existingUser) {
        String accountKey = simpleRedisRepository.generateKey(RedisKeyType.ACCOUNT, account.getAccessToken());
        String privilegeKey = simpleRedisRepository.generateKey(RedisKeyType.AUTHORIZATION, account.getAccessToken());
        accountDTO.setAccessToken(account.getAccessToken());
        accountDTO.setNickname(existingUser.getNickname());
        accountDTO.setAvatarUrl(existingUser.getAvatarUrl());
        accountDTO.setPosition(existingUser.getPosition());
        accountDTO.setOpenId(existingUser.getOpenId());
        accountDTO.setUserId(existingUser.getId());
        MPJLambdaWrapper<User> mpjLambdaWrapper = new MPJLambdaWrapper<User>()
                .select(Resource::getCode)
                .select(RolePrivilege::getPrivilege)
                .select(Resource::getUrl)
                .leftJoin(Role.class, Role::getId, User::getPosition)
                .leftJoin(RolePrivilege.class, RolePrivilege::getRoleId, Role::getId)
                .leftJoin(Resource.class, Resource::getId, RolePrivilege::getResourceId).eq(User::getId, existingUser.getId());
        List<PrivilegeDTO> privileges = userMapper.selectJoinList(PrivilegeDTO.class, mpjLambdaWrapper);
        simpleRedisRepository.set(privilegeKey, JSON.toJSONString(privileges), NumberCode.EXPIRED_TIME);
        simpleRedisRepository.set(accountKey, JSON.toJSONString(accountDTO), NumberCode.EXPIRED_TIME);
        account.setExpiredAt(System.currentTimeMillis() + NumberCode.EXPIRED_TIME * 1000);
        LambdaUpdateWrapper<Account> accountLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        accountLambdaUpdateWrapper.eq(Account::getOpenId, account.getOpenId());
        accountMapper.update(account, accountLambdaUpdateWrapper);
    }

}
