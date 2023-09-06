package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.products.object.domain.Category;
import com.sztus.meshop.products.object.domain.Product;
import com.sztus.meshop.products.repository.reader.ProductReader;
import com.sztus.meshop.products.repository.writer.CategoryWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteCategoryService {
    @Autowired
    private CategoryWriter categoryWriter;
    @Autowired
    private ProductReader productReader;

    public JSONObject deleteCategoryById (Long id){
        LambdaQueryWrapper<Category> categoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryLambdaQueryWrapper.eq(Category::getId, id);
        Category category = categoryWriter.getOne(categoryLambdaQueryWrapper);
        LambdaQueryWrapper<Product> productLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productLambdaQueryWrapper.eq(Product::getCategoryId, id);
        List<Product>  productList = productReader.list(productLambdaQueryWrapper);
        if(category !=null ){
            if(!productList.isEmpty() ){
                return JSON.parseObject(AjaxResult.failure("There are products under this category!"));
            }else {
                boolean deleteResult = categoryWriter.removeById(category);
                if(deleteResult){
                    return JSON.parseObject(AjaxResult.success());
                }
                else {
                    return JSON.parseObject(AjaxResult.failure());
                }
            }
            }
        else {
            return JSON.parseObject(AjaxResult.failure("Category not found"));
        }

    }
}
