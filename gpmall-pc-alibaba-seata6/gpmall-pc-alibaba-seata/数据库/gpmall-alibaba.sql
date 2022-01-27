/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.8.132（上课专用）
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 192.168.8.132:3306
 Source Schema         : gpmall-alibaba

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 22/01/2022 22:29:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mms_lottery
-- ----------------------------
DROP TABLE IF EXISTS `mms_lottery`;
CREATE TABLE `mms_lottery`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `topic` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `state` int NOT NULL DEFAULT 1 COMMENT '活动状态，1-上线，2-下线',
  `link` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `images` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `start_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mms_lottery
-- ----------------------------
INSERT INTO `mms_lottery` VALUES (1, '幸运大抽奖', 1, 'localhost:8080/lottery', NULL, '2021-07-01 22:06:23', '2027-07-31 22:06:28', '2021-07-01 22:06:32');

-- ----------------------------
-- Table structure for mms_lottery_chance
-- ----------------------------
DROP TABLE IF EXISTS `mms_lottery_chance`;
CREATE TABLE `mms_lottery_chance`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `now_limit` int NOT NULL DEFAULT 0,
  `max_limit` int UNSIGNED NOT NULL DEFAULT 0,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UNIQUE_KEY_UID`(`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mms_lottery_chance
-- ----------------------------
INSERT INTO `mms_lottery_chance` VALUES (1, 75, 2, 10, '2022-01-18 21:57:57');
INSERT INTO `mms_lottery_chance` VALUES (2, 76, 1, 1, '2022-01-18 23:15:46');
INSERT INTO `mms_lottery_chance` VALUES (19, 122, 0, 1, '2022-01-21 13:30:51');

-- ----------------------------
-- Table structure for mms_lottery_item
-- ----------------------------
DROP TABLE IF EXISTS `mms_lottery_item`;
CREATE TABLE `mms_lottery_item`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `lottery_id` int NULL DEFAULT NULL COMMENT '活动id',
  `item_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '奖项名称',
  `level` int NOT NULL COMMENT '奖项等级',
  `percent` decimal(2, 2) NOT NULL COMMENT '奖项概率',
  `prize_id` int NOT NULL COMMENT '奖品id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `default_item` int NULL DEFAULT 0 COMMENT '是否是默认的奖项, 0-不是 ， 1-是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mms_lottery_item
-- ----------------------------
INSERT INTO `mms_lottery_item` VALUES (1, 1, '一等奖', 1, 0.02, 1, '2021-07-01 22:10:00', 100000);
INSERT INTO `mms_lottery_item` VALUES (2, 1, '二等奖', 2, 0.09, 2, '2021-07-01 22:11:10', 100000);
INSERT INTO `mms_lottery_item` VALUES (3, 1, '三等奖', 3, 0.20, 3, '2021-07-01 22:11:37', 100000);
INSERT INTO `mms_lottery_item` VALUES (4, 1, '四等奖', 4, 0.30, 4, '2021-07-01 22:12:25', 100000);
INSERT INTO `mms_lottery_item` VALUES (5, 1, '五等奖', 5, 0.40, 5, '2021-07-01 22:12:48', 100000);
INSERT INTO `mms_lottery_item` VALUES (6, 1, '六等奖', 6, 0.80, 6, '2021-07-01 22:13:04', 1);

-- ----------------------------
-- Table structure for mms_lottery_prize
-- ----------------------------
DROP TABLE IF EXISTS `mms_lottery_prize`;
CREATE TABLE `mms_lottery_prize`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `lottery_id` int NULL DEFAULT NULL COMMENT '活动ID',
  `prize_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '奖品名称',
  `prize_type` int NOT NULL COMMENT '奖品类型， -1-谢谢参与、1-普通奖品、2-唯一性奖品',
  `total_stock` int NULL DEFAULT NULL COMMENT '总库存',
  `valid_stock` int NULL DEFAULT NULL COMMENT '可用库存',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mms_lottery_prize
-- ----------------------------
INSERT INTO `mms_lottery_prize` VALUES (1, 1, '55寸小米电视', 1, 1, 1, NULL);
INSERT INTO `mms_lottery_prize` VALUES (2, 1, 'AirPods', 1, 5, 3, NULL);
INSERT INTO `mms_lottery_prize` VALUES (3, 1, '摄影背包', 1, 10, 9, NULL);
INSERT INTO `mms_lottery_prize` VALUES (4, 1, '三脚架套餐', 1, 15, 14, NULL);
INSERT INTO `mms_lottery_prize` VALUES (5, 1, '移动电源', 1, 40, 34, NULL);
INSERT INTO `mms_lottery_prize` VALUES (6, 1, '记事本', -1, 1000, 1000, NULL);

-- ----------------------------
-- Table structure for mms_lottery_record
-- ----------------------------
DROP TABLE IF EXISTS `mms_lottery_record`;
CREATE TABLE `mms_lottery_record`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `item_id` int NOT NULL,
  `prize_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mms_lottery_record
-- ----------------------------
INSERT INTO `mms_lottery_record` VALUES (6, '0:0:0:0:0:0:0:1', 5, '移动电源', '2022-01-18 21:03:18');
INSERT INTO `mms_lottery_record` VALUES (7, '0:0:0:0:0:0:0:1', 6, '记事本', '2022-01-18 21:15:07');
INSERT INTO `mms_lottery_record` VALUES (8, '0:0:0:0:0:0:0:1', 6, '记事本', '2022-01-18 21:15:11');
INSERT INTO `mms_lottery_record` VALUES (9, '0:0:0:0:0:0:0:1', 6, '记事本', '2022-01-18 21:15:16');
INSERT INTO `mms_lottery_record` VALUES (10, '0:0:0:0:0:0:0:1', 6, '记事本', '2022-01-18 21:15:16');
INSERT INTO `mms_lottery_record` VALUES (11, '0:0:0:0:0:0:0:1', 6, '记事本', '2022-01-18 21:15:43');
INSERT INTO `mms_lottery_record` VALUES (12, '0:0:0:0:0:0:0:1', 6, '记事本', '2022-01-18 21:21:49');
INSERT INTO `mms_lottery_record` VALUES (13, '0:0:0:0:0:0:0:1', 5, '移动电源', '2022-01-18 21:21:50');
INSERT INTO `mms_lottery_record` VALUES (14, '75', 6, '记事本', '2022-01-18 22:02:11');
INSERT INTO `mms_lottery_record` VALUES (15, '76', 4, '三脚架套餐', '2022-01-18 23:16:59');

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '性别',
  `state` int NULL DEFAULT 0 COMMENT '状态（1，有效，0，无效）',
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `points` int NULL DEFAULT 0 COMMENT '积分',
  `balance` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `created` datetime(0) NULL DEFAULT NULL,
  `updated` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_user
-- ----------------------------
INSERT INTO `ums_user` VALUES (62, 'test', '098f6bcd4621d373cade4e832627b4f6', NULL, NULL, NULL, 1, 'https://gper.club/server-img/avatars/000/00/00/user_origin_30.jpg?time1565591384242', NULL, NULL, NULL, '2017-09-05 21:27:54', '2017-10-08 18:13:51');
INSERT INTO `ums_user` VALUES (75, 'mic', 'mic', NULL, NULL, '', 0, NULL, 0, 0.00, NULL, NULL, NULL);
INSERT INTO `ums_user` VALUES (122, 'mic122', 'mic122', NULL, NULL, '', 1, NULL, 0, 0.00, NULL, '2022-01-21 13:30:51', '2022-01-21 13:30:51');

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_id` bigint NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
