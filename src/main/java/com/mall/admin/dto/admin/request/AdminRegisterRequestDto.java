package com.mall.admin.dto.admin.request;

import lombok.Data;

@Data
public class AdminRegisterRequestDto {

    private String email;

    private String icon;

    private String nickName;

    private String note;

    private String username;

    private String password;

}
