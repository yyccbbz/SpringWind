/*
Navicat MySQL Data Transfer

Source Server         : 116.62.134.242
Source Server Version : 50718
Source Host           : 116.62.134.242:3306
Source Database       : big_customer

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-05-17 18:12:14
*/

SET FOREIGN_KEY_CHECKS=0;

#功能模块--1--投资顾问表
DROP TABLE IF EXISTS `advisor`;
CREATE TABLE `advisor` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `serial_number` VARCHAR(20) DEFAULT NULL COMMENT '投顾编号',
  `job_title` VARCHAR(20) DEFAULT NULL COMMENT '投顾级别',
  `login_name` VARCHAR(20) DEFAULT NULL COMMENT '系统登录名',
  `actual_name` VARCHAR(20) DEFAULT NULL COMMENT '投顾姓名',
  `mobile_no` VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '电子邮箱',
  `is_valid` INT(4) NOT NULL DEFAULT 1 COMMENT '是否有效{1:有效,0:无效}',
  `is_leader` INT(4) DEFAULT NULL COMMENT '是否组长{0:否,1:是}',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注信息',
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='投资顾问表';

#功能模块--2--投资顾问团队表
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `team_name` VARCHAR(20) DEFAULT NULL COMMENT '团队名称',
  `team_leader_id` INT(20) DEFAULT NULL COMMENT '团队长ID{advisor.id}',
  `location` VARCHAR(20) DEFAULT NULL COMMENT '所在地',
  `remark` VARCHAR(200) DEFAULT NULL COMMENT '备注信息',
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='投资顾问团队表';

#功能模块--3--投顾团队关联表
DROP TABLE IF EXISTS `advisor_team`;
CREATE TABLE `advisor_team` (
  `id` INT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `aid` INT(20) DEFAULT NULL COMMENT '投资顾问id',
  `tid` INT(20) DEFAULT NULL COMMENT '团队id',
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='投顾团队关联表';



#统计报表--1--产品到期表 product_expires
DROP TABLE IF EXISTS `product_expires`;
CREATE TABLE `product_expires` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `mobile_no`  VARCHAR(20) NOT NULL COMMENT '客户手机号' ,
  `member_no`  VARCHAR(50) NOT NULL COMMENT '客户会员号' ,
  `user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '客户姓名' ,
  `advisor_id`  INT(6) NULL DEFAULT NULL COMMENT '投资顾问ID号' ,
  `advisor_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '投资顾问姓名' ,
  `is_performance_pool`  INT(6) NULL DEFAULT NULL COMMENT '是否业绩池：0：否，1：是' ,
  `product_id`  VARCHAR(32) NULL DEFAULT NULL COMMENT '产品ID号' ,
  `product_name`  VARCHAR(100) NULL DEFAULT NULL COMMENT '产品名称' ,
  `trans_amount`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '投资金额' ,
  `inception_date`  DATE NULL DEFAULT NULL COMMENT '定期产品成立日' ,
  `due_date`  DATE NULL DEFAULT NULL COMMENT '定期产品到期日' ,
  `limit_days`  INT(6) NULL DEFAULT NULL COMMENT '定期产品期限（天）' ,
  `limit_type`  INT(6) NULL DEFAULT NULL COMMENT '定期产品期限类型：0：新人专享，1:6个月，2:12个月' ,
  `product_rate`  VARCHAR(20) NULL DEFAULT NULL COMMENT '产品利率' ,
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  INDEX `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号索引',
  INDEX `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号索引',
  INDEX `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名索引',
  INDEX `index_advisor_id` (`advisor_id`) USING BTREE COMMENT '投资顾问ID号索引',
  INDEX `index_advisor_name` (`advisor_name`) USING BTREE COMMENT '投资顾问姓名索引',
  INDEX `index_due_date` (`due_date`) USING BTREE COMMENT '定期产品到期日索引',
  INDEX `index_limit_type` (`limit_type`) USING BTREE COMMENT '定期产品期限类型索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='产品到期表';


#统计报表--2--资产余额表 assets_balance
DROP TABLE IF EXISTS `assets_balance`;
CREATE TABLE `assets_balance` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `mobile_no`  VARCHAR(20) NOT NULL COMMENT '客户手机号' ,
  `member_no`  VARCHAR(50) NOT NULL COMMENT '客户会员号' ,
  `user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '客户姓名' ,
  `user_type`  INT(6) NULL DEFAULT NULL COMMENT '客户类别：1：上报，2：分配，3：未分配vip' ,
  `register_time`  DATETIME NULL DEFAULT NULL COMMENT '客户注册时间' ,
  `dingqi_aum`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '纯定期AUM' ,
  `huoqi_aum`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '活期AUM' ,
  `huobaoding_aum`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '活包定AUM' ,
  `secondmarket_aum`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '二级市场转让AUM' ,
  `trans_aum`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '持有理财AUM' ,
  `account_aum`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '账户余额AUM' ,
  `aum_date`  DATE NULL DEFAULT NULL COMMENT 'AUM更新日期' ,
  `is_performance_pool`  INT(6) NULL DEFAULT NULL COMMENT '是否业绩池：0：否，1：是' ,
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  INDEX `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号索引',
  INDEX `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号索引',
  INDEX `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名索引',
  INDEX `index_user_type` (`user_type`) USING BTREE COMMENT '客户类别索引',
  INDEX `index_trans_aum` (`trans_aum`) USING BTREE COMMENT '持有理财AUM索引',
  INDEX `index_is_performance_pool` (`is_performance_pool`) USING BTREE COMMENT '是否业绩池索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='资产余额表';


#统计报表--3--客户注册情况 customer_registry
DROP TABLE IF EXISTS `customer_registry`;
CREATE TABLE `customer_registry` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `mobile_no`  VARCHAR(20) NOT NULL COMMENT '客户手机号' ,
  `member_no`  VARCHAR(50) NOT NULL COMMENT '客户会员号' ,
  `user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '客户姓名' ,
  `is_register`  INT(6) NULL DEFAULT NULL COMMENT '是否注册：0：否，1：是' ,
  `register_time`  DATETIME NULL DEFAULT NULL COMMENT '客户注册时间' ,
  `t_mobile_no`  VARCHAR(20) NOT NULL COMMENT '推荐人手机号' ,
  `t_member_no`  VARCHAR(50) NOT NULL COMMENT '推荐人会员号' ,
  `t_user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '推荐人姓名' ,
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  INDEX `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号索引',
  INDEX `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号索引',
  INDEX `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名索引',
  INDEX `index_is_register` (`is_register`) USING BTREE COMMENT '是否注册索引',
  INDEX `index_t_mobile_no` (`t_mobile_no`) USING BTREE COMMENT '推荐人手机号索引',
  INDEX `index_t_member_no` (`t_member_no`) USING BTREE COMMENT '推荐人会员号索引',
  INDEX `index_t_user_name` (`t_user_name`) USING BTREE COMMENT '推荐人姓名索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='客户注册情况';


# 业绩报表--1--销售明细
DROP TABLE IF EXISTS `sales_details`;
CREATE TABLE `sales_details` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `mobile_no`  VARCHAR(20) NOT NULL COMMENT '客户手机号' ,
  `member_no`  VARCHAR(50) NOT NULL COMMENT '客户会员号' ,
  `user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '客户姓名' ,
  `advisor_id`  INT(6) NULL DEFAULT NULL COMMENT '投资顾问ID号' ,
  `advisor_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '投资顾问姓名' ,
  `product_id`  VARCHAR(32) NULL DEFAULT NULL COMMENT '产品ID号' ,
  `product_name`  VARCHAR(100) NULL DEFAULT NULL COMMENT '产品名称' ,
  `product_type`  INT(6) NULL DEFAULT NULL COMMENT '产品类型：1：定期，2：活期，3：活包定，4：转让' ,
  `product_rate`  VARCHAR(20) NULL DEFAULT NULL COMMENT '产品利率' ,
  `trans_amount`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '投资金额' ,
  `trans_time`  DATETIME NULL DEFAULT NULL COMMENT '投资时间' ,
  `inception_date`  DATE NULL DEFAULT NULL COMMENT '定期产品成立日' ,
  `due_date`  DATE NULL DEFAULT NULL COMMENT '定期产品到期日' ,
  `limit_days`  INT(6) NULL DEFAULT NULL COMMENT '定期产品期限（天）' ,
  `limit_type`  INT(6) NULL DEFAULT NULL COMMENT '定期产品期限类型：0：新人专享，1:6个月，2:12个月' ,
  `user_type`  INT(6) NULL DEFAULT NULL COMMENT '客户类别：1：上报，2：分配，3：未分配vip' ,
  `register_time`  DATETIME NULL DEFAULT NULL COMMENT '客户注册时间' ,
  `report_date`  DATE NULL DEFAULT NULL COMMENT '上报分配日期' ,
  `is_vipuser`  INT(6) NULL DEFAULT NULL COMMENT '是否vip：0：否，1：是' ,
  `vip_date`  DATE NULL DEFAULT NULL COMMENT '成为vip日期' ,
  `is_performance_pool`  INT(6) NULL DEFAULT NULL COMMENT '是否业绩池：0：否，1：是' ,
  `user_mark`  VARCHAR(10) NULL DEFAULT NULL COMMENT '用户标识：1、DKH000；2、DKH001' ,
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  INDEX `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号索引',
  INDEX `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号索引',
  INDEX `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名索引',
  INDEX `index_advisor_id` (`advisor_id`) USING BTREE COMMENT '投资顾问ID号索引',
  INDEX `index_advisor_name` (`advisor_name`) USING BTREE COMMENT '投资顾问姓名索引',
  INDEX `index_product_type` (`product_type`) USING BTREE COMMENT '产品类型索引',
  INDEX `index_trans_time` (`trans_time`) USING BTREE COMMENT '投资时间索引',
  INDEX `index_limit_type` (`limit_type`) USING BTREE COMMENT '定期产品期限类型索引',
  INDEX `index_user_type` (`user_type`) USING BTREE COMMENT '客户类别索引',
  INDEX `index_is_performance_pool` (`is_performance_pool`) USING BTREE COMMENT '是否业绩池索引',
  INDEX `index_is_user_mark` (`user_mark`) USING BTREE COMMENT '用户标识索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='销售明细';

# 业绩报表--2--获客信息
DROP TABLE IF EXISTS `get_information`;
CREATE TABLE `get_information` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `t_mobile_no`  VARCHAR(20) NOT NULL COMMENT '推荐人手机号' ,
  `t_member_no`  VARCHAR(50) NOT NULL COMMENT '推荐人会员号' ,
  `t_user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '推荐人姓名' ,
  `advisor_id`  INT(6) NULL DEFAULT NULL COMMENT '投资顾问ID号' ,
  `advisor_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '投资顾问姓名' ,
  `t_user_type`  INT(6) NULL DEFAULT NULL COMMENT '推荐人客户类别：1：上报，2：分配，3：未分配vip' ,
  `t_report_date`  DATE NULL DEFAULT NULL COMMENT '推荐人上报分配日期' ,
  `t_is_performance_pool`  INT(6) NULL DEFAULT NULL COMMENT '推荐人是否业绩池：0：否，1：是' ,
  `bt_mobile_no`  VARCHAR(20) NOT NULL COMMENT '被推荐人手机号' ,
  `bt_member_no`  VARCHAR(50) NOT NULL COMMENT '被推荐人会员号' ,
  `bt_user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '被推荐人姓名' ,
  `bt_register_time`  DATETIME NULL DEFAULT NULL COMMENT '被推荐人注册时间' ,
  `bt_trans_amount`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '被推荐人投资金额（不含活期和转让）' ,
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  INDEX `index_t_mobile_no` (`t_mobile_no`) USING BTREE COMMENT '推荐人手机号索引',
  INDEX `index_t_member_no` (`t_member_no`) USING BTREE COMMENT '推荐人会员号索引',
  INDEX `index_t_user_name` (`t_user_name`) USING BTREE COMMENT '推荐人姓名索引',
  INDEX `index_advisor_id` (`advisor_id`) USING BTREE COMMENT '投资顾问ID号索引',
  INDEX `index_advisor_name` (`advisor_name`) USING BTREE COMMENT '投资顾问姓名索引',
  INDEX `index_t_user_type` (`t_user_type`) USING BTREE COMMENT '推荐人客户类别索引',
  INDEX `index_t_is_performance_pool` (`t_is_performance_pool`) USING BTREE COMMENT '推荐人是否业绩池索引',
  INDEX `index_bt_mobile_no` (`bt_mobile_no`) USING BTREE COMMENT '被推荐人手机号索引',
  INDEX `index_bt_member_no` (`bt_member_no`) USING BTREE COMMENT '被推荐人会员号索引',
  INDEX `index_bt_user_name` (`bt_user_name`) USING BTREE COMMENT '被推荐人姓名索引',
  INDEX `index_bt_register_time` (`bt_register_time`) USING BTREE COMMENT '被推荐人注册时间索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='获客信息';

# 业绩报表--3--获客销售明细
DROP TABLE IF EXISTS `get_sales_details`;
CREATE TABLE `get_sales_details` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `t_mobile_no`  VARCHAR(20) NOT NULL COMMENT '推荐人手机号' ,
  `t_member_no`  VARCHAR(50) NOT NULL COMMENT '推荐人会员号' ,
  `t_user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '推荐人姓名' ,
  `advisor_id`  INT(6) NULL DEFAULT NULL COMMENT '投资顾问ID号' ,
  `advisor_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '投资顾问姓名' ,
  `t_user_type`  INT(6) NULL DEFAULT NULL COMMENT '推荐人客户类别：1：上报，2：分配，3：未分配vip' ,
  `t_report_date`  DATE NULL DEFAULT NULL COMMENT '推荐人上报分配日期' ,
  `t_is_performance_pool`  INT(6) NULL DEFAULT NULL COMMENT '推荐人是否业绩池：0：否，1：是' ,
  `bt_mobile_no`  VARCHAR(20) NOT NULL COMMENT '被推荐人手机号' ,
  `bt_member_no`  VARCHAR(50) NOT NULL COMMENT '被推荐人会员号' ,
  `bt_user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '被推荐人姓名' ,
  `bt_register_time`  DATETIME NULL DEFAULT NULL COMMENT '被推荐人注册时间' ,
  `product_id`  VARCHAR(32) NULL DEFAULT NULL COMMENT '产品ID号' ,
  `product_name`  VARCHAR(100) NULL DEFAULT NULL COMMENT '产品名称' ,
  `product_type`  INT(6) NULL DEFAULT NULL COMMENT '产品类型：1：定期，2：活期，3：活包定，4：转让' ,
  `product_rate`  VARCHAR(20) NULL DEFAULT NULL COMMENT '产品利率' ,
  `trans_amount`  DOUBLE(20,2) NULL DEFAULT NULL COMMENT '投资金额' ,
  `trans_time`  DATETIME NULL DEFAULT NULL COMMENT '投资时间' ,
  `inception_date`  DATE NULL DEFAULT NULL COMMENT '定期产品成立日' ,
  `due_date`  DATE NULL DEFAULT NULL COMMENT '定期产品到期日' ,
  `limit_days`  INT(6) NULL DEFAULT NULL COMMENT '定期产品期限（天）' ,
  `limit_type`  INT(6) NULL DEFAULT NULL COMMENT '定期产品期限类型：0：新人专享，1:6个月，2:12个月' ,
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  INDEX `index_t_mobile_no` (`t_mobile_no`) USING BTREE COMMENT '推荐人手机号索引',
  INDEX `index_t_member_no` (`t_member_no`) USING BTREE COMMENT '推荐人会员号索引',
  INDEX `index_t_user_name` (`t_user_name`) USING BTREE COMMENT '推荐人姓名索引',
  INDEX `index_advisor_id` (`advisor_id`) USING BTREE COMMENT '投资顾问ID号索引',
  INDEX `index_advisor_name` (`advisor_name`) USING BTREE COMMENT '投资顾问姓名索引',
  INDEX `index_t_user_type` (`t_user_type`) USING BTREE COMMENT '推荐人客户类别索引',
  INDEX `index_bt_mobile_no` (`bt_mobile_no`) USING BTREE COMMENT '被推荐人手机号索引',
  INDEX `index_bt_member_no` (`bt_member_no`) USING BTREE COMMENT '被推荐人会员号索引',
  INDEX `index_bt_user_name` (`bt_user_name`) USING BTREE COMMENT '被推荐人姓名索引',
  INDEX `index_bt_register_time` (`bt_register_time`) USING BTREE COMMENT '被推荐人注册时间索引',
  INDEX `index_product_type` (`product_type`) USING BTREE COMMENT '产品类型索引',
  INDEX `index_trans_time` (`trans_time`) USING BTREE COMMENT '投资时间索引',
  INDEX `index_limit_type` (`limit_type`) USING BTREE COMMENT '定期产品期限类型索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='获客销售明细';



# 客户名单--1--正式名单
DROP TABLE IF EXISTS `final_user`;
CREATE TABLE `final_user` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `mobile_no`  VARCHAR(20) NOT NULL COMMENT '客户手机号' ,
  `member_no`  VARCHAR(50) NOT NULL COMMENT '客户会员号' ,
  `user_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '客户姓名' ,
  `user_type`  INT(6) NULL DEFAULT NULL COMMENT '客户类别：1上报；2分配；3未分配vip' ,
  `report_date`  DATE NULL DEFAULT NULL COMMENT '上报日期' ,
  `register_time`  DATETIME NULL DEFAULT NULL COMMENT '注册时间' ,
  `is_vipuser`  INT(6) NULL DEFAULT NULL COMMENT '是否vip：1是；0否' ,
  `vip_date`  DATE NULL DEFAULT NULL COMMENT '成为vip日期' ,
  `advisor_id`  INT(6) NULL DEFAULT NULL COMMENT '投资顾问ID号' ,
  `advisor_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '投资顾问姓名' ,
  `user_mark`  VARCHAR(10) NULL DEFAULT NULL COMMENT '用户标识：1、DKH000；2、DKH001' ,
  `is_performance_pool`  INT(6) NULL DEFAULT NULL COMMENT '是否业绩池：1、是；0、否' ,
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号唯一索引',
  UNIQUE INDEX `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号唯一索引',
  INDEX `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名一般索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='正式名单';

INSERT INTO `big_customer`.`final_user` (`id`, `mobile_no`, `member_no`, `user_name`, `user_type`, `report_date`, `register_time`, `is_vipuser`, `vip_date`, `advisor_id`, `advisor_name`, `user_mark`, `is_performance_pool`, `create_time`, `update_time`) VALUES ('2', '47724480137', 'kTYAmXjant', 'QFTkD', '3', '2007-03-04', '2007-01-17 15:04:44', '0', '2007-01-22', '7081', 'sUQLUT', 'xVYL1N', '1', '2017-05-21 17:32:07', '2017-05-21 17:32:07');


# 客户名单--2--未分配的VIP名单
DROP TABLE IF EXISTS `unassigned_vip_user`;
CREATE TABLE `unassigned_vip_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile_no` VARCHAR(20) NOT NULL COMMENT '客户手机号',
  `member_no` VARCHAR(50) NOT NULL COMMENT '客户会员号',
  `user_name` VARCHAR(20) DEFAULT NULL COMMENT '客户姓名',
  `register_time` DATETIME DEFAULT NULL COMMENT '注册时间',
  `vip_date` DATE DEFAULT NULL COMMENT '成为vip日期',
  `vip_trans_dingqi` INT(20) DEFAULT NULL COMMENT '客户成为vip时的历史定期投资额',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号唯一索引',
  UNIQUE KEY `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号唯一索引',
  KEY `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='未分配的VIP名单';

# 客户名单--3--分配/上报名单导入
DROP TABLE IF EXISTS `assign_report_import_user`;
CREATE TABLE `assign_report_import_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile_no` VARCHAR(20) NOT NULL COMMENT '客户手机号',
  `user_name` VARCHAR(20) DEFAULT NULL COMMENT '客户姓名',
  `user_type`  INT(6) NULL DEFAULT NULL COMMENT '客户类别：1上报；2分配；3未分配vip' ,
  `report_date` DATE DEFAULT NULL COMMENT '上报日期',
  `advisor_name` VARCHAR(20) NOT NULL COMMENT '投资顾问姓名',
  `user_mark`  VARCHAR(10) NULL DEFAULT NULL COMMENT '用户标识：DKH000/DKH001',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号唯一索引',
  KEY `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名索引',
  KEY `index_user_type` (`user_type`) USING BTREE COMMENT '客户类型索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='分配/上报名单导入';

# 客户名单--4--业绩池名单导入
DROP TABLE IF EXISTS `performance_pool_import_user`;
CREATE TABLE `performance_pool_import_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile_no` VARCHAR(20) NOT NULL COMMENT '客户手机号',
  `user_name` VARCHAR(20) DEFAULT NULL COMMENT '客户姓名',
  `pfm_pool_date` DATE DEFAULT NULL COMMENT '纳入业绩池日期',
  `advisor_name` VARCHAR(20) NOT NULL COMMENT '投资顾问姓名',
  `user_mark`  VARCHAR(10) NULL DEFAULT NULL COMMENT '用户标识：DKH000/DKH001',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号唯一索引',
  KEY `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名索引',
  KEY `index_pfm_pool_date` (`pfm_pool_date`) USING BTREE COMMENT '纳入业绩池日期索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='业绩池名单导入';

# 客户名单--5--历史正式名单
DROP TABLE IF EXISTS `history_final_user`;
CREATE TABLE `history_final_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `month_id` VARCHAR(20) NOT NULL COMMENT '历史月份',
  `mobile_no` VARCHAR(20) NOT NULL COMMENT '客户手机号',
  `member_no`  VARCHAR(50) NOT NULL COMMENT '客户会员号' ,
  `user_name` VARCHAR(20) DEFAULT NULL COMMENT '客户姓名',
  `user_type` INT(6) DEFAULT NULL COMMENT '客户类别：1:上报,2:分配,3:未分配vip',
  `report_date` DATE DEFAULT NULL COMMENT '上报日期',
  `register_time` DATETIME DEFAULT NULL COMMENT '注册时间',
  `is_vipuser`  INT(6) NULL DEFAULT NULL COMMENT '是否vip：1是；0否' ,
  `vip_date`  DATE NULL DEFAULT NULL COMMENT '成为vip日期' ,
  `advisor_id`  INT(6) NULL DEFAULT NULL COMMENT '投资顾问ID号' ,
  `advisor_name`  VARCHAR(20) NULL DEFAULT NULL COMMENT '投资顾问姓名' ,
  `user_mark`  VARCHAR(10) NULL DEFAULT NULL COMMENT '用户标识：DKH000/DKH001' ,
  `is_performance_pool`  INT(6) NULL DEFAULT NULL COMMENT '是否业绩池：1、是；0、否' ,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_month_id` (`month_id`) USING BTREE COMMENT '历史月份索引',
  KEY `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号索引',
  KEY `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='历史正式名单';

# 客户名单--6--历史业绩池名单
DROP TABLE IF EXISTS `history_pfm_pool_user`;
CREATE TABLE `history_pfm_pool_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `month_id` VARCHAR(20) NOT NULL COMMENT '历史月份',
  `mobile_no` VARCHAR(20) NOT NULL COMMENT '客户手机号',
  `user_name` VARCHAR(20) DEFAULT NULL COMMENT '客户姓名',
  `pfm_pool_date` DATE DEFAULT NULL COMMENT '纳入业绩池日期',
  `advisor_name` VARCHAR(20) NOT NULL COMMENT '投资顾问姓名',
  `user_mark`  VARCHAR(10) NULL DEFAULT NULL COMMENT '用户标识：DKH000/DKH001',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `index_month_id` (`month_id`) USING BTREE COMMENT '历史月份索引',
  KEY `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='历史业绩池名单';


-- ----------------------------
-- Table structure for excel
-- ----------------------------
DROP TABLE IF EXISTS `excel`;
CREATE TABLE `excel` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `uid` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
  `excel_name` VARCHAR(100) DEFAULT NULL COMMENT 'Excel源文件名',
  `excel_real_name` VARCHAR(100) DEFAULT NULL COMMENT 'Excel服务器文件名',
  `excel_real_path` VARCHAR(200) DEFAULT NULL COMMENT 'Excel服务器路径',
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Excel上传文件';


-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `uid` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
  `content` VARCHAR(600) NOT NULL DEFAULT '' COMMENT '日志内容',
  `operation` VARCHAR(250) DEFAULT NULL COMMENT '用户操作',
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志表';



-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `id`  BIGINT(20) NOT NULL COMMENT '主键',
  `pid` BIGINT(20) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `title` VARCHAR(30) NOT NULL COMMENT '标题',
  `type` INT(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、功能',
  `state` INT(6) NOT NULL DEFAULT '0' COMMENT '状态 0、正常 1、禁用',
  `sort` INT(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` VARCHAR(100) NULL DEFAULT NULL COMMENT '地址',
  `perm_code` VARCHAR(30) NULL DEFAULT NULL COMMENT '权限编码',
  `icon` VARCHAR(30) NULL DEFAULT NULL COMMENT '图标',
  `description` VARCHAR(80) NULL NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';


INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('1', '0', '功能管理', '0', '0', '1', '/sys/', '1000', 'fa fa-magic', '功能管理');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('2', '0', '权限管理', '0', '0', '2', '/perm/', '2000', 'fa fa-eye', '权限管理');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('3', '0', '系统监控', '0', '0', '3', '/monitor/', '3000', 'fa fa-bar-chart-o', '系统监控');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('4', '0', '操作日志', '0', '0', '4', '/log/', '4000', 'fa fa-bug', '操作日志');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('5', '0', '客户名单', '0', '0', '5', '/cl/', '5000', 'fa fa-magic', '客户名单');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('6', '0', '业绩报表', '0', '0', '6', '/pr/', '6000', 'fa fa-magic', '业绩报表');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('7', '0', '业绩核算', '0', '0', '7', '/pa/', '7000', 'fa fa-magic', '业绩核算');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('8', '0', '统计报表', '0', '0', '8', '/sr/', '8000', 'fa fa-magic', '统计报表');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('9', '0', '功能模块', '0', '0', '9', '/other/', '9000', 'fa fa-magic', '功能模块');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('11', '1', '发送邮件测试', '0', '0', '1', '/sys/mail/send.html', '1001', null, '发送邮件测试');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('12', '1', 'Echarts 测试', '0', '0', '2', '/sys/echarts/map.html', '1002', null, '暂无2');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('21', '2', '用户管理', '0', '0', '1', '/perm/user/list.html', '2001', null, '用户管理');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('22', '2', '角色管理', '0', '0', '2', '/perm/role/list.html', '2002', null, '角色管理');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('23', '2', '权限管理', '0', '0', '3', '/perm/permission/list.html', '2003', null, '权限管理');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('31', '3', '警告列表', '0', '0', '1', 'abc.html', '3001', null, '警告列表');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('32', '3', '实时监控', '0', '0', '2', '/monitor/realTime.html', '3002', null, '实时监控');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('41', '4', '日志列表', '0', '0', '1', '/log/list.html', '4001', null, '日志列表');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('51', '5', '正式名单', '0', '0', '1', '/clientList/finalUser/list.html', '5001', '', '正式名单');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('52', '5', '未分配的vip名单', '0', '0', '2', '/clientList/unassignedVipUser/list.html', '5002', '', '未分配的vip名单');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('53', '5', '分配/上报名单导入', '0', '0', '3', '/clientList/importUser/list.html', '5003', '', '分配/上报名单导入');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('54', '5', '业绩池名单导入', '0', '0', '4', '/clientList/performancePoolUser/list.html', '5004', '', '业绩池名单导入');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('55', '5', '历史正式名单', '0', '0', '5', '/clientList/historyFinalUser/list.html', '5005', '', '历史正式名单');
INSERT INTO `big_customer`.`privilege` (`id`, `pid`, `title`, `type`, `state`, `sort`, `url`, `perm_code`, `icon`, `description`) VALUES ('56', '5', '历史业绩池名单', '0', '0', '6', '/clientList/historyPfmPoolUser/list.html', '5006', '', '历史业绩池名单');




-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `name` VARCHAR(30) NOT NULL COMMENT '角色',
  `sort` INT(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` VARCHAR(60) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

INSERT INTO `role` VALUES ('1', '管理员', '1', '系统管理员');
INSERT INTO `role` VALUES ('2', '普通会员', '2', '普通会员');
INSERT INTO `role` VALUES ('3', '超级管理员', '3', '拥有所有最高权限');

-- ----------------------------
-- Table structure for role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `rid` BIGINT(20) NOT NULL COMMENT '角色ID',
  `pid` BIGINT(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限表';

INSERT INTO `role_privilege` VALUES ('1', '1', '52');
INSERT INTO `role_privilege` VALUES ('2', '1', '51');
INSERT INTO `role_privilege` VALUES ('3', '1', '5');
INSERT INTO `role_privilege` VALUES ('4', '1', '41');
INSERT INTO `role_privilege` VALUES ('5', '1', '4');
INSERT INTO `role_privilege` VALUES ('6', '1', '32');
INSERT INTO `role_privilege` VALUES ('7', '1', '31');
INSERT INTO `role_privilege` VALUES ('8', '1', '3');
INSERT INTO `role_privilege` VALUES ('9', '1', '12');
INSERT INTO `role_privilege` VALUES ('10', '1', '11');
INSERT INTO `role_privilege` VALUES ('11', '1', '1');
INSERT INTO `role_privilege` VALUES ('12', '1', '23');
INSERT INTO `role_privilege` VALUES ('13', '1', '22');
INSERT INTO `role_privilege` VALUES ('14', '1', '21');
INSERT INTO `role_privilege` VALUES ('15', '1', '2');
INSERT INTO `role_privilege` VALUES ('16', '1', '54');
INSERT INTO `role_privilege` VALUES ('17', '1', '6');
INSERT INTO `role_privilege` VALUES ('18', '1', '7');
INSERT INTO `role_privilege` VALUES ('19', '1', '8');
INSERT INTO `role_privilege` VALUES ('20', '1', '53');


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `login_name` VARCHAR(30) NOT NULL COMMENT '登录名称',
  `password` VARCHAR(32) NOT NULL COMMENT '密码',
  `email` VARCHAR(60) NULL DEFAULT NULL COMMENT '邮箱',
  `type` INT(6) NOT NULL DEFAULT '0' COMMENT '0、普通用户 1、管理员 2、超级管理员',
  `status` INT(6) NOT NULL DEFAULT '1' COMMENT '0、禁用 1、正常',
  `create_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `last_time`  TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登录时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

INSERT INTO `user` VALUES ('1', 'admin', 'b9140469ac5d2a70d86a583e9095ad6f', null, '1', '1', '2016-04-17 18:52:26', '2016-04-17 18:52:26');
INSERT INTO `user` VALUES ('2', 'test', '9f15088a3377aabdfa846f25d56da925', 'test@evergrande.com', '0', '1', '2016-04-14 18:22:58', '2016-04-14 18:22:58');
INSERT INTO `user` VALUES ('3', 'abc', 'b9140469ac5d2a70d86a583e9095ad6f', null, '1', '1', '2016-04-17 00:00:00', '2016-04-17 00:00:00');
INSERT INTO `user` VALUES ('4', 'def', 'd227f9edb541169068b11221869a014a', null, '0', '1', '2016-04-14 00:00:00', '2016-04-14 00:00:00');
INSERT INTO `user` VALUES ('5', 'zhangsan', 'b9140469ac5d2a70d86a583e9095ad6f', null, '1', '1', '2016-04-17 00:00:00', '2016-04-17 00:00:00');
INSERT INTO `user` VALUES ('6', '李四', 'd227f9edb541169068b11221869a014a', null, '0', '1', '2016-04-14 00:00:00', '2016-04-14 00:00:00');
INSERT INTO `user` VALUES ('7', '王五', 'b9140469ac5d2a70d86a583e9095ad6f', null, '1', '1', '2016-04-17 00:00:00', '2016-04-17 00:00:00');
INSERT INTO `user` VALUES ('8', '赵六', 'd227f9edb541169068b11221869a014a', null, '0', '1', '2016-04-14 00:00:00', '2016-04-14 00:00:00');
INSERT INTO `user` VALUES ('9', '田七', 'fe4ff4db4fffb0b3833be935cecdbfb4', null, '0', '1', '2017-05-09 18:13:22', '2017-05-09 18:13:22');
INSERT INTO `user` VALUES ('10', '牛十', 'ce5c0abe9a0d564d5b1a6e5158ab7d09', null, '0', '1', '2017-05-09 18:13:39', '2017-05-09 18:13:39');
INSERT INTO `user` VALUES ('11', '十一', '755157e3aa1445489dddf0765784a01f', null, '0', '1', '2017-05-09 18:13:49', '2017-05-09 18:13:49');
INSERT INTO `user` VALUES ('12', '一打', '87548c2c1a3d638beae644522d813931', null, '0', '1', '2017-05-09 18:14:09', '2017-05-09 18:14:09');



-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id`  BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `uid` BIGINT(20) NOT NULL COMMENT '用户ID',
  `rid` BIGINT(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色表';

INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');

SET FOREIGN_KEY_CHECKS=1;
