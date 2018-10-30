package com.liyuan.ecommerce.domain.po.memberlevel;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class MemberLevelPo implements Serializable {

	/**
	 * 主键
	*/
	private Integer id;
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
	 * 添加时间
	*/
	private Date addTime;
	/**
	 * 添加人
	*/
	private String addUserCode;
	/**
	 * 修改操作时间
	*/
	private Date optTime;
	/**
	 * 修改操作人
	*/
	private String optUserCode;
	/**
	 * 状态，1启用，0禁用
	*/
	private Integer state;
	/**
	 * 是否删除，1是0否
	*/
	private Integer isDelete;
}