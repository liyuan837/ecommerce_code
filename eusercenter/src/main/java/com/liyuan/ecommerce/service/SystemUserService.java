package com.liyuan.ecommerce.service;

import com.liyuan.ecommerce.domain.po.systemuser.SystemUserPo;
import com.liyuan.ecommerce.domain.condition.systemuser.SystemUserCondition;
import com.liyuan.ecommerce.form.systemuser.SystemUserCreateForm;
import com.liyuan.ecommerce.vo.systemuser.SystemUserVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
public interface SystemUserService extends BaseService<SystemUserPo, SystemUserCondition> {

    /**
     * 添加系统用户
     * @param form
     * @param loginUserVo
     * @return
     */
    SystemUserVo addSystemUser(SystemUserCreateForm form, LoginUserVo loginUserVo);
}