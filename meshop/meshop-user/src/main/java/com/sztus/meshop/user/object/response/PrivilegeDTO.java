package com.sztus.meshop.user.object.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.io.Serializable;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrivilegeDTO implements Serializable {
    private String code;
    private int privilege;
    private String url;
}
