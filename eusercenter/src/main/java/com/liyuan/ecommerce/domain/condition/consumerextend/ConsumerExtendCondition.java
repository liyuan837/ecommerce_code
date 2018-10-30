package com.liyuan.ecommerce.domain.condition.consumerextend;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.domain.condition.BaseCondition;
import lombok.Data;

@Data
public class ConsumerExtendCondition extends BaseCondition implements Serializable {

	/**
	 * 主键，关联消费者主表
	*/
	private Integer consumerId;
	/**
	 * 主键，关联消费者主表列表
	*/
	private List<Integer> consumerIdList;
	/**
	 * 年龄
	*/
	private Integer age;
	/**
	 * 身份证号
	*/
	private String idcard;
	/**
	 * 身份证正面
	*/
	private String idcardImgFore;
	/**
	 * 身份证反面
	*/
	private String idcardImgBack;
	/**
	 * 邮箱
	*/
	private String email;
	/**
	 * 居住地
	*/
	private String addressProvince;
	/**
	 * 居住地
	*/
	private String addressCity;
	/**
	 * 居住地
	*/
	private String addressDistinct;
	/**
	 * 居住地
	*/
	private String addressDetail;
	/**
	 * 推荐人
	*/
	private String recommenderId;
	/**
	 * 所属商家
	*/
	private String companyId;
}