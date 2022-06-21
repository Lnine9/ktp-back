package com.ktp.main.service;

import com.ktp.main.dto.CourseDto;
import com.ktp.main.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ktp.main.util.ResResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
public interface CourseService extends IService<Course> {
    ResResult<Object> createCourse(CourseDto courseInfo, String creator);

    ResResult<Object> dropCourse(String courseId, String teacherId);

    ResResult<CourseDto> getCourseById(String courseId);

    ResResult<CourseDto> updateCourse(CourseDto courseInfo);
}
