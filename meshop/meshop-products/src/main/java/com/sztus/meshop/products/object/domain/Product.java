package com.sztus.meshop.products.object.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@TableName(value = "product")
public class Product implements Serializable {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Integer quantity;
    private Long createdAt;
    private Long updatedAt;
    private Long categoryId;
    private Integer purchaseLimit;
    private Integer clickCount;
    private Integer status;
}
