package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Category;
import com.sztus.meshop.products.repository.writer.CategoryWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EditCategoryService {
    @Autowired
    private CategoryWriter categoryWriter;

    @Options(useGeneratedKeys = true, keyProperty = "id")
    public JSONObject editCategoryById(Long id, Category category) {
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(Category::getId, id);
        Category newCategory = categoryWriter.getOne(categoryLambdaQueryWrapper);

        if (newCategory != null) {
            LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.notIn(Category::getId, id);
            List<Category> categoryList = categoryWriter.list(lambdaQueryWrapper);

            for (Category existingCategory : categoryList) {
                if (existingCategory.getName().equals(category.getName())) {
                    return JSON.parseObject(AjaxResult.failure("分类名称已存在"));
                } else if (existingCategory.getSlug().equals(category.getSlug())) {
                    return JSON.parseObject(AjaxResult.failure("Slug 已存在"));
                }
            }

            newCategory.setSlug(category.getSlug());
            newCategory.setName(category.getName());
            newCategory.setDescription(category.getDescription());
            newCategory.setImageUrl(category.getImageUrl());
            newCategory.setStatus(category.getStatus());
            newCategory.setUpdatedAt(System.currentTimeMillis());
            log.info(newCategory.toString());

            boolean updateResult = categoryWriter.updateById(newCategory);
            if (updateResult) {
                return JSON.parseObject(AjaxResult.success());
            } else {
                return JSON.parseObject(AjaxResult.failure());
            }
        } else {
            return JSON.parseObject(AjaxResult.failure("分类不存在"));
        }
    }

}

