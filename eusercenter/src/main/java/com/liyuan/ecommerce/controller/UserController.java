package com.liyuan.ecommerce.controller;

import com.github.pagehelper.util.StringUtil;
import com.liyuan.ecommerce.annotation.NotToken;
import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.constants.UserType;
import com.liyuan.ecommerce.domain.po.company.CompanyPo;
import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.po.store.StorePo;
import com.liyuan.ecommerce.domain.po.storeuser.StoreUserPo;
import com.liyuan.ecommerce.domain.po.systemuser.SystemUserPo;
import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.domain.condition.user.UserCondition;
import com.liyuan.ecommerce.form.user.*;
import com.liyuan.ecommerce.service.*;
import com.liyuan.ecommerce.util.*;
import com.liyuan.ecommerce.vo.company.CompanySimpleVo;
import com.liyuan.ecommerce.vo.store.StoreSimpleVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
import com.liyuan.ecommerce.vo.user.UserVo;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.domain.response.ResponseEntity;
import com.liyuan.ecommerce.domain.response.PageListResponse;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @Author: LiYuan
 * @Description:平台用户管理
 * @Date: 16:26 2018/10/30
 */
@RestController
@RequestMapping("/user")
@Api(value = "/user", description = "平台用户管理")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
    private SystemUserService systemUserService;

    @Autowired
    private CompanyUserService companyUserService;

    @Autowired
    private StoreUserService storeUserService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private RedisService redisService;

	@NotToken
    @ApiOperation(value = "平台用户登录",notes = "平台用户登录",httpMethod = "POST")
    @PostMapping(value = "/login")
    public ResponseEntity<Integer> login(@RequestBody@Valid UserLoginForm form, HttpServletRequest request) throws eusercenterException {
	    if(form.getUserType() != UserType.SYSTEM_USER && form.getUserType() != UserType.COMPANY_USER && form.getUserType() != UserType.STORE_USER){
	        return getFailResult("错误的用户类型");
        }
	    //[1]获取失败次数
        String requestIp = RequestUtil.getIp(request);
        Integer errorNum = redisService.get("userloginerror" + requestIp,Integer.class);
        errorNum = errorNum == null ? 0 : errorNum;
        //[2]如果登陆失败次数达到系统上限，则进行验证码校验
        if(errorNum >= SystemConstants.LOGINERROR_NUM){
            String verifyCode = form.getVerifyCode();
            if(StringUtil.isEmpty(verifyCode)){
                return getFailResult("请先输入验证码");
            }
            if(!CaptureUtil.verifyCode(verifyCode,redisService,request)){
                return getFailResult("验证码有误，请重新输入");
            }
        }
        //[3]登录校验—平台用户、商家用户、店铺用户
        UserCondition condition = CopyUtil.transfer(form,UserCondition.class);
        condition.setIsDelete(SystemConstants.UNDELETED);
        UserPo loginUserPo = userService.queryLoginUser(condition);
        if(loginUserPo == null){
            //登陆失败
            condition.setPassword(null);
            loginUserPo = userService.queryLoginUser(condition);
            if(loginUserPo == null){
                return getFailResult("该用户不存在");
            }else{
                errorNum += 1;
                redisService.set("userloginerror" + requestIp,errorNum);
                logger.warn("登陆失败:" + form.getNickName() == null ? form.getPhone() : form.getNickName() + "登陆失败");
                return getFailResult("密码输入错误");
            }
        }
        redisService.remove("userloginerror" + requestIp);
        //登陆成功，查询其他信息
        LoginUserVo loginUserVo = CopyUtil.transfer(loginUserPo,LoginUserVo.class);
        switch (form.getUserType()){
            case UserType.SYSTEM_USER:
                SystemUserPo systemUserPo = systemUserService.query(loginUserPo.getUserId());
                if(systemUserPo != null){
                    loginUserVo.setUserCode(systemUserPo.getUserCode());
                    loginUserVo.setHeaderImg(systemUserPo.getHeaderImg());
                    loginUserVo.setRealName(systemUserPo.getRealName());
                    loginUserVo.setType(systemUserPo.getType());

                    CompanyPo platFormPo = companyService.query("P10000");
                    CompanySimpleVo platFormVo = CopyUtil.transfer(platFormPo,CompanySimpleVo.class);
                    loginUserVo.setCompany(platFormVo);
                }else{
                    return getFailResult("平台用户信息不存在");
                }
                break;
            case UserType.COMPANY_USER:
                CompanyUserPo companyUserPo = companyUserService.query(loginUserPo.getUserId());
                if(companyUserPo != null){
                    loginUserVo.setUserCode(companyUserPo.getUserCode());
                    loginUserVo.setHeaderImg(companyUserPo.getHeaderImg());
                    loginUserVo.setRealName(companyUserPo.getRealName());
                    loginUserVo.setType(companyUserPo.getType());

                    CompanyPo companyPo = companyService.query(companyUserPo.getCompanyId());
                    CompanySimpleVo companyVo = CopyUtil.transfer(companyPo,CompanySimpleVo.class);
                    loginUserVo.setCompany(companyVo);
                }else{
                    return getFailResult("商家用户信息不存在");
                }

                break;
            case UserType.STORE_USER:
                StoreUserPo storeUserPo = storeUserService.query(loginUserPo.getUserId());
                if(storeUserPo != null){
                    loginUserVo.setUserCode(storeUserPo.getUserCode());
                    loginUserVo.setHeaderImg(storeUserPo.getHeaderImg());
                    loginUserVo.setRealName(storeUserPo.getRealName());
                    loginUserVo.setType(storeUserPo.getType());

                    StorePo storePo = storeService.query(storeUserPo.getStoreId());
                    StoreSimpleVo storeVo = CopyUtil.transfer(storePo,StoreSimpleVo.class);
                    loginUserVo.setStore(storeVo);
                }else{
                    return getFailResult("店铺用户信息不存在");
                }
                break;
            default:
                return getFailResult("错误的用户类型");
        }
        //[4]生成token
        String token = JwtUtil.generateUserToken(loginUserVo);
        loginUserVo.setToken(token);

        //[5]更新登录信息
        UserPo userPo = new UserPo();
        userPo.setUserId(loginUserPo.getUserId());
        userPo.setLastLoginIp(RequestUtil.getIp(request));
        userPo.setLastLoginTime(new Date());
        userService.update(userPo);

        return getSuccessResult(loginUserVo);
    }


    @ApiOperation(value = "查询平台用户表",notes = "根据ID查询平台用户表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<UserVo> query(@ApiParam(value = "用户编号", required = true)@RequestParam String userCode) throws eusercenterException {
		UserPo po = userService.queryWithValid(userCode);
		UserVo vo = CopyUtil.transfer(po, UserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询平台用户表数量",notes = "查询平台用户表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid UserQueryForm form) throws eusercenterException {
		UserCondition condition = CopyUtil.transfer(form, UserCondition.class);
		int count = userService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询平台用户表列表",notes = "查询平台用户表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<UserVo>> queryList(@RequestBody@Valid UserQueryForm form) throws eusercenterException {
		UserCondition condition = CopyUtil.transfer(form, UserCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<UserPo> poList = userService.queryList(condition);
		List<UserVo> voList = CopyUtil.transfer(poList, UserVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询平台用户表列表(带分页)",notes = "查询平台用户表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<UserVo>> queryPageList(@RequestBody@Valid UserQueryForm form) throws eusercenterException {
		UserCondition condition = CopyUtil.transfer(form, UserCondition.class);
		List<UserVo> voList = new ArrayList<>();
		int count = userService.queryCount(condition);
		if (count > 0) {
			List<UserPo> poList = userService.queryList(condition);
			voList = CopyUtil.transfer(poList, UserVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增平台用户表",notes = "新增平台用户表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<UserVo> add(@RequestBody@Valid UserCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		UserPo po = CopyUtil.transfer(form, UserPo.class);
		userService.insert(po);
		UserVo vo = CopyUtil.transfer(po, UserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改平台用户表",notes = "修改平台用户表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid UserUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		UserPo po = CopyUtil.transfer(form, UserPo.class);
		userService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除平台用户表",notes = "删除平台用户表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid UserDeleteForm form) throws eusercenterException {
		userService.delete(form.getUserId());
		return getSuccessResult();
	}
}