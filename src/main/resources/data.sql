/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : latesummer

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-04-05 01:46:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `learn_resource`
-- ----------------------------
DROP TABLE IF EXISTS `learn_resource`;
CREATE TABLE `learn_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `title` varchar(100) DEFAULT NULL COMMENT '描述',
  `url` varchar(100) DEFAULT NULL COMMENT '地址链接',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1034 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of learn_resource
-- ----------------------------
INSERT INTO `learn_resource` VALUES ('999', '官方SpriongBoot例子', '官方SpriongBoot例子', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1000', '龙果学院', 'Spring Boot 教程系列学习', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1001', '嘟嘟MD独立博客', 'Spring Boot干货系列', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1002', '后端编程嘟', 'Spring Boot视频教程', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1003', '程序猿DD', 'Spring Boot系列', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1004', '纯洁的微笑', 'Sping Boot系列文章', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1005', 'CSDN——小当博客专栏', 'Sping Boot学习', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1006', '梁桂钊的博客', 'Spring Boot 揭秘与实战', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1007', '林祥纤博客系列', '从零开始学Spring Boot', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1028', '杜琪', '关于Spring Boot的博客集合', 'http://www.baidu.com');
INSERT INTO `learn_resource` VALUES ('1030', '苏振威', '苏振威', 'http://www.baidu.com');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `email` varchar(64) DEFAULT NULL COMMENT 'Email',
  `certificate_type` tinyint(2) DEFAULT NULL COMMENT '证件类型',
  `certificate_num` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `referee_name` varchar(32) DEFAULT NULL COMMENT '推荐人姓名',
  `is_used` tinyint(2) DEFAULT NULL COMMENT '是否可用',
  `is_advanced` tinyint(2) DEFAULT NULL COMMENT '是否高级认证',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '苏振威', '865839251@qq.com', '1', '427654199678765438', '苏振威', '1', '1', '2018-04-05 00:36:29', '1', null, null);
INSERT INTO `user` VALUES ('2', '西西大人', '337265439@qq.com', '1', '420621199204092714', '苏振威', '1', '1', '2018-04-05 00:36:32', '1', null, null);
INSERT INTO `user` VALUES ('3', '女王大人', '876545678@qq.com', '1', '420324199305180097', '苏振威', '1', '1', '2018-04-05 00:36:35', '1', null, null);
