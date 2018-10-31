package com.liyuan.ecommerce.constants;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 17:38 2018/10/31
 * @Modified By:
 */
public interface CompanyState {
    /**
     * 商家状态，0待审核，1审核通过，-1审核未通过
     */
    int WAIT_VERIFY = 0;
    int NORMAL = 1;
    int VERIFY_UNPASS = -1;
}
