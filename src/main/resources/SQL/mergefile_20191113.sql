/*
Navicat MySQL Data Transfer

Date: 2019-11-13 22:08:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `P_CHECK_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `P_CHECK_LOG`;
CREATE TABLE `P_CHECK_LOG` (
  `id` varchar(32) NOT NULL,
  `file_id` varchar(32) DEFAULT NULL,
  `check_config_type` varchar(32) DEFAULT '本次检验所用配置类型',
  `check_result` varchar(1) DEFAULT '' COMMENT '0未通过，1通过',
  `date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of P_CHECK_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for `P_CONFIG`
-- ----------------------------
DROP TABLE IF EXISTS `P_CONFIG`;
CREATE TABLE `P_CONFIG` (
  `id` varchar(32) NOT NULL,
  `file_id` varchar(32) DEFAULT NULL,
  `config_key` varchar(10) DEFAULT NULL,
  `config_value` varchar(225) DEFAULT NULL,
  `date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of P_CONFIG
-- ----------------------------

-- ----------------------------
-- Table structure for `P_DICTIONARY`
-- ----------------------------
DROP TABLE IF EXISTS `P_DICTIONARY`;
CREATE TABLE `P_DICTIONARY` (
  `id` varchar(32) DEFAULT NULL,
  `key` varchar(10) DEFAULT '',
  `value` varchar(225) DEFAULT NULL,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(225) DEFAULT NULL,
  `date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of P_DICTIONARY
-- ----------------------------

-- ----------------------------
-- Table structure for `P_FILE_INFO`
-- ----------------------------
DROP TABLE IF EXISTS `P_FILE_INFO`;
CREATE TABLE `P_FILE_INFO` (
  `id` varchar(32) NOT NULL,
  `file_name` varchar(225) DEFAULT NULL,
  `upload_user` varchar(225) DEFAULT NULL,
  `file_path` varchar(225) DEFAULT NULL,
  `file_is_ftp` varchar(1) DEFAULT NULL COMMENT '0已上传ftp；1未上传ftp',
  `file_ftp_name` varchar(225) DEFAULT NULL COMMENT '文件信息表',
  `date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of P_FILE_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for `P_MERGE_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `P_MERGE_LOG`;
CREATE TABLE `P_MERGE_LOG` (
  `id` varchar(32) NOT NULL,
  `merge_log` text,
  `date_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of P_MERGE_LOG
-- ----------------------------
