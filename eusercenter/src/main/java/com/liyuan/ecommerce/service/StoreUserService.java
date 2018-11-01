package com.liyuan.ecommerce.service;

import com.liyuan.ecommerce.domain.po.storeuser.StoreUserPo;
import com.liyuan.ecommerce.domain.condition.storeuser.StoreUserCondition;
import com.liyuan.ecommerce.form.storeuser.StoreUserCreateForm;
import com.liyuan.ecommerce.form.storeuser.StoreUserRegisterForm;
import com.liyuan.ecommerce.vo.storeuser.StoreUserVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;

public interface StoreUserService extends BaseService<StoreUserPo, StoreUserCondition> {

    /**
     * 店铺用户注册
     * @param form
     * @return
     */
    StoreUserVo registerUser(StoreUserRegisterForm form);

    /**
     * 添加店铺用户
     * @param form
     * @param loginUserVo
     * @return
     */
    StoreUserVo addCompanyUser(StoreUserCreateForm form, LoginUserVo loginUserVo);
}