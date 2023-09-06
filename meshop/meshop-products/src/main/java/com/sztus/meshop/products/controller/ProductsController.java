package com.sztus.meshop.products.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.service.DeleteProductsService;
import com.sztus.meshop.products.service.EditProductsService;
import com.sztus.meshop.products.service.SearchProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Slf4j
@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private SearchProductsService searchProductsService;
    @Autowired
    private DeleteProductsService deleteProductsService;
    @Autowired
    private EditProductsService editProductsService;
    @GetMapping(value = "/search/{id}")
    public JSONObject searchById(@PathVariable ("id") Long id){
        return searchProductsService.searchProductsById(id);
    }
    @GetMapping(value = "/search/page/{pageId}")
    public JSONObject searchByPage(
            @PathVariable("pageId") Long pageId,
            @RequestParam Long pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice
    ) {
        if (categoryId != null && categoryId == 0) {
            categoryId = null; // 将传入的 0 转换为 null，表示查询所有分类
        }
        return searchProductsService.searchAllForProducts(pageId, pageSize, categoryId,productName,minPrice,maxPrice);
    }

    @DeleteMapping(value = "/delete/{id}")
    public JSONObject deleteById(@PathVariable ("id") Long id){
        return deleteProductsService.deleteProductById(id);
    }
    @PostMapping(value = "/edit/{id}")
    public JSONObject editById(@PathVariable ("id") Long id,@RequestBody Product product){
        return editProductsService.editProductsById(id,product);
    }
}
