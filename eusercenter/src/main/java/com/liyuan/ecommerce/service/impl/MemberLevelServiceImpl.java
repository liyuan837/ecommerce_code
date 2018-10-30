package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.memberlevel.MemberLevelPo;
import com.liyuan.ecommerce.domain.condition.memberlevel.MemberLevelCondition;
import com.liyuan.ecommerce.mapper.MemberLevelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.MemberLevelService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.memberlevel")
public class MemberLevelServiceImpl extends BaseServiceImpl<MemberLevelPo, MemberLevelCondition, MemberLevelMapper> implements MemberLevelService {

    @Override
    @Cacheable(key = "#id")
    public MemberLevelPo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}