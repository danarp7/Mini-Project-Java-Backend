/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.30-log : Database - db_bank
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_bank` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_bank`;

/*Table structure for table `history_tranx` */

DROP TABLE IF EXISTS `history_tranx`;

CREATE TABLE `history_tranx` (
  `date_tranx` date DEFAULT NULL,
  `time_tranx` time DEFAULT NULL,
  `rekening_number` varchar(20) DEFAULT NULL,
  `nominal` decimal(18,2) DEFAULT NULL,
  `type_tranx` varchar(7) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `history_tranx` */

insert  into `history_tranx`(`date_tranx`,`time_tranx`,`rekening_number`,`nominal`,`type_tranx`,`description`) values 
('2020-08-01','10:00:00','1111222233',20000000.00,'DB','TF From PT.IBM'),
('2020-08-04','17:22:40','1111222233',300000.00,'CR','Withdraw ATM'),
('2020-08-07','14:50:32','1111222233',400000.00,'CR','TF To Budi'),
('2020-08-09','09:20:12','1111222233',100000.00,'CR','Withdraw ATM'),
('2020-08-10','12:41:05','1111222233',100000.00,'CR','Withdraw ATM'),
('2020-08-12','10:50:45','1111222233',500000.00,'CR','Top Up Shopee Pay'),
('2020-08-16','11:20:09','1111222233',300000.00,'CR','Withdraw ATM'),
('2020-08-17','17:01:50','1111222233',1000000.00,'DB','TF From Lisa'),
('2020-08-19','09:57:06','1111222233',50000.00,'CR','Withdraw ATM'),
('2020-08-19','20:00:31','1111222233',150000.00,'CR','Withdraw ATM'),
('2020-08-19','19:45:02','1111222233',100000.00,'CR','Top Up Shopee Pay'),
('2020-08-21','08:37:45','1111222233',500000.00,'CR','Withdraw ATM'),
('2020-08-23','14:41:23','1111222233',1000000.00,'DB','TF From Deni'),
('2020-08-24','09:00:32','1111222233',300000.00,'CR','Withdraw ATM'),
('2020-08-28','08:50:41','1111222233',1500000.00,'DB','TF From Dimas'),
('2020-08-28','12:34:21','1111222233',500000.00,'CR','Top Up Shopee Pay'),
('2020-09-03','10:49:54','1111222233',300000.00,'CR','Withdraw ATM'),
('2020-09-06','17:56:02','1111222233',300000.00,'CR','Top Up OVO'),
('2020-09-08','09:23:56','1111222233',500000.00,'CR','Top Up Shopee Pay'),
('2020-09-12','11:20:47','1111222233',100000.00,'CR','Withdraw ATM'),
('2020-09-14','08:32:41','1111222233',100000.00,'CR','Withdraw ATM'),
('2020-09-01','08:32:41','1111222233',20000000.00,'DB','TF From PT.IBM'),
('2020-08-04','18:50:49','3322221111',15000000.00,'DB','TF From PT.G2'),
('2020-08-05','08:00:20','3322221111',500000.00,'CR','Withdraw ATM'),
('2020-08-07','15:55:02','3322221111',300000.00,'CR','TF To Ikhsan'),
('2020-08-11','10:45:20','3322221111',100000.00,'CR','Withdraw ATM'),
('2020-08-12','19:00:50','3322221111',500000.00,'DB','TF From Dwi'),
('2020-08-12','16:08:56','3322221111',300000.00,'CR','TF To Doni'),
('2020-08-15','08:54:00','3322221111',100000.00,'CR','TF To Ikhsan'),
('2020-08-20','19:44:32','3322221111',200000.00,'CR','Top Up OVO'),
('2020-08-20','18:32:41','3322221111',100000.00,'CR','TF To Suparjo'),
('2020-08-22','08:23:19','3322221111',1000000.00,'CR','Withdraw ATM'),
('2020-08-28','19:41:23','3322221111',10000000.00,'DB','TF From PT.CKL'),
('2020-08-30','11:21:20','3322221111',2000000.00,'CR','Withdraw ATM'),
('2020-09-05','09:20:14','3322221111',500000.00,'CR','TF To Reni'),
('2020-09-10','14:18:03','3322221111',200000.00,'CR','Withdraw ATM'),
('2020-09-13','12:20:41','3322221111',200000.00,'CR','Withdraw ATM'),
('2020-09-22','21:03:24','1111222233',50000.00,'CR','TF to Allianz 7777777777');

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
  `dc_number` varchar(16) DEFAULT NULL,
  `rekening_number` varchar(20) DEFAULT NULL,
  `pin_atm` varchar(6) DEFAULT NULL,
  `user_id` varchar(12) DEFAULT NULL,
  `m_pin` varchar(6) DEFAULT NULL,
  `pwd_tranx` varchar(12) DEFAULT NULL,
  `balance` decimal(18,2) DEFAULT NULL,
  `bank_status` varchar(20) DEFAULT NULL,
  `m_banking_status` varchar(20) DEFAULT NULL,
  `m_banking_activation` varchar(20) DEFAULT NULL,
  `m_session_active` tinyint(1) DEFAULT NULL,
  `verify_code` varchar(10) DEFAULT NULL,
  `date_verify_code` date DEFAULT NULL,
  `time_verify_code` time DEFAULT NULL,
  `otp` varchar(6) DEFAULT NULL,
  `date_otp` date DEFAULT NULL,
  `time_otp` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`full_name`,`nik`,`gender`,`birth_date`,`phone_number`,`email`,`dc_number`,`rekening_number`,`pin_atm`,`user_id`,`m_pin`,`pwd_tranx`,`balance`,`bank_status`,`m_banking_status`,`m_banking_activation`,`m_session_active`,`verify_code`,`date_verify_code`,`time_verify_code`,`otp`,`date_otp`,`time_otp`) values 
(1,'Dandelion','1050241708900001','Laki-laki','1990-08-17','082299991122','dandelion001@gmail.com','1234567890000000','1111222233','123456','dandelion7','007007','*Dandelion01',69950000.00,'Active','Registered','Active',0,'a9l7w7VmBL','2020-09-22','20:56:20','383492','2020-09-22','20:58:42'),
(2,'Mamat','1050240707920001','Laki-laki','1992-07-07','082277770000','mamat007@gmail.com','9876543210000000','3322221111','654321','mamat7','111111','*Mamat01',34000000.00,'Active','Registered','Active',0,NULL,NULL,NULL,NULL,NULL,NULL),
(3,'Mikha','1050244810940001','Perempuan','1994-10-18','081244441111','mikha001@gmail.com','1122334455000000','1122334455','111111','(NULL',NULL,NULL,20000000.00,'Block','Not Registered','Not Active',0,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
