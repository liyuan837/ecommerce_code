package com.liyuan.ecommerce.util;

import com.github.pagehelper.util.StringUtil;
import com.liyuan.ecommerce.constants.Times;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.UUID;

/**
 * @Author:LiYuan
 * @description:校验码生成工具
 * @Date:Create in 17:58 2018/10/30
 * @Modified By:
 */
public class CaptureUtil {
    /**
     * 产生校验码类型
     */
    public static Integer NUMTYPE = 0;
    public static Integer CHARTYPE = 1;
    public static Integer NUMANDCHARTYPE = 2;

    private static String[] randStrings = new String[]{"0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"};

    /**
     * 图片宽
     */
    private static int width = 95;
    /**
     * 图片高
     */
    private static int height = 25;
    /**
     * 干扰线数量
     */
    private static int lineSize = 40;
    /**
     * 随机产生字符数量
     */
    private static int stringNum = 4;

    private static final Logger logger = LoggerFactory.getLogger(CaptureUtil.class);

    private static Random random = new Random();

    /**
     * 获得字体
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public static void drawImage(int captureType, int expSeconds, RedisService redisService, HttpServletRequest request, HttpServletResponse response) {
        if (captureType > randStrings.length - 1) {
            throw new eusercenterException("校验码类型错误");
        }
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        // 图片大小
        g.fillRect(0, 0, width, height);
        // 字体大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        // 字体颜色
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String captureValue = "";
        for (int i = 1; i <= stringNum; i++) {
            captureValue = drowString(g, captureValue, captureType, i);
        }
        //将生成的校验码以及校验码key保存在redis和cookie中
        String captureKey = setCookie(response, request.getCookies(), Times.ONE_MONTH);
        redisService.set(captureKey, captureValue, Times.ONE_MINUTE * 5L);

        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            logger.error("校验码输出失败 ", e);
        }

    }

    /**
     * 绘制字符串
     */
    private static String drowString(Graphics g, String randomString, int captureType, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(captureType, random.nextInt(randStrings[captureType].length())));
        randomString += rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private static void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    private static String getRandomString(int captureType, int num) {
        return String.valueOf(randStrings[captureType].charAt(num));
    }

    private static String setCookie(HttpServletResponse response, Cookie[] cookies, int expSeconds) {
        Cookie cookie = getCookiesByKey(cookies, "capture");
        String captureKey = "";
        if (null != cookie) {
            captureKey = cookie.getValue();
        }

        if (null == cookie || StringUtils.isEmpty(captureKey)) {
            captureKey = "capture" + UUID.randomUUID().toString();
            cookie = new Cookie("capture", captureKey);
        }

        cookie.setPath("/");
        cookie.setMaxAge(expSeconds);
        response.addCookie(cookie);
        return captureKey;
    }

    private static Cookie getCookiesByKey(Cookie[] cookies, String keyName) {
        if (null == cookies) {
            return null;
        } else {
            for (int i = 0; i < cookies.length; ++i) {
                Cookie c = cookies[i];
                if (c.getName().equalsIgnoreCase(keyName)) {
                    return c;
                }
            }

            return null;
        }
    }

    /**
     * 校验校验码
     * @param verifyCode
     * @param redisService
     * @param request
     * @return
     */
    public static boolean verifyCode(String verifyCode,RedisService redisService,HttpServletRequest request){
        Cookie cookie = getCookiesByKey(request.getCookies(),"capture");
        if(cookie == null){
            return false;
        }
        String captureKey = cookie.getValue();
        if(StringUtil.isEmpty(captureKey)){
            return false;
        }
        String captureValue = (String) redisService.get(captureKey);
        //清除校验码
        redisService.remove(captureKey);
        if(StringUtil.isEmpty(captureValue)){
            return false;
        }
        if(verifyCode.equalsIgnoreCase(captureValue)){
            return true;
        }
        return false;
    }
}
