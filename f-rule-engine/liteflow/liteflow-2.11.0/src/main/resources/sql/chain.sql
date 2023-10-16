/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50727 (5.7.27-log)
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50727 (5.7.27-log)
 File Encoding         : 65001

 Date: 16/08/2023 14:34:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chain
-- ----------------------------
DROP TABLE IF EXISTS `chain`;
CREATE TABLE `chain`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `chain_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `chain_desc` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `el_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chain
-- ----------------------------
INSERT INTO `chain` VALUES (1, 'demo', 'chain1', '测试流程', 'THEN(a, b, s1, c, d);', '2022-09-19 19:31:00');
INSERT INTO `chain` VALUES (2, 'demo', 'groovy_test1', 'groovy测试1', 'THEN(a,b,c,groovy1);', '2023-08-09 15:19:10');
INSERT INTO `chain` VALUES (3, 'demo', 'groovy_test2', 'groovy测试2', 'THEN(a,b,c,groovy2);', '2023-08-09 15:19:29');
INSERT INTO `chain` VALUES (4, 'demo', 'groovy_test3', 'groovy测试3', 'IF(groovy3, THEN(a, b));', '2023-08-09 15:30:35');
INSERT INTO `chain` VALUES (5, 'demo', 'groovy_test4', 'groovy测试4', 'IF(groovy4, THEN(a, b), THEN(c, d));', '2023-08-09 15:30:40');
INSERT INTO `chain` VALUES (6, 'demo', 'chain5555', '测试2', 'THEN(a,s3)', '2023-08-16 13:38:26');

-- ----------------------------
-- Table structure for script
-- ----------------------------
DROP TABLE IF EXISTS `script`;
CREATE TABLE `script`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `script_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `script_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `script_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `script_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of script
-- ----------------------------
INSERT INTO `script` VALUES (1, 'demo', 's1', '脚本s1', 'import cn.hutool.core.date.DateUtil\r\n\r\ndef date = DateUtil.parse(\"2022-10-17 13:31:43\")\r\nprintln(date)\r\ndefaultContext.setData(\"demoDate\", date)\r\n\r\nclass Student {\r\n   int studentID\r\n   String studentName\r\n}\r\n\r\nStudent student = new Student()\r\nstudent.studentID = 100301\r\nstudent.studentName = \"张三\"\r\ndefaultContext.setData(\"student\",student)\r\n\r\ndef a=3\r\ndef b=2\r\ndefaultContext.setData(\"s1\",a*b)', 'script');
INSERT INTO `script` VALUES (2, 'demo', 's2', '脚本s2', 'defaultContext.setData(\"s2\",\"hello\")', 'script');
INSERT INTO `script` VALUES (3, 'demo', 'groovy1', 'groovy测试1', '                order.setOrderNo(\"order1\")\r\n                checkContext.setSign(\"sign1\")\r\n                order2Context.setOrderNo(\"order2\")', 'script');
INSERT INTO `script` VALUES (4, 'demo', 'groovy2', 'groovy测试2', 'def orderNo = order.getOrderNo()\r\n                println orderNo\r\n                def sign = checkContext.getSign()\r\n                println sign\r\n                def orderNo2 = order2Context.getOrderNo()\r\n                println orderNo2', 'script');
INSERT INTO `script` VALUES (5, 'demo', 'groovy3', 'groovy测试3', 'return true', 'if_script');
INSERT INTO `script` VALUES (6, 'demo', 'groovy4', 'groovy测试4', '\r\nreturn false', 'if_script');
INSERT INTO `script` VALUES (10, 'demo', 's3', 's3', 'import com.demo.util.SendUtil\ndef f2 = SendUtil.sendMessage(\"1\",\"2\",\"3\",4)\nprintln(f2)', 'script');

SET FOREIGN_KEY_CHECKS = 1;
