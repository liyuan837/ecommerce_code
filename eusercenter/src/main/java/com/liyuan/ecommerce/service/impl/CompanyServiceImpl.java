package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.domain.po.company.CompanyPo;
import com.liyuan.ecommerce.domain.condition.company.CompanyCondition;
import com.liyuan.ecommerce.mapper.CompanyMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "com.liyuan.ecommerce.domain.po.company")
public class CompanyServiceImpl extends BaseServiceImpl<CompanyPo, CompanyCondition, CompanyMapper> implements CompanyService {

    @Override
    @Cacheable(key = "#id")
    public CompanyPo queryWithValid(Object id) throws eusercenterException {
        return super.queryWithValid(id);
    }
}