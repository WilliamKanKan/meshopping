package com.sztus.meshop.upload.repository.reader;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.meshop.upload.object.domain.UploadFiles;
import com.sztus.meshop.upload.repository.mapper.UploadFilesMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("reader")
public class UploadFilesReader extends MPJBaseServiceImpl<UploadFilesMapper, UploadFiles> {
}
