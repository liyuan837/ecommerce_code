package com.liyuan.ecommerce.mapper;

import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.domain.condition.user.UserCondition;
public interface UserMapper extends BaseMapper<UserPo, UserCondition> {
    /**
     * 用户登录
     * @param condition
     * @return
     */
    UserPo queryLoginUser(UserCondition condition);
}