package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.object.domain.ProductAttribute;
import com.sztus.meshop.products.repository.mapper.ProductAttributeMapper;
import com.sztus.meshop.products.repository.mapper.ProductMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddProductsService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    @Options(useGeneratedKeys = true, keyProperty = "id")
    public JSONObject addProducts(Product product, List<ProductAttribute> productAttributes) {
        LambdaQueryWrapper<Product> productLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productLambdaQueryWrapper.eq(Product::getName, product.getName());
        Product existingProduct = productMapper.selectOne(productLambdaQueryWrapper);
        if (existingProduct != null) {
            return JSONObject.parseObject(AjaxResult.failure("name already exist"));
        } else {
            product.setCreatedAt(System.currentTimeMillis());
            product.setUpdatedAt(System.currentTimeMillis());
            productMapper.insert(product);
            Long productId = product.getId();
            for (ProductAttribute attribute : productAttributes) {
                attribute.setProductId(productId);
                productAttributeMapper.insert(attribute);
            }
            return JSONObject.parseObject(AjaxResult.success());
        }
    }
}
