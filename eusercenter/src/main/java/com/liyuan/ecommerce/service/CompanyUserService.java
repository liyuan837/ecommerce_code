package com.liyuan.ecommerce.service;

import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.condition.companyuser.CompanyUserCondition;
import com.liyuan.ecommerce.form.companyuser.CompanyUserCreateForm;
import com.liyuan.ecommerce.form.companyuser.CompanyUserRegisterForm;
import com.liyuan.ecommerce.vo.companyuser.CompanyUserVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;

public interface CompanyUserService extends BaseService<CompanyUserPo, CompanyUserCondition> {

    /**
     * 商家用户注册
     * @param form
     */
    CompanyUserVo registerUser(CompanyUserRegisterForm form);

    /**
     * 添加商家用户
     * @param form
     * @param loginUserVo
     * @return
     */
    CompanyUserVo addCompanyUser(CompanyUserCreateForm form, LoginUserVo loginUserVo);
}