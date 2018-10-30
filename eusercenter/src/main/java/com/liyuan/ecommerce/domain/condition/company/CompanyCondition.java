package com.liyuan.ecommerce.domain.condition.company;

import java.io.Serializable;
import java.util.List;
import com.liyuan.ecommerce.domain.condition.BaseCondition;
import java.util.Date;
import lombok.Data;

@Data
public class CompanyCondition extends BaseCondition implements Serializable {

	/**
	 * 主键，平台也属于商家，给定特定编号
	*/
	private String id;
	/**
	 * 主键，平台也属于商家，给定特定编号列表
	*/
	private List<String> idList;
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
	private String addUserCode;
	/**
	 * 修改操作人
	*/
	private String optUserCode;
	/**
	 * 业务状态，0待审核，1正常，审核通过，-1审核未通过
	*/
	private Integer state;
	/**
	 * 是否已删除，1是，0否
	*/
	private Integer isDelete;
}