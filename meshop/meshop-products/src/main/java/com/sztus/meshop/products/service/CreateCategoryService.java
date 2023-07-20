package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Category;
import com.sztus.meshop.products.repository.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateCategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public JSONObject createCategory(Category category){
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(Category::getName,category.getName()).or().eq(Category::getSlug,category);
        Category existingCategory = categoryMapper.selectOne(categoryLambdaQueryWrapper);
        if(existingCategory !=null ){
            if(existingCategory.getName().equals(category.getName())){
                return JSONObject.parseObject(AjaxResult.failure("name already exist"));
            }
            else {
                return JSONObject.parseObject(AjaxResult.failure("slug already exist"));
            }
        }else {
            category.setCreatedAt(System.currentTimeMillis());
            category.setUpdatedAt(System.currentTimeMillis());
            categoryMapper.insert(category);
            return JSONObject.parseObject(AjaxResult.success());
        }

    }
}
