/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : master

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 03/09/2022 11:33:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_valid` int(10) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(10) NULL DEFAULT NULL,
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade_Id` int(10) NULL DEFAULT NULL,
  `create_date` datetime NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `roleId` int(255) NULL DEFAULT NULL,
  `trueName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'ICy5YqxZB1uWSwcVLSNLcA==', 1, '126@com', '上海', '13851642911', 33, '60', 1, '2022-09-01 14:11:04', '2022-09-02 14:44:53', 1, 'zhangsan');
INSERT INTO `t_user` VALUES (2, 'admin1', 'gdyb21LQTcIANtvYMT7QVQ==', 1, '126@163.com', '上海', '13111111111', 25, '大数据29', 1, '2022-09-01 20:23:18', '2022-09-01 20:23:18', 1, NULL);
INSERT INTO `t_user` VALUES (3, 'xiaodu', 'gdyb21LQTcIANtvYMT7QVQ==', 0, '126@126.com', '上海', '13815641111', 18, '大数据29', 2, '2022-09-02 09:36:10', '2022-09-02 09:36:10', 2, 'xiaodu');
INSERT INTO `t_user` VALUES (4, 'dudu', '123456', 1, '125@126.com', '上海', '13188888888', NULL, 'java59', 2, '2022-09-02 20:15:56', '2022-09-02 20:15:56', 2, NULL);
INSERT INTO `t_user` VALUES (5, 'dudu', '4QrcOUm6Wau+VuBX8g+IPg==', 0, NULL, '上海', '13199999999', NULL, 'java59', 2, '2022-09-02 20:16:50', '2022-09-02 20:16:50', 2, NULL);
INSERT INTO `t_user` VALUES (6, 'dudu', '4QrcOUm6Wau+VuBX8g+IPg==', 0, NULL, '上海', '13111111111', NULL, 'java59', 2, '2022-09-02 20:24:33', '2022-09-02 20:24:33', 2, NULL);
INSERT INTO `t_user` VALUES (7, '1234', 'gdyb21LQTcIANtvYMT7QVQ==', 1, NULL, NULL, '13811111111', NULL, 'java59', NULL, '2022-09-03 09:35:01', '2022-09-03 09:35:01', NULL, NULL);
INSERT INTO `t_user` VALUES (14, '1111119', 'ICy5YqxZB1uWSwcVLSNLcA==', 1, NULL, NULL, '13851642921', NULL, 'java', NULL, '2022-09-03 10:32:26', '2022-09-03 10:32:26', NULL, NULL);
INSERT INTO `t_user` VALUES (15, '1111117', 'ICy5YqxZB1uWSwcVLSNLcA==', 1, NULL, NULL, '13851642921', NULL, 'java', NULL, '2022-09-03 10:33:12', '2022-09-03 10:33:12', NULL, NULL);
INSERT INTO `t_user` VALUES (16, '1111115', 'ICy5YqxZB1uWSwcVLSNLcA==', 1, NULL, NULL, '13851642921', NULL, 'java', NULL, '2022-09-03 10:41:29', '2022-09-03 10:41:29', NULL, NULL);
INSERT INTO `t_user` VALUES (17, '1111112', 'ICy5YqxZB1uWSwcVLSNLcA==', 1, NULL, NULL, '13851642921', NULL, 'java', NULL, '2022-09-03 10:42:31', '2022-09-03 10:42:31', NULL, NULL);
INSERT INTO `t_user` VALUES (18, '1111113', 'ICy5YqxZB1uWSwcVLSNLcA==', 1, NULL, NULL, '13851642921', NULL, 'java', NULL, '2022-09-03 10:42:48', '2022-09-03 10:42:48', NULL, NULL);
INSERT INTO `t_user` VALUES (19, '1111111', 'ICy5YqxZB1uWSwcVLSNLcA==', 1, NULL, NULL, '13851642921', NULL, 'java', NULL, '2022-09-03 10:46:40', '2022-09-03 10:46:40', NULL, NULL);
INSERT INTO `t_user` VALUES (20, '1111231', 'gdyb21LQTcIANtvYMT7QVQ==', 1, NULL, NULL, '13815641111', NULL, 'java59', NULL, '2022-09-03 10:47:36', '2022-09-03 10:47:36', NULL, NULL);
INSERT INTO `t_user` VALUES (21, '111nnn', 'gdyb21LQTcIANtvYMT7QVQ==', 1, NULL, NULL, '13815641111', NULL, 'java59', NULL, '2022-09-03 10:50:00', '2022-09-03 10:50:00', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
