package com.sztus.meshop.products.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.meshop.products.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class UserReader extends MPJBaseServiceImpl<UserMapper, User> {

}
