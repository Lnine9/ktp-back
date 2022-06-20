package com.ktp.main.service;

import com.ktp.main.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhn
 * @since 2022-06-20
 */
public interface UserService extends IService<User> {
    User findUserByEmailOrAccount(String key);
}
