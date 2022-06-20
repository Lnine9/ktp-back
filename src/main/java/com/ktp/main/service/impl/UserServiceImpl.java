package com.ktp.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktp.main.entity.User;
import com.ktp.main.mapper.UserMapper;
import com.ktp.main.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public User findUserByEmailOrAccount(String key) {
        if (key != null){
            QueryWrapper<User> qw = new QueryWrapper<>();
            qw
                    .eq("email",key)
                    .or()
                    .eq("account", key);
            return getOne(qw);
        }
        return null;
    }
}
