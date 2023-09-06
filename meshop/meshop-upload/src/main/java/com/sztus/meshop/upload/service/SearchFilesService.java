package com.sztus.meshop.upload.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.upload.object.domain.UploadFiles;
import com.sztus.meshop.upload.repository.reader.UploadFilesReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchFilesService {
    @Autowired
    private UploadFilesReader uploadFilesReader;

    public JSONObject searchUploadFiles(Long pageId,Long pageSize){
        Page<UploadFiles> uploadFilesPage = new Page<>(pageId,pageSize);
        LambdaQueryChainWrapper<UploadFiles> lambdaQueryChainWrapper = new LambdaQueryChainWrapper<>(uploadFilesReader.getBaseMapper());
        lambdaQueryChainWrapper.orderByDesc(UploadFiles::getUploadedAt);
        Page<UploadFiles> page = lambdaQueryChainWrapper.page(uploadFilesPage);
        return JSONObject.parseObject(AjaxResult.success(page, CodeEnum.SUCCESS.getText()));
    }
}
