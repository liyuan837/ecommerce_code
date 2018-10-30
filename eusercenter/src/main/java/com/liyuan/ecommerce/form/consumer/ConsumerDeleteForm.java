package com.liyuan.ecommerce.form.consumer;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "消费者用户表")
public class ConsumerDeleteForm implements Serializable {

	@ApiModelProperty(value = "主键，C开头", required = true)
	@NotEmpty(message = "主键，C开头不能为空")
	private String id;

}