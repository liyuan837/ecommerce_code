package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.domain.response.ResponseEntity;
import com.liyuan.ecommerce.service.RedisService;
import com.liyuan.ecommerce.util.JwtUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/redis")
@Api(value = "/redis", description = "jwt登录验证测试：此Controller下的所有接口" +
        "（除添加@NotToken注解）都是被JwtInterceptor拦截的，用来进行登录验证测试")
public class RedisController extends BaseController{
    @Autowired
    private RedisService redisService ;

    @ApiOperation(value = "存储redis",notes = "存储redis",httpMethod = "POST")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody JwtUser jwtUser){
        redisService.set(jwtUser.getId().toString(),jwtUser);
        return getSuccessResult("保存成功");
    }

    @ApiOperation(value = "获取redis",notes = "获取redis",httpMethod = "POST")
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public ResponseEntity get(@RequestParam("id")Integer id){
        if(redisService.get(id.toString())!=null){
            JwtUser jwtUser = (JwtUser)redisService.get(id.toString());
            return getSuccessResult(jwtUser);
        }
        return getFailResult("redis中没有该用户信息");
    }
}