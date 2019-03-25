/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : springboot_learn

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-03-25 15:55:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('bbb', '1', 'tom');
INSERT INTO `person` VALUES ('ccc', '13', 'tom');
INSERT INTO `person` VALUES ('ddd', '15', 'tom');
INSERT INTO `person` VALUES ('eee', '2', 'tom');
INSERT INTO `person` VALUES ('fff', '7', 'tom');
INSERT INTO `person` VALUES ('test001', '77', 'test');
