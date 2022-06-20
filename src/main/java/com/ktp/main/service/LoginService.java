package com.ktp.main.service;

import com.ktp.main.dto.UserDto;
import com.ktp.main.util.ResResult;

public interface LoginService {

    ResResult<UserDto> login(String key, String password);

    ResResult<Object> logout(String token);

}
