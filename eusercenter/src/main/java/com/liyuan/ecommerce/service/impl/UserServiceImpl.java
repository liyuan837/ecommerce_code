package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.domain.condition.user.UserCondition;
import com.liyuan.ecommerce.mapper.UserMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.UserService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.user")
public class UserServiceImpl extends BaseServiceImpl<UserPo, UserCondition, UserMapper> implements UserService {

    @Override
    @Cacheable(key = "#id")
    public UserPo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}