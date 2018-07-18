/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : slack

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-07-18 16:36:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for addresses
-- ----------------------------
DROP TABLE IF EXISTS `addresses`;
CREATE TABLE `addresses` (
  `ADDR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `STREET` varchar(50) NOT NULL,
  `CITY` varchar(50) NOT NULL,
  `STATE` varchar(50) NOT NULL,
  `ZIP` varchar(10) DEFAULT NULL,
  `COUNTRY` varchar(50) NOT NULL,
  PRIMARY KEY (`ADDR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of addresses
-- ----------------------------
INSERT INTO `addresses` VALUES ('1', '4891 Pacific Hwy', 'San Diego', 'CA', '92110', 'San Diego');
INSERT INTO `addresses` VALUES ('2', '2400 N Jefferson St', 'Perry', 'FL', '32347', 'Taylor');
INSERT INTO `addresses` VALUES ('3', '710 N Cable Rd', 'Lima', 'OH', '45825', 'Allen');
INSERT INTO `addresses` VALUES ('4', '5108 W Gore Blvd', 'Lawton', 'OK', '32365', 'Comanche');

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `COURSE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `START_DATE` date DEFAULT NULL,
  `END_DATE` date DEFAULT NULL,
  `TUTOR_ID` int(11) NOT NULL,
  PRIMARY KEY (`COURSE_ID`),
  KEY `FK_COURSE_TUTOR` (`TUTOR_ID`),
  CONSTRAINT `FK_COURSE_TUTOR` FOREIGN KEY (`TUTOR_ID`) REFERENCES `tutors` (`TUTOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('1', 'Quickstart Core Java', 'Core Java Programming', '2013-03-01', '2013-04-15', '1');
INSERT INTO `courses` VALUES ('2', 'Quickstart JavaEE6', 'Enterprise App Development using JavaEE6', '2013-04-01', '2013-08-30', '1');
INSERT INTO `courses` VALUES ('3', 'MyBatis3 Premier', 'MyBatis 3 framework', '2013-06-01', '2013-07-15', '2');

-- ----------------------------
-- Table structure for course_enrollment
-- ----------------------------
DROP TABLE IF EXISTS `course_enrollment`;
CREATE TABLE `course_enrollment` (
  `COURSE_ID` int(11) NOT NULL,
  `STUD_ID` int(11) NOT NULL,
  PRIMARY KEY (`COURSE_ID`,`STUD_ID`),
  KEY `FK_ENROLLMENT_STUD` (`STUD_ID`),
  CONSTRAINT `FK_ENROLLMENT_COURSE` FOREIGN KEY (`COURSE_ID`) REFERENCES `courses` (`COURSE_ID`),
  CONSTRAINT `FK_ENROLLMENT_STUD` FOREIGN KEY (`STUD_ID`) REFERENCES `students` (`STUD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of course_enrollment
-- ----------------------------
INSERT INTO `course_enrollment` VALUES ('1', '1');
INSERT INTO `course_enrollment` VALUES ('1', '2');
INSERT INTO `course_enrollment` VALUES ('2', '2');

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `STUD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `GENDER` varchar(6) DEFAULT NULL,
  `BIO` longtext,
  `PIC` blob,
  `ADDR_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`STUD_ID`),
  UNIQUE KEY `UK_EMAIL` (`EMAIL`),
  KEY `FK_STUDENTS_ADDR` (`ADDR_ID`),
  CONSTRAINT `FK_STUDENTS_ADDR` FOREIGN KEY (`ADDR_ID`) REFERENCES `addresses` (`ADDR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('1', 'Timothy', 'timothy@gmail.com', '123-123-1234', '1988-04-25', 'MALE', null, null, '3');
INSERT INTO `students` VALUES ('2', 'Douglas', 'douglas@gmail.com', '789-456-1234', '1990-08-15', 'MALE', null, null, '4');

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `age` int(2) DEFAULT NULL COMMENT '年龄',
  `sex` smallint(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `head_image` varchar(128) DEFAULT NULL COMMENT '头像',
  `del_flag` smallint(2) DEFAULT NULL COMMENT '是否删除',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('30e45ae9549947a2921ae85789b4bd17', '奇衡三', '1234', '小三', '120', '0', '13355656565', '333@qq.com', null, '0', '2018-06-29 16:54:31', '2018-06-29 16:54:31');
INSERT INTO `s_user` VALUES ('aa77e7bc001b49b8bb884a7e64b01247', '真李四', '1234', '真李四', '20', '0', '18888888888', 'zhenLisi@qq.com', null, '1', '2018-06-26 18:33:16', '2018-06-26 18:33:35');
INSERT INTO `s_user` VALUES ('bda7e6d9ed064ee49c2c17d3a0595865', '吴征', '1234', '小五', '23', '0', '185888888888', 'wang@qq.com', null, '0', '2018-06-27 17:17:35', null);
INSERT INTO `s_user` VALUES ('ea1a078250e74da494042852cf630ec9', '马六', '1234', '麻溜', '24', '0', '18965656555', 'maliu@qq.com', null, '0', '2018-07-05 16:24:25', null);

-- ----------------------------
-- Table structure for tutors
-- ----------------------------
DROP TABLE IF EXISTS `tutors`;
CREATE TABLE `tutors` (
  `TUTOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `GENDER` varchar(6) DEFAULT NULL,
  `BIO` longtext,
  `PIC` blob,
  `ADDR_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TUTOR_ID`),
  UNIQUE KEY `UK_EMAIL` (`EMAIL`),
  KEY `FK_TUTORS_ADDR` (`ADDR_ID`),
  CONSTRAINT `FK_TUTORS_ADDR` FOREIGN KEY (`ADDR_ID`) REFERENCES `addresses` (`ADDR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of tutors
-- ----------------------------
INSERT INTO `tutors` VALUES ('1', 'John', 'john@gmail.com', '111-222-3333', '1980-05-20', 'MALE', null, null, '1');
INSERT INTO `tutors` VALUES ('2', 'Ken', 'ken@gmail.com', '111-222-3333', '1980-05-20', 'MALE', null, null, '1');
INSERT INTO `tutors` VALUES ('3', 'Paul', 'paul@gmail.com', '123-321-4444', '1981-03-15', 'FEMALE', null, null, '2');
INSERT INTO `tutors` VALUES ('4', 'Mike', 'mike@gmail.com', '123-321-4444', '1981-03-15', 'MALE', null, null, '2');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(40) NOT NULL DEFAULT '' COMMENT '角色名',
  `desc` varchar(40) NOT NULL DEFAULT '' COMMENT '角色描述',
  `category` varchar(40) DEFAULT '' COMMENT '分类',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` int(2) NOT NULL DEFAULT '0' COMMENT '是否删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '超级管理员', '', '2018-07-13 17:54:05', '2018-07-13 17:54:10', '0');
INSERT INTO `t_role` VALUES ('2', 'manager', '管理员', '', '2018-07-16 12:09:32', '2018-07-16 12:09:32', '0');
INSERT INTO `t_role` VALUES ('3', 'guest', '普通用户', '', '2018-07-16 12:09:45', '2018-07-16 12:09:49', '0');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户的id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色的id',
  `del_flag` tinyint(2) DEFAULT NULL COMMENT '是否删除的标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '30e45ae9549947a2921ae85789b4bd17', '1', '0');
INSERT INTO `t_user_role` VALUES ('2', 'aa77e7bc001b49b8bb884a7e64b01247', '2', '0');
INSERT INTO `t_user_role` VALUES ('3', 'bda7e6d9ed064ee49c2c17d3a0595865', '3', '0');

-- ----------------------------
-- Table structure for t_wx_user
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_user`;
CREATE TABLE `t_wx_user` (
  `id` varchar(255) NOT NULL,
  `union_id` varchar(255) DEFAULT NULL COMMENT '微信公众平台唯一标识',
  `openid` varchar(255) DEFAULT NULL COMMENT '公众号唯一标识',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `sex_desc` varchar(255) DEFAULT NULL COMMENT '性别描述',
  `sex` smallint(6) DEFAULT NULL COMMENT '性别',
  `language` varchar(255) DEFAULT NULL COMMENT '语言',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像',
  `subscribe_time` datetime DEFAULT NULL COMMENT '订阅时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `group_id` int(11) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wx_user
-- ----------------------------
