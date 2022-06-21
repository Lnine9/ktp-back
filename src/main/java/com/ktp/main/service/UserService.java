package com.ktp.main.service;

import com.ktp.main.dto.UserDto;
import com.ktp.main.entity.User;
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
public interface UserService extends IService<User> {
    User findUserByEmailOrPhone(String key);

    boolean queryExists(UserDto userInfo);

    ResResult<UserDto> updateUser(UserDto userInfo);
}
