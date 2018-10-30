package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.domain.po.systemuser.SystemUserPo;
import com.liyuan.ecommerce.domain.condition.systemuser.SystemUserCondition;
import com.liyuan.ecommerce.form.systemuser.*;
import com.liyuan.ecommerce.vo.systemuser.SystemUserVo;
import com.liyuan.ecommerce.service.SystemUserService;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.domain.response.ResponseEntity;
import com.liyuan.ecommerce.domain.response.PageListResponse;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

@RestController
@RequestMapping("/systemuser")
@Api(value = "/systemuser", description = "平台用户表")
public class SystemUserController extends BaseController {

	@Autowired
	private SystemUserService systemUserService;

	@ApiOperation(value = "查询平台用户表",notes = "根据ID查询平台用户表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<SystemUserVo> query(@ApiParam(value = "主键", required = true)@RequestParam String id) throws eusercenterException {
		SystemUserPo po = systemUserService.queryWithValid(id);
		SystemUserVo vo = CopyUtil.transfer(po, SystemUserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询平台用户表数量",notes = "查询平台用户表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid SystemUserQueryForm form) throws eusercenterException {
		SystemUserCondition condition = CopyUtil.transfer(form, SystemUserCondition.class);
		int count = systemUserService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询平台用户表列表",notes = "查询平台用户表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<SystemUserVo>> queryList(@RequestBody@Valid SystemUserQueryForm form) throws eusercenterException {
		SystemUserCondition condition = CopyUtil.transfer(form, SystemUserCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<SystemUserPo> poList = systemUserService.queryList(condition);
		List<SystemUserVo> voList = CopyUtil.transfer(poList, SystemUserVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询平台用户表列表(带分页)",notes = "查询平台用户表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<SystemUserVo>> queryPageList(@RequestBody@Valid SystemUserQueryForm form) throws eusercenterException {
		SystemUserCondition condition = CopyUtil.transfer(form, SystemUserCondition.class);
		List<SystemUserVo> voList = new ArrayList<>();
		int count = systemUserService.queryCount(condition);
		if (count > 0) {
			List<SystemUserPo> poList = systemUserService.queryList(condition);
			voList = CopyUtil.transfer(poList, SystemUserVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增平台用户表",notes = "新增平台用户表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<SystemUserVo> add(@RequestBody@Valid SystemUserCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		SystemUserPo po = CopyUtil.transfer(form, SystemUserPo.class);
		po.setAddTime(optTime);
		systemUserService.insert(po);
		SystemUserVo vo = CopyUtil.transfer(po, SystemUserVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改平台用户表",notes = "修改平台用户表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid SystemUserUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		SystemUserPo po = CopyUtil.transfer(form, SystemUserPo.class);
		po.setOptTime(optTime);
		systemUserService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除平台用户表",notes = "删除平台用户表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid SystemUserDeleteForm form) throws eusercenterException {
		systemUserService.delete(form.getId());
		return getSuccessResult();
	}

}