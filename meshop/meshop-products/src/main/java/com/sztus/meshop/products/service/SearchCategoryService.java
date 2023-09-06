package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Category;
import com.sztus.meshop.products.repository.reader.CategoryReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchCategoryService {
    @Autowired
    private CategoryReader categoryReader;

    public JSONObject searchAllForCategory (Long pageId, Long pageSize,String categoryName){
        Page<Category> categoryPage = new Page<>(pageId,pageSize);
        LambdaQueryChainWrapper<Category> queryWrapper = new LambdaQueryChainWrapper<>(categoryReader.getBaseMapper());
        // 如果传入的 categoryName 不为空，表示要进行名字模糊搜索
        if (categoryName != null && !categoryName.isEmpty()) {
            queryWrapper.like(Category::getName, categoryName);
        }
        Page<Category> page = queryWrapper.page(categoryPage);
        return JSONObject.parseObject(AjaxResult.success(page, CodeEnum.SUCCESS.getText()));
    }
}
