/*
Navicat MySQL Data Transfer

Source Server         : l
Source Server Version : 50622
Source Host           : 127.0.0.1:3306
Source Database       : house

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-09-25 09:25:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bargain
-- ----------------------------
DROP TABLE IF EXISTS `bargain`;
CREATE TABLE `bargain` (
  `buyerID` int(20) NOT NULL,
  `houseID` int(20) NOT NULL,
  `sellerID` int(20) NOT NULL,
  `price` double(20,0) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`buyerID`,`houseID`,`sellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of bargain
-- ----------------------------
INSERT INTO `bargain` VALUES ('24', '37', '2', null, '2015-08-28 18:38:14', null);
INSERT INTO `bargain` VALUES ('24', '38', '2', null, '2015-08-28 18:38:14', null);
INSERT INTO `bargain` VALUES ('25', '37', '2', null, '2015-08-28 18:38:55', null);
INSERT INTO `bargain` VALUES ('25', '38', '2', null, '2015-08-28 18:38:55', null);
INSERT INTO `bargain` VALUES ('26', '39', '2', null, '2015-08-29 13:16:16', '0');
INSERT INTO `bargain` VALUES ('26', '40', '2', null, '2015-08-29 13:16:16', '0');

-- ----------------------------
-- Table structure for buyer
-- ----------------------------
DROP TABLE IF EXISTS `buyer`;
CREATE TABLE `buyer` (
  `buyerID` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `phone` int(20) DEFAULT NULL,
  `weiChat` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`buyerID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of buyer
-- ----------------------------
INSERT INTO `buyer` VALUES ('24', '1', null, '16', '1', '2');
INSERT INTO `buyer` VALUES ('25', '1', null, '16', '1', '2');
INSERT INTO `buyer` VALUES ('26', 'zzy', null, '19', '138832', 'weichart');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `houseID` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `location` varchar(60) COLLATE utf8_bin DEFAULT NULL,
  `size` double(10,0) DEFAULT NULL,
  `years` int(10) DEFAULT NULL,
  `price` double(20,0) DEFAULT NULL,
  `brief` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `picture` text COLLATE utf8_bin,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`houseID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES ('37', 'zzy', 'kobe', '111', '11', '1222', '11', 0x486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383238313631333436302E6A706723486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383238313631333436312E6A706723486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383238313631333436322E6A706723, '0');
INSERT INTO `house` VALUES ('38', 'weichar', 'weicha', '111', '1111', '111', '11', 0x486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383238313631343538302E6A706723486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383238313631343538312E6A706723486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383238313631343538322E6A706723, '0');
INSERT INTO `house` VALUES ('39', '南坪', '重庆', '15000', '21', '15000', '环境伊人', 0x486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383238323034363036302E4A504723486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383238323034363036312E4A504723, '0');
INSERT INTO `house` VALUES ('40', 'DELAIWEN', 'CHONGQING', '11', '11', '11', '11', 0x486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383239313234353037302E706E6723486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383239313234353037312E6A706723486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383239313234353037322E6A706723, '0');
INSERT INTO `house` VALUES ('41', '弄', '哦哦哦', '333', '66', '666', '哦哦哦', 0x486F7573655365727669636530322F75706C6F616446696C65506C6163652F3230313530383330313230353132302E4A504723, '0');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `sex` varchar(12) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `weichat` varchar(60) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL COMMENT '管理员入公司的时间',
  `account` varchar(255) NOT NULL,
  PRIMARY KEY (`managerID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'Hello', '男', '2', '2223444222222222', 'hel', 'jkdfjkdjf', '2015-08-22', '1');
INSERT INTO `manager` VALUES ('2', null, null, null, null, null, null, '2015-08-22', '2');

-- ----------------------------
-- Table structure for manageraccount
-- ----------------------------
DROP TABLE IF EXISTS `manageraccount`;
CREATE TABLE `manageraccount` (
  `password` varchar(60) NOT NULL,
  `account` varchar(255) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manageraccount
-- ----------------------------
INSERT INTO `manageraccount` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8_bin,
  `date` date DEFAULT NULL,
  `managerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', 0x68656C6C, '2015-08-10', '1');
INSERT INTO `notice` VALUES ('2', 0x636F6E74656E7432, '2015-08-13', '1');
INSERT INTO `notice` VALUES ('3', 0x636F6E74656E7433, '2015-08-13', '1');
INSERT INTO `notice` VALUES ('11', 0x68656C6C6F, '2015-07-09', '2');
INSERT INTO `notice` VALUES ('12', 0x68656C6C, null, null);
INSERT INTO `notice` VALUES ('21', 0x6B6F6265, '2015-08-19', '1');
INSERT INTO `notice` VALUES ('22', 0xC3A5C297C2AFC3A5C297C2AF, '2015-08-20', '1');
INSERT INTO `notice` VALUES ('23', 0xC3A5C2A5C2BDC3A5C2A5C2BDC3A5C2ADC2A6C3A4C2B9C2A0, '2015-08-20', '1');
INSERT INTO `notice` VALUES ('24', 0x627676737673, '2015-08-20', '1');
INSERT INTO `notice` VALUES ('25', 0xC3A4C2BDC2A0C3A5C2BEC288C3A5C2A5C2BD, '2015-08-28', '1');
INSERT INTO `notice` VALUES ('26', 0xC3A5C28FC2AFC3A7C288C2B1, '2015-08-30', '1');

-- ----------------------------
-- Table structure for owner
-- ----------------------------
DROP TABLE IF EXISTS `owner`;
CREATE TABLE `owner` (
  `ownerID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `age` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `weiChat` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `managerAccount` varchar(60) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ownerID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of owner
-- ----------------------------
INSERT INTO `owner` VALUES ('1', null, '1', null, '1234556', null, '1');
INSERT INTO `owner` VALUES ('2', null, '1', null, '1234556', null, '1');
INSERT INTO `owner` VALUES ('3', null, '1', null, '1234556', null, '1');
INSERT INTO `owner` VALUES ('4', 'zzy', '21', 'kobe', '111', '11', '6');
INSERT INTO `owner` VALUES ('5', '1', '1', '1', '11', '1', '6');
INSERT INTO `owner` VALUES ('6', '1', '1', '1', '1', '1', '6');
INSERT INTO `owner` VALUES ('7', 'zhangzhiyuan', '21', 'nan', '13884333', 'weichart', '6');
INSERT INTO `owner` VALUES ('8', 'zhangzhiyuan', '21', 'nan', '13884333', 'weichart', '6');
INSERT INTO `owner` VALUES ('9', 'zhangzhiyuan', '20', 'nan', '1399932332', 'weihrt', '6');
INSERT INTO `owner` VALUES ('10', 'huaige', '19', 'nan', '138832333', 'weichart', '6');
INSERT INTO `owner` VALUES ('11', 'é£', '3', '2', '6', 'è¯', '6');
INSERT INTO `owner` VALUES ('12', 'é£', '3', '2', '6', 'è¯', '6');
INSERT INTO `owner` VALUES ('13', 'é£', '3', '2', '6', 'è¯', '6');
INSERT INTO `owner` VALUES ('14', 'sutong', '21', 'nan', '13883233', 'weichart', '6');
INSERT INTO `owner` VALUES ('15', 'null', '0', 'null', '0', 'null', '6');
INSERT INTO `owner` VALUES ('16', 'null', '0', 'null', '0', 'null', '6');
INSERT INTO `owner` VALUES ('17', 'zzy', '2111', 'nan', '138832', 'weichart', '6');
INSERT INTO `owner` VALUES ('18', 'zzy', '21', 'kobe', '23', 'en', '6');
INSERT INTO `owner` VALUES ('19', '8', '5', '5', '2', 'v6', '6');
INSERT INTO `owner` VALUES ('20', 'å¼ å¿è¿', '21', 'ç·', '138832', 'å°±çç', '6');
INSERT INTO `owner` VALUES ('21', '2', '52', '5', '5588888', 'é¸é¸é¸é¸é¸é¸é¸', '6');
INSERT INTO `owner` VALUES ('22', 'zzy', '21', 'nan', '138832', 'kj ', '6');
INSERT INTO `owner` VALUES ('23', 'å¼ å¿è¿', '21', 'ç·', '138832333', 'æä»¬', '6');
INSERT INTO `owner` VALUES ('24', '1', '1', '1', '1', '1', '6');
INSERT INTO `owner` VALUES ('25', 'ç¸ç¥è¿', '20', 'å¥³', '138832', 'å¾®ç¬', '6');
INSERT INTO `owner` VALUES ('26', 'å¼ å¿è¿', '19', 'ç·', '138832333', 'å¾®ä¿¡', '6');
INSERT INTO `owner` VALUES ('27', 'å¼ å¿è¿', '19', 'null', '138832333', 'å¾®ä¿¡', '6');
INSERT INTO `owner` VALUES ('28', 'å¼ å¿è¿å§', '19', 'å¥³', '138832333', 'å¾®ä¿¡', '6');
INSERT INTO `owner` VALUES ('29', '张志远吧', '19', '女', '138832333', '微信', '6');
INSERT INTO `owner` VALUES ('30', '张志远吧', '19', '女', '138832333', '微信', '6');
INSERT INTO `owner` VALUES ('31', '刚刚55', '16', 'null', '666', '别别别', '6');
INSERT INTO `owner` VALUES ('32', 'zzy', '19', '女', '2388323', 'weichart', '6');
INSERT INTO `owner` VALUES ('33', 'kobe', '19', '女', '138832333', 'weichart', '6');
INSERT INTO `owner` VALUES ('34', 'kobe', '20', '女', '138832', 'weichar', '6');
INSERT INTO `owner` VALUES ('35', '张志远', '19', '女', '138832', '微信', '6');
INSERT INTO `owner` VALUES ('36', 'ZHANGZHIYUAN', '19', '女', '139932', 'WEICHART', '6');
INSERT INTO `owner` VALUES ('37', 'null', '0', 'null', '0', 'null', '6');

-- ----------------------------
-- Table structure for ownerhouserelation
-- ----------------------------
DROP TABLE IF EXISTS `ownerhouserelation`;
CREATE TABLE `ownerhouserelation` (
  `HouseID` int(11) NOT NULL,
  `ownerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ownerhouserelation
-- ----------------------------

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `sellerID` int(20) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `degree` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `time` date DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `weichat` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `account` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`sellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES ('1', 'Hello', '111', '111', '111', '111', '2015-08-04', 'HouseService02/uploadFilePlace/201508192305280.jpg', '111', '1');

-- ----------------------------
-- Table structure for selleraccount
-- ----------------------------
DROP TABLE IF EXISTS `selleraccount`;
CREATE TABLE `selleraccount` (
  `password` varchar(30) COLLATE utf8_bin NOT NULL,
  `managerID` int(11) DEFAULT NULL,
  `account` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of selleraccount
-- ----------------------------
INSERT INTO `selleraccount` VALUES ('123', '1', '1');
INSERT INTO `selleraccount` VALUES ('3', '1', '');
INSERT INTO `selleraccount` VALUES ('1111', '1', '');
INSERT INTO `selleraccount` VALUES ('1', '3', '');
INSERT INTO `selleraccount` VALUES ('jjj', '1', '');
INSERT INTO `selleraccount` VALUES ('jjj', '1', '');
INSERT INTO `selleraccount` VALUES ('123', '1', '');
INSERT INTO `selleraccount` VALUES ('1', '1', '');

-- ----------------------------
-- Table structure for sellergoal
-- ----------------------------
DROP TABLE IF EXISTS `sellergoal`;
CREATE TABLE `sellergoal` (
  `sellerID` int(20) NOT NULL,
  `goalCount` int(10) DEFAULT NULL,
  `finishCount` int(10) DEFAULT NULL,
  `salary` int(10) DEFAULT NULL,
  `basicSalary` int(11) DEFAULT NULL,
  PRIMARY KEY (`sellerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sellergoal
-- ----------------------------
INSERT INTO `sellergoal` VALUES ('1', '1', '2', '22222', '111111');
INSERT INTO `sellergoal` VALUES ('13', '43', '32', '333333', '222222');
DROP TRIGGER IF EXISTS `watch_insert_manager`;
DELIMITER ;;
CREATE TRIGGER `watch_insert_manager` AFTER INSERT ON `manageraccount` FOR EACH ROW insert into manager(account,date)values(new.account,now())
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `watch_insert`;
DELIMITER ;;
CREATE TRIGGER `watch_insert` AFTER INSERT ON `selleraccount` FOR EACH ROW insert into seller (account,time) values(NEW.account,now())
;;
DELIMITER ;
