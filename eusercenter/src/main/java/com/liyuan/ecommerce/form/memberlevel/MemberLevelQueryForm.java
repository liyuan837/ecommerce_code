package com.liyuan.ecommerce.form.memberlevel;

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
@ApiModel(description = "会员等级表")
public class MemberLevelQueryForm extends BaseQueryForm implements Serializable {

	@ApiModelProperty(value = "主键")
	private Integer id;

	@ApiModelProperty(value = "主键列表")
	private List<Integer> idList;

	@ApiModelProperty(value = "等级值")
	private String levelValue;

	@ApiModelProperty(value = "登记名称")
	private String levelName;

	@ApiModelProperty(value = "最小经验值")
	private Integer minExperienceValue;

	@ApiModelProperty(value = "最大经验值")
	private Integer maxExperienceValue;

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

	@ApiModelProperty(value = "状态，1启用，0禁用")
	private Integer state;

	@ApiModelProperty(value = "是否删除，1是0否")
	private Integer isDelete;

}