package com.liyuan.ecommerce.service.impl;

import com.liyuan.ecommerce.constants.CompanyState;
import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.domain.condition.company.CompanyCondition;
import com.liyuan.ecommerce.domain.condition.user.UserCondition;
import com.liyuan.ecommerce.domain.po.company.CompanyPo;
import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.form.company.CompanyRegisterForm;
import com.liyuan.ecommerce.mapper.CompanyMapper;
import com.liyuan.ecommerce.mapper.CompanyUserMapper;
import com.liyuan.ecommerce.service.CompanyService;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.util.UserCodeGenerator;
import com.liyuan.ecommerce.vo.company.CompanyVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<CompanyPo, CompanyCondition, CompanyMapper> implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyUserMapper companyUserMapper;

    @Override
    public CompanyVo register(CompanyRegisterForm form, LoginUserVo loginUserVo) {
        Date optTime = new Date();

        //保存企业信息
        CompanyPo po = CopyUtil.transfer(form,CompanyPo.class);

        po.setCompanyCode(UserCodeGenerator.generateUserCode("C"));
        po.setState(CompanyState.WAIT_VERIFY);
        po.setIsDelete(SystemConstants.UNDELETED);
        po.setAddTime(optTime);
        po.setOptTime(optTime);
        po.setAddUserId(loginUserVo.getUserId());
        po.setOptUserId(loginUserVo.getUserId());

        companyMapper.insert(po);

        //设置企业与用户关联
        CompanyUserPo companyUserPo = new CompanyUserPo();
        companyUserPo.setId(loginUserVo.getUserId());
        companyUserPo.setCompanyId(po.getId());
        companyUserMapper.update(companyUserPo);

        CompanyVo vo = CopyUtil.transfer(po,CompanyVo.class);

        return vo;
    }
}