/*
Navicat MySQL Data Transfer

Source Server         : MySql
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : highschool

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2014-05-09 15:20:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_class_time`
-- ----------------------------
DROP TABLE IF EXISTS `tb_class_time`;
CREATE TABLE `tb_class_time` (
  `id` varchar(36) NOT NULL default '',
  `name` varchar(40) NOT NULL,
  `endTime` time NOT NULL,
  `startTime` time NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- ----------------------------
-- Table structure for `tb_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `father_id` varchar(36) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dictionary
-- ----------------------------
INSERT INTO `tb_dictionary` VALUES ('100', '时间', '0');
INSERT INTO `tb_dictionary` VALUES ('100001', '凌晨12', '100');
INSERT INTO `tb_dictionary` VALUES ('100002', '凌晨1', '100');
INSERT INTO `tb_dictionary` VALUES ('100003', '凌晨2', '100');
INSERT INTO `tb_dictionary` VALUES ('100004', '凌晨3', '100');
INSERT INTO `tb_dictionary` VALUES ('100005', '凌晨4', '100');
INSERT INTO `tb_dictionary` VALUES ('100006', '凌晨5', '100');
INSERT INTO `tb_dictionary` VALUES ('100007', '早上6', '100');
INSERT INTO `tb_dictionary` VALUES ('100008', '早上7', '100');
INSERT INTO `tb_dictionary` VALUES ('100009', '早上8', '100');
INSERT INTO `tb_dictionary` VALUES ('100010', '上午9', '100');
INSERT INTO `tb_dictionary` VALUES ('100011', '上午10', '100');
INSERT INTO `tb_dictionary` VALUES ('100012', '上午11', '100');
INSERT INTO `tb_dictionary` VALUES ('100013', '中午12', '100');
INSERT INTO `tb_dictionary` VALUES ('100014', '下午1', '100');
INSERT INTO `tb_dictionary` VALUES ('100015', '下午2', '100');
INSERT INTO `tb_dictionary` VALUES ('100016', '下午3', '100');
INSERT INTO `tb_dictionary` VALUES ('100017', '下午4', '100');
INSERT INTO `tb_dictionary` VALUES ('100018', '下午5', '100');
INSERT INTO `tb_dictionary` VALUES ('100019', '晚上6', '100');
INSERT INTO `tb_dictionary` VALUES ('100020', '晚上7', '100');
INSERT INTO `tb_dictionary` VALUES ('100021', '晚上8', '100');
INSERT INTO `tb_dictionary` VALUES ('100022', '晚上9', '100');
INSERT INTO `tb_dictionary` VALUES ('100023', '晚上10', '100');
INSERT INTO `tb_dictionary` VALUES ('100024', '晚上11', '100');
INSERT INTO `tb_dictionary` VALUES ('101', '日志记录类型', '0');
INSERT INTO `tb_dictionary` VALUES ('101001', '一次', '101');
INSERT INTO `tb_dictionary` VALUES ('101002', '小计', '101');
INSERT INTO `tb_dictionary` VALUES ('102', '日志方式', '0');
INSERT INTO `tb_dictionary` VALUES ('102001', '增加', '102');
INSERT INTO `tb_dictionary` VALUES ('102002', '扣除', '102');
INSERT INTO `tb_dictionary` VALUES ('103', '日志结果', '0');

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
  `cashRefundFee` decimal(22,0) default NULL,
  `transferRefundFee` decimal(22,0) default NULL,
  `cashPayAgainFee` decimal(22,0) default NULL,
  `transferPayAgainFee` decimal(22,0) default NULL,
  `payee` varchar(36) default NULL,
  `detail` varchar(100) default NULL,
  `money` decimal(22,0) default NULL,
  `paydatetime` datetime default NULL,
  `payflg` varchar(1) default NULL,
  `text` varchar(100) default NULL,
  `aliFee` decimal(19,2) default NULL,
  `aliPayAgainFee` decimal(22,0) default NULL,
  `aliRefundFee` decimal(22,0) default NULL,
  `bankFee` decimal(22,0) default NULL,
  `bankPayAgainFee` decimal(22,0) default NULL,
  `bankRefundFee` decimal(22,0) default NULL,
  `cancel_flg` varchar(2) default NULL,
  `crashHistoryType` varchar(50) default NULL,
  `lakalaFee` decimal(19,2) default NULL,
  `lakalaPayAgainFee` decimal(22,0) default NULL,
  `lakalaRefundFee` decimal(22,0) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_atbixe4b0gwnmic7gewjxnkn2` (`payee`),
  KEY `finance_class_type` (`class_type`),
  CONSTRAINT `finance_class_type` FOREIGN KEY (`class_type`) REFERENCES `tb_class_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `tb_finance_ibfk_1` FOREIGN KEY (`payee`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_finance
-- ----------------------------

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

-- ----------------------------
-- Table structure for `tb_log_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_info`;
CREATE TABLE `tb_log_info` (
  `id` varchar(36) NOT NULL,
  `endTime` time NOT NULL,
  `startTime` time NOT NULL,
  `classId` varchar(36) NOT NULL,
  `classTimeId` varchar(36) NOT NULL,
  `remark` varchar(100) default NULL,
  `resultId` varchar(36) NOT NULL,
  `studentId` varchar(36) NOT NULL,
  `typeId` varchar(36) NOT NULL,
  `date` date default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_278e4y31utxuhy7xxomrk7cju` (`classTimeId`),
  KEY `FK_pwlixy23ik7o1mpvvxwrwm191` (`typeId`),
  KEY `FK_fw1ay5qq1a7t449ewy2gxblhc` (`resultId`),
  KEY `FK_dmt3jdin4ln3fb70i605ykjuj` (`studentId`),
  CONSTRAINT `FK_278e4y31utxuhy7xxomrk7cju` FOREIGN KEY (`classTimeId`) REFERENCES `tb_class_time` (`id`),
  CONSTRAINT `FK_dmt3jdin4ln3fb70i605ykjuj` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`),
  CONSTRAINT `FK_fw1ay5qq1a7t449ewy2gxblhc` FOREIGN KEY (`resultId`) REFERENCES `tb_log_result` (`id`),
  CONSTRAINT `FK_pwlixy23ik7o1mpvvxwrwm191` FOREIGN KEY (`typeId`) REFERENCES `tb_log_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tb_log_result`
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_result`;
CREATE TABLE `tb_log_result` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tb_log_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_log_type`;
CREATE TABLE `tb_log_type` (
  `id` varchar(36) NOT NULL,
  `name` varchar(100) default NULL,
  `score` int(11) default NULL,
  `type_id` varchar(36) default NULL,
  `count` int(11) default NULL,
  `mode_id` varchar(36) default NULL,
  `remark` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `tb_menu` VALUES ('0c77a745-90b3-4c8e-9848-60d1d806ad9d', '补交学费', 'icon-save', '3', 'jsp/stu/stuArrears.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('0efc84da-0ca3-47e5-9f1e-9da9b2595986', '月考', '', '4', '', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('1447677b-562e-4b58-98af-ebb26dcbeaca', '报名缴费', 'icon-tip', '2', 'jsp/stu/stuPayment.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('1aa7767e-68a8-41eb-b7d5-7cb8478cbe9c', '分配考场', '', '5', '', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('1e60c2f1-e4e1-4606-8c0e-cd7d85d0de98', '日志管理', '', '3', 'jsp/director/logManage.jsp', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('31472bb2-949e-4a5e-8d87-bfd7647424e5', '月考成绩计算方式', '', '11', '', 'xtgl');
INSERT INTO `tb_menu` VALUES ('46ec3a24-43a4-44fb-a9e7-d2315eb3c698', '学生管理', 'icon-search', '5', 'jsp/stu/studentManager.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('474f41e0-3bd3-4918-bac1-a777fad86692', '设定月考信息', '', '10', '', 'xtgl');
INSERT INTO `tb_menu` VALUES ('47a5b8a7-4d07-43d5-9823-91e34f3ec33c', '课程时间设定', '', '7', 'jsp/comn/classTime.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('4a915675-6ffd-46f4-93f8-d2a9464bf9d6', '退款', 'icon-cancel', '4', 'jsp/stu/stuRefundment.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('5f9bb20e-887e-459a-baee-ac697d058775', '财务管理', '', '2', '', null);
INSERT INTO `tb_menu` VALUES ('6546cfdb-e0d1-48f0-bda7-734c5063fe20', '班级种别管理', 'icon-ok', '6', 'jsp/stu/classTypeManager.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('701da494-93ca-49f3-9b9a-28774030e527', '报名优惠管理', '', '7', 'jsp/fin/preferentialManager.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('73b5aed7-f047-4b86-90b5-2ec1965429df', '学年设定', '', '4', '', 'xtgl');
INSERT INTO `tb_menu` VALUES ('797287d9-5886-42ce-b0a5-49dd8b312d46', '分班', '', '6', '', 'xtgl');
INSERT INTO `tb_menu` VALUES ('9e4fa011-ac95-44a7-91f7-09dd1fbff7dd', '分班规则设定', '', '5', '', 'xtgl');
INSERT INTO `tb_menu` VALUES ('ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8', '学生信息修改历史', '', '8', 'jsp/stu/sutdentInfoHistory.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('bzrgl', '班主任管理', '', '3', '', null);
INSERT INTO `tb_menu` VALUES ('cdb86281-4089-4b9b-a24f-9499724e6310', '日志结果管理', '', '9', 'jsp/comn/logResult.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('cdgl', '菜单管理', '', '2', 'jsp/comn/menuManager.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('d53505e7-b424-47dc-9f2f-2b1016e76f3b', '高考成绩', '', '6', '', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('d62355fe-2884-4e4e-a620-fa69515bce5f', '日志类型管理', '', '8', 'jsp/comn/logType.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('e71b4c05-640b-40be-b6b1-2afca4026825', '日志日历', '', '2', 'jsp/director/logCalendar.jsp', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('ffe51ba1-2682-4c27-a141-0422e2e25d78', '历史账单', '', '2', 'jsp/fin/crashHistory.jsp', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_menu` VALUES ('jsgl', '角色管理', '', '3', 'jsp/comn/roleManager.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('xsbm', '学生报名', '', '1', 'jsp/stu/stuSignup.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('xsgl', '学生管理', '', '1', '', null);
INSERT INTO `tb_menu` VALUES ('xtgl', '系统管理', '', '4', '', null);
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
INSERT INTO `tb_role` VALUES ('df3ab88d-c5a2-4f82-aef0-597468fd70d0', '', '班主任');
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
INSERT INTO `tb_role_menu` VALUES ('003926bb-c62a-426d-bc7e-d17cda79de20', '0', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');
INSERT INTO `tb_role_menu` VALUES ('04fed3c4-3741-4de7-b36c-883b1c498b3a', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', 'd53505e7-b424-47dc-9f2f-2b1016e76f3b');
INSERT INTO `tb_role_menu` VALUES ('077bf1f7-1968-4f3f-aa01-c1f80a2a8eee', '0', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO `tb_role_menu` VALUES ('09e4312a-bdf5-479c-ae9e-4caf6a3f0f42', '0', 'jsgl');
INSERT INTO `tb_role_menu` VALUES ('108b6341-47e9-469e-a39b-3f17ccb2a938', '0', 'cdgl');
INSERT INTO `tb_role_menu` VALUES ('11ee44ff-46c6-4612-b42f-4df76eac2577', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8');
INSERT INTO `tb_role_menu` VALUES ('13f09aac-53e1-46b4-9d06-5c8b1a67ac2b', '0', 'xtgl');
INSERT INTO `tb_role_menu` VALUES ('19701f4d-d08a-49f4-97e3-d7604c2c5fe3', '0', 'cdb86281-4089-4b9b-a24f-9499724e6310');
INSERT INTO `tb_role_menu` VALUES ('23552fda-b6b1-4801-98ee-fd5b1b8083d3', '0', '1aa7767e-68a8-41eb-b7d5-7cb8478cbe9c');
INSERT INTO `tb_role_menu` VALUES ('258b8246-18de-4c10-add4-1c5c446b0dc2', '0', '47a5b8a7-4d07-43d5-9823-91e34f3ec33c');
INSERT INTO `tb_role_menu` VALUES ('28ef9679-6bb4-49f7-8f3b-ae2ca08ddcb3', '0', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO `tb_role_menu` VALUES ('29203ea5-4b75-4e0a-9027-8c0a491e84bb', '0', 'xsgl');
INSERT INTO `tb_role_menu` VALUES ('2aeb8db4-96dc-42fd-b34b-56e729b8f2e9', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsbm');
INSERT INTO `tb_role_menu` VALUES ('3bbf7397-5fbd-45e0-8ab3-afb009f6be88', 'fdd5e503-0638-4483-9773-74b2f32369d3', '4a915675-6ffd-46f4-93f8-d2a9464bf9d6');
INSERT INTO `tb_role_menu` VALUES ('3c5e0d52-b4c6-43e1-94fd-36c7d0a8b906', '0', '0efc84da-0ca3-47e5-9f1e-9da9b2595986');
INSERT INTO `tb_role_menu` VALUES ('47eaa187-13c3-41fb-a407-25d0d91716b3', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', '1e60c2f1-e4e1-4606-8c0e-cd7d85d0de98');
INSERT INTO `tb_role_menu` VALUES ('4f54fc9e-d2cb-4ff8-a174-acb23fa96acf', 'fdd5e503-0638-4483-9773-74b2f32369d3', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO `tb_role_menu` VALUES ('4f95c953-dd1b-4ce5-ab16-7cf4413bf796', '0', 'e71b4c05-640b-40be-b6b1-2afca4026825');
INSERT INTO `tb_role_menu` VALUES ('56e2568c-4bc6-444b-8027-b80c7cfe34f6', '0', '1447677b-562e-4b58-98af-ebb26dcbeaca');
INSERT INTO `tb_role_menu` VALUES ('611448fc-b14f-498f-9773-1d43e07a6b4d', '0', '797287d9-5886-42ce-b0a5-49dd8b312d46');
INSERT INTO `tb_role_menu` VALUES ('62eb3e9e-b522-43d9-bce2-9817f58501b1', '0', '474f41e0-3bd3-4918-bac1-a777fad86692');
INSERT INTO `tb_role_menu` VALUES ('69faca38-1084-43f8-8e8c-5fca578c08e8', 'fdd5e503-0638-4483-9773-74b2f32369d3', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO `tb_role_menu` VALUES ('71b4b16b-9d35-41ca-951c-fcaaa7b7f054', '0', '31472bb2-949e-4a5e-8d87-bfd7647424e5');
INSERT INTO `tb_role_menu` VALUES ('723054cf-8da0-41f4-8dde-61376df87c37', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', 'e71b4c05-640b-40be-b6b1-2afca4026825');
INSERT INTO `tb_role_menu` VALUES ('74eccf9a-449d-4c90-aaf5-cee21934e631', '0', 'd62355fe-2884-4e4e-a620-fa69515bce5f');
INSERT INTO `tb_role_menu` VALUES ('7e861402-1334-42ff-946c-b3c8d798fcb9', '0', 'xsbm');
INSERT INTO `tb_role_menu` VALUES ('8a632d30-6270-4dc8-be03-3f2e96be5a69', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_role_menu` VALUES ('8d0cc79f-00f0-4f65-b089-0d9702a8e5c6', '0', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO `tb_role_menu` VALUES ('8d8bc1a0-4d00-49f1-a534-1f1760afd5b8', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', '0efc84da-0ca3-47e5-9f1e-9da9b2595986');
INSERT INTO `tb_role_menu` VALUES ('8f8bd597-633d-463b-9b39-4cf6faf66eff', '0', '1e60c2f1-e4e1-4606-8c0e-cd7d85d0de98');
INSERT INTO `tb_role_menu` VALUES ('901f155d-57b8-4d01-81b5-5efb1a9ce9a4', 'fdd5e503-0638-4483-9773-74b2f32369d3', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');
INSERT INTO `tb_role_menu` VALUES ('9d757ac4-8c2e-4734-a66e-ae68a41dc4f0', '0', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO `tb_role_menu` VALUES ('9f0712c2-88d0-4929-9ab8-518a41627b98', 'fdd5e503-0638-4483-9773-74b2f32369d3', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO `tb_role_menu` VALUES ('adfbc400-9166-4253-8c08-8d6744fef9f6', '0', '73b5aed7-f047-4b86-90b5-2ec1965429df');
INSERT INTO `tb_role_menu` VALUES ('b045e2dd-ae74-47a8-8ac4-ffe79a2ba770', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsgl');
INSERT INTO `tb_role_menu` VALUES ('b2c18d3c-294e-43f2-b974-29e79b69105f', '0', 'bzrgl');
INSERT INTO `tb_role_menu` VALUES ('b533f7d5-8f14-42ae-851b-db2ce2705393', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO `tb_role_menu` VALUES ('c1dbd4a0-2131-4c64-9210-ed046a2349e5', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', '1aa7767e-68a8-41eb-b7d5-7cb8478cbe9c');
INSERT INTO `tb_role_menu` VALUES ('c3d8321f-3d6f-4e13-9376-dc3bf5a57dbb', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', 'bzrgl');
INSERT INTO `tb_role_menu` VALUES ('d062ceb8-abe0-44c3-a51e-7ee39779cd3d', '0', '9e4fa011-ac95-44a7-91f7-09dd1fbff7dd');
INSERT INTO `tb_role_menu` VALUES ('d35ba828-5840-40a5-9bc1-77eea0d64cee', '0', '4a915675-6ffd-46f4-93f8-d2a9464bf9d6');
INSERT INTO `tb_role_menu` VALUES ('e74ae94e-0b28-4351-b9ed-5c79aecd393d', '0', 'yhgl');
INSERT INTO `tb_role_menu` VALUES ('e902a9ea-24d0-4bc6-af3d-abe5a3cd98b0', '0', 'd53505e7-b424-47dc-9f2f-2b1016e76f3b');
INSERT INTO `tb_role_menu` VALUES ('ef436aa9-4e1a-4cf4-8b73-f72af067b0f6', '0', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_role_menu` VALUES ('f552addd-d094-4bea-95ce-a1d79dbf02fc', '0', 'ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8');
INSERT INTO `tb_role_menu` VALUES ('f88fac41-2c45-4d24-9162-3a9b6ee6aa94', 'fdd5e503-0638-4483-9773-74b2f32369d3', '1447677b-562e-4b58-98af-ebb26dcbeaca');

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
  `transferSignUpMoneyFlg` varchar(1) default NULL,
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
  `aliSignUpMoneyFlg` varchar(1) default NULL,
  `bankSignUpMoneyFlg` varchar(1) default NULL,
  `lakalaSignUpMoneyFlg` varchar(1) default NULL,
  `stuTypeContent` varchar(255) default NULL,
  `studentType` varchar(2) default NULL,
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

-- ----------------------------
-- Table structure for `tb_student_info_history`
-- ----------------------------
DROP TABLE IF EXISTS `tb_student_info_history`;
CREATE TABLE `tb_student_info_history` (
  `id` varchar(36) NOT NULL,
  `num` varchar(14) default NULL,
  `name` varchar(10) default NULL,
  `id_num` varchar(30) default NULL,
  `class_type_id` varchar(36) default NULL,
  `class_type_name` varchar(40) default NULL,
  `update_content` varchar(2000) default NULL,
  `createdatetime` datetime default NULL,
  `updatedatetime` datetime default NULL,
  `payee` varchar(36) default NULL,
  `update_type` varchar(40) default NULL,
  `student_id` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `student_payee` (`payee`),
  CONSTRAINT `tb_student_info_history_ibfk_4` FOREIGN KEY (`payee`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

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
INSERT INTO tb_user VALUES ('0', 'admin', '管理员', '21232f297a57a5a743894a0e4a801fc3', null, null);

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
INSERT INTO tb_user_role VALUES ('e9ab9cf6-ebf7-4d2b-aa8c-5617ad71a4cd', '0', '0');
