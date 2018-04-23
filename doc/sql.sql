/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.17-log : Database - lingshi_mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lingshi_mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lingshi_mall`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `addressId` varchar(32) NOT NULL COMMENT '主键',
  `addressContent` varchar(100) NOT NULL COMMENT '地址',
  `addressName` varchar(20) NOT NULL COMMENT '收货人姓名',
  `addressPhone` varchar(20) NOT NULL COMMENT '收货人联系方式',
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`addressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`addressId`,`addressContent`,`addressName`,`addressPhone`,`userId`) values ('A11515215058021','香港九龙岛蓬莱仙岛7号','蔡蔡','17605036258','A17217312372710'),('A74219472688710','冒险岛12138号','小菜','74123654895','A17217312372710');

/*Table structure for table `addresslist` */

DROP TABLE IF EXISTS `addresslist`;

CREATE TABLE `addresslist` (
  `addressListId` varchar(32) NOT NULL COMMENT '通讯录编号',
  `addressListName` varchar(20) NOT NULL DEFAULT '' COMMENT '通讯录名称',
  `addressListPhone` varchar(20) NOT NULL DEFAULT '' COMMENT '通讯录电话',
  `addressListTime` datetime NOT NULL DEFAULT '2014-01-01 00:00:00' COMMENT '通讯录创建时间',
  PRIMARY KEY (`addressListId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `addresslist` */

insert  into `addresslist`(`addressListId`,`addressListName`,`addressListPhone`,`addressListTime`) values ('A56211370089912','小小','17612145896','2014-01-01 00:00:00'),('A95511371387513','大大','17548523695','2014-01-01 00:00:00');

/*Table structure for table `banner` */

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner` (
  `bannerId` varchar(32) NOT NULL COMMENT '编号',
  `bannerImgUrl` varchar(200) NOT NULL DEFAULT '' COMMENT '轮播图Url',
  `bannerLink` varchar(200) NOT NULL DEFAULT '' COMMENT '轮播图链接地址',
  `bannerColorCode` varchar(20) NOT NULL DEFAULT '#fff' COMMENT '背景色代码',
  PRIMARY KEY (`bannerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `banner` */

insert  into `banner`(`bannerId`,`bannerImgUrl`,`bannerLink`,`bannerColorCode`) values ('B11813284202716','/Uploadfile/banner/LG37913283671015.png','https://www.baidu.com','#0f1024'),('B40913280740914','/Uploadfile/banner/LG82513275775313.png','https://www.baidu.com','#f44661'),('B73513272566512','/Uploadfile/banner/LG17413272456511.png','https://www.baidu.com','#55be59'),('B80913335181512','/Uploadfile/banner/LG91313260271710.png','https://www.baidu.com','#ff9801');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedbackId` varchar(32) NOT NULL COMMENT '编号',
  `feedbackContent` varchar(200) NOT NULL DEFAULT '' COMMENT '反馈内容',
  `feedbackTime` date NOT NULL DEFAULT '2014-01-01' COMMENT '反馈时间',
  `userId` varchar(32) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`feedbackId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `feedback` */

insert  into `feedback`(`feedbackId`,`feedbackContent`,`feedbackTime`,`userId`) values ('FB2138','我是测试内容','2014-01-01','R00001'),('LG27420060908112','啦啦啦','2018-04-23','A17217312372710'),('LG32820054810011','我随便试试1','2018-04-23','A17217312372710'),('LG79320044852610','我随便试试','2018-04-23','A17217312372710');

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goodsId` varchar(32) NOT NULL COMMENT '商品编号',
  `goodsName` varchar(40) NOT NULL DEFAULT '' COMMENT '商品名称',
  `goodsCost` float NOT NULL DEFAULT '0' COMMENT '商品成本',
  `goodsPrice` float NOT NULL DEFAULT '0' COMMENT '售价',
  `goodsCount` int(11) NOT NULL DEFAULT '0' COMMENT '商品数量',
  `goodsTime` datetime NOT NULL DEFAULT '2014-01-01 00:00:00' COMMENT '创建商品时间',
  `goodsPercentage` float NOT NULL DEFAULT '0' COMMENT '商品提成',
  `goodsKindId` varchar(32) NOT NULL COMMENT '商品种类编号',
  `goodsImgUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '预览图Url',
  `goodsContents` varchar(2000) NOT NULL DEFAULT '' COMMENT '商品介绍',
  PRIMARY KEY (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`goodsId`,`goodsName`,`goodsCost`,`goodsPrice`,`goodsCount`,`goodsTime`,`goodsPercentage`,`goodsKindId`,`goodsImgUrl`,`goodsContents`) values ('LG26617020283211','鸭牌洗衣粉',4,6,97,'2018-04-09 17:02:03',1,'LG16314185305513','/Uploadfile/goodsfirstimg/LG32917075321916.png','我是雕牌第二'),('LG54113260413514','真维斯被套',50,80,96,'2018-03-27 13:26:04',10,'LG72614183633411','','我是被套'),('LG61713251421311','雕牌洗衣粉',8,10,486,'2018-03-27 13:25:14',0.5,'LG16314185305513','','<p style=\"text-align: center;\"><b>我雕牌洗衣粉<img src=\"http://localhost:8091/WebContent/Contents/lib/layui/images/face/16.gif\" alt=\"[太开心]\"></b></p>'),('LG89317025760114','鸡牌洗衣粉',3,5,97,'2018-04-09 17:02:58',1,'LG16314185305513','','<p style=\"text-align: center;\"><b>我是鸡排洗衣粉</b></p><p style=\"text-align: left;\">啦啦啦&nbsp;</p><p style=\"text-align: left;\">此处省略2000字</p>');

/*Table structure for table `goodsimg` */

DROP TABLE IF EXISTS `goodsimg`;

CREATE TABLE `goodsimg` (
  `goodsImgId` varchar(32) NOT NULL COMMENT '编号',
  `goodsImgUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '商品图片url',
  `goodsId` varchar(32) NOT NULL DEFAULT '' COMMENT '商品id',
  PRIMARY KEY (`goodsImgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodsimg` */

insert  into `goodsimg`(`goodsImgId`,`goodsImgUrl`,`goodsId`) values ('LG11616181176010','/Uploadfile/goodsimg/LG51917024634213.png','LG89317025760114'),('LG35813260414615','/Uploadfile/goodsimg/LG39013254957513.png','LG54113260413514'),('LG60317075453417','/Uploadfile/goodsimg/LG56917015924410.png','LG26617020283211'),('LG63417053994814','/Uploadfile/goodsimg/LG34117053280312.png','LG61713251421311'),('LG92517053994813','/Uploadfile/goodsimg/LG63317052838811.png','LG61713251421311');

/*Table structure for table `goodskind` */

DROP TABLE IF EXISTS `goodskind`;

CREATE TABLE `goodskind` (
  `goodsKindId` varchar(32) NOT NULL COMMENT '编号',
  `goodsKindName` varchar(20) NOT NULL COMMENT '商品分类名称',
  `goodsKindTime` datetime NOT NULL DEFAULT '2014-01-01 00:00:00' COMMENT '创建时间',
  `goodsKindNote` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`goodsKindId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodskind` */

insert  into `goodskind`(`goodsKindId`,`goodsKindName`,`goodsKindTime`,`goodsKindNote`) values ('LG16314185305513','清洁','2014-01-01 00:00:00','我是清洁'),('LG72614183633411','床具','2014-01-01 00:00:00','我是床具'),('LG85714184497412','厨具','2014-01-01 00:00:00','我是厨具'),('LG87914190419914','衣服','2014-01-01 00:00:00','我是衣服');

/*Table structure for table `orderform` */

DROP TABLE IF EXISTS `orderform`;

CREATE TABLE `orderform` (
  `orderId` varchar(32) NOT NULL COMMENT '订单号',
  `orderTime` datetime NOT NULL DEFAULT '2014-01-01 00:00:00' COMMENT '订单时间',
  `orderPrice` float NOT NULL DEFAULT '0' COMMENT '订单金额',
  `userId` varchar(32) NOT NULL COMMENT '售出人员编号',
  `orderType` int(11) NOT NULL DEFAULT '1' COMMENT '订单类型：1记录单 2用户单',
  `orderStatus` int(11) NOT NULL DEFAULT '0' COMMENT '用户单状态：0未发货 1已发货 2退货',
  `orderDesc` varchar(200) NOT NULL DEFAULT '' COMMENT '订单备注',
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderform` */

insert  into `orderform`(`orderId`,`orderTime`,`orderPrice`,`userId`,`orderType`,`orderStatus`,`orderDesc`) values ('LG36916282149019','2018-04-23 16:28:21',5,'A17217312372710',2,0,'收件人：蔡蔡，联系方式：17605036258，收货地址：香港九龙岛蓬莱仙岛7号'),('LG52819324980211','2018-04-23 19:32:50',6,'A17217312372710',2,0,'收件人：小菜，联系方式：74123654895，收货地址：冒险岛12138号'),('LG54616271399610','2018-04-23 16:27:14',22,'A17217312372710',2,1,'收件人：蔡蔡，联系方式：17605036258，收货地址：香港九龙岛蓬莱仙岛7号'),('LG61914303108112','2018-03-28 14:30:31',80,'A96311013517616',1,1,'记录单'),('LG69414135298510','2018-04-08 14:13:53',80,'A96311013517616',2,1,'');

/*Table structure for table `ordergoods` */

DROP TABLE IF EXISTS `ordergoods`;

CREATE TABLE `ordergoods` (
  `orderGoodsId` varchar(32) NOT NULL COMMENT '订单物品编号',
  `goodsId` varchar(32) NOT NULL COMMENT '物品编号',
  `orderGoodsName` varchar(20) NOT NULL COMMENT '售出时名称',
  `orderGoodsCost` float NOT NULL DEFAULT '0' COMMENT '售出时成本',
  `orderGoodsPrice` float NOT NULL DEFAULT '0' COMMENT '售出时售价',
  `orderGoodsCount` int(11) NOT NULL DEFAULT '0' COMMENT '售出数量',
  `orderGoodsPercentage` float NOT NULL DEFAULT '0' COMMENT '售出时提成',
  `orderId` varchar(32) NOT NULL COMMENT '订单编号',
  `orderGoodsImgUrl` varchar(100) NOT NULL COMMENT '订单物品预览图',
  PRIMARY KEY (`orderGoodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ordergoods` */

insert  into `ordergoods`(`orderGoodsId`,`goodsId`,`orderGoodsName`,`orderGoodsCost`,`orderGoodsPrice`,`orderGoodsCount`,`orderGoodsPercentage`,`orderId`,`orderGoodsImgUrl`) values ('LG19419324980313','LG26617020283211','鸭牌洗衣粉',4,6,1,1,'LG52819324980211','/Uploadfile/goodsfirstimg/LG32917075321916.png'),('LG32814135299311','LG54113260413514','真维斯被套',50,80,1,10,'LG69414135298510',''),('LG58216282149021','LG89317025760114','鸡牌洗衣粉',3,5,1,1,'LG36916282149019',''),('LG68514303108613','LG54113260413514','真维斯被套',50,80,1,10,'LG61914303108112',''),('LG77016271401914','LG26617020283211','鸭牌洗衣粉',4,6,2,1,'LG54616271399610',''),('LG87116271399712','LG89317025760114','鸡牌洗衣粉',3,5,2,1,'LG54616271399610','');

/*Table structure for table `shopcart` */

DROP TABLE IF EXISTS `shopcart`;

CREATE TABLE `shopcart` (
  `shopcartId` varchar(32) NOT NULL COMMENT '编号',
  `userId` varchar(32) NOT NULL COMMENT '用户编号',
  `goodsId` varchar(32) NOT NULL COMMENT '物品编号',
  `shopcartCount` int(11) NOT NULL DEFAULT '1' COMMENT '数量',
  PRIMARY KEY (`shopcartId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shopcart` */

/*Table structure for table `syslog` */

DROP TABLE IF EXISTS `syslog`;

CREATE TABLE `syslog` (
  `sysLogId` varchar(32) NOT NULL COMMENT '日志编号',
  `sysLogContent` varchar(200) NOT NULL DEFAULT '' COMMENT '日志内容',
  `sysLogTime` datetime NOT NULL DEFAULT '2014-01-01 00:00:00' COMMENT '日志时间',
  `userId` varchar(32) NOT NULL COMMENT '操作人编号',
  PRIMARY KEY (`sysLogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `syslog` */

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `userId` varchar(32) NOT NULL COMMENT '用户编号',
  `userName` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `realName` varchar(20) NOT NULL DEFAULT '' COMMENT '真实姓名',
  `createTime` datetime NOT NULL DEFAULT '2014-01-01 00:00:00' COMMENT '创建时间',
  `wages` float NOT NULL DEFAULT '0' COMMENT '基本工资',
  `userType` int(11) NOT NULL DEFAULT '1' COMMENT '用户类型（-1管理员 1分销员 2采购员）',
  `userHeadImgUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '用户头像url',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`userId`,`userName`,`password`,`realName`,`createTime`,`wages`,`userType`,`userHeadImgUrl`) values ('A17217312372710','17605036258','202CB962AC59075B964B07152D234B70','蔡蔡','2018-04-08 17:31:24',0,3,'/Uploadfile/userheads/LG99114524150916.png'),('A47611123321712','zhoujielun','202CB962AC59075B964B07152D234B70','周杰伦','2014-01-01 00:00:00',3000,2,''),('A76511001709613','xiaoxiao','202CB962AC59075B964B07152D234B70','小小','2014-01-01 00:00:00',3000,1,''),('A84511110453511','lingjunjie','202CB962AC59075B964B07152D234B70','林俊杰','2014-01-01 00:00:00',3000,2,''),('A96311013517616','dada','202CB962AC59075B964B07152D234B70','大大','2014-01-01 00:00:00',3000,1,''),('R00001','root','C4CA4238A0B923820DCC509A6F75849B','我是超管','2014-01-01 00:00:00',0,-1,'');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
