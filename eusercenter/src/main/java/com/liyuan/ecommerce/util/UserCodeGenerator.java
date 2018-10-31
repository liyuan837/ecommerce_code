package com.liyuan.ecommerce.util;

import java.util.Random;

/**
 * @Author:LiYuan
 * @description:
 * @Date:Create in 18:49 2018/10/31
 * @Modified By:
 */
public class UserCodeGenerator {
    public static String generateUserCode(String prefix){
        Random random = new Random(System.currentTimeMillis());
        return prefix + Math.abs(random.nextInt());
    }
}
