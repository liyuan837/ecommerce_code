package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.consumer.ConsumerPo;
import com.liyuan.ecommerce.domain.condition.consumer.ConsumerCondition;
import com.liyuan.ecommerce.mapper.ConsumerMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.ConsumerService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.consumer")
public class ConsumerServiceImpl extends BaseServiceImpl<ConsumerPo, ConsumerCondition, ConsumerMapper> implements ConsumerService {

    @Override
    @Cacheable(key = "#id")
    public ConsumerPo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}