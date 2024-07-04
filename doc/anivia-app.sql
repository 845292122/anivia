/*
 Navicat Premium Dump SQL

 Source Server         : local_mysql
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : anivia-app

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 04/07/2024 15:57:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` int DEFAULT NULL COMMENT '租户ID',
  `title` varchar(255) DEFAULT NULL COMMENT '模块标题',
  `type` tinyint(1) DEFAULT NULL COMMENT '操作类型',
  `type_name` varchar(255) DEFAULT NULL COMMENT '操作类型中文',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `param` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  `result` varchar(2000) DEFAULT NULL COMMENT '请求结果',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态: 1 成功; 0 失败',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint DEFAULT NULL COMMENT '消耗时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm`;
CREATE TABLE `sys_perm` (
  `id` int NOT NULL AUTO_INCREMENT,
  `p_id` int DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `key` varchar(50) DEFAULT NULL COMMENT '权限标识符',
  `deleted` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_perm
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `company` varchar(255) DEFAULT NULL COMMENT '企业名称',
  `license` varchar(30) DEFAULT NULL COMMENT '社会信用代码',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `intro` varchar(255) DEFAULT NULL COMMENT '企业信息',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `perms` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_tenant_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant_perm`;
CREATE TABLE `sys_tenant_perm` (
  `tenant_id` int NOT NULL,
  `perm_id` int NOT NULL,
  PRIMARY KEY (`tenant_id`,`perm_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_tenant_perm
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
