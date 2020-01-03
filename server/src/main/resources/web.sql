/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.28-log : Database - web
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`web` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `web`;

/*Table structure for table `e_role` */

DROP TABLE IF EXISTS `e_role`;

CREATE TABLE `e_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`eid`),
  KEY `FK_Reference_6` (`rid`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `e_role` */

insert  into `e_role`(`id`,`eid`,`rid`) values (1,1,1),(2,2,2),(3,3,2),(4,4,2),(5,5,2),(6,6,2),(7,7,2),(8,8,2),(9,9,2),(10,10,2),(11,11,2),(12,12,2),(13,13,2),(14,14,2),(15,15,2),(16,16,2);

/*Table structure for table `enterprise` */

DROP TABLE IF EXISTS `enterprise`;

CREATE TABLE `enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_name` varchar(20) NOT NULL DEFAULT '',
  `credit_code` varchar(20) NOT NULL DEFAULT '',
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `representative` varchar(20) NOT NULL DEFAULT '',
  `phone` varchar(11) NOT NULL DEFAULT '',
  `nature` varchar(20) NOT NULL DEFAULT '' COMMENT '所有者性质',
  `position` varchar(20) DEFAULT '' COMMENT '基本地位',
  `address` varchar(255) DEFAULT '',
  `scope` varchar(255) DEFAULT '' COMMENT '经营范围',
  `pl_situation` varchar(255) DEFAULT '' COMMENT '生产线情况',
  `routing` varchar(255) DEFAULT '' COMMENT '主要工艺路线',
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `enterprise` */

insert  into `enterprise`(`id`,`enterprise_name`,`credit_code`,`username`,`password`,`representative`,`phone`,`nature`,`position`,`address`,`scope`,`pl_situation`,`routing`,`enabled`) values (1,'系统管理员','','admin','admin','系统管理员','13112345678','','','','','','',1),(2,'测试企业名称','91440300279692779A','123','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(3,'测试企业名称1','91440300279692779A','124','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(4,'测试企业名称2','91440300279692779A','125','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(5,'测试企业名称3','91440300279692779A','126','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(6,'测试企业的名称1','91440300279692779A','127','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(7,'测试企业的名称2','91440300279692779A','128','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(8,'测试企业的名称3','91440300279692779A','129','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(9,'测试企业的名称4','91440300279692779A','130','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(10,'测试企业的名称5','91440300279692779A','131','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(11,'测试企业的名称6','91440300279692779A','132','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(12,'测试企业的名称7','91440300279692779A','133','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(13,'测试企业的名称8','91440300279692779A','134','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(14,'测试企业的名称9','91440300279692779A','135','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(15,'测试企业名称4','91440300279692779A','136','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1),(16,'测试企业名称5','91440300279692779A','137','123','李明','13112345679','私营企业','市场领导者','广西壮族自治区南宁市西乡塘区测试路100号','计算机软、硬件的技术研发与销售、技术咨询与服务；数据库管理与服务','现有生产线情况','主要工艺路线',1);

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`parentId`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`id`,`url`,`path`,`component`,`name`,`iconCls`,`keepAlive`,`requireAuth`,`parentId`,`enabled`) values (1,'/',NULL,NULL,'所有',NULL,NULL,NULL,NULL,1),(2,'/','/home','Home','企业信息','',NULL,1,1,1),(3,'/','/home','Home','项目信息','',NULL,1,1,1),(4,'/','/home','Home','问题反馈','',NULL,1,1,1),(5,'/','/home','Home','运行情况','',NULL,1,1,1),(6,'/','/home','Home','系统管理','',NULL,1,1,1),(7,'/enterprise/basic/**','/ent/basic','EntBasic','基本资料',NULL,NULL,1,2,1),(8,'/enterprise/all/**','/ent/all','EntAll','企业管理',NULL,NULL,1,2,1),(9,'/project/entpm/**','/proj/entpm','ProjEnt','项目管理',NULL,NULL,1,3,1),(10,'/project/all/**','/proj/all','ProjAll','项目统计',NULL,NULL,1,3,1),(11,'/project/monreport/**','/proj/mrepo','ProjMrepo','项目月报',NULL,NULL,1,3,1),(12,'/problem/submit/**','/prob/sub','ProbSub','问题建议',NULL,NULL,1,4,1),(13,'/problem/feedback/**','/prob/fb','ProbFb','反馈问题',NULL,NULL,1,4,1),(14,'/operation/statistics/**','/op/sta','OpSta','运行情况统计',NULL,NULL,1,5,1),(15,'/system/cfg/**','/sys/cfg','SysCfg','系统管理',NULL,NULL,1,6,1);

/*Table structure for table `menu_role` */

DROP TABLE IF EXISTS `menu_role`;

CREATE TABLE `menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`mid`),
  KEY `FK_Reference_9` (`rid`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`mid`) REFERENCES `menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `menu_role` */

insert  into `menu_role`(`id`,`mid`,`rid`) values (1,8,1),(2,10,1),(3,13,1),(4,15,1),(14,7,2),(15,9,2),(16,11,2),(17,12,2),(18,14,2);

/*Table structure for table `operation` */

DROP TABLE IF EXISTS `operation`;

CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT '0',
  `product_output` int(11) DEFAULT '0',
  `output_value` int(11) DEFAULT '0',
  `tax` int(11) DEFAULT '0',
  `energy_used` int(11) DEFAULT '0',
  `report_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`enterprise_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `operation` */

/*Table structure for table `problem` */

DROP TABLE IF EXISTS `problem`;

CREATE TABLE `problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `enterprise_id` int(11) DEFAULT '0',
  `problem_type` varchar(20) DEFAULT '',
  `problem` varchar(255) DEFAULT '',
  `suggestion` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_4` (`enterprise_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`enterprise_id`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `problem` */

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) DEFAULT '0',
  `project_name` varchar(20) NOT NULL DEFAULT '',
  `p_code` varchar(20) NOT NULL DEFAULT '',
  `principal` varchar(20) NOT NULL DEFAULT '' COMMENT '责任人',
  `scale` int(11) DEFAULT '0' COMMENT '投资规模（万）',
  `industry` varchar(20) DEFAULT '' COMMENT '所属行业',
  `location` varchar(20) DEFAULT '' COMMENT '所在市州',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`eid`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`eid`) REFERENCES `enterprise` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`id`,`eid`,`project_name`,`p_code`,`principal`,`scale`,`industry`,`location`,`start_date`,`end_date`) values (1,2,'测试项目名称1','12345678910','李明',1000,'制造业','福建省福州市','2019-04-01','2020-01-01'),(2,2,'测试项目名称2','10987654321','李华',2000,'农、林、渔、牧业','广西壮族自治区南宁市','2019-05-01','2020-02-01'),(3,2,'测试项目名称2','18746186487','李强',3000,'采矿业','山西省西安市','2019-03-01','2020-03-19');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `nameZh` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`nameZh`) values (1,'ROLE_admin','系统管理员'),(2,'ROLE_e1','企业用户');

/*Table structure for table `stage` */

DROP TABLE IF EXISTS `stage`;

CREATE TABLE `stage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT '0',
  `project_stage` varchar(20) DEFAULT '',
  `project_progress` varchar(255) DEFAULT '',
  `report_date` date DEFAULT NULL,
  `project_pro` varchar(255) DEFAULT '',
  `project_protype` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_2` (`project_id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stage` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
