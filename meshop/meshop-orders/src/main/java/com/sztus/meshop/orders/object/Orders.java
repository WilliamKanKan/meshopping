package com.sztus.meshop.orders.object;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@TableName(value = "orders")
public class Orders implements Serializable {
    private Long id;
    private String orderNumber;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private String paymentMethod;
    private Integer paymentStatus;
    private Integer orderStatus;
    private String logisticsInfo;
    private String orderNotes;
    private Long paymentDueAt;
    private Long createdAt;
    private Long updatedAt;
}
