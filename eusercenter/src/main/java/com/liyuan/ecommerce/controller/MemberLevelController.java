package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.domain.po.memberlevel.MemberLevelPo;
import com.liyuan.ecommerce.domain.condition.memberlevel.MemberLevelCondition;
import com.liyuan.ecommerce.form.memberlevel.*;
import com.liyuan.ecommerce.vo.memberlevel.MemberLevelVo;
import com.liyuan.ecommerce.service.MemberLevelService;
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
@RequestMapping("/memberlevel")
@Api(value = "/memberlevel", description = "会员等级表")
public class MemberLevelController extends BaseController {

	@Autowired
	private MemberLevelService memberLevelService;

	@ApiOperation(value = "查询会员等级表",notes = "根据ID查询会员等级表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<MemberLevelVo> query(@ApiParam(value = "主键", required = true)@RequestParam Integer id) throws eusercenterException {
		MemberLevelPo po = memberLevelService.queryWithValid(id);
		MemberLevelVo vo = CopyUtil.transfer(po, MemberLevelVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询会员等级表数量",notes = "查询会员等级表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid MemberLevelQueryForm form) throws eusercenterException {
		MemberLevelCondition condition = CopyUtil.transfer(form, MemberLevelCondition.class);
		int count = memberLevelService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询会员等级表列表",notes = "查询会员等级表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<MemberLevelVo>> queryList(@RequestBody@Valid MemberLevelQueryForm form) throws eusercenterException {
		MemberLevelCondition condition = CopyUtil.transfer(form, MemberLevelCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<MemberLevelPo> poList = memberLevelService.queryList(condition);
		List<MemberLevelVo> voList = CopyUtil.transfer(poList, MemberLevelVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询会员等级表列表(带分页)",notes = "查询会员等级表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<MemberLevelVo>> queryPageList(@RequestBody@Valid MemberLevelQueryForm form) throws eusercenterException {
		MemberLevelCondition condition = CopyUtil.transfer(form, MemberLevelCondition.class);
		List<MemberLevelVo> voList = new ArrayList<>();
		int count = memberLevelService.queryCount(condition);
		if (count > 0) {
			List<MemberLevelPo> poList = memberLevelService.queryList(condition);
			voList = CopyUtil.transfer(poList, MemberLevelVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增会员等级表",notes = "新增会员等级表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<MemberLevelVo> add(@RequestBody@Valid MemberLevelCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		MemberLevelPo po = CopyUtil.transfer(form, MemberLevelPo.class);
		po.setAddTime(optTime);
		memberLevelService.insert(po);
		MemberLevelVo vo = CopyUtil.transfer(po, MemberLevelVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改会员等级表",notes = "修改会员等级表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid MemberLevelUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		MemberLevelPo po = CopyUtil.transfer(form, MemberLevelPo.class);
		po.setOptTime(optTime);
		memberLevelService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除会员等级表",notes = "删除会员等级表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid MemberLevelDeleteForm form) throws eusercenterException {
		memberLevelService.delete(form.getId());
		return getSuccessResult();
	}

}