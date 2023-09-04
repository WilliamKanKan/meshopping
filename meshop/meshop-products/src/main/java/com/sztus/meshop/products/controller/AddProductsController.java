package com.sztus.meshop.products.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.service.AddProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/products")
public class AddProductsController {
    @Autowired
    private AddProductsService addProductsService;
    @PostMapping(value = "/add-product")
    public JSONObject addProduct(@RequestBody Product product){
        return addProductsService.addProducts(product);
    }
}
