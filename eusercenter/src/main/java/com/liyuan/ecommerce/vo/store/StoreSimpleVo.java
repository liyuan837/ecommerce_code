package com.liyuan.ecommerce.vo.store;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "店铺表")
public class StoreSimpleVo implements Serializable {

	@ApiModelProperty(value = "主键", required = true)
	private String id;

	@ApiModelProperty(value = "店铺编号")
	private String storeCode;

	@ApiModelProperty(value = "店铺名称")
	private String storeName;

	@ApiModelProperty(value = "门店类型，1商家，2自营")
	private Integer type;

	@ApiModelProperty(value = "所属商家")
	private String companyId;

}