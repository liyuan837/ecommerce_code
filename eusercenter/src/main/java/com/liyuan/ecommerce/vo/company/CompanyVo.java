package com.liyuan.ecommerce.vo.company;

import java.io.Serializable;
import com.liyuan.ecommerce.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商家表")
public class CompanyVo implements Serializable {

	@ApiModelProperty(value = "主键，平台也属于商家，给定特定编号", required = true)
	private String id;

	@ApiModelProperty(value = "企业编号")
	private String companyCode;

	@ApiModelProperty(value = "企业名称")
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

	@ApiModelProperty(value = "添加时间")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date addTime;

	@ApiModelProperty(value = "添加人")
	private String addUserCode;

	@ApiModelProperty(value = "修改操作时间")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date optTime;

	@ApiModelProperty(value = "修改操作人")
	private String optUserCode;

	@ApiModelProperty(value = "业务状态，0待审核，1正常，审核通过，-1审核未通过")
	private Integer state;

	@ApiModelProperty(value = "是否已删除，1是，0否")
	private Integer isDelete;

}