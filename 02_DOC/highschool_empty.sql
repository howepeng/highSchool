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
  `bankFee` decimal(22,0) default NULL,
  `lakalaFee` decimal(22,0) default NULL,
  `aliFee` decimal(22,0) default NULL,
  `payAgainFee` decimal(22,0) default NULL,
  `refundFee` decimal(22,0) default NULL,
  `count_pay_fee` decimal(22,0) default NULL,
  `cashRefundFee` decimal(22,0) default NULL,
  `cashPayAgainFee` decimal(22,0) default NULL,
  `bankPayAgainFee` decimal(22,0) default NULL,
  `payee` varchar(36) default NULL,
  `lakalaPayAgainFee` decimal(22,0) default NULL,
  `aliPayAgainFee` decimal(22,0) default NULL,
  `aliRefundFee` decimal(22,0) default NULL,
  `bankRefundFee` decimal(22,0) default NULL,
  `lakalaRefundFee` decimal(22,0) default NULL,
  `cancel_flg` varchar(2) default NULL,
  `crashHistoryType` varchar(50) default NULL,
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
INSERT INTO `tb_menu` VALUES ('ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8', '学生信息修改历史', '', '8', 'jsp/stu/sutdentInfoHistory.jsp', 'xsgl');
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
INSERT INTO `tb_role_menu` VALUES ('03056b9f-c721-4403-a055-5885cf699c53', '0', '0');
INSERT INTO `tb_role_menu` VALUES ('1bb149d0-9351-4881-93b1-50e46838ae68', '0', 'xsbm');
INSERT INTO `tb_role_menu` VALUES ('21c51c20-6e8d-4c82-a93d-211f0ae347bd', 'fdd5e503-0638-4483-9773-74b2f32369d3', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO `tb_role_menu` VALUES ('25beb6f1-caa9-4f0d-8232-29d99594c19e', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsgl');
INSERT INTO `tb_role_menu` VALUES ('2c2e8ad9-95c1-4020-b0ea-0efab165ebfb', 'fdd5e503-0638-4483-9773-74b2f32369d3', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO `tb_role_menu` VALUES ('39bf4be4-0a33-475a-922a-aeee43aa609f', 'fdd5e503-0638-4483-9773-74b2f32369d3', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO `tb_role_menu` VALUES ('45163885-de94-4105-b16c-3e74e91d84e0', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsbm');
INSERT INTO `tb_role_menu` VALUES ('493b6602-d594-4acd-a3ac-52f79283189e', 'fdd5e503-0638-4483-9773-74b2f32369d3', '4a915675-6ffd-46f4-93f8-d2a9464bf9d6');
INSERT INTO `tb_role_menu` VALUES ('49f3ba86-f22b-4001-80c6-fd626c8ee97c', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO `tb_role_menu` VALUES ('50c68c26-12f8-46d3-95d9-af7f4c42c00d', '0', 'xsgl');
INSERT INTO `tb_role_menu` VALUES ('5a966f1b-cdc6-4d6f-87b9-af0a3dcb36de', '0', 'cdgl');
INSERT INTO `tb_role_menu` VALUES ('67eef788-1b10-48a6-80e9-a90f116f3a40', '0', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO `tb_role_menu` VALUES ('688e4402-3b99-48a0-92f2-4345068b28e7', 'fdd5e503-0638-4483-9773-74b2f32369d3', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');
INSERT INTO `tb_role_menu` VALUES ('6d6bb533-b199-44e5-a253-04766a1a18ef', '0', 'xtgl');
INSERT INTO `tb_role_menu` VALUES ('6f93f48b-2cec-4a75-aa2c-cd353e66e556', '0', 'jsgl');
INSERT INTO `tb_role_menu` VALUES ('81db9c4c-8a45-477f-85fe-ed55d6b06dc4', '0', 'yhgl');
INSERT INTO `tb_role_menu` VALUES ('8ea5571b-7c77-44b9-be2f-38d1b0256650', '0', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO `tb_role_menu` VALUES ('91bea6d1-9ec4-4b84-a0d1-31e770560970', '0', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_role_menu` VALUES ('939c7278-3cd7-4ae7-92d0-4a303f9e524e', '0', 'ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8');
INSERT INTO `tb_role_menu` VALUES ('9a4c6068-8f39-4afb-9805-8eaf94e1eaae', '0', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO `tb_role_menu` VALUES ('a73d4e06-8347-486a-91fa-86a07471cdb8', '0', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');
INSERT INTO `tb_role_menu` VALUES ('b26965d9-75a1-4315-9a34-f78de7fb6485', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8');
INSERT INTO `tb_role_menu` VALUES ('ba864495-c5b5-4967-85d9-0d57428fcb36', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_role_menu` VALUES ('c5afa6f6-671a-48ee-93be-d35c1e741f89', '0', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO `tb_role_menu` VALUES ('e7cda2ff-a6ab-4ad3-9893-4c06b982e388', '0', '1447677b-562e-4b58-98af-ebb26dcbeaca');
INSERT INTO `tb_role_menu` VALUES ('e871a572-623d-4afa-8666-eadfaaae0b3f', '0', '4a915675-6ffd-46f4-93f8-d2a9464bf9d6');
INSERT INTO `tb_role_menu` VALUES ('ef50280d-ff96-4b1c-888b-e3cf41307302', 'fdd5e503-0638-4483-9773-74b2f32369d3', '1447677b-562e-4b58-98af-ebb26dcbeaca');

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
  `bankSignUpMoneyFlg` varchar(1) default NULL,
  `lakalaSignUpMoneyFlg` varchar(1) default NULL,
  `aliSignUpMoneyFlg` varchar(1) default NULL,
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
