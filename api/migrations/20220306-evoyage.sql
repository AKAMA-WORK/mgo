-- Adminer 4.8.1 MySQL 8.0.28 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

USE `evoyage`;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `agency`;
CREATE TABLE `agency` (
  `idagency` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idagency`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `agency` (`idagency`, `name`) VALUES
(1,	'E-VOYAGE');

DROP TABLE IF EXISTS `agencyemployee`;
CREATE TABLE `agencyemployee` (
  `idemployee` int NOT NULL AUTO_INCREMENT,
  `agency` int DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `matricule` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` int DEFAULT NULL,
  `cin` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idemployee`),
  KEY `fk_role_idx` (`role`),
  KEY `fk_agency_idx` (`agency`),
  CONSTRAINT `fk_agencyantenna` FOREIGN KEY (`agency`) REFERENCES `agency` (`idagency`),
  CONSTRAINT `fk_role` FOREIGN KEY (`role`) REFERENCES `employeerole` (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `agencyemployee` (`idemployee`, `agency`, `username`, `password`, `matricule`, `name`, `role`, `cin`, `contact`) VALUES
(1,	1,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL);

DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `idbooking` int NOT NULL AUTO_INCREMENT,
  `departure` int DEFAULT NULL,
  `client` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `datepointage` datetime DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `paymentmethod` int DEFAULT NULL,
  `paymentid` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bookingstatus` int DEFAULT NULL,
  `confirmdate` datetime DEFAULT NULL,
  `canceldate` datetime DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `authorizedluggage` decimal(10,0) DEFAULT NULL,
  `transferedfrom` int DEFAULT NULL COMMENT 'If booking is from transfer from another company',
  `boarding` tinyint(1) DEFAULT '0',
  `boarding_date` datetime DEFAULT NULL,
  PRIMARY KEY (`idbooking`),
  KEY `fk_departure_booking_idx` (`departure`),
  KEY `fk_client_booking_idx` (`client`),
  KEY `fk_client_payment_idx` (`paymentmethod`),
  KEY `fk_booking_bookingstatus_idx` (`bookingstatus`),
  KEY `fk_company_from_idx` (`transferedfrom`),
  CONSTRAINT `fk_booking_bookingstatus` FOREIGN KEY (`bookingstatus`) REFERENCES `bookingstatus` (`idbookingstatus`),
  CONSTRAINT `fk_client_booking` FOREIGN KEY (`client`) REFERENCES `client` (`idclient`),
  CONSTRAINT `fk_client_payment` FOREIGN KEY (`paymentmethod`) REFERENCES `paymentmethod` (`idpaymentmethod`),
  CONSTRAINT `fk_company_from` FOREIGN KEY (`transferedfrom`) REFERENCES `company` (`idcompany`),
  CONSTRAINT `fk_departure_booking` FOREIGN KEY (`departure`) REFERENCES `departure` (`iddeparture`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `booking` (`idbooking`, `departure`, `client`, `date`, `datepointage`, `amount`, `paymentmethod`, `paymentid`, `bookingstatus`, `confirmdate`, `canceldate`, `description`, `authorizedluggage`, `transferedfrom`, `boarding`, `boarding_date`) VALUES
(16,	53,	2,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	0,	NULL),
(17,	77,	4,	'2021-11-23 18:47:54',	NULL,	70000,	2,	'TTT',	2,	'2021-11-23 18:47:54',	NULL,	NULL,	NULL,	NULL,	0,	NULL),
(18,	77,	5,	'2021-11-23 18:51:51',	NULL,	35000,	1,	'',	2,	'2021-11-23 18:51:51',	NULL,	NULL,	NULL,	NULL,	0,	NULL),
(19,	77,	4,	'2021-11-23 18:54:09',	NULL,	35000,	5,	'RMSDKDKD',	2,	'2021-11-23 18:54:09',	NULL,	NULL,	NULL,	NULL,	0,	NULL),
(20,	95,	4,	'2021-11-23 21:46:28',	NULL,	90000,	1,	'',	2,	'2021-11-23 21:46:28',	NULL,	NULL,	NULL,	NULL,	0,	NULL),
(21,	95,	4,	'2021-11-23 21:55:17',	NULL,	90000,	1,	'',	2,	'2021-11-23 21:55:17',	NULL,	NULL,	NULL,	NULL,	0,	NULL),
(22,	89,	5,	'2021-11-23 22:46:49',	NULL,	70000,	1,	'',	2,	'2021-11-23 22:46:49',	NULL,	NULL,	NULL,	NULL,	0,	NULL),
(23,	96,	6,	'2022-03-02 21:10:17',	NULL,	20000,	1,	'RF001',	2,	'2022-03-02 21:10:17',	NULL,	NULL,	NULL,	NULL,	1,	'2022-03-03 06:17:43'),
(24,	96,	7,	'2022-03-03 07:41:37',	NULL,	40000,	1,	'',	2,	'2022-03-03 07:41:37',	NULL,	NULL,	NULL,	NULL,	0,	NULL),
(25,	420,	7,	'2022-03-05 17:51:36',	NULL,	40000,	1,	'',	2,	'2022-03-05 17:51:36',	NULL,	NULL,	NULL,	NULL,	0,	NULL);

DROP TABLE IF EXISTS `bookingcancel`;
CREATE TABLE `bookingcancel` (
  `idbookingcancel` int NOT NULL AUTO_INCREMENT,
  `booking` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `amountreimbursed` decimal(10,0) DEFAULT NULL,
  `paymentmethod` int DEFAULT NULL,
  `paymentid` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idbookingcancel`),
  KEY `fk_cancel_booking_idx` (`booking`),
  KEY `fk_paymentethod_idx` (`paymentmethod`),
  CONSTRAINT `fk_cancel_booking` FOREIGN KEY (`booking`) REFERENCES `booking` (`idbooking`),
  CONSTRAINT `fk_paymentethod` FOREIGN KEY (`paymentmethod`) REFERENCES `paymentmethod` (`idpaymentmethod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DROP TABLE IF EXISTS `bookingextraluggage`;
CREATE TABLE `bookingextraluggage` (
  `idbookingextraluggage` int NOT NULL AUTO_INCREMENT,
  `booking` int DEFAULT NULL,
  `weight` decimal(10,0) DEFAULT NULL,
  `amount` decimal(10,0) DEFAULT NULL,
  `paymentmethod` int DEFAULT NULL,
  `paymentid` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idbookingextraluggage`),
  KEY `fk_extra_booking_idx` (`booking`),
  KEY `fk_extra_paymentmethod_idx` (`paymentmethod`),
  CONSTRAINT `fk_extra_booking` FOREIGN KEY (`booking`) REFERENCES `booking` (`idbooking`),
  CONSTRAINT `fk_extra_paymentmethod` FOREIGN KEY (`paymentmethod`) REFERENCES `paymentmethod` (`idpaymentmethod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `bookingextraluggage` (`idbookingextraluggage`, `booking`, `weight`, `amount`, `paymentmethod`, `paymentid`) VALUES
(1,	23,	5,	1000,	NULL,	'120');

DROP TABLE IF EXISTS `bookingluggage`;
CREATE TABLE `bookingluggage` (
  `idbookingluggage` int NOT NULL AUTO_INCREMENT,
  `booking` int DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `weight` decimal(10,0) DEFAULT NULL,
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`idbookingluggage`),
  KEY `fk_booking_luggage_idx` (`booking`),
  CONSTRAINT `fk_luggage_booking` FOREIGN KEY (`booking`) REFERENCES `booking` (`idbooking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `bookingluggage` (`idbookingluggage`, `booking`, `date`, `weight`, `description`) VALUES
(1,	NULL,	NULL,	NULL,	NULL),
(2,	23,	'2022-03-03 06:08:30',	30,	'Description');

DROP TABLE IF EXISTS `bookingplace`;
CREATE TABLE `bookingplace` (
  `idbookingplace` int NOT NULL AUTO_INCREMENT,
  `booking` int DEFAULT NULL,
  `place` int DEFAULT NULL,
  PRIMARY KEY (`idbookingplace`),
  UNIQUE KEY `uq_booking_place` (`booking`,`place`),
  KEY `fk_booking_place_idx` (`booking`),
  CONSTRAINT `fk_place_booking` FOREIGN KEY (`booking`) REFERENCES `booking` (`idbooking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `bookingplace` (`idbookingplace`, `booking`, `place`) VALUES
(9,	16,	1),
(10,	16,	5),
(11,	17,	1),
(12,	17,	4),
(13,	18,	6),
(14,	19,	9),
(16,	20,	1),
(15,	20,	2),
(17,	21,	8),
(18,	21,	11),
(19,	22,	4),
(20,	22,	5),
(21,	23,	2),
(22,	24,	4),
(23,	24,	8),
(24,	25,	1),
(25,	25,	2);

DROP TABLE IF EXISTS `bookingstatus`;
CREATE TABLE `bookingstatus` (
  `idbookingstatus` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idbookingstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `bookingstatus` (`idbookingstatus`, `name`) VALUES
(1,	'PENDING'),
(2,	'CONFIRMED'),
(3,	'CANCELED'),
(4,	'TRANSFERED');

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `idcategory` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idcategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `category` (`idcategory`, `name`) VALUES
(1,	'LITE'),
(2,	'PREMIUM'),
(3,	'VIP');

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `idcity` int NOT NULL AUTO_INCREMENT,
  `trigram` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `longitude` double DEFAULT NULL COMMENT 'GPS information, long/lat',
  `latitude` double DEFAULT NULL,
  PRIMARY KEY (`idcity`),
  UNIQUE KEY `trigram_UNIQUE` (`trigram`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `city` (`idcity`, `trigram`, `name`, `longitude`, `latitude`) VALUES
(101,	'TNR',	'ANTANANARIVO',	NULL,	NULL),
(102,	'ABE',	'ANTSIRABE',	NULL,	NULL),
(201,	'DIE',	'ANTSIRANANA (DIEGO SUAREZ)',	NULL,	NULL),
(301,	'FNR',	'FIANARANTSOA',	NULL,	NULL),
(401,	'MJN',	'MAHAJANGA',	NULL,	NULL),
(501,	'TOA',	'TOAMASINA',	NULL,	NULL),
(601,	'TOL',	'TOLIARA',	NULL,	NULL);

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `idclient` int NOT NULL AUTO_INCREMENT,
  `subscribedate` datetime DEFAULT NULL,
  `civility` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'M.,Mme,Mlle',
  `fname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `idtype` int DEFAULT NULL,
  `idnumber` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idissuedate` date DEFAULT NULL,
  `idissuelocation` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idduplicatadate` date DEFAULT NULL,
  `idduplicatalocation` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contactablebymail` tinyint DEFAULT '1',
  `contactablebyphone` tinyint DEFAULT '1',
  `contactablebysms` tinyint DEFAULT '1',
  PRIMARY KEY (`idclient`),
  UNIQUE KEY `uk_phone` (`phone`),
  KEY `fk_idtype_client_idx` (`idtype`),
  CONSTRAINT `fk_idtype_client` FOREIGN KEY (`idtype`) REFERENCES `idtype` (`ididtype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `client` (`idclient`, `subscribedate`, `civility`, `fname`, `lname`, `phone`, `idtype`, `idnumber`, `idissuedate`, `idissuelocation`, `idduplicatadate`, `idduplicatalocation`, `username`, `email`, `password`, `contactablebymail`, `contactablebyphone`, `contactablebysms`) VALUES
(2,	'2021-09-05 03:00:00',	'Mme',	'string',	'string',	1,	1,	'string',	'2021-09-05',	'string',	'2021-09-05',	'string',	'string',	'string',	'string',	0,	0,	0),
(3,	NULL,	'Mme',	'string',	'string',	2,	1,	'string',	'2021-09-05',	'string',	'2021-09-05',	'string',	'string',	'string',	'string',	0,	0,	0),
(4,	NULL,	'Mr',	'FETYNEW',	NULL,	320707077,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
(5,	NULL,	'Mr',	'FARANIARIJAONA',	NULL,	348127558,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
(6,	NULL,	'Mr',	'Fety Faraniarijaona',	NULL,	320770777,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
(7,	NULL,	'Mr',	'ANTSANIRINA Ginot',	NULL,	346718212,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL);

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `idcompany` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idcompany`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `company` (`idcompany`, `name`) VALUES
(1,	'COTISSE TRANSPORT');

DROP TABLE IF EXISTS `companyagency`;
CREATE TABLE `companyagency` (
  `idcompanyagency` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `idcity` int NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `idcompany` int NOT NULL,
  PRIMARY KEY (`idcompanyagency`),
  KEY `idcity` (`idcity`),
  KEY `idcompany` (`idcompany`),
  CONSTRAINT `companyagency_ibfk_1` FOREIGN KEY (`idcity`) REFERENCES `city` (`idcity`),
  CONSTRAINT `companyagency_ibfk_2` FOREIGN KEY (`idcompany`) REFERENCES `company` (`idcompany`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `companyagency` (`idcompanyagency`, `name`, `address`, `idcity`, `phone`, `idcompany`) VALUES
(1,	'COTISSE TRANSPORT',	'Lot IVM 12 Ter Ambodivona',	101,	'032 11 027 10 / 33',	1);

DROP TABLE IF EXISTS `companyconfig`;
CREATE TABLE `companyconfig` (
  `tiketlogourl` varchar(252) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idcompanyconfig` int NOT NULL,
  `company` int DEFAULT NULL,
  `authorizedluggage` decimal(10,0) DEFAULT NULL,
  `priceextraluggage` decimal(10,0) DEFAULT NULL,
  `bookingcancelpenality` int DEFAULT NULL,
  `transfercommission` int DEFAULT NULL COMMENT 'commission for company transferer if they trasfer a booking to my company ',
  PRIMARY KEY (`idcompanyconfig`),
  KEY `fk_company_config_idx` (`company`),
  CONSTRAINT `fk_company_config` FOREIGN KEY (`company`) REFERENCES `company` (`idcompany`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `companyconfig` (`tiketlogourl`, `idcompanyconfig`, `company`, `authorizedluggage`, `priceextraluggage`, `bookingcancelpenality`, `transfercommission`) VALUES
('https://cotisse-transport.com/assets/images/logo/logo-cotisse.png',	1,	1,	25,	200,	10,	10);

DROP TABLE IF EXISTS `companyemployee`;
CREATE TABLE `companyemployee` (
  `idcompanyagency` int DEFAULT NULL,
  `idcompanyemployee` int NOT NULL AUTO_INCREMENT,
  `company` int DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `matricule` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` int DEFAULT NULL,
  `cin` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idcompanyemployee`),
  UNIQUE KEY `unique_username` (`username`),
  KEY `fk_role_idxidcompanyemployee` (`role`),
  KEY `fk_companyemployee_company_idx` (`company`),
  KEY `idcompanyagency` (`idcompanyagency`),
  CONSTRAINT `companyemployee_ibfk_1` FOREIGN KEY (`idcompanyagency`) REFERENCES `companyagency` (`idcompanyagency`),
  CONSTRAINT `fk_companyemployee_company` FOREIGN KEY (`company`) REFERENCES `company` (`idcompany`),
  CONSTRAINT `fk_roleidcompanyemployee` FOREIGN KEY (`role`) REFERENCES `employeerole` (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `companyemployee` (`idcompanyagency`, `idcompanyemployee`, `company`, `username`, `password`, `matricule`, `name`, `role`, `cin`, `contact`) VALUES
(1,	1,	1,	'fety',	'string',	'MAT',	'FETY',	3,	'CIN',	'020 75 852 36'),
(NULL,	2,	1,	'faraniarijaona',	'a',	'20210730001',	'Fety Faraniarijaona',	1,	'201031037775',	'0320707077'),
(NULL,	3,	1,	NULL,	NULL,	NULL,	'RAKOTONDRABE Rivo',	6,	NULL,	NULL),
(NULL,	4,	1,	NULL,	NULL,	NULL,	'RANDRIANASOLO Jean',	6,	NULL,	NULL);

DROP TABLE IF EXISTS `departure`;
CREATE TABLE `departure` (
  `idcompanyagency` int DEFAULT NULL,
  `iddeparture` int NOT NULL AUTO_INCREMENT,
  `company` int DEFAULT NULL,
  `startin` int DEFAULT NULL,
  `destination` int DEFAULT NULL,
  `dateheure` datetime DEFAULT NULL,
  `vehicle` int DEFAULT NULL,
  `carmatricule` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `driver` int DEFAULT NULL,
  `driver2` int DEFAULT NULL,
  `departurestatus` int DEFAULT NULL,
  PRIMARY KEY (`iddeparture`),
  KEY `fk_city_starting_idx` (`startin`),
  KEY `fk_city_destination_idx` (`destination`),
  KEY `fk_vehicle_departure_idx` (`vehicle`),
  KEY `fk_departurestatus_idx` (`departurestatus`),
  KEY `fk_company_idx` (`company`),
  KEY `fk_category_depatrture_idx` (`category`),
  KEY `fk_dirver_idx` (`driver`),
  KEY `fk_driver2_departure_idx` (`driver2`),
  KEY `idcompanyagency` (`idcompanyagency`),
  CONSTRAINT `departure_ibfk_1` FOREIGN KEY (`driver`) REFERENCES `companyemployee` (`idcompanyemployee`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `departure_ibfk_2` FOREIGN KEY (`driver2`) REFERENCES `companyemployee` (`idcompanyemployee`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `departure_ibfk_3` FOREIGN KEY (`idcompanyagency`) REFERENCES `companyagency` (`idcompanyagency`),
  CONSTRAINT `fk_category_depatrture` FOREIGN KEY (`category`) REFERENCES `category` (`idcategory`),
  CONSTRAINT `fk_city_destination` FOREIGN KEY (`destination`) REFERENCES `city` (`idcity`),
  CONSTRAINT `fk_city_starting` FOREIGN KEY (`startin`) REFERENCES `city` (`idcity`),
  CONSTRAINT `fk_company_departure` FOREIGN KEY (`company`) REFERENCES `company` (`idcompany`),
  CONSTRAINT `fk_departurestatus` FOREIGN KEY (`departurestatus`) REFERENCES `departurestatus` (`iddeparturestatus`),
  CONSTRAINT `fk_vehicle_departure` FOREIGN KEY (`vehicle`) REFERENCES `vehicle` (`idvehicle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `departure` (`idcompanyagency`, `iddeparture`, `company`, `startin`, `destination`, `dateheure`, `vehicle`, `carmatricule`, `category`, `price`, `driver`, `driver2`, `departurestatus`) VALUES
(NULL,	53,	1,	101,	102,	'2021-10-08 06:00:00',	3,	NULL,	1,	10000,	NULL,	NULL,	1),
(NULL,	54,	1,	101,	102,	'2021-10-11 08:00:00',	2,	NULL,	2,	25000,	NULL,	NULL,	2),
(NULL,	55,	1,	101,	102,	'2021-10-11 09:00:00',	2,	NULL,	2,	25000,	NULL,	NULL,	1),
(NULL,	56,	1,	101,	102,	'2021-10-12 08:00:00',	2,	NULL,	2,	25000,	NULL,	NULL,	1),
(NULL,	57,	1,	101,	102,	'2021-10-12 09:00:00',	2,	NULL,	2,	25000,	NULL,	NULL,	1),
(NULL,	58,	1,	101,	301,	'2021-10-13 09:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	59,	1,	101,	501,	'2021-10-12 07:00:00',	2,	NULL,	2,	25000,	NULL,	NULL,	1),
(NULL,	60,	1,	101,	501,	'2021-10-12 07:00:00',	2,	NULL,	2,	25000,	NULL,	NULL,	1),
(NULL,	61,	1,	101,	301,	'2021-11-03 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	62,	1,	101,	301,	'2021-11-03 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	63,	1,	101,	301,	'2021-11-04 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	64,	1,	101,	301,	'2021-11-04 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	65,	1,	101,	301,	'2021-11-05 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	66,	1,	101,	301,	'2021-11-05 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	67,	1,	101,	301,	'2021-11-06 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	68,	1,	101,	301,	'2021-11-06 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	69,	1,	101,	301,	'2021-11-07 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	70,	1,	101,	301,	'2021-11-07 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	71,	1,	101,	301,	'2021-11-08 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	72,	1,	101,	301,	'2021-11-08 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	73,	1,	101,	301,	'2021-11-09 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	74,	1,	101,	301,	'2021-11-09 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	75,	1,	101,	301,	'2021-11-10 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	76,	1,	101,	301,	'2021-11-10 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	77,	1,	101,	301,	'2021-11-11 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	78,	1,	101,	301,	'2021-11-11 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	79,	1,	101,	301,	'2021-11-12 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	80,	1,	101,	301,	'2021-11-12 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	81,	1,	101,	301,	'2021-11-13 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	82,	1,	101,	301,	'2021-11-13 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	83,	1,	101,	301,	'2021-11-14 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	84,	1,	101,	301,	'2021-11-14 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	85,	1,	101,	301,	'2021-11-15 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	86,	1,	101,	301,	'2021-11-15 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	87,	1,	101,	301,	'2021-11-16 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	88,	1,	101,	301,	'2021-11-16 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	89,	1,	101,	301,	'2021-11-17 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	90,	1,	101,	301,	'2021-11-17 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	91,	1,	101,	301,	'2021-11-18 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	92,	1,	101,	301,	'2021-11-18 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	93,	1,	101,	301,	'2021-11-19 07:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	94,	1,	101,	301,	'2021-11-19 09:00:00',	2,	NULL,	3,	35000,	NULL,	NULL,	1),
(NULL,	95,	1,	101,	401,	'2021-11-30 08:00:00',	3,	NULL,	1,	45000,	NULL,	NULL,	1),
(NULL,	96,	1,	101,	301,	'2022-03-03 04:00:00',	1,	'1213TA',	1,	20000,	4,	NULL,	1),
(NULL,	97,	1,	101,	301,	'2022-03-03 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	98,	1,	101,	301,	'2022-03-03 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	99,	1,	101,	301,	'2022-03-03 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	100,	1,	101,	301,	'2022-03-03 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	101,	1,	101,	301,	'2022-03-03 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	102,	1,	101,	301,	'2022-03-03 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	103,	1,	101,	301,	'2022-03-03 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	104,	1,	101,	301,	'2022-03-03 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	105,	1,	101,	301,	'2022-03-03 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	106,	1,	101,	301,	'2022-03-03 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	107,	1,	101,	301,	'2022-03-03 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	108,	1,	101,	301,	'2022-03-04 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	109,	1,	101,	301,	'2022-03-04 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	110,	1,	101,	301,	'2022-03-04 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	111,	1,	101,	301,	'2022-03-04 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	112,	1,	101,	301,	'2022-03-04 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	113,	1,	101,	301,	'2022-03-04 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	114,	1,	101,	301,	'2022-03-04 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	115,	1,	101,	301,	'2022-03-04 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	116,	1,	101,	301,	'2022-03-04 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	117,	1,	101,	301,	'2022-03-04 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	118,	1,	101,	301,	'2022-03-04 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	119,	1,	101,	301,	'2022-03-04 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	120,	1,	101,	301,	'2022-03-05 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	121,	1,	101,	301,	'2022-03-05 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	122,	1,	101,	301,	'2022-03-05 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	123,	1,	101,	301,	'2022-03-05 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	124,	1,	101,	301,	'2022-03-05 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	125,	1,	101,	301,	'2022-03-05 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	126,	1,	101,	301,	'2022-03-05 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	127,	1,	101,	301,	'2022-03-05 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	128,	1,	101,	301,	'2022-03-05 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	129,	1,	101,	301,	'2022-03-05 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	130,	1,	101,	301,	'2022-03-05 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	131,	1,	101,	301,	'2022-03-05 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	132,	1,	101,	301,	'2022-03-06 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	133,	1,	101,	301,	'2022-03-06 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	134,	1,	101,	301,	'2022-03-06 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	135,	1,	101,	301,	'2022-03-06 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	136,	1,	101,	301,	'2022-03-06 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	137,	1,	101,	301,	'2022-03-06 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	138,	1,	101,	301,	'2022-03-06 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	139,	1,	101,	301,	'2022-03-06 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	140,	1,	101,	301,	'2022-03-06 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	141,	1,	101,	301,	'2022-03-06 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	142,	1,	101,	301,	'2022-03-06 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	143,	1,	101,	301,	'2022-03-06 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	144,	1,	101,	301,	'2022-03-07 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	145,	1,	101,	301,	'2022-03-07 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	146,	1,	101,	301,	'2022-03-07 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	147,	1,	101,	301,	'2022-03-07 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	148,	1,	101,	301,	'2022-03-07 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	149,	1,	101,	301,	'2022-03-07 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	150,	1,	101,	301,	'2022-03-07 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	151,	1,	101,	301,	'2022-03-07 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	152,	1,	101,	301,	'2022-03-07 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	153,	1,	101,	301,	'2022-03-07 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	154,	1,	101,	301,	'2022-03-07 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	155,	1,	101,	301,	'2022-03-07 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	156,	1,	101,	301,	'2022-03-08 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	157,	1,	101,	301,	'2022-03-08 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	158,	1,	101,	301,	'2022-03-08 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	159,	1,	101,	301,	'2022-03-08 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	160,	1,	101,	301,	'2022-03-08 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	161,	1,	101,	301,	'2022-03-08 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	162,	1,	101,	301,	'2022-03-08 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	163,	1,	101,	301,	'2022-03-08 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	164,	1,	101,	301,	'2022-03-08 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	165,	1,	101,	301,	'2022-03-08 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	166,	1,	101,	301,	'2022-03-08 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	167,	1,	101,	301,	'2022-03-08 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	168,	1,	101,	301,	'2022-03-09 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	169,	1,	101,	301,	'2022-03-09 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	170,	1,	101,	301,	'2022-03-09 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	171,	1,	101,	301,	'2022-03-09 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	172,	1,	101,	301,	'2022-03-09 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	173,	1,	101,	301,	'2022-03-09 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	174,	1,	101,	301,	'2022-03-09 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	175,	1,	101,	301,	'2022-03-09 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	176,	1,	101,	301,	'2022-03-09 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	177,	1,	101,	301,	'2022-03-09 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	178,	1,	101,	301,	'2022-03-09 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	179,	1,	101,	301,	'2022-03-09 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	180,	1,	101,	301,	'2022-03-10 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	181,	1,	101,	301,	'2022-03-10 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	182,	1,	101,	301,	'2022-03-10 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	183,	1,	101,	301,	'2022-03-10 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	184,	1,	101,	301,	'2022-03-10 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	185,	1,	101,	301,	'2022-03-10 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	186,	1,	101,	301,	'2022-03-10 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	187,	1,	101,	301,	'2022-03-10 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	188,	1,	101,	301,	'2022-03-10 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	189,	1,	101,	301,	'2022-03-10 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	190,	1,	101,	301,	'2022-03-10 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	191,	1,	101,	301,	'2022-03-10 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	192,	1,	101,	301,	'2022-03-11 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	193,	1,	101,	301,	'2022-03-11 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	194,	1,	101,	301,	'2022-03-11 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	195,	1,	101,	301,	'2022-03-11 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	196,	1,	101,	301,	'2022-03-11 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	197,	1,	101,	301,	'2022-03-11 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	198,	1,	101,	301,	'2022-03-11 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	199,	1,	101,	301,	'2022-03-11 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	200,	1,	101,	301,	'2022-03-11 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	201,	1,	101,	301,	'2022-03-11 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	202,	1,	101,	301,	'2022-03-11 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	203,	1,	101,	301,	'2022-03-11 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	204,	1,	101,	301,	'2022-03-12 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	205,	1,	101,	301,	'2022-03-12 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	206,	1,	101,	301,	'2022-03-12 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	207,	1,	101,	301,	'2022-03-12 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	208,	1,	101,	301,	'2022-03-12 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	209,	1,	101,	301,	'2022-03-12 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	210,	1,	101,	301,	'2022-03-12 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	211,	1,	101,	301,	'2022-03-12 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	212,	1,	101,	301,	'2022-03-12 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	213,	1,	101,	301,	'2022-03-12 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	214,	1,	101,	301,	'2022-03-12 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	215,	1,	101,	301,	'2022-03-12 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	216,	1,	101,	301,	'2022-03-13 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	217,	1,	101,	301,	'2022-03-13 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	218,	1,	101,	301,	'2022-03-13 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	219,	1,	101,	301,	'2022-03-13 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	220,	1,	101,	301,	'2022-03-13 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	221,	1,	101,	301,	'2022-03-13 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	222,	1,	101,	301,	'2022-03-13 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	223,	1,	101,	301,	'2022-03-13 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	224,	1,	101,	301,	'2022-03-13 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	225,	1,	101,	301,	'2022-03-13 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	226,	1,	101,	301,	'2022-03-13 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	227,	1,	101,	301,	'2022-03-13 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	228,	1,	101,	301,	'2022-03-14 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	229,	1,	101,	301,	'2022-03-14 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	230,	1,	101,	301,	'2022-03-14 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	231,	1,	101,	301,	'2022-03-14 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	232,	1,	101,	301,	'2022-03-14 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	233,	1,	101,	301,	'2022-03-14 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	234,	1,	101,	301,	'2022-03-14 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	235,	1,	101,	301,	'2022-03-14 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	236,	1,	101,	301,	'2022-03-14 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	237,	1,	101,	301,	'2022-03-14 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	238,	1,	101,	301,	'2022-03-14 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	239,	1,	101,	301,	'2022-03-14 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	240,	1,	101,	301,	'2022-03-15 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	241,	1,	101,	301,	'2022-03-15 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	242,	1,	101,	301,	'2022-03-15 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	243,	1,	101,	301,	'2022-03-15 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	244,	1,	101,	301,	'2022-03-15 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	245,	1,	101,	301,	'2022-03-15 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	246,	1,	101,	301,	'2022-03-15 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	247,	1,	101,	301,	'2022-03-15 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	248,	1,	101,	301,	'2022-03-15 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	249,	1,	101,	301,	'2022-03-15 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	250,	1,	101,	301,	'2022-03-15 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	251,	1,	101,	301,	'2022-03-15 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	252,	1,	101,	301,	'2022-03-16 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	253,	1,	101,	301,	'2022-03-16 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	254,	1,	101,	301,	'2022-03-16 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	255,	1,	101,	301,	'2022-03-16 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	256,	1,	101,	301,	'2022-03-16 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	257,	1,	101,	301,	'2022-03-16 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	258,	1,	101,	301,	'2022-03-16 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	259,	1,	101,	301,	'2022-03-16 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	260,	1,	101,	301,	'2022-03-16 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	261,	1,	101,	301,	'2022-03-16 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	262,	1,	101,	301,	'2022-03-16 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	263,	1,	101,	301,	'2022-03-16 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	264,	1,	101,	301,	'2022-03-17 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	265,	1,	101,	301,	'2022-03-17 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	266,	1,	101,	301,	'2022-03-17 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	267,	1,	101,	301,	'2022-03-17 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	268,	1,	101,	301,	'2022-03-17 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	269,	1,	101,	301,	'2022-03-17 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	270,	1,	101,	301,	'2022-03-17 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	271,	1,	101,	301,	'2022-03-17 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	272,	1,	101,	301,	'2022-03-17 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	273,	1,	101,	301,	'2022-03-17 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	274,	1,	101,	301,	'2022-03-17 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	275,	1,	101,	301,	'2022-03-17 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	276,	1,	101,	301,	'2022-03-18 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	277,	1,	101,	301,	'2022-03-18 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	278,	1,	101,	301,	'2022-03-18 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	279,	1,	101,	301,	'2022-03-18 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	280,	1,	101,	301,	'2022-03-18 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	281,	1,	101,	301,	'2022-03-18 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	282,	1,	101,	301,	'2022-03-18 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	283,	1,	101,	301,	'2022-03-18 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	284,	1,	101,	301,	'2022-03-18 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	285,	1,	101,	301,	'2022-03-18 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	286,	1,	101,	301,	'2022-03-18 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	287,	1,	101,	301,	'2022-03-18 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	288,	1,	101,	301,	'2022-03-19 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	289,	1,	101,	301,	'2022-03-19 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	290,	1,	101,	301,	'2022-03-19 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	291,	1,	101,	301,	'2022-03-19 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	292,	1,	101,	301,	'2022-03-19 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	293,	1,	101,	301,	'2022-03-19 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	294,	1,	101,	301,	'2022-03-19 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	295,	1,	101,	301,	'2022-03-19 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	296,	1,	101,	301,	'2022-03-19 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	297,	1,	101,	301,	'2022-03-19 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	298,	1,	101,	301,	'2022-03-19 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	299,	1,	101,	301,	'2022-03-19 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	300,	1,	101,	301,	'2022-03-20 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	301,	1,	101,	301,	'2022-03-20 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	302,	1,	101,	301,	'2022-03-20 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	303,	1,	101,	301,	'2022-03-20 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	304,	1,	101,	301,	'2022-03-20 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	305,	1,	101,	301,	'2022-03-20 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	306,	1,	101,	301,	'2022-03-20 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	307,	1,	101,	301,	'2022-03-20 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	308,	1,	101,	301,	'2022-03-20 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	309,	1,	101,	301,	'2022-03-20 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	310,	1,	101,	301,	'2022-03-20 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	311,	1,	101,	301,	'2022-03-20 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	312,	1,	101,	301,	'2022-03-21 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	313,	1,	101,	301,	'2022-03-21 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	314,	1,	101,	301,	'2022-03-21 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	315,	1,	101,	301,	'2022-03-21 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	316,	1,	101,	301,	'2022-03-21 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	317,	1,	101,	301,	'2022-03-21 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	318,	1,	101,	301,	'2022-03-21 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	319,	1,	101,	301,	'2022-03-21 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	320,	1,	101,	301,	'2022-03-21 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	321,	1,	101,	301,	'2022-03-21 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	322,	1,	101,	301,	'2022-03-21 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	323,	1,	101,	301,	'2022-03-21 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	324,	1,	101,	301,	'2022-03-22 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	325,	1,	101,	301,	'2022-03-22 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	326,	1,	101,	301,	'2022-03-22 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	327,	1,	101,	301,	'2022-03-22 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	328,	1,	101,	301,	'2022-03-22 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	329,	1,	101,	301,	'2022-03-22 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	330,	1,	101,	301,	'2022-03-22 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	331,	1,	101,	301,	'2022-03-22 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	332,	1,	101,	301,	'2022-03-22 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	333,	1,	101,	301,	'2022-03-22 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	334,	1,	101,	301,	'2022-03-22 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	335,	1,	101,	301,	'2022-03-22 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	336,	1,	101,	301,	'2022-03-23 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	337,	1,	101,	301,	'2022-03-23 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	338,	1,	101,	301,	'2022-03-23 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	339,	1,	101,	301,	'2022-03-23 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	340,	1,	101,	301,	'2022-03-23 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	341,	1,	101,	301,	'2022-03-23 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	342,	1,	101,	301,	'2022-03-23 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	343,	1,	101,	301,	'2022-03-23 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	344,	1,	101,	301,	'2022-03-23 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	345,	1,	101,	301,	'2022-03-23 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	346,	1,	101,	301,	'2022-03-23 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	347,	1,	101,	301,	'2022-03-23 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	348,	1,	101,	301,	'2022-03-24 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	349,	1,	101,	301,	'2022-03-24 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	350,	1,	101,	301,	'2022-03-24 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	351,	1,	101,	301,	'2022-03-24 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	352,	1,	101,	301,	'2022-03-24 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	353,	1,	101,	301,	'2022-03-24 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	354,	1,	101,	301,	'2022-03-24 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	355,	1,	101,	301,	'2022-03-24 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	356,	1,	101,	301,	'2022-03-24 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	357,	1,	101,	301,	'2022-03-24 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	358,	1,	101,	301,	'2022-03-24 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	359,	1,	101,	301,	'2022-03-24 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	360,	1,	101,	301,	'2022-03-25 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	361,	1,	101,	301,	'2022-03-25 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	362,	1,	101,	301,	'2022-03-25 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	363,	1,	101,	301,	'2022-03-25 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	364,	1,	101,	301,	'2022-03-25 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	365,	1,	101,	301,	'2022-03-25 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	366,	1,	101,	301,	'2022-03-25 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	367,	1,	101,	301,	'2022-03-25 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	368,	1,	101,	301,	'2022-03-25 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	369,	1,	101,	301,	'2022-03-25 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	370,	1,	101,	301,	'2022-03-25 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	371,	1,	101,	301,	'2022-03-25 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	372,	1,	101,	301,	'2022-03-26 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	373,	1,	101,	301,	'2022-03-26 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	374,	1,	101,	301,	'2022-03-26 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	375,	1,	101,	301,	'2022-03-26 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	376,	1,	101,	301,	'2022-03-26 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	377,	1,	101,	301,	'2022-03-26 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	378,	1,	101,	301,	'2022-03-26 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	379,	1,	101,	301,	'2022-03-26 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	380,	1,	101,	301,	'2022-03-26 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	381,	1,	101,	301,	'2022-03-26 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	382,	1,	101,	301,	'2022-03-26 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	383,	1,	101,	301,	'2022-03-26 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	384,	1,	101,	301,	'2022-03-27 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	385,	1,	101,	301,	'2022-03-27 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	386,	1,	101,	301,	'2022-03-27 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	387,	1,	101,	301,	'2022-03-27 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	388,	1,	101,	301,	'2022-03-27 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	389,	1,	101,	301,	'2022-03-27 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	390,	1,	101,	301,	'2022-03-27 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	391,	1,	101,	301,	'2022-03-27 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	392,	1,	101,	301,	'2022-03-27 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	393,	1,	101,	301,	'2022-03-27 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	394,	1,	101,	301,	'2022-03-27 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	395,	1,	101,	301,	'2022-03-27 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	396,	1,	101,	301,	'2022-03-28 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	397,	1,	101,	301,	'2022-03-28 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	398,	1,	101,	301,	'2022-03-28 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	399,	1,	101,	301,	'2022-03-28 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	400,	1,	101,	301,	'2022-03-28 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	401,	1,	101,	301,	'2022-03-28 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	402,	1,	101,	301,	'2022-03-28 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	403,	1,	101,	301,	'2022-03-28 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	404,	1,	101,	301,	'2022-03-28 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	405,	1,	101,	301,	'2022-03-28 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	406,	1,	101,	301,	'2022-03-28 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	407,	1,	101,	301,	'2022-03-28 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	408,	1,	101,	301,	'2022-03-29 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	409,	1,	101,	301,	'2022-03-29 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	410,	1,	101,	301,	'2022-03-29 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	411,	1,	101,	301,	'2022-03-29 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	412,	1,	101,	301,	'2022-03-29 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	413,	1,	101,	301,	'2022-03-29 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	414,	1,	101,	301,	'2022-03-29 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	415,	1,	101,	301,	'2022-03-29 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	416,	1,	101,	301,	'2022-03-29 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	417,	1,	101,	301,	'2022-03-29 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	418,	1,	101,	301,	'2022-03-29 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	419,	1,	101,	301,	'2022-03-29 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	420,	1,	101,	301,	'2022-03-30 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	421,	1,	101,	301,	'2022-03-30 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	422,	1,	101,	301,	'2022-03-30 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	423,	1,	101,	301,	'2022-03-30 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	424,	1,	101,	301,	'2022-03-30 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	425,	1,	101,	301,	'2022-03-30 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	426,	1,	101,	301,	'2022-03-30 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	427,	1,	101,	301,	'2022-03-30 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	428,	1,	101,	301,	'2022-03-30 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	429,	1,	101,	301,	'2022-03-30 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	430,	1,	101,	301,	'2022-03-30 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	431,	1,	101,	301,	'2022-03-30 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	432,	1,	101,	301,	'2022-03-31 04:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	433,	1,	101,	301,	'2022-03-31 04:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	434,	1,	101,	301,	'2022-03-31 04:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	435,	1,	101,	301,	'2022-03-31 04:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	436,	1,	101,	301,	'2022-03-31 04:30:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	437,	1,	101,	301,	'2022-03-31 04:30:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	438,	1,	101,	301,	'2022-03-31 04:30:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	439,	1,	101,	301,	'2022-03-31 04:30:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	440,	1,	101,	301,	'2022-03-31 05:00:00',	1,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	441,	1,	101,	301,	'2022-03-31 05:00:00',	2,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	442,	1,	101,	301,	'2022-03-31 05:00:00',	3,	NULL,	1,	20000,	NULL,	NULL,	1),
(NULL,	443,	1,	101,	301,	'2022-03-31 05:00:00',	4,	NULL,	1,	20000,	NULL,	NULL,	1);

DROP TABLE IF EXISTS `departuremanifold`;
CREATE TABLE `departuremanifold` (
  `iddeparturemanifold` int NOT NULL AUTO_INCREMENT,
  `departure` int DEFAULT NULL,
  `placenumber` int DEFAULT NULL,
  `civility` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idtype` int DEFAULT NULL,
  `idnumber` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idissuedate` date DEFAULT NULL,
  `idissuelocation` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idduplicatadate` date DEFAULT NULL,
  `idduplicatalocation` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contactcivility` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contactfname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contactlname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contactaddress` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contactphone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contactphone1` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `daty` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`iddeparturemanifold`),
  KEY `fk_departure_idx` (`departure`),
  KEY `fk_idtype_departuremanifold_idx` (`idtype`),
  CONSTRAINT `fk_departure` FOREIGN KEY (`departure`) REFERENCES `departure` (`iddeparture`),
  CONSTRAINT `fk_idtype_departuremanifold` FOREIGN KEY (`idtype`) REFERENCES `idtype` (`ididtype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `departuremanifold` (`iddeparturemanifold`, `departure`, `placenumber`, `civility`, `fname`, `lname`, `address`, `idtype`, `idnumber`, `idissuedate`, `idissuelocation`, `idduplicatadate`, `idduplicatalocation`, `contactcivility`, `contactfname`, `contactlname`, `contactaddress`, `contactphone`, `contactphone1`, `daty`) VALUES
(45,	96,	10,	'',	'1231',	'',	'',	NULL,	'',	NULL,	'',	NULL,	'',	'',	'',	'',	'',	'',	'',	''),
(46,	96,	11,	'Mr',	'456123',	'',	'',	1,	'',	NULL,	'',	NULL,	'',	'Mr',	'',	'',	'',	'',	'',	'');

DROP TABLE IF EXISTS `departurestatus`;
CREATE TABLE `departurestatus` (
  `iddeparturestatus` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`iddeparturestatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `departurestatus` (`iddeparturestatus`, `status`) VALUES
(1,	'ONDATE'),
(2,	'CANCELLED'),
(3,	'TRANSFERED');

DROP TABLE IF EXISTS `employeerole`;
CREATE TABLE `employeerole` (
  `idrole` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `employeerole` (`idrole`, `name`) VALUES
(1,	'superadmin'),
(2,	'admin'),
(3,	'responsible'),
(4,	'receptionist'),
(5,	'porter'),
(6,	'driver');

DROP TABLE IF EXISTS `idtype`;
CREATE TABLE `idtype` (
  `ididtype` int NOT NULL,
  `idtypecol` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ididtype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `idtype` (`ididtype`, `idtypecol`) VALUES
(1,	'CIN'),
(2,	'PASSPORT'),
(3,	'CARTE ETUDIANT');

DROP TABLE IF EXISTS `paymentmethod`;
CREATE TABLE `paymentmethod` (
  `idpaymentmethod` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idpaymentmethod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `paymentmethod` (`idpaymentmethod`, `name`) VALUES
(1,	'CASH'),
(2,	'MVOLA'),
(3,	'ORANGEMONEY'),
(4,	'AIRTELMONEY'),
(5,	'CREDITCARD');

DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `idvehicle` int NOT NULL AUTO_INCREMENT,
  `vehicletype` int DEFAULT NULL,
  `vehicleplace` int DEFAULT NULL,
  PRIMARY KEY (`idvehicle`),
  KEY `fk_vehicletype_idx` (`vehicletype`),
  KEY `fk_vehicleplace_idx` (`vehicleplace`),
  CONSTRAINT `fk_vehicleplace` FOREIGN KEY (`vehicleplace`) REFERENCES `vehicleplace` (`idvehicleplace`),
  CONSTRAINT `fk_vehicletype` FOREIGN KEY (`vehicletype`) REFERENCES `vehicletype` (`idvehicletype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `vehicle` (`idvehicle`, `vehicletype`, `vehicleplace`) VALUES
(1,	1,	2),
(2,	4,	1),
(3,	1,	3),
(4,	1,	4);

DROP TABLE IF EXISTS `vehiclemappingplace`;
CREATE TABLE `vehiclemappingplace` (
  `idvehiclemappingplace` int NOT NULL AUTO_INCREMENT,
  `vehicle` int DEFAULT NULL,
  `x` int DEFAULT NULL,
  `y` int DEFAULT NULL,
  `placenumber` int DEFAULT NULL,
  PRIMARY KEY (`idvehiclemappingplace`),
  KEY `fk_vehicle_idx` (`vehicle`),
  CONSTRAINT `fk_vehicle` FOREIGN KEY (`vehicle`) REFERENCES `vehicle` (`idvehicle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `vehiclemappingplace` (`idvehiclemappingplace`, `vehicle`, `x`, `y`, `placenumber`) VALUES
(1,	2,	1,	1,	-2),
(2,	2,	1,	2,	-1),
(3,	2,	1,	3,	1),
(4,	2,	2,	1,	2),
(5,	2,	2,	2,	3),
(6,	2,	2,	3,	4),
(7,	2,	3,	1,	5),
(8,	2,	3,	2,	6),
(9,	2,	3,	3,	-1),
(10,	2,	4,	1,	7),
(11,	2,	4,	2,	8),
(12,	2,	4,	3,	9),
(13,	1,	1,	1,	-2),
(14,	1,	1,	2,	-1),
(15,	1,	1,	3,	1),
(16,	1,	1,	4,	2),
(17,	1,	2,	1,	3),
(18,	1,	2,	2,	4),
(19,	1,	2,	3,	5),
(20,	1,	2,	4,	6),
(21,	1,	3,	1,	7),
(22,	1,	3,	2,	8),
(23,	1,	3,	3,	-1),
(24,	1,	3,	4,	9),
(25,	1,	4,	1,	10),
(26,	1,	4,	2,	11),
(27,	1,	4,	3,	-1),
(28,	1,	4,	4,	12),
(29,	1,	5,	1,	13),
(30,	1,	5,	2,	14),
(31,	1,	5,	3,	15),
(32,	1,	5,	4,	16),
(33,	3,	1,	1,	-2),
(34,	3,	1,	2,	-1),
(35,	3,	1,	3,	1),
(36,	3,	1,	4,	2),
(37,	3,	2,	1,	3),
(38,	3,	2,	2,	4),
(39,	3,	2,	3,	5),
(40,	3,	2,	4,	6),
(41,	3,	3,	1,	7),
(42,	3,	3,	2,	8),
(43,	3,	3,	3,	-1),
(44,	3,	3,	4,	9),
(45,	3,	4,	1,	10),
(46,	3,	4,	2,	11),
(47,	3,	4,	3,	-1),
(48,	3,	4,	4,	12),
(49,	3,	5,	1,	17),
(50,	3,	5,	2,	18),
(51,	3,	5,	3,	-1),
(52,	3,	5,	4,	19),
(53,	3,	6,	1,	13),
(54,	3,	6,	2,	14),
(55,	3,	6,	3,	15),
(56,	3,	6,	4,	16),
(58,	4,	1,	1,	-2),
(59,	4,	1,	2,	-1),
(60,	4,	1,	3,	1),
(61,	4,	1,	4,	2),
(62,	4,	2,	1,	3),
(63,	4,	2,	2,	4),
(64,	4,	2,	3,	5),
(65,	4,	2,	4,	6),
(66,	4,	3,	1,	7),
(67,	4,	3,	2,	8),
(68,	4,	3,	3,	-1),
(69,	4,	3,	4,	9),
(70,	4,	4,	1,	10),
(71,	4,	4,	2,	11),
(72,	4,	4,	3,	-1),
(73,	4,	4,	4,	12),
(74,	4,	5,	1,	17),
(75,	4,	5,	2,	18),
(76,	4,	5,	3,	-1),
(77,	4,	5,	4,	19),
(78,	4,	6,	1,	20),
(79,	4,	6,	2,	21),
(80,	4,	6,	3,	-1),
(81,	4,	6,	4,	22),
(82,	4,	7,	1,	13),
(83,	4,	7,	2,	14),
(84,	4,	7,	3,	15),
(85,	4,	7,	4,	16);

DROP TABLE IF EXISTS `vehicleplace`;
CREATE TABLE `vehicleplace` (
  `idvehicleplace` int NOT NULL AUTO_INCREMENT,
  `nbtotalplace` int DEFAULT NULL,
  `nbligne` int DEFAULT NULL,
  `nbcolumn` int DEFAULT NULL,
  PRIMARY KEY (`idvehicleplace`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `vehicleplace` (`idvehicleplace`, `nbtotalplace`, `nbligne`, `nbcolumn`) VALUES
(1,	9,	4,	3),
(2,	16,	5,	4),
(3,	19,	6,	4),
(4,	22,	7,	4);

DROP TABLE IF EXISTS `vehicletype`;
CREATE TABLE `vehicletype` (
  `idvehicletype` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idvehicletype`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `vehicletype` (`idvehicletype`, `name`) VALUES
(3,	'4X4'),
(2,	'MINIBUS'),
(1,	'SPRINTER'),
(4,	'STAREX');


CREATE TABLE `permission` (
  `idpermission` varchar(255) NOT NULL PRIMARY KEY,
  `description` TEXT
  ) ENGINE='InnoDB' COLLATE 'utf8mb4_unicode_ci';


CREATE TABLE `role` (
  `idrole` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255) NOT NULL
  ) ENGINE='InnoDB' COLLATE 'utf8mb4_unicode_ci';


CREATE TABLE `rolepermission` (
  `idrole` int(11) NOT NULL,
  `idpermission` varchar(255) NOT NULL,
  PRIMARY KEY (`idrole`,`idpermission`),
  CONSTRAINT `role_permission_ibfk_1` 
   FOREIGN KEY (`idrole`) REFERENCES `role` (`idrole`),
  CONSTRAINT `role_permission_ibfk_2` 
   FOREIGN KEY (`idpermission`) REFERENCES `permission` (`idpermission`)
) ENGINE='InnoDB' COLLATE 'utf8mb4_unicode_ci';

CREATE TABLE `user` (
  `iduser` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int,
    KEY `fk_user_role_idx` (`role`),
    CONSTRAINT `fk_user_role_idx` FOREIGN KEY (`role`) REFERENCES `role` (`idrole`)
  ) ENGINE='InnoDB' COLLATE 'utf8mb4_unicode_ci';

CREATE TABLE `userpermission` (
  `iduser` int(11) NOT NULL,
  `idpermission` varchar(255) NOT NULL,
  PRIMARY KEY (`iduser`,`idpermission`),
  CONSTRAINT `user_permission_ibfk_1` 
   FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `user_permission_ibfk_2` 
   FOREIGN KEY (`idpermission`) REFERENCES `permission` (`idpermission`)
) ENGINE='InnoDB' COLLATE 'utf8mb4_unicode_ci';



INSERT INTO `permission` (`idpermission`, `description`)
VALUES ('departure', 'All read/write operations to departure API'),
('departure.readonly', 'Read only operations to departure API'),
('booking', 'All read/write operations to Booking API'),
('booking.readonly', 'Read only operations to Booking API'),
('payment-method', 'All read/write operations to payment method API'),
('payment-method.readonly', 'Read only operations to payment method API'),
('city', 'All read/write operations to city API'),
('city.readonly', 'Read only operations to city API'),
('company', 'All read/write operations to company API'),
('company.readonly', 'Read only operations to company API');


INSERT INTO `user` (`username`, `password`)
VALUES ('webapp', 'webapp');

-- 2022-03-06 20:09:13