package com.liyuan.ecommerce.domain.condition.storeuser;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.domain.condition.BaseCondition;
import java.util.Date;
import lombok.Data;

@Data
public class StoreUserCondition extends BaseCondition implements Serializable {

	/**
	 * 主键
	*/
	private String id;
	/**
	 * 主键列表
	*/
	private List<String> idList;
	/**
	 * 用户编号
	*/
	private String userCode;
	/**
	 * 昵称，可用作登录验证
	*/
	private String nickName;
	/**
	 * 真实姓名
	*/
	private String realName;
	/**
	 * 手机号，可用作登录验证
	*/
	private String phone;
	/**
	 * 店铺用户类型，1店主，2其他
	*/
	private Integer type;
	/**
	 * 性别，0未知，1男2女
	*/
	private Integer sex;
	/**
	 * 年龄
	*/
	private Integer age;
	/**
	 * 头像
	*/
	private String headerImg;
	/**
	 * 用户角色id
	*/
	private Integer userRoleId;
	/**
	 * 所属店铺
	*/
	private String storeId;
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
	 * 状态，1正常，0注销
	*/
	private Integer state;
	/**
	 * 是否删除
	*/
	private Integer isDelete;
}