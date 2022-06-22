package com.ktp.main.controller;


import com.ktp.main.dto.HomeworkDto;
import com.ktp.main.service.HomeworkService;
import com.ktp.main.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-06-22
 */
@RestController
@RequestMapping("/api/homework")
public class HomeworkController {

    @Autowired
    HomeworkService homeworkService;

    @PostMapping
    public ResResult<HomeworkDto> submitHomework(@RequestBody HomeworkDto homeworkInfo){
        System.out.println(homeworkInfo.toString());
        return homeworkService.submitHomework(homeworkInfo);
    }
}

