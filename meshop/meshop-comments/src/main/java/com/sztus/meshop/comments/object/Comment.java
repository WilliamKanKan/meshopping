package com.sztus.meshop.comments.object;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName(value = "comment")
public class Comment implements Serializable {
    private Long id;
    private Long userId;
    private String nickname;
    private Long productId;
    private String commentContent;
    private String replyContent;
    private Long createdAt;
    private Long updatedAt;
}
