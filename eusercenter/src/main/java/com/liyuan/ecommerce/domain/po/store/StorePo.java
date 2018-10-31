package com.liyuan.ecommerce.domain.po.store;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class StorePo implements Serializable {

	/**
	 * 主键
	*/
	private String id;
	/**
	 * 店铺编号
	*/
	private String storeCode;
	/**
	 * 店铺名称
	*/
	private String storeName;
	/**
	 * 联系人手机号
	*/
	private String phone;
	/**
	 * 联系人真实姓名
	*/
	private String realName;
	/**
	 * 门店类型，1商家，2自营
	*/
	private Integer type;
	/**
	 * 联系人身份证
	*/
	private String idcard;
	/**
	 * 身份证正面
	*/
	private String idcardImgFore;
	/**
	 * 身份证反面
	*/
	private String idcardImgBack;
	/**
	 * 邮箱
	*/
	private String email;
	/**
	 * qq
	*/
	private String qq;
	/**
	 * 微信
	*/
	private String wechat;
	/**
	 * 店铺logo
	*/
	private String storeLogo;
	/**
	 * 营业执照
	*/
	private String licenceImg;
	/**
	 * 统一社会信用代码
	*/
	private String licenceCode;
	/**
	 * 门店地址
	*/
	private String addressProvince;
	/**
	 * 门店地址
	*/
	private String addressCity;
	/**
	 * 门店地址
	*/
	private String addressDistinct;
	/**
	 * 门店地址
	*/
	private String addressDetail;
	/**
	 * 门店介绍
	*/
	private String storeProfile;
	/**
	 * 经营范围
	*/
	private String businessScope;
	/**
	 * 所属商家
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
	 * 业务状态，0待审核，1正常，审核通过，-1审核不通过
	*/
	private Integer state;
	/**
	 * 是否已删除，1是，0否
	*/
	private Integer isDelete;
}