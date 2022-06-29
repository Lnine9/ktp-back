package com.ktp.main.service;

import com.ktp.main.dto.CourseDto;
import com.ktp.main.entity.UserCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ktp.main.util.ResResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
public interface UserCourseService extends IService<UserCourse> {
    ResResult<Object> joinCourse(String courseId, String userId, int type);

    ResResult<Object> quitCourse(String courseId, String userId);

    ResResult<List<CourseDto>> getCourses(String userId);

    ResResult<Object> fieldCourse(String courseId, String userId);

    ResResult<Object> unFieldCourse(String courseId, String userId);

    ResResult<Object> fieldAllCourse(String courseId, String userId);
}
