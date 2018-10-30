package com.liyuan.ecommerce.domain.condition.user;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.domain.condition.BaseCondition;
import lombok.Data;

@Data
public class UserCondition extends BaseCondition implements Serializable {

	/**
	 * 用户编号
	*/
	private String userCode;
	/**
	 * 用户编号列表
	*/
	private List<String> userCodeList;
	/**
	 * 昵称，可用作登录验证
	*/
	private String nickName;
	/**
	 * 手机号，可用作登录验证
	*/
	private String phone;
	/**
	 * 登录密码
	*/
	private String password;
	/**
	 * 用户类型，1平台人员，2商家人员，3店铺人员，和昵称、手机号作为唯一用户登录
	*/
	private Integer userType;
	/**
	 * 最后一次登录IP
	*/
	private String lastLoginIp;
	/**
	 * 状态，1正常，0注销
	*/
	private Integer state;
	/**
	 * 是否删除
	*/
	private Integer isDelete;
}