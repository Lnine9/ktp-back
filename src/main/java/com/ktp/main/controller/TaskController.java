package com.ktp.main.controller;


import com.ktp.main.dto.TaskDto;
import com.ktp.main.service.TaskService;
import com.ktp.main.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2022-06-20
 */
@Controller
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    public ResResult<TaskDto> createTask(TaskDto taskInfo){
        return taskService.createTask(taskInfo);
    }

}

