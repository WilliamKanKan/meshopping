package com.sztus.meshop.user.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.meshop.user.object.domain.Role;
import com.sztus.meshop.user.repository.mapper.RoleMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class RoleWriter extends MPJBaseServiceImpl<RoleMapper, Role> {
}
