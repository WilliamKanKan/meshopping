package com.sztus.meshop.products.controller;

import com.sztus.meshop.products.api.client.UserApi;
import com.sztus.meshop.products.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("test")
    String getTest(){
        return searchService.getTestUser();
    }
}
