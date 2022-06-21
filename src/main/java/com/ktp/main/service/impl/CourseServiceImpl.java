package com.ktp.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktp.main.dto.CourseDto;
import com.ktp.main.entity.Course;
import com.ktp.main.entity.UserCourse;
import com.ktp.main.mapper.CourseMapper;
import com.ktp.main.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktp.main.service.UserCourseService;
import com.ktp.main.util.ResResult;
import com.ktp.main.util.StringUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    private final ModelMapper modelMapper;

    @Autowired
    UserCourseService userCourseService;

    @Autowired
    public CourseServiceImpl(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    public ResResult<Object> createCourse(CourseDto courseInfo, String creator) {
        try {
            String courseId;
            do {
                courseId = StringUtil.getRandomCourseId();
            } while (getById(courseId) != null);
            Course course = modelMapper.map(courseInfo, Course.class);
            course.setId(courseId);
            save(course);
            userCourseService.joinCourse(courseId, creator, 1);
            Map<String, String> resMap = new HashMap<>();
            resMap.put("courseId", courseId);
            return ResResult.ok(resMap);
        } catch (RuntimeException e){
            return ResResult.fail("创建失败");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ResResult<Object> dropCourse(String courseId, String teacherId){
        try {
            Map<String, String> qm = new HashMap<>();
            qm.put("course_id", courseId);
            qm.put("user_id", teacherId);
            QueryWrapper<UserCourse> qw = new QueryWrapper<>();
            qw.allEq(qm);
            UserCourse rel = userCourseService.getOne(qw);
            if (rel != null && rel.getUserRole() == 1){
                removeById(courseId);
                return ResResult.ok();
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResResult.fail("用户不是该课程教师，没有删除权限");
            }
        } catch (RuntimeException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResResult.fail("删除失败");
        }
    }

    @Override
    public ResResult<CourseDto> getCourseById(String courseId) {
        try {
            Course course = getById(courseId);
            if (course == null){
                return ResResult.fail("课程不存在");
            }
            CourseDto res = modelMapper.map(course, CourseDto.class);
            return ResResult.ok(res);
        } catch (RuntimeException e){
            return ResResult.fail("查询失败");
        }
    }

    @Override
    public ResResult<CourseDto> updateCourse(CourseDto courseInfo) {
        try {
            Course course = modelMapper.map(courseInfo, Course.class);
            if (updateById(course)) {
                return ResResult.ok(courseInfo);
            } else {
                return ResResult.fail("更新失败");
            }
        } catch (RuntimeException e){
            return ResResult.fail("更新失败");
        }
    }
}
