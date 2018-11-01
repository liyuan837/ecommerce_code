package com.liyuan.ecommerce.form.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "店铺表")
public class StoreRegisterForm implements Serializable {

	@ApiModelProperty(value = "店铺名称")
    @NotNull(message = "店铺名称不能为空")
	private String storeName;

	@ApiModelProperty(value = "联系人手机号")
	private String phone;

	@ApiModelProperty(value = "联系人真实姓名")
	private String realName;

	@ApiModelProperty(value = "联系人身份证")
	private String idcard;

	@ApiModelProperty(value = "身份证正面")
	private String idcardImgFore;

	@ApiModelProperty(value = "身份证反面")
	private String idcardImgBack;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "qq")
	private String qq;

	@ApiModelProperty(value = "微信")
	private String wechat;

	@ApiModelProperty(value = "店铺logo")
	private String storeLogo;

	@ApiModelProperty(value = "营业执照")
	private String licenceImg;

	@ApiModelProperty(value = "统一社会信用代码")
	private String licenceCode;

	@ApiModelProperty(value = "门店地址")
	private String addressProvince;

	@ApiModelProperty(value = "门店地址")
	private String addressCity;

	@ApiModelProperty(value = "门店地址")
	private String addressDistinct;

	@ApiModelProperty(value = "门店地址")
	private String addressDetail;

	@ApiModelProperty(value = "门店介绍")
	private String storeProfile;

	@ApiModelProperty(value = "经营范围")
	private String businessScope;

}