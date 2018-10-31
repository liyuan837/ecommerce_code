package com.liyuan.ecommerce.form.user;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "平台用户表")
public class UserQueryForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "用户编号")
	private String userId;

	@ApiModelProperty(value = "用户编号列表")
	private List<String> userIdList;

	@ApiModelProperty(value = "昵称，可用作登录验证")
	private String nickName;

	@ApiModelProperty(value = "手机号，可用作登录验证")
	private String phone;

	@ApiModelProperty(value = "登录密码")
	private String password;

	@ApiModelProperty(value = "用户类型，1平台人员，2商家人员，3店铺人员，和昵称、手机号作为唯一用户登录")
	private Integer userType;

	@ApiModelProperty(value = "最后一次登录IP")
	private String lastLoginIp;

	@ApiModelProperty(value = "状态，1正常，0注销")
	private Integer state;

	@ApiModelProperty(value = "是否删除")
	private Integer isDelete;

}