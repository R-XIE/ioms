/*
Navicat MySQL Data Transfer

Source Server         : dev.iitdev.com.ioms
Source Server Version : 50616
Source Host           : dev.iitdev.com:3306
Source Database       : ioms

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2014-10-17 10:48:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_branch
-- ----------------------------
DROP TABLE IF EXISTS `b_branch`;
CREATE TABLE `b_branch` (
  `branch_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `branch_name` varchar(50) NOT NULL COMMENT '部门名称',
  `branch_desc` varchar(100) DEFAULT NULL COMMENT '部门描述',
  PRIMARY KEY (`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_branch
-- ----------------------------
INSERT INTO `b_branch` VALUES ('1', '开发部', '开发部门的描述');
INSERT INTO `b_branch` VALUES ('2', '人事行政部', '人事行政部门');
INSERT INTO `b_branch` VALUES ('3', '总经办', '');
INSERT INTO `b_branch` VALUES ('4', '市场部', '');
INSERT INTO `b_branch` VALUES ('5', '销售部', '');
INSERT INTO `b_branch` VALUES ('6', '企划部', '');

-- ----------------------------
-- Table structure for b_position
-- ----------------------------
DROP TABLE IF EXISTS `b_position`;
CREATE TABLE `b_position` (
  `position_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `branch_id` int(11) DEFAULT NULL,
  `position_name` varchar(50) NOT NULL COMMENT '职位名称( 职位，就是主管、经理  )',
  `position_desc` varchar(100) DEFAULT NULL COMMENT '职位描述',
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_position
-- ----------------------------
INSERT INTO `b_position` VALUES ('1', '1', 'JSP开发工程师', 'JSP开发描述');
INSERT INTO `b_position` VALUES ('2', '1', 'PHP开发工程师', '');
INSERT INTO `b_position` VALUES ('3', '1', '.Net开发工程师', '');
INSERT INTO `b_position` VALUES ('4', '1', 'IOS开发工程师', '');
INSERT INTO `b_position` VALUES ('5', '1', '安卓开发工程师', '');
INSERT INTO `b_position` VALUES ('6', '1', '运维工程师', '');
INSERT INTO `b_position` VALUES ('7', '1', '美工设计', '');
INSERT INTO `b_position` VALUES ('8', '1', '前端', '');
INSERT INTO `b_position` VALUES ('9', '2', '行政专员', '');
INSERT INTO `b_position` VALUES ('10', '2', '人事专员', '');
INSERT INTO `b_position` VALUES ('11', '4', '市场', '');
INSERT INTO `b_position` VALUES ('12', '6', '文案', '');
INSERT INTO `b_position` VALUES ('13', '4', '网编', '');
INSERT INTO `b_position` VALUES ('14', '3', '总经理', '');
INSERT INTO `b_position` VALUES ('15', '3', '副总经理', '');

-- ----------------------------
-- Table structure for b_post
-- ----------------------------
DROP TABLE IF EXISTS `b_post`;
CREATE TABLE `b_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称(岗位就是比如说IOS变成做云去了)',
  `post_desc` varchar(50) NOT NULL COMMENT '岗位描述',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_post
-- ----------------------------
INSERT INTO `b_post` VALUES ('1', '普通专员', '普通专员描述');
INSERT INTO `b_post` VALUES ('2', '经理', '');

-- ----------------------------
-- Table structure for b_project
-- ----------------------------
DROP TABLE IF EXISTS `b_project`;
CREATE TABLE `b_project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目ID',
  `project_code` varchar(255) DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `staff_id` int(11) DEFAULT NULL COMMENT '项目负责人',
  `project_description` varchar(255) DEFAULT NULL COMMENT '项目描述',
  PRIMARY KEY (`project_id`),
  KEY `project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_project
-- ----------------------------

-- ----------------------------
-- Table structure for b_project_staff
-- ----------------------------
DROP TABLE IF EXISTS `b_project_staff`;
CREATE TABLE `b_project_staff` (
  `project_staff_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `staff_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `project_id` int(11) DEFAULT NULL COMMENT '项目ID',
  `project_duty` varchar(255) DEFAULT NULL COMMENT ' 项目职责',
  PRIMARY KEY (`project_staff_id`),
  KEY `project_staff_id` (`project_staff_id`) USING BTREE,
  KEY `project_id` (`project_id`) USING BTREE,
  KEY `staff_id` (`staff_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of b_project_staff
-- ----------------------------

-- ----------------------------
-- Table structure for bo_book
-- ----------------------------
DROP TABLE IF EXISTS `bo_book`;
CREATE TABLE `bo_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `book_code` varchar(50) NOT NULL COMMENT '图书编号',
  `book_type` varchar(11) DEFAULT NULL COMMENT '类型ID',
  `book_name` varchar(50) NOT NULL COMMENT '图书名称',
  `book_price` decimal(7,2) NOT NULL COMMENT '图书作者',
  `book_input_date` datetime NOT NULL COMMENT '图书录入日期',
  `book_state` int(1) NOT NULL COMMENT '图书状态｛已借出,在库,丢失｝',
  `book_desc` varchar(200) DEFAULT NULL COMMENT '图书描述',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bo_book
-- ----------------------------

-- ----------------------------
-- Table structure for bo_book_record
-- ----------------------------
DROP TABLE IF EXISTS `bo_book_record`;
CREATE TABLE `bo_book_record` (
  `book_record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `staff_id` int(11) NOT NULL COMMENT '借阅人',
  `book_id` int(11) NOT NULL COMMENT '借阅书籍',
  `borrow_date` datetime NOT NULL COMMENT '借阅日期',
  `return_date` datetime DEFAULT NULL COMMENT '归还日期',
  PRIMARY KEY (`book_record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bo_book_record
-- ----------------------------

-- ----------------------------
-- Table structure for bo_book_type
-- ----------------------------
DROP TABLE IF EXISTS `bo_book_type`;
CREATE TABLE `bo_book_type` (
  `book_type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `book_type_name` varchar(500) NOT NULL COMMENT '图书类型名称',
  `book_type_desc` varchar(200) DEFAULT NULL COMMENT '图书类型描述',
  PRIMARY KEY (`book_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bo_book_type
-- ----------------------------
INSERT INTO `bo_book_type` VALUES ('1', '计算机类别', '');
INSERT INTO `bo_book_type` VALUES ('2', '其他类别', '');

-- ----------------------------
-- Table structure for bu_business_travel
-- ----------------------------
DROP TABLE IF EXISTS `bu_business_travel`;
CREATE TABLE `bu_business_travel` (
  `business_travel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `travel_staff` int(11) NOT NULL COMMENT '请假人',
  `travel_start_time` datetime NOT NULL COMMENT '精确到小时 开始时间',
  `travel_end_time` datetime NOT NULL COMMENT '结束时间',
  `travel_address` varchar(255) NOT NULL COMMENT '地址',
  `travel_goal` varchar(255) NOT NULL COMMENT '目的',
  `travel_to_gether` varchar(255) DEFAULT NULL COMMENT '同行人',
  `travel_vehicle` varchar(255) NOT NULL COMMENT '交通工具',
  `travel_invoice_num` bigint(11) DEFAULT NULL,
  `travel_ticket` decimal(7,2) DEFAULT NULL COMMENT '机票费',
  `travel_fare` decimal(7,2) DEFAULT NULL COMMENT '火车费',
  `travel_fares` decimal(7,2) DEFAULT NULL COMMENT '车船费',
  `travel_transport` decimal(7,2) DEFAULT NULL COMMENT '市内交通',
  `travel_allowance` decimal(7,2) DEFAULT NULL COMMENT '出差补助',
  `travel_accommodation` decimal(7,2) DEFAULT NULL COMMENT '住宿费',
  `travel_other` decimal(7,2) DEFAULT NULL,
  `travel_trip` varchar(255) DEFAULT NULL COMMENT '行程',
  PRIMARY KEY (`business_travel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bu_business_travel
-- ----------------------------

-- ----------------------------
-- Table structure for c_cost
-- ----------------------------
DROP TABLE IF EXISTS `c_cost`;
CREATE TABLE `c_cost` (
  `cost_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost_type` bigint(20) NOT NULL,
  `cost_money` decimal(20,2) NOT NULL,
  `cost_desc` varchar(255) DEFAULT NULL,
  `cost_time` datetime NOT NULL,
  `cost_create_time` datetime NOT NULL,
  `cost_detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cost_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_cost
-- ----------------------------

-- ----------------------------
-- Table structure for c_cost_type
-- ----------------------------
DROP TABLE IF EXISTS `c_cost_type`;
CREATE TABLE `c_cost_type` (
  `cost_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cost_type_name` varchar(255) NOT NULL,
  `cost_type_super` bigint(20) DEFAULT NULL,
  `cost_type_inex` smallint(6) NOT NULL,
  `cost_type_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cost_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_cost_type
-- ----------------------------
INSERT INTO `c_cost_type` VALUES ('1', '固定资产', null, '0', '');
INSERT INTO `c_cost_type` VALUES ('2', '办公用品', null, '0', '');
INSERT INTO `c_cost_type` VALUES ('3', '管理费用', null, '0', '');
INSERT INTO `c_cost_type` VALUES ('4', '汽车费用', null, '0', '');
INSERT INTO `c_cost_type` VALUES ('5', '招待费用', null, '0', '');
INSERT INTO `c_cost_type` VALUES ('6', '其他费用', null, '0', '');
INSERT INTO `c_cost_type` VALUES ('7', '主营业务收入', null, '1', '');
INSERT INTO `c_cost_type` VALUES ('8', '其他业务收入', null, '1', '');
INSERT INTO `c_cost_type` VALUES ('9', '工资', '3', '0', '');
INSERT INTO `c_cost_type` VALUES ('10', '福利', '3', '0', '保险和公积金');
INSERT INTO `c_cost_type` VALUES ('11', '差旅费', '3', '0', '');
INSERT INTO `c_cost_type` VALUES ('12', '交通费', '3', '0', '');
INSERT INTO `c_cost_type` VALUES ('13', '税费', '3', '0', '');
INSERT INTO `c_cost_type` VALUES ('14', '其他费用', '3', '0', '');
INSERT INTO `c_cost_type` VALUES ('15', '加油费', '4', '0', '');
INSERT INTO `c_cost_type` VALUES ('16', '过路费', '4', '0', '');
INSERT INTO `c_cost_type` VALUES ('17', '停车费', '4', '0', '');
INSERT INTO `c_cost_type` VALUES ('18', '其他费用', '4', '0', '');
INSERT INTO `c_cost_type` VALUES ('19', '餐饮费', '5', '0', '');
INSERT INTO `c_cost_type` VALUES ('20', '住宿费', '5', '0', '');
INSERT INTO `c_cost_type` VALUES ('21', '其他费用', '5', '0', '');
INSERT INTO `c_cost_type` VALUES ('22', '房屋租凭费', '6', '0', '');
INSERT INTO `c_cost_type` VALUES ('23', '物业费', '6', '0', '');
INSERT INTO `c_cost_type` VALUES ('24', '电费', '6', '0', '');
INSERT INTO `c_cost_type` VALUES ('25', '固定电话及宽带费', '6', '0', '');
INSERT INTO `c_cost_type` VALUES ('26', '专利申报费用', '6', '0', '');
INSERT INTO `c_cost_type` VALUES ('27', '政府扶持', '8', '1', '');
INSERT INTO `c_cost_type` VALUES ('28', '专项资金', '8', '1', '');
INSERT INTO `c_cost_type` VALUES ('29', '其他收入', '8', '1', '');

-- ----------------------------
-- Table structure for m_interview
-- ----------------------------
DROP TABLE IF EXISTS `m_interview`;
CREATE TABLE `m_interview` (
  `interview_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `interview_name` varchar(50) NOT NULL COMMENT '面试姓名（应聘人）',
  `interview_to_name` varchar(50) NOT NULL COMMENT '面试官',
  `interview_sex` int(11) NOT NULL COMMENT '面试性别',
  `interview_position` int(11) NOT NULL COMMENT '面试岗位',
  `interview_date` datetime NOT NULL COMMENT '面试职位',
  `interview_phone` varchar(50) NOT NULL COMMENT '面试联系方式',
  `interview_current` smallint(3) NOT NULL COMMENT '当前状况',
  `interview_current_work` varchar(50) NOT NULL COMMENT '现工作单位',
  `interview_quit` varchar(50) NOT NULL COMMENT '跳槽原因',
  `interview_salary` decimal(8,2) NOT NULL COMMENT '期望薪水',
  `interview_score` decimal(4,2) NOT NULL COMMENT '面试评分',
  `interview_result` smallint(3) NOT NULL COMMENT '面试结果',
  `interview_CV` varchar(100) DEFAULT NULL COMMENT '面试简历,上传word或者上传zip压缩',
  `create_time` datetime NOT NULL COMMENT '记录日期',
  PRIMARY KEY (`interview_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_interview
-- ----------------------------

-- ----------------------------
-- Table structure for m_leave
-- ----------------------------
DROP TABLE IF EXISTS `m_leave`;
CREATE TABLE `m_leave` (
  `leave_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `staff_id` int(11) NOT NULL COMMENT '请假人',
  `leave_type` int(11) NOT NULL COMMENT '请假类别[事假、病假、婚假、丧假、公假、工伤、产假、护理假、其他福利假]',
  `leave_subject` varchar(100) NOT NULL COMMENT '请假事由',
  `leave_date_begin` datetime NOT NULL COMMENT '请假开始时间',
  `leave_date_end` datetime NOT NULL COMMENT '请假结束时间(自动计算共计x天y时)',
  `leave_days` decimal(10,2) NOT NULL,
  `create_date` datetime NOT NULL COMMENT '填写时间',
  PRIMARY KEY (`leave_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_leave
-- ----------------------------

-- ----------------------------
-- Table structure for m_salary
-- ----------------------------
DROP TABLE IF EXISTS `m_salary`;
CREATE TABLE `m_salary` (
  `salary_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `salary_staff` int(11) NOT NULL COMMENT '用户',
  `salary_date_months` varchar(10) NOT NULL COMMENT '月份',
  `salary_attendance` decimal(4,2) NOT NULL COMMENT '应出勤',
  `salary_overtime` decimal(4,0) DEFAULT NULL,
  `salary_fact_attendance` decimal(4,2) NOT NULL COMMENT '实际出勤',
  `salary_base` decimal(8,2) NOT NULL COMMENT '基本工资',
  `salary_position` decimal(8,2) NOT NULL COMMENT '岗位工资',
  `salary_performance` decimal(8,2) NOT NULL COMMENT '绩效工资',
  `salary_add` decimal(8,2) DEFAULT NULL COMMENT '其他工资(补贴)',
  `salary_supply` decimal(8,2) DEFAULT NULL COMMENT '补发工资',
  `salary_absence` decimal(8,2) DEFAULT NULL COMMENT '缺勤',
  `salary_performance_sub` decimal(8,2) DEFAULT NULL COMMENT '绩效扣项',
  `salary_late` decimal(8,2) DEFAULT NULL COMMENT '迟到早退',
  `salary_insurance` decimal(8,2) DEFAULT NULL COMMENT '保险',
  `salary_other` decimal(8,2) DEFAULT NULL COMMENT '其他',
  `salary_tax` decimal(8,2) DEFAULT NULL COMMENT '个税',
  PRIMARY KEY (`salary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_salary
-- ----------------------------

-- ----------------------------
-- Table structure for m_salary_alter
-- ----------------------------
DROP TABLE IF EXISTS `m_salary_alter`;
CREATE TABLE `m_salary_alter` (
  `alter_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `staff_id` int(11) NOT NULL COMMENT '申请人的名字、部门、职位、入职日期',
  `alter_cause` varchar(11) NOT NULL COMMENT '异动原因[调薪、调岗、调职、转正]',
  `alter_before` decimal(7,2) NOT NULL COMMENT '原薪资标准',
  `alter_after` decimal(7,2) NOT NULL COMMENT '异动后薪资标准',
  `alter_date` varchar(20) NOT NULL COMMENT '生效月份',
  `alter_create_date` datetime NOT NULL,
  PRIMARY KEY (`alter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_salary_alter
-- ----------------------------

-- ----------------------------
-- Table structure for m_staff
-- ----------------------------
DROP TABLE IF EXISTS `m_staff`;
CREATE TABLE `m_staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `staff_code` varchar(255) NOT NULL COMMENT '用户编号(与打卡机结合)',
  `staff_login_name` varchar(255) DEFAULT NULL COMMENT '用户登录名',
  `staff_real_name` varchar(255) DEFAULT NULL COMMENT '用户真实名',
  `staff_icon` varchar(255) DEFAULT NULL,
  `staff_pwd` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `staff_sex` int(11) DEFAULT NULL COMMENT '性别',
  `post_id` int(11) DEFAULT NULL COMMENT '职位',
  `position_id` int(11) DEFAULT NULL COMMENT '岗位',
  `role_id` int(11) DEFAULT NULL COMMENT '角色',
  `staff_email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `staff_id_card` varchar(255) DEFAULT NULL COMMENT '用户身份证号',
  `staff_phone` varchar(255) DEFAULT NULL COMMENT '用户联系方式',
  `staff_emergency` varchar(255) DEFAULT NULL COMMENT '紧急联系人和方式',
  `staff_address` varchar(255) DEFAULT NULL COMMENT '地址',
  `staff_degree` smallint(10) DEFAULT NULL COMMENT '学历',
  `staff_graduation` varchar(255) DEFAULT NULL COMMENT '毕业学校',
  `staff_major` varchar(255) DEFAULT NULL COMMENT '专业',
  `staff_major_date` datetime DEFAULT NULL COMMENT '毕业时间',
  `staff_entry_date` datetime DEFAULT NULL COMMENT '入职时间',
  `staff_contract_date` datetime DEFAULT NULL COMMENT '签到合同日期',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `staff_state` int(11) DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`staff_id`),
  KEY `staff_id` (`staff_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='毕业时间目的为了体现用户是否为应届生.用';

-- ----------------------------
-- Records of m_staff
-- ----------------------------
INSERT INTO `m_staff` VALUES ('1', '0001', 'admin', '超级管理员', null, '6e4e01e30ccff52ec112c1d301fa744d', '0', '1', '1', '1', 'cuisongliu@qq.com', '142431199005220038', '15342192530', '15342192530', '天津市塘沽区1', '3', '江西理工大学', '计算机', '2014-07-18 00:00:00', '2014-07-18 00:00:00', '2014-08-29 00:00:00', '2014-07-18 17:19:13', '1');

-- ----------------------------
-- Table structure for p_permissions
-- ----------------------------
DROP TABLE IF EXISTS `p_permissions`;
CREATE TABLE `p_permissions` (
  `permissions_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `resources_id` int(11) NOT NULL COMMENT '资源ID',
  PRIMARY KEY (`permissions_id`),
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `resources_id` (`resources_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_permissions
-- ----------------------------
INSERT INTO `p_permissions` VALUES ('1', '1', '10');
INSERT INTO `p_permissions` VALUES ('5', '1', '20');
INSERT INTO `p_permissions` VALUES ('8', '1', '21');
INSERT INTO `p_permissions` VALUES ('9', '1', '22');
INSERT INTO `p_permissions` VALUES ('11', '1', '30');
INSERT INTO `p_permissions` VALUES ('12', '1', '31');
INSERT INTO `p_permissions` VALUES ('15', '1', '32');
INSERT INTO `p_permissions` VALUES ('16', '1', '33');
INSERT INTO `p_permissions` VALUES ('17', '1', '40');
INSERT INTO `p_permissions` VALUES ('18', '1', '41');
INSERT INTO `p_permissions` VALUES ('19', '1', '44');
INSERT INTO `p_permissions` VALUES ('20', '1', '51');
INSERT INTO `p_permissions` VALUES ('21', '1', '34');
INSERT INTO `p_permissions` VALUES ('23', '1', '42');
INSERT INTO `p_permissions` VALUES ('24', '1', '43');
INSERT INTO `p_permissions` VALUES ('25', '3', '20');
INSERT INTO `p_permissions` VALUES ('26', '3', '21');
INSERT INTO `p_permissions` VALUES ('27', '3', '22');
INSERT INTO `p_permissions` VALUES ('28', '3', '10');
INSERT INTO `p_permissions` VALUES ('29', '3', '30');
INSERT INTO `p_permissions` VALUES ('30', '3', '31');
INSERT INTO `p_permissions` VALUES ('31', '3', '32');
INSERT INTO `p_permissions` VALUES ('32', '3', '33');
INSERT INTO `p_permissions` VALUES ('33', '3', '34');
INSERT INTO `p_permissions` VALUES ('34', '4', '40');
INSERT INTO `p_permissions` VALUES ('35', '4', '43');
INSERT INTO `p_permissions` VALUES ('36', '4', '41');
INSERT INTO `p_permissions` VALUES ('37', '4', '42');
INSERT INTO `p_permissions` VALUES ('38', '4', '44');

-- ----------------------------
-- Table structure for p_role
-- ----------------------------
DROP TABLE IF EXISTS `p_role`;
CREATE TABLE `p_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`),
  KEY `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_role
-- ----------------------------
INSERT INTO `p_role` VALUES ('1', '超级管理员', '超级管理员角色');
INSERT INTO `p_role` VALUES ('2', '普通会员', '普通会员信息');
INSERT INTO `p_role` VALUES ('3', '人事', '');
INSERT INTO `p_role` VALUES ('4', '行政', '');

-- ----------------------------
-- Table structure for w_weekly
-- ----------------------------
DROP TABLE IF EXISTS `w_weekly`;
CREATE TABLE `w_weekly` (
  `weekly_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `weekly_begin_date` date NOT NULL COMMENT '周报开始日期',
  `weekly_end_date` date NOT NULL COMMENT '周报结束日期',
  `weekly_staff` bigint(20) NOT NULL COMMENT '用户',
  `weekly_last_summary` text NOT NULL COMMENT '上周工作总结',
  `weekly_this_summary` text NOT NULL COMMENT '本周工作内容',
  `weekly_next_summary` text NOT NULL COMMENT '下周工作',
  `weekly_problem` text COMMENT '工作遇到的问题',
  `work_notes` text COMMENT '工作注意事项',
  `weekly_create_date` date NOT NULL COMMENT '编写日期',
  `weekly_state` smallint(6) NOT NULL,
  PRIMARY KEY (`weekly_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of w_weekly
-- ----------------------------


-- ----------------------------
-- View structure for v_leave
-- ----------------------------
DROP VIEW IF EXISTS `v_leave`;
CREATE OR REPLACE VIEW `v_leave` AS select `m_leave`.`leave_id` AS `leave_id`,`m_leave`.`staff_id` AS `staff_id`,`m_leave`.`leave_type` AS `leave_type`,`m_leave`.`leave_subject` AS `leave_subject`,`m_leave`.`leave_date_begin` AS `leave_date_begin`,`m_leave`.`leave_date_end` AS `leave_date_end`,`m_leave`.`leave_days` AS `leave_days`,`m_leave`.`create_date` AS `create_date`,date_format(`m_leave`.`leave_date_begin`,'%Y.%m') AS `leave_date_months` from `m_leave` ;


ALTER TABLE `m_staff` ADD `leave_date` VARCHAR(255)  COMMENT '离职时间';
ALTER TABLE `m_staff` ADD `leave_remark` VARCHAR(255)  COMMENT '离职原因';
--modify by 2.1
-- ----------------------------
-- Table structure for o_pwd
-- ----------------------------
DROP TABLE IF EXISTS `o_pwd`;
CREATE TABLE `o_pwd` (
  `pwd_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pwd_name` varchar(255) NOT NULL COMMENT '敏感信息标题',
  `pwd_location` varchar(255) NOT NULL COMMENT '敏感信息位置',
  `pwd_remark` varchar(500) DEFAULT NULL COMMENT '敏感信息内容',
  PRIMARY KEY (`pwd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for o_log
-- ----------------------------
DROP TABLE IF EXISTS `o_log`;
CREATE TABLE `o_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_location` varchar(255) NOT NULL  COMMENT '产生修改位置',
  `log_title` varchar(1000) NOT NULL COMMENT '记录修改内容',
  `log_cause` varchar(255) NOT NULL COMMENT '记录修改原因',
  `log_content` longtext NOT NULL COMMENT '记录修改方法',
  `log_staff` int NOT NULL COMMENT '记录者',
  `log_date` datetime NOT NULL COMMENT '记录日期',  
  `log_state` int NOT NULL COMMENT '该方法是否可行',
  `log_remark` varchar(500) DEFAULT NULL COMMENT '记录备注',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--modify by 2.2
ALTER TABLE `o_pwd` ADD `pwd_level` int  NOT NULL  COMMENT '敏感信息级别';
-- ----------------------------
-- Table structure for o_pwd_staff
-- ----------------------------
DROP TABLE IF EXISTS `o_pwd_staff`;
CREATE TABLE `o_pwd_staff` (
  `pwd_staff_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pwd_id` bigint(20) NOT NULL COMMENT '敏感信息ID',
  `staff_id` bigint(20) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`pwd_staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--modify by 2.3
-- --------------------------------
-- Table structure for o_backup_log
-- --------------------------------
DROP TABLE IF EXISTS `o_backup`;
CREATE TABLE `o_backup`(
	`backup_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`backup_server` varchar(255) NOT NULL COMMENT '备份服务器', 
	`backup_date` datetime NOT NULL COMMENT '备份日期',  
	`backup_title` varchar(255) NOT NULL COMMENT '备份内容',  
	`backup_content` longtext NOT NULL COMMENT '备份详情',
	`backup_state` int NOT NULL COMMENT '备份状态',
	PRIMARY KEY (`backup_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;