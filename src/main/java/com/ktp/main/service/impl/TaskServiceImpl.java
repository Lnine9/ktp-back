package com.ktp.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktp.main.dto.TaskDto;
import com.ktp.main.entity.Task;
import com.ktp.main.mapper.TaskMapper;
import com.ktp.main.service.TaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktp.main.util.ResResult;
import com.ktp.main.util.StringUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    public ResResult<TaskDto> createTask(TaskDto taskInfo){
        try {
            String id = StringUtil.getUUID();
            taskInfo.setId(id);
            Task task = modelMapper.map(taskInfo, Task.class);
            if (save(task)){
                return ResResult.ok(taskInfo);
            } else {
                return ResResult.fail("发布失败");
            }
        } catch (RuntimeException e){
            return ResResult.fail("发布失败");
        }
    }

    @Override
    public ResResult<List<TaskDto>> getTasks(String courseId){
        QueryWrapper<Task> qw = new QueryWrapper<>();
        qw.eq("course_id", courseId);
        List<Task> tasks = baseMapper.selectList(qw);
        List<TaskDto> res = modelMapper.map(tasks, new TypeToken<List<TaskDto>>(){}.getType());
        return ResResult.ok(res);
    }

}
