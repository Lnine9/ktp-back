package com.ktp.main.service.impl;

import com.ktp.main.dto.UserDto;
import com.ktp.main.entity.User;
import com.ktp.main.service.LoginService;
import com.ktp.main.service.UserService;
import com.ktp.main.util.JwtUtil;
import com.ktp.main.util.ResResult;
import com.ktp.main.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final ModelMapper modelMapper;

    @Autowired
    public LoginServiceImpl(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper = modelMapper;
    }

    @Autowired
    UserService userService;


    @Override
    public ResResult<UserDto> login(String key, String password) {
        User user = userService.findUserByEmailOrPhone(key);
        if (user == null){
            return ResResult.fail("用户不存在");
        }
        if (user.getPassword().equals(password)){
            UserDto respUser = modelMapper.map(user, UserDto.class);
            respUser.setPassword(null);
            respUser.setToken(JwtUtil.sign(respUser.getUsername(), respUser.getId()));
            return ResResult.ok(respUser);
        } else {
            return ResResult.fail("密码错误");
        }
    }

    @Override
    public ResResult<Object> logout() {
        return ResResult.ok();
    }

    @Override
    public ResResult<Object> registry(UserDto userInfo) {
        try {
            User user = modelMapper.map(userInfo, User.class);
            user.setId(StringUtil.getUUID());
            if (userService.queryExists(userInfo)){
                return ResResult.fail("已存在");
            }
            if (userService.save(user)){
                return ResResult.ok();
            } else {
                return ResResult.fail("注册失败");
            }
        } catch (RuntimeException e){
            log.debug(Arrays.toString(e.getStackTrace()));
            return ResResult.fail("注册失败");
        }
    }
}
