package com.ktp.main.controller;


import com.ktp.main.dto.CourseDto;
import com.ktp.main.entity.Course;
import com.ktp.main.service.CourseService;
import com.ktp.main.util.JwtUtil;
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
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping
    public ResResult<CourseDto> getCourseById(@RequestParam String courseId){
        return courseService.getCourseById(courseId);
    }

    @PostMapping
    public ResResult<Object> createCourse(@RequestBody CourseDto courseInfo, HttpServletRequest req){
        String userId = (String) req.getAttribute("userId");
        return courseService.createCourse(courseInfo, userId);
    }

    @PutMapping
    public ResResult<CourseDto> updateCourse(@RequestBody CourseDto courseInfo){
        return courseService.updateCourse(courseInfo);
    }

    @DeleteMapping
    public ResResult<Object> dropCourse(@RequestParam String courseId, HttpServletRequest req){
        String userId = (String) req.getAttribute("userId");
        return courseService.dropCourse(courseId, userId);
    }

}

