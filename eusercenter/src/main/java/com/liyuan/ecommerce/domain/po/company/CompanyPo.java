package com.liyuan.ecommerce.domain.po.company;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CompanyPo implements Serializable {

	/**
	 * 主键，平台也属于商家，给定特定编号
	*/
	private String id;
	/**
	 * 企业编号
	*/
	private String companyCode;
	/**
	 * 企业名称
	*/
	private String companyName;
	/**
	 * 联系人手机号
	*/
	private String phone;
	/**
	 * 联系人真实姓名
	*/
	private String realName;
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
	 * 微博
	*/
	private String weibo;
	/**
	 * 企业logo
	*/
	private String companyLogo;
	/**
	 * 营业执照
	*/
	private String licenceImg;
	/**
	 * 统一社会信用代码
	*/
	private String licenceCode;
	/**
	 * 企业主页
	*/
	private String companyPage;
	/**
	 * 企业位置
	*/
	private String addressProvince;
	/**
	 * 企业位置
	*/
	private String addressCity;
	/**
	 * 企业位置
	*/
	private String addressDistinct;
	/**
	 * 企业位置
	*/
	private String addressDetail;
	/**
	 * 企业介绍
	*/
	private String companyProfile;
	/**
	 * 企业性质
	*/
	private String companyQuality;
	/**
	 * 经营范围
	*/
	private String businessScope;
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
	 * 业务状态，0待审核，1正常，审核通过，-1审核未通过
	*/
	private Integer state;
	/**
	 * 是否已删除，1是，0否
	*/
	private Integer isDelete;
}