package com.liyuan.ecommerce.vo.consumer;

import java.io.Serializable;
import com.liyuan.ecommerce.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "消费者用户表")
public class ConsumerVo implements Serializable {

	@ApiModelProperty(value = "主键，C开头", required = true)
	private String id;

	@ApiModelProperty(value = "消费者编号")
	private String consumerCode;

	@ApiModelProperty(value = "昵称，可用作登录名")
	private String nickName;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "手机号，可用作登录名")
	private String phone;

	@ApiModelProperty(value = "登录密码，加密")
	private String password;

	@ApiModelProperty(value = "性别，0未知，1男，2女")
	private Integer sex;

	@ApiModelProperty(value = "头像")
	private String headerImg;

	@ApiModelProperty(value = "二维码")
	private String qrcodeImg;

	@ApiModelProperty(value = "积分值")
	private Integer bonusPoints;

	@ApiModelProperty(value = "经验值")
	private Integer experienceValue;

	@ApiModelProperty(value = "会员等级")
	private Integer memberLevelId;

	@ApiModelProperty(value = "个人简介")
	private String personalProfile;

	@ApiModelProperty(value = "最后一次登录IP")
	private String lastLoginIp;

	@ApiModelProperty(value = "最后一次登陆时间")
	@JsonFormat(pattern= DateUtil.FORMAT,timezone="GMT+8")
	private Date lastLoginTime;

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

	@ApiModelProperty(value = "状态，1正常，0注销")
	private Integer state;

	@ApiModelProperty(value = "是否删除，1是0否")
	private Integer isDelete;

}