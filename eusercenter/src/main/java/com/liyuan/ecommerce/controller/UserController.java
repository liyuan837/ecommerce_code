package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.annotation.NotToken;
import com.liyuan.ecommerce.domain.po.user.UserPo;
import com.liyuan.ecommerce.domain.condition.user.UserCondition;
import com.liyuan.ecommerce.form.user.*;
import com.liyuan.ecommerce.vo.user.UserVo;
import com.liyuan.ecommerce.service.UserService;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.domain.response.ResponseEntity;
import com.liyuan.ecommerce.domain.response.PageListResponse;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
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

	@NotToken
    @ApiOperation(value = "平台用户登录",notes = "平台用户登录",httpMethod = "POST")
    @PostMapping(value = "/login")
    public ResponseEntity<Integer> login(@RequestBody@Valid UserLoginForm form) throws eusercenterException {
//        userService.login();
        return getSuccessResult();
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
		userService.delete(form.getUserCode());
		return getSuccessResult();
	}


}