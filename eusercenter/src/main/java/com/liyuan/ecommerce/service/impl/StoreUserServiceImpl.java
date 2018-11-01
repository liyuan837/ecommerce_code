package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.constants.UserState;
import com.liyuan.ecommerce.constants.UserType;
import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.po.storeuser.StoreUserPo;
import com.liyuan.ecommerce.domain.condition.storeuser.StoreUserCondition;
import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.form.storeuser.StoreUserCreateForm;
import com.liyuan.ecommerce.form.storeuser.StoreUserRegisterForm;
import com.liyuan.ecommerce.mapper.StoreUserMapper;
import com.liyuan.ecommerce.mapper.UserMapper;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.util.MD5Util;
import com.liyuan.ecommerce.util.UserCodeGenerator;
import com.liyuan.ecommerce.vo.companyuser.CompanyUserVo;
import com.liyuan.ecommerce.vo.storeuser.StoreUserVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.StoreUserService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StoreUserServiceImpl extends BaseServiceImpl<StoreUserPo, StoreUserCondition, StoreUserMapper> implements StoreUserService {

    @Autowired
    private StoreUserMapper storeUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public StoreUserVo registerUser(StoreUserRegisterForm form) {
        Date optTime = new Date();

        //保存storeUser
        StoreUserPo po = CopyUtil.transfer(form, StoreUserPo.class);
        po.setUserCode(UserCodeGenerator.generateUserCode("SU"));
        po.setType(UserType.SUPERMANAGER);
        po.setIsDelete(SystemConstants.UNDELETED);
        po.setState(UserState.NORMAL);
        po.setAddTime(optTime);
        po.setOptTime(optTime);
        storeUserMapper.insert(po);

        //保存user
        UserPo userPo = new UserPo();
        userPo.setUserId(po.getId());
        userPo.setUserType(UserType.STORE_USER);
        userPo.setNickName(form.getNickName());
        userPo.setPhone(form.getPhone());
        userPo.setPassword(MD5Util.string2MD5(form.getPassword()));
        userPo.setIsDelete(SystemConstants.UNDELETED);
        userPo.setState(UserState.NORMAL);
        userMapper.insert(userPo);

        StoreUserVo vo = CopyUtil.transfer(po,StoreUserVo.class);

        return vo;
    }

    @Override
    public StoreUserVo addCompanyUser(StoreUserCreateForm form, LoginUserVo loginUserVo) {
        Date optTime = new Date();

        //查询添加者所属店铺
        StoreUserPo addUserPo = storeUserMapper.select(loginUserVo.getUserId());

        //保存storeUser
        StoreUserPo po = CopyUtil.transfer(form, StoreUserPo.class);
        po.setUserCode(UserCodeGenerator.generateUserCode("SU"));
        po.setStoreId(addUserPo.getStoreId());
        po.setType(UserType.SUPERMANAGER);
        po.setIsDelete(SystemConstants.UNDELETED);
        po.setState(UserState.NORMAL);
        po.setAddUserId(loginUserVo.getUserId());
        po.setOptUserId(loginUserVo.getUserId());
        po.setAddTime(optTime);
        po.setOptTime(optTime);
        storeUserMapper.insert(po);

        //保存user
        UserPo userPo = new UserPo();
        userPo.setUserId(po.getId());
        userPo.setUserType(UserType.STORE_USER);
        userPo.setNickName(form.getNickName());
        userPo.setPhone(form.getPhone());
        userPo.setPassword(MD5Util.string2MD5(form.getPassword()));
        userPo.setIsDelete(SystemConstants.UNDELETED);
        userPo.setState(UserState.NORMAL);
        userMapper.insert(userPo);

        StoreUserVo vo = CopyUtil.transfer(po,StoreUserVo.class);

        return vo;
    }
}