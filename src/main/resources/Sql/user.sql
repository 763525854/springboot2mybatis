/*
Navicat MySQL Data Transfer

Source Server         : localhost-root
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2021-02-28 02:05:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbUser
-- ----------------------------
DROP TABLE IF EXISTS `tbUser`;
CREATE TABLE `tbUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbUser
-- ----------------------------
INSERT INTO `tbUser` VALUES ('1', 'rhyme', '2');
INSERT INTO `tbUser` VALUES ('2', 'apple', '18');
