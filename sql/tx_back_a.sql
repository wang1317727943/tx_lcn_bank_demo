/*
Navicat MySQL Data Transfer

Source Server         : ThisMe
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : tx_back_a

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-08-26 17:27:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank_a
-- ----------------------------
DROP TABLE IF EXISTS `bank_a`;
CREATE TABLE `bank_a` (
  `id` int(225) NOT NULL AUTO_INCREMENT,
  `money` int(225) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_a
-- ----------------------------
INSERT INTO `bank_a` VALUES ('1', '550', 'shen');
