package com.ktp.main.controller;

import com.ktp.main.dto.UserDto;
import com.ktp.main.service.LoginService;
import com.ktp.main.util.ResResult;
import com.ktp.main.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping
    public ResResult<UserDto> login(@RequestParam String key, @RequestParam String password){
        return loginService.login(key, password);
    }

    @GetMapping("/logout")
    public ResResult<Object> logout(){
        return loginService.logout();
    }

    @PostMapping("/registry")
    public ResResult<Object> registry(@RequestBody UserDto userInfo){
        return loginService.registry(userInfo);
    }

}
