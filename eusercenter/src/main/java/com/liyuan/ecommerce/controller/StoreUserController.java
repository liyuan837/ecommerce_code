package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.annotation.NotToken;
import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.constants.UserType;
import com.liyuan.ecommerce.domain.condition.user.UserCondition;
import com.liyuan.ecommerce.domain.po.storeuser.StoreUserPo;
import com.liyuan.ecommerce.domain.condition.storeuser.StoreUserCondition;
import com.liyuan.ecommerce.form.companyuser.CompanyUserRegisterForm;
import com.liyuan.ecommerce.form.storeuser.*;
import com.liyuan.ecommerce.service.UserService;
import com.liyuan.ecommerce.util.JwtUtil;
import com.liyuan.ecommerce.vo.companyuser.CompanyUserVo;
import com.liyuan.ecommerce.vo.storeuser.StoreUserVo;
import com.liyuan.ecommerce.service.StoreUserService;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.domain.response.ResponseEntity;
import com.liyuan.ecommerce.domain.response.PageListResponse;
import java.util.List;
import java.util.ArrayList;

import com.liyuan.ecommerce.vo.user.LoginUserVo;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

@RestController
@RequestMapping("/storeuser")
@Api(value = "/storeuser", description = "店铺员工表")
public class StoreUserController extends BaseController {

	@Autowired
	private StoreUserService storeUserService;

    @Autowired
    private UserService userService;

	@ApiOperation(value = "查询店铺员工表",notes = "根据ID查询店铺员工表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<StoreUserVo> query(@ApiParam(value = "主键", required = true)@RequestParam String id) throws eusercenterException {
		StoreUserPo po = storeUserService.queryWithValid(id);
		StoreUserVo vo = CopyUtil.transfer(po, StoreUserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询店铺员工表数量",notes = "查询店铺员工表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid StoreUserQueryForm form) throws eusercenterException {
		StoreUserCondition condition = CopyUtil.transfer(form, StoreUserCondition.class);
		int count = storeUserService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询店铺员工表列表",notes = "查询店铺员工表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<StoreUserVo>> queryList(@RequestBody@Valid StoreUserQueryForm form) throws eusercenterException {
		StoreUserCondition condition = CopyUtil.transfer(form, StoreUserCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<StoreUserPo> poList = storeUserService.queryList(condition);
		List<StoreUserVo> voList = CopyUtil.transfer(poList, StoreUserVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询店铺员工表列表(带分页)",notes = "查询店铺员工表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<StoreUserVo>> queryPageList(@RequestBody@Valid StoreUserQueryForm form) throws eusercenterException {
		StoreUserCondition condition = CopyUtil.transfer(form, StoreUserCondition.class);
		List<StoreUserVo> voList = new ArrayList<>();
		int count = storeUserService.queryCount(condition);
		if (count > 0) {
			List<StoreUserPo> poList = storeUserService.queryList(condition);
			voList = CopyUtil.transfer(poList, StoreUserVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

    @NotToken
    @ApiOperation(value = "注册新用户",notes = "注册新用户",httpMethod = "POST")
    @PostMapping(value = "/register")
    public ResponseEntity<StoreUserVo> register(@RequestBody@Valid StoreUserRegisterForm form) throws eusercenterException {
        //[1]校验user表用户是否存在
        checkRegisterForm(form);

        //[2]保存用户信息
        StoreUserVo vo = storeUserService.registerUser(form);

        return getSuccessResult(vo);
    }

	@ApiOperation(value = "新增店铺员工表",notes = "新增店铺员工表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<StoreUserVo> add(@RequestHeader("Authorization") String authorization, @RequestBody@Valid StoreUserCreateForm form) throws eusercenterException {
        //[1]判断用户类型
        LoginUserVo loginUserVo = JwtUtil.checkUserLogin(authorization);
        if(loginUserVo.getUserType() != UserType.STORE_USER || loginUserVo.getType() != UserType.SUPERMANAGER){
            return getFailResult("您不是店铺超级管理员，无法添加店铺用户");
        }

        //[2]验证表单
        checkCreateForm(form);

        //[3]添加用户
        StoreUserVo vo = storeUserService.addCompanyUser(form,loginUserVo);
        return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改店铺员工表",notes = "修改店铺员工表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid StoreUserUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		StoreUserPo po = CopyUtil.transfer(form, StoreUserPo.class);
		po.setOptTime(optTime);
		storeUserService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除店铺员工表",notes = "删除店铺员工表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid StoreUserDeleteForm form) throws eusercenterException {
		storeUserService.delete(form.getId());
		return getSuccessResult();
	}

    private void checkRegisterForm(StoreUserRegisterForm form){
        //[1]用户名校验
        UserCondition condition = new UserCondition();
        condition.setNickName(form.getNickName());
        condition.setUserType(UserType.STORE_USER);
        condition.setIsDelete(SystemConstants.UNDELETED);

        Integer count = userService.queryCount(condition);
        if(count > 0){
            throw new eusercenterException("昵称已经存在");
        }
        //[2]手机号校验
        condition.setNickName(null);
        condition.setPhone(form.getPhone());
        count = userService.queryCount(condition);
        if(count > 0){
            throw new eusercenterException("手机号已经存在");
        }
    }

    private void checkCreateForm(StoreUserCreateForm form){
        //[1]用户名校验
        UserCondition condition = new UserCondition();
        condition.setNickName(form.getNickName());
        condition.setUserType(UserType.STORE_USER);
        condition.setIsDelete(SystemConstants.UNDELETED);

        Integer count = userService.queryCount(condition);
        if(count > 0){
            throw new eusercenterException("昵称已经存在");
        }
        //[2]手机号校验
        condition.setNickName(null);
        condition.setPhone(form.getPhone());
        count = userService.queryCount(condition);
        if(count > 0){
            throw new eusercenterException("手机号已经存在");
        }
    }
}