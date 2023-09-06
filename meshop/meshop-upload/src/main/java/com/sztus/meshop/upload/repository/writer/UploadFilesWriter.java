package com.sztus.meshop.upload.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.meshop.upload.object.domain.UploadFiles;
import com.sztus.meshop.upload.repository.mapper.UploadFilesMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class UploadFilesWriter extends MPJBaseServiceImpl<UploadFilesMapper, UploadFiles> {
}
