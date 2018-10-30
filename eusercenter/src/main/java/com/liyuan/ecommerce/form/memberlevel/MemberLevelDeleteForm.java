package com.liyuan.ecommerce.form.memberlevel;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "会员等级表")
public class MemberLevelDeleteForm implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	@NotNull(message = "主键不能为空")
	private Integer id;

}