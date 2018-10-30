package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.domain.po.consumerextend.ConsumerExtendPo;
import com.liyuan.ecommerce.domain.condition.consumerextend.ConsumerExtendCondition;
import com.liyuan.ecommerce.form.consumerextend.*;
import com.liyuan.ecommerce.vo.consumerextend.ConsumerExtendVo;
import com.liyuan.ecommerce.service.ConsumerExtendService;
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

@RestController
@RequestMapping("/consumerextend")
@Api(value = "/consumerextend", description = "消费者用户扩展表")
public class ConsumerExtendController extends BaseController {

	@Autowired
	private ConsumerExtendService consumerExtendService;

	@ApiOperation(value = "查询消费者用户扩展表",notes = "根据ID查询消费者用户扩展表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<ConsumerExtendVo> query(@ApiParam(value = "主键，关联消费者主表", required = true)@RequestParam Integer consumerId) throws eusercenterException {
		ConsumerExtendPo po = consumerExtendService.queryWithValid(consumerId);
		ConsumerExtendVo vo = CopyUtil.transfer(po, ConsumerExtendVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询消费者用户扩展表数量",notes = "查询消费者用户扩展表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid ConsumerExtendQueryForm form) throws eusercenterException {
		ConsumerExtendCondition condition = CopyUtil.transfer(form, ConsumerExtendCondition.class);
		int count = consumerExtendService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询消费者用户扩展表列表",notes = "查询消费者用户扩展表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<ConsumerExtendVo>> queryList(@RequestBody@Valid ConsumerExtendQueryForm form) throws eusercenterException {
		ConsumerExtendCondition condition = CopyUtil.transfer(form, ConsumerExtendCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<ConsumerExtendPo> poList = consumerExtendService.queryList(condition);
		List<ConsumerExtendVo> voList = CopyUtil.transfer(poList, ConsumerExtendVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询消费者用户扩展表列表(带分页)",notes = "查询消费者用户扩展表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<ConsumerExtendVo>> queryPageList(@RequestBody@Valid ConsumerExtendQueryForm form) throws eusercenterException {
		ConsumerExtendCondition condition = CopyUtil.transfer(form, ConsumerExtendCondition.class);
		List<ConsumerExtendVo> voList = new ArrayList<>();
		int count = consumerExtendService.queryCount(condition);
		if (count > 0) {
			List<ConsumerExtendPo> poList = consumerExtendService.queryList(condition);
			voList = CopyUtil.transfer(poList, ConsumerExtendVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增消费者用户扩展表",notes = "新增消费者用户扩展表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<ConsumerExtendVo> add(@RequestBody@Valid ConsumerExtendCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		ConsumerExtendPo po = CopyUtil.transfer(form, ConsumerExtendPo.class);
		consumerExtendService.insert(po);
		ConsumerExtendVo vo = CopyUtil.transfer(po, ConsumerExtendVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改消费者用户扩展表",notes = "修改消费者用户扩展表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid ConsumerExtendUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		ConsumerExtendPo po = CopyUtil.transfer(form, ConsumerExtendPo.class);
		consumerExtendService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除消费者用户扩展表",notes = "删除消费者用户扩展表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid ConsumerExtendDeleteForm form) throws eusercenterException {
		consumerExtendService.delete(form.getConsumerId());
		return getSuccessResult();
	}

}