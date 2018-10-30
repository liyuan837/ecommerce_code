package com.liyuan.ecommerce.util;

import com.liyuan.ecommerce.constants.Nums;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: LiYuan
 * @Description:请求路径、ip获取，方便cookie存储与检索
 * @Date: 17:20 2018/10/30
 */
public final class RequestUtil {
    private static final Logger LOG = LoggerFactory.getLogger(RequestUtil.class);

    private RequestUtil() {

    }

    /**
     * 获取客户端ip
     */
    public static String getIp(HttpServletRequest request) {
        return IPUtil.getIp(request);
    }

    /**
     * 获取请求路径
     *
     * @param request
     * @return /url/1
     */
    public static String getRestURL(HttpServletRequest request) {
        return request.getRequestURI();
    }

    /**
     * 获取请求路径表达式
     *
     * @param request
     * @return /url/{id}
     */
    public static String getRestPatternURL(HttpServletRequest request) {
        return (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
    }

    /**
     * Convenience method to set a cookie
     *
     * @param response the current response
     * @param name     the name of the cookie
     * @param value    the value of the cookie
     * @param path     the path to set it on
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Setting cookie '" + name + "' on path '" + path + "'");
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        // 30 days
        cookie.setMaxAge(Nums.NUM60 * Nums.NUM60 * Nums.NUM24 * Nums.NUM30);

        response.addCookie(cookie);
    }

    /**
     * Convenience method to set a cookie
     *
     * @param response the current response
     * @param name     the name of the cookie
     * @param value    the value of the cookie
     * @param path     the path to set it on
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Setting cookie '" + name + "' on path '" + path + "'");
        }

        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(false);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    /**
     * Convenience method to get a cookie by name
     *
     * @param request the current request
     * @param name    the name of the cookie to find
     * @return the cookie (if found), null if not found
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        Cookie returnCookie = null;

        if (cookies == null) {
            return returnCookie;
        }

        for (final Cookie thisCookie : cookies) {
            if (thisCookie.getName().equals(name) && !"".equals(thisCookie.getValue())) {
                returnCookie = thisCookie;
                break;
            }
        }

        return returnCookie;
    }

    /**
     * Convenience method for deleting a cookie by name
     *
     * @param response the current web response
     * @param cookie   the cookie to delete
     * @param path     the path on which the cookie was set (i.e. /appfuse)
     */
    public static void deleteCookie(HttpServletResponse response, Cookie cookie, String path) {
        if (cookie != null) {
            // Delete the cookie by setting its maximum age to zero
            cookie.setMaxAge(0);
            cookie.setPath(path);
            response.addCookie(cookie);
        }
    }

    /**
     *
     * 验证是否是ajax请求
     * 常用的jquery之类的框架，ajax使用都会带上header [X-Requested-With=XMLHttpRequest]
     *
     * @author 彭文哲[OF789]
     * @date 2013-10-15 下午3:08:04
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return true;
        }
        return false;
    }

    /**
     * Convenience method to get the application's URL based on request
     * variables.
     *
     * @param request the current request
     * @return URL to application
     */
    public static String getAppURL(HttpServletRequest request) {
        StringBuffer url = new StringBuffer();
        int port = request.getServerPort();
        if (port < 0) {
            port = 80; // Work around java.net.URL bug
        }
        String scheme = request.getScheme();
        url.append(scheme);
        url.append("://");
        url.append(request.getServerName());
        if ((scheme.equals("http") && (port != 80)) || (scheme.equals("https") && (port != 443))) {
            url.append(':');
            url.append(port);
        }
        url.append(request.getContextPath());
        return url.toString();
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {

        String ip = request.getHeader("X-Real-IP");

        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip.split(",")[0];
    }

}
