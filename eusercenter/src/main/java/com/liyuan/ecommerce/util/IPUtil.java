package com.liyuan.ecommerce.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;

/**
 * @Author: LiYuan
 * @Description:ip地址工具类
 * @Date: 17:32 2018/10/30
 */
public final class IPUtil {
    private static Logger logger = LoggerFactory.getLogger(IPUtil.class);
    /**
     * IP长度
     */
    private static final int IPLENGTH = 4;

    private IPUtil() {

    }

    /**
     * 获取客户端ip
     */
    public static String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");
        String xip = request.getHeader("x-forwarded-for");

        if (!checkIP(ip)) {
            ip = request.getHeader("REMOTE-HOST");
        }

        if (StringUtils.isNotBlank(ip) && !ip.equals(xip)) {
            logger.info("getipne   X-Real-IP  " + ip + "    ,x-forwarded-for " + xip);
        }

        if (!checkIP(ip) || !ip.equals(xip)) {
            String info = request.getHeader("x-forwarded-for");
            if (StringUtils.isNotBlank(info)) {
                String[] ips = info.trim().split(",");
                for (String s : ips) {
                    //取第一个非unknown的ip地址
                    if (!"unknown".equalsIgnoreCase(s)) {
                        ip = s;
                        break;
                    }
                }
            }
        }
        if (!checkIP(ip)) {
            //只在 Apache（Weblogic Plug-In Enable）+WebLogic 搭配下出现
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            //只在 Apache（Weblogic Plug-In Enable）+WebLogic 搭配下出现
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (!checkIP(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!checkIP(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多级反向代理
        if (null != ip && !"".equals(ip.trim())) {
            StringTokenizer st = new StringTokenizer(ip, ",");
            if (st.countTokens() > 1) {
                logger.info("getip StringTokenizer  " + ip);
                return st.nextToken();
            }
        }

        return ip;
    }

    /**
     * 验证ip地址格式是否正确
     * @param ip
     * @return
     */
    private static boolean checkIP(String ip) {
        if (StringUtils.isNotBlank(ip) && ip.split("\\.").length == IPLENGTH) {
            return true;
        }
        return false;
    }

}