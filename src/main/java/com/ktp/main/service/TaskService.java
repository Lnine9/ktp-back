package com.ktp.main.service;

import com.ktp.main.dto.TaskDto;
import com.ktp.main.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ktp.main.util.ResResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-06-20
 */
public interface TaskService extends IService<Task> {

    ResResult<TaskDto> createTask(TaskDto taskInfo);

    ResResult<List<TaskDto>> getTasks(String courseId);
}
