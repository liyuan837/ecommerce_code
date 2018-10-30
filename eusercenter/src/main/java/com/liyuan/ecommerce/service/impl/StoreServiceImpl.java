package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.store.StorePo;
import com.liyuan.ecommerce.domain.condition.store.StoreCondition;
import com.liyuan.ecommerce.mapper.StoreMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.StoreService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.store")
public class StoreServiceImpl extends BaseServiceImpl<StorePo, StoreCondition, StoreMapper> implements StoreService {

    @Override
    @Cacheable(key = "#id")
    public StorePo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}