package com.liyuan.ecommerce.form.consumerextend;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "消费者用户扩展表")
public class ConsumerExtendDeleteForm implements Serializable {

	@ApiModelProperty(value = "主键，关联消费者主表", required = true)
	@NotNull(message = "主键，关联消费者主表不能为空")
	private Integer consumerId;

}