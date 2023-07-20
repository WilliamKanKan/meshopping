package com.sztus.meshop.products.object.request;

import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.object.domain.ProductAttribute;
import lombok.Data;

import java.util.List;

@Data
public class ProductForm {
    private Product product;
    private List<ProductAttribute> productAttributes;
}
