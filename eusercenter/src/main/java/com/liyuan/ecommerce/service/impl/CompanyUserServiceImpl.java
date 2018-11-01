package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.constants.UserState;
import com.liyuan.ecommerce.constants.UserType;
import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.condition.companyuser.CompanyUserCondition;
import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.form.companyuser.CompanyUserCreateForm;
import com.liyuan.ecommerce.form.companyuser.CompanyUserRegisterForm;
import com.liyuan.ecommerce.mapper.CompanyUserMapper;
import com.liyuan.ecommerce.mapper.UserMapper;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.util.MD5Util;
import com.liyuan.ecommerce.util.UserCodeGenerator;
import com.liyuan.ecommerce.vo.companyuser.CompanyUserVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.CompanyUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class CompanyUserServiceImpl extends BaseServiceImpl<CompanyUserPo, CompanyUserCondition, CompanyUserMapper> implements CompanyUserService {

    @Autowired
    private CompanyUserMapper companyUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CompanyUserVo registerUser(CompanyUserRegisterForm form) {
        Date optTime = new Date();

        //保存companyUser
        CompanyUserPo po = CopyUtil.transfer(form, CompanyUserPo.class);
        po.setUserCode(UserCodeGenerator.generateUserCode("CU"));
        po.setType(UserType.SUPERMANAGER);
        po.setIsDelete(SystemConstants.UNDELETED);
        po.setState(UserState.NORMAL);
        po.setAddTime(optTime);
        po.setOptTime(optTime);
        companyUserMapper.insert(po);

        //保存user
        UserPo userPo = new UserPo();
        userPo.setUserId(po.getId());
        userPo.setUserType(UserType.COMPANY_USER);
        userPo.setNickName(form.getNickName());
        userPo.setPhone(form.getPhone());
        userPo.setPassword(MD5Util.string2MD5(form.getPassword()));
        userPo.setIsDelete(SystemConstants.UNDELETED);
        userPo.setState(UserState.NORMAL);
        userMapper.insert(userPo);

        CompanyUserVo vo = CopyUtil.transfer(po,CompanyUserVo.class);

        return vo;
    }

    @Override
    public CompanyUserVo addCompanyUser(CompanyUserCreateForm form, LoginUserVo loginUserVo) {
        Date optTime = new Date();

        //查询添加者所属企业
        CompanyUserPo addUserPo = companyUserMapper.select(loginUserVo.getUserId());

        //保存companyUser
        CompanyUserPo po = CopyUtil.transfer(form, CompanyUserPo.class);
        po.setUserCode(UserCodeGenerator.generateUserCode("CU"));
        po.setCompanyId(addUserPo.getCompanyId());
        po.setType(UserType.COMMONMANAGER);
        po.setIsDelete(SystemConstants.UNDELETED);
        po.setState(UserState.NORMAL);
        po.setAddUserId(loginUserVo.getUserId());
        po.setOptUserId(loginUserVo.getUserId());
        po.setAddTime(optTime);
        po.setOptTime(optTime);
        companyUserMapper.insert(po);

        //保存user
        UserPo userPo = new UserPo();
        userPo.setUserId(po.getId());
        userPo.setUserType(UserType.COMPANY_USER);
        userPo.setNickName(form.getNickName());
        userPo.setPhone(form.getPhone());
        userPo.setPassword(MD5Util.string2MD5(form.getPassword()));
        userPo.setIsDelete(SystemConstants.UNDELETED);
        userPo.setState(UserState.NORMAL);
        userMapper.insert(userPo);

        CompanyUserVo vo = CopyUtil.transfer(po,CompanyUserVo.class);

        return vo;
    }
}