package com.slxy.www.service.system.impl;

import com.slxy.www.mapper.system.UserMapper;
import com.slxy.www.model.system.User;
import com.slxy.www.service.system.IUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


}