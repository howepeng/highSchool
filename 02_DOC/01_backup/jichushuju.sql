/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : highschool

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2013-11-18 00:30:00
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
INSERT INTO tb_class_type VALUES ('017bf6bb-918c-404a-a410-872578fa5f67', '艺术班', '20000', '1000', '100', '100', '100', '100', '100', '21500');
INSERT INTO tb_class_type VALUES ('4a0d7f10-210f-4cfd-b712-2bbc6a45aebb', '签约普通班', '18000', '2500', '500', '100', '100', '100', '200', '21500');
INSERT INTO tb_class_type VALUES ('4f6ef1dc-360d-43b9-8bc9-3be6ecf0469e', '签约小班', '35000', '2500', '500', '100', '100', '100', '200', '38500');

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
  `detail` varchar(100) default NULL,
  `money` decimal(22,0) default NULL,
  `paydatetime` datetime default NULL,
  `payflg` varchar(1) default NULL,
  `text` varchar(100) default NULL,
  `payee` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_atbixe4b0gwnmic7gewjxnkn2` (`payee`),
  CONSTRAINT `FK_atbixe4b0gwnmic7gewjxnkn2` FOREIGN KEY (`payee`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_finance
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
INSERT INTO tb_menu VALUES ('0', '首页', null, null, null, null);
INSERT INTO tb_menu VALUES ('0c77a745-90b3-4c8e-9848-60d1d806ad9d', '补交学费', 'icon-save', '9', 'jsp/stu/stuArrears.jsp', 'xsgl');
INSERT INTO tb_menu VALUES ('1447677b-562e-4b58-98af-ebb26dcbeaca', '报名缴费', 'icon-tip', '1', 'jsp/stu/stuPayment.jsp', 'xsgl');
INSERT INTO tb_menu VALUES ('46ec3a24-43a4-44fb-a9e7-d2315eb3c698', '学生管理', 'icon-search', '9', 'jsp/stu/studentManager.jsp', 'xsgl');
INSERT INTO tb_menu VALUES ('5f9bb20e-887e-459a-baee-ac697d058775', '财务管理', '', '8', '', '0');
INSERT INTO tb_menu VALUES ('6546cfdb-e0d1-48f0-bda7-734c5063fe20', '班级种别管理', 'icon-ok', '9', 'jsp/stu/classTypeManager.jsp', 'xsgl');
INSERT INTO tb_menu VALUES ('701da494-93ca-49f3-9b9a-28774030e527', '报名优惠管理', '', '9', 'jsp/fin/preferentialManager.jsp', 'xsgl');
INSERT INTO tb_menu VALUES ('cdgl', '菜单管理', '', '2', 'jsp/comn/menuManager.jsp', 'xtgl');
INSERT INTO tb_menu VALUES ('f81581b3-f5d2-46bd-a42c-cfd8f013db2c', '收款', '', '9', 'jsp/fin/crashReceipt.jsp', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO tb_menu VALUES ('ffe51ba1-2682-4c27-a141-0422e2e25d78', '历史账单', '', '9', 'jsp/fin/crashHistory.jsp', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO tb_menu VALUES ('jsgl', '角色管理', null, null, 'jsp/comn/roleManager.jsp', 'xtgl');
INSERT INTO tb_menu VALUES ('xsbm', '学生报名', null, null, 'jsp/stu/stuSignup.jsp', 'xsgl');
INSERT INTO tb_menu VALUES ('xsgl', '学生管理', null, null, null, '0');
INSERT INTO tb_menu VALUES ('xtgl', '系统管理', null, null, null, '0');
INSERT INTO tb_menu VALUES ('yhgl', '用户管理', '', '1', 'jsp/comn/userManager.jsp', 'xtgl');

-- ----------------------------
-- Records of tb_menu
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
INSERT INTO tb_preferential VALUES ('08821df7-51b7-4efa-b740-1841b453a9aa', '高考一本线以上', '5000');
INSERT INTO tb_preferential VALUES ('ac2d906e-25c9-402b-b6f4-8c9e3248ba3a', '高考二本线以上', '2000');
INSERT INTO tb_preferential VALUES ('b68702de-5fbd-4590-887f-85e82cf9819d', '高三在读', '2000');

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
INSERT INTO tb_role VALUES ('0', '超级管理员', 'admin');
INSERT INTO tb_role VALUES ('233c43ba-a29a-45b3-bc0e-6192fd8b5059', '', '财务');
INSERT INTO tb_role VALUES ('fdd5e503-0638-4483-9773-74b2f32369d3', '', '报名老师');

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
INSERT INTO tb_role_menu VALUES ('137998fc-8bb7-4f75-943f-42a81c1791ee', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsbm');
INSERT INTO tb_role_menu VALUES ('30e454f1-e456-4d41-86c1-16575c82c048', '0', 'cdgl');
INSERT INTO tb_role_menu VALUES ('3e2c0817-08c3-4ee0-9274-ee32f18e98c6', '0', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO tb_role_menu VALUES ('40e450f9-ca3d-4126-aa50-d938755a2a3f', '0', 'xsbm');
INSERT INTO tb_role_menu VALUES ('49f3ba86-f22b-4001-80c6-fd626c8ee97c', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO tb_role_menu VALUES ('4bff7571-d9f2-487d-861e-4de4226f9717', '0', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');
INSERT INTO tb_role_menu VALUES ('7c29cb04-1f21-47bc-9152-52baae7e2e53', '0', 'jsgl');
INSERT INTO tb_role_menu VALUES ('88b1ee7f-7329-499f-80df-4e08f0487144', '0', 'yhgl');
INSERT INTO tb_role_menu VALUES ('8fa2b92c-cb34-4be0-bc46-653887058622', 'fdd5e503-0638-4483-9773-74b2f32369d3', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO tb_role_menu VALUES ('92095b03-93b1-41a8-8e86-29e92707d477', 'fdd5e503-0638-4483-9773-74b2f32369d3', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO tb_role_menu VALUES ('976efb15-840b-4ee4-9b5f-ea29be1f4511', '0', 'f81581b3-f5d2-46bd-a42c-cfd8f013db2c');
INSERT INTO tb_role_menu VALUES ('ab2380ab-1e44-4fc1-adb5-c4fa056ad660', 'fdd5e503-0638-4483-9773-74b2f32369d3', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');
INSERT INTO tb_role_menu VALUES ('ae6a5c89-6956-45c5-9326-d4ce634af802', '0', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO tb_role_menu VALUES ('aeaa2a53-6d09-4bb2-9d73-2095c7ecbc1b', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', 'f81581b3-f5d2-46bd-a42c-cfd8f013db2c');
INSERT INTO tb_role_menu VALUES ('ba864495-c5b5-4967-85d9-0d57428fcb36', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO tb_role_menu VALUES ('c2fcc8d4-8e24-4b13-ac80-c8e0e7d817e5', '0', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO tb_role_menu VALUES ('cbb31eeb-4d5b-44d7-ad9f-5f66af2905e0', 'fdd5e503-0638-4483-9773-74b2f32369d3', '1447677b-562e-4b58-98af-ebb26dcbeaca');
INSERT INTO tb_role_menu VALUES ('d7cbb547-1ecf-40c1-8260-88836932b460', '0', '1447677b-562e-4b58-98af-ebb26dcbeaca');
INSERT INTO tb_role_menu VALUES ('dd698d84-62f7-4a06-a9fb-1ebc9d0246a0', '0', '0');
INSERT INTO tb_role_menu VALUES ('e2d943e6-d234-4666-9c83-d7301261a54c', '0', 'xtgl');
INSERT INTO tb_role_menu VALUES ('ea360465-57c4-426d-b602-a5f64604a95e', '0', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO tb_role_menu VALUES ('ee280ad2-a8b3-4ac8-8ea2-c5d378ae8af8', '0', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO tb_role_menu VALUES ('f17587a1-f284-4049-bbbd-65b2213e351f', 'fdd5e503-0638-4483-9773-74b2f32369d3', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO tb_role_menu VALUES ('f18654b1-4fa8-4c48-bcc2-8fe1c2a33757', '0', 'xsgl');
INSERT INTO tb_role_menu VALUES ('f6d064b1-bdf0-4cc2-abab-0de786e6c875', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsgl');

-- ----------------------------
-- Table structure for `tb_student`
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
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
  `payee` varchar(36) default NULL,
  `preferential` varchar(36) default NULL,
  `file_id` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `student_class_type` (`class_type`),
  KEY `student_payee` (`payee`),
  KEY `student_preferential` (`preferential`),
  KEY `student_file` (`file_id`),
  CONSTRAINT `student_class_type` FOREIGN KEY (`class_type`) REFERENCES `tb_class_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `student_file` FOREIGN KEY (`file_id`) REFERENCES `tb_file` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_payee` FOREIGN KEY (`payee`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `student_preferential` FOREIGN KEY (`preferential`) REFERENCES `tb_preferential` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO tb_student VALUES ('4c165d8e-9dc2-4554-bda6-5b69631f33a3', 'chunpeng', '0', '0', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-11-03 12:29:28', null, null, '4f6ef1dc-360d-43b9-8bc9-3be6ecf0469e', '32000', '2500', '500', '100', '100', '100', '200', '35500', '1', 'd2eed059-84ea-40d8-9511-8649bba7bf46', 'ac2d906e-25c9-402b-b6f4-8c9e3248ba3a', '13c59508-1ea0-4573-8511-7aa9706df6c4');
INSERT INTO tb_student VALUES ('57598cb3-2fc7-4b5c-be79-20edd81cad47', '李阳', '0', '0', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-11-03 10:58:02', null, null, '4a0d7f10-210f-4cfd-b712-2bbc6a45aebb', '12800', '2500', '500', '100', '100', '100', '200', '16300', '1', '0', '08821df7-51b7-4efa-b740-1841b453a9aa', 'ed48b112-9cf6-4ca0-a067-ed8cd7d2152d');
INSERT INTO tb_student VALUES ('dbfcc1cc-2773-4208-ac07-64b9cf7973ec', '马晨', '0', '0', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-11-03 12:38:34', null, null, '4a0d7f10-210f-4cfd-b712-2bbc6a45aebb', '15500', '2500', '500', '100', '100', '100', '200', '19000', '1', '5170f6c4-783f-476b-a87f-a23f84ae1eea', 'b68702de-5fbd-4590-887f-85e82cf9819d', 'e0fffc86-6ccb-420d-bd49-2f9f7b9ef98e');
INSERT INTO tb_student VALUES ('f995738c-3e35-4047-8500-d5aebfb7d6ea', 'machen', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-29 01:00:10', null, null, '4a0d7f10-210f-4cfd-b712-2bbc6a45aebb', '13000', '2500', '500', '100', '100', '100', '200', '16500', '0', '0', '08821df7-51b7-4efa-b740-1841b453a9aa', '8c3ecd50-054d-40d8-9876-be692f6ea76b');

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
INSERT INTO tb_stu_signup VALUES ('0f632fc4-bc27-4475-bd4e-e25912b1a485', '回澍', '0', '0', null, '回邵增', '123123123', '南开', '穆醒', '1231231231', '南开', '120102230210230202', '天津市南开区', '12312313', '理工大学', '120', '120', '120', '120', '120', '120', '300', '700', '2', '0', '201', '123123', '12312231313', '0', '0', '123131313123123123131312312313213123213123123', null, null, null);
INSERT INTO tb_stu_signup VALUES ('23f6f286-8b82-4d0d-af18-8885302bf8bc', 'asdad', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 17:33:24', null, null);
INSERT INTO tb_stu_signup VALUES ('5d65b5b9-30d1-4f04-8bbc-f913aa88d781', 'huishu2', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 17:33:16', null, null);
INSERT INTO tb_stu_signup VALUES ('6d139eb6-90ee-4659-9b4a-2b80e8291ef2', 'shuhui', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 17:35:28', null, null);
INSERT INTO tb_stu_signup VALUES ('74a46090-3c02-4da5-8283-329d18100e93', '123123123', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 18:15:20', null, null);
INSERT INTO tb_stu_signup VALUES ('bb4dbea1-60a8-4460-bea5-6ebcfdfec616', '324324', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 18:08:40', null, null);
INSERT INTO tb_stu_signup VALUES ('c5ebf4fb-6de8-42e8-ac60-e26727513e16', '123132131', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 19:03:15', null, null);
INSERT INTO tb_stu_signup VALUES ('ce6d90ef-4b66-405f-84cb-4fab92a17a63', 'huishu2', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 17:32:41', null, null);
INSERT INTO tb_stu_signup VALUES ('d13e5444-70f2-41de-b055-9aa856ff2330', '123123ads', null, null, null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, '', '', '', '0', '0', '', '2013-10-12 18:15:10', null, null);

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
INSERT INTO tb_user VALUES ('4672aca7-38df-46d5-a4fe-a4e76c9a3f5f', 'huishu', '回澍', '21232f297a57a5a743894a0e4a801fc3', '2013-11-03 11:06:25', null);
INSERT INTO tb_user VALUES ('5170f6c4-783f-476b-a87f-a23f84ae1eea', 'liyang', '李阳', '21232f297a57a5a743894a0e4a801fc3', '2013-11-03 11:04:42', null);
INSERT INTO tb_user VALUES ('d2eed059-84ea-40d8-9511-8649bba7bf46', 'machen', '马晨', '21232f297a57a5a743894a0e4a801fc3', null, null);

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
INSERT INTO tb_user_role VALUES ('13629406-c11e-4ecb-96f1-9ad142ee57a0', '4672aca7-38df-46d5-a4fe-a4e76c9a3f5f', '233c43ba-a29a-45b3-bc0e-6192fd8b5059');
INSERT INTO tb_user_role VALUES ('32ce85cd-242e-4e72-99bf-aa9078781544', '5170f6c4-783f-476b-a87f-a23f84ae1eea', '233c43ba-a29a-45b3-bc0e-6192fd8b5059');
INSERT INTO tb_user_role VALUES ('51c706d0-043e-4b1b-81e3-5865cdf3ac54', '4672aca7-38df-46d5-a4fe-a4e76c9a3f5f', 'fdd5e503-0638-4483-9773-74b2f32369d3');
INSERT INTO tb_user_role VALUES ('ad23fdcb-afa3-404b-a960-57833eabc4aa', 'd2eed059-84ea-40d8-9511-8649bba7bf46', 'fdd5e503-0638-4483-9773-74b2f32369d3');
INSERT INTO tb_user_role VALUES ('e9ab9cf6-ebf7-4d2b-aa8c-5617ad71a4cd', '0', '0');

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
