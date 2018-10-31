package com.liyuan.ecommerce.form.companyuser;

import com.liyuan.ecommerce.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "商家用户注册表")
public class CompanyUserRegisterForm implements Serializable {

	@ApiModelProperty(value = "昵称，可用作登录验证")
    @NotNull(message = "昵称不能为空")
	private String nickName;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "手机号，可用作登录验证")
    @NotNull(message = "手机号不能为空")
	private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

	@ApiModelProperty(value = "性别，0未知，1男2女")
	private Integer sex;

	@ApiModelProperty(value = "生日,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date birthday;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "头像")
	private String headerImg;

}