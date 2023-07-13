package com.sztus.meshop.products.service;

import com.sztus.meshop.products.api.client.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SearchService {

    @Autowired
    UserApi userApi;

    public String getTestUser(){
        log.info("Start to call test user");
        String message = userApi.testUser();
        if(StringUtils.isBlank(message)){
            return "error";
        }else {
            //search
            return "success";
        }
    }

}
