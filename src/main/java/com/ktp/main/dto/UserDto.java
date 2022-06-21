package com.ktp.main.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {

    private String id;

    private String email;

    private String phone;

    private String username;

    private String password;

    private String account;

    private String avatar;

    private Integer role;

    private String token;

}
