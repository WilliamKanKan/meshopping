package com.sztus.meshop.user.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.meshop.user.object.domain.User;
import com.sztus.meshop.user.repository.mapper.UserMapper;
import org.springframework.stereotype.Repository;


@Repository
@DS("writer")
public class UserWriter extends MPJBaseServiceImpl<UserMapper, User> {

}
