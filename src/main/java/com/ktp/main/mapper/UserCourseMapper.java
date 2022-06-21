package com.ktp.main.mapper;

import com.ktp.main.entity.Course;
import com.ktp.main.entity.UserCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
public interface UserCourseMapper extends BaseMapper<UserCourse> {

    @Select("select * from course, user_course where user_course.user_id = #{userId}")
    List<Course> getCoursesByUserId(String userId);
}
