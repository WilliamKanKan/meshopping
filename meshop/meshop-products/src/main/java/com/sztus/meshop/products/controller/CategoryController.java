package com.sztus.meshop.products.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.products.object.domain.Category;
import com.sztus.meshop.products.service.CreateCategoryService;
import com.sztus.meshop.products.service.DeleteCategoryService;
import com.sztus.meshop.products.service.EditCategoryService;
import com.sztus.meshop.products.service.SearchCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CreateCategoryService createCategoryService;
    @Autowired
    private SearchCategoryService searchCategoryService;
    @Autowired
    private EditCategoryService editCategoryService;
    @Autowired
    private DeleteCategoryService deleteCategoryService;

    @PostMapping(value = "/create-category")
    public JSONObject createCategory(@RequestBody Category category){
        return createCategoryService.createCategory(category);

    }
    @GetMapping(value = "/search/page/{pageId}")
    public JSONObject searchByPage (@PathVariable("pageId") Long pageId, @RequestParam Long pageSize,@RequestParam String categoryName){
        return searchCategoryService.searchAllForCategory(pageId,pageSize,categoryName);
    }
    @PostMapping(value = "/edit/{id}")
    public JSONObject editById(@PathVariable("id")Long id,@RequestBody Category category){
        return editCategoryService.editCategoryById(id,category);
    }
    @DeleteMapping(value="/delete/{id}")
    public JSONObject deleteById(@PathVariable("id")Long id){
        return deleteCategoryService.deleteCategoryById(id);
    }

}
