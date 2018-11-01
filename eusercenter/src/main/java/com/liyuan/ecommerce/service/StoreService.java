package com.liyuan.ecommerce.service;

import com.liyuan.ecommerce.domain.po.store.StorePo;
import com.liyuan.ecommerce.domain.condition.store.StoreCondition;
import com.liyuan.ecommerce.form.store.StoreRegisterForm;
import com.liyuan.ecommerce.vo.store.StoreVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
public interface StoreService extends BaseService<StorePo, StoreCondition> {

    /**
     * 注册店铺
     * @param form
     * @param loginUserVo
     * @return
     */
    StoreVo register(StoreRegisterForm form, LoginUserVo loginUserVo);
}