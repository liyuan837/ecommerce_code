package com.liyuan.ecommerce.form.company;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "商家表")
public class CompanyDeleteForm implements Serializable {

	@ApiModelProperty(value = "主键，平台也属于商家，给定特定编号", required = true)
	@NotEmpty(message = "主键，平台也属于商家，给定特定编号不能为空")
	private String id;

}