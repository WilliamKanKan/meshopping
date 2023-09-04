package com.sztus.meshop.products.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@TableName(value = "product")
public  class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String title;
    private String description;
    private String thumbnail;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Integer quantity;
    private Long createdAt;
    private Long updatedAt;
    private Integer categoryId;
    private Integer purchaseLimit;
    private Integer clickCount;
    private Integer status;

    public String getCategory() {
        Map<Integer, String> categoryMap = new HashMap<>();
        categoryMap.put(1, "手机");
        categoryMap.put(2, "笔记本");
        categoryMap.put(3, "手表");

        return categoryMap.getOrDefault(categoryId, "未知类别");
    }
}
