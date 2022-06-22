package com.ktp.main.controller;


import com.ktp.main.dto.TaskDto;
import com.ktp.main.service.TaskService;
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
 * @author ${author}
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResResult<TaskDto> createTask(TaskDto taskInfo){
        return taskService.createTask(taskInfo);
    }

    @GetMapping
    public ResResult<List<TaskDto>> getTasks(String courseId){
        return taskService.getTasks(courseId);
    }

}

