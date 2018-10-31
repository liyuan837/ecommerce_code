package com.liyuan.ecommerce.service;

import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.domain.condition.user.UserCondition;
public interface UserService extends BaseService<UserPo, UserCondition> {
    /**
     * 用户登录
     * @param condition
     * @return
     */
    UserPo queryLoginUser(UserCondition condition);
}