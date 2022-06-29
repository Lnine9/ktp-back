package com.ktp.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ktp.main.dto.HomeworkDto;
import com.ktp.main.entity.Homework;
import com.ktp.main.entity.Task;
import com.ktp.main.mapper.HomeworkMapper;
import com.ktp.main.service.HomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktp.main.util.FileUtil;
import com.ktp.main.util.ResResult;
import com.ktp.main.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2022-06-22
 */
@Service
@Slf4j
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements HomeworkService {
    private final ModelMapper modelMapper;

    @Value("${server.port}")
    String port;

    @Autowired
    public HomeworkServiceImpl(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Autowired
    TaskServiceImpl taskService;

    @Override
    public ResResult<HomeworkDto> submitHomework(HomeworkDto homeworkInfo){
        String id = StringUtil.getUUID();
        String filename = homeworkInfo.getFileName();
        homeworkInfo.setId(id);
        homeworkInfo.setFilePath("http://localhost:" +port + "/file/" + filename);
        Homework homework = modelMapper.map(homeworkInfo, Homework.class);
        UpdateWrapper<Task> uw = new UpdateWrapper<>();
        Task task = taskService.getById(homeworkInfo.getTaskId());
        uw
                .eq("id", task.getId())
                .set("submit_num", task.getSubmitNum() + 1)
                .set("unSubmit_num", task.getUnSubmitNum() - 1);
        taskService.update(uw);
        if (save(homework)){
            System.out.println("ok");
            return ResResult.ok(homeworkInfo);
        } else {
            return ResResult.fail("上传失败");
        }
    }
}
