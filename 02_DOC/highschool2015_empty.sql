/*
Navicat MySQL Data Transfer

Source Server         : mydb
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : highschool2015

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2015-05-22 00:04:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_class_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_class_info`;
CREATE TABLE `tb_class_info` (
  `id` varchar(36) NOT NULL,
  `class_mode` varchar(36) NOT NULL,
  `createdatetime` datetime default NULL,
  `name` varchar(40) NOT NULL,
  `class_type` varchar(36) default NULL,
  `payee` varchar(36) default NULL,
  `year_id` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_8adxs1u7v5dtvebrkkyljvi1d` (`class_type`),
  KEY `FK_mlvep9lx7f69cu5uo0opj75kh` (`payee`),
  KEY `FK_rewr4bsyr1p52nb39yn3xl7fc` (`year_id`),
  CONSTRAINT `FK_8adxs1u7v5dtvebrkkyljvi1d` FOREIGN KEY (`class_type`) REFERENCES `tb_class_type` (`id`),
  CONSTRAINT `FK_mlvep9lx7f69cu5uo0opj75kh` FOREIGN KEY (`payee`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FK_rewr4bsyr1p52nb39yn3xl7fc` FOREIGN KEY (`year_id`) REFERENCES `tb_year_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Records of tb_class_time
-- ----------------------------
INSERT INTO `tb_class_time` VALUES ('64c4537f-efd3-432b-9159-52ff1d63972c', '第三堂课', '14:00:00', '13:00:00');
INSERT INTO `tb_class_time` VALUES ('6678f01f-b1f9-4251-8a53-307a8c61b0ea', '第二堂课', '10:00:00', '09:00:00');
INSERT INTO `tb_class_time` VALUES ('f6f10a21-6f3a-4ad7-b0ab-31eb7553cdb3', '第一堂课', '08:00:00', '07:00:00');

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
  `professional_id` varchar(6) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_class_type
-- ----------------------------
INSERT INTO `tb_class_type` VALUES ('2cc7cfc0-ed4f-4765-bd80-be2dd31e12b1', '理科小班', '35000', '2500', '500', '100', '100', '100', '200', '38500', '112002');
INSERT INTO `tb_class_type` VALUES ('5eb46dfd-0c77-49ac-af99-738e1c710b92', '文科小班', '35000', '2500', '500', '100', '100', '100', '200', '38500', '112001');
INSERT INTO `tb_class_type` VALUES ('7072a732-6780-4d7b-a4df-fe52e582493c', '理科大班', '19000', '2500', '500', '100', '100', '100', '200', '22500', '112002');
INSERT INTO `tb_class_type` VALUES ('7ccf0f7d-5384-4352-bf8f-3f3f4eb85362', '退学', '0', '0', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `tb_class_type` VALUES ('811d78de-6ce3-47c7-a7f1-76c27a850617', '不复读', '0', '0', '0', '0', '0', '0', '0', '0', '');
INSERT INTO `tb_class_type` VALUES ('95b84869-cfda-407b-8e3c-71b7195f72d8', '理科0班', '25000', '2500', '500', '100', '100', '100', '200', '28500', '112002');
INSERT INTO `tb_class_type` VALUES ('cb4617ea-b88e-49c1-b3d6-d729bb7aa63d', '文科大班', '19000', '2500', '500', '100', '100', '100', '200', '22500', '112001');

-- ----------------------------
-- Table structure for `tb_deduction_fee`
-- ----------------------------
DROP TABLE IF EXISTS `tb_deduction_fee`;
CREATE TABLE `tb_deduction_fee` (
  `id` varchar(36) NOT NULL,
  `deduction_fee` decimal(22,0) default NULL,
  `end_date` datetime NOT NULL,
  `name` varchar(40) NOT NULL,
  `start_date` datetime NOT NULL,
  `status` varchar(36) NOT NULL,
  `time_type` varchar(36) NOT NULL,
  `year_id` varchar(36) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_pixyjmtloa8m1ovx5hltku6m5` (`year_id`),
  CONSTRAINT `FK_pixyjmtloa8m1ovx5hltku6m5` FOREIGN KEY (`year_id`) REFERENCES `tb_year_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `tb_dictionary` VALUES ('104', '分班方式', '0');
INSERT INTO `tb_dictionary` VALUES ('104001', '总分降序排列平行分', '104');
INSERT INTO `tb_dictionary` VALUES ('104002', '总分降序排列前后分', '104');
INSERT INTO `tb_dictionary` VALUES ('105', '是否', '0');
INSERT INTO `tb_dictionary` VALUES ('105001', '是', '105');
INSERT INTO `tb_dictionary` VALUES ('105002', '否', '105');
INSERT INTO `tb_dictionary` VALUES ('106', '学科', '0');
INSERT INTO `tb_dictionary` VALUES ('106001', '语文', '106');
INSERT INTO `tb_dictionary` VALUES ('106002', '数学', '106');
INSERT INTO `tb_dictionary` VALUES ('106003', '外语', '106');
INSERT INTO `tb_dictionary` VALUES ('106004', '历史/物理', '106');
INSERT INTO `tb_dictionary` VALUES ('106005', '地理/化学', '106');
INSERT INTO `tb_dictionary` VALUES ('106006', '政治/生物', '106');
INSERT INTO `tb_dictionary` VALUES ('106007', '文/理综', '106');
INSERT INTO `tb_dictionary` VALUES ('106008', '总分', '106');
INSERT INTO `tb_dictionary` VALUES ('107', '比较方式', '0');
INSERT INTO `tb_dictionary` VALUES ('107001', '成绩', '107');
INSERT INTO `tb_dictionary` VALUES ('107002', '班级名次', '107');
INSERT INTO `tb_dictionary` VALUES ('107003', '年级名次', '107');
INSERT INTO `tb_dictionary` VALUES ('108', '平均分', '0');
INSERT INTO `tb_dictionary` VALUES ('108001', '大于平均分', '108');
INSERT INTO `tb_dictionary` VALUES ('108002', '大于等于平均分', '108');
INSERT INTO `tb_dictionary` VALUES ('108003', '小于平均分', '108');
INSERT INTO `tb_dictionary` VALUES ('108004', '小于等于平均分', '108');
INSERT INTO `tb_dictionary` VALUES ('108005', '等于平均分', '108');
INSERT INTO `tb_dictionary` VALUES ('109', '平均分比较对象', '0');
INSERT INTO `tb_dictionary` VALUES ('109001', '班级平均分', '109');
INSERT INTO `tb_dictionary` VALUES ('109002', '年级平均分', '109');
INSERT INTO `tb_dictionary` VALUES ('110', '时间类型', '0');
INSERT INTO `tb_dictionary` VALUES ('110001', '按天', '110');
INSERT INTO `tb_dictionary` VALUES ('110002', '按月', '110');
INSERT INTO `tb_dictionary` VALUES ('110003', '按周', '110');
INSERT INTO `tb_dictionary` VALUES ('111', '状态', '0');
INSERT INTO `tb_dictionary` VALUES ('111001', '有效', '111');
INSERT INTO `tb_dictionary` VALUES ('111002', '无效', '111');
INSERT INTO `tb_dictionary` VALUES ('112', '专业', '0');
INSERT INTO `tb_dictionary` VALUES ('112001', '文科', '112');
INSERT INTO `tb_dictionary` VALUES ('112002', '理科', '112');
INSERT INTO `tb_dictionary` VALUES ('112003', '艺术文科', '112');
INSERT INTO `tb_dictionary` VALUES ('112004', '艺术理科', '112');
INSERT INTO `tb_dictionary` VALUES ('112005', '体育文科', '112');
INSERT INTO `tb_dictionary` VALUES ('112006', '艺术理科', '112');
INSERT INTO `tb_dictionary` VALUES ('113', '考场计算方式', '0');
INSERT INTO `tb_dictionary` VALUES ('113001', 'S型', '113');
INSERT INTO `tb_dictionary` VALUES ('113002', 'I型', '113');
INSERT INTO `tb_dictionary` VALUES ('114', '排序', '0');
INSERT INTO `tb_dictionary` VALUES ('114001', '降序', '114');
INSERT INTO `tb_dictionary` VALUES ('114002', '升序', '114');

-- ----------------------------
-- Table structure for `tb_dormitory_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_dormitory_info`;
CREATE TABLE `tb_dormitory_info` (
  `id` varchar(36) NOT NULL,
  `dormitory_count` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `people_count` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `tb_examination_room`
-- ----------------------------
DROP TABLE IF EXISTS `tb_examination_room`;
CREATE TABLE `tb_examination_room` (
  `id` varchar(36) NOT NULL default '',
  `dispatch_type` varchar(6) default NULL,
  `name` varchar(22) default NULL,
  `range_order` varchar(6) default NULL,
  `monthTime_id1` varchar(36) default NULL,
  `monthTime_id2` varchar(36) default NULL,
  `file_name` varchar(100) default NULL,
  `year_id` varchar(36) default NULL,
  `professional_id` varchar(6) default NULL,
  `column_count` varchar(6) default NULL,
  `row_count` varchar(6) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_lgqh8lyt6gbb60mubag25lotv` (`year_id`),
  CONSTRAINT `FK_lgqh8lyt6gbb60mubag25lotv` FOREIGN KEY (`year_id`) REFERENCES `tb_year_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_examination_room
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
  `deductionFee` decimal(22,0) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_atbixe4b0gwnmic7gewjxnkn2` (`payee`),
  KEY `finance_class_type` (`class_type`),
  KEY `FK_blaglmjn4k7hpal67wd2fhs3f` (`studentId`),
  CONSTRAINT `finance_class_type` FOREIGN KEY (`class_type`) REFERENCES `tb_class_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_blaglmjn4k7hpal67wd2fhs3f` FOREIGN KEY (`studentId`) REFERENCES `tb_student` (`id`),
  CONSTRAINT `tb_finance_ibfk_1` FOREIGN KEY (`payee`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_finance
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_gaokao_score`
-- ----------------------------
DROP TABLE IF EXISTS `tb_gaokao_score`;
CREATE TABLE `tb_gaokao_score` (
  `id` varchar(36) NOT NULL,
  `admission_school_name` varchar(150) default NULL,
  `createdatetime` datetime default NULL,
  `fraction_comp1` decimal(5,0) default NULL,
  `fraction_comp2` decimal(5,0) default NULL,
  `fraction_comp3` decimal(5,0) default NULL,
  `fraction_comp_count` decimal(5,0) default NULL,
  `fraction_count` decimal(5,0) default NULL,
  `fraction_english` decimal(5,0) default NULL,
  `fraction_language` decimal(5,0) default NULL,
  `fraction_math` decimal(5,0) default NULL,
  `gaokao_num` varchar(50) default NULL,
  `student_id` varchar(36) default NULL,
  `year_id` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_i5g2haijhhckeft0rnl6la8xe` (`student_id`),
  KEY `FK_77cuskiq0g2go8t8ua9u3389w` (`year_id`),
  CONSTRAINT `FK_77cuskiq0g2go8t8ua9u3389w` FOREIGN KEY (`year_id`) REFERENCES `tb_year_info` (`id`),
  CONSTRAINT `FK_i5g2haijhhckeft0rnl6la8xe` FOREIGN KEY (`student_id`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


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
  `classTimeId` varchar(36) NOT NULL,
  `remark` varchar(100) default NULL,
  `resultId` varchar(36) NOT NULL,
  `studentId` varchar(36) NOT NULL,
  `typeId` varchar(36) NOT NULL,
  `date` date default NULL,
  `is_delete` int(11) NOT NULL,
  `score` int(11) NOT NULL,
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
-- Records of tb_log_result
-- ----------------------------
INSERT INTO `tb_log_result` VALUES ('48f883d5-cb83-4cfa-a743-671b89be5d77', '小计');
INSERT INTO `tb_log_result` VALUES ('54549307-afa4-4d03-8660-54aa30297d13', '已确认');
INSERT INTO `tb_log_result` VALUES ('c6133865-a9a5-4d86-ba25-6895613d2b53', '待确认');

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
  `attence` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_log_type
-- ----------------------------
INSERT INTO `tb_log_type` VALUES ('14dcc657-aa97-4d41-908e-398a2100cbc2', '未出勤', '1', '102002', '0', '101001', '计入考勤', '1');
INSERT INTO `tb_log_type` VALUES ('51c9b56b-fac6-4bc6-8079-94fb2ee718be', '迟到', '1', '102002', '1', '101001', '不能删除', '0');
INSERT INTO `tb_log_type` VALUES ('6cd67b2a-3611-46f6-aeb0-e44e5f0e9b0b', '上课睡觉', '2', '102002', '1', '101001', '上课睡觉一次扣2分', '0');

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
INSERT INTO `tb_menu` VALUES ('0efc84da-0ca3-47e5-9f1e-9da9b2595986', '月考成绩', '', '4', 'jsp/director/monthScore.jsp', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('1447677b-562e-4b58-98af-ebb26dcbeaca', '报名缴费', 'icon-tip', '2', 'jsp/stu/stuPayment.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('1aa7767e-68a8-41eb-b7d5-7cb8478cbe9c', '分配考场', '', '2', 'jsp/comn/examinationRoom.jsp', '805bcc71-b41c-4f06-a7f2-6c2de2d917bc');
INSERT INTO `tb_menu` VALUES ('1e60c2f1-e4e1-4606-8c0e-cd7d85d0de98', '日志管理', '', '3', 'jsp/director/logManage.jsp', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('3106c0df-bdb5-40cb-a0d5-593f926fdc6c', '月考排名', '', '6', 'jsp/director/scoreCountShow.jsp', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('31472bb2-949e-4a5e-8d87-bfd7647424e5', '月考成绩计算', '', '3', 'jsp/comn/scoreCountShow.jsp', '805bcc71-b41c-4f06-a7f2-6c2de2d917bc');
INSERT INTO `tb_menu` VALUES ('46ec3a24-43a4-44fb-a9e7-d2315eb3c698', '学生管理', 'icon-search', '5', 'jsp/stu/studentManager.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('474f41e0-3bd3-4918-bac1-a777fad86692', '设定月考信息', '', '8', 'jsp/comn/monthInfo.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('47a5b8a7-4d07-43d5-9823-91e34f3ec33c', '设定课程时间', '', '5', 'jsp/comn/classTime.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('4a915675-6ffd-46f4-93f8-d2a9464bf9d6', '退款', 'icon-cancel', '4', 'jsp/stu/stuRefundment.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('5f9bb20e-887e-459a-baee-ac697d058775', '财务管理', '', '2', '', null);
INSERT INTO `tb_menu` VALUES ('6546cfdb-e0d1-48f0-bda7-734c5063fe20', '设定班级种别', '', '2', 'jsp/stu/classTypeManager.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('66679bce-4c9d-41d1-88c1-33f755a681c7', '月出勤汇总', '', '5', 'jsp/comn/attenceAllClass.jsp', '805bcc71-b41c-4f06-a7f2-6c2de2d917bc');
INSERT INTO `tb_menu` VALUES ('6a613a0c-41db-4d41-b0f6-06075eed18e4', '月考折线图', '', '5', 'jsp/director/fractionLine.jsp', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('701da494-93ca-49f3-9b9a-28774030e527', '设定报名优惠', '', '3', 'jsp/fin/preferentialManager.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('73b5aed7-f047-4b86-90b5-2ec1965429df', '设定学年', '', '1', 'jsp/comn/yearManager.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('7710e83e-7a57-40fb-965c-daa69958586a', '月出勤率', '', '4', 'jsp/comn/attence.jsp', '805bcc71-b41c-4f06-a7f2-6c2de2d917bc');
INSERT INTO `tb_menu` VALUES ('797287d9-5886-42ce-b0a5-49dd8b312d46', '分班', '', '1', 'jsp/comn/classDivide.jsp', '805bcc71-b41c-4f06-a7f2-6c2de2d917bc');
INSERT INTO `tb_menu` VALUES ('805bcc71-b41c-4f06-a7f2-6c2de2d917bc', '管理员操作', '', '4', '', null);
INSERT INTO `tb_menu` VALUES ('9e4fa011-ac95-44a7-91f7-09dd1fbff7dd', '设定扣费规则', '', '4', 'jsp/comn/deductionFee.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8', '学生信息修改历史', '', '8', 'jsp/stu/sutdentInfoHistory.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('b8b6ab5d-ee2e-4e7a-bc17-48db74bc6819', '设定宿舍信息', '', '9', 'jsp/comn/dormitoryManager.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('bzrgl', '班主任管理', '', '3', '', null);
INSERT INTO `tb_menu` VALUES ('cdb86281-4089-4b9b-a24f-9499724e6310', '设定日志结果', '', '7', 'jsp/comn/logResult.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('cdgl', '菜单管理', '', '2', 'jsp/comn/menuManager.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('d53505e7-b424-47dc-9f2f-2b1016e76f3b', '高考成绩', '', '8', 'jsp/director/gaokaoScore.jsp', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('d62355fe-2884-4e4e-a620-fa69515bce5f', '设定日志类型', '', '6', 'jsp/comn/logType.jsp', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_menu` VALUES ('e71b4c05-640b-40be-b6b1-2afca4026825', '日志日历', '', '2', 'jsp/director/logCalendar.jsp', 'bzrgl');
INSERT INTO `tb_menu` VALUES ('ecd940c2-7d84-4aac-8b74-24b08321848b', '管理员设定信息', '', '5', '', null);
INSERT INTO `tb_menu` VALUES ('ffe51ba1-2682-4c27-a141-0422e2e25d78', '历史账单', '', '2', 'jsp/fin/crashHistory.jsp', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_menu` VALUES ('jsgl', '角色管理', '', '3', 'jsp/comn/roleManager.jsp', 'xtgl');
INSERT INTO `tb_menu` VALUES ('xsbm', '学生报名', '', '1', 'jsp/stu/stuSignup.jsp', 'xsgl');
INSERT INTO `tb_menu` VALUES ('xsgl', '学生管理', '', '1', '', null);
INSERT INTO `tb_menu` VALUES ('xtgl', '系统管理', '', '6', '', null);
INSERT INTO `tb_menu` VALUES ('yhgl', '用户管理', '', '1', 'jsp/comn/userManager.jsp', 'xtgl');

-- ----------------------------
-- Table structure for `tb_month_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_month_info`;
CREATE TABLE `tb_month_info` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) NOT NULL,
  `value` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_month_info
-- ----------------------------
INSERT INTO `tb_month_info` VALUES ('17440bab-729c-4e37-958d-763481327dba', '第一次月考', '1');
INSERT INTO `tb_month_info` VALUES ('2ce2705b-8bdc-4b29-954b-5641d932a0f3', '第二次月考', '2');
INSERT INTO `tb_month_info` VALUES ('40ea56d8-9fe6-4768-a464-031605cc6e9c', '第三次月考', '3');
INSERT INTO `tb_month_info` VALUES ('5beabb80-e406-47df-b9f3-95d2f37a74c3', '入学高考成绩', '0');
INSERT INTO `tb_month_info` VALUES ('83c54fee-9016-4149-9c1f-4d9046cdfe70', '第四次月考', '4');

-- ----------------------------
-- Table structure for `tb_month_score`
-- ----------------------------
DROP TABLE IF EXISTS `tb_month_score`;
CREATE TABLE `tb_month_score` (
  `id` varchar(36) NOT NULL,
  `createdatetime` datetime default NULL,
  `fraction_comp1` decimal(5,0) NOT NULL,
  `fraction_comp2` decimal(5,0) NOT NULL,
  `fraction_comp3` decimal(5,0) NOT NULL,
  `fraction_comp_count` decimal(5,0) NOT NULL,
  `fraction_count` decimal(5,0) NOT NULL,
  `fraction_english` decimal(5,0) NOT NULL,
  `fraction_language` decimal(5,0) NOT NULL,
  `fraction_math` decimal(5,0) NOT NULL,
  `month_time_id` varchar(36) default NULL,
  `student_id` varchar(36) default NULL,
  `year_id` varchar(36) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_fnhgqftku4q7ls5m2a23ug6te` (`month_time_id`),
  KEY `FK_6i38djx00qokpclb1d23rx7at` (`student_id`),
  KEY `FK_iyw7eo4st0i8g1yok9x5if1s6` (`year_id`),
  CONSTRAINT `FK_6i38djx00qokpclb1d23rx7at` FOREIGN KEY (`student_id`) REFERENCES `tb_student` (`id`),
  CONSTRAINT `FK_fnhgqftku4q7ls5m2a23ug6te` FOREIGN KEY (`month_time_id`) REFERENCES `tb_month_info` (`id`),
  CONSTRAINT `FK_iyw7eo4st0i8g1yok9x5if1s6` FOREIGN KEY (`year_id`) REFERENCES `tb_year_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
INSERT INTO `tb_preferential` VALUES ('1dda29d4-b6fe-4d9c-96c6-70ae55a9cd6e', '一本线上', '5000');
INSERT INTO `tb_preferential` VALUES ('40672124-7f72-487c-a0e6-a8d20b8b691e', '应届生', '2000');
INSERT INTO `tb_preferential` VALUES ('bc5375b1-e621-49ee-a33c-7f56b3d1410b', '二本线上', '2000');

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
INSERT INTO `tb_role_menu` VALUES ('00f0117a-5135-421b-bf22-896f0a9e7b23', '0', '474f41e0-3bd3-4918-bac1-a777fad86692');
INSERT INTO `tb_role_menu` VALUES ('05cdb783-3896-400c-98ac-359265250e57', '0', 'ecd940c2-7d84-4aac-8b74-24b08321848b');
INSERT INTO `tb_role_menu` VALUES ('07fa4a20-87ac-4166-892a-d651e8d87a93', '0', '73b5aed7-f047-4b86-90b5-2ec1965429df');
INSERT INTO `tb_role_menu` VALUES ('08e047ed-62e0-4d22-9dd0-12332b858e2c', '0', '6a613a0c-41db-4d41-b0f6-06075eed18e4');
INSERT INTO `tb_role_menu` VALUES ('0e8a5e19-8798-4ed6-951f-61ca52154ca0', '0', 'ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8');
INSERT INTO `tb_role_menu` VALUES ('0e902da8-f4c9-4cc3-bd4a-45ef6e550e14', '0', '31472bb2-949e-4a5e-8d87-bfd7647424e5');
INSERT INTO `tb_role_menu` VALUES ('1308d2d7-c73a-470e-aa3f-a6cb6539b1d8', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', 'bzrgl');
INSERT INTO `tb_role_menu` VALUES ('1bd6a056-fe73-4aff-ae8f-5cf52120beeb', '0', '805bcc71-b41c-4f06-a7f2-6c2de2d917bc');
INSERT INTO `tb_role_menu` VALUES ('2043e615-0cd7-4431-9b8e-62425a517a71', '0', '4a915675-6ffd-46f4-93f8-d2a9464bf9d6');
INSERT INTO `tb_role_menu` VALUES ('20a44798-113d-41f9-b05d-1a94062c7745', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'ab44acb1-c54e-4d3f-ac7e-b26eb984e5c8');
INSERT INTO `tb_role_menu` VALUES ('214ac643-c28d-4be9-980d-36ae06d80e6b', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', '1e60c2f1-e4e1-4606-8c0e-cd7d85d0de98');
INSERT INTO `tb_role_menu` VALUES ('25e9d744-1636-40aa-bab0-cc5f6292d223', '0', 'cdgl');
INSERT INTO `tb_role_menu` VALUES ('2e91ede3-45be-430e-bbba-266f321d9f09', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', '6a613a0c-41db-4d41-b0f6-06075eed18e4');
INSERT INTO `tb_role_menu` VALUES ('36dc5fb6-e45d-4983-9f53-b41fd5d1cdb1', '0', '1aa7767e-68a8-41eb-b7d5-7cb8478cbe9c');
INSERT INTO `tb_role_menu` VALUES ('3ad8650e-0b5a-45e7-b3cc-20acf4c873bf', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', '3106c0df-bdb5-40cb-a0d5-593f926fdc6c');
INSERT INTO `tb_role_menu` VALUES ('453ffa71-c801-4ede-847d-b3cf89165591', '0', '6546cfdb-e0d1-48f0-bda7-734c5063fe20');
INSERT INTO `tb_role_menu` VALUES ('483419f5-aab9-435c-998c-e9a3937edf34', '0', '1e60c2f1-e4e1-4606-8c0e-cd7d85d0de98');
INSERT INTO `tb_role_menu` VALUES ('4851a3cb-bc1c-40a1-b697-cbcb255220ad', '0', 'b8b6ab5d-ee2e-4e7a-bc17-48db74bc6819');
INSERT INTO `tb_role_menu` VALUES ('53499b56-e0a0-4a7d-a233-eaff327baba5', 'fdd5e503-0638-4483-9773-74b2f32369d3', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO `tb_role_menu` VALUES ('552781d8-33f2-4073-b96c-4889bad7a915', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsgl');
INSERT INTO `tb_role_menu` VALUES ('5604f790-eb6a-4d21-b49c-1c8ecaa1da6b', '0', 'cdb86281-4089-4b9b-a24f-9499724e6310');
INSERT INTO `tb_role_menu` VALUES ('59c90096-7ea0-4232-b01b-759be413c367', '0', '3106c0df-bdb5-40cb-a0d5-593f926fdc6c');
INSERT INTO `tb_role_menu` VALUES ('59d4b4b5-d946-4ff4-b6b3-18f8b0fd7db8', '0', 'e71b4c05-640b-40be-b6b1-2afca4026825');
INSERT INTO `tb_role_menu` VALUES ('6b32ae4e-eca2-4831-a9f5-4452e01d92eb', '0', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO `tb_role_menu` VALUES ('715c6785-33e4-42e5-be89-5891f9ee76ac', '0', '7710e83e-7a57-40fb-965c-daa69958586a');
INSERT INTO `tb_role_menu` VALUES ('736b2a02-92da-44f6-b580-43e526f2d8d6', '0', '1447677b-562e-4b58-98af-ebb26dcbeaca');
INSERT INTO `tb_role_menu` VALUES ('7966749a-cfc2-4940-b314-108098f229bb', '0', 'd53505e7-b424-47dc-9f2f-2b1016e76f3b');
INSERT INTO `tb_role_menu` VALUES ('8307ddff-eb79-4c4e-a86c-3397b781e71d', '0', '9e4fa011-ac95-44a7-91f7-09dd1fbff7dd');
INSERT INTO `tb_role_menu` VALUES ('8a632d30-6270-4dc8-be03-3f2e96be5a69', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_role_menu` VALUES ('8d57d244-0c2b-4467-b56f-4caa63036362', 'fdd5e503-0638-4483-9773-74b2f32369d3', '4a915675-6ffd-46f4-93f8-d2a9464bf9d6');
INSERT INTO `tb_role_menu` VALUES ('8fb22476-2c6a-4b5e-a0fe-d6eee14627b5', '0', 'yhgl');
INSERT INTO `tb_role_menu` VALUES ('99d2f27f-649e-49ab-8345-666cb2558e58', '0', '5f9bb20e-887e-459a-baee-ac697d058775');
INSERT INTO `tb_role_menu` VALUES ('a6711d15-fc81-469b-ac00-17ecd1ef4cae', '0', 'bzrgl');
INSERT INTO `tb_role_menu` VALUES ('a7203bce-e4f6-413d-a6ee-376c25267dce', '0', 'jsgl');
INSERT INTO `tb_role_menu` VALUES ('a7425e0e-0b03-4407-844e-94e2e7769ea1', '0', 'xsgl');
INSERT INTO `tb_role_menu` VALUES ('ad680770-d37c-4b31-a37d-b9dca1510590', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', '0efc84da-0ca3-47e5-9f1e-9da9b2595986');
INSERT INTO `tb_role_menu` VALUES ('af8739fc-bdcf-4d6b-93b0-10e8ce22297e', '0', '797287d9-5886-42ce-b0a5-49dd8b312d46');
INSERT INTO `tb_role_menu` VALUES ('b1367c09-984b-4fa3-a7df-a545cdf93949', '0', '46ec3a24-43a4-44fb-a9e7-d2315eb3c698');
INSERT INTO `tb_role_menu` VALUES ('b533f7d5-8f14-42ae-851b-db2ce2705393', '233c43ba-a29a-45b3-bc0e-6192fd8b5059', 'ffe51ba1-2682-4c27-a141-0422e2e25d78');
INSERT INTO `tb_role_menu` VALUES ('b82f8a8c-629b-430d-bf4a-015b6550aa3c', '0', 'd62355fe-2884-4e4e-a620-fa69515bce5f');
INSERT INTO `tb_role_menu` VALUES ('b8faa5f9-ef9d-452d-b70f-ab35c45ad7df', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', '1aa7767e-68a8-41eb-b7d5-7cb8478cbe9c');
INSERT INTO `tb_role_menu` VALUES ('bbc6632a-3e1c-4bc3-bfde-3c468b068e2b', 'fdd5e503-0638-4483-9773-74b2f32369d3', '1447677b-562e-4b58-98af-ebb26dcbeaca');
INSERT INTO `tb_role_menu` VALUES ('bdf7fe7e-a029-4647-8a09-cd2580f2c27c', 'fdd5e503-0638-4483-9773-74b2f32369d3', 'xsbm');
INSERT INTO `tb_role_menu` VALUES ('be65b5ea-4769-4f18-a805-a3a35ecd12d6', '0', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');
INSERT INTO `tb_role_menu` VALUES ('c3f570a0-2dfe-470b-a9cc-bda5a4275fa2', '0', '66679bce-4c9d-41d1-88c1-33f755a681c7');
INSERT INTO `tb_role_menu` VALUES ('c5de49b0-af1c-4928-ba84-e55cfa1d5a75', '0', '701da494-93ca-49f3-9b9a-28774030e527');
INSERT INTO `tb_role_menu` VALUES ('c712247c-7acb-4fda-9a2f-8059bb521feb', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', 'd53505e7-b424-47dc-9f2f-2b1016e76f3b');
INSERT INTO `tb_role_menu` VALUES ('c7a9bac3-6052-4d99-9ab8-842e8119457d', '0', 'xtgl');
INSERT INTO `tb_role_menu` VALUES ('c9ed23c5-1e0a-47bf-be9a-52dfeb9708ab', '0', '47a5b8a7-4d07-43d5-9823-91e34f3ec33c');
INSERT INTO `tb_role_menu` VALUES ('cfc56aba-6494-4c27-88e3-383576a86393', 'df3ab88d-c5a2-4f82-aef0-597468fd70d0', 'e71b4c05-640b-40be-b6b1-2afca4026825');
INSERT INTO `tb_role_menu` VALUES ('e63304dd-bc97-41c9-a6f4-d62243cb2c8c', '0', '0efc84da-0ca3-47e5-9f1e-9da9b2595986');
INSERT INTO `tb_role_menu` VALUES ('f8cc0c9c-f751-4799-993b-6aa5c1c444cc', '0', 'xsbm');
INSERT INTO `tb_role_menu` VALUES ('fdf03e51-b297-4c44-bc80-80cc0c537218', 'fdd5e503-0638-4483-9773-74b2f32369d3', '0c77a745-90b3-4c8e-9848-60d1d806ad9d');

-- ----------------------------
-- Table structure for `tb_score_count`
-- ----------------------------
DROP TABLE IF EXISTS `tb_score_count`;
CREATE TABLE `tb_score_count` (
  `class_change` int(5) default NULL,
  `class_rank` int(5) default NULL,
  `grade_change` int(5) default NULL,
  `grade_rank` int(5) default NULL,
  `student_id` varchar(36) NOT NULL,
  `month_time2` varchar(36) NOT NULL,
  `month_time1` varchar(36) NOT NULL,
  `subject_id` varchar(6) NOT NULL,
  `year_id` varchar(36) default NULL,
  PRIMARY KEY  (`student_id`,`month_time2`,`month_time1`),
  KEY `FK_s8b0tvdkr0vexxr7wno63uymv` (`student_id`),
  KEY `FK_aa9nrkxhltrwmd3vhdhybvk6e` (`month_time2`),
  KEY `FK_orw22j3smw43mrr76062co0sj` (`month_time1`),
  KEY `FK_bl03tsu6hjluver6vbr3fxik9` (`year_id`),
  CONSTRAINT `FK_aa9nrkxhltrwmd3vhdhybvk6e` FOREIGN KEY (`month_time2`) REFERENCES `tb_month_info` (`id`),
  CONSTRAINT `FK_bl03tsu6hjluver6vbr3fxik9` FOREIGN KEY (`year_id`) REFERENCES `tb_year_info` (`id`),
  CONSTRAINT `FK_orw22j3smw43mrr76062co0sj` FOREIGN KEY (`month_time1`) REFERENCES `tb_month_info` (`id`),
  CONSTRAINT `FK_s8b0tvdkr0vexxr7wno63uymv` FOREIGN KEY (`student_id`) REFERENCES `tb_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_score_count
-- ----------------------------

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
  `score` int(11) NOT NULL,
  `class_id` varchar(36) default NULL,
  `year_id` varchar(36) default NULL,
  `payFinishdatetime` datetime default NULL,
  `nativePlace` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  KEY `student_class_type` (`class_type`),
  KEY `student_payee` (`payee`),
  KEY `student_preferential` (`preferential`),
  KEY `student_file` (`file_id`),
  KEY `student_id_file` (`id_file_id`),
  KEY `student_photo_file` (`photo_file_id`),
  KEY `student_report_file` (`report_file_id`),
  KEY `FK_rjce9uirl5pr4on9lbr9au2fb` (`class_id`),
  KEY `FK_7gslw5tbsaa6ror1ipvft8wx3` (`year_id`),
  KEY `FK_5q1gp5wbbkrhsfx08gn84was7` (`dormitory_num`),
  CONSTRAINT `FK_5q1gp5wbbkrhsfx08gn84was7` FOREIGN KEY (`dormitory_num`) REFERENCES `tb_dormitory_info` (`id`),
  CONSTRAINT `FK_7gslw5tbsaa6ror1ipvft8wx3` FOREIGN KEY (`year_id`) REFERENCES `tb_year_info` (`id`),
  CONSTRAINT `FK_rjce9uirl5pr4on9lbr9au2fb` FOREIGN KEY (`class_id`) REFERENCES `tb_class_info` (`id`),
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
INSERT INTO `tb_user` VALUES ('0', 'admin', '宋校', '098f6bcd4621d373cade4e832627b4f6', null, null);
INSERT INTO `tb_user` VALUES ('1fdd8f4d-5c65-4e94-9629-be08827a25e9', '冯磊', '冯磊', '6d1ce7aa0a1d988dc96a2abcd187b45a', null, null);
INSERT INTO `tb_user` VALUES ('5a196425-d7a5-40a0-89f7-d2e528a15925', 'machen', 'machen', '5be98f122b45fdaef4ffd851588edef7', '2014-07-30 15:18:25', null);
INSERT INTO `tb_user` VALUES ('8112ea42-6949-4f4c-ab40-e1767b8b761f', 'test', 'test', '098f6bcd4621d373cade4e832627b4f6', '2014-05-17 10:35:51', null);
INSERT INTO `tb_user` VALUES ('8feb5787-c675-40e1-b268-bbbabfdec0e7', '龙明磊', '龙明磊', '209d439cb668c11fc8657c4d90dee1d2', null, null);
INSERT INTO `tb_user` VALUES ('ba9683ed-0436-4349-8d40-36a7283e1f9e', '槐睿毅', '槐睿毅', '5a4629189a54ce51bd2018227e923bbc', '2014-06-19 13:31:47', null);
INSERT INTO `tb_user` VALUES ('e24250c9-e6de-47e5-a733-de446762142d', '杨阳', '杨阳', '0c52985d5de5ae5df317ab435ac6b2b6', null, null);
INSERT INTO `tb_user` VALUES ('f5e71755-bf4b-4d9f-883a-33b5522e7e8b', '马辰', '马辰', '5be98f122b45fdaef4ffd851588edef7', '2014-06-19 13:32:21', null);

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
INSERT INTO `tb_user_role` VALUES ('00fa3755-60ac-4f5f-9cf8-21554b42d65e', '0', '0');
INSERT INTO `tb_user_role` VALUES ('3e1161f4-8abf-4572-8ec0-f54ae56337b9', '8feb5787-c675-40e1-b268-bbbabfdec0e7', 'fdd5e503-0638-4483-9773-74b2f32369d3');
INSERT INTO `tb_user_role` VALUES ('97f09f2c-ed90-4a53-967c-999732878d1b', '1fdd8f4d-5c65-4e94-9629-be08827a25e9', 'fdd5e503-0638-4483-9773-74b2f32369d3');
INSERT INTO `tb_user_role` VALUES ('df490480-5d32-4a80-ab86-79c1fd9ba6ee', 'e24250c9-e6de-47e5-a733-de446762142d', 'fdd5e503-0638-4483-9773-74b2f32369d3');

-- ----------------------------
-- Table structure for `tb_year_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_year_info`;
CREATE TABLE `tb_year_info` (
  `id` varchar(36) NOT NULL,
  `is_default` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_year_info
-- ----------------------------
INSERT INTO `tb_year_info` VALUES ('530801be-37e9-4b9d-a331-b83cc778dac9', '105001', '2014');
INSERT INTO `tb_year_info` VALUES ('d3fd8eef-d364-4919-944f-19263b5c65f2', '105002', '2015');
