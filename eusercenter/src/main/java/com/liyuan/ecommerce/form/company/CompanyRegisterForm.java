package com.liyuan.ecommerce.form.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "商家表")
public class CompanyRegisterForm implements Serializable {

	@ApiModelProperty(value = "企业名称")
    @NotNull(message = "企业名称不能为空")
	private String companyName;

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

	@ApiModelProperty(value = "微博")
	private String weibo;

	@ApiModelProperty(value = "企业logo")
	private String companyLogo;

	@ApiModelProperty(value = "营业执照")
	private String licenceImg;

	@ApiModelProperty(value = "统一社会信用代码")
	private String licenceCode;

	@ApiModelProperty(value = "企业主页")
	private String companyPage;

	@ApiModelProperty(value = "企业位置")
	private String addressProvince;

	@ApiModelProperty(value = "企业位置")
	private String addressCity;

	@ApiModelProperty(value = "企业位置")
	private String addressDistinct;

	@ApiModelProperty(value = "企业位置")
	private String addressDetail;

	@ApiModelProperty(value = "企业介绍")
	private String companyProfile;

	@ApiModelProperty(value = "企业性质")
	private String companyQuality;

	@ApiModelProperty(value = "经营范围")
	private String businessScope;

}