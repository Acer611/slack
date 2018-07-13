/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : slack

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2018-07-13 17:55:23
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '超级管理员', '', '2018-07-13 17:54:05', '2018-07-13 17:54:10', '0');

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
