package com.liyuan.ecommerce.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.constants.UserState;
import com.liyuan.ecommerce.constants.UserType;
import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.po.systemuser.SystemUserPo;
import com.liyuan.ecommerce.domain.condition.systemuser.SystemUserCondition;
import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.form.systemuser.SystemUserCreateForm;
import com.liyuan.ecommerce.mapper.SystemUserMapper;
import com.liyuan.ecommerce.mapper.UserMapper;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.util.MD5Util;
import com.liyuan.ecommerce.util.UserCodeGenerator;
import com.liyuan.ecommerce.vo.companyuser.CompanyUserVo;
import com.liyuan.ecommerce.vo.systemuser.SystemUserVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.SystemUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUserPo, SystemUserCondition, SystemUserMapper> implements SystemUserService {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public SystemUserVo addSystemUser(SystemUserCreateForm form, LoginUserVo loginUserVo) {
        Date optTime = new Date();

        //保存systemUser
        SystemUserPo po = CopyUtil.transfer(form, SystemUserPo.class);
        po.setUserCode(UserCodeGenerator.generateUserCode("PU"));
        po.setType(UserType.COMMONMANAGER);
        po.setIsDelete(SystemConstants.UNDELETED);
        po.setState(UserState.NORMAL);
        po.setAddTime(optTime);
        po.setOptTime(optTime);
        po.setAddUserId(loginUserVo.getUserId());
        po.setOptUserId(loginUserVo.getUserId());
        systemUserMapper.insert(po);

        //保存user
        UserPo userPo = new UserPo();
        userPo.setUserId(po.getId());
        userPo.setUserType(UserType.SYSTEM_USER);
        userPo.setNickName(form.getNickName());
        userPo.setPhone(form.getPhone());
        if(StringUtil.isEmpty(form.getPassword())){
            userPo.setPassword(MD5Util.string2MD5("123456"));
        }else{
            userPo.setPassword(MD5Util.string2MD5(form.getPassword()));
        }

        userPo.setIsDelete(SystemConstants.UNDELETED);
        userPo.setState(UserState.NORMAL);
        userMapper.insert(userPo);

        SystemUserVo vo = CopyUtil.transfer(po,SystemUserVo.class);

        return vo;
    }
}