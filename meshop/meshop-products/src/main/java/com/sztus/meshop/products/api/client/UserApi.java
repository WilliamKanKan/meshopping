package com.sztus.meshop.products.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "meshop-user")
public interface UserApi {

    @PostMapping("/user/test")
    String testUser();

}
