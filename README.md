# tx_lcn_bank_demo
tx-lcn 演示项目. 银行扣款  A,B 应用同时扣款, 模拟B应用出异常, 整体扣费操作回滚,不提交数据库
启动流程:  
  1. 启动 Eureka 工程 默认端口8761
  2. 启动 Redis 默认端口6379
  3. 创建TC工程需要的数据库,启动TX-Manager 工程
  4. 创建bank_demo工程所需数据库, 并启动 BANK-A,BANK-B工程
  
 Eureka 启动后查看地址: http://localhost:8761/ <br/>
 TxManager系统后台地址: http://127.0.0.1:7970/admin/index.html    登录密码: codingapi <br/>
 bank_demo 测试get请求地址url: http://127.0.0.1:7071/start?money=150  <br/>
 1) 调用成功返回 success,查看A,B数据库都修改成功 
 2) 在service里制造异常 (注释掉了)
 3) 调用失败返回 error 500到页面,查看A,B数据库均未被修改
 
1.  bank-a sql

------------------------------------------------
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

2.bank_b sql
------------------------------------------------

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bank_b
-- ----------------------------
DROP TABLE IF EXISTS `bank_b`;
CREATE TABLE `bank_b` (
  `id` int(225) NOT NULL AUTO_INCREMENT,
  `money` int(225) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank_b
-- ----------------------------
INSERT INTO `bank_b` VALUES ('1', '1450', 'shen');


3. tx-manager sql 

------------------------------------------------
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');

-- ----------------------------
-- Table structure for t_logger
-- ----------------------------
DROP TABLE IF EXISTS `t_logger`;

CREATE TABLE `t_logger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) NOT NULL,
  `unit_id` varchar(32) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `create_time` varchar(30) NOT NULL,
  `app_name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logger
-- ----------------------------

-- ----------------------------
-- Table structure for t_tx_exception
-- ----------------------------
DROP TABLE IF EXISTS `t_tx_exception`;

CREATE TABLE `t_tx_exception` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `unit_id` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `mod_id` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transaction_state` tinyint(4) DEFAULT NULL,
  `registrar` tinyint(4) DEFAULT NULL,
  `remark` varchar(4096) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ex_state` tinyint(4) DEFAULT NULL COMMENT '0 未解决 1已解决',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_tx_exception
-- ----------------------------

