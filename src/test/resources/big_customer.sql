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

DROP TABLE IF EXISTS ``;
CREATE TABLE `NewTable` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
`mobile_no`  varchar(20) NOT NULL COMMENT '客户手机号' ,
`member_no`  varchar(50) NOT NULL COMMENT '客户会员号' ,
`user_name`  varchar(20) NULL DEFAULT NULL COMMENT '客户姓名' ,
`user_type`  int(4) NULL DEFAULT NULL COMMENT '客户类别：1上报；2分配；3未分配vip' ,
`report_date`  date NULL DEFAULT NULL COMMENT '上报日期' ,
`register_time`  datetime NULL DEFAULT NULL COMMENT '注册时间' ,
`is_vipuser`  int(4) NULL DEFAULT NULL COMMENT '是否vip：1是；0否' ,
`vip_date`  date NULL DEFAULT NULL COMMENT '成为vip日期' ,
`advisor_id`  int(4) NULL DEFAULT NULL COMMENT '投资顾问ID号' ,
`advisor_name`  varchar(20) NULL DEFAULT NULL COMMENT '投资顾问姓名' ,
`user_mark`  varchar(10) NULL DEFAULT NULL COMMENT '用户标识：1、DKH000；2、DKH001' ,
`is_performance_pool`  int(4) NULL DEFAULT NULL COMMENT '是否业绩池：1、是；0、否' ,
`create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号唯一索引',
UNIQUE INDEX `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号唯一索引',
INDEX `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名一般索引'
)
;





























SET FOREIGN_KEY_CHECKS=1;
