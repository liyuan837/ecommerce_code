package com.liyuan.ecommerce.form.memberlevel;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "会员等级表")
public class MemberLevelCreateForm implements Serializable {

	@ApiModelProperty(value = "等级值")
	private String levelValue;

	@ApiModelProperty(value = "登记名称")
	private String levelName;

	@ApiModelProperty(value = "最小经验值")
	private Integer minExperienceValue;

	@ApiModelProperty(value = "最大经验值")
	private Integer maxExperienceValue;

	@ApiModelProperty(value = "添加人")
	private String addUserId;

	@ApiModelProperty(value = "修改操作人")
	private String optUserId;

	@ApiModelProperty(value = "状态，1启用，0禁用")
	private Integer state;

	@ApiModelProperty(value = "是否删除，1是0否")
	private Integer isDelete;

}