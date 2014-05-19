/*
Navicat MySQL Data Transfer

Source Server         : highSchool
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : highschool

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2014-05-03 02:04:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_class_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_class_type`;
CREATE TABLE `tb_class_type` (
  `id` varchar(36) NOT NULL default '',
  `class_type` varchar(40) default NULL,
  `study_fee` decimal(22,0) default NULL,
  `stay_fee` decimal(22,0) default NULL,
  `self_fee` decimal(22,0) default NULL,
  `sign_fee` decimal(22,0) default NULL,
  `score_fee` decimal(22,0) default NULL,
  `safety_fee` decimal(22,0) default NULL,
  `water_fee` decimal(22,0) default NULL,
  `count_fee` decimal(22,0) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_class_type
-- ----------------------------
INSERT INTO `tb_class_type` VALUES ('017bf6bb-918c-404a-a410-872578fa5f67', '艺术班', '20000', '1000', '100', '100', '100', '100', '100', '21500');
INSERT INTO `tb_class_type` VALUES ('4a0d7f10-210f-4cfd-b712-2bbc6a45aebb', '签约普通班', '18000', '2500', '500', '100', '100', '100', '200', '21500');
INSERT INTO `tb_class_type` VALUES ('4f6ef1dc-360d-43b9-8bc9-3be6ecf0469e', '签约小班', '35000', '2500', '500', '100', '100', '100', '200', '38500');

-- ----------------------------
-- Table structure for `tb_file`
-- ----------------------------
DROP TABLE IF EXISTS `tb_file`;
CREATE TABLE `tb_file` (
  `id` varchar(36) NOT NULL,
  `file_name` varchar(100) default NULL,
  `file_path` varchar(100) default NULL,
  `file_realname` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_file
-- ----------------------------
INSERT INTO `tb_file` VALUES ('07e066a9-347e-4d5b-bdb8-1d06458b54ff', '微云相册-使用指引.jpg', 'd:\\file\\cc120d81-8b82-4a8e-a5f5-ecbab91622de.jpg', 'cc120d81-8b82-4a8e-a5f5-ecbab91622de.jpg');
INSERT INTO `tb_file` VALUES ('2d0e44fd-9b1b-4a2f-9897-dbf401c5cbd5', '身份证号码验证.xls', 'd:\\file\\0c775a30-6bbf-4749-b281-68ddcdf2edff.xls', '0c775a30-6bbf-4749-b281-68ddcdf2edff.xls');
INSERT INTO `tb_file` VALUES ('307e3ddd-aa52-497c-bd6a-54e571569d7f', '学生信息输入自制模板v1.8.xls', 'd:\\file\\2ba27374-0270-45db-810e-2d5e25e081ff.xls', '2ba27374-0270-45db-810e-2d5e25e081ff.xls');
INSERT INTO `tb_file` VALUES ('5b181d2f-9912-47b9-a8e1-e3f2a4c3d720', 'subshopcc.jsp', 'd:\\file\\d14b613a-075a-40e5-8684-2e29c2339212.jsp', 'd14b613a-075a-40e5-8684-2e29c2339212.jsp');
INSERT INTO `tb_file` VALUES ('857c7ec7-e4e1-4b9d-85aa-701d0a2f096d', 'Wildlife.wmv', 'd:\\file\\62093b91-408b-451c-8396-8677f2d066eb.wmv', '62093b91-408b-451c-8396-8677f2d066eb.wmv');
INSERT INTO `tb_file` VALUES ('89edca1b-7f8e-4b23-8c78-4c6c85651819', '身份证号码验证.xls', 'd:\\file\\e865c207-cef7-4e61-af66-63f62834e306.xls', 'e865c207-cef7-4e61-af66-63f62834e306.xls');
INSERT INTO `tb_file` VALUES ('c7038867-a342-4f7b-9bf7-53bb9fb6b472', '学生信息输入自制模板v1.8.xls', 'd:\\file\\5cbd22f0-8767-4f10-8d31-55f30bb40eda.xls', '5cbd22f0-8767-4f10-8d31-55f30bb40eda.xls');
INSERT INTO `tb_file` VALUES ('db9ce34c-88b6-4839-a10b-14fe788be870', 'CRM-研发-彭正明.jpg', 'd:\\file\\video\\d5438f0e-d4f5-4cc7-968a-d7a1a31503cc.jpg', 'd5438f0e-d4f5-4cc7-968a-d7a1a31503cc.jpg');
INSERT INTO `tb_file` VALUES ('e7385082-641f-4ba4-a8fd-a2b926fa43cf', '学生信息输入自制模板v1.8.xls', 'd:\\file\\954fb494-177e-4d14-8961-8e785d785dc6.xls', '954fb494-177e-4d14-8961-8e785d785dc6.xls');
INSERT INTO `tb_file` VALUES ('e8d87ca1-72de-463c-b277-b51bb40b07ca', '学生信息输入自制模板v1.8.xls', 'd:\\file\\b05fa122-2878-432a-a5fb-33ce04a36ddd.xls', 'b05fa122-2878-432a-a5fb-33ce04a36ddd.xls');
INSERT INTO `tb_file` VALUES ('ed48b112-9cf6-4ca0-a067-ed8cd7d2152d', '学生信息输入自制模板v1.8.xls', 'd:\\file\\7a917a50-aa0b-40d1-8fd1-22a3df0461b4.xls', '7a917a50-aa0b-40d1-8fd1-22a3df0461b4.xls');
INSERT INTO `tb_file` VALUES ('f05cec09-08ac-4b56-af06-75d82a03775d', 'subshopcc.jsp', 'd:\\file\\49387ae7-7da9-49e0-9a36-bec929631e68.jsp', '49387ae7-7da9-49e0-9a36-bec929631e68.jsp');
INSERT INTO `tb_file` VALUES ('f2f38b04-3ca2-4e0b-b894-2e477002cb16', 'POSCenter.exe', 'd:\\file\\f3fa5979-3b23-45cd-a1c0-e906283de512.exe', 'f3fa5979-3b23-45cd-a1c0-e906283de512.exe');
INSERT INTO `tb_file` VALUES ('fe85fb9a-78cb-4c10-8adf-2b6794a82edd', '学生信息输入自制模板v1.8.xls', 'd:\\file\\7a917a50-aa0b-40d1-8fd1-22a3df0461b4.xls', '7a917a50-aa0b-40d1-8fd1-22a3df0461b4.xls');

-- ----------------------------
-- Table structure for `tb_finance`
-- ----------------------------
DROP TABLE IF EXISTS `tb_finance`;
CREATE TABLE `tb_finance` (
  `id` varchar(36) NOT NULL,
  `createdatetime` datetime default NULL,
  `name` varchar(10) default NULL,
  `id_num` varchar(30) default NULL,
  `class_type` varchar(36) default NULL,
  `studentId` varchar(36) default NULL,
  `study_fee` decimal(22,0) default NULL,
  `stay_fee` decimal(22,0) default NULL,
  `self_fee` decimal(22,0) default NULL,
  `sign_fee` decimal(22,0) default NULL,
  `score_fee` decimal(22,0) default NULL,
  `safety_fee` decimal(22,0) default NULL,
  `water_fee` decimal(22,0) default NULL,
  `preferentialFee` decimal(22,0) default NULL,
  `arrearFee` decimal(22,0) default NULL,
  `cashFee` decimal(22,0) default NULL,
  `transferFee` decimal(22,0) default NULL,
  `payAgainFee` decimal(22,0) default NULL,
  `refundFee` decimal(22,0) default NULL,
  `count_pay_fee` decimal(22,0) default NULL,
  `payee` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_atbixe4b0gwnmic7gewjxnkn2` (`payee`),
  KEY `finance_class_type` (`class_type`),
  CONSTRAINT `finance_class_type` FOREIGN KEY (`class_type`) REFERENCES `tb_class_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `tb_finance_ibfk_1` FOREIGN KEY (`payee`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_finance
-- ----------------------------
INSERT INTO `tb_finance` VALUES ('5506384a-a533-4b82-8ed0-071ed4283e2a', '2014-04-14 09:47:34', 'yryer ', '120224199001011234', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '100', '200', '300', '400', '500', '600', '700', '800', '900', '1000', '2000', '3000', '4000', '5000', '0');
INSERT INTO `tb_finance` VALUES ('569946ba-1ec3-473f-8061-75ec37e5da42', '2014-04-14 09:47:34', '李阳', '120224199001011235', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '101', '201', null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('57f7325a-4366-4296-9c89-3fcabef51f96', '2014-04-14 09:47:34', 'fdsdfdsa ', '120224199001011236', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '102', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('5a34bc88-0037-43b9-8e07-70b3a98c97c2', '2014-04-14 09:32:19', '庆华', '120224199001011237', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '103', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('61be71eb-ead7-4454-815f-415ffbe31104', '2014-04-14 09:47:34', null, null, '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '104', null, null, null, null, null, null, null, null, null, null, null, '400', '16100', '0');
INSERT INTO `tb_finance` VALUES ('6468522b-5401-4156-85f9-e3acc995c344', '2014-04-14 09:47:34', 'test1', '120224199001011238', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '105', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('721739b0-6205-4fdb-9063-f4a75365b876', '2014-04-14 09:17:24', '姓名', '120224199001011239', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '106', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('749371be-54b2-4639-bcd9-c413a29df99f', '2014-04-14 09:47:34', '1235', '120224199001011240', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '107', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('8049c853-d0e5-4d1b-8df2-92477abe315d', '2014-04-14 09:25:34', 'yryer', '120224199001011241', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '108', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('85fd86e9-ef27-468b-9599-0676d95042a3', '2014-04-14 09:47:34', 'retret3', '120224199001011242', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '109', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('8ba21b74-7f96-45ad-a4fc-be2e8be06547', '2014-04-14 09:32:36', 'wew', '120224199001011243', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '110', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('8c9ea4a5-adc0-45cb-88ea-eb64ddb9256f', '2014-04-14 09:47:34', 'we', '120224199001011244', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '111', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('8f668184-0d2e-47e5-b30f-07c4d35a1c5e', '2014-04-14 09:47:34', 'wewr', '120224199001011245', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '112', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('a5972ed4-89da-4343-8ac7-c3f50ee25970', '2014-04-14 09:32:55', 'dfg', '120224199001011246', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '113', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('b8a1b8d9-2973-466a-b3f2-e028f934a5d3', '2014-04-14 09:47:34', 'gggs', '120224199001011247', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '114', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('bfe074c8-c381-4cab-925f-c1e91fe53f6f', '2014-04-14 09:47:34', 'sfase', '120224199001011248', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '115', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('caae943b-dec8-4d2e-bc8e-912fdec18729', '2014-04-14 09:09:36', 'dftraq', '120224199001011249', '017bf6bb-918c-404a-a410-872578fa5f67', '57598cb3-2fc7-4b5c-be79-20edd81cad47', '116', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('d46617ac-9385-4e06-b2cc-88b7cf002a70', '2014-04-14 09:45:24', 'dtaq', '120224199001011250', '017bf6bb-918c-404a-a410-872578fa5f67', '57598cb3-2fc7-4b5c-be79-20edd81cad47', '117', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('dcab33ca-7f00-474b-b653-0a222fa12a14', '2014-04-14 09:15:05', 'yuy', '120224199001011251', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '118', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('e844ba08-f655-417d-a92c-ad44a2eeab31', '2014-04-14 09:18:06', 'poiu', '120224199001011252', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '119', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('ebe5a248-29c6-4b3b-99f2-cf8888822f8b', '2014-04-14 09:36:26', 'uiy', '120224199001011253', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '120', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('ed1cd364-882a-4a3d-ae0c-7232d9e3a379', '2014-04-14 09:47:34', 'kyj', '120224199001011254', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '121', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('efb2bcb5-0323-46d0-b66a-cbbf313e484c', '2014-04-14 09:47:34', 'mnl', '120224199001011255', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '122', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');
INSERT INTO `tb_finance` VALUES ('f3d978e7-282d-4861-8960-6c2099fbe385', '2014-04-14 09:47:34', 'nli', '120224199001011256', '017bf6bb-918c-404a-a410-872578fa5f67', 'e6cb23e1-0147-4716-96aa-997ad25d4dcc', '123', null, null, null, null, null, null, null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for `tb_id_file`
-- ----------------------------
DROP TABLE IF EXISTS `tb_id_file`;
CREATE TABLE `tb_id_file` (
  `id` varchar(36) NOT NULL,
  `file_name` varchar(100) default NULL,
  `file_path` varchar(100) default NULL,
  `file_realname` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_id_file
-- ----------------------------
INSERT INTO `tb_id_file` VALUES ('c6167836-49ea-4ab6-ad41-b37c3d3c94ec', 'CRM-研发-彭正明.jpg', 'd:\\file\\id\\275b62f7-809f-498b-aac1-be8d1a1b6e11.jpg', '275b62f7-809f-498b-aac1-be8d1a1b6e11.jpg');

-- ----------------------------
-- Table structure for `tb_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` varchar(36) NOT NULL default '',
  `text` varchar(100) default NULL,
  `icon_cls` varchar(20) default NULL,
  `seq` decimal(22,0) default NULL,
  `url` varchar(100) default NULL,
  `pid` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `mpid` (`pid`),
  CONSTRAINT `mpid` FOREIGN KEY (`pid`) REFERENCES `tb_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('0', '首页', null, null, null, null);
INSERT INTO `tb_menu` VALUES ('0c77a745-90b3-4c8e-9848-60d1d806ad9d', '补交学费', 'icon-save', '3', 'jsp/stu/stuArrears.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('1447677b-562e-4b58-98af-ebb26dcbeaca', '报名缴费', 'icon-tip', '2', 'jsp/stu/stuPayment.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('46ec3a24-43a4-44fb-a9e7-d2315eb3c698', '学生管理', 'icon-search', '5', 'jsp/stu/studentManager.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('4a915675-6ffd-46f4-93f8-d2a9464bf9d6', '退款', 'icon-cancel', '4', 'jsp/stu/stuRefundment.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('5f9bb20e-887e-459a-baee-ac697d058775', '财务管理', '', '2', '', '0');
INSERT INTO `tb_menu` VALUES ('6546cfdb-e0d1-48f0-bda7-734c5063fe20', '班级种别管理', 'icon-ok', '6', 'jsp/stu/classTypeManager.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('701da494-93ca-49f3-9b9a-28774030e527', '报名优惠管理', '', '7', 'jsp/fin/preferentialManager.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('cdgl', '菜单管理', '', '2', 'jsp/comn/menuManager.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('ffe51ba1-2682-4c27-a141-0422e2e25d78', '历史账单', '', '2', 'jsp/fin/crashHistory.jsp', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_menu` VALUES ('jsgl', '角色管理', '', '3', 'jsp/comn/roleManager.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('xsbm', '学生报名', '', '1', 'jsp/stu/stuSignup.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('xsgl', '学生管理', '', '1', '', '0');
INSERT INTO `tb_menu` VALUES ('xtgl', '系统管理', '', '3', '', '0');
INSERT INTO `tb_menu` VALUES ('yhgl', '用户管理', '', '1', 'jsp/comn/userManager.jsp', 'xtgl');

-- ----------------------------
-- Table structure for `tb_photo_file`
-- ----------------------------
DROP TABLE IF EXISTS `tb_photo_file`;
CREATE TABLE `tb_photo_file` (
  `id` varchar(36) NOT NULL,
  `file_name` varchar(100) default NULL,
  `file_path` varchar(100) default NULL,
  `file_realname` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_photo_file
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_preferential`
-- ----------------------------
DROP TABLE IF EXISTS `tb_preferential`;
CREATE TABLE `tb_preferential` (
  `id` varchar(36) NOT NULL default '',
  `pre_name` varchar(40) default NULL,
  `preferential_fee` decimal(22,0) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_preferential
-- ----------------------------
INSERT INTO `tb_preferential` VALUES ('08821df7-51b7-4efa-b740-1841b453a9aa', '高考一本线以上', '5000');
INSERT INTO `tb_preferential` VALUES ('483b5431-20e6-416e-bcf4-45b3ab737613', '21123213', '132321231');
INSERT INTO `tb_preferential` VALUES ('ac2d906e-25c9-402b-b6f4-8c9e3248ba3a', '高考二本线以上', '2000');
INSERT INTO `tb_preferential` VALUES ('b68702de-5fbd-4590-887f-85e82cf9819d', '高三在读', '2000');

-- ----------------------------
-- Table structure for `tb_report_file`
-- ----------------------------
DROP TABLE IF EXISTS `tb_report_file`;
CREATE TABLE `tb_report_file` (
  `id` varchar(36) NOT NULL,
  `file_name` varchar(100) default NULL,
  `file_path` varchar(100) default NULL,
  `file_realname` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_report_file
-- ----------------------------
INSERT INTO `tb_report_file` VALUES ('7c220ebd-cfdf-4ae3-92e1-7232cdd1ec84', 'CRM-研发-彭正明.jpg', 'd:\\file\\report\\cd95c8d3-e086-461e-91d0-4f1cb811db41.jpg', 'cd95c8d3-e086-461e-91d0-4f1cb811db41.jpg');

-- ----------------------------
-- Table structure for `tb_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` varchar(36) NOT NULL default '',
  `detail` varchar(100) default NULL,
  `name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('0', '超级管理员', 'admin');
INSERT INTO `tb_role` VALUES ('233c43ba-a29a-45b3-bc0e-6192fd8b5059', '', '财务');
INSERT INTO `tb_role` VALUES ('fdd5e503-0638-4483-9773-74b2f32369d3', '', '报名老师');

-- ----------------------------
-- Table structure for `tb_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `id` varchar(36) NOT NULL default '',
  `rid` varchar(36) default NULL,
  `mid` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `rmmid` (`mid`),
  KEY `rmrid` (`rid`),
  CONSTRAINT `rmmid` FOREIGN KEY (`mid`) REFERENCES `tb_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rmrid` FOREIGN KEY (`rid`) REFERENCES `tb_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('18344bba-6c9f-4d99-8e5b-e41e84de8bb3', '0', '1447677b-562e-4b58-98af-ebb26dcbeaca');
INSERT INTO `tb_role_menu` VALUES ('190c3499-932e-466d-87a5-16a688ee8a7a', 'fdd5e503-0638-4483-9773-74b2f32369d3', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO `tb_role_menu` VALUES ('1f5214a8-469f-4bbb-aa1d-c7394d09e6d2', '0', 'xsbm');
INSERT INTO `tb_role_menu` VALUES ('25dfeab4-8110-4cac-a89e-633c59bbc19b', '0', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO `tb_role_menu` VALUES ('2e571579-9dd6-4f56-8c26-b98f2490e1a0', '0', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_role_menu` VALUES ('3b6bc3de-1f39-47ab-a1b8-84a0889b4528', 'fdd5e503-0638-4483-9773-74b2f32369d3', '1447677b-562e-4b58-98af-ebb26dcbeaca');
INSERT INTO `tb_role_menu` VALUES ('3f32b9cb-2de2-476b-a8c7-03411697ed32', '0', 'cdgl');
INSERT INTO `tb_role_menu` VALUES ('407ac48c-84ba-4aa0-8d0e-5254e8eb31ca', '0', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');
INSERT INTO `tb_role_menu` VALUES ('442af366-03a0-49b0-a3b0-89b59078ee11', '0', 'xtgl');
INSERT INTO `tb_role_menu` VALUES ('49f3ba86-f22b-4001-80c6-fd626c8ee97c', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO `tb_role_menu` VALUES ('5bcfa2b5-1c98-4698-8451-67eda68e14a6', 'fdd5e503-0638-4483-9773-74b2f32369d3', '4a915675-6ffd-46f4-93f8-d2a9464bf9d6');
INSERT INTO `tb_role_menu` VALUES ('5c349684-e380-4653-8cd2-f68881aa2346', 'fdd5e503-0638-4483-9773-74b2f32369d3', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO `tb_role_menu` VALUES ('64ec3931-20bb-48c1-8778-feeca693215a', '0', '4a915675-6ffd-46f4-93f8-d2a9464bf9d6');
INSERT INTO `tb_role_menu` VALUES ('6a8cab81-3f7d-4845-843c-795fbe280f4f', '0', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO `tb_role_menu` VALUES ('818efafa-9159-48c8-9a5a-5c2ac02fec17', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsbm');
INSERT INTO `tb_role_menu` VALUES ('8c4ae1cd-5794-42b0-a90f-ddd44dd49642', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsgl');
INSERT INTO `tb_role_menu` VALUES ('8fb09c78-2641-45ab-8d78-48090b6b2d81', '0', '0');
INSERT INTO `tb_role_menu` VALUES ('9228c9ed-b9a1-4076-b23b-6164409ae31b', '0', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO `tb_role_menu` VALUES ('94d96952-2275-4c75-abcb-6c3792a7e41c', '0', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO `tb_role_menu` VALUES ('96195c93-006f-48af-9b09-b92614dfb50b', 'fdd5e503-0638-4483-9773-74b2f32369d3', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO `tb_role_menu` VALUES ('b7690f30-d38c-4096-8818-c6cac38e40b3', '0', 'yhgl');
INSERT INTO `tb_role_menu` VALUES ('ba864495-c5b5-4967-85d9-0d57428fcb36', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_role_menu` VALUES ('c554b084-7b62-47d3-857b-b99f0f24eb4a', '0', 'xsgl');
INSERT INTO `tb_role_menu` VALUES ('eb92df4e-e860-4b41-b507-1172dda1f255', '0', 'jsgl');
INSERT INTO `tb_role_menu` VALUES ('f9c7629d-4dc0-4ae5-b3f6-53d77a9e3131', 'fdd5e503-0638-4483-9773-74b2f32369d3', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');

-- ----------------------------
-- Table structure for `tb_student`
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` varchar(36) NOT NULL,
  `num` varchar(14) default NULL,
  `name` varchar(10) default NULL,
  `sex` varchar(2) default NULL,
  `nation` varchar(40) default NULL,
  `signed_flg` varchar(1) default NULL,
  `tel` varchar(20) default NULL,
  `intention` varchar(200) default NULL,
  `wlqf` varchar(2) default NULL,
  `photo` varchar(100) default NULL,
  `father_name` varchar(10) default NULL,
  `father_tel` varchar(20) default NULL,
  `father_work` varchar(100) default NULL,
  `mother_name` varchar(10) default NULL,
  `mother_tel` varchar(20) default NULL,
  `mother_work` varchar(100) default NULL,
  `id_num` varchar(30) default NULL,
  `address` varchar(100) default NULL,
  `home_tel` varchar(20) default NULL,
  `graduate_school` varchar(100) default NULL,
  `fraction_language` varchar(5) default NULL,
  `fraction_math` varchar(5) default NULL,
  `fraction_english` varchar(5) default NULL,
  `fraction_comp1` varchar(5) default NULL,
  `fraction_comp2` varchar(5) default NULL,
  `fraction_comp3` varchar(5) default NULL,
  `fraction_comp_count` varchar(5) default NULL,
  `fraction_count` varchar(5) default NULL,
  `class_name` varchar(36) default NULL,
  `stay_flg` varchar(2) default NULL,
  `signup_money_flg` varchar(2) default NULL,
  `secure_flg` varchar(2) default NULL,
  `art_type` varchar(50) default NULL,
  `stay_num` varchar(36) default NULL,
  `stay_tel` varchar(30) default NULL,
  `dormitory_num` varchar(50) default NULL,
  `stu_num` varchar(36) default NULL,
  `selfstudy_nightflg` varchar(2) default NULL,
  `selfstudy_noonflg` varchar(2) default NULL,
  `remark` varchar(100) default NULL,
  `createdatetime` datetime default NULL,
  `modifydatetime` datetime default NULL,
  `year` varchar(4) default NULL,
  `class_type` varchar(36) default NULL,
  `study_fee` decimal(22,0) default NULL,
  `stay_fee` decimal(22,0) default NULL,
  `self_fee` decimal(22,0) default NULL,
  `sign_fee` decimal(22,0) default NULL,
  `score_fee` decimal(22,0) default NULL,
  `safety_fee` decimal(22,0) default NULL,
  `water_fee` decimal(22,0) default NULL,
  `count_fee` decimal(22,0) default NULL,
  `arrearflg` varchar(1) default NULL,
  `arrearFee` decimal(22,0) default NULL,
  `preferentialFee` decimal(22,0) default NULL,
  `refundFee` decimal(22,0) default NULL,
  `payee` varchar(36) default NULL,
  `preferential` varchar(36) default NULL,
  `photo_file_id` varchar(36) default NULL,
  `report_file_id` varchar(36) default NULL,
  `id_file_id` varchar(36) default NULL,
  `file_id` varchar(36) default NULL,
  `selfstudyNightContent` varchar(255) default NULL,
  `selfstudyNoonContent` varchar(255) default NULL,
  `sexContent` varchar(255) default NULL,
  `signedContent` varchar(255) default NULL,
  `stayContent` varchar(255) default NULL,
  `wlqfContent` varchar(255) default NULL,
  `oldFileName` varchar(255) default NULL,
  `oldIdFileName` varchar(255) default NULL,
  `oldPhotoFileName` varchar(255) default NULL,
  `oldReportFileName` varchar(255) default NULL,
  `classTypeName` varchar(255) default NULL,
  `secureContent` varchar(255) default NULL,
  `isPayment_Flg` varchar(1) default NULL,
  `signUpMoneyContent` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `student_class_type` (`class_type`),
  KEY `student_payee` (`payee`),
  KEY `student_preferential` (`preferential`),
  KEY `student_file` (`file_id`),
  KEY `student_id_file` (`id_file_id`),
  KEY `student_photo_file` (`photo_file_id`),
  KEY `student_report_file` (`report_file_id`),
  CONSTRAINT `student_class_type` FOREIGN KEY (`class_type`) REFERENCES `tb_class_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `student_file` FOREIGN KEY (`file_id`) REFERENCES `tb_file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_id_file` FOREIGN KEY (`id_file_id`) REFERENCES `tb_id_file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_payee` FOREIGN KEY (`payee`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `student_photo_file` FOREIGN KEY (`photo_file_id`) REFERENCES `tb_photo_file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_preferential` FOREIGN KEY (`preferential`) REFERENCES `tb_preferential` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `student_report_file` FOREIGN KEY (`report_file_id`) REFERENCES `tb_report_file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('13549511-ce72-435c-abe4-c240a60f6f5a', '考生号4', '姓名4', '0', '民族4', '1', '本人电话4', '报名意向5', '2', null, '父亲姓名4', '电话441', '工作单位441', '母亲姓名4', '电话442', '工作单位442', '身份证4', '家庭住址4', '家庭电话4', '毕业学校4', '语文4', '数学4', '外语4', '历史4', '地理4', '政治4', '42345', '42346', '班级5', '0', null, null, null, null, null, null, '学号5', '1', '1', '4 备注\r\n4 备注\r\n4 备注\r\n', '2014-04-17 14:53:36', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, 'e8d87ca1-72de-463c-b277-b51bb40b07ca', null, null, null, null, null, null, '学生信息输入自制模板v1.8.xls', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, '0', null);
INSERT INTO `tb_student` VALUES ('15728b87-0464-430f-aff3-74c473a28ea9', '考生号5', '姓名5', '1', '民族5', '1', '本人电话5', '', '0', null, '父亲姓名5', '电话551', '工作单位551', '母亲姓名5', '电话552', '工作单位552', '身份证6', '', '家庭电话5', '毕业学校5', '语文5', '数学5', '外语5', '历史5', '地理45', '政治5', '52345', '52346', '班级5', '1', null, null, null, null, null, null, '学号5', '1', '1', '55555555', '2014-04-17 12:41:22', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, '307e3ddd-aa52-497c-bd6a-54e571569d7f', null, null, null, null, null, null, '学生信息输入自制模板v1.8.xls', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, '0', null);
INSERT INTO `tb_student` VALUES ('27d64ab0-b247-40b1-8ba1-1026f01a2cf9', '14120000910000', 'pengzm', null, '', null, 'wqeq', '', '91', null, '', '32213', '', '', '123312312', '', '身份证号321321', '', '', '321231321321', '', '', '', '', '', '', '', '12345', '', null, null, null, '', null, null, '', '', '0', '0', '', '2014-04-28 17:26:14', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, '没有上传入学视频', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, null, null);
INSERT INTO `tb_student` VALUES ('4d50699c-7551-4737-abac-c299c517cc4a', '2312321321', '中心搜索条件需要增加', '0', '123', null, '本人电话证号', '', '0', null, '123', '1235', '工作单位1', '母亲姓名', '1234', '工作单位2', '身份证号', '家庭住址', '家庭电话', '毕业学校', '13231', '1233', '12123', '12331', '21331', '31213', '12332', '12345', '发大水发到', '0', null, null, null, null, null, null, '123321312321', '1', '1', 'ewqrewter2132131231213123', '2014-04-17 11:43:21', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, 'f2f38b04-3ca2-4e0b-b894-2e477002cb16', null, null, null, null, null, null, 'POSCenter.exe', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, null, null);
INSERT INTO `tb_student` VALUES ('57598cb3-2fc7-4b5c-be79-20edd81cad47', null, '李阳', '0', null, null, null, null, '0', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null, null, null, '', '', null, '', '0', '0', '', '2013-11-03 10:58:02', null, null, '4a0d7f10-210f-4cfd-b712-2bbc6a45aebb', '13000', '2500', '500', '100', '100', '100', '200', '16100', '0', null, null, '400', '0', '08821df7-51b7-4efa-b740-1841b453a9aa', null, null, null, 'ed48b112-9cf6-4ca0-a067-ed8cd7d2152d', null, null, null, null, null, null, '学生信息输入自制模板v1.8.xls', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, '0', null);
INSERT INTO `tb_student` VALUES ('5cf62300-7b6d-4234-addd-5d21dfed5e5c', '考生号3', '姓名3', '1', '民族3', '1', '', '', '3', null, '父亲姓名3', '电话331', '工作单位331', '母亲姓名3', '电话332', '工作单位332', '身份证号3', '家庭住址3', '', '毕业学校3', '语文3', '数学3', '外语3', '物理3', '化学3', '生物3', '32345', '32346', '班级3', '1', null, null, null, null, null, null, '学号3', '1', '1', '3备注\r\n3备注\r\n3备注3备注3备注\r\n3备注\r\n\r\n3备注3备注\r\n\r\n3备注', '2014-04-17 12:21:21', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, 'e7385082-641f-4ba4-a8fd-a2b926fa43cf', null, null, null, null, null, null, '学生信息输入自制模板v1.8.xls', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, null, null);
INSERT INTO `tb_student` VALUES ('64ef7831-5506-4db4-95ad-c55a523b1925', '考生号6', '考生号6', '1', '民族6', '1', '本人电话6', '', '1', null, '父亲姓名6', '电话661', '工作单位661', '母亲姓名6', '电话662', '工作单位662', '身份证号6', '家庭住址6', '家庭电话6', '毕业学校6', '语文6', '数学6', '外语6', '物理6', '化学6', '生物6', '62345', '62346', '班级6', '1', null, null, null, null, null, null, '学号6', '1', '1', '6 备注', '2014-04-17 15:17:24', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, '07e066a9-347e-4d5b-bdb8-1d06458b54ff', null, null, null, null, null, null, '微云相册-使用指引.jpg', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, null, null);
INSERT INTO `tb_student` VALUES ('dbfcc1cc-2773-4208-ac07-64b9cf7973ec', '', '马晨', '0', '', null, '', '', '0', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null, null, null, null, null, null, '', '0', '0', '', '2014-04-17 14:44:15', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, 'c7038867-a342-4f7b-9bf7-53bb9fb6b472', null, null, null, null, null, null, '学生信息输入自制模板v1.8.xls', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, null, null);
INSERT INTO `tb_student` VALUES ('e6cb23e1-0147-4716-96aa-997ad25d4dcc', '14120000970000', 'pengzm', '0', '民族1', '1', '本人电话1', '23123123', '97', null, '父亲姓名1', '电话11', '工作单位11', '母亲姓名2', '电话22', '工作单位22', '身份证号1', '家庭住址1', '家庭电话1', '毕业学校1', '13231', '1233', '外语1', '物理2', '化学2', '31213', '12345', '22346', '班级12', '0', '1', '1', '艺术生类型1', null, null, '宿舍号1', '学号2131', '1', '1', 'fdfaadsfsadfa', '2014-04-29 13:44:54', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, '7c220ebd-cfdf-4ae3-92e1-7232cdd1ec84', 'c6167836-49ea-4ab6-ad41-b37c3d3c94ec', 'db9ce34c-88b6-4839-a10b-14fe788be870', null, null, null, null, null, null, 'CRM-研发-彭正明.jpg', 'CRM-研发-彭正明.jpg', '没有照片', 'CRM-研发-彭正明.jpg', null, null, null, null);
INSERT INTO `tb_student` VALUES ('f43d44bc-b471-4e6b-83c4-a2dba3fea227', '考生号1', '姓名1', '1', '民族1', null, '本人电话1', '', '2', null, '父亲姓名1', '电话11', '工作单位11', '母亲姓名2', '电话22', '工作单位22', '身份证号1', '家庭住址1', '家庭电话1', '毕业学校1', '语文1', '数学1', '外语1', '物理1', '化学1', '生物1', '12345', '12346', '班级12', '1', null, null, null, null, null, null, '学号2131', '1', '1', '1 备注\r\n1 备注\r\n1 备注\r\n1 备注\r\n1 备注\r\n1 备注\r\n1 备注1 备注1 备注1 备注1 备注1 备注\r\n1 备注1 备注\r\n1 备注1 备注', '2014-04-17 11:53:34', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, '2d0e44fd-9b1b-4a2f-9897-dbf401c5cbd5', null, null, null, null, null, null, '身份证号码验证.xls', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, null, null);
INSERT INTO `tb_student` VALUES ('fac02eae-31ad-41e7-a3cf-b643ca4873ac', '考生号2', '姓名2', '1', '民族2', '1', '本人电话2', '报名意向2', '2', null, '父亲姓名2', '11电话11', '11工作单位11', '母亲姓名2', '22电话22', '22工作单位22', '身份证号2', '家庭住址2', '家庭电话2', '毕业学校2', '语文2', '数学2', '外语2', '物理2', '化学2', '生物2', '22345', '22346', '班级1223222', '0', null, null, null, null, null, null, '学号2131222222222222222222222', '1', '1', '22222222222222222222222222222222222222222222222', '2014-04-17 12:16:42', null, null, null, null, null, null, '100', null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, null, null, '没有上传入学视频', '没有身份证扫描件', '没有照片', '没有成绩单扫描件', null, null, null, null);

-- ----------------------------
-- Table structure for `tb_stu_signup`
-- ----------------------------
DROP TABLE IF EXISTS `tb_stu_signup`;
CREATE TABLE `tb_stu_signup` (
  `id` varchar(36) NOT NULL,
  `name` varchar(10) default NULL,
  `sex` varchar(2) default NULL,
  `wlqf` varchar(2) default NULL,
  `photo` varchar(100) default NULL,
  `father_name` varchar(10) default NULL,
  `father_tel` varchar(20) default NULL,
  `father_work` varchar(100) default NULL,
  `mother_name` varchar(10) default NULL,
  `mother_tel` varchar(20) default NULL,
  `mother_work` varchar(100) default NULL,
  `id_num` varchar(30) default NULL,
  `address` varchar(100) default NULL,
  `home_tel` varchar(20) default NULL,
  `graduate_school` varchar(100) default NULL,
  `fraction_language` varchar(5) default NULL,
  `fraction_math` varchar(5) default NULL,
  `fraction_english` varchar(5) default NULL,
  `fraction_comp1` varchar(5) default NULL,
  `fraction_comp2` varchar(5) default NULL,
  `fraction_comp3` varchar(5) default NULL,
  `fraction_comp_count` varchar(5) default NULL,
  `fraction_count` varchar(5) default NULL,
  `class_name` varchar(36) default NULL,
  `stay_flg` varchar(2) default NULL,
  `stay_num` varchar(36) default NULL,
  `stay_tel` varchar(30) default NULL,
  `stu_num` varchar(36) default NULL,
  `selfstudy_nightflg` varchar(2) default NULL,
  `selfstudy_noonflg` varchar(2) default NULL,
  `remark` varchar(100) default NULL,
  `createdatetime` datetime default NULL,
  `modifydatetime` datetime default NULL,
  `year` varchar(4) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_stu_signup
-- ----------------------------
INSERT INTO `tb_stu_signup` VALUES ('0f632fc4-bc27-4475-bd4e-e25912b1a485', '回澍', '0', '0', null, '回邵增', '123123123', '南开', '穆醒', '1231231231', '南开', '120102230210230202', '天津市南开区', '12312313', '理工大学', '120', '120', '120', '120', '120', '120', '300', '700', '2', '0', '201', '123123', '12312231313', '0', '0', '123131313123123123131312312313213123213123123', null, null, null);
INSERT INTO `tb_stu_signup` VALUES ('23f6f286-8b82-4d0d-af18-8885302bf8bc', 'asdad', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 17:33:24', null, null);
INSERT INTO `tb_stu_signup` VALUES ('5d65b5b9-30d1-4f04-8bbc-f913aa88d781', 'huishu2', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 17:33:16', null, null);
INSERT INTO `tb_stu_signup` VALUES ('6d139eb6-90ee-4659-9b4a-2b80e8291ef2', 'shuhui', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 17:35:28', null, null);
INSERT INTO `tb_stu_signup` VALUES ('74a46090-3c02-4da5-8283-329d18100e93', '123123123', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 18:15:20', null, null);
INSERT INTO `tb_stu_signup` VALUES ('bb4dbea1-60a8-4460-bea5-6ebcfdfec616', '324324', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 18:08:40', null, null);
INSERT INTO `tb_stu_signup` VALUES ('c5ebf4fb-6de8-42e8-ac60-e26727513e16', '123132131', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 19:03:15', null, null);
INSERT INTO `tb_stu_signup` VALUES ('ce6d90ef-4b66-405f-84cb-4fab92a17a63', 'huishu2', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 17:32:41', null, null);
INSERT INTO `tb_stu_signup` VALUES ('d13e5444-70f2-41de-b055-9aa856ff2330', '123123ads', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 18:15:10', null, null);

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(36) NOT NULL default '',
  `username` varchar(100) default NULL,
  `name` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  `createdatetime` datetime default NULL,
  `modifydatetime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('0', 'admin', '管理员', '21232f297a57a5a743894a0e4a801fc3', null, null);
INSERT INTO `tb_user` VALUES ('4672aca7-38df-46d5-a4fe-a4e76c9a3f5f', 'huishu', '回澍', '21232f297a57a5a743894a0e4a801fc3', '2013-11-03 11:06:25', null);
INSERT INTO `tb_user` VALUES ('5170f6c4-783f-476b-a87f-a23f84ae1eea', 'liyang', '李阳', '21232f297a57a5a743894a0e4a801fc3', '2013-11-03 11:04:42', null);
INSERT INTO `tb_user` VALUES ('d2eed059-84ea-40d8-9511-8649bba7bf46', 'machen', '马晨', '21232f297a57a5a743894a0e4a801fc3', null, null);

-- ----------------------------
-- Table structure for `tb_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` varchar(36) NOT NULL default '',
  `uid` varchar(36) default NULL,
  `rid` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `uruid` (`uid`),
  KEY `urrid` (`rid`),
  CONSTRAINT `urrid` FOREIGN KEY (`rid`) REFERENCES `tb_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uruid` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('13629406-c11e-4ecb-96f1-9ad142ee57a0', '4672aca7-38df-46d5-a4fe-a4e76c9a3f5f', '233c43ba-a29a-45b3-bc0e-6192fd8b5059');
INSERT INTO `tb_user_role` VALUES ('32ce85cd-242e-4e72-99bf-aa9078781544', '5170f6c4-783f-476b-a87f-a23f84ae1eea', '233c43ba-a29a-45b3-bc0e-6192fd8b5059');
INSERT INTO `tb_user_role` VALUES ('51c706d0-043e-4b1b-81e3-5865cdf3ac54', '4672aca7-38df-46d5-a4fe-a4e76c9a3f5f', 'fdd5e503-0638-4483-9773-74b2f32369d3');
INSERT INTO `tb_user_role` VALUES ('ad23fdcb-afa3-404b-a960-57833eabc4aa', 'd2eed059-84ea-40d8-9511-8649bba7bf46', 'fdd5e503-0638-4483-9773-74b2f32369d3');
INSERT INTO `tb_user_role` VALUES ('e9ab9cf6-ebf7-4d2b-aa8c-5617ad71a4cd', '0', '0');

-- ----------------------------
-- Table structure for `tmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tmenu`;
CREATE TABLE `tmenu` (
  `id` varchar(36) NOT NULL,
  `iconCls` varchar(50) default NULL,
  `text` varchar(100) default NULL,
  `url` varchar(200) default NULL,
  `pid` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_hggv0vehbpurg9eepya972yw0` (`pid`),
  CONSTRAINT `FK_hggv0vehbpurg9eepya972yw0` FOREIGN KEY (`pid`) REFERENCES `tmenu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tmenu
-- ----------------------------

-- ----------------------------
-- Table structure for `tuser`
-- ----------------------------
DROP TABLE IF EXISTS `tuser`;
CREATE TABLE `tuser` (
  `id` varchar(36) NOT NULL,
  `createdatetime` date default NULL,
  `modifydatetime` date default NULL,
  `name` varchar(100) default NULL,
  `pwd` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tuser
-- ----------------------------
