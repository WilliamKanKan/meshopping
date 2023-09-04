package com.sztus.meshop.user.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer position;
    private String openId;
    private String username;
    private String email;
    @TableField(exist = false)
    private String password;
    @TableField(exist = false)
    private String confirmPassword;
    private String telephone;
    private Long birthday;
    private String nickname;
    private Long gender;
    private String avatarUrl;
    private Long createdAt;
    private Long updatedAt;
    private Integer status;
}
