package com.sztus.meshop.user.object.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO implements Serializable {
    private Integer position;
    private Long UserId;
    private String openId;
    private String username;
    private String nickname;
    private String avatarUrl;
    private String accessToken;
}
