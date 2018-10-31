package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.annotation.NotToken;
import com.liyuan.ecommerce.constants.Nums;
import com.liyuan.ecommerce.constants.Times;
import com.liyuan.ecommerce.service.RedisService;
import com.liyuan.ecommerce.util.CaptureUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:LiYuan
 * @description:一些通用接口
 * @Date:Create in 17:46 2018/10/30
 * @Modified By:
 */
@RestController
@RequestMapping("/common")
@Api(value = "/common", description = "通用接口")
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    RedisService redisService;

    @NotToken
    @ApiOperation(value = "获取验证码",notes = "获取验证码",httpMethod = "GET")
    @RequestMapping(value = {"/capture.jpg"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String getCapture(HttpServletRequest request, HttpServletResponse response) {
        CaptureUtil.drawImage(CaptureUtil.NUMTYPE, Times.ONE_MINUTE * 10, redisService,request,response);

        return null;
    }

    @NotToken
    @ApiOperation(value = "校验验证码",notes = "校验验证码",httpMethod = "GET")
    @RequestMapping(value = {"/verifyCode"}, method = {RequestMethod.GET,RequestMethod.POST})
    public String verifyCode(HttpServletRequest request, @RequestParam("verifyCode") String verifyCode) {
        if(CaptureUtil.verifyCode(verifyCode,redisService,request)){
            return "success";
        }

        return "failure";
    }
}
