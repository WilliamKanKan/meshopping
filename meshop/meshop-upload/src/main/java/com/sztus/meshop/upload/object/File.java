package com.sztus.meshop.upload.object;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "file")
public class File implements Serializable {
    private Long id;
    private Long userId;
    private Long productId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long uploadedAt;
}
