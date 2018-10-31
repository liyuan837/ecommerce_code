package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.domain.condition.user.UserCondition;
import com.liyuan.ecommerce.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserPo, UserCondition, UserMapper> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserPo queryLoginUser(UserCondition condition) {
        return userMapper.queryLoginUser(condition);
    }
}