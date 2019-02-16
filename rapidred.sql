-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 15, 2019 at 01:24 PM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rapidred`
--

-- --------------------------------------------------------

--
-- Table structure for table `donate`
--

DROP TABLE IF EXISTS `donate`;
CREATE TABLE IF NOT EXISTS `donate` (
  `phone` decimal(50,0) NOT NULL,
  UNIQUE KEY `phone` (`phone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `donate`
--

INSERT INTO `donate` (`phone`) VALUES
('7996797409'),
('8884147122'),
('9036343205'),
('9901405208'),
('9916358797'),
('9916358877');

-- --------------------------------------------------------

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
CREATE TABLE IF NOT EXISTS `login_details` (
  `name` varchar(50) DEFAULT NULL,
  `phone` decimal(50,0) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(20) NOT NULL,
  `age` int(10) NOT NULL,
  `blood_group` varchar(50) NOT NULL,
  `address` varchar(500) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login_details`
--

INSERT INTO `login_details` (`name`, `phone`, `email`, `gender`, `age`, `blood_group`, `address`, `password`) VALUES
('Hari Prasad K', '9916358797', 'prasadraj9220@gmail.com', 'Male', 20, 'B+', 'G-03,manjusree Building, 1st Main Road, 1st Cross Rd, Dollar Scheme Colony, 1st Stage, BTM Layout, Bengaluru, Karnataka 560068, India', 'hari'),
('Anup Kumar', '7996797409', 'anup99886@gmail.com', 'Male', 19, 'B+', 'G-03,manjusree Building, 1st Main Road, 1st Cross Rd, Dollar Scheme Colony, 1st Stage, BTM Layout, Bengaluru, Karnataka 560068, India', 'akb'),
('Alen S Thomas', '8884147122', 'alena106@gmail.com', 'Male', 20, 'AB+', '1/4, 1st Main Rd, Madiwala, 1st Stage, BTM Layout, Bengaluru, Karnataka 560068, India', 'alen'),
('Arpitha Ganesh', '9901405208', 'arpithaganesh17@gmail.com', 'Male', 20, 'O+', 'Banashankari Stage II, Banashankari, Bengaluru, Karnataka 560070, India', 'arpitha'),
('Anup Kumar', '7996797408', 'arpithtaganesh17@gmail.com', 'Female', 55, 'AB+', 'hsjhsb', 'akakakak'),
('alalal', '1234567899', 'akak@aka.com', 'Male', 12, 'AB-', 'jdhrnfjfjht', 'md5(akakakak)'),
('hahaha', '1212121212', 'a@a.c', 'Male', 65, 'B-', 'hshdhrjrjt', 'afe5d0bf6f2d019b9cf7142bfdcdbe65'),
('hahahahahahah', '1111111111', 'a@j.d', 'Male', 26, 'AB-', 'jsjfhrjfjtkfkt', 'e219b56989281a7846dd836161d7a2bd'),
('Abc', '9916358877', 'prasadraj9220@gmail.com', 'Male', 30, 'O+', 'The Belair, 320-321, Silver Springs Layout, Spice Garden Compound Road, Kudlu Gate, Srinivasa Nagar, Hal Layout, Singasandra, Bengaluru, Karnataka 560037, India', 'anupanup'),
('Gautam Gobinda', '9036343205', 'rockgautam16@gmail.com', 'Male', 20, 'O+', 'Unnamed Road, Jay Bheema Nagar, 1st Stage, BTM Layout 1, Bengaluru, Karnataka 560068, India', 'gautam1234');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
