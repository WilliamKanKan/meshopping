package com.sztus.meshop.user.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "user_address")
public class UserAddress implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String address;
    private Integer zipCode;
    private Integer status;
}
