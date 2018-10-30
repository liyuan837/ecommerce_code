package com.liyuan.ecommerce.form.consumerextend;

import java.io.Serializable;
import com.liyuan.ecommerce.util.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "消费者用户扩展表")
public class ConsumerExtendCreateForm implements Serializable {

	@ApiModelProperty(value = "生日,格式为:" + DateUtil.FORMAT)
	@DateTimeFormat(pattern = DateUtil.FORMAT)
	private Date birthday;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "身份证号")
	private String idcard;

	@ApiModelProperty(value = "身份证正面")
	private String idcardImgFore;

	@ApiModelProperty(value = "身份证反面")
	private String idcardImgBack;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@ApiModelProperty(value = "居住地")
	private String addressProvince;

	@ApiModelProperty(value = "居住地")
	private String addressCity;

	@ApiModelProperty(value = "居住地")
	private String addressDistinct;

	@ApiModelProperty(value = "居住地")
	private String addressDetail;

	@ApiModelProperty(value = "推荐人")
	private String recommenderId;

	@ApiModelProperty(value = "所属商家")
	private String companyId;

}