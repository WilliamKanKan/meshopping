package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.repository.writer.ProductWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteProductsService {
    @Autowired
    private ProductWriter productWriter;

    public JSONObject deleteProductById(Long id) {
        LambdaQueryWrapper<Product> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Product::getId, id);
        Product product = productWriter.getOne(lambdaQueryWrapper);
        if(product != null){
            boolean deleteResult = productWriter.removeById(product);
            if(deleteResult){
                return JSON.parseObject(AjaxResult.success());
            }
            else {
                return JSON.parseObject(AjaxResult.failure());
            }
        }
        else{
            return JSON.parseObject(AjaxResult.failure("Data Not Found"));
        }
    }
}
