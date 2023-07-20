package com.sztus.meshop.products.service;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.products.repository.mapper.ProductAttributeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddProductAttributeService {
    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    public void addProductAttribute(JSONObject jsonObject){

    }
}
