package com.ktp.main.controller;

import com.ktp.main.dto.UserDto;
import com.ktp.main.service.LoginService;
import com.ktp.main.util.ResResult;
import com.ktp.main.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/")
    public ResResult<UserDto> login(@RequestParam String key, @RequestParam String password){
        return loginService.login(key, password);
    }

}
