package com.sztus.meshop.upload.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.upload.service.SearchFilesService;
import com.sztus.meshop.upload.service.UploadFilesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Slf4j
@RestController
@RequestMapping(value = "/upload")
public class UploadFilesController {
    @Autowired
    private UploadFilesService uploadFilesService;
    @Autowired
    private SearchFilesService searchFilesService;
    @PostMapping(value = "/files")
    public JSONObject uploadFile(@RequestParam MultipartFile file, @RequestParam(required = false) String userId) throws IOException {

        return uploadFilesService.uploadFile(file, userId);
    }
    @GetMapping(value="/search/page/{pageId}")
    public  JSONObject searchFiles(@PathVariable("pageId") Long pageId,@RequestParam Long pageSize){
        return searchFilesService.searchUploadFiles(pageId, pageSize);
    }
}
