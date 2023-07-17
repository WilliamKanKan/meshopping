package com.sztus.meshop.cart.object;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@TableName(value = "cart")
public class Cart implements Serializable {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Long createdAt;
    private Long updatedAt;
}
