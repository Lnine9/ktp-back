package com.ktp.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ktp.main.dto.UserDto;
import com.ktp.main.entity.User;
import com.ktp.main.service.LoginService;
import com.ktp.main.service.UserService;
import com.ktp.main.util.ResResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final ModelMapper modelMapper;

    @Autowired
    UserService userService;

    @Autowired
    public LoginServiceImpl(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Override
    public ResResult<UserDto> login(String key, String password) {
        User user = userService.findUserByEmailOrAccount(key);
        if (user == null){
            return ResResult.fail("用户不存在");
        }
        if (DigestUtils.md5Hex(user.getPassword()).equals(password)){
            UserDto respUser = modelMapper.map(user, UserDto.class);
            respUser.setPassword(null);
            return ResResult.ok(respUser);
        } else {
            return ResResult.fail("密码错误");
        }
    }

    @Override
    public ResResult<Object> logout(String token) {
        return null;
    }
}
