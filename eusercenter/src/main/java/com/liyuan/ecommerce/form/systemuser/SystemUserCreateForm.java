package com.liyuan.ecommerce.form.systemuser;

import java.io.Serializable;
import com.liyuan.ecommerce.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "平台用户表")
public class SystemUserCreateForm implements Serializable {

	@ApiModelProperty(value = "昵称，可用作登录验证")
    @NotNull(message = "昵称不能为空")
	private String nickName;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

    @ApiModelProperty(value = "登录密码")
    private String password;

	@ApiModelProperty(value = "手机号，可用作登录验证")
    @NotNull(message = "手机号不能为空")
	private String phone;

	@ApiModelProperty(value = "性别，0未知，1男2女")
	private Integer sex;

	@ApiModelProperty(value = "生日,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date birthday;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "头像")
	private String headerImg;

	@ApiModelProperty(value = "用户角色id")
	private Integer userRoleId;

}