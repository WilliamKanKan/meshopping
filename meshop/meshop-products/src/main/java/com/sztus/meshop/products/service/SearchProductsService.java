package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.repository.reader.ProductReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class SearchProductsService {
    @Autowired
    private ProductReader productReader;


    public JSONObject searchProductsById(Long id) {
        LambdaQueryWrapper<Product> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Product::getId, id);
        Product product = productReader.getOne(lambdaQueryWrapper);
        if (product != null) {
            String category = product.getCategory();
            JSONObject resultJson = JSONObject.parseObject(AjaxResult.success(product, CodeEnum.SUCCESS.getText()));
            resultJson.put("category", category);
            return resultJson;
        } else {
            return JSONObject.parseObject(AjaxResult.failure("No data found"));
        }
    }
    public JSONObject searchAllForProducts(Long pageId, Long pageSize, Integer categoryId, String productName, BigDecimal minPrice, BigDecimal maxPrice) {
        Page<Product> productPage = new Page<>(pageId, pageSize);

        LambdaQueryChainWrapper<Product> queryWrapper = new LambdaQueryChainWrapper<>(productReader.getBaseMapper());

        // 如果传入的 categoryId 不为空，表示要按分类查询
        if (categoryId != null) {
            queryWrapper.eq(Product::getCategoryId, categoryId);
        }

        // 如果传入的 productName 不为空，表示要进行名字模糊搜索
        if (productName != null && !productName.isEmpty()) {
            queryWrapper.like(Product::getName, productName);
        }

        // 如果传入的 minPrice 不为空，表示要按照最低价格查询
        if (minPrice != null) {
            queryWrapper.ge(Product::getPrice, minPrice);
        }

        // 如果传入的 maxPrice 不为空，表示要按照最高价格查询
        if (maxPrice != null) {
            queryWrapper.le(Product::getPrice, maxPrice);
        }

        Page<Product> page = queryWrapper.page(productPage);

        return JSONObject.parseObject(AjaxResult.success(page, CodeEnum.SUCCESS.getText()));
    }




}
