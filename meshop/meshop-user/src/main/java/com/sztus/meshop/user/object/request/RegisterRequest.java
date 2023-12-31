package com.sztus.meshop.user.object.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterRequest {

    @NotNull(message = "must not null")
    private String name;

    @NotNull(message = "must not null")
    private String email;

    @NotNull(message = "must not null")
    private String password;

}
