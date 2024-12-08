/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : init

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 08/12/2024 17:15:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xx_permission
-- ----------------------------
DROP TABLE IF EXISTS `xx_permission`;
CREATE TABLE `xx_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `p_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限码',
  `p_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xx_permission
-- ----------------------------
INSERT INTO `xx_permission` VALUES (1, 'per:add', '新增权限', '2024-12-08 16:03:55', '2024-12-08 16:03:55', 0);
INSERT INTO `xx_permission` VALUES (2, 'per:udpate', '修改权限', '2024-12-08 16:04:07', '2024-12-08 16:04:07', 0);
INSERT INTO `xx_permission` VALUES (3, 'per:deleted', '删除权限', '2024-12-08 16:04:26', '2024-12-08 16:04:26', 0);
INSERT INTO `xx_permission` VALUES (4, 'user:deleted', '删除用户', '2024-12-08 16:04:51', '2024-12-08 16:04:51', 0);
INSERT INTO `xx_permission` VALUES (5, 'role:deleted', '删除角色', '2024-12-08 16:08:07', '2024-12-08 16:08:07', 0);
INSERT INTO `xx_permission` VALUES (6, 'role:add', '新增角色', '2024-12-08 16:08:31', '2024-12-08 16:08:31', 0);
INSERT INTO `xx_permission` VALUES (7, 'role:update', '更新角色', '2024-12-08 16:08:40', '2024-12-08 16:08:40', 0);

-- ----------------------------
-- Table structure for xx_role
-- ----------------------------
DROP TABLE IF EXISTS `xx_role`;
CREATE TABLE `xx_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色码',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xx_role
-- ----------------------------
INSERT INTO `xx_role` VALUES (1, 'SYS_USER', '普通用户', '2024-12-08 15:43:25', '2024-12-08 15:43:25', 0);
INSERT INTO `xx_role` VALUES (2, 'SYS_ADMIN', '管理员', '2024-12-08 15:43:25', '2024-12-08 15:43:25', 0);
INSERT INTO `xx_role` VALUES (3, 'SYS_VISITOR', '游客', '2024-12-08 15:43:25', '2024-12-08 15:43:25', 0);

-- ----------------------------
-- Table structure for xx_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `xx_role_permission`;
CREATE TABLE `xx_role_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `r_id` bigint(0) NULL DEFAULT NULL COMMENT '角色 ID',
  `p_id` bigint(0) NULL DEFAULT NULL COMMENT '权限 ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xx_role_permission
-- ----------------------------
INSERT INTO `xx_role_permission` VALUES (1, 1, 1, NULL, '2024-12-08 16:15:43', 0);
INSERT INTO `xx_role_permission` VALUES (2, 1, 2, NULL, '2024-12-08 16:15:46', 0);
INSERT INTO `xx_role_permission` VALUES (3, 1, 3, NULL, '2024-12-08 16:15:49', 0);
INSERT INTO `xx_role_permission` VALUES (4, 1, 4, NULL, '2024-12-08 16:15:52', 0);

-- ----------------------------
-- Table structure for xx_user
-- ----------------------------
DROP TABLE IF EXISTS `xx_user`;
CREATE TABLE `xx_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信 OpenID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `sex` int(0) NULL DEFAULT NULL COMMENT '性别',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '加密盐',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xx_user
-- ----------------------------
INSERT INTO `xx_user` VALUES (1, 'xx_user', '新用户', NULL, NULL, '2024-12-08 14:37:27', '2024-12-08 14:37:27', 0, NULL, 2, '6d320d17f849d2f91fe708bbdc1a458a', 'ee89431e', NULL);
INSERT INTO `xx_user` VALUES (2, 'xx_none', '新用户', NULL, NULL, '2024-12-08 16:09:07', '2024-12-08 16:09:07', 0, NULL, 2, '800c5392c4e416a92ad4b7acbe222b91', 'fc5746ad', NULL);

-- ----------------------------
-- Table structure for xx_user_role
-- ----------------------------
DROP TABLE IF EXISTS `xx_user_role`;
CREATE TABLE `xx_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `u_id` bigint(0) NULL DEFAULT NULL COMMENT '用户 ID',
  `r_id` bigint(0) NULL DEFAULT NULL COMMENT '角色 ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xx_user_role
-- ----------------------------
INSERT INTO `xx_user_role` VALUES (1, 1, 1, '2024-12-08 14:37:27', '2024-12-08 14:37:27', 0);
INSERT INTO `xx_user_role` VALUES (2, 2, 1, '2024-12-08 16:09:07', '2024-12-08 16:09:07', 0);

SET FOREIGN_KEY_CHECKS = 1;
