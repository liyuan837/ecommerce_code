package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.consumerextend.ConsumerExtendPo;
import com.liyuan.ecommerce.domain.condition.consumerextend.ConsumerExtendCondition;
import com.liyuan.ecommerce.mapper.ConsumerExtendMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.ConsumerExtendService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.consumerextend")
public class ConsumerExtendServiceImpl extends BaseServiceImpl<ConsumerExtendPo, ConsumerExtendCondition, ConsumerExtendMapper> implements ConsumerExtendService {

    @Override
    @Cacheable(key = "#id")
    public ConsumerExtendPo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}