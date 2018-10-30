package com.liyuan.ecommerce.vo.store;

import java.io.Serializable;
import com.liyuan.ecommerce.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "店铺表")
public class StoreVo implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	private String id;

	@ApiModelProperty(value = "店铺编号")
	private String storeCode;

	@ApiModelProperty(value = "店铺名称")
	private String storeName;

	@ApiModelProperty(value = "联系人手机号")
	private String phone;

	@ApiModelProperty(value = "联系人真实姓名")
	private String realName;

	@ApiModelProperty(value = "门店类型，1商家，2自营")
	private Integer type;

	@ApiModelProperty(value = "所属商家id")
	private Integer companyIc;

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

	@ApiModelProperty(value = "所属商家")
	private String companyId;

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

	@ApiModelProperty(value = "业务状态，0待审核，1正常，审核通过，-1审核不通过")
	private Integer state;

	@ApiModelProperty(value = "是否已删除，1是，0否")
	private Integer isDelete;

}