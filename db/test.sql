/*
SQLyog v10.2 
MySQL - 5.6.21-log : Database - macore
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `t_dbsql` */

DROP TABLE IF EXISTS `t_dbsql`;

CREATE TABLE `t_dbsql` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_sql_string` text,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_dbsql` */

insert  into `t_dbsql`(`c_id`,`c_sql_string`) values (3,'create table xxx'),(4,'create table yyy');

/*Table structure for table `t_device_attr` */

DROP TABLE IF EXISTS `t_device_attr`;

CREATE TABLE `t_device_attr` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_device_id` int(11) DEFAULT NULL,
  `c_attrtype_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `t_device_attr` */

insert  into `t_device_attr`(`c_id`,`c_device_id`,`c_attrtype_id`) values (7,4,1),(8,4,2),(9,4,3),(14,5,1),(17,6,3);

/*Table structure for table `t_device_attr_type` */

DROP TABLE IF EXISTS `t_device_attr_type`;

CREATE TABLE `t_device_attr_type` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_devicetype_id` int(11) DEFAULT NULL,
  `c_name` varchar(24) DEFAULT NULL,
  `c_field_name` varchar(24) DEFAULT NULL,
  `c_label` varchar(24) DEFAULT NULL,
  `c_data_type` varchar(8) DEFAULT NULL COMMENT 'int    float      ',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_device_attr_type` */

insert  into `t_device_attr_type`(`c_id`,`c_devicetype_id`,`c_name`,`c_field_name`,`c_label`,`c_data_type`) values (1,1,'currentA','c_currentA','电流A','float'),(2,1,'currentB','c_currentB','电流B','float'),(3,1,'mode','c_mode','工作模式','int');

/*Table structure for table `t_device_type` */

DROP TABLE IF EXISTS `t_device_type`;

CREATE TABLE `t_device_type` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_name` varchar(24) DEFAULT NULL,
  `c_table_name` varchar(24) DEFAULT NULL,
  `c_multi` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_device_type` */

insert  into `t_device_type`(`c_id`,`c_name`,`c_table_name`,`c_multi`) values (1,'pump','t_pump',1),(2,'box','t_box',0);

/*Table structure for table `t_group` */

DROP TABLE IF EXISTS `t_group`;

CREATE TABLE `t_group` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_object_id` int(11) DEFAULT NULL,
  `c_name` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_group` */

insert  into `t_group`(`c_id`,`c_object_id`,`c_name`) values (2,2,'group1'),(3,2,'group2'),(4,5,'11111');

/*Table structure for table `t_host` */

DROP TABLE IF EXISTS `t_host`;

CREATE TABLE `t_host` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_ip` varchar(24) DEFAULT NULL,
  `c_port` int(11) DEFAULT NULL,
  `c_account` varchar(24) DEFAULT NULL,
  `c_password` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_host` */

insert  into `t_host`(`c_id`,`c_ip`,`c_port`,`c_account`,`c_password`) values (1,'localhost',3306,'root','123456'),(3,'3',3306,'3','3');

/*Table structure for table `t_object` */

DROP TABLE IF EXISTS `t_object`;

CREATE TABLE `t_object` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_host_id` int(11) DEFAULT NULL,
  `c_type_id` int(11) DEFAULT NULL,
  `c_database_name` varchar(24) DEFAULT NULL,
  `c_name` varchar(24) DEFAULT NULL,
  `c_address` varchar(24) DEFAULT NULL,
  `c_linkman` varchar(24) DEFAULT NULL,
  `c_mobile` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_object` */

insert  into `t_object`(`c_id`,`c_host_id`,`c_type_id`,`c_database_name`,`c_name`,`c_address`,`c_linkman`,`c_mobile`) values (2,1,NULL,'testcreate2','test2u','xxxu','xxxu','123u'),(3,1,NULL,'pump1','水厂1','城市学院','徐峰','13456533456'),(5,1,NULL,'lp','1','1','1','1');

/*Table structure for table `t_object_device` */

DROP TABLE IF EXISTS `t_object_device`;

CREATE TABLE `t_object_device` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_group_id` int(11) DEFAULT NULL,
  `c_object_id` int(11) DEFAULT NULL,
  `c_devicetype_id` int(11) DEFAULT NULL,
  `c_code` varchar(24) DEFAULT NULL,
  `c_index` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_object_device` */

insert  into `t_object_device`(`c_id`,`c_group_id`,`c_object_id`,`c_devicetype_id`,`c_code`,`c_index`) values (4,2,2,1,'t_pump',2),(5,2,2,1,'t_pump',3),(6,2,2,1,'t_pump',4);

/*Table structure for table `t_terminal` */

DROP TABLE IF EXISTS `t_terminal`;

CREATE TABLE `t_terminal` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_object_id` int(11) DEFAULT NULL,
  `c_code` varchar(24) DEFAULT NULL,
  `c_name` varchar(24) DEFAULT NULL,
  `c_ip` varchar(24) DEFAULT NULL,
  `c_port` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_terminal` */

insert  into `t_terminal`(`c_id`,`c_object_id`,`c_code`,`c_name`,`c_ip`,`c_port`) values (1,2,'xxx','zzz','1.1.1.1',8080),(3,2,'33','33','33',3333);

/*Table structure for table `t_terminal_attr` */

DROP TABLE IF EXISTS `t_terminal_attr`;

CREATE TABLE `t_terminal_attr` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_terminal_id` int(11) DEFAULT NULL,
  `c_deviceattr_id` int(11) DEFAULT NULL,
  `c_plc_address` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_terminal_attr` */

insert  into `t_terminal_attr`(`c_id`,`c_terminal_id`,`c_deviceattr_id`,`c_plc_address`) values (3,1,7,'ccc'),(10,3,17,'111'),(11,3,14,'222');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_account` varchar(24) DEFAULT NULL,
  `c_password` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`c_id`,`c_account`,`c_password`) values (1,'root','123456'),(3,'meian','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
