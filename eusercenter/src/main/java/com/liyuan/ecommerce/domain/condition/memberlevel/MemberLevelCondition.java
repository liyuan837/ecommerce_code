package com.liyuan.ecommerce.domain.condition.memberlevel;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.domain.condition.BaseCondition;
import java.util.Date;
import lombok.Data;

@Data
public class MemberLevelCondition extends BaseCondition implements Serializable {

	/**
	 * 主键
	*/
	private Integer id;
	/**
	 * 主键列表
	*/
	private List<Integer> idList;
	/**
	 * 等级值
	*/
	private String levelValue;
	/**
	 * 登记名称
	*/
	private String levelName;
	/**
	 * 最小经验值
	*/
	private Integer minExperienceValue;
	/**
	 * 最大经验值
	*/
	private Integer maxExperienceValue;
	/**
	 * 最小添加时间
	*/
	private Date minAddTime;
	/**
	 * 最大添加时间
	*/
	private Date maxAddTime;
	/**
	 * 添加人
	*/
	private String addUserId;
	/**
	 * 修改操作人
	*/
	private String optUserId;
	/**
	 * 状态，1启用，0禁用
	*/
	private Integer state;
	/**
	 * 是否删除，1是0否
	*/
	private Integer isDelete;
}