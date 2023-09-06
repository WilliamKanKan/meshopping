package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.repository.writer.ProductWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EditProductsService {
    @Autowired
    private ProductWriter productWriter;

    @Options(useGeneratedKeys = true, keyProperty = "id")
    public JSONObject editProductsById(Long id, Product product) {
        LambdaQueryWrapper<Product> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Product::getId, id);
        Product newProduct = productWriter.getOne(lambdaQueryWrapper);

        if (newProduct != null) {
            LambdaQueryWrapper<Product> productLambdaQueryWrapper = new LambdaQueryWrapper<>();
            productLambdaQueryWrapper.notIn(Product::getId, id);
            List<Product> productList = productWriter.list(productLambdaQueryWrapper);

            for (Product existingProduct : productList) {
                if (existingProduct.getName().equals(product.getName())) {
                    return JSON.parseObject(AjaxResult.failure("商品名称已存在"));
                }
            }

            newProduct.setCategoryId(product.getCategoryId());
            newProduct.setQuantity(product.getQuantity());
            newProduct.setPrice(product.getPrice());
            newProduct.setTitle(product.getTitle());
            newProduct.setThumbnail(product.getThumbnail());
            newProduct.setPurchaseLimit(product.getPurchaseLimit());
            newProduct.setDescription(product.getDescription());
            newProduct.setDiscountPrice(product.getDiscountPrice());
            newProduct.setUpdatedAt(System.currentTimeMillis());
            newProduct.setName(product.getName());
            newProduct.setStatus(product.getStatus());

            boolean updateResult = productWriter.updateById(newProduct);
            if (updateResult) {
                return JSON.parseObject(AjaxResult.success());
            } else {
                return JSON.parseObject(AjaxResult.failure());
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("商品不存在"));
        }

    }

}
