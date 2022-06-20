package com.ktp.main;

import com.ktp.main.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    LoginService loginService;

    @Test
    void contextLoads() {
        System.out.println(loginService.login("12023020402", "123456"));
    }

}
