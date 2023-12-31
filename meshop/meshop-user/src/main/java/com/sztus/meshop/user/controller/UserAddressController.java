package com.sztus.meshop.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.user.object.domain.UserAddress;
import com.sztus.meshop.user.service.AddressSearchService;
import com.sztus.meshop.user.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private AddressSearchService addressSearchService;
    @PostMapping(value = "/add-address")
    public JSONObject addAddress(@RequestBody UserAddress userAddress){
        return userAddressService.addUserAddress(userAddress);
    }
    @GetMapping("/search-address/{userId}")
    public JSONObject searchAddressById(@PathVariable("userId") Long userId){
        return addressSearchService.searchUserAddressByUserId(userId);
    }
}
