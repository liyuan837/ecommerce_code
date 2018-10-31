package com.liyuan.ecommerce.domain.condition.consumer;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.domain.condition.BaseCondition;
import java.util.Date;
import lombok.Data;

@Data
public class ConsumerCondition extends BaseCondition implements Serializable {

	/**
	 * 主键，C开头
	*/
	private String id;
	/**
	 * 主键，C开头列表
	*/
	private List<String> idList;
	/**
	 * 消费者编号
	*/
	private String consumerCode;
	/**
	 * 昵称，可用作登录名
	*/
	private String nickName;
	/**
	 * 真实姓名
	*/
	private String realName;
	/**
	 * 手机号，可用作登录名
	*/
	private String phone;
	/**
	 * 登录密码，加密
	*/
	private String password;
	/**
	 * 性别，0未知，1男，2女
	*/
	private Integer sex;
	/**
	 * 头像
	*/
	private String headerImg;
	/**
	 * 二维码
	*/
	private String qrcodeImg;
	/**
	 * 积分值
	*/
	private Integer bonusPoints;
	/**
	 * 经验值
	*/
	private Integer experienceValue;
	/**
	 * 会员等级
	*/
	private Integer memberLevelId;
	/**
	 * 个人简介
	*/
	private String personalProfile;
	/**
	 * 最后一次登录IP
	*/
	private String lastLoginIp;
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
	 * 是否删除，1是0否
	*/
	private Integer isDelete;
}