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

# 客户名单---正式名单
DROP TABLE IF EXISTS `final_user`;
CREATE TABLE `final_user` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `mobile_no`  varchar(20) NOT NULL COMMENT '客户手机号' ,
  `member_no`  varchar(50) NOT NULL COMMENT '客户会员号' ,
  `user_name`  varchar(20) NULL DEFAULT NULL COMMENT '客户姓名' ,
  `user_type`  int(6) NULL DEFAULT NULL COMMENT '客户类别：1上报；2分配；3未分配vip' ,
  `report_date`  date NULL DEFAULT NULL COMMENT '上报日期' ,
  `register_time`  datetime NULL DEFAULT NULL COMMENT '注册时间' ,
  `is_vipuser`  int(6) NULL DEFAULT NULL COMMENT '是否vip：1是；0否' ,
  `vip_date`  date NULL DEFAULT NULL COMMENT '成为vip日期' ,
  `advisor_id`  int(6) NULL DEFAULT NULL COMMENT '投资顾问ID号' ,
  `advisor_name`  varchar(20) NULL DEFAULT NULL COMMENT '投资顾问姓名' ,
  `user_mark`  varchar(10) NULL DEFAULT NULL COMMENT '用户标识：1、DKH000；2、DKH001' ,
  `is_performance_pool`  int(6) NULL DEFAULT NULL COMMENT '是否业绩池：1、是；0、否' ,
  `create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `index_mobile_no` (`mobile_no`) USING BTREE COMMENT '客户手机号唯一索引',
  UNIQUE INDEX `index_member_no` (`member_no`) USING BTREE COMMENT '客户会员号唯一索引',
  INDEX `index_user_name` (`user_name`) USING BTREE COMMENT '客户姓名一般索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='正式名单'
;

INSERT INTO `big_customer`.`final_user` (`id`, `mobile_no`, `member_no`, `user_name`, `user_type`, `report_date`, `register_time`, `is_vipuser`, `vip_date`, `advisor_id`, `advisor_name`, `user_mark`, `is_performance_pool`, `create_time`, `update_time`) VALUES ('2', '47724480137', 'kTYAmXjant', 'QFTkD', '3', '2007-03-04', '2007-01-17 15:04:44', '0', '2007-01-22', '7081', 'sUQLUT', 'xVYL1N', '1', '2017-05-21 17:32:07', '2017-05-21 17:32:07');


-- ----------------------------
-- Table structure for excel
-- ----------------------------
DROP TABLE IF EXISTS `excel`;
CREATE TABLE `excel` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `excel_name` varchar(100) DEFAULT NULL COMMENT 'Excel源文件名',
  `excel_real_name` varchar(100) DEFAULT NULL COMMENT 'Excel服务器文件名',
  `excel_real_path` varchar(200) DEFAULT NULL COMMENT 'Excel服务器路径',
  `create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='Excel上传文件';


-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `uid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `content` varchar(600) NOT NULL DEFAULT '' COMMENT '日志内容',
  `operation` varchar(250) DEFAULT NULL COMMENT '用户操作',
  `create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志表';



-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `id`  bigint(20) NOT NULL COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `type` int(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、功能',
  `state` int(6) NOT NULL DEFAULT '0' COMMENT '状态 0、正常 1、禁用',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(100) NULL DEFAULT NULL COMMENT '地址',
  `perm_code` varchar(30) NULL DEFAULT NULL COMMENT '权限编码',
  `icon` varchar(30) NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(80) NULL NOT NULL COMMENT '描述',
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
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `name` varchar(30) NOT NULL COMMENT '角色',
  `sort` int(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` varchar(60) NOT NULL COMMENT '描述',
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
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `pid` bigint(20) NOT NULL COMMENT '权限ID',
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
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `login_name` varchar(30) NOT NULL COMMENT '登录名称',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(60) NULL DEFAULT NULL COMMENT '邮箱',
  `type` int(6) NOT NULL DEFAULT '0' COMMENT '0、普通用户 1、管理员 2、超级管理员',
  `status` int(6) NOT NULL DEFAULT '1' COMMENT '0、禁用 1、正常',
  `create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `last_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登录时间' ,
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
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色表';

INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');

SET FOREIGN_KEY_CHECKS=1;
