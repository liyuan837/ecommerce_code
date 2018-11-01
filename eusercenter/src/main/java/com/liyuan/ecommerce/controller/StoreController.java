package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.constants.SystemConstants;
import com.liyuan.ecommerce.constants.UserType;
import com.liyuan.ecommerce.domain.condition.company.CompanyCondition;
import com.liyuan.ecommerce.domain.po.company.CompanyPo;
import com.liyuan.ecommerce.domain.po.companyuser.CompanyUserPo;
import com.liyuan.ecommerce.domain.po.store.StorePo;
import com.liyuan.ecommerce.domain.condition.store.StoreCondition;
import com.liyuan.ecommerce.domain.po.storeuser.StoreUserPo;
import com.liyuan.ecommerce.form.company.CompanyRegisterForm;
import com.liyuan.ecommerce.form.store.*;
import com.liyuan.ecommerce.service.StoreUserService;
import com.liyuan.ecommerce.util.JwtUtil;
import com.liyuan.ecommerce.vo.company.CompanyVo;
import com.liyuan.ecommerce.vo.store.StoreVo;
import com.liyuan.ecommerce.service.StoreService;
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
@RequestMapping("/store")
@Api(value = "/store", description = "店铺表")
public class StoreController extends BaseController {

	@Autowired
	private StoreService storeService;

    @Autowired
    private StoreUserService storeUserService;

	@ApiOperation(value = "查询店铺表",notes = "根据ID查询店铺表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<StoreVo> query(@ApiParam(value = "主键", required = true)@RequestParam String id) throws eusercenterException {
		StorePo po = storeService.queryWithValid(id);
		StoreVo vo = CopyUtil.transfer(po, StoreVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询店铺表数量",notes = "查询店铺表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid StoreQueryForm form) throws eusercenterException {
		StoreCondition condition = CopyUtil.transfer(form, StoreCondition.class);
		int count = storeService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询店铺表列表",notes = "查询店铺表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<StoreVo>> queryList(@RequestBody@Valid StoreQueryForm form) throws eusercenterException {
		StoreCondition condition = CopyUtil.transfer(form, StoreCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<StorePo> poList = storeService.queryList(condition);
		List<StoreVo> voList = CopyUtil.transfer(poList, StoreVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询店铺表列表(带分页)",notes = "查询店铺表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<StoreVo>> queryPageList(@RequestBody@Valid StoreQueryForm form) throws eusercenterException {
		StoreCondition condition = CopyUtil.transfer(form, StoreCondition.class);
		List<StoreVo> voList = new ArrayList<>();
		int count = storeService.queryCount(condition);
		if (count > 0) {
			List<StorePo> poList = storeService.queryList(condition);
			voList = CopyUtil.transfer(poList, StoreVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

    @ApiOperation(value = "注册新店铺",notes = "注册新店铺",httpMethod = "POST")
    @PostMapping(value = "/register")
    public ResponseEntity<StoreVo> register(@RequestHeader("Authorization") String authorization,@RequestBody@Valid StoreRegisterForm form) throws eusercenterException {
        LoginUserVo loginUserVo = JwtUtil.checkUserLogin(authorization);
        if(loginUserVo.getUserType() != UserType.STORE_USER || loginUserVo.getType() != UserType.SUPERMANAGER){
            return getFailResult("您不是店铺超级管理员，无法注册店铺");
        }
        //[0]判断该用户是否已经关联店铺
        StoreUserPo storeUserPo = storeUserService.query(loginUserVo.getUserId());
        if(storeUserPo == null){
            return getFailResult("您的店铺用户信息不存在");
        }
        StorePo storePo = storeService.query(storeUserPo.getStoreId());
        if(storePo != null){
            return getFailResult("该账号已和"+storePo.getStoreName()+ "关联");
        }

        //[1]校验店铺是否存在
        checkRegisterForm(form);

        //[2]保存企业信息
        StoreVo vo = storeService.register(form,loginUserVo);

        return getSuccessResult(vo);
    }

	@ApiOperation(value = "新增店铺表",notes = "新增店铺表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<StoreVo> add(@RequestBody@Valid StoreCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		StorePo po = CopyUtil.transfer(form, StorePo.class);
		po.setAddTime(optTime);
		storeService.insert(po);
		StoreVo vo = CopyUtil.transfer(po, StoreVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改店铺表",notes = "修改店铺表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid StoreUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		StorePo po = CopyUtil.transfer(form, StorePo.class);
		po.setOptTime(optTime);
		storeService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除店铺表",notes = "删除店铺表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid StoreDeleteForm form) throws eusercenterException {
		storeService.delete(form.getId());
		return getSuccessResult();
	}

    /**
     * 店铺注册只校验企业名是否存在
     * @param form
     */
    private void checkRegisterForm(StoreRegisterForm form){
        StoreCondition condition = new StoreCondition();
        condition.setStoreName(form.getStoreName());
        condition.setIsDelete(SystemConstants.UNDELETED);
        Integer count = storeService.queryCount(condition);
        if(count > 0){
            throw new eusercenterException("该店铺名称已存在");
        }
    }
}