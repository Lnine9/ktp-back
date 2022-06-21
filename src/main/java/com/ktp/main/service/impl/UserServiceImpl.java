package com.ktp.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktp.main.dto.UserDto;
import com.ktp.main.entity.User;
import com.ktp.main.mapper.UserMapper;
import com.ktp.main.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ktp.main.util.ResResult;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    public User findUserByEmailOrPhone(String key) {
        if (key != null){
            QueryWrapper<User> qw = new QueryWrapper<>();
            qw
                    .eq("email",key)
                    .or()
                    .eq("phone", key);
            return getOne(qw);
        }
        return null;
    }

    @Override
    public boolean queryExists(UserDto userInfo) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (userInfo.getEmail() != null){
            qw.eq("email", userInfo.getEmail());
        } else if (userInfo.getPhone() != null){
            qw.eq("phone", userInfo.getPhone());
        } else {
            return true;
        }
        return getOne(qw) != null;
    }

    @Override
    public ResResult<UserDto> updateUser(UserDto userInfo){
        try {
            User user = modelMapper.map(userInfo, User.class);
            User old = getById(user.getId());
            user.setPassword(old.getPassword());
            if (updateById(user)) {
                return ResResult.ok(userInfo);
            } else {
                return ResResult.fail("更新失败");
            }
        } catch (RuntimeException e){
            return ResResult.fail("更新失败");
        }

    }
}
