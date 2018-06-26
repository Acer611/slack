/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : slack

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-06-26 13:58:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` varchar(32) NOT NULL COMMENT '唯一标识',
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
