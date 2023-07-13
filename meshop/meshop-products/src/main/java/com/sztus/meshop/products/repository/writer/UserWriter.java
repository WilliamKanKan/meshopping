package com.sztus.meshop.products.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.meshop.products.object.domain.User;
import com.sztus.meshop.products.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;

/**
 * @author LuffyWang
 */
@Repository
@DS("writer")
public class UserWriter extends MPJBaseServiceImpl<UserMapper, User> {

}
