package com.ktp.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ktp.main.dto.CourseDto;
import com.ktp.main.entity.Course;
import com.ktp.main.entity.Task;
import com.ktp.main.entity.User;
import com.ktp.main.entity.UserCourse;
import com.ktp.main.mapper.UserCourseMapper;
import com.ktp.main.service.TaskService;
import com.ktp.main.service.UserCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktp.main.service.UserService;
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
    UserService userService;

    @Autowired
    TaskService taskService;

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
        for (CourseDto c : res) {
            User teacher = baseMapper.getCourseTeacher(c.getId());
            if (teacher != null){
                c.setTeacherName(teacher.getUsername());
            }
            QueryWrapper<Task> qw = new QueryWrapper<>();
            qw.eq("course_id", c.getId());
            int count = taskService.count(qw);
            c.setTaskCount(count);
        }
        return ResResult.ok(res);
    }

    @Override
    public ResResult<Object> fieldCourse(String courseId, String userId) {
        UpdateWrapper<UserCourse> uw = new UpdateWrapper<>();
        uw
                .eq("course_id", courseId)
                .eq("user_id", userId)
                .set("isfield", 1);
        if(update(uw)){
            return ResResult.ok();
        } else {
            return ResResult.fail("归档失败");
        }
    }

    @Override
    public ResResult<Object> unFieldCourse(String courseId, String userId) {
        UpdateWrapper<UserCourse> uw = new UpdateWrapper<>();
        uw
                .eq("course_id", courseId)
                .eq("user_id", userId)
                .set("isfield", 0);
        if(update(uw)){
            return ResResult.ok();
        } else {
            return ResResult.fail("取消归档失败");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ResResult<Object> fieldAllCourse(String courseId, String userId) {
        User user = userService.getById(userId);
        if (user.getRole() != 1){
            return ResResult.fail("用户不是该课程教师，无权限归档全部");
        }
        try {
            UpdateWrapper<UserCourse> uw = new UpdateWrapper<>();
            uw
                    .eq("course_id", courseId)
                    .set("isfield", 1);
            if(update(uw)){
                return ResResult.ok();
            } else {
                return ResResult.fail("归档全部失败");
            }
        } catch (RuntimeException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResResult.fail("归档全部失败");
        }

    }
}
