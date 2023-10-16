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

 Date: 08/08/2023 17:02:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pl_mqtt_connect
-- ----------------------------
DROP TABLE IF EXISTS `pl_mqtt_connect`;
CREATE TABLE `pl_mqtt_connect`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '项目id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端id',
  `tenant_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常;1删除;2停用 3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'mqtt连接表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_mqtt_connect
-- ----------------------------
INSERT INTO `pl_mqtt_connect` VALUES ('1605386185860325379', 'chlrob', 'admin', 'chlrob', 'zuhu001', '0', 'system', '2023-01-28 10:15:25', 'system', '2023-01-28 10:15:28', NULL);
INSERT INTO `pl_mqtt_connect` VALUES ('1628204065508130816', 'ckbe6v940b35kbhc', '248813101424', 'szymr7mxjcrlmqwb', 'ceshi', '0', 'system', '2023-02-22 09:24:34', 'system', '2023-02-22 09:24:34', NULL);
INSERT INTO `pl_mqtt_connect` VALUES ('1635560065172049920', '06m7f2iced9ptop3', '091506400429', 'jneru9z49ks3gdnc', 'test001', '0', 'system', '2023-03-14 16:34:41', 'system', '2023-03-14 16:34:41', NULL);

-- ----------------------------
-- Table structure for pl_topic
-- ----------------------------
DROP TABLE IF EXISTS `pl_topic`;
CREATE TABLE `pl_topic`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题id',
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `topic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主题',
  `topic_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主题类型',
  `device_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备主题类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常;1删除;2停用 3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '主题表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pl_topic
-- ----------------------------
INSERT INTO `pl_topic` VALUES ('1612738229293129728', '1612738229309906944', 'h523mphyf7ey/iocmds', '10', '10', '0', 'zuhu001_adminzuhu001', '2023-01-10 17:08:52', 'zuhu001_adminzuhu001', '2023-01-10 17:08:52', NULL, 'zuhu001', 'zuhu001');
INSERT INTO `pl_topic` VALUES ('1612738385522565120', '1612738384855670784', 'h523mphyf7ey/n1jw5kykrq21', '5', '5', '0', 'zuhu001_adminzuhu001', '2023-01-10 17:09:29', 'zuhu001_adminzuhu001', '2023-02-14 13:30:34', NULL, 'zuhu001', 'zuhu001');

SET FOREIGN_KEY_CHECKS = 1;
