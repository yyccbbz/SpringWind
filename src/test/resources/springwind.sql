/*
Navicat MySQL Data Transfer

Source Server         : mysql_5.6.36
Source Server Version : 50636
Source Host           : localhost:3306
Source Database       : springwind

Target Server Type    : MYSQL
Target Server Version : 50636
File Encoding         : 65001

Date: 2017-05-17 18:19:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for excel
-- ----------------------------
DROP TABLE IF EXISTS `excel`;
CREATE TABLE `excel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '当前用户ID',
  `excel_name` varchar(100) DEFAULT NULL COMMENT 'Excel源文件名',
  `excel_real_name` varchar(100) DEFAULT NULL COMMENT 'Excel服务器文件名',
  `excel_real_path` varchar(200) DEFAULT NULL COMMENT 'Excel服务器路径',
  `mtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Excel文件类';

-- ----------------------------
-- Records of excel
-- ----------------------------
INSERT INTO `excel` VALUES ('1', '0', '测试Excel上传.xlsx', 'Usgi83hymonDEuPPXNrstj.xlsx', '\\opt\\upload\\2017-04\\Usgi83hymonDEuPPXNrstj.xlsx', '2017-04-14 18:09:46', '2017-04-13 16:47:42');
INSERT INTO `excel` VALUES ('2', '0', '测试Excel上传.xlsx', '9msB5tbZJpuAvwtG9Y4t63.xlsx', '\\opt\\upload\\2017-04\\9msB5tbZJpuAvwtG9Y4t63.xlsx', '2017-04-14 18:09:49', '2017-04-13 16:57:02');
INSERT INTO `excel` VALUES ('3', '0', '测试Excel上传.xlsx', 'TNhps27QiYPi68RuDMcTpk.xlsx', '\\opt\\upload\\2017-04\\TNhps27QiYPi68RuDMcTpk.xlsx', '2017-04-14 18:09:50', '2017-04-13 17:00:33');
INSERT INTO `excel` VALUES ('4', '0', '测试Excel上传.xlsx', 'VsPD3CwcQ7Tqu9cvqSzNyU.xlsx', '\\opt\\upload\\2017-04\\VsPD3CwcQ7Tqu9cvqSzNyU.xlsx', '2017-04-14 18:09:52', '2017-04-14 11:12:55');
INSERT INTO `excel` VALUES ('5', '0', '测试Excel上传.xlsx', 'FxeWmBrrmXGZbpgkE4zQKP.xlsx', '\\opt\\upload\\2017-04\\FxeWmBrrmXGZbpgkE4zQKP.xlsx', '2017-04-14 18:09:56', '2017-04-14 15:34:52');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级ID',
  `title` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、功能',
  `state` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态 0、正常 1、禁用',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `permCode` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '权限编码',
  `icon` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `description` varchar(80) COLLATE utf8_bin NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('2', '0', '权限管理', '0', '0', '2', '/perm/', '2000', 'fa fa-eye', '权限管理');
INSERT INTO `permission` VALUES ('1', '0', '功能管理', '0', '0', '1', '/sys/', '1000', 'fa fa-magic', '功能管理');
INSERT INTO `permission` VALUES ('3', '0', '系统监控', '0', '0', '3', '/monitor/', '3000', 'fa fa-bar-chart-o', '系统监控');
INSERT INTO `permission` VALUES ('4', '0', '操作日志', '0', '0', '4', '/log/', '4000', 'fa fa-bug', '操作日志');
INSERT INTO `permission` VALUES ('11', '1', '发送邮件测试', '0', '0', '1', '/sys/mail/send.html', '1001', null, '发送邮件测试');
INSERT INTO `permission` VALUES ('12', '1', 'Echarts 测试', '0', '0', '2', '/sys/echarts/map.html', '1002', null, '暂无2');
INSERT INTO `permission` VALUES ('21', '2', '用户管理', '0', '0', '1', '/perm/user/list.html', '2001', null, '用户管理');
INSERT INTO `permission` VALUES ('22', '2', '角色管理', '0', '0', '2', '/perm/role/list.html', '2002', null, '角色管理');
INSERT INTO `permission` VALUES ('23', '2', '权限管理', '0', '0', '3', '/perm/permission/list.html', '2003', null, '权限管理');
INSERT INTO `permission` VALUES ('31', '3', '警告列表', '0', '0', '1', 'abc.html', '3001', null, '警告列表');
INSERT INTO `permission` VALUES ('32', '3', '实时监控', '0', '0', '2', '/monitor/realTime.html', '3002', null, '实时监控');
INSERT INTO `permission` VALUES ('41', '4', '日志列表', '0', '0', '1', '/log/list.html', '4001', null, '日志列表');
INSERT INTO `permission` VALUES ('5', '0', '客户名单', '0', '0', '5', '/cl/', '5000', 'fa fa-magic', '客户名单');
INSERT INTO `permission` VALUES ('6', '0', '统计报表', '0', '0', '6', '/sr/', '6000', 'fa fa-magic', '统计报表');
INSERT INTO `permission` VALUES ('7', '0', '业绩核算', '0', '0', '7', '/pa/', '7000', 'fa fa-magic', '业绩核算');
INSERT INTO `permission` VALUES ('8', '0', '业绩报表', '0', '0', '8', '/pr/', '8000', 'fa fa-magic', '业绩报表');
INSERT INTO `permission` VALUES ('51', '5', '外部拓展导入', '0', '0', '1', '/cl/extbak/list.html', '5001', '', '外部拓展导入');
INSERT INTO `permission` VALUES ('52', '5', '外部拓展', '0', '0', '2', '/cl/extbak/list.html', '5002', '', '外部拓展');
INSERT INTO `permission` VALUES ('53', '5', '平台待分配', '0', '0', '3', '/cl/extbak/list.html', '5003', '', '平台待分配');
INSERT INTO `permission` VALUES ('54', '5', '正式名单', '0', '0', '4', '/cl/extbak/list.html', '5004', '', '正式名单');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '角色',
  `sort` smallint(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` varchar(60) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '1', '系统管理员');
INSERT INTO `role` VALUES ('2', '普通会员', '2', '普通会员');
INSERT INTO `role` VALUES ('3', '超级管理员', '3', '拥有所有最高权限');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `pid` bigint(20) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('20', '1', '53');
INSERT INTO `role_permission` VALUES ('1', '1', '52');
INSERT INTO `role_permission` VALUES ('2', '1', '51');
INSERT INTO `role_permission` VALUES ('3', '1', '5');
INSERT INTO `role_permission` VALUES ('4', '1', '41');
INSERT INTO `role_permission` VALUES ('5', '1', '4');
INSERT INTO `role_permission` VALUES ('6', '1', '32');
INSERT INTO `role_permission` VALUES ('7', '1', '31');
INSERT INTO `role_permission` VALUES ('8', '1', '3');
INSERT INTO `role_permission` VALUES ('9', '1', '12');
INSERT INTO `role_permission` VALUES ('10', '1', '11');
INSERT INTO `role_permission` VALUES ('11', '1', '1');
INSERT INTO `role_permission` VALUES ('12', '1', '23');
INSERT INTO `role_permission` VALUES ('13', '1', '22');
INSERT INTO `role_permission` VALUES ('14', '1', '21');
INSERT INTO `role_permission` VALUES ('15', '1', '2');
INSERT INTO `role_permission` VALUES ('16', '1', '54');
INSERT INTO `role_permission` VALUES ('17', '1', '6');
INSERT INTO `role_permission` VALUES ('18', '1', '7');
INSERT INTO `role_permission` VALUES ('19', '1', '8');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) unsigned DEFAULT NULL COMMENT '用户ID',
  `content` varchar(600) NOT NULL DEFAULT '' COMMENT '日志内容',
  `operation` varchar(250) DEFAULT NULL COMMENT '用户操作',
  `crTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=852748128224387150 DEFAULT CHARSET=utf8 COMMENT='操作日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('423827573609332736', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=abc&loginName=abc&,[IP]:10.10.10.10', '登录', '2016-05-09 21:55:10');
INSERT INTO `sys_log` VALUES ('423827889272651776', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=abc&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-09 21:56:23');
INSERT INTO `sys_log` VALUES ('423828945075437568', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=acbbb&repassword=acbbb&loginName=acbbb&,[IP]:10.10.10.10', '登录', '2016-05-09 22:00:37');
INSERT INTO `sys_log` VALUES ('423828960116211712', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:insertSelective,[参数]:password=acbbb&repassword=acbbb&loginName=acbbb&,[IP]:10.10.10.10', '添加用户', '2016-05-09 22:00:40');
INSERT INTO `sys_log` VALUES ('423829494923526144', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=abc&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-09 22:02:48');
INSERT INTO `sys_log` VALUES ('423829536409387008', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=abc&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-09 22:02:58');
INSERT INTO `sys_log` VALUES ('423829555678019584', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-09 22:03:02');
INSERT INTO `sys_log` VALUES ('423829597029662720', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:deleteUser,[参数]:null,[IP]:10.10.10.10', '删除用户', '2016-05-09 22:03:12');
INSERT INTO `sys_log` VALUES ('423829597029662721', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:deleteUser,[参数]:null,[IP]:10.10.10.10', '删除用户', '2016-05-09 22:03:12');
INSERT INTO `sys_log` VALUES ('424204895713755136', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:10.10.10.10', '登录', '2016-05-10 22:54:30');
INSERT INTO `sys_log` VALUES ('424204946615828480', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=abc&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-10 22:54:42');
INSERT INTO `sys_log` VALUES ('424204971169284096', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-10 22:54:48');
INSERT INTO `sys_log` VALUES ('424212917953495040', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:10.10.10.10', '登录', '2016-05-10 23:26:23');
INSERT INTO `sys_log` VALUES ('424213509627183104', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:10.10.10.10', '登录', '2016-05-10 23:28:44');
INSERT INTO `sys_log` VALUES ('424213551029157888', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-10 23:28:54');
INSERT INTO `sys_log` VALUES ('424538597211766784', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=abc&loginName=abc&,[IP]:10.10.10.10', '登录', '2016-05-11 21:00:31');
INSERT INTO `sys_log` VALUES ('424538650328432640', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=abc&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-11 21:00:44');
INSERT INTO `sys_log` VALUES ('424538669534150656', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=abc&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-11 21:00:48');
INSERT INTO `sys_log` VALUES ('424538690912518144', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&loginName=admin&,[IP]:10.10.10.10', '登录', '2016-05-11 21:00:53');
INSERT INTO `sys_log` VALUES ('424538709661057024', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:10.10.10.10', '登录', '2016-05-11 21:00:58');
INSERT INTO `sys_log` VALUES ('424538725716852736', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:10.10.10.10', '登录', '2016-05-11 21:01:02');
INSERT INTO `sys_log` VALUES ('846265562365517824', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=c43033b9e2fd43e49c53e19c919199f9&captcha=k3ns&,[IP]:172.16.7.28', '登录', '2017-03-27 15:40:07');
INSERT INTO `sys_log` VALUES ('846296205082271744', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=3e7e6cb0792e426fa9ebeaae6ab4b936&password=123&captcha=b6po&loginName=admin&,[IP]:172.16.7.28', '登录', '2017-03-27 17:41:53');
INSERT INTO `sys_log` VALUES ('846299741992034304', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-03-27 17:55:57');
INSERT INTO `sys_log` VALUES ('846299815019061248', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-03-27 17:56:14');
INSERT INTO `sys_log` VALUES ('846302624384851968', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:172.16.7.28', '登录', '2017-03-27 18:07:24');
INSERT INTO `sys_log` VALUES ('846549552267829248', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=8076f36ce2a24d98bfbed51eb4d44c10&password=123&captcha=dds6&loginName=admin&rememberMe=on&,[IP]:172.16.7.28', '登录', '2017-03-28 10:28:36');
INSERT INTO `sys_log` VALUES ('846661107575799808', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=dd3e7bc7ff234d3b872bc616775c5f44&captcha=5e7d&,[IP]:172.16.7.28', '登录', '2017-03-28 17:51:53');
INSERT INTO `sys_log` VALUES ('846664305728405504', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:insert,[参数]:id=&loginName=zizou&password=123456&rid=管理员&,[IP]:172.16.7.28', '添加用户', '2017-03-28 18:04:35');
INSERT INTO `sys_log` VALUES ('847023480329289728', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=be8d0a8329b946fe9482b1e158287c9b&captcha=d458&,[IP]:172.16.7.28', '登录', '2017-03-29 17:51:49');
INSERT INTO `sys_log` VALUES ('849449811331641344', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=71089701064a43faa777cd2d94fd10e3&captcha=aBPP&rememberMe=on&,[IP]:172.16.7.28', '登录', '2017-04-05 10:33:12');
INSERT INTO `sys_log` VALUES ('849463809074597888', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=a5bcd26dedac4c7d8fbea18110fea7b9&captcha=gnm3&rememberMe=on&,[IP]:172.16.7.28', '登录', '2017-04-05 11:28:49');
INSERT INTO `sys_log` VALUES ('849464045738201088', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=test&password=123&ctoken=df4487a46a574061aa3c8f98a1aaf165&captcha=e9pk&,[IP]:172.16.7.28', '登录', '2017-04-05 11:29:45');
INSERT INTO `sys_log` VALUES ('849464273220472832', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:deleteUser,[参数]:null,[IP]:172.16.7.28', '删除用户', '2017-04-05 11:30:40');
INSERT INTO `sys_log` VALUES ('849464283534266368', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:deleteUser,[参数]:null,[IP]:172.16.7.28', '删除用户', '2017-04-05 11:30:42');
INSERT INTO `sys_log` VALUES ('849464294653366272', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:deleteUser,[参数]:null,[IP]:172.16.7.28', '删除用户', '2017-04-05 11:30:45');
INSERT INTO `sys_log` VALUES ('849516186574065664', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=e0580d9472a34db9915d0165d0e38aaf&captcha=53sx&rememberMe=on&,[IP]:172.16.7.28', '登录', '2017-04-05 14:56:57');
INSERT INTO `sys_log` VALUES ('849516728742383616', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=dae563aa91e744e5b60cc98dfae80a03&captcha=8pxg&,[IP]:172.16.7.28', '登录', '2017-04-05 14:59:06');
INSERT INTO `sys_log` VALUES ('849516792483221504', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-05 14:59:21');
INSERT INTO `sys_log` VALUES ('849516837169336320', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-05 14:59:32');
INSERT INTO `sys_log` VALUES ('849517086545874944', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:172.16.7.28', '登录', '2017-04-05 15:00:31');
INSERT INTO `sys_log` VALUES ('849517120402296832', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-05 15:00:39');
INSERT INTO `sys_log` VALUES ('849517631314661376', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=2faa3ee5cd3f43a29cab53c604802451&captcha=3akg&,[IP]:172.16.7.28', '登录', '2017-04-05 15:02:41');
INSERT INTO `sys_log` VALUES ('849518185348665344', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=68204d6f369a411db393c9e030703413&captcha=ebao&,[IP]:172.16.7.28', '登录', '2017-04-05 15:04:53');
INSERT INTO `sys_log` VALUES ('849540577148104704', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=059b3e387f264469bc7cf62c30b2b7fc&captcha=4o2g&,[IP]:172.16.7.28', '登录', '2017-04-05 16:33:52');
INSERT INTO `sys_log` VALUES ('849540859416375296', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=dd70a8cb93204d9c9ce63f7ff4b972d9&captcha=p37e&,[IP]:172.16.7.28', '登录', '2017-04-05 16:34:59');
INSERT INTO `sys_log` VALUES ('849541226652856320', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=8bb37ad3182a4d898e52397fb03e6f6f&captcha=bmo5&,[IP]:172.16.7.28', '登录', '2017-04-05 16:36:27');
INSERT INTO `sys_log` VALUES ('849541580794720256', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:172.16.7.28', '登录', '2017-04-05 16:37:51');
INSERT INTO `sys_log` VALUES ('849543217500221440', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:172.16.7.28', '登录', '2017-04-05 16:44:21');
INSERT INTO `sys_log` VALUES ('849544224904966144', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=5c0b28e177b1414f8b8f034b98bdfb40&captcha=3s5k&,[IP]:172.16.7.28', '登录', '2017-04-05 16:48:22');
INSERT INTO `sys_log` VALUES ('849545795172691968', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:password=123&,[IP]:172.16.7.28', '登录', '2017-04-05 16:54:36');
INSERT INTO `sys_log` VALUES ('849545988685295616', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=4f5aa3633990436bb9fe516f1127c84e&captcha=w98b&,[IP]:172.16.7.28', '登录', '2017-04-05 16:55:22');
INSERT INTO `sys_log` VALUES ('849546264381091840', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=f43123efd9c24102923580aa1a957374&captcha=maa2&rememberMe=on&,[IP]:172.16.7.28', '登录', '2017-04-05 16:56:28');
INSERT INTO `sys_log` VALUES ('849546653142740992', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-05 16:58:01');
INSERT INTO `sys_log` VALUES ('849546662609285120', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-05 16:58:03');
INSERT INTO `sys_log` VALUES ('849546726727610368', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-05 16:58:18');
INSERT INTO `sys_log` VALUES ('849546755794137088', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-05 16:58:25');
INSERT INTO `sys_log` VALUES ('849558500671614976', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=a0e5494e66b9452798281de9b3244aa4&captcha=m6a5&,[IP]:172.16.7.28', '登录', '2017-04-05 17:45:05');
INSERT INTO `sys_log` VALUES ('849822535916740608', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=ce8bcdd6a48b4c38a20d5dc5b2f1e1b8&captcha=agn8&,[IP]:172.16.7.28', '登录', '2017-04-06 11:14:16');
INSERT INTO `sys_log` VALUES ('849825302366412800', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=cb08db00ca9e45feab16ed168b3bd1af&captcha=s67K&,[IP]:172.16.7.28', '登录', '2017-04-06 11:25:16');
INSERT INTO `sys_log` VALUES ('849844379671674880', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=7727416a4c06473d85071f2d32c3561f&captcha=8bo7&,[IP]:172.16.7.28', '登录', '2017-04-06 12:41:04');
INSERT INTO `sys_log` VALUES ('849845551878971392', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=cd0a7523c9e7462c963ccb899e9096fd&captcha=8o7w&,[IP]:172.16.7.28', '登录', '2017-04-06 12:45:43');
INSERT INTO `sys_log` VALUES ('849847955902959616', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=81d8d0a3746549bb9f587ccf4999495e&captcha=ww66&rememberMe=on&,[IP]:172.16.7.28', '登录', '2017-04-06 12:55:17');
INSERT INTO `sys_log` VALUES ('849856524790534144', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=294b08e9d90b4fd08204525a0d935248&captcha=gb55&,[IP]:172.16.7.28', '登录', '2017-04-06 13:29:20');
INSERT INTO `sys_log` VALUES ('849910571757219840', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=fbafbe218a3342498e24301dd8403c54&captcha=8aa2&,[IP]:172.16.7.28', '登录', '2017-04-06 17:04:05');
INSERT INTO `sys_log` VALUES ('850170856178987008', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=0e2bcc454f3b4e698cae43e7cc149baa&captcha=24d8&,[IP]:172.16.7.28', '登录', '2017-04-07 10:18:22');
INSERT INTO `sys_log` VALUES ('850196880019189760', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491537673025&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 12:01:47');
INSERT INTO `sys_log` VALUES ('850197005349187584', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=a&_=1491537673026&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 12:02:17');
INSERT INTO `sys_log` VALUES ('850198384859967488', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=123&_=1491537673027&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 12:07:46');
INSERT INTO `sys_log` VALUES ('850238943553155072', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491547718908&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 14:48:55');
INSERT INTO `sys_log` VALUES ('850239570375110656', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491547855004&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 14:51:25');
INSERT INTO `sys_log` VALUES ('850241784116178944', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491548401651&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 15:00:13');
INSERT INTO `sys_log` VALUES ('850242144016822272', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491548498371&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 15:01:39');
INSERT INTO `sys_log` VALUES ('850242223255613440', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491548517296&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 15:01:57');
INSERT INTO `sys_log` VALUES ('850255322578710528', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491551640318&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 15:54:01');
INSERT INTO `sys_log` VALUES ('850287602328170496', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559336475&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:02:17');
INSERT INTO `sys_log` VALUES ('850288544322711552', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559561106&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:01');
INSERT INTO `sys_log` VALUES ('850288548651233280', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559562154&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:02');
INSERT INTO `sys_log` VALUES ('850288550417035264', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559562528&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:03');
INSERT INTO `sys_log` VALUES ('850288551784378368', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559562908&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:03');
INSERT INTO `sys_log` VALUES ('850288552900063232', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559563191&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:03');
INSERT INTO `sys_log` VALUES ('850288554149965824', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559563447&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:04');
INSERT INTO `sys_log` VALUES ('850288556368752640', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559564019&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:04');
INSERT INTO `sys_log` VALUES ('850288563792670720', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559565759&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:06');
INSERT INTO `sys_log` VALUES ('850288567613681664', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559566719&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:07');
INSERT INTO `sys_log` VALUES ('850288638455476224', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559583557&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:24');
INSERT INTO `sys_log` VALUES ('850288642469425152', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559584560&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:25');
INSERT INTO `sys_log` VALUES ('850288645665484800', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559585250&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:25');
INSERT INTO `sys_log` VALUES ('850288647234154496', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559585638&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:26');
INSERT INTO `sys_log` VALUES ('850288648593108992', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559586001&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:26');
INSERT INTO `sys_log` VALUES ('850288649553604608', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559586239&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:06:26');
INSERT INTO `sys_log` VALUES ('850288957516181504', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559659603&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:07:40');
INSERT INTO `sys_log` VALUES ('850289779654291456', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559855579&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:10:56');
INSERT INTO `sys_log` VALUES ('850289782430920704', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559856260&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:10:56');
INSERT INTO `sys_log` VALUES ('850289786700722176', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559857194&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:10:57');
INSERT INTO `sys_log` VALUES ('850289796003688448', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559859527&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:00');
INSERT INTO `sys_log` VALUES ('850289797417168896', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559859851&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:00');
INSERT INTO `sys_log` VALUES ('850289798755151872', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559860187&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:00');
INSERT INTO `sys_log` VALUES ('850289800147660800', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559860520&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:01');
INSERT INTO `sys_log` VALUES ('850289801619861504', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559860865&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:01');
INSERT INTO `sys_log` VALUES ('850289803297583104', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559861238&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:01');
INSERT INTO `sys_log` VALUES ('850289804945944576', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559861666&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:02');
INSERT INTO `sys_log` VALUES ('850289806485254144', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559862030&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:02');
INSERT INTO `sys_log` VALUES ('850289808339136512', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559862473&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:03');
INSERT INTO `sys_log` VALUES ('850289810159464448', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559862871&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:03');
INSERT INTO `sys_log` VALUES ('850289812453748736', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559863403&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:04');
INSERT INTO `sys_log` VALUES ('850289814798364672', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559864017&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:04');
INSERT INTO `sys_log` VALUES ('850289817210089472', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491559864582&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-07 18:11:05');
INSERT INTO `sys_log` VALUES ('851324454917124096', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=7712d31064eb4b25b89be7dc36580b6c&captcha=5774&,[IP]:172.16.7.28', '登录', '2017-04-10 14:42:21');
INSERT INTO `sys_log` VALUES ('851354747464069120', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491813763524&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 16:42:44');
INSERT INTO `sys_log` VALUES ('851369614518591488', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491817307956&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 17:41:48');
INSERT INTO `sys_log` VALUES ('851375088563462144', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491818613192&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 18:03:34');
INSERT INTO `sys_log` VALUES ('851381903091113984', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491820237992&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 18:30:38');
INSERT INTO `sys_log` VALUES ('851383910371762176', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491820716500&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 18:38:37');
INSERT INTO `sys_log` VALUES ('851385827743309824', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491821173800&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 18:46:14');
INSERT INTO `sys_log` VALUES ('851385890590760960', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491821188749&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 18:46:29');
INSERT INTO `sys_log` VALUES ('851385964062384128', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491821206167&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 18:46:46');
INSERT INTO `sys_log` VALUES ('851386277926346752', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491821280962&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 18:48:01');
INSERT INTO `sys_log` VALUES ('851386344758386688', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491821297066&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-10 18:48:17');
INSERT INTO `sys_log` VALUES ('851671681980276736', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123456&ctoken=eeddf98774eb4b9e8b734675806f6f36&captcha=35Ba&,[IP]:172.16.7.28', '登录', '2017-04-11 13:42:07');
INSERT INTO `sys_log` VALUES ('851671729048756224', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=bb50878eb938497c817344a00f50a44e&captcha=ssoe&,[IP]:172.16.7.28', '登录', '2017-04-11 13:42:18');
INSERT INTO `sys_log` VALUES ('851672231861919744', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491889457756&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-11 13:44:18');
INSERT INTO `sys_log` VALUES ('851681658610491392', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491891705283&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-11 14:21:46');
INSERT INTO `sys_log` VALUES ('851713146060120064', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=49a02bcf0945412a830023dbffda63c3&captcha=9533&rememberMe=on&,[IP]:172.16.7.28', '登录', '2017-04-11 16:26:53');
INSERT INTO `sys_log` VALUES ('851714174490877952', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491899456768&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-11 16:30:58');
INSERT INTO `sys_log` VALUES ('851715817127124992', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491899849258&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-11 16:37:30');
INSERT INTO `sys_log` VALUES ('851715869841137664', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491899861991&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-11 16:37:42');
INSERT INTO `sys_log` VALUES ('851999586279067648', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=e86aa816aa5248cc8519c2dc7bf0f78f&captcha=sb2p&,[IP]:172.16.7.28', '登录', '2017-04-12 11:25:05');
INSERT INTO `sys_log` VALUES ('851999644630224896', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491967519082&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 11:25:19');
INSERT INTO `sys_log` VALUES ('852005930247753728', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491969017517&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 11:50:18');
INSERT INTO `sys_log` VALUES ('852025170036101120', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491973604107&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 13:06:45');
INSERT INTO `sys_log` VALUES ('852089219109195776', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491988875067&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 17:21:16');
INSERT INTO `sys_log` VALUES ('852089230119243776', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491988877843&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 17:21:18');
INSERT INTO `sys_log` VALUES ('852090105768284160', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491989086559&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 17:24:47');
INSERT INTO `sys_log` VALUES ('852091229883699200', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491989354548&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 17:29:15');
INSERT INTO `sys_log` VALUES ('852091570410852352', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491989435844&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 17:30:36');
INSERT INTO `sys_log` VALUES ('852096901262184448', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491990706903&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 17:51:47');
INSERT INTO `sys_log` VALUES ('852097200613855232', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1491990778112&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-12 17:52:58');
INSERT INTO `sys_log` VALUES ('852357712211693568', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492052888743&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 11:08:09');
INSERT INTO `sys_log` VALUES ('852364507583922176', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492054509005&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 11:35:09');
INSERT INTO `sys_log` VALUES ('852367548592807936', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492055234035&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 11:47:14');
INSERT INTO `sys_log` VALUES ('852369323387379712', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492055657423&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 11:54:18');
INSERT INTO `sys_log` VALUES ('852370557934624768', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492055951786&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 11:59:12');
INSERT INTO `sys_log` VALUES ('852379472600621056', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=7a54817dfb484433986ddbde86932035&captcha=mxp5&,[IP]:172.16.7.28', '登录', '2017-04-13 12:34:37');
INSERT INTO `sys_log` VALUES ('852379559032643584', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492058097738&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 12:34:58');
INSERT INTO `sys_log` VALUES ('852441688121094144', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492072910236&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 16:41:51');
INSERT INTO `sys_log` VALUES ('852444014240149504', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492073465111&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 16:51:05');
INSERT INTO `sys_log` VALUES ('852444024205815808', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492073467559&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 16:51:08');
INSERT INTO `sys_log` VALUES ('852444034523803648', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492073469163&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 16:51:10');
INSERT INTO `sys_log` VALUES ('852446627585142784', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492074088201&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-13 17:01:28');
INSERT INTO `sys_log` VALUES ('852721085193494528', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492139523765&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 11:12:04');
INSERT INTO `sys_log` VALUES ('852721684668588032', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492139666637&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 11:14:27');
INSERT INTO `sys_log` VALUES ('852748128224387072', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492145971552&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 12:59:32');
INSERT INTO `sys_log` VALUES ('852748128224387073', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492155228856&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 15:33:49');
INSERT INTO `sys_log` VALUES ('852748128224387074', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492155245890&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 15:34:07');
INSERT INTO `sys_log` VALUES ('852748128224387075', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492156075078&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 15:47:55');
INSERT INTO `sys_log` VALUES ('852748128224387076', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492157301515&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 16:08:22');
INSERT INTO `sys_log` VALUES ('852748128224387077', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492157592228&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 16:13:13');
INSERT INTO `sys_log` VALUES ('852748128224387078', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492157615116&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-14 16:13:36');
INSERT INTO `sys_log` VALUES ('852748128224387079', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=1e9ad11ca0a64de381f83231e0c60f9d&captcha=7s5w&,[IP]:172.16.7.28', '登录', '2017-04-17 10:51:20');
INSERT INTO `sys_log` VALUES ('852748128224387080', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=d4fb465ca6884349ad1ed5baf9460729&captcha=7e39&,[IP]:172.16.7.28', '登录', '2017-04-17 12:12:45');
INSERT INTO `sys_log` VALUES ('852748128224387081', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1492402368627&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-17 12:12:49');
INSERT INTO `sys_log` VALUES ('852748128224387082', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=7aa6f0a38b5d46d19a40fa40ddee3896&captcha=p72s&rememberMe=on&,[IP]:172.16.7.28', '登录', '2017-04-24 14:54:56');
INSERT INTO `sys_log` VALUES ('852748128224387083', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493016901616&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 14:55:02');
INSERT INTO `sys_log` VALUES ('852748128224387084', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493017142747&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 14:59:03');
INSERT INTO `sys_log` VALUES ('852748128224387085', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493017150950&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 14:59:11');
INSERT INTO `sys_log` VALUES ('852748128224387086', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=788b09b21e2047879f4841f90aae2165&captcha=ps63&,[IP]:172.16.7.28', '登录', '2017-04-24 17:59:19');
INSERT INTO `sys_log` VALUES ('852748128224387087', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493028029444&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 18:00:30');
INSERT INTO `sys_log` VALUES ('852748128224387088', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493028036784&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 18:00:37');
INSERT INTO `sys_log` VALUES ('852748128224387089', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493028046266&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 18:00:46');
INSERT INTO `sys_log` VALUES ('852748128224387090', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493028232753&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 18:03:53');
INSERT INTO `sys_log` VALUES ('852748128224387091', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493028333608&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 18:05:34');
INSERT INTO `sys_log` VALUES ('852748128224387092', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493028759606&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-24 18:12:40');
INSERT INTO `sys_log` VALUES ('852748128224387093', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:14:21');
INSERT INTO `sys_log` VALUES ('852748128224387094', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:14:28');
INSERT INTO `sys_log` VALUES ('852748128224387095', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:14:43');
INSERT INTO `sys_log` VALUES ('852748128224387096', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:14:47');
INSERT INTO `sys_log` VALUES ('852748128224387097', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:14:55');
INSERT INTO `sys_log` VALUES ('852748128224387098', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:15:13');
INSERT INTO `sys_log` VALUES ('852748128224387099', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=1&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:36:39');
INSERT INTO `sys_log` VALUES ('852748128224387100', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:36:44');
INSERT INTO `sys_log` VALUES ('852748128224387101', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-24 18:42:54');
INSERT INTO `sys_log` VALUES ('852748128224387102', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=0cc0c317f83a4a15b7fdf957abe64f95&captcha=3mwe&,[IP]:172.16.7.28', '登录', '2017-04-25 11:18:36');
INSERT INTO `sys_log` VALUES ('852748128224387103', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:loginName=admin&password=123&ctoken=0cc0c317f83a4a15b7fdf957abe64f95&captcha=3mwe&,[IP]:172.16.7.28', '登录', '2017-04-25 11:18:38');
INSERT INTO `sys_log` VALUES ('852748128224387104', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493090321830&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-25 11:18:42');
INSERT INTO `sys_log` VALUES ('852748128224387105', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493090514835&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-25 11:21:56');
INSERT INTO `sys_log` VALUES ('852748128224387106', '1', '[类名]:com.baomidou.springwind.service.impl.RolePermissionServiceImpl,[方法]:selecPermissionIdsByRoleId,[参数]:roleId=2&,[IP]:172.16.7.28', '角色关联菜单查询', '2017-04-25 11:22:10');
INSERT INTO `sys_log` VALUES ('852748128224387107', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1493194011427&,[IP]:172.16.7.28', '分页(条件)查询用户列表', '2017-04-26 16:06:52');
INSERT INTO `sys_log` VALUES ('852748128224387108', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=b4f67eeaf42646c3b7cd8688cff20272&password=123&captcha=xx96&loginName=admin&,[IP]:172.16.102.178', '登录', '2017-05-09 18:11:17');
INSERT INTO `sys_log` VALUES ('852748128224387109', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494324705520&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:11:46');
INSERT INTO `sys_log` VALUES ('852748128224387110', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:insert,[参数]:password=123456&loginName=田七&id=&rid=普通会员&,[IP]:172.16.102.178', '添加用户', '2017-05-09 18:13:22');
INSERT INTO `sys_log` VALUES ('852748128224387111', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494324802415&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:13:23');
INSERT INTO `sys_log` VALUES ('852748128224387112', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:insert,[参数]:password=123456&loginName=牛十&id=&rid=普通会员&,[IP]:172.16.102.178', '添加用户', '2017-05-09 18:13:39');
INSERT INTO `sys_log` VALUES ('852748128224387113', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494324819537&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:13:40');
INSERT INTO `sys_log` VALUES ('852748128224387114', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:insert,[参数]:password=123456&loginName=十一&id=&rid=管理员&,[IP]:172.16.102.178', '添加用户', '2017-05-09 18:13:49');
INSERT INTO `sys_log` VALUES ('852748128224387115', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494324829666&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:13:50');
INSERT INTO `sys_log` VALUES ('852748128224387116', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=10&_search=&_=1494324829667&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:13:55');
INSERT INTO `sys_log` VALUES ('852748128224387117', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494324829668&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:13:57');
INSERT INTO `sys_log` VALUES ('852748128224387118', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:insert,[参数]:password=123456&loginName=一打&id=&rid=普通会员&,[IP]:172.16.102.178', '添加用户', '2017-05-09 18:14:09');
INSERT INTO `sys_log` VALUES ('852748128224387119', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494324849768&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:14:10');
INSERT INTO `sys_log` VALUES ('852748128224387120', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=10&_search=&_=1494324849769&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:14:19');
INSERT INTO `sys_log` VALUES ('852748128224387121', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494324849770&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:14:20');
INSERT INTO `sys_log` VALUES ('852748128224387122', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325158804&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:19:19');
INSERT INTO `sys_log` VALUES ('852748128224387123', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325158805&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:20:19');
INSERT INTO `sys_log` VALUES ('852748128224387124', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325158806&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:20:21');
INSERT INTO `sys_log` VALUES ('852748128224387125', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325158807&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:20:22');
INSERT INTO `sys_log` VALUES ('852748128224387126', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325429065&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:23:49');
INSERT INTO `sys_log` VALUES ('852748128224387127', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325734510&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:28:55');
INSERT INTO `sys_log` VALUES ('852748128224387128', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=10&_search=&_=1494325734511&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:29:01');
INSERT INTO `sys_log` VALUES ('852748128224387129', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325734512&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:29:03');
INSERT INTO `sys_log` VALUES ('852748128224387130', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=10&_search=&_=1494325734513&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:29:31');
INSERT INTO `sys_log` VALUES ('852748128224387131', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325734514&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:29:33');
INSERT INTO `sys_log` VALUES ('852748128224387132', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=10&_search=&_=1494325734515&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:29:37');
INSERT INTO `sys_log` VALUES ('852748128224387133', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325734516&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:29:39');
INSERT INTO `sys_log` VALUES ('852748128224387134', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325796441&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:29:57');
INSERT INTO `sys_log` VALUES ('852748128224387135', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325799561&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:30:00');
INSERT INTO `sys_log` VALUES ('852748128224387136', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325812981&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:30:13');
INSERT INTO `sys_log` VALUES ('852748128224387137', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=25&_index=0&_search=&_=1494325812982&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:30:27');
INSERT INTO `sys_log` VALUES ('852748128224387138', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325812983&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:30:32');
INSERT INTO `sys_log` VALUES ('852748128224387139', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494325845466&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-09 18:30:46');
INSERT INTO `sys_log` VALUES ('852748128224387140', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=34b2a355c84e49f9ae762590f46c0fb5&password=123&captcha=og8x&loginName=admin&,[IP]:172.16.102.178', '登录', '2017-05-16 17:08:26');
INSERT INTO `sys_log` VALUES ('852748128224387141', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494925761797&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-16 17:09:22');
INSERT INTO `sys_log` VALUES ('852748128224387142', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494925767314&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-16 17:09:27');
INSERT INTO `sys_log` VALUES ('852748128224387143', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494925814538&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-16 17:10:15');
INSERT INTO `sys_log` VALUES ('852748128224387144', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494925818324&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-16 17:10:18');
INSERT INTO `sys_log` VALUES ('852748128224387145', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494926048689&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-16 17:14:09');
INSERT INTO `sys_log` VALUES ('852748128224387146', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494926059921&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-16 17:14:20');
INSERT INTO `sys_log` VALUES ('852748128224387147', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494926065708&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-16 17:14:26');
INSERT INTO `sys_log` VALUES ('852748128224387148', null, '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectByLoginName,[参数]:ctoken=86f4d1e5d42343cb99904de69c6dcce3&password=123&captcha=oob2&loginName=admin&,[IP]:172.16.102.178', '登录', '2017-05-16 18:05:21');
INSERT INTO `sys_log` VALUES ('852748128224387149', '1', '[类名]:com.baomidou.springwind.service.impl.UserServiceImpl,[方法]:selectPageBySearch,[参数]:_size=10&_index=0&_search=&_=1494986380393&,[IP]:172.16.102.178', '分页(条件)查询用户列表', '2017-05-17 09:59:41');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `loginName` varchar(30) NOT NULL COMMENT '登录名称',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `type` smallint(6) NOT NULL DEFAULT '0' COMMENT '0、普通用户 1、管理员',
  `status` smallint(6) NOT NULL DEFAULT '1' COMMENT '0、禁用 1、正常',
  `crTime` datetime NOT NULL COMMENT '创建时间',
  `lastTime` datetime NOT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');
SET FOREIGN_KEY_CHECKS=1;
