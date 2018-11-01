package com.liyuan.ecommerce.constants;

/**
 * @Author:LiYuan
 * @description:平台用户类型
 * @Date:Create in 16:32 2018/10/30
 * @Modified By:
 */
public interface UserType {
    /**
     * 1平台人员，2商家人员，3店铺人员
     */
    int SYSTEM_USER = 1;
    int COMPANY_USER = 2;
    int STORE_USER = 3;

    /**
     * 内部用户身份，
     * 1负责人、超级管理员
     * 2普通员工
     */
    int SUPERMANAGER = 1;
    int COMMONMANAGER = 2;
}
