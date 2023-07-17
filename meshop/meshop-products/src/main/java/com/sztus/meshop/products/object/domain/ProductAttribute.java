package com.sztus.meshop.products.object.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "product_attribute")
public class ProductAttribute implements Serializable {
    private Long id;
    private Long productId;
    private String attributeName;
    private String attributeValue;
}
