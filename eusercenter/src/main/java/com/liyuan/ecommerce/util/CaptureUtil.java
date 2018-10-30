package com.liyuan.ecommerce.util;

import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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

    private static String[] randStrings= new String[]{"0123456789","ABCDEFGHIJKLMNOPQRSTUVWXYZ","0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"};


    private static int width = 95;// 图片宽
    private static int height = 25;// 图片高
    private static int lineSize = 40;// 干扰线数量
    private static int stringNum = 4;// 随机产生字符数量

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
        if (fc > 255){
            fc = 255;
        }
        if (bc > 255){
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
        if(captureType > randStrings.length-1){
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
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString,captureType, i);
        }
        //将生成的随机字符串保存到redis中 TODO

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
    private static String drowString(Graphics g, String randomString,int captureType, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(captureType,random.nextInt(randStrings[captureType].length())));
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
    public  static String getRandomString(int captureType, int num) {
        return String.valueOf(randStrings[captureType].charAt(num));
    }
}
