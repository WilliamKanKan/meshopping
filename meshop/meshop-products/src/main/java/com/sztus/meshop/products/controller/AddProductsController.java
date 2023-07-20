package com.sztus.meshop.products.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.object.domain.ProductAttribute;
import com.sztus.meshop.products.object.request.ProductForm;
import com.sztus.meshop.products.service.AddProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/products")
public class AddProductsController {
    @Autowired
    private AddProductsService addProductsService;
    @PostMapping(value = "/add-product")
    public JSONObject addProduct(@RequestBody ProductForm productForm){
        Product product = productForm.getProduct();
        List<ProductAttribute> productAttributes = productForm.getProductAttributes();
        return addProductsService.addProducts(product, productAttributes);
    }
}
