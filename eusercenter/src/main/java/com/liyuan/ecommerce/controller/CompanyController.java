package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.domain.po.company.CompanyPo;
import com.liyuan.ecommerce.domain.condition.company.CompanyCondition;
import com.liyuan.ecommerce.form.company.*;
import com.liyuan.ecommerce.vo.company.CompanyVo;
import com.liyuan.ecommerce.service.CompanyService;
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
@RequestMapping("/company")
@Api(value = "/company", description = "商家表")
public class CompanyController extends BaseController {

	@Autowired
	private CompanyService companyService;

	@ApiOperation(value = "查询商家表",notes = "根据ID查询商家表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<CompanyVo> query(@ApiParam(value = "主键，平台也属于商家，给定特定编号", required = true)@RequestParam String id) throws eusercenterException {
		CompanyPo po = companyService.queryWithValid(id);
		CompanyVo vo = CopyUtil.transfer(po, CompanyVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询商家表数量",notes = "查询商家表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid CompanyQueryForm form) throws eusercenterException {
		CompanyCondition condition = CopyUtil.transfer(form, CompanyCondition.class);
		int count = companyService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询商家表列表",notes = "查询商家表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<CompanyVo>> queryList(@RequestBody@Valid CompanyQueryForm form) throws eusercenterException {
		CompanyCondition condition = CopyUtil.transfer(form, CompanyCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<CompanyPo> poList = companyService.queryList(condition);
		List<CompanyVo> voList = CopyUtil.transfer(poList, CompanyVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询商家表列表(带分页)",notes = "查询商家表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<CompanyVo>> queryPageList(@RequestBody@Valid CompanyQueryForm form) throws eusercenterException {
		CompanyCondition condition = CopyUtil.transfer(form, CompanyCondition.class);
		List<CompanyVo> voList = new ArrayList<>();
		int count = companyService.queryCount(condition);
		if (count > 0) {
			List<CompanyPo> poList = companyService.queryList(condition);
			voList = CopyUtil.transfer(poList, CompanyVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增商家表",notes = "新增商家表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<CompanyVo> add(@RequestBody@Valid CompanyCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		CompanyPo po = CopyUtil.transfer(form, CompanyPo.class);
		po.setAddTime(optTime);
		companyService.insert(po);
		CompanyVo vo = CopyUtil.transfer(po, CompanyVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改商家表",notes = "修改商家表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid CompanyUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		CompanyPo po = CopyUtil.transfer(form, CompanyPo.class);
		po.setOptTime(optTime);
		companyService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除商家表",notes = "删除商家表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid CompanyDeleteForm form) throws eusercenterException {
		companyService.delete(form.getId());
		return getSuccessResult();
	}

}