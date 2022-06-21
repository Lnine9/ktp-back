package com.ktp.main.controller;


import com.ktp.main.dto.CourseDto;
import com.ktp.main.service.UserCourseService;
import com.ktp.main.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/api/userCourse")
public class UserCourseController {

    @Autowired
    UserCourseService userCourseService;

    @PostMapping
    public ResResult<Object> joinCourse(HttpServletRequest req, @RequestBody String courseId, @RequestBody Integer type){
        String userId =(String) req.getAttribute("userId");
        return userCourseService.joinCourse(courseId, userId, type);
    }

    @DeleteMapping
    public ResResult<Object> quitCourse(HttpServletRequest req, @RequestBody String courseId){
        String userId =(String) req.getAttribute("userId");
        return userCourseService.quitCourse(courseId, userId);
    }

    @GetMapping
    public ResResult<List<CourseDto>> getCourses(@RequestParam String userId){
        return userCourseService.getCourses(userId);
    }

}

