package com.sztus.meshop.products.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "category")
public class Category implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String imageUrl;
    private Long createdAt;
    private Long updatedAt;
    private Integer status;
}
