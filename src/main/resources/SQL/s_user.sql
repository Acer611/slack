/*
Navicat MySQL Data Transfer

Source Server         : 阿里库47.104.248.63
Source Server Version : 50640
Source Host           : 47.104.248.63:3306
Source Database       : slack

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-06-27 22:14:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `s_user`
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
INSERT INTO `s_user` VALUES ('30e45ae9549947a2921ae85789b4bd17', 'zhansan', '1234', '环环紧扣', '18', '0', '15836545695', '123@qq.com', null, '0', '2018-06-26 15:18:52', '2018-06-26 15:23:04');
INSERT INTO `s_user` VALUES ('aa77e7bc001b49b8bb884a7e64b01247', 'lisi', '1234', 'lisi', '20', '0', '16547412344', 'lisi@qq.com', null, '0', '2018-06-26 15:22:36', null);
