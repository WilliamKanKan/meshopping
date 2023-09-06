package com.sztus.meshop.user.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.meshop.user.object.domain.UserAddress;
import com.sztus.meshop.user.repository.mapper.UserAddressMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class UserAddressReader extends MPJBaseServiceImpl<UserAddressMapper, UserAddress> {
}
