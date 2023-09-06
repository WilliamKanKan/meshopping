package com.sztus.meshop.user.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.meshop.user.object.domain.Role;
import com.sztus.meshop.user.repository.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class RoleReader extends MPJBaseServiceImpl<RoleMapper, Role> {
}
