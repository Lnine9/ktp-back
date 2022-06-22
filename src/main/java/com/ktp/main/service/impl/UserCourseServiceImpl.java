package com.ktp.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktp.main.dto.CourseDto;
import com.ktp.main.entity.Course;
import com.ktp.main.entity.UserCourse;
import com.ktp.main.mapper.UserCourseMapper;
import com.ktp.main.service.UserCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktp.main.util.ResResult;
import org.apache.naming.factory.ResourceFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
@Service
public class UserCourseServiceImpl extends ServiceImpl<UserCourseMapper, UserCourse> implements UserCourseService {

    private final ModelMapper modelMapper;

    @Autowired
    public UserCourseServiceImpl(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    public ResResult<Object> joinCourse(String courseId, String userId, int type) {
        try {
            save(new UserCourse().setCourseId(courseId).setUserId(userId).setUserRole(type));
            return ResResult.ok();
        } catch (RuntimeException e){
            return ResResult.fail("加入课程失败");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ResResult<Object> quitCourse(String courseId, String userId){
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("course_id", courseId);
            map.put("user_id", userId);
            if (removeByMap(map)) {
                return ResResult.ok();
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResResult.fail("退课失败");
            }
        } catch (RuntimeException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResResult.fail("退课失败");
        }
    }

    @Override
    public ResResult<List<CourseDto>> getCourses(String userId){
        List<Course> courses = baseMapper.getCoursesByUserId(userId);
        List<CourseDto> res = modelMapper.map(courses, new TypeToken<List<CourseDto>>(){}.getType());
        return ResResult.ok(res);
    }
}
