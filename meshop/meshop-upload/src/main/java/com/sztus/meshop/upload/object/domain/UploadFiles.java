package com.sztus.meshop.upload.object.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "file")
public class UploadFiles implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userId;
    private String fileName;
    private String url;
    private Long uploadedAt;
}
