package com.ktp.main.service;

import com.ktp.main.dto.HomeworkDto;
import com.ktp.main.entity.Homework;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ktp.main.util.ResResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ${author}
 * @since 2022-06-22
 */
public interface HomeworkService extends IService<Homework> {

    ResResult<HomeworkDto> submitHomework(HomeworkDto homeworkInfo);
}
