-- Adminer 4.8.1 MySQL 8.0.28 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking` (
  `id_booking` varchar(36) NOT NULL,
  `amount` double DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `description` text,
  `payment_id` varchar(100) DEFAULT NULL,
  `payment_method` varchar(100) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_booking_cancellation` varchar(36) DEFAULT NULL,
  `client_id_person` varchar(36) DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  `status_date` datetime DEFAULT NULL,
  `wait_confirm_until` datetime DEFAULT NULL,
  PRIMARY KEY (`id_booking`),
  KEY `FKk1qcath8q15t3132opvopvoom` (`id_booking_cancellation`),
  KEY `FK34tpjnl8ri0dd2hfa5g5463cd` (`client_id_person`),
  KEY `FKlxcm43w5sja7wrlcg80mydomr` (`creation_id_person`),
  KEY `FK8mr7kq9wts7tp9qbd5jsdlk93` (`update_id_person`),
  CONSTRAINT `FK34tpjnl8ri0dd2hfa5g5463cd` FOREIGN KEY (`client_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FK8mr7kq9wts7tp9qbd5jsdlk93` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKk1qcath8q15t3132opvopvoom` FOREIGN KEY (`id_booking_cancellation`) REFERENCES `booking_cancellation` (`id_booking_cancellation`),
  CONSTRAINT `FKlxcm43w5sja7wrlcg80mydomr` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `booking_cancellation`;
CREATE TABLE `booking_cancellation` (
  `id_booking_cancellation` varchar(36) NOT NULL,
  `amount_reimbursed` double DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `payment_id` varchar(100) DEFAULT NULL,
  `payment_method` varchar(100) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_booking_cancellation`),
  KEY `FK8kxe132qexlamn18k5xqdoobu` (`creation_id_person`),
  KEY `FKorhmm5w7yem0y00t7675tgi98` (`update_id_person`),
  CONSTRAINT `FK8kxe132qexlamn18k5xqdoobu` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKorhmm5w7yem0y00t7675tgi98` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `booking_line`;
CREATE TABLE `booking_line` (
  `id_booking_line` varchar(36) NOT NULL,
  `price` double NOT NULL,
  `amount` double DEFAULT NULL,
  `boarding_date` datetime DEFAULT NULL,
  `check_in_date` datetime DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `description` text,
  `status` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_booking` varchar(36) DEFAULT NULL,
  `id_booking_cancellation` varchar(36) DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `id_departure` varchar(36) DEFAULT NULL,
  `transferred_from_id_organization` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_booking_line`),
  KEY `FK93o5n45x2k57yjuf5bsmkf0en` (`id_booking`),
  KEY `FK5my1xfdenq0k00dcri9g8b3aq` (`id_booking_cancellation`),
  KEY `FKkbqbvto0jlxhumt8brwn3obth` (`creation_id_person`),
  KEY `FK8c9k2cyyumkkqd5yexod7nviq` (`id_departure`),
  KEY `FKjd9drmgjy1c8q21nnsdnil5y6` (`transferred_from_id_organization`),
  KEY `FKvxw791m2hl6uar122apg1l6l` (`update_id_person`),
  CONSTRAINT `FK5my1xfdenq0k00dcri9g8b3aq` FOREIGN KEY (`id_booking_cancellation`) REFERENCES `booking_cancellation` (`id_booking_cancellation`),
  CONSTRAINT `FK8c9k2cyyumkkqd5yexod7nviq` FOREIGN KEY (`id_departure`) REFERENCES `departure` (`id_departure`),
  CONSTRAINT `FK93o5n45x2k57yjuf5bsmkf0en` FOREIGN KEY (`id_booking`) REFERENCES `booking` (`id_booking`),
  CONSTRAINT `FKjd9drmgjy1c8q21nnsdnil5y6` FOREIGN KEY (`transferred_from_id_organization`) REFERENCES `organization` (`id_organization`),
  CONSTRAINT `FKkbqbvto0jlxhumt8brwn3obth` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKvxw791m2hl6uar122apg1l6l` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `booking_line_extra_luggage`;
CREATE TABLE `booking_line_extra_luggage` (
  `id_booking_line_extra_luggage` varchar(36) NOT NULL,
  `amount` double DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `payment_id` varchar(100) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `id_booking_line` varchar(36) DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_booking_line_extra_luggage`),
  KEY `FK2ofn87qhsw8xg5e24liv5pafu` (`id_booking_line`),
  KEY `FKtq6pu7jga9mdpyxrn99cr64hk` (`creation_id_person`),
  KEY `FKhqtnhboh8s8d6ysplv9xr9x6w` (`update_id_person`),
  CONSTRAINT `FK2ofn87qhsw8xg5e24liv5pafu` FOREIGN KEY (`id_booking_line`) REFERENCES `booking_line` (`id_booking_line`),
  CONSTRAINT `FKhqtnhboh8s8d6ysplv9xr9x6w` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKtq6pu7jga9mdpyxrn99cr64hk` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `booking_line_luggage`;
CREATE TABLE `booking_line_luggage` (
  `id_booking_line_luggage` varchar(36) NOT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `description` text,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `id_booking_line` varchar(36) DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_booking_line_luggage`),
  KEY `FK4981g1qcqjpxl7ysddleejm0j` (`id_booking_line`),
  KEY `FKpgt72gpr1i1i18uregb2yecbb` (`creation_id_person`),
  KEY `FKq3s9xdyasn4gchfotchfxo9l1` (`update_id_person`),
  CONSTRAINT `FK4981g1qcqjpxl7ysddleejm0j` FOREIGN KEY (`id_booking_line`) REFERENCES `booking_line` (`id_booking_line`),
  CONSTRAINT `FKpgt72gpr1i1i18uregb2yecbb` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKq3s9xdyasn4gchfotchfxo9l1` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `booking_line_seat`;
CREATE TABLE `booking_line_seat` (
  `id_booking_line_seat` varchar(36) NOT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `seat_number` int DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_booking_line` varchar(36) DEFAULT NULL,
  `id_booking_cancellation` varchar(36) DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_booking_line_seat`),
  KEY `FKs3hbllwp7v3y8nf4jv5pbliua` (`id_booking_line`),
  KEY `FK46kmg3o88d8yk6o52sh18sh2m` (`id_booking_cancellation`),
  KEY `FKe0nvaggcvf6wy2rlibd1j61b9` (`creation_id_person`),
  KEY `FKtjobwx4ykvebqpv9l55xcpgq2` (`update_id_person`),
  CONSTRAINT `FK46kmg3o88d8yk6o52sh18sh2m` FOREIGN KEY (`id_booking_cancellation`) REFERENCES `booking_cancellation` (`id_booking_cancellation`),
  CONSTRAINT `FKe0nvaggcvf6wy2rlibd1j61b9` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKs3hbllwp7v3y8nf4jv5pbliua` FOREIGN KEY (`id_booking_line`) REFERENCES `booking_line` (`id_booking_line`),
  CONSTRAINT `FKtjobwx4ykvebqpv9l55xcpgq2` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id_category` varchar(36) NOT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `id_organization` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_category`),
  KEY `FKietybfn50alm2w4m40qavyk19` (`creation_id_person`),
  KEY `FKkfuehyh464awfs9u6853q8f2w` (`id_organization`),
  KEY `FK9a1qs8io3vi1fk4l18p7cimom` (`update_id_person`),
  CONSTRAINT `FK9a1qs8io3vi1fk4l18p7cimom` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKietybfn50alm2w4m40qavyk19` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKkfuehyh464awfs9u6853q8f2w` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id_city` varchar(36) NOT NULL,
  `zip_code` int DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `trigram` varchar(10) NOT NULL,
  PRIMARY KEY (`id_city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `departure`;
CREATE TABLE `departure` (
  `id_departure` varchar(36) NOT NULL,
  `car_registration_number` varchar(255) DEFAULT NULL,
  `seat_columns` int DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  `end_date_time` datetime DEFAULT NULL,
  `seat_lines` int DEFAULT NULL,
  `lock_until` datetime DEFAULT NULL,
  `locked_by_phone` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int DEFAULT '0',
  `id_category` varchar(36) DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `id_employee_driver` varchar(36) DEFAULT NULL,
  `id_employee_driver2` varchar(36) DEFAULT NULL,
  `from_id_city` varchar(36) DEFAULT NULL,
  `locked_by_id_person` varchar(36) DEFAULT NULL,
  `id_organization` varchar(36) DEFAULT NULL,
  `to_id_city` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  `id_vehicle` varchar(36) DEFAULT NULL,
  `total_seats` int DEFAULT NULL,
  `available_seats` int DEFAULT NULL,
  `id_vehicle_type` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_departure`),
  KEY `FKrs6s9notwm7kt9le188djr76t` (`id_category`),
  KEY `FK87re6twncf5snor9x6w2mswqh` (`creation_id_person`),
  KEY `FKnxnqbx6a33qktw0yc6ii5jci5` (`id_employee_driver`),
  KEY `FK529qwykh2xj0i3vwd7vsp3fq2` (`id_employee_driver2`),
  KEY `FKjc0i7n8fu9h92oegk1t5vdomt` (`from_id_city`),
  KEY `FKamg9hm5hmivyp1726faeydwgb` (`locked_by_id_person`),
  KEY `FK38qrmhm5qt9wltdvv6q0cf4q7` (`id_organization`),
  KEY `FKskks8ejp1qbqauvks5mpmd86x` (`to_id_city`),
  KEY `FK1wp3b1y9oarpgck09fcbnrk1b` (`update_id_person`),
  KEY `FK6pg6j5x6iocnuo3mx175g0wlf` (`id_vehicle`),
  KEY `id_vehicle_type` (`id_vehicle_type`),
  CONSTRAINT `departure_ibfk_1` FOREIGN KEY (`id_vehicle_type`) REFERENCES `vehicle_type` (`id_vehicle_type`),
  CONSTRAINT `FK1wp3b1y9oarpgck09fcbnrk1b` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FK38qrmhm5qt9wltdvv6q0cf4q7` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`),
  CONSTRAINT `FK529qwykh2xj0i3vwd7vsp3fq2` FOREIGN KEY (`id_employee_driver2`) REFERENCES `organization_employee` (`id_employee`),
  CONSTRAINT `FK6pg6j5x6iocnuo3mx175g0wlf` FOREIGN KEY (`id_vehicle`) REFERENCES `vehicle` (`id_vehicle`),
  CONSTRAINT `FK87re6twncf5snor9x6w2mswqh` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKamg9hm5hmivyp1726faeydwgb` FOREIGN KEY (`locked_by_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKjc0i7n8fu9h92oegk1t5vdomt` FOREIGN KEY (`from_id_city`) REFERENCES `city` (`id_city`),
  CONSTRAINT `FKnxnqbx6a33qktw0yc6ii5jci5` FOREIGN KEY (`id_employee_driver`) REFERENCES `organization_employee` (`id_employee`),
  CONSTRAINT `FKrs6s9notwm7kt9le188djr76t` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  CONSTRAINT `FKskks8ejp1qbqauvks5mpmd86x` FOREIGN KEY (`to_id_city`) REFERENCES `city` (`id_city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `departure_manifold`;
CREATE TABLE `departure_manifold` (
  `id_departure_manifold` varchar(36) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `civility` varchar(10) DEFAULT NULL,
  `contact_address` varchar(255) DEFAULT NULL,
  `contact_civility` varchar(255) DEFAULT NULL,
  `contact_first_name` varchar(255) DEFAULT NULL,
  `contact_last_name` varchar(255) DEFAULT NULL,
  `contact_phone_number` varchar(50) DEFAULT NULL,
  `contact_phone_number2` varchar(50) DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `id_duplicate_date` date DEFAULT NULL,
  `id_duplicate_location` varchar(255) DEFAULT NULL,
  `id_issue_date` date DEFAULT NULL,
  `id_issue_location` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `id_type` varchar(50) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `seat_number` int DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_person_contact` varchar(36) DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `id_departure` varchar(36) DEFAULT NULL,
  `id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_departure_manifold`),
  KEY `FKkl6qiqwr0n4y26mfgr5omtglo` (`id_person_contact`),
  KEY `FK9mro7vick50ngn5pxeairnfye` (`creation_id_person`),
  KEY `FKdc9hfnrd7gdjfhs7tbko55hst` (`id_departure`),
  KEY `FKqvaiua2ivr5aad2vuelkk3nm9` (`id_person`),
  KEY `FK50rk2pcuna786pajqoaxq6fw1` (`update_id_person`),
  CONSTRAINT `FK50rk2pcuna786pajqoaxq6fw1` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FK9mro7vick50ngn5pxeairnfye` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKdc9hfnrd7gdjfhs7tbko55hst` FOREIGN KEY (`id_departure`) REFERENCES `departure` (`id_departure`),
  CONSTRAINT `FKkl6qiqwr0n4y26mfgr5omtglo` FOREIGN KEY (`id_person_contact`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKqvaiua2ivr5aad2vuelkk3nm9` FOREIGN KEY (`id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `departure_vehicle_seat`;
CREATE TABLE `departure_vehicle_seat` (
  `id_departure_vehicle_seat` varchar(36) NOT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `lock_until` datetime DEFAULT NULL,
  `locked_by_phone` varchar(50) DEFAULT NULL,
  `seat_number` int DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` int DEFAULT '0',
  `x` int DEFAULT NULL,
  `y` int DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `id_departure` varchar(36) DEFAULT NULL,
  `locked_by_id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_departure_vehicle_seat`),
  KEY `FKliea2h3uvethlmxg3bgrf40kr` (`creation_id_person`),
  KEY `FKhfnvoj7pxq5tm2tdtddvpv035` (`id_departure`),
  KEY `FKmsndrm6eupd3wp9gd916fdsey` (`locked_by_id_person`),
  KEY `FKcdijhbjwvsfebsvyebnf7vll2` (`update_id_person`),
  CONSTRAINT `FKcdijhbjwvsfebsvyebnf7vll2` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKhfnvoj7pxq5tm2tdtddvpv035` FOREIGN KEY (`id_departure`) REFERENCES `departure` (`id_departure`),
  CONSTRAINT `FKliea2h3uvethlmxg3bgrf40kr` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKmsndrm6eupd3wp9gd916fdsey` FOREIGN KEY (`locked_by_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id_organization` varchar(36) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `id_parent` varchar(36) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `ticket_logo` varchar(255) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_organization`),
  KEY `FKniekgqfs5jv0dv10v8yj43aol` (`creation_id_person`),
  KEY `FKk0yeoly86fs336be1uilcuat8` (`update_id_person`),
  CONSTRAINT `FKk0yeoly86fs336be1uilcuat8` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKniekgqfs5jv0dv10v8yj43aol` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `organization_config`;
CREATE TABLE `organization_config` (
  `id_organization_config` varchar(36) NOT NULL,
  `authorized_luggage` double DEFAULT NULL,
  `booking_cancellation_penalty_amount` double DEFAULT NULL,
  `booking_cancellation_penalty_percent` double DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `price_extra_luggage` double DEFAULT NULL,
  `transfer_commission` double DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_category` varchar(36) DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `from_id_city` varchar(36) DEFAULT NULL,
  `id_organization` varchar(36) DEFAULT NULL,
  `to_id_city` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_organization_config`),
  KEY `FK24t7l1yhildfb5aqt3o6ixq2a` (`id_category`),
  KEY `FKjfvhy3ie8o1epculw2tyj58ho` (`creation_id_person`),
  KEY `FKefsgtai76yo2vq9ucy0m5nb3b` (`from_id_city`),
  KEY `FK4vhyo7n7c3xex6a37pppcwef7` (`id_organization`),
  KEY `FK1dl61alq1ey9evjhiqhbfbrg8` (`to_id_city`),
  KEY `FK6mf6qs95ku8ws73182jl6ary0` (`update_id_person`),
  CONSTRAINT `FK1dl61alq1ey9evjhiqhbfbrg8` FOREIGN KEY (`to_id_city`) REFERENCES `city` (`id_city`),
  CONSTRAINT `FK24t7l1yhildfb5aqt3o6ixq2a` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  CONSTRAINT `FK4vhyo7n7c3xex6a37pppcwef7` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`),
  CONSTRAINT `FK6mf6qs95ku8ws73182jl6ary0` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKefsgtai76yo2vq9ucy0m5nb3b` FOREIGN KEY (`from_id_city`) REFERENCES `city` (`id_city`),
  CONSTRAINT `FKjfvhy3ie8o1epculw2tyj58ho` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `organization_employee`;
CREATE TABLE `organization_employee` (
  `id_employee` varchar(36) NOT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `identification_number` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `id_organization` varchar(36) DEFAULT NULL,
  `id_person` varchar(36) DEFAULT NULL,
  `id_position` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_employee`),
  KEY `FKsh6q2rhudw49nrvtm0a90eifr` (`creation_id_person`),
  KEY `FKjj7wp2h70ng4lheanrx1k8cne` (`id_organization`),
  KEY `FKk97f3rix5ai3th2cq40burxh5` (`id_person`),
  KEY `FKcgy2j5gf21dco7nmqx0uniddq` (`id_position`),
  KEY `FKakb24wpjm6oobvklk9k00el8k` (`update_id_person`),
  CONSTRAINT `FKakb24wpjm6oobvklk9k00el8k` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKcgy2j5gf21dco7nmqx0uniddq` FOREIGN KEY (`id_position`) REFERENCES `position` (`id_position`),
  CONSTRAINT `FKjj7wp2h70ng4lheanrx1k8cne` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`),
  CONSTRAINT `FKk97f3rix5ai3th2cq40burxh5` FOREIGN KEY (`id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKsh6q2rhudw49nrvtm0a90eifr` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id_person` varchar(36) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `civility` varchar(10) DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `creation_id_person` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `id_duplicate_date` date DEFAULT NULL,
  `id_duplicate_location` varchar(255) DEFAULT NULL,
  `id_issue_date` date DEFAULT NULL,
  `id_issue_location` varchar(255) DEFAULT NULL,
  `id_number` varchar(255) DEFAULT NULL,
  `id_type` varchar(50) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `id_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id_position` varchar(36)  NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `creation_id_person` varchar(36)  DEFAULT NULL,
  `id_organization` varchar(36)  DEFAULT NULL,
  `update_id_person` varchar(36)  DEFAULT NULL,
  PRIMARY KEY (`id_position`),
  KEY `FKscr126ub66fl5neadtxamg3y` (`creation_id_person`),
  KEY `FKq6822v4v6csvyycahdf6drda0` (`id_organization`),
  KEY `FK2ieoj6bcghom0e55y2dmn0apb` (`update_id_person`),
  CONSTRAINT `FK2ieoj6bcghom0e55y2dmn0apb` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKq6822v4v6csvyycahdf6drda0` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`),
  CONSTRAINT `FKscr126ub66fl5neadtxamg3y` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `id_vehicle` varchar(36)  NOT NULL,
  `car_registration_number` varchar(255) DEFAULT NULL,
  `seat_columns` int DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `seat_lines` int DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `creation_id_person` varchar(36)  DEFAULT NULL,
  `id_organization` varchar(36)  DEFAULT NULL,
  `update_id_person` varchar(36)  DEFAULT NULL,
  `id_vehicle_type` varchar(36)  DEFAULT NULL,
  PRIMARY KEY (`id_vehicle`),
  KEY `FKjawwjrrcajcmhaep8pux5k7ar` (`creation_id_person`),
  KEY `FKk4up1tlvgc8dli6biiwncnj8c` (`id_organization`),
  KEY `FKp9e0rofh3igqdtdhoiorxoox5` (`update_id_person`),
  KEY `FKt33krnxux8hk8mi9kp67hks6j` (`id_vehicle_type`),
  CONSTRAINT `FKjawwjrrcajcmhaep8pux5k7ar` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKk4up1tlvgc8dli6biiwncnj8c` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`),
  CONSTRAINT `FKp9e0rofh3igqdtdhoiorxoox5` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKt33krnxux8hk8mi9kp67hks6j` FOREIGN KEY (`id_vehicle_type`) REFERENCES `vehicle_type` (`id_vehicle_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `vehicle_model`;
CREATE TABLE `vehicle_model` (
  `id_vehicle_model` varchar(36)  NOT NULL,
  `seat_columns` int DEFAULT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `seat_lines` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `creation_id_person` varchar(36)  DEFAULT NULL,
  `id_organization` varchar(36)  DEFAULT NULL,
  `update_id_person` varchar(36)  DEFAULT NULL,
  PRIMARY KEY (`id_vehicle_model`),
  KEY `FKlpjk7upsqi6wnklb1s59tll03` (`creation_id_person`),
  KEY `FK8jvahpswjpgd0kqnj55dpel5s` (`id_organization`),
  KEY `FKskmtykusdktg8y4quegg46sui` (`update_id_person`),
  CONSTRAINT `FK8jvahpswjpgd0kqnj55dpel5s` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`),
  CONSTRAINT `FKlpjk7upsqi6wnklb1s59tll03` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKskmtykusdktg8y4quegg46sui` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `vehicle_model_seat`;
CREATE TABLE `vehicle_model_seat` (
  `id_vehicle_model_seat` varchar(36)  NOT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `seat_number` int DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `x` int DEFAULT NULL,
  `y` int DEFAULT NULL,
  `creation_id_person` varchar(36)  DEFAULT NULL,
  `id_vehicle_model` varchar(36)  DEFAULT NULL,
  `update_id_person` varchar(36)  DEFAULT NULL,
  PRIMARY KEY (`id_vehicle_model_seat`),
  KEY `FKrwem58d8214qb7mqs3pffj9uq` (`creation_id_person`),
  KEY `FKj9g2mwfpv7kk0ngm58hssvlik` (`id_vehicle_model`),
  KEY `FKfgasb5jl6pat2j04tt6neksbc` (`update_id_person`),
  CONSTRAINT `FKfgasb5jl6pat2j04tt6neksbc` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKj9g2mwfpv7kk0ngm58hssvlik` FOREIGN KEY (`id_vehicle_model`) REFERENCES `vehicle_model` (`id_vehicle_model`),
  CONSTRAINT `FKrwem58d8214qb7mqs3pffj9uq` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `vehicle_seat`;
CREATE TABLE `vehicle_seat` (
  `id_vehicle_seat` varchar(36)  NOT NULL,
  `creation_client_id` varchar(255) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `seat_number` int DEFAULT NULL,
  `update_client_id` varchar(255) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `x` int DEFAULT NULL,
  `y` int DEFAULT NULL,
  `creation_id_person` varchar(36) DEFAULT NULL,
  `update_id_person` varchar(36) DEFAULT NULL,
  `id_vehicle` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id_vehicle_seat`),
  KEY `FKf9f4nlsdpgjdrqd8snyebbq3f` (`creation_id_person`),
  KEY `FK8dcmvaamtgke7ga45xtb3f026` (`update_id_person`),
  KEY `FKlv0ywf5mpsqn5i762tlhc8lix` (`id_vehicle`),
  CONSTRAINT `FK8dcmvaamtgke7ga45xtb3f026` FOREIGN KEY (`update_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKf9f4nlsdpgjdrqd8snyebbq3f` FOREIGN KEY (`creation_id_person`) REFERENCES `person` (`id_person`),
  CONSTRAINT `FKlv0ywf5mpsqn5i762tlhc8lix` FOREIGN KEY (`id_vehicle`) REFERENCES `vehicle` (`id_vehicle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `vehicle_type`;
CREATE TABLE `vehicle_type` (
  `id_vehicle_type` varchar(36) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_vehicle_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 2022-11-06 13:45:28