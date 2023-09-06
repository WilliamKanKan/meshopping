package com.sztus.meshop.upload.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sztus.meshop.lib.core.enumerate.CodeEnum;
import com.sztus.meshop.lib.core.type.AjaxResult;
import com.sztus.meshop.lib.core.util.UuidUtil;
import com.sztus.meshop.upload.object.domain.UploadFiles;
import com.sztus.meshop.upload.repository.writer.UploadFilesWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@Slf4j
public class UploadFilesService {
    @Autowired
    private UploadFilesWriter uploadFilesWriter;

    @Autowired
    private ResourceLoader resourceLoader; // ResourceLoader用于获取项目资源路径
    public JSONObject uploadFile(MultipartFile file, String userId) throws IOException {
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = UuidUtil.getUuid() + "_" + originalFileName;
        String serverUrl = "http://172.18.1.36:8910";

        // 获取静态资源路径
        String staticResourcePath = resourceLoader.getResource("classpath:/static/").getFile().getAbsolutePath();

        // 构建上传文件的目标路径
        String targetLocation = staticResourcePath + "/upload/" + uniqueFileName;

        try {
            // 将上传文件保存到目标路径
            Path targetPath = Paths.get(targetLocation);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = serverUrl +"/upload/" + uniqueFileName; // 构建文件的完整 URL
            UploadFiles uploadFiles = new UploadFiles();
            uploadFiles.setUserId(userId);
            uploadFiles.setUrl(fileUrl);
            uploadFiles.setFileName(uniqueFileName);
            uploadFiles.setUploadedAt(System.currentTimeMillis());

            boolean uploadResult = uploadFilesWriter.saveOrUpdate(uploadFiles);
            if (uploadResult) {
                return JSON.parseObject(AjaxResult.success(uploadFiles, CodeEnum.SUCCESS.getText()));
            } else {
                return JSON.parseObject(AjaxResult.failure());
            }

        } catch (IOException e) {
            throw new IOException("Could not store file " + uniqueFileName, e);
        }

    }
}
