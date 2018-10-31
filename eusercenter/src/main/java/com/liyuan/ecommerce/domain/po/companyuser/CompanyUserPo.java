package com.liyuan.ecommerce.domain.po.companyuser;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CompanyUserPo implements Serializable {

	/**
	 * 主键
	*/
	private String id;
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
	 * 商家用户类型，1商家负责人，2其他
	*/
	private Integer type;
	/**
	 * 性别，0未知，1男2女
	*/
	private Integer sex;
	/**
	 * 生日
	*/
	private Date birthday;
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
	 * 所属商家id
	*/
	private String companyId;
	/**
	 * 添加时间
	*/
	private Date addTime;
	/**
	 * 添加人
	*/
	private String addUserId;
	/**
	 * 修改操作时间
	*/
	private Date optTime;
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