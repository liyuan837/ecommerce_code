package com.liyuan.ecommerce.form.user;

import com.liyuan.ecommerce.form.BaseQueryForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
/**
 * @Author: LiYuan
 * @Description:平台用户登录表，接收用户登录参数
 * @Date: 16:29 2018/10/30
 */
@Data
@ApiModel(description = "平台用户登录表")
public class UserLoginForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "昵称，可用作登录验证")
	private String nickName;

	@ApiModelProperty(value = "手机号，可用作登录验证")
	private String phone;

	@ApiModelProperty(value = "登录密码")
    @NotNull(message = "密码不能为空")
	private String password;

	@ApiModelProperty(value = "用户类型，1平台人员，2商家人员，3店铺人员，和昵称、手机号作为唯一用户登录")
    @NotNull(message = "用户类型不能为空")
	private Integer userType;

}