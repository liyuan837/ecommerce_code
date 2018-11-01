/*
SQLyog Ultimate v8.32 
MySQL - 5.6.33-log : Database - ecommercedb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ecommercedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin */;

USE `ecommercedb`;

/*Table structure for table `tb_back_category` */

DROP TABLE IF EXISTS `tb_back_category`;

CREATE TABLE `tb_back_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `type` int(11) DEFAULT NULL COMMENT '分类类型，1平台，2商家',
  `order_num` int(11) DEFAULT NULL COMMENT '同级分类中的排序值',
  `remarks` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `dept_num` int(11) DEFAULT NULL COMMENT '分类深度',
  `product_model_id` int(11) DEFAULT NULL COMMENT '所属产品模型',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级分类',
  `company_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除，1是0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='后端分类表';

/*Data for the table `tb_back_category` */

/*Table structure for table `tb_company` */

DROP TABLE IF EXISTS `tb_company`;

CREATE TABLE `tb_company` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键，平台也属于商家，给定特定编号',
  `company_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业编号',
  `company_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业名称',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人手机号',
  `real_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人真实姓名',
  `idcard` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人身份证',
  `idcard_img_fore` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证正面',
  `idcard_img_back` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证反面',
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'qq',
  `wechat` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信',
  `weibo` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微博',
  `company_logo` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业logo',
  `licence_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '营业执照',
  `licence_code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '统一社会信用代码',
  `company_page` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业主页',
  `address_province` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业位置',
  `address_city` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业位置',
  `address_distinct` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业位置',
  `address_detail` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业位置',
  `company_profile` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业介绍',
  `company_quality` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '企业性质',
  `business_scope` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '经营范围',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '修改操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改操作人',
  `state` int(11) DEFAULT NULL COMMENT '业务状态，0待审核，1正常，审核通过，-1审核未通过',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否已删除，1是，0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商家表';

/*Data for the table `tb_company` */

insert  into `tb_company`(`id`,`company_code`,`company_name`,`phone`,`real_name`,`idcard`,`idcard_img_fore`,`idcard_img_back`,`email`,`qq`,`wechat`,`weibo`,`company_logo`,`licence_img`,`licence_code`,`company_page`,`address_province`,`address_city`,`address_distinct`,`address_detail`,`company_profile`,`company_quality`,`business_scope`,`add_time`,`add_user_id`,`opt_time`,`opt_user_id`,`state`,`is_delete`) values ('C10001','C1360581662','大煎饼铺子','18260631811','王丽坤',NULL,NULL,NULL,'707335344@qq.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'江苏省','南京','雨花台','新华汇','煎饼供应','4人','分销商','2018-11-01 15:28:01','CU10001','2018-11-01 15:28:01','CU10001',0,0),('P10000','P00000000','电商平台','18260631810','李袁','320684199108304918',NULL,NULL,'18260631810@163.com','707335344','ly707335344','演得太逼真','http://dyjkglass.com/LOGO_IMGa53d6d86100000001',NULL,NULL,NULL,'江苏省','南京市','雨花台区','铁心桥街道蓝岸尚城','这是一个电商平台','电商','1000人',NULL,NULL,NULL,NULL,1,0);

/*Table structure for table `tb_company_user` */

DROP TABLE IF EXISTS `tb_company_user`;

CREATE TABLE `tb_company_user` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `user_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户编号',
  `nick_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称，可用作登录验证',
  `real_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号，可用作登录验证',
  `type` int(11) DEFAULT NULL COMMENT '商家用户类型，1商家负责人，2其他',
  `sex` int(2) DEFAULT NULL COMMENT '性别，0未知，1男2女',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `header_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `user_role_id` int(11) DEFAULT NULL COMMENT '用户角色id',
  `company_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属商家id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '修改操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1正常，0注销',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商家用户表';

/*Data for the table `tb_company_user` */

insert  into `tb_company_user`(`id`,`user_code`,`nick_name`,`real_name`,`phone`,`type`,`sex`,`birthday`,`age`,`header_img`,`user_role_id`,`company_id`,`add_time`,`add_user_id`,`opt_time`,`opt_user_id`,`state`,`is_delete`) values ('CU10001','CU403734033','冰雪之华','王昭君','18260631811',1,2,NULL,20,NULL,NULL,'C10001','2018-10-31 19:25:31',NULL,'2018-10-31 19:25:31',NULL,1,0),('CU10002','CU1509895858','纽约教父','黄忠','18260631814',2,1,NULL,18,NULL,NULL,'C10001','2018-11-01 19:00:57','CU10001','2018-11-01 19:00:57','CU10001',1,0);

/*Table structure for table `tb_consumer` */

DROP TABLE IF EXISTS `tb_consumer`;

CREATE TABLE `tb_consumer` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键，C开头',
  `consumer_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消费者编号',
  `nick_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称，可用作登录名',
  `real_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号，可用作登录名',
  `password` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录密码，加密',
  `sex` int(1) DEFAULT NULL COMMENT '性别，0未知，1男，2女',
  `header_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `qrcode_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '二维码',
  `bonus_points` int(11) DEFAULT NULL COMMENT '积分值',
  `experience_value` int(11) DEFAULT NULL COMMENT '经验值',
  `member_level_id` int(11) DEFAULT NULL COMMENT '会员等级',
  `personal_profile` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '个人简介',
  `last_login_ip` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最后一次登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登陆时间',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '修改操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1正常，0注销',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除，1是0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消费者用户表';

/*Data for the table `tb_consumer` */

/*Table structure for table `tb_consumer_cart` */

DROP TABLE IF EXISTS `tb_consumer_cart`;

CREATE TABLE `tb_consumer_cart` (
  `cart_code` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '购物车编号',
  `spu_id` int(11) DEFAULT NULL COMMENT '关联货品id',
  `sku_id` int(11) DEFAULT NULL COMMENT '关联商品id',
  `ammount` int(11) DEFAULT NULL COMMENT '商品数量',
  `consumer_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消费者Id',
  `store_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺Id',
  `company_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '商家id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`cart_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='购物车';

/*Data for the table `tb_consumer_cart` */

/*Table structure for table `tb_consumer_extend` */

DROP TABLE IF EXISTS `tb_consumer_extend`;

CREATE TABLE `tb_consumer_extend` (
  `consumer_id` int(11) NOT NULL COMMENT '主键，关联消费者主表',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `idcard` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证号',
  `idcard_img_fore` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证正面',
  `idcard_img_back` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证反面',
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `address_province` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '居住地',
  `address_city` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '居住地',
  `address_distinct` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '居住地',
  `address_detail` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '居住地',
  `recommender_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '推荐人',
  `company_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属商家',
  PRIMARY KEY (`consumer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消费者用户扩展表';

/*Data for the table `tb_consumer_extend` */

/*Table structure for table `tb_consumer_order` */

DROP TABLE IF EXISTS `tb_consumer_order`;

CREATE TABLE `tb_consumer_order` (
  `order_code` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '订单编号',
  `order_group_code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属订单组编号',
  `order_type` int(11) DEFAULT NULL COMMENT '订单类型，1普通单，2拆单，3砍价单，4拼单，5团购单',
  `amount` int(11) DEFAULT NULL COMMENT '订单商品总数',
  `total_price` decimal(5,2) DEFAULT NULL COMMENT '总金额',
  `consumer_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关联消费者id',
  `store_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关联店铺Id',
  `address_id` int(11) DEFAULT NULL COMMENT '关联收货地址Id',
  `address_json` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '冗余收货地址数据',
  `create_time` datetime DEFAULT NULL COMMENT '成单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `deliver_time` datetime DEFAULT NULL COMMENT '发货时间',
  `complete_time` datetime DEFAULT NULL COMMENT '完成时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `remarks` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT NULL COMMENT '订单状态',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`order_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消费者订单表';

/*Data for the table `tb_consumer_order` */

/*Table structure for table `tb_consumer_order_group` */

DROP TABLE IF EXISTS `tb_consumer_order_group`;

CREATE TABLE `tb_consumer_order_group` (
  `order_group_code` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '订单组编号',
  `amount` int(11) DEFAULT NULL COMMENT '订单组商品总数',
  `total_price` decimal(5,2) DEFAULT NULL COMMENT '订单组总金额',
  `consumer_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '消费者编号',
  `create_time` datetime DEFAULT NULL COMMENT '下单时间',
  `state` int(11) DEFAULT NULL COMMENT '订单组状态',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`order_group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='消费者订单组表';

/*Data for the table `tb_consumer_order_group` */

/*Table structure for table `tb_consumer_order_item` */

DROP TABLE IF EXISTS `tb_consumer_order_item`;

CREATE TABLE `tb_consumer_order_item` (
  `order_item_code` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '订单条目编号',
  `order_code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属订单编号',
  `spu_id` int(11) DEFAULT NULL COMMENT '货品spu编号',
  `sku_id` int(11) DEFAULT NULL COMMENT '商品sku编号',
  `sku_detail_json` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '冗余商品信息',
  `actual_price` decimal(5,2) DEFAULT NULL COMMENT '实际订单价格',
  `amount` int(11) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(5,2) DEFAULT NULL COMMENT '总价',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`order_item_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单项表';

/*Data for the table `tb_consumer_order_item` */

/*Table structure for table `tb_fore_category` */

DROP TABLE IF EXISTS `tb_fore_category`;

CREATE TABLE `tb_fore_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类名称',
  `type` int(11) DEFAULT NULL COMMENT '分类类型，1平台，2商家',
  `order_num` int(11) DEFAULT NULL COMMENT '同级分类中的排序值',
  `remarks` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `dept_num` int(11) DEFAULT NULL COMMENT '分类深度',
  `product_model_id` int(11) DEFAULT NULL COMMENT '所属产品模型',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '修改操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除，1是0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='前端分类表';

/*Data for the table `tb_fore_category` */

/*Table structure for table `tb_fore_category_store_ref` */

DROP TABLE IF EXISTS `tb_fore_category_store_ref`;

CREATE TABLE `tb_fore_category_store_ref` (
  `fore_category_id` int(11) DEFAULT NULL COMMENT '前端分类id',
  `store_id` int(11) DEFAULT NULL COMMENT '关联店铺id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='前端分类与店铺关联表';

/*Data for the table `tb_fore_category_store_ref` */

/*Table structure for table `tb_goods_banner` */

DROP TABLE IF EXISTS `tb_goods_banner`;

CREATE TABLE `tb_goods_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '轮播图标题',
  `url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片路径',
  `order_num` int(11) DEFAULT NULL COMMENT '排序值',
  `remarks` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `spu_id` int(11) DEFAULT NULL COMMENT '货品spu id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='货品轮播图表';

/*Data for the table `tb_goods_banner` */

/*Table structure for table `tb_goods_brand` */

DROP TABLE IF EXISTS `tb_goods_brand`;

CREATE TABLE `tb_goods_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌名称',
  `logo_url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '品牌logo',
  `type` int(11) DEFAULT NULL COMMENT '品牌类型，1平台， 2商家',
  `company_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属商家id',
  `remarks` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `order_num` int(11) DEFAULT NULL COMMENT '排序值',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级品牌',
  `is_hot` int(1) DEFAULT NULL COMMENT '是否热门',
  `dept_num` int(11) DEFAULT NULL COMMENT '深度值',
  `back_category_id` int(11) DEFAULT NULL COMMENT '所属分类id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='货品轮播图表';

/*Data for the table `tb_goods_brand` */

/*Table structure for table `tb_goods_detail` */

DROP TABLE IF EXISTS `tb_goods_detail`;

CREATE TABLE `tb_goods_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '细节标题',
  `content` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '细节内容',
  `url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '细节图',
  `order_num` int(11) DEFAULT NULL COMMENT '排序值',
  `remarks` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `spu_id` int(11) DEFAULT NULL COMMENT '所属货品Id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='货品细节表';

/*Data for the table `tb_goods_detail` */

/*Table structure for table `tb_goods_model` */

DROP TABLE IF EXISTS `tb_goods_model`;

CREATE TABLE `tb_goods_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模型编号',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '模型名',
  `remarks` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='货品模型表';

/*Data for the table `tb_goods_model` */

/*Table structure for table `tb_goods_param` */

DROP TABLE IF EXISTS `tb_goods_param`;

CREATE TABLE `tb_goods_param` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数名',
  `value` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '参数值',
  `order_num` int(11) DEFAULT NULL COMMENT '排序值',
  `remarks` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `spu_id` int(11) DEFAULT NULL COMMENT '所属货品id',
  `is_tag` int(1) DEFAULT NULL COMMENT '是否是吊牌标签',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='货品参数表';

/*Data for the table `tb_goods_param` */

/*Table structure for table `tb_goods_unit` */

DROP TABLE IF EXISTS `tb_goods_unit`;

CREATE TABLE `tb_goods_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '货品名',
  `spu_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '货品spu编号',
  `logo_url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '货品logo',
  `remarks` varbinary(1000) DEFAULT NULL COMMENT '备注',
  `min_price` decimal(10,2) DEFAULT NULL COMMENT '最小价格',
  `max_price` decimal(10,2) DEFAULT NULL COMMENT '最大价格',
  `sales_volume` int(11) DEFAULT NULL COMMENT '销量',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `click_num` int(11) DEFAULT NULL COMMENT '点击量',
  `is_hot` int(1) DEFAULT NULL COMMENT '是否热门',
  `is_new` int(1) DEFAULT NULL COMMENT '是否新品',
  `brand_id` int(11) DEFAULT NULL COMMENT '所属品牌id',
  `back_category_id` int(11) DEFAULT NULL COMMENT '所属分类id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，0待审核，1正常上架中，-1已下架',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='货品表';

/*Data for the table `tb_goods_unit` */

/*Table structure for table `tb_member_level` */

DROP TABLE IF EXISTS `tb_member_level`;

CREATE TABLE `tb_member_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level_value` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '等级值',
  `level_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登记名称',
  `min_experience_value` int(11) DEFAULT NULL COMMENT '最小经验值',
  `max_experience_value` int(11) DEFAULT NULL COMMENT '最大经验值',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '修改操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除，1是0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='会员等级表';

/*Data for the table `tb_member_level` */

/*Table structure for table `tb_model_property_ref` */

DROP TABLE IF EXISTS `tb_model_property_ref`;

CREATE TABLE `tb_model_property_ref` (
  `product_model_id` int(11) NOT NULL COMMENT '产品模型id',
  `product_property_id` int(11) NOT NULL COMMENT '属性id',
  PRIMARY KEY (`product_model_id`,`product_property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='模型参数关联表';

/*Data for the table `tb_model_property_ref` */

/*Table structure for table `tb_product_price` */

DROP TABLE IF EXISTS `tb_product_price`;

CREATE TABLE `tb_product_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sku_id` int(11) DEFAULT NULL COMMENT '所属skuid',
  `price_name` decimal(5,2) DEFAULT NULL COMMENT '价格名',
  `price` decimal(5,2) DEFAULT NULL COMMENT '价格值',
  `remarks` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品价格表';

/*Data for the table `tb_product_price` */

/*Table structure for table `tb_product_property` */

DROP TABLE IF EXISTS `tb_product_property`;

CREATE TABLE `tb_product_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性名',
  `type` int(11) DEFAULT NULL COMMENT '类型，1规格属性，2基本属性',
  `remarks` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品属性表';

/*Data for the table `tb_product_property` */

/*Table structure for table `tb_product_property_value` */

DROP TABLE IF EXISTS `tb_product_property_value`;

CREATE TABLE `tb_product_property_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `property_id` int(11) DEFAULT NULL COMMENT '属性id',
  `property_value` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '属性值',
  `remarks` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1启用，0禁用',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='商品属性值表';

/*Data for the table `tb_product_property_value` */

/*Table structure for table `tb_product_unit` */

DROP TABLE IF EXISTS `tb_product_unit`;

CREATE TABLE `tb_product_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku产品名',
  `sku_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'sku编号',
  `logo_url` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '产品logo',
  `origin_price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `price` decimal(10,2) DEFAULT NULL COMMENT '售价',
  `sales_volume` int(11) DEFAULT NULL COMMENT '销量',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `remarks` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `property_json` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '规格属性json值',
  `spu_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属spu货品id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '操作人',
  `state` int(11) DEFAULT NULL COMMENT '业务状态，0待审核，1正常上架中，-1已下架',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='库存量单位sku';

/*Data for the table `tb_product_unit` */

/*Table structure for table `tb_receive_address` */

DROP TABLE IF EXISTS `tb_receive_address`;

CREATE TABLE `tb_receive_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `address_tag` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址标签',
  `receiver` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人姓名',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货人联系方式',
  `address_province` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货地址—省',
  `address_city` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货地址—市',
  `address_distinct` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货地址—区',
  `address_street` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货地址—街道',
  `address_detail` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '收货地址—详细',
  `is_default` int(1) DEFAULT NULL COMMENT '是否是默认',
  `consumer_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属消费者',
  `state` int(11) DEFAULT NULL COMMENT '状态，1正常',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='收货地址表';

/*Data for the table `tb_receive_address` */

/*Table structure for table `tb_sequence` */

DROP TABLE IF EXISTS `tb_sequence`;

CREATE TABLE `tb_sequence` (
  `NAME` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `current_value` int(11) NOT NULL,
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `tb_sequence` */

insert  into `tb_sequence`(`NAME`,`current_value`,`increment`) values ('company_id',10001,1),('company_user_id',10002,1),('store_id',10001,1),('store_user_id',10001,1),('system_user_id',10002,1);

/*Table structure for table `tb_store` */

DROP TABLE IF EXISTS `tb_store`;

CREATE TABLE `tb_store` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `store_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺编号',
  `store_name` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺名称',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人手机号',
  `real_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人真实姓名',
  `type` int(2) DEFAULT NULL COMMENT '门店类型，1商家，2自营',
  `idcard` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系人身份证',
  `idcard_img_fore` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证正面',
  `idcard_img_back` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '身份证反面',
  `email` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'qq',
  `wechat` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信',
  `store_logo` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺logo',
  `licence_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '营业执照',
  `licence_code` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '统一社会信用代码',
  `address_province` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '门店地址',
  `address_city` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '门店地址',
  `address_distinct` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '门店地址',
  `address_detail` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '门店地址',
  `store_profile` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '门店介绍',
  `business_scope` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '经营范围',
  `company_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属商家',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '修改操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改操作人',
  `state` int(11) DEFAULT NULL COMMENT '业务状态，0待审核，1正常，审核通过，-1审核不通过',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否已删除，1是，0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺表';

/*Data for the table `tb_store` */

insert  into `tb_store`(`id`,`store_code`,`store_name`,`phone`,`real_name`,`type`,`idcard`,`idcard_img_fore`,`idcard_img_back`,`email`,`qq`,`wechat`,`store_logo`,`licence_img`,`licence_code`,`address_province`,`address_city`,`address_distinct`,`address_detail`,`store_profile`,`business_scope`,`company_id`,`add_time`,`add_user_id`,`opt_time`,`opt_user_id`,`state`,`is_delete`) values ('S10001','S1220397756','煎饼铺','18260631812','鲁班',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'江苏省','南京','雨花台区','铁心桥',NULL,'煎饼',NULL,'2018-11-01 17:10:06','SU10001','2018-11-01 17:10:06','SU10001',0,0);

/*Table structure for table `tb_store_user` */

DROP TABLE IF EXISTS `tb_store_user`;

CREATE TABLE `tb_store_user` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `user_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户编号',
  `nick_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称，可用作登录验证',
  `real_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号，可用作登录验证',
  `type` int(11) DEFAULT NULL COMMENT '店铺用户类型，1店主，2其他',
  `sex` int(2) DEFAULT NULL COMMENT '性别，0未知，1男2女',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `header_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `user_role_id` int(11) DEFAULT NULL COMMENT '用户角色id',
  `store_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '所属店铺',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '修改操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1正常，0注销',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='店铺员工表';

/*Data for the table `tb_store_user` */

insert  into `tb_store_user`(`id`,`user_code`,`nick_name`,`real_name`,`phone`,`type`,`sex`,`birthday`,`age`,`header_img`,`user_role_id`,`store_id`,`add_time`,`add_user_id`,`opt_time`,`opt_user_id`,`state`,`is_delete`) values ('SU10001','SU498600154','智障','鲁班','18260631812',1,1,'2018-11-01 16:55:03',24,NULL,NULL,'S10001','2018-11-01 16:57:32',NULL,'2018-11-01 16:57:32',NULL,1,0);

/*Table structure for table `tb_system_user` */

DROP TABLE IF EXISTS `tb_system_user`;

CREATE TABLE `tb_system_user` (
  `id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `user_code` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户编号',
  `nick_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称，可用作登录验证',
  `real_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号，可用作登录验证',
  `type` int(11) DEFAULT NULL COMMENT '平台用户类型，1平台负责人，2其他',
  `sex` int(2) DEFAULT NULL COMMENT '性别，0未知，1男2女',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `header_img` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `user_role_id` int(11) DEFAULT NULL COMMENT '用户角色id',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `add_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '添加人',
  `opt_time` datetime DEFAULT NULL COMMENT '修改操作时间',
  `opt_user_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改操作人',
  `state` int(11) DEFAULT NULL COMMENT '状态，1正常，0注销',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台用户表';

/*Data for the table `tb_system_user` */

insert  into `tb_system_user`(`id`,`user_code`,`nick_name`,`real_name`,`phone`,`type`,`sex`,`birthday`,`age`,`header_img`,`user_role_id`,`add_time`,`add_user_id`,`opt_time`,`opt_user_id`,`state`,`is_delete`) values ('PU10001','9527443','演得太逼真','李袁','18260631810',1,1,NULL,27,NULL,NULL,'2018-10-10 15:00:00',NULL,'2018-10-10 15:00:00',NULL,NULL,NULL),('PU10002','PU1459234362','守望之月','芈月','18260631813',2,2,NULL,21,NULL,NULL,'2018-11-01 18:13:20','PU10001','2018-11-01 18:13:20','PU10001',1,0);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `user_id` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '用户编号',
  `nick_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称，可用作登录验证',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号，可用作登录验证',
  `password` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登录密码',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型，1平台人员，2商家人员，3店铺人员，和昵称、手机号作为唯一用户登录',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登陆时间',
  `last_login_ip` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '最后一次登录IP',
  `state` int(11) DEFAULT NULL COMMENT '状态，1正常，0注销',
  `is_delete` int(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='平台用户表';

/*Data for the table `tb_user` */

insert  into `tb_user`(`user_id`,`nick_name`,`phone`,`password`,`user_type`,`last_login_time`,`last_login_ip`,`state`,`is_delete`) values ('CU10001','冰雪之华','18260631811','e10adc3949ba59abbe56e057f20f883e',2,'2018-11-01 18:58:36','0:0:0:0:0:0:0:1',1,0),('CU10002','纽约教父','18260631814','e10adc3949ba59abbe56e057f20f883e',2,NULL,NULL,1,0),('PU10001','演得太逼真','18260631810','123456',1,'2018-11-01 18:09:37','0:0:0:0:0:0:0:1',1,0),('PU10002','守望之月','18260631813','e10adc3949ba59abbe56e057f20f883e',1,'2018-11-01 18:36:47','0:0:0:0:0:0:0:1',1,0),('SU10001','智障','18260631812','e10adc3949ba59abbe56e057f20f883e',3,'2018-11-01 17:03:04','0:0:0:0:0:0:0:1',1,0);

/* Function  structure for function  `currval` */

/*!50003 DROP FUNCTION IF EXISTS `currval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `currval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
     DECLARE VALUE INTEGER;
     SET VALUE = 0;
     SELECT current_value INTO VALUE
          FROM tb_sequence
          WHERE NAME = seq_name;
     RETURN VALUE;
END */$$
DELIMITER ;

/* Function  structure for function  `nextval` */

/*!50003 DROP FUNCTION IF EXISTS `nextval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `nextval`(seq_name VARCHAR(50)) RETURNS int(11)
    DETERMINISTIC
BEGIN
     UPDATE tb_sequence
          SET current_value = current_value + increment
          WHERE NAME = seq_name;
     RETURN currval(seq_name);
END */$$
DELIMITER ;

/* Function  structure for function  `setval` */

/*!50003 DROP FUNCTION IF EXISTS `setval` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`127.0.0.1` FUNCTION `setval`(seq_name VARCHAR(50), VALUE INTEGER) RETURNS int(11)
    DETERMINISTIC
BEGIN
     UPDATE tb_sequence
          SET current_value = VALUE
          WHERE NAME = seq_name;
     RETURN currval(seq_name);
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
