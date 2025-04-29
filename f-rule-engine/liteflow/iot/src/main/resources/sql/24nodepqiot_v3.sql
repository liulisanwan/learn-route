/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : pqiot_v3

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 20/09/2023 13:15:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pl_chain
-- ----------------------------
DROP TABLE IF EXISTS `pl_chain`;
CREATE TABLE `pl_chain`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `application_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `chain_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行链名称',
  `chain_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行链描述',
  `el_data` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'el数据',
  `product_model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品型号',
  `execution_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行key',
  `single_multiple` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单实例多实例',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常;1删除;2停用;3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '执行链表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_chain
-- ----------------------------
INSERT INTO `pl_chain` VALUES ('1', 'demo', 'chain1', 'chain测试', 'THEN(iot1,iot2)', '1', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_chain` VALUES ('2', 'demo', 'chain7', '加锁测试', 'THEN(iot164,iot85,iot126,WHEN(iot164,iot85,iot126))', '7', '7', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_chain` VALUES ('3', 'demo', 'swms1', 'wms测试', 'SWITCH(br1).TO(THEN(br2,br3,br4,br5,br6,br7,br8,br9,br10,br11,br12,br13,br14,br15,br16,br17,br18,br19,br20,br21,br22,br23,br24).id(\"blrk\"))', 'wms', 'wms', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pl_constant
-- ----------------------------
DROP TABLE IF EXISTS `pl_constant`;
CREATE TABLE `pl_constant`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `constant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '常量名称',
  `constant_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '常量类型',
  `constant_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '常量value',
  `chain_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常;1删除;2停用;3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '常量表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_constant
-- ----------------------------
INSERT INTO `pl_constant` VALUES ('1', 'age', 'Long', NULL, 'chain1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pl_control
-- ----------------------------
DROP TABLE IF EXISTS `pl_control`;
CREATE TABLE `pl_control`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `attribute_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '控制属性名称',
  `attribute_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '控制属性类型',
  `attribute_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '控制属性值',
  `node_id` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常;1删除 2停用 3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '控制表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_control
-- ----------------------------
INSERT INTO `pl_control` VALUES ('1', 'sb', 'Long', '0', 'br4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_control` VALUES ('2', 'pdx', 'Long', '1', 'br8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_control` VALUES ('3', 'fw', 'Long', '1', 'br10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_control` VALUES ('4', 'zd', 'Long', '1', 'br15', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_control` VALUES ('5', 'plcrk', 'Long', '1', 'br20', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_control` VALUES ('6', 'rk', 'Long', '1', 'br21', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_control` VALUES ('7', 'finsh', 'Long', '1', 'br23', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pl_execution_log
-- ----------------------------
DROP TABLE IF EXISTS `pl_execution_log`;
CREATE TABLE `pl_execution_log`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `log_data` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志数据',
  `log_time` datetime(0) NULL DEFAULT NULL COMMENT '日志时间',
  `order_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单信息',
  `node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `exception_message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常;1删除;2停用;3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_date` datetime(0) NOT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '执行日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_execution_log
-- ----------------------------

-- ----------------------------
-- Table structure for pl_listen
-- ----------------------------
DROP TABLE IF EXISTS `pl_listen`;
CREATE TABLE `pl_listen`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `attribute_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '监听属性名称',
  `attribute_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '监听属性类型',
  `attribute_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '监听属性值',
  `node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `switch_node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '选择节点id',
  `ignore_check` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '忽略比对',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常;1删除 2停用 3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '监听表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_listen
-- ----------------------------
INSERT INTO `pl_listen` VALUES ('1', 'start', 'Long', '0', 'iot1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('10', 'agv_huidiao', 'Long', NULL, 'br16', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('11', 'plc_fuwei2', 'Long', NULL, 'br17', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('12', 'rdid_ruku', 'Long', NULL, 'br18', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('13', 'rkwc', 'Long', '1', 'br22', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('2', 'start', 'Long', '0', 'br1', 'blrk', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('3', 'stop', 'Long', '0', 'br1', 'blrk', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('4', 'rk', 'Long', '0', 'br1', 'blrk', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('5', 'rfid', 'Long', NULL, 'br2', NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('6', 'agv_status', 'Long', '1', 'br6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('7', 'agv_status', 'Long', '1', 'br9', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('8', 'agv_status', 'Long', '1', 'br12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_listen` VALUES ('9', 'agv_rl', 'Long', '1', 'br13', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pl_other_node_lock
-- ----------------------------
DROP TABLE IF EXISTS `pl_other_node_lock`;
CREATE TABLE `pl_other_node_lock`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `sub_product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子产品名称',
  `node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常;1删除 2停用 3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '其他节点加锁表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_other_node_lock
-- ----------------------------
INSERT INTO `pl_other_node_lock` VALUES ('1', 'test7', 'iot126', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pl_script_node
-- ----------------------------
DROP TABLE IF EXISTS `pl_script_node`;
CREATE TABLE `pl_script_node`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `application_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `script_node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `script_node_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点名称',
  `script_node_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点类型',
  `script_node_data` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点数据',
  `script_language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点语言',
  `sub_product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子产品名称',
  `idle` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '空闲',
  `release_redis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否释放',
  `alias_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '别名id',
  `chain_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行链id',
  `redis_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'redis中的key',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常;1删除;2停用;3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '执行节点表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_script_node
-- ----------------------------
INSERT INTO `pl_script_node` VALUES ('0363e058ae2afc53c3201e976205e293', 'demo', 'br14', '允许入料，调用agv小车皮带线外传,送料到plc2', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.THIRD,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('05019ed3a180fc474a4ffd81291bac66', 'demo', 'br12', 'agv到达输送平台2回调mes', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', 'ruku_agv', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('07bc5edc1e7e8d35aefa2def386ebb1f', 'demo', 'br4', 'plc物料通过', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.CONTROL,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('095990519a136da4fc8c724eef2ab606', 'demo', 'br20', 'plc物料通过', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.CONTROL,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('1', 'demo', 'iot1', 'iot1', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\r\nimport com.iot.custom_node.CustomNodeExecuteFactory;\r\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\r\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\r\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\r\n\r\npublic class Demo implements JaninoCommonScriptBody {\r\n    public Void body(ScriptExecuteWrap executeWrap) {\r\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\r\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\r\n        return null;\r\n    }\r\n}', 'java', 'test2', '1', '0', 'iot1', 'chain1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('2', 'demo', 'iot2', 'iot2', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\r\nimport com.iot.custom_node.CustomNodeExecuteFactory;\r\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\r\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\r\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\r\n\r\npublic class Demo2 implements JaninoCommonScriptBody {\r\n    public Void body(ScriptExecuteWrap executeWrap) {\r\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\r\n        nodeExecuteFactory.execute(ExecuteType.THIRD,executeWrap);\r\n        return null;\r\n    }\r\n}', 'java', 'test2', '0', '1', 'iot1', 'chain1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('37d6f45e9fdc30a47f0ac98122d8f9c4', 'demo', 'br23', '入库完成', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.CONTROL,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('4aee437127c3d03d59158b34d69c1933', 'demo', 'br7', 'avg皮带线内转1', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.THIRD,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('5c8107b44e59b38bed4a513f399c5eaa', 'demo', 'br10', 'plc复位1', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.CONTROL,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '1', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('5df6221b3be437a604d95e93407b9d02', 'demo', 'br8', 'plc皮带线转1', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.CONTROL,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('6db41639c0e251019d07cf3ca3196ae9', 'demo', 'br22', '监听入库完成信号', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', 'wz_agv', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('7429952ff2cd3692019db2b286d87bcd', 'demo', 'iot164', 'iot164', 'script', 'import com.yomahub.liteflow.script.ScriptExecuteWrap;\r\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\r\n\r\n\r\npublic class ListenDemo2 implements JaninoCommonScriptBody {\r\n    public Void body(ScriptExecuteWrap executeWrap) {\r\n        String nodeId = executeWrap.cmp.getNodeId();\r\n        System.err.println(nodeId+\"执行了\");\r\n        return null;\r\n    }\r\n}', 'java', 'test7', '1', '0', 'iot164', 'chain7', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('778e7d583c6438d95785eb637319db6c', 'demo', 'br1', '判断自动', 'switch_script', 'import com.iot.custom_node.CustomNodeExecuteType;\r\nimport com.iot.custom_node.CustomNodeExecuteFactory;\r\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\r\nimport com.yomahub.liteflow.script.body.JaninoSwitchScriptBody;\r\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\r\n\r\npublic class Demo3 implements JaninoSwitchScriptBody {\r\n    \r\n    public String body(ScriptExecuteWrap executeWrap) {\r\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\r\n        String key = nodeExecuteFactory.execute(ExecuteType.SWITCH, executeWrap).toString();\r\n        return key;\r\n    }\r\n}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', 'zidong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('7aa08cd68eb409ed8ab1fb76954f6497', 'demo', 'br19', 'mes给排行列', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.THIRD,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('81f98ca61dd953277a793043dc53c5b8', 'demo', 'br6', 'agv回调1', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '0', 'blrk', 'swms1', 'agv1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('83c0f628845cb9d185dad1a784d41f98', 'demo', 'br13', '监听plc输送平台2是否空闲允许agv小车入料', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', 'yxrl', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('88b35faecda2f60453fc4a67924885a8', 'demo', 'br24', '封闭mes订单任务', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.THIRD,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('9a317503970e80a411e717ef2ca2a634', 'demo', 'iot85', 'iot85', 'script', 'import com.yomahub.liteflow.script.ScriptExecuteWrap;\r\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\r\n\r\n\r\npublic class ListenDemo2 implements JaninoCommonScriptBody {\r\n    public Void body(ScriptExecuteWrap executeWrap) {\r\n        String nodeId = executeWrap.cmp.getNodeId();\r\n        System.err.println(nodeId+\"执行了\");\r\n        return null;\r\n    }\r\n}', 'java', 'test7', '0', '0', 'iot85', 'chain7', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('a54acde8281d6204a70bb76a480adb38', 'demo', 'br15', '给plc输送2 一个内转的信号，让皮带线转动', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.CONTROL,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('ab5aac6f76dce55a0e4cbc2a82ed00c2', 'demo', 'br3', '订单查询', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.THIRD,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('bad639979efa2cc849e161984f66b410', 'demo', 'br9', 'avg内转回调1', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '0', 'blrk', 'swms1', 'agv2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('c7b1eb541f7ef234cabe0f5ae2539b8b', 'demo', 'br5', 'agv调用1', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.THIRD,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('cc830cc121bd815e089a933fcc777ea2', 'demo', 'br18', '监听Rfid是否可以入库', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', 'wz_agv', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('cc987167f866781f79ffd87fa4409325', 'demo', 'br2', '监听rfid', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '0', '0', 'blrk', 'swms1', 'rfid', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('cd89802ce2894791f55adabb0665c79a', 'demo', 'br21', '控制入库', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.CONTROL,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('d79edfef8f0c46bce231c2f4694fa5ae', 'demo', 'iot126', 'iot126', 'script', 'import com.yomahub.liteflow.script.ScriptExecuteWrap;\r\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\r\n\r\n\r\npublic class ListenDemo2 implements JaninoCommonScriptBody {\r\n    public Void body(ScriptExecuteWrap executeWrap) {\r\n        String nodeId = executeWrap.cmp.getNodeId();\r\n        System.err.println(nodeId+\"执行了\");\r\n        return null;\r\n    }\r\n}', 'java', 'test7', '0', '1', 'iot126', 'chain7', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('e66c3354e7f41533a5c2ac926649b110', 'demo', 'br11', '调用AGV小车到达库房输送平台2', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.THIRD,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('f225c79db75dee3d1f113f24648b6cc0', 'demo', 'br16', '小车外传回调', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', 'wz_agv', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('faf743d010b702badce6993b77f652ad', 'demo', 'iot86', 'iot86', 'script', 'import com.yomahub.liteflow.script.ScriptExecuteWrap;\r\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\r\n\r\n\r\npublic class ListenDemo2 implements JaninoCommonScriptBody {\r\n    public Void body(ScriptExecuteWrap executeWrap) {\r\n        String nodeId = executeWrap.cmp.getNodeId();\r\n        System.err.println(nodeId+\"执行了\");\r\n        return null;\r\n    }\r\n}', 'java', 'test8', '1', '1', 'iot86', 'chain7', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_script_node` VALUES ('fbdbb062fa9cbacb4678fdd78ecd307a', 'demo', 'br17', 'plc复位2', 'script', 'import com.iot.custom_node.CustomNodeExecuteType;\nimport com.iot.custom_node.CustomNodeExecuteFactory;\nimport com.yomahub.liteflow.script.ScriptExecuteWrap;\nimport com.yomahub.liteflow.script.body.JaninoCommonScriptBody;\nimport com.yomahub.liteflow.spi.holder.ContextAwareHolder;\npublic class Demo implements JaninoCommonScriptBody {\n    public Void body(ScriptExecuteWrap executeWrap) {\n        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);\n        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);\n        return null;\n}}', 'java', 'wms库房', '1', '0', 'blrk', 'swms1', 'huidiaoPLC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pl_third_party
-- ----------------------------
DROP TABLE IF EXISTS `pl_third_party`;
CREATE TABLE `pl_third_party`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `interaction_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交互类型',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `request_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `node_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点id',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常;1删除;2停用;3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_third_party
-- ----------------------------
INSERT INTO `pl_third_party` VALUES ('1', 'http', 'http://localhost:9865/wmsTest/order', 'GET', 'br3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party` VALUES ('2', 'http', 'http://localhost:9865/wmsTest/agv', 'GET', 'br5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party` VALUES ('3', 'http', 'http://localhost:9865/wmsTest/agv', 'GET', 'br7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party` VALUES ('4', 'http', 'http://localhost:9865/wmsTest/agv', 'GET', 'br11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party` VALUES ('5', 'http', 'http://localhost:9865/wmsTest/agv', 'GET', 'br14', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party` VALUES ('6', 'http', 'http://localhost:9865/wmsTest/agv', 'GET', 'br19', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party` VALUES ('7', 'http', 'http://localhost:9865/wmsTest/order', 'GET', 'br24', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pl_third_party_data
-- ----------------------------
DROP TABLE IF EXISTS `pl_third_party_data`;
CREATE TABLE `pl_third_party_data`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `third_party_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方id',
  `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方数据类型',
  `data_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方数据值',
  `request_response_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方请求或响应类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（0正常;1删除;2停用;3冻结）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `corp_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户代码',
  `corp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pl_third_party_data
-- ----------------------------
INSERT INTO `pl_third_party_data` VALUES ('1', '1', 'Long', '{\"orderNo\":\"\"}', 'request', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('10', '5', 'Long', '{\"status\":\"done\"}', 'response', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('11', '6', 'Long', '{\"orderNo\":\"1\"}', 'request', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('12', '6', 'Long', '{\"status\":\"done\"}', 'response', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('13', '7', 'Long', '{\"orderNo\":\"\"}', 'request', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('14', '7', 'Long', '{\"flag\":1}', 'response', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('2', '1', 'Long', '{\"flag\":1}', 'response', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('3', '2', 'Long', '{\"orderNo\":\"1\"}', 'request', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('4', '2', 'Long', '{\"status\":\"done\"}', 'response', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('5', '3', 'Long', '{\"orderNo\":\"1\"}', 'request', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('6', '3', 'Long', '{\"status\":\"done\"}', 'response', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('7', '4', 'Long', '{\"orderNo\":\"1\"}', 'request', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('8', '4', 'Long', '{\"status\":\"done\"}', 'response', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `pl_third_party_data` VALUES ('9', '5', 'Long', '{\"orderNo\":\"1\"}', 'request', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
