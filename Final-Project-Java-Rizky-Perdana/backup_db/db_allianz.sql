/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.30-log : Database - db_allianz
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_allianz` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_allianz`;

/*Table structure for table `history_tranx` */

DROP TABLE IF EXISTS `history_tranx`;

CREATE TABLE `history_tranx` (
  `date_tranx` date DEFAULT NULL,
  `time_tranx` time DEFAULT NULL,
  `polis_number` varchar(20) DEFAULT NULL,
  `nominal` decimal(18,2) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `month` varchar(2) DEFAULT NULL,
  `year` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `history_tranx` */

insert  into `history_tranx`(`date_tranx`,`time_tranx`,`polis_number`,`nominal`,`grade`,`month`,`year`) values 
('2020-06-01','10:00:00','7777777777',50000.00,'Silver','06','2020'),
('2020-06-04','17:22:40','4444444444',25000.00,'Bronze','06','2020'),
('2020-06-07','14:50:32','1111111111',75000.00,'Gold','06','2020'),
('2020-07-09','09:20:12','7777777777',50000.00,'Silver','07','2020'),
('2020-07-10','12:41:05','4444444444',25000.00,'Bronze','07','2020'),
('2020-07-12','10:50:45','1111111111',75000.00,'Gold','07','2020'),
('2020-08-06','11:20:09','7777777777',50000.00,'Silver','08','2020'),
('2020-08-07','17:01:50','4444444444',25000.00,'Bronze','08','2020'),
('2020-08-09','09:57:06','1111111111',75000.00,'Gold','08','2020'),
('2020-09-22','21:03:24','7777777777',50000.00,'Silver',NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `nik` varchar(16) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `phone_number` varchar(16) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `polis_number` varchar(20) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`full_name`,`nik`,`gender`,`birth_date`,`phone_number`,`email`,`polis_number`,`grade`) values 
(1,'Dandelion','1050241708900001','Laki-laki','1990-08-17','082299991122','dandelion001@gmail.com','7777777777','Silver'),
(2,'Mamat','1050240707920001','Laki-laki','1992-07-07','082277770000','mamat007@gmail.com','4444444444','Bronze'),
(3,'Mikha','1050244810940001','Perempuan','1994-10-18','081244441111','mikha001@gmail.com','1111111111','Gold');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
