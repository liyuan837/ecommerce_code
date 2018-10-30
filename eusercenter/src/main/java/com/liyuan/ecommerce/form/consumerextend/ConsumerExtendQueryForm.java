package com.liyuan.ecommerce.form.consumerextend;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "消费者用户扩展表")
public class ConsumerExtendQueryForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "主键，关联消费者主表")
	private Integer consumerId;

	@ApiModelProperty(value = "主键，关联消费者主表列表")
	private List<Integer> consumerIdList;

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