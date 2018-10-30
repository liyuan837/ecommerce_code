package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.systemuser.SystemUserPo;
import com.liyuan.ecommerce.domain.condition.systemuser.SystemUserCondition;
import com.liyuan.ecommerce.mapper.SystemUserMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.SystemUserService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.systemuser")
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUserPo, SystemUserCondition, SystemUserMapper> implements SystemUserService {

    @Override
    @Cacheable(key = "#id")
    public SystemUserPo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}