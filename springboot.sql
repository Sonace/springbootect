-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 18, 2018 at 12:26 PM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `springboot`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertInfor` (IN `username` VARCHAR(255), IN `pass_word` VARCHAR(255), IN `bod` DATE, IN `phonenumber` INT, IN `email` VARCHAR(255), IN `fax` INT)  BEGIN
   DECLARE userid INT DEFAULT 0;
   INSERT INTO _user (user_name, pass_word, bod) VALUES (username, pass_word, bod);
   set  userid=LAST_INSERT_ID();
   INSERT INTO address (user_id,phone_number,email,fax) VALUES (userid, phonenumber, email, fax);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `tinhTong` ()  BEGIN
    DECLARE a INT DEFAULT 0;
   	INSERT INTO _user (user_name, pass_word, bod) VALUES ('a', 'b', '12-12-2018');
    SET a = LAST_INSERT_ID();
    
    INSERT INTO address (user_id,phone_number,email,fax) VALUES (a, 123, 'asdasdasd',123123);
   
     
    SELECT a;
     
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_id`, `amount`, `user_id`) VALUES
(1, 10000000, 1),
(2, 10000000, 6),
(3, 213213, 9);

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `user_id` int(11) DEFAULT NULL,
  `phone_number` int(11) DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fax` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`user_id`, `phone_number`, `email`, `fax`) VALUES
(27, 123, 'asdasdasd', 123123),
(28, 123123, 'asdasd', 123123),
(29, 123123, 'qweqwe', 123123),
(30, 123123, 'qweqwe', 123123),
(31, 213312, 'afasdfasd', 23123123),
(32, 213312, 'afasdfasd', 23123123),
(33, 1627516125, 'sonace264@gmail.com', 1627516125),
(34, 1627516125, 'sonace264@gmail.com', 1627516125),
(35, 1627516125, 'sonace264@gmail.com', 1627516125),
(36, 1627516125, 'sonace264@gmail.com', 1627516125),
(37, 1627516125, 'sonace264@gmail.com', 1627516125);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'ROLE_GEST'),
(2, 'ROLE_ADMIN'),
(3, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `role_id`, `user_id`) VALUES
(1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `_user`
--

CREATE TABLE `_user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `pass_word` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `bod` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `_user`
--

INSERT INTO `_user` (`user_id`, `user_name`, `pass_word`, `bod`) VALUES
(1, 'son@gmail.com', '$2a$10$Ze6P.bQe5YZ3bQvnnxgQ9uqcIB/MtDFTDagyFFheXAb.UfnHKXbFy', '2018-05-09'),
(4, 'qweqwe', 'asdasdasd', '2017-05-05'),
(5, 'qweqwe', 'asdasdasd', '2017-05-05'),
(6, 'qweqwe', 'asdasdasd', '2018-05-31'),
(7, 'ngu0is0i97', 'asdasdasdsadasdsadsaddssdsadsadsda', '2018-05-25'),
(8, 'ngu0is0i97', '$2a$10$u8fMMgXZSMPVfrhIcn02MeIJDbbwMdfVlfQSEMqw4HgOAlQ2p20I2', '2018-05-19'),
(9, 'ngu0is0i97', '$2a$10$QACqOtr7RhxQWZQaZMGzuOsPYxdJ9iAuD64uBPJw0NmKZJjA3jqQS', '2018-06-16'),
(10, 'ngu0is0i97', '$2a$10$ByoWqgWBH49SJ6HsmhYWIemLb7Quh/ZBozVQ74tvt/SjnVpwt/pge', '2018-06-15'),
(11, 'ngu0is0i97', '$2a$10$3MFslYmfzjn1tbuSbpNF7eV5e1PgVDThomjBPbJfsoqxjX6MUgjsq', '2018-06-15'),
(12, 'ngu0is0i97', '$2a$10$GOV/VSYPpNcNvvLqwLBZCe/1mh0fkaouOWF7pUP0S14xig4/MeUem', '2018-06-15'),
(13, 'ngu0is0i97', '$2a$10$lJuxjmR.J.d6qy42YvAlleNsSHcDBs.iDNkcRJzfOqdtdzb6v7q3q', '2018-06-15'),
(14, 'ngu0is0i97', '$2a$10$ZfUcc0OICcwFTA.WoABhmev.I/No6GivVZ5I5DhQiIAuIrFg8gLce', '2018-06-15'),
(15, 'ngu0is0i97', '$2a$10$6JJakC4A.Cpyy8EzvKPwResnGZWYszsrgIitz/kNORyUDyqOZB4iW', '2018-06-15'),
(16, 'ngu0is0i97', '$2a$10$vcSlmwNxDBclOj4pcjB3Dugo/I84Ju7SnXuuXWGynFg22/JS5eRx2', '2018-06-15'),
(17, 'ngu0is0i97', '$2a$10$.iEsgiXjZO/OvT..yGYBee3kfRIucuo6Du1/kC/bARWBH1dkVyId.', '2018-06-14'),
(18, 'ngu0is0i97', '$2a$10$VvlfFcjYIt204ntpqUJmOeNbFd/0MQ4cWJR53Sutxy.D8Om/BLDuq', '2018-06-16'),
(19, 'ngu0is0i97', '$2a$10$XadIRSrklYiLvZpNe4NTR.na1/sFEUaeYOCM2ZAdx8EukBjQrwuxu', '2018-06-07'),
(20, 'ngu0is0i97', '$2a$10$FIEWhXiiLOq8tXSMXN0L1ePaV/TLUhLy4kKWSLONdIEKUdYjBWwl6', '2018-06-16'),
(21, 'ngu0is0i97', '$2a$10$95ABtbSF8jRsmf9RWBvo1eOXeNWx7/73tiSrN8EwcWbTWc1ydze8C', '2018-06-16'),
(22, 'ngu0is0i97', '$2a$10$477LhIcKgbxLpy7s.N2BPu.J4BtqOphb6T7HbG/OZyooFkUs84J.e', '2018-06-16'),
(23, 'ngu0is0i97', '$2a$10$Q7dOOOKFbKDBV3D2dFCsFuhL5aoM387px3WMuCk06qfD7iQAsThuy', '2018-06-06'),
(24, 'ngu0is0i97', '$2a$10$Yh.xMlONq2Pw5bvY5hJxYeiXp3e1MHQAh415OPysm2CcyaY5nU8FK', '2018-06-07'),
(25, 'ngu0is0i97', '$2a$10$tG/7sQ7nA7kFk6OTv0PoYegQ9XghNJ3lVzNRHGHF8ba/NContVMcG', '2018-06-16'),
(26, 'ngu0is0i97', '$2a$10$AJa.Jg/m3AaJdoT6CM5Zy.qrilsic.q0iAZaGkT/644mwc8FakZWi', '2018-06-15'),
(31, 'qweqwe', '$2a$10$TZJywTeELB3jbXdcp6zFiOfOJCTGbY9/hgyfTP3ewtxnSqcuT527y', '2018-06-04'),
(32, 'qweqwe', '$2a$10$azd2IUJFmlq8B9GSZsBtGesGlVSRw.uoXw/yYvlK4vqMf4Cjo8n5i', '2018-06-04'),
(33, 'ngu0is0i97', '$2a$10$noLzDmJTlTv5/P9zO3dRgewhETgBFZ25vbDYp1T/sjIV0oTi5wXPK', '2018-06-15'),
(34, 'ngu0is0i97', '$2a$10$S3iNoeA4gP9dKsR5FnQRO.X7RdBsiAV9LoZdKe./HCFLYgvw4OznS', '2018-06-15'),
(35, 'ngu0is0i971', '$2a$10$jIk21.czpUJHYoJjx47m2O84WcU1V0h9avXYs27./NgQ835zj1qzy', '2018-06-16'),
(36, 'ngu0is0i97123123', '$2a$10$6asC.jejntBAvbK.xwwWSu6kKyDomv99WZffQmMJGHnuEcqCuWq72', '2018-06-15'),
(37, 'ngu0is0i9732423423', '$2a$10$Azp14AuKAwLoKHuzltRYi.pvhDzrwJSoTLT2DuMfz/NEzzIpmDpxi', '2018-06-15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `_user`
--
ALTER TABLE `_user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `_user`
--
ALTER TABLE `_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
