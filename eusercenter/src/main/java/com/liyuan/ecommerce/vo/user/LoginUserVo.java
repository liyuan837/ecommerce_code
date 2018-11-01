package com.liyuan.ecommerce.vo.user;

import com.liyuan.ecommerce.vo.company.CompanySimpleVo;
import com.liyuan.ecommerce.vo.store.StoreSimpleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(description = "平台用户表")
public class LoginUserVo implements Serializable {
    @ApiModelProperty(value = "用户主键", required = true)
    private String userId;

	@ApiModelProperty(value = "用户编号")
	private String userCode;

	@ApiModelProperty(value = "昵称，可用作登录验证")
	private String nickName;

    @ApiModelProperty(value = "用户头像")
	private String headerImg;

	@ApiModelProperty(value = "手机号，可用作登录验证")
	private String phone;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "用户类型，1平台人员，2商家人员，3店铺人员")
    private Integer userType;

    @ApiModelProperty(value = "1为负责人，2为其他")
    private Integer type;

    @ApiModelProperty(value = "用户角色id")
    private Integer userRoleId;

	@ApiModelProperty(value = "token")
	private String token;

	@ApiModelProperty(value = "所属店铺")
	private StoreSimpleVo store;

	@ApiModelProperty(value = "所属商家")
	private CompanySimpleVo company;
}