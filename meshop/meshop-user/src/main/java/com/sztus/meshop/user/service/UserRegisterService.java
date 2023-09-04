package com.sztus.meshop.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.lib.core.type.NumberCode;
import com.sztus.meshop.lib.core.util.CreatePwUtil;
import com.sztus.meshop.lib.core.util.NumVerifyUtil;
import com.sztus.meshop.lib.core.util.UuidUtil;
import com.sztus.meshop.user.object.domain.Account;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.repository.mapper.AccountMapper;
import com.sztus.meshop.user.repository.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserRegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountMapper accountMapper;

    // Options注解的作用是主键返回，因为注册时需要在user以外其他的表存入user_id,而这个user_id就是user表里的id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public JSONObject register(User user) {
        String emailValidationResult = NumVerifyUtil.emailMatcher(user.getEmail());
        String passwordValidationResult = NumVerifyUtil.passwordMatcher(user.getPassword());
        String usernameValidationResult = NumVerifyUtil.usernameMatcher(user.getUsername());
        String phoneNumberValidationResult = NumVerifyUtil.phoneNumberMatcher(user.getTelephone());
        Account account = new Account();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername()).or().eq(User::getEmail, user.getEmail())
                                                                        .or().eq(User::getNickname, user.getNickname())
                                                                        .or().eq(User::getTelephone, user.getTelephone());
        List<User> existingUserList = userMapper.selectList(userLambdaQueryWrapper);
        for (User existingUser : existingUserList) {
            if (existingUser != null) {
                if (existingUser.getUsername().equals(user.getUsername())) {
                    return JSON.parseObject(AjaxResult.failure("username already exists"));
                } else if(existingUser.getEmail().equals(user.getEmail())) {
                    return JSON.parseObject(AjaxResult.failure("email already exists"));
                }
                else if(existingUser.getNickname().equals(user.getNickname())) {
                    return JSON.parseObject(AjaxResult.failure("nickname already exists"));
                } else {
                    return JSON.parseObject(AjaxResult.failure("telephone already exists"));
                }
            }
        }

        // 验证用户名格式
        if (!usernameValidationResult.equals("success")) {
            return JSON.parseObject(usernameValidationResult);
        }
        // 验证邮箱格式
        else if (!emailValidationResult.equals("success")) {
            return JSON.parseObject(emailValidationResult);
        }
        // 验证手机格式
        else if (!phoneNumberValidationResult.equals("success")) {
            return JSON.parseObject(phoneNumberValidationResult);
        }
        // 验证密码格式
        else if (!passwordValidationResult.equals("success")) {
            return JSON.parseObject(passwordValidationResult);
        }
        // 验证密码和确认密码是否匹配
        else if (!user.getPassword().equals(user.getConfirmPassword())) {
            return JSON.parseObject(AjaxResult.failure("InputPassword does not match with ConfirmPassword"));
        } else {
            // 全部验证通过后，将需要的值全部set到对应的对象上，最后再存入到数据库，注意：因为其他关联表需要user_id,所以要先存user表的数据
            String salt = CreatePwUtil.generateSalt();
            String uuid = UuidUtil.getUuid();
            String accessToken = UuidUtil.getUuid();
            account.setPassword(CreatePwUtil.hashPassword(user.getPassword(), salt));
            account.setSalt(salt);
            account.setRoleId(NumberCode.USER);
            account.setOpenId(uuid);
            account.setAccessToken(accessToken);
            user.setOpenId(uuid);
            user.setPosition(NumberCode.USER);
            user.setCreatedAt(System.currentTimeMillis());
            user.setUpdatedAt(System.currentTimeMillis());
            accountMapper.insert(account);
            userMapper.insert(user);
            return JSONObject.parseObject(AjaxResult.success());
        }
    }
}

