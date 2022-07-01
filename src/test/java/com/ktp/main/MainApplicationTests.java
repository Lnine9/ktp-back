package com.ktp.main;

import com.ktp.main.dto.CourseDto;
import com.ktp.main.dto.UserDto;
import com.ktp.main.mapper.UserCourseMapper;
import com.ktp.main.service.CourseService;
import com.ktp.main.service.LoginService;
//import com.ktp.main.service.StringCacheService;
import com.ktp.main.service.UserCourseService;
import com.ktp.main.util.JwtUtil;
import com.ktp.main.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class MainApplicationTests {

    @Autowired
    UserCourseMapper mapper;

    @Autowired
    LoginService loginService;

//    @Autowired
//    StringCacheService cacheService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserCourseService userCourseService;

    @Test
    void contextLoads() {
//        System.out.println(loginService.login("15668165149", "123456"));
        UserDto userInfo = new UserDto().setUsername("韭菜盒子").setAccount("12023020400").setEmail("1498613781@qq.com").setRole(0);
        System.out.println(loginService.registry(userInfo));
    }

//    @Test
//    void redis(){
//        cacheService.add("11","dwadawd");
//        System.out.println(cacheService.get("11"));
//    }

    @Test
    void jwt(){
        String t = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NTU3ODgxNTcsInVzZXJJZCI6IjM1NTQzNSIsInVzZXJuYW1lIjoiMTExIn0.JGDvg8GESti0wT_lTHMKgXkHYA8dck3Mm9pAtFOkSWo";
        String t2 = JwtUtil.sign("111","355435");
        System.out.println(t);
        System.out.println(t2);
        System.out.println(t.equals(t2));
    }

    @Test
    void course(){
//        CourseDto courseInfo = new CourseDto().setId("JNCWXZ");
//        System.out.println(courseService.updateCourse(courseInfo.setTerm("2022").setCourseName("APEX")));
//        System.out.println(courseService.dropCourse("JNCWXZ", "2"));
//        System.out.println(userCourseService.quitCourse("JNCWXZ", "1"));
        System.out.println(userCourseService.getCourses("1"));
//        System.out.println(mapper.getCourseTeacher("ULDIWF"));
    }

}
