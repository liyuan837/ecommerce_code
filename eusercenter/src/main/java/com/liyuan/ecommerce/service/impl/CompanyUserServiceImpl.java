package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.condition.companyuser.CompanyUserCondition;
import com.liyuan.ecommerce.mapper.CompanyUserMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.CompanyUserService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.companyuser")
public class CompanyUserServiceImpl extends BaseServiceImpl<CompanyUserPo, CompanyUserCondition, CompanyUserMapper> implements CompanyUserService {

    @Override
    @Cacheable(key = "#id")
    public CompanyUserPo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}