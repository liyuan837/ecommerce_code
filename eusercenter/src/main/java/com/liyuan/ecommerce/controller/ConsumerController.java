package com.liyuan.ecommerce.controller;

import com.liyuan.ecommerce.domain.po.consumer.ConsumerPo;
import com.liyuan.ecommerce.domain.condition.consumer.ConsumerCondition;
import com.liyuan.ecommerce.form.consumer.*;
import com.liyuan.ecommerce.vo.consumer.ConsumerVo;
import com.liyuan.ecommerce.service.ConsumerService;
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
@RequestMapping("/consumer")
@Api(value = "/consumer", description = "消费者用户表")
public class ConsumerController extends BaseController {

	@Autowired
	private ConsumerService consumerService;

	@ApiOperation(value = "查询消费者用户表",notes = "根据ID查询消费者用户表",httpMethod = "GET")
	@GetMapping(value = "/query")
	public ResponseEntity<ConsumerVo> query(@ApiParam(value = "主键，C开头", required = true)@RequestParam String id) throws eusercenterException {
		ConsumerPo po = consumerService.queryWithValid(id);
		ConsumerVo vo = CopyUtil.transfer(po, ConsumerVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "查询消费者用户表数量",notes = "查询消费者用户表数量",httpMethod = "POST")
	@PostMapping(value = "/queryCount")
	public ResponseEntity<Integer> queryCount(@RequestBody@Valid ConsumerQueryForm form) throws eusercenterException {
		ConsumerCondition condition = CopyUtil.transfer(form, ConsumerCondition.class);
		int count = consumerService.queryCount(condition);
		return getSuccessResult(count);
	}

	@ApiOperation(value = "查询消费者用户表列表",notes = "查询消费者用户表列表",httpMethod = "POST")
	@PostMapping(value = "/queryList")
	public ResponseEntity<PageListResponse<ConsumerVo>> queryList(@RequestBody@Valid ConsumerQueryForm form) throws eusercenterException {
		ConsumerCondition condition = CopyUtil.transfer(form, ConsumerCondition.class);
		condition.setPageNum(0);
		condition.setPageSize(Integer.MAX_VALUE);
		List<ConsumerPo> poList = consumerService.queryList(condition);
		List<ConsumerVo> voList = CopyUtil.transfer(poList, ConsumerVo.class);
		return getSuccessResult(voList);
	}

	@ApiOperation(value = "查询消费者用户表列表(带分页)",notes = "查询消费者用户表列表(带分页)",httpMethod = "POST")
	@PostMapping(value = "/queryPageList")
	public ResponseEntity<PageListResponse<ConsumerVo>> queryPageList(@RequestBody@Valid ConsumerQueryForm form) throws eusercenterException {
		ConsumerCondition condition = CopyUtil.transfer(form, ConsumerCondition.class);
		List<ConsumerVo> voList = new ArrayList<>();
		int count = consumerService.queryCount(condition);
		if (count > 0) {
			List<ConsumerPo> poList = consumerService.queryList(condition);
			voList = CopyUtil.transfer(poList, ConsumerVo.class);
		}
		return getSuccessResult(getPageListResponse(condition.getPageNum(),condition.getPageSize(),count,voList));
	}

	@ApiOperation(value = "新增消费者用户表",notes = "新增消费者用户表",httpMethod = "POST")
	@PostMapping(value = "/add")
	public ResponseEntity<ConsumerVo> add(@RequestBody@Valid ConsumerCreateForm form) throws eusercenterException {
		Date optTime = new Date();
		ConsumerPo po = CopyUtil.transfer(form, ConsumerPo.class);
		po.setAddTime(optTime);
		consumerService.insert(po);
		ConsumerVo vo = CopyUtil.transfer(po, ConsumerVo.class);
		return getSuccessResult(vo);
	}

	@ApiOperation(value = "修改消费者用户表",notes = "修改消费者用户表",httpMethod = "POST")
	@PostMapping(value = "/update")
	public ResponseEntity update(@RequestBody@Valid ConsumerUpdateForm form) throws eusercenterException {
		Date optTime = new Date();
		ConsumerPo po = CopyUtil.transfer(form, ConsumerPo.class);
		po.setOptTime(optTime);
		consumerService.update(po);
		return getSuccessResult();
	}

	@ApiOperation(value = "（单个）删除消费者用户表",notes = "删除消费者用户表",httpMethod = "POST")
	@PostMapping(value = "/delete")
	public ResponseEntity delete(@RequestBody@Valid ConsumerDeleteForm form) throws eusercenterException {
		consumerService.delete(form.getId());
		return getSuccessResult();
	}

}