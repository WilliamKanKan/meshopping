package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.repository.mapper.ProductMapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddProductsService {
    @Autowired
    private ProductMapper productMapper;

    @Options(useGeneratedKeys = true, keyProperty = "id")
    public JSONObject addProducts(Product product) {
        LambdaQueryWrapper<Product> productLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productLambdaQueryWrapper.eq(Product::getName, product.getName());
        Product existingProduct = productMapper.selectOne(productLambdaQueryWrapper);
        if (existingProduct != null) {
            return JSONObject.parseObject(AjaxResult.failure("name already exist"));
        } else {
            product.setCreatedAt(System.currentTimeMillis());
            product.setUpdatedAt(System.currentTimeMillis());
            productMapper.insert(product);
            return JSONObject.parseObject(AjaxResult.success());
        }
    }
}
