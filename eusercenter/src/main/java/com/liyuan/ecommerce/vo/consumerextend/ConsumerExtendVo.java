package com.liyuan.ecommerce.vo.consumerextend;

import java.io.Serializable;
import com.liyuan.ecommerce.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "消费者用户扩展表")
public class ConsumerExtendVo implements Serializable {

	@ApiModelProperty(value = "主键，关联消费者主表", required = true)
	private Integer consumerId;

	@ApiModelProperty(value = "生日")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date birthday;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "身份证号")
	private String idcard;

	@ApiModelProperty(value = "身份证正面")
	private String idcardImgFore;

	@ApiModelProperty(value = "身份证反面")
	private String idcardImgBack;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "居住地")
	private String addressProvince;

	@ApiModelProperty(value = "居住地")
	private String addressCity;

	@ApiModelProperty(value = "居住地")
	private String addressDistinct;

	@ApiModelProperty(value = "居住地")
	private String addressDetail;

	@ApiModelProperty(value = "推荐人")
	private String recommenderId;

	@ApiModelProperty(value = "所属商家")
	private String companyId;

}