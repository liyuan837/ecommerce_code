package com.liyuan.ecommerce.service;

import com.liyuan.ecommerce.domain.po.company.CompanyPo;
import com.liyuan.ecommerce.domain.condition.company.CompanyCondition;
import com.liyuan.ecommerce.form.company.CompanyRegisterForm;
import com.liyuan.ecommerce.vo.company.CompanyVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
public interface CompanyService extends BaseService<CompanyPo, CompanyCondition> {

    /**
     * 注册企业信息
     * @param form
     * @param loginUserVo
     * @return
     */
    CompanyVo register(CompanyRegisterForm form, LoginUserVo loginUserVo);
}