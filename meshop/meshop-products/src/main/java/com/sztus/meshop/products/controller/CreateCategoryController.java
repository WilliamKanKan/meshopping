package com.sztus.meshop.products.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.products.object.domain.Category;
import com.sztus.meshop.products.service.CreateCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/products")
public class CreateCategoryController {
    @Autowired
    private CreateCategoryService createCategoryService;
    @PostMapping(value = "/create-category")
    public JSONObject createCategory(@RequestBody Category category){
        return createCategoryService.createCategory(category);

    }
}
