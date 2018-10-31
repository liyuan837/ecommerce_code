package com.liyuan.ecommerce.form.storeuser;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.form.BaseQueryForm;
import com.liyuan.ecommerce.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "店铺员工表")
public class StoreUserQueryForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "主键")
	private String id;

	@ApiModelProperty(value = "主键列表")
	private List<String> idList;

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

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "头像")
	private String headerImg;

	@ApiModelProperty(value = "用户角色id")
	private Integer userRoleId;

	@ApiModelProperty(value = "所属店铺")
	private String storeId;

	@ApiModelProperty(value = "最小添加时间,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date minAddTime;

	@ApiModelProperty(value = "最大添加时间,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date maxAddTime;

	@ApiModelProperty(value = "添加人")
	private String addUserId;

	@ApiModelProperty(value = "修改操作人")
	private String optUserId;

	@ApiModelProperty(value = "状态，1正常，0注销")
	private Integer state;

	@ApiModelProperty(value = "是否删除")
	private Integer isDelete;

}