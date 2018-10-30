package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.storeuser.StoreUserPo;
import com.liyuan.ecommerce.domain.condition.storeuser.StoreUserCondition;
import com.liyuan.ecommerce.mapper.StoreUserMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.StoreUserService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.storeuser")
public class StoreUserServiceImpl extends BaseServiceImpl<StoreUserPo, StoreUserCondition, StoreUserMapper> implements StoreUserService {

    @Override
    @Cacheable(key = "#id")
    public StoreUserPo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}