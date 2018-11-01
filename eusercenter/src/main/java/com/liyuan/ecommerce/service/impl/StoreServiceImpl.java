package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.constants.CompanyState;
import com.liyuan.ecommerce.constants.StoreState;
import com.liyuan.ecommerce.constants.StoreType;
import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.domain.po.company.CompanyPo;
import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.po.store.StorePo;
import com.liyuan.ecommerce.domain.condition.store.StoreCondition;
import com.liyuan.ecommerce.domain.po.storeuser.StoreUserPo;
import com.liyuan.ecommerce.form.store.StoreRegisterForm;
import com.liyuan.ecommerce.mapper.CompanyMapper;
import com.liyuan.ecommerce.mapper.CompanyUserMapper;
import com.liyuan.ecommerce.mapper.StoreMapper;
import com.liyuan.ecommerce.mapper.StoreUserMapper;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.util.UserCodeGenerator;
import com.liyuan.ecommerce.vo.company.CompanyVo;
import com.liyuan.ecommerce.vo.store.StoreVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StoreServiceImpl extends BaseServiceImpl<StorePo, StoreCondition, StoreMapper> implements StoreService {
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreUserMapper storeUserMapper;

    @Override
    public StoreVo register(StoreRegisterForm form, LoginUserVo loginUserVo) {
        Date optTime = new Date();

        //保存店铺信息
        StorePo po = CopyUtil.transfer(form,StorePo.class);

        po.setStoreCode(UserCodeGenerator.generateUserCode("S"));
        po.setType(StoreType.PLATFORM_STORE);
        po.setState(StoreState.WAIT_VERIFY);
        po.setIsDelete(SystemConstants.UNDELETED);
        po.setAddTime(optTime);
        po.setOptTime(optTime);
        po.setAddUserId(loginUserVo.getUserId());
        po.setOptUserId(loginUserVo.getUserId());

        storeMapper.insert(po);

        //设置店铺与用户关联
        StoreUserPo storeUserPo = new StoreUserPo();
        storeUserPo.setId(loginUserVo.getUserId());
        storeUserPo.setStoreId(po.getId());
        storeUserMapper.update(storeUserPo);

        StoreVo vo = CopyUtil.transfer(po,StoreVo.class);

        return vo;
    }
}