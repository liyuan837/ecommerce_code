package com.liyuan.ecommerce.vo.storeuser;

import java.io.Serializable;
import com.liyuan.ecommerce.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "店铺员工表")
public class StoreUserVo implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	private String id;

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "昵称，可用作登录验证")
	private String nickName;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "手机号，可用作登录验证")
	private String phone;

	@ApiModelProperty(value = "店铺用户类型，1店主，2其他")
	private Integer type;

	@ApiModelProperty(value = "性别，0未知，1男2女")
	private Integer sex;

	@ApiModelProperty(value = "生日")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date birthday;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "头像")
	private String headerImg;

	@ApiModelProperty(value = "用户角色id")
	private Integer userRoleId;

	@ApiModelProperty(value = "所属店铺")
	private String storeId;

	@ApiModelProperty(value = "添加时间")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date addTime;

	@ApiModelProperty(value = "添加人")
	private String addUserId;

	@ApiModelProperty(value = "修改操作时间")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date optTime;

	@ApiModelProperty(value = "修改操作人")
	private String optUserId;

	@ApiModelProperty(value = "状态，1正常，0注销")
	private Integer state;

	@ApiModelProperty(value = "是否删除")
	private Integer isDelete;

}