package com.liyuan.ecommerce.vo.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liyuan.ecommerce.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(description = "商家表")
public class CompanySimpleVo implements Serializable {

	@ApiModelProperty(value = "主键，平台也属于商家，给定特定编号", required = true)
	private String id;

	@ApiModelProperty(value = "企业编号")
	private String companyCode;

	@ApiModelProperty(value = "企业名称")
	private String companyName;

	@ApiModelProperty(value = "企业logo")
	private String companyLogo;


}