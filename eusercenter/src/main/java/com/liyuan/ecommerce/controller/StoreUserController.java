package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.domain.po.storeuser.StoreUserPo;
import com.liyuan.ecommerce.domain.condition.storeuser.StoreUserCondition;
import com.liyuan.ecommerce.form.storeuser.*;
import com.liyuan.ecommerce.vo.storeuser.StoreUserVo;
import com.liyuan.ecommerce.service.StoreUserService;
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
@RequestMapping("/storeuser")
@Api(value = "/storeuser", description = "店铺员工表")
public class StoreUserController extends BaseController {

	@Autowired
	private StoreUserService storeUserService;

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

	@ApiOperation(value = "新增店铺员工表",notes = "新增店铺员工表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<StoreUserVo> add(@RequestBody@Valid StoreUserCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		StoreUserPo po = CopyUtil.transfer(form, StoreUserPo.class);
		po.setAddTime(optTime);
		storeUserService.insert(po);
		StoreUserVo vo = CopyUtil.transfer(po, StoreUserVo.class);
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

}