/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : localhost:3306
 Source Schema         : latesummer

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : 65001

 Date: 22/09/2017 09:50:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for learn_resource
-- ----------------------------
DROP TABLE IF EXISTS `learn_resource`;
CREATE TABLE `learn_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `title` varchar(100) DEFAULT NULL COMMENT '描述',
  `url` varchar(100) DEFAULT NULL COMMENT '地址链接',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8;

insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (999,'官方SpriongBoot例子','官方SpriongBoot例子','https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1000,'龙果学院','Spring Boot 教程系列学习','http://www.roncoo.com/article/detail/124661');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1001,'嘟嘟MD独立博客','Spring Boot干货系列','http://tengj.top/');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1002,'后端编程嘟','Spring Boot视频教程','http://www.toutiao.com/m1559096720023553/');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1003,'程序猿DD','Spring Boot系列','http://www.roncoo.com/article/detail/125488');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1004,'纯洁的微笑','Sping Boot系列文章','http://www.ityouknow.com/spring-boot');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1005,'CSDN——小当博客专栏','Sping Boot学习','http://blog.csdn.net/column/details/spring-boot.html');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1006,'梁桂钊的博客','Spring Boot 揭秘与实战','http://blog.csdn.net/column/details/spring-boot.html');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1007,'林祥纤博客系列','从零开始学Spring Boot','http://412887952-qq-com.iteye.com/category/356333');
insert  into `learn_resource`(`id`,`author`,`title`,`url`) values (1028,'杜琪','关于Spring Boot的博客集合','http://www.jianshu.com/p/7e2e5e7b32ab');
