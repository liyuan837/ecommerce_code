package com.liyuan.ecommerce.service;

import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.condition.companyuser.CompanyUserCondition;
import com.liyuan.ecommerce.form.companyuser.CompanyUserRegisterForm;
import com.liyuan.ecommerce.vo.companyuser.CompanyUserVo;

public interface CompanyUserService extends BaseService<CompanyUserPo, CompanyUserCondition> {

    /**
     * 商家用户注册
     * @param form
     */
    CompanyUserVo registerUser(CompanyUserRegisterForm form);
}