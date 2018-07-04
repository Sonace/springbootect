-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2018 at 11:52 AM
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertTable` (IN `username` VARCHAR(255), IN `pass_word` VARCHAR(255), IN `bod` DATE, IN `phonenumber` INT, IN `email` VARCHAR(255), IN `amount` FLOAT)  BEGIN
   DECLARE userid INT DEFAULT 0;
   INSERT INTO _user (user_name, pass_word, bod) VALUES (username, pass_word, bod);
   set  userid=LAST_INSERT_ID();
   INSERT INTO address (user_id,phone_number,email) VALUES (userid, phonenumber, email);

   INSERT INTO account (amount,user_id) VALUES (amount, userid);
   
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `tinhTong` ()  BEGIN
    DECLARE a INT DEFAULT 0;
   	INSERT INTO _user (user_name, pass_word, bod) VALUES ('a', 'b', '12-12-2018');
    SET a = LAST_INSERT_ID();
    
    INSERT INTO address (user_id,phone_number,email,fax) VALUES (a, 123, 'asdasdasd',123123);
   
     
    SELECT a;
     
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTable` (IN `userid` INT, IN `username` VARCHAR(255), IN `bod` DATE, IN `phonenumber` INT, IN `email` VARCHAR(255), IN `amount` FLOAT)  BEGIN
  
  
   UPDATE _user SET user_name =username, bod= bod  WHERE user_id =userid;
   
   UPDATE address SET email =email, phone_number= phonenumber WHERE user_id =userid;
   
   UPDATE account SET amount =amount WHERE user_id =userid;
   
  
   
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
(3, 213213, 9),
(6, 0, 113);

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
(37, 1627516125, 'sonace264@gmail.com', 1627516125),
(38, 1627516125, 'sonace264@gmail.com', 1627516125),
(61, 1627516125, 'sonace264@gmail.com', 1627516125),
(90, 1627516125, 'son97.it@gmail.com', 0),
(108, 1627516125, 'ngu0is0i97', 0),
(113, 356145081, 'ngu0is0i97', NULL),
(114, 356145081, 'saaaaa', 0);

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
(1, 2, 38);

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
(38, 'ngu0is0i97', '$2a$10$qcLeSOxDit5O7JqT7lVSgOwQOcDwA5dIA8peUM6OrPMTSTTYy.7Oq', '1997-04-26'),
(61, 'ngu0is0i97aweqwe', '$2a$10$OdarBy1rXDtfeTOJ337BkepSqteBMCL4vXUVNggCbT8.MV5KdwHNK', '2018-06-14'),
(90, 'https://www.youtube.com/watch?v=0dVDj3l1AcM', '$2a$10$jOoNmpgiWx4iGudYHpG4XOXRP52/1BY0Nv8N4p.d0svp.7TG6HhwK', '2018-06-06'),
(108, 'ngovanson', '$2a$10$X34wJKww9pHsHGkM7ceNHOGala./cHAIFbdITspDbERiIyvaOmLjy', '2018-06-06'),
(113, 'xzxzxzxzxz', 'asdasd', '2018-06-06'),
(114, 'saaaaaaaa', '$2a$10$xEAf0ej5/uJ7fHfOjDxd.er18z25ZUu.4EqNdCwhDpt.Yspy8x/RC', '2018-06-06');

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
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=115;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
