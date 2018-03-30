/*
Navicat MySQL Data Transfer

Source Server         : localData
Source Server Version : 50144
Source Host           : localhost:3306
Source Database       : slxy-select

Target Server Type    : MYSQL
Target Server Version : 50144
File Encoding         : 65001

Date: 2018-03-30 17:09:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for select_department
-- ----------------------------
DROP TABLE IF EXISTS `select_department`;
CREATE TABLE `select_department` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '系别id',
  `dep_name` varchar(20) DEFAULT NULL COMMENT '系别名称',
  `dep_info` text COMMENT '系别介绍',
  `dep_status` int(4) DEFAULT '1' COMMENT '系别状态 0禁用，1启用',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_department
-- ----------------------------
INSERT INTO `select_department` VALUES ('7', '网络工程系', '网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1网络工程哇1', '1', '2018-01-13 17:12:25', '2018-01-19 11:36:56');
INSERT INTO `select_department` VALUES ('8', '计算机科学与技术', '12312', '1', '2018-01-13 17:02:41', '2018-01-15 13:48:24');
INSERT INTO `select_department` VALUES ('9', '音乐系', '1', '0', '2018-01-13 17:01:18', '2018-01-17 21:31:26');
INSERT INTO `select_department` VALUES ('15', '外语系', '外语1', '0', '2018-01-13 20:28:38', '2018-01-17 21:49:41');
INSERT INTO `select_department` VALUES ('18', '芙蓉系', '芙蓉1', '0', '2018-01-13 20:36:03', '2018-01-17 22:02:38');
INSERT INTO `select_department` VALUES ('25', '1111', '111111', '0', '2018-01-17 21:59:49', '2018-01-17 22:02:34');
INSERT INTO `select_department` VALUES ('26', '2232323', '123213', '1', '2018-01-17 22:00:08', '2018-01-22 20:36:22');
INSERT INTO `select_department` VALUES ('27', '12412412', '1412412', '1', '2018-01-18 10:04:28', '2018-01-22 20:36:17');
INSERT INTO `select_department` VALUES ('29', '44', '44', '1', '2018-01-25 17:20:49', null);
INSERT INTO `select_department` VALUES ('30', 'jiaoyanceshi ', '', '1', '2018-03-30 15:26:20', null);

-- ----------------------------
-- Table structure for select_major
-- ----------------------------
DROP TABLE IF EXISTS `select_major`;
CREATE TABLE `select_major` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '专业id',
  `maj_name` varchar(20) DEFAULT NULL COMMENT '专业名称',
  `dep_id` int(10) DEFAULT NULL COMMENT '所属系别',
  `maj_class_num` int(4) DEFAULT NULL COMMENT '专业班级数',
  `maj_status` int(4) DEFAULT '1' COMMENT '专业状态 0禁用，1启用',
  `maj_info` text COMMENT '专业介绍',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_major
-- ----------------------------
INSERT INTO `select_major` VALUES ('1', '网络工程', '7', '2', '1', '网络', '2018-01-02 17:30:57', '2018-01-18 14:59:52');
INSERT INTO `select_major` VALUES ('2', '计算机科学与技术', '8', '2', '1', '计算计科学技术asjdahsfjasbif是卡换卡FNASKJFBASBFJASAKSFBASNJFBN ASMNDsjkdnaksfNSAKNNF是看你反抗是肯定你饭卡上你饭卡思考三番三番看法卡了师傅那里开始反思开发呢啊看了烦死了放那你发烧了可能flask能否啊师傅看上了卡饭上来看你卡拉方式水水水水水水水水水水水水水水呢烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦烦发 发发发发发发芬芬发发发发饭卡上啊什么按摩手法爱似麻烦啊沙发上发生发生发什么疯啊师傅阿三', '2018-01-02 17:31:00', '2018-01-19 11:36:29');
INSERT INTO `select_major` VALUES ('6', '数学', '26', '3', '1', '事故学', '2018-01-22 16:58:24', '2018-03-30 15:47:17');
INSERT INTO `select_major` VALUES ('7', '111', '7', '1', '0', '121212', '2018-01-25 17:23:34', '2018-02-28 19:43:56');

-- ----------------------------
-- Table structure for select_process_control
-- ----------------------------
DROP TABLE IF EXISTS `select_process_control`;
CREATE TABLE `select_process_control` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '流程id',
  `pro_name` varchar(20) DEFAULT NULL COMMENT '流程名称',
  `pro_start_time` datetime DEFAULT NULL COMMENT '流程开始时间',
  `pro_end_time` datetime DEFAULT NULL COMMENT '流程结束时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_process_control
-- ----------------------------
INSERT INTO `select_process_control` VALUES ('1', '上传题目', '2018-03-29 11:57:42', '2018-03-30 01:00:54', '2018-03-28 09:48:21', '2018-03-29 11:57:48');
INSERT INTO `select_process_control` VALUES ('2', '审核题目', '2018-03-30 09:48:34', '2018-04-01 09:48:39', '2018-03-28 09:48:53', null);
INSERT INTO `select_process_control` VALUES ('3', '学生选题', '2018-04-02 09:49:11', '2018-04-06 09:49:25', '2018-03-28 09:49:43', null);
INSERT INTO `select_process_control` VALUES ('4', '教师审核', '2018-04-02 09:50:25', '2018-04-06 09:50:31', '2018-03-28 09:50:39', null);
INSERT INTO `select_process_control` VALUES ('5', '成绩查询', '2018-04-09 09:51:27', '2018-06-01 09:51:44', '2018-03-28 09:51:57', null);

-- ----------------------------
-- Table structure for select_score_per
-- ----------------------------
DROP TABLE IF EXISTS `select_score_per`;
CREATE TABLE `select_score_per` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `score_name` varchar(30) DEFAULT NULL COMMENT '成绩模块',
  `score_per` int(10) DEFAULT NULL COMMENT '成绩所占百分比',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_score_per
-- ----------------------------
INSERT INTO `select_score_per` VALUES ('1', '指导老师评分', '30', '2018-03-29 11:11:56', null);
INSERT INTO `select_score_per` VALUES ('2', '评阅老师评分', '20', '2018-03-29 11:12:15', null);
INSERT INTO `select_score_per` VALUES ('3', '答辩评分', '50', '2018-03-29 11:12:45', null);

-- ----------------------------
-- Table structure for select_subject
-- ----------------------------
DROP TABLE IF EXISTS `select_subject`;
CREATE TABLE `select_subject` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '题目id',
  `sub_name` varchar(50) DEFAULT NULL COMMENT '题目名称',
  `tea_id` int(20) DEFAULT NULL COMMENT '发布教师id',
  `sub_type` int(4) DEFAULT NULL COMMENT '题目类型，1应用型，2理论性',
  `sub_file` varchar(255) DEFAULT NULL COMMENT '题目文件路径',
  `sub_content` text COMMENT '题目内容',
  `sub_select_status` int(4) DEFAULT '0' COMMENT '题目选题状态 0未选 1审核中 2已选',
  `adm_audit_state` int(4) DEFAULT '0' COMMENT '审核状态 0未处理，1审核不通过，2审核通过',
  `adm_audit_content` text COMMENT '审核意见',
  `adm_audit_id` int(20) DEFAULT NULL COMMENT '审核人id',
  `tutor_score` double DEFAULT '0' COMMENT '指导老师评分',
  `judge_score` double DEFAULT '0' COMMENT '评阅老师评分',
  `defence_score` double DEFAULT '0' COMMENT '答辩得分',
  `final_total_score` double DEFAULT '0' COMMENT '最终总得分',
  `for_dep_id` int(10) DEFAULT NULL COMMENT '题目面向系别',
  `sub_year` varchar(10) DEFAULT NULL COMMENT '题目年份',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_subject
-- ----------------------------
INSERT INTO `select_subject` VALUES ('1', '选题系统', '12', '2', null, '选题选题', '2', '2', '恭喜您，论文审核通过！！！', '1', '80', '80', '90', '85', '7', '2014', '2018-01-25 16:57:28', '2018-03-29 17:44:10');
INSERT INTO `select_subject` VALUES ('2', '房产系统', '12', '2', null, '房产', '0', '2', '恭喜您，论文审核通过！！！', '1', '0', '0', '0', '0', '7', '2014', '2018-01-25 17:07:05', '2018-03-29 17:40:01');
INSERT INTO `select_subject` VALUES ('3', '影视介绍', '12', '1', null, 'ing是影视', '0', '2', '恭喜您，论文审核通过！！！', '1', '0', '0', '0', '0', '7', '2014', '2018-01-25 17:08:16', '2018-03-29 17:40:01');
INSERT INTO `select_subject` VALUES ('4', '旅游展示', '12', '1', null, '旅游啊啊啊啊', '0', '0', null, null, '0', '0', '0', '0', '7', '2013', '2018-01-24 17:09:47', '2018-03-29 17:40:01');
INSERT INTO `select_subject` VALUES ('5', '新闻介绍', '12', '1', null, '新闻上新闻', '0', '2', 'u哦股顾工', '1', '0', '0', '0', '0', '7', '2013', '2018-01-17 17:10:31', '2018-03-29 17:40:01');
INSERT INTO `select_subject` VALUES ('6', '网络搭建', '12', '2', null, '网络网络啊', '0', '0', null, null, '0', '0', '0', '0', '7', '2013', '2018-01-10 17:11:09', '2018-03-29 17:40:01');
INSERT INTO `select_subject` VALUES ('7', '校园网组件', '12', '2', null, '鞍组件', '0', '0', null, null, '0', '0', '0', '0', '7', '2013', '2018-01-06 17:11:56', '2018-03-29 17:40:02');
INSERT INTO `select_subject` VALUES ('8', '网络维护', '12', '1', null, '鞥', '0', '0', null, null, '0', '0', '0', '0', '7', '2013', '2018-01-13 17:12:33', '2018-03-29 17:40:02');
INSERT INTO `select_subject` VALUES ('9', '撒谎的骄傲开始', '12', '2', null, '是哪家快递哈是', '0', '0', null, null, '0', '0', '0', '0', '27', '2015', '2018-02-02 09:55:38', '2018-03-29 17:40:02');
INSERT INTO `select_subject` VALUES ('10', '倒萨倒萨', '12', '2', null, '萨达阿三', '0', '0', null, null, '0', '0', '0', '0', '7', '2016', '2018-02-02 09:58:13', '2018-03-29 17:40:02');
INSERT INTO `select_subject` VALUES ('11', '撒大苏打', '12', '1', null, ' 啊实打实大神', '0', '2', '恭喜您，论文审核通过！！！', null, '0', '0', '0', '0', '27', '2017', '2018-02-02 10:01:39', '2018-03-29 17:40:02');
INSERT INTO `select_subject` VALUES ('12', '231216', '2', '1', null, '31654156', '0', '0', null, null, '0', '0', '0', '0', '8', '2016', '2018-02-02 10:57:25', '2018-03-29 17:40:02');
INSERT INTO `select_subject` VALUES ('13', '实打实的撒', '2', '2', null, '撒打算', '0', '1', 'teacher不过', '1', '0', '0', '0', '0', '7', '2016', '2018-02-02 10:58:12', '2018-03-29 17:40:02');
INSERT INTO `select_subject` VALUES ('14', '撒旦撒旦阿三', '2', '2', null, '撒打算', '0', '2', '恭喜您，论文审核通过！！！', '1', '0', '0', '0', '0', '26', '2016', '2018-02-02 10:59:28', '2018-03-29 17:40:02');
INSERT INTO `select_subject` VALUES ('24', '本科毕业论文系统设计', '2', '1', 'demo/郑其龙任务书.docx', '设计毕业选题系统', '0', '2', '恭喜您，论文审核通过！！！', '1', '0', '0', '0', '0', '7', '2014', '2018-03-23 10:03:06', '2018-03-29 17:40:02');

-- ----------------------------
-- Table structure for select_topic
-- ----------------------------
DROP TABLE IF EXISTS `select_topic`;
CREATE TABLE `select_topic` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '选题id',
  `sub_id` int(10) DEFAULT NULL COMMENT '题目id',
  `tea_id` int(20) DEFAULT NULL COMMENT '教师id',
  `stu_id` int(20) DEFAULT NULL COMMENT '学生id',
  `stu_select_reason` text COMMENT '学生选择理由',
  `tea_audit_state` int(4) DEFAULT '0' COMMENT '教师审核状态 0待处理 1审核不通过，2审核通过',
  `tea_audit_content` text COMMENT '教师审核理由',
  `topic_year` varchar(10) DEFAULT NULL COMMENT '选题年份',
  `task_file` varchar(255) DEFAULT NULL COMMENT '任务书',
  `opening_report` varchar(255) DEFAULT NULL COMMENT '开题报告',
  `dissertation` varchar(255) DEFAULT NULL COMMENT '毕业论文',
  `del_state` int(10) DEFAULT '0' COMMENT '删除状态 0未删除，1已删除',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_topic
-- ----------------------------
INSERT INTO `select_topic` VALUES ('6', '1', '12', '3', '撒打算', '2', '恭喜你选题成功！', '2014', 'demo/初试成绩-四级-联系方式.xlsx', 'demo/杨瑞-烟台大学调剂申请表.doc', 'demo/2-附件-调剂考生信息表.xls', '0', '2018-02-05 16:12:03', '2018-03-29 14:24:22');
INSERT INTO `select_topic` VALUES ('7', '2', '12', '6', '要选啊', '1', '23132123\n', '2014', null, null, null, '0', '2018-03-05 14:32:28', '2018-03-29 14:24:22');
INSERT INTO `select_topic` VALUES ('9', '5', '12', '4', '终身大事的阿三', '1', '23131313', '2013', null, null, null, '1', '2018-03-27 10:22:33', '2018-03-29 14:23:02');
INSERT INTO `select_topic` VALUES ('10', '2', '12', '8', '545646', '1', '不过', '2014', null, null, null, '0', '2018-03-27 10:28:59', '2018-03-29 14:24:24');

-- ----------------------------
-- Table structure for select_user_base
-- ----------------------------
DROP TABLE IF EXISTS `select_user_base`;
CREATE TABLE `select_user_base` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_code` varchar(20) DEFAULT NULL COMMENT '账号',
  `user_password` varchar(20) DEFAULT NULL COMMENT '密码',
  `user_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `user_sex` int(4) DEFAULT NULL COMMENT '性别 1男，2女',
  `user_mail` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `user_qq` varchar(15) DEFAULT NULL COMMENT 'qq',
  `stu_major_id` int(20) DEFAULT NULL COMMENT '学生专业id',
  `stu_class` int(4) DEFAULT NULL COMMENT '学生班级',
  `stu_year` varchar(10) DEFAULT NULL COMMENT '学生届别',
  `tea_position` int(4) DEFAULT NULL COMMENT '教师职称 1教授，2副教授，3讲师，4助教',
  `tea_major_name` varchar(20) DEFAULT NULL COMMENT '教师专业名称',
  `tea_education` int(4) DEFAULT NULL COMMENT '教师学历 1博士，2硕士，3本科',
  `tea_direction` varchar(20) DEFAULT NULL COMMENT '教师研究方向',
  `tea_dep_id` int(20) DEFAULT NULL COMMENT '教师所属系别id',
  `tea_info` text COMMENT '教师个人简介',
  `user_status` int(4) DEFAULT '1' COMMENT '用户状态 0禁用，1启用',
  `user_type` int(4) DEFAULT NULL COMMENT '用户类型 1管理员，2教师，3学生',
  `operator_id` int(20) DEFAULT NULL COMMENT '操作人员id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_user_base
-- ----------------------------
INSERT INTO `select_user_base` VALUES ('1', 'admin001', '134679', 'admin', '2', '489671435@qq.com', '13108018752', '489671435', null, null, null, null, null, null, null, null, null, '1', '0', null, '2018-01-02 17:19:59', '2018-03-30 15:23:11');
INSERT INTO `select_user_base` VALUES ('2', 'teacher1', '123456', 'teacher', '1', 'zhengql@senthink.com', '13108018752', '489671435', null, null, null, '1', '网络工程', '1', '网络搭建网络搭建网络搭建', '7', '暂无dsadsadasd asdasd', '1', '2', null, '2018-01-02 17:32:28', '2018-03-27 15:07:21');
INSERT INTO `select_user_base` VALUES ('3', 'student', '123456', 'student', '1', 'zhenql@lierda.com', '13108018752', '8979789798', '1', '1', '2014', null, null, null, null, null, null, '1', '3', null, '2018-01-02 17:39:32', '2018-03-29 09:35:31');
INSERT INTO `select_user_base` VALUES ('4', 'f001', '123456', '芙蓉刚', '1', 'zhenql@lierda.com', '13108018752', '56165', '1', '2', '2013', null, null, null, null, null, null, '1', '3', null, '2018-01-03 09:56:02', '2018-02-02 11:24:19');
INSERT INTO `select_user_base` VALUES ('5', 'c001', '123456', '陈浩天', '1', 'zhenql@lierda.com', '13108018752', '151156165', '2', '2', '2014', null, null, null, '', null, '', '1', '3', null, '2018-01-04 09:56:02', '2018-02-02 11:24:38');
INSERT INTO `select_user_base` VALUES ('6', 'cht', '123456', '陈昊天', '1', 'zhenql@lierda.com', '13108018752', '65165161', '1', '2', '2014', null, null, null, '', null, '', '1', '3', null, '2018-01-05 09:56:02', '2018-02-09 15:07:50');
INSERT INTO `select_user_base` VALUES ('7', 'frg', '123456', '付荣刚', '1', 'zhenql@lierda.com', '13108018752', '51616545', '6', '2', '2016', null, null, null, '', null, '', '1', '3', null, '2018-01-06 09:56:02', '2018-02-09 15:07:30');
INSERT INTO `select_user_base` VALUES ('8', 'hcm', '123456', '黄春梅', '2', 'chunmei@lierda.com', '111123213123', '156123023', '1', '2', '2014', null, null, null, '', null, '', '1', '3', null, '2018-01-07 09:58:22', '2018-03-28 16:55:55');
INSERT INTO `select_user_base` VALUES ('9', 'l001', '123456', '李四', '1', 'zhenql@lierda.com', '13108018752', '231165165', '1', '2', '2017', null, null, null, '', null, '', '0', '3', null, '2018-01-08 09:58:27', '2018-02-02 11:24:25');
INSERT INTO `select_user_base` VALUES ('10', 'z002', '123456', '赵四', '1', 'zhenql@lierda.com', '13108018752', '165165165', '1', '2', '2016', null, null, null, '', null, '', '1', '3', null, '2018-01-08 09:56:02', '2018-02-02 11:24:25');
INSERT INTO `select_user_base` VALUES ('11', 'w001', '123456', '王五', '2', 'zhengql@lierda.com', '13108018752', '489671435', '1', '2', '2014', null, '', null, '', null, '', '1', '3', null, '2018-01-08 09:56:02', '2018-02-02 11:24:25');
INSERT INTO `select_user_base` VALUES ('12', 'teacher', '123456', '郑老师', '1', 'zhengql@senthink.com', '13108018752', '489671435', null, null, '', '1', '网络工程', '1', '网络搭建网络搭建网络搭建', '7', 'yououuoou', '1', '2', null, '2018-01-02 17:32:28', '2018-02-02 11:25:18');
INSERT INTO `select_user_base` VALUES ('17', '131312312', '123456', '12312', '1', '123123123', '12312312', '1231231', '2', '2', '2016', null, null, null, null, null, null, '0', '3', null, '2018-01-22 20:19:15', '2018-02-02 11:24:36');
INSERT INTO `select_user_base` VALUES ('18', 'qweqw', '123456', 'wqe', '1', 'qwe', 'qweqw', 'qeqw', '1', '2', '2016', null, null, null, null, null, null, '0', '3', null, '2018-01-22 20:19:34', '2018-02-02 11:24:28');
INSERT INTO `select_user_base` VALUES ('19', '212', '123456', '123', '1', '12121', '212', '1221', '6', '1', '2017', null, null, null, null, null, null, '0', '3', null, '2018-01-22 20:21:19', '2018-02-02 11:24:42');
INSERT INTO `select_user_base` VALUES ('21', '1231', '123456', '王老师', '1', 'wqeqw', 'qweqwe', 'wqeqwe', null, null, null, '3', 'wqeqwe', '3', 'qweqwe', '7', 'wqeqwe', '1', '2', null, '2018-01-23 20:19:28', '2018-02-02 11:25:20');
INSERT INTO `select_user_base` VALUES ('22', 'dasd', '123456', 'dada', '1', 'adada', 'dadad', 'adada', null, null, null, '2', 'dadad', '2', 'adad', '7', 'dadad', '1', '2', null, '2018-01-24 19:25:42', '2018-02-02 11:25:23');
INSERT INTO `select_user_base` VALUES ('23', 'xjl', '123456', '佳林', '1', 'junzi@126.com', '12312312312311', '111', '6', '1', '2017', null, null, null, null, null, null, '1', '3', null, '2018-01-25 17:21:28', '2018-02-02 11:24:45');
INSERT INTO `select_user_base` VALUES ('24', '24098125', '123456', '小福', '1', '12312312412', '213123124', '12312312', '1', '1', '2014', null, null, null, null, null, null, '1', '3', null, '2018-01-30 13:25:41', '2018-02-02 11:24:29');
INSERT INTO `select_user_base` VALUES ('25', '156601', '123456', '杜洪乐', '1', '7894563', '87898989', '6848998', null, null, null, '1', '给排水', '2', '', '7', '', '1', '2', null, '2018-01-30 14:35:37', '2018-02-02 12:54:30');
INSERT INTO `select_user_base` VALUES ('26', 'sdas', '123456', 'sdsd', '1', 'sdsd', 'sdsd', 'sdsdsd', '1', '2', '2016', null, null, null, null, null, null, '1', '3', null, '2018-01-31 11:18:07', '2018-02-02 11:24:31');
INSERT INTO `select_user_base` VALUES ('27', '78745212', '123456', '去问驱', '1', '12312312412', '213123124', '12312312', '2', '1', '2014', null, null, null, null, null, null, '0', '3', null, '2018-02-02 09:33:15', '2018-02-02 12:56:53');
INSERT INTO `select_user_base` VALUES ('28', '78745213', '123456', '萨达', '1', '12312312412', '213123124', '12312312', '2', '1', '2014', null, null, null, null, null, null, '1', '3', null, '2018-02-02 09:33:15', '2018-02-02 11:24:35');
INSERT INTO `select_user_base` VALUES ('29', '78745214', '123456', '请问去', '1', '12312312412', '213123124', '12312312', '2', '1', '2014', null, null, null, null, null, null, '1', '3', null, '2018-02-02 09:33:15', '2018-02-02 11:24:35');
INSERT INTO `select_user_base` VALUES ('31', '787452121', '123456', '测测测测', '1', '123123124121', '2131231214', '12312312', '2', '1', '2014', null, null, null, null, null, null, '1', '3', null, '2018-02-09 15:00:19', null);
INSERT INTO `select_user_base` VALUES ('32', '140099', '123456', '321651', '1', '51651', '6465465', '16516', '2', '1', '2016', null, null, null, null, null, null, '1', '3', null, '2018-03-22 14:05:04', null);
INSERT INTO `select_user_base` VALUES ('33', 'sjxy001', '123456', '管理员1', '1', 'zhengqilong@qq.com', '12312312312', '214312412412', null, null, null, null, null, null, null, null, null, '1', '1', null, '2018-03-27 16:48:41', '2018-03-27 17:02:00');
INSERT INTO `select_user_base` VALUES ('34', '', '123456', '', '1', '', '', '', null, null, null, null, null, null, null, null, null, '1', '1', null, '2018-03-30 14:11:47', '2018-03-30 15:07:59');
INSERT INTO `select_user_base` VALUES ('35', '12312', '123456', '', '1', '', '', '', null, null, null, null, null, null, null, null, null, '1', '1', null, '2018-03-30 14:30:40', '2018-03-30 15:07:59');
INSERT INTO `select_user_base` VALUES ('36', '14098126', '123456', 'admin', '1', '489671435@q.com', '13131645', '48994651613', null, null, null, null, null, null, null, null, null, '1', '1', null, '2018-03-30 15:03:24', '2018-03-30 15:07:59');
INSERT INTO `select_user_base` VALUES ('37', '12312123', '123456', 'sadsad', '1', '489671435@q.com', '13131645', '48994651613', null, null, null, null, null, null, null, null, null, '1', '1', null, '2018-03-30 15:04:12', '2018-03-30 15:07:59');
INSERT INTO `select_user_base` VALUES ('38', '14098147', '123456', 'admin', '1', '489671435@q.com', '13131645', '48994651613', null, null, null, null, null, null, null, null, null, '1', '1', null, '2018-03-30 15:05:18', '2018-03-30 15:07:59');
