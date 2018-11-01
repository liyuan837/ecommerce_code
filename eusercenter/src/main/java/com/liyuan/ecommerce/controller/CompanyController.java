package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.annotation.NotToken;
import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.constants.UserType;
import com.liyuan.ecommerce.domain.po.company.CompanyPo;
import com.liyuan.ecommerce.domain.condition.company.CompanyCondition;
import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.form.company.*;
import com.liyuan.ecommerce.form.companyuser.CompanyUserRegisterForm;
import com.liyuan.ecommerce.service.CompanyUserService;
import com.liyuan.ecommerce.util.JwtUser;
import com.liyuan.ecommerce.util.JwtUtil;
import com.liyuan.ecommerce.vo.company.CompanyVo;
import com.liyuan.ecommerce.service.CompanyService;
import com.liyuan.ecommerce.domain.exception.eusercenterException;
import com.liyuan.ecommerce.util.CopyUtil;
import com.liyuan.ecommerce.domain.response.ResponseEntity;
import com.liyuan.ecommerce.domain.response.PageListResponse;
import java.util.List;
import java.util.ArrayList;

import com.liyuan.ecommerce.vo.companyuser.CompanyUserVo;
import com.liyuan.ecommerce.vo.user.LoginUserVo;
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

	@Autowired
	private CompanyUserService companyUserService;

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

    @ApiOperation(value = "注册新商家企业",notes = "注册新商家企业",httpMethod = "POST")
    @PostMapping(value = "/register")
    public ResponseEntity<CompanyVo> register(@RequestHeader("Authorization") String authorization,@RequestBody@Valid CompanyRegisterForm form) throws eusercenterException {
        LoginUserVo loginUserVo = JwtUtil.checkUserLogin(authorization);
        if(loginUserVo.getUserType() != UserType.COMPANY_USER || loginUserVo.getType() != UserType.SUPERMANAGER){
            return getFailResult("您不是企业超级管理员，无法注册企业");
        }
        //[0]判断该用户是否已经关联企业
        CompanyUserPo companyUserPo = companyUserService.query(loginUserVo.getUserId());
        if(companyUserPo == null){
            return getFailResult("您的企业用户信息不存在");
        }
        CompanyPo companyPo = companyService.query(companyUserPo.getCompanyId());
        if(companyPo != null){
            return getFailResult("该账号已和"+companyPo.getCompanyName()+ "关联");
        }

        //[1]校验企业是否存在
        checkRegisterForm(form);

        //[2]保存企业信息
        CompanyVo vo = companyService.register(form,loginUserVo);

        return getSuccessResult(vo);
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

    /**
     * 企业注册只校验企业名是否存在
     * @param form
     */
	private void checkRegisterForm(CompanyRegisterForm form){
        CompanyCondition condition = new CompanyCondition();
        condition.setCompanyName(form.getCompanyName());
        condition.setIsDelete(SystemConstants.UNDELETED);
        Integer count = companyService.queryCount(condition);
        if(count > 0){
            throw new eusercenterException("该企业名称已存在");
        }
    }

}