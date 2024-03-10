-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 07:14 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`proj_soft` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `proj_soft`;

--
-- Database: `proj_soft`
--

-- --------------------------------------------------------

--
-- Table structure for table `application_form`
--

CREATE TABLE `application_form` (
  `AthleteID` int(11) NOT NULL,
  `CompetitionID` int(11) NOT NULL,
  `DisciplineID` int(11) NOT NULL,
  `Performance` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `athlete`
--

CREATE TABLE `athlete` (
  `ID` int(11) NOT NULL,
  `Firstname` varchar(255) NOT NULL,
  `Lastname` varchar(255) NOT NULL,
  `Birthday` date NOT NULL,
  `State` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `competition`
--

CREATE TABLE `competition` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `DateStart` date DEFAULT NULL,
  `DateEnd` date DEFAULT NULL,
  `TypeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `competition_discipline`
--

CREATE TABLE `competition_discipline` (
  `CompetitionID` int(11) NOT NULL,
  `DisciplineID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `competition_discipline`
--

-- --------------------------------------------------------

--
-- Table structure for table `competition_type`
--

CREATE TABLE `competition_type` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `competition_type`
--

INSERT INTO `competition_type` (`ID`, `Name`) VALUES
(2, 'Balkansko prvenstvo'),
(6, 'Dijamantska liga'),
(1, 'Državno prvenstvo'),
(3, 'Evropsko prvenstvo'),
(7, 'Mitinzi'),
(5, 'Olimpijada'),
(4, 'Svetsko prvenstvo');

-- --------------------------------------------------------

--
-- Table structure for table `discipline`
--

CREATE TABLE `discipline` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `CategoryID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `discipline`
--

INSERT INTO `discipline` (`ID`, `Name`, `CategoryID`) VALUES
(1, '60m', 1),
(2, '100m', 1),
(3, '200m', 1),
(4, '400m', 1),
(5, '800m', 2),
(6, '1500m', 2),
(7, '3000m', 3),
(8, '5000m', 3),
(9, '10000m', 3),
(10, '110m prepone', 4),
(11, '100m prepone', 4),
(12, '400m prepone', 4),
(13, 'Skok udalj', 5),
(14, 'Skok uvis', 5),
(15, 'Troskok', 5),
(16, 'Skok motkom', 5),
(17, 'Bacanje kugle', 6),
(18, 'Bacanje kladiva', 6),
(19, 'Bacanje koplja', 6),
(20, 'Bacanje diska', 6),
(21, '20km brzo hodanje', 7),
(22, '50km brzo hodanje', 7),
(23, '4x100m', 8),
(24, '4x400m', 8),
(25, 'Desetoboj', 9),
(26, 'Sedmoboj', 9);

-- --------------------------------------------------------

--
-- Table structure for table `discipline_category`
--

CREATE TABLE `discipline_category` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `discipline_category`
--

INSERT INTO `discipline_category` (`ID`, `Name`) VALUES
(6, 'Bacanja'),
(7, 'Brzo hodanje'),
(3, 'Duge pruge'),
(4, 'Prepone'),
(5, 'Skokovi'),
(1, 'Sprint'),
(2, 'Srednje pruge'),
(8, 'Štafete'),
(9, 'Višeboj');

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `id` int(11) NOT NULL,
  `beforeState` varchar(255) DEFAULT NULL,
  `afterState` varchar(255) DEFAULT NULL,
  `createdAt` datetime DEFAULT current_timestamp(),
  `userId` int(11) DEFAULT NULL,
  `operationId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `operation`
--

CREATE TABLE `operation` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `operation`
--

INSERT INTO `operation` (`ID`, `Name`) VALUES
(1, 'LOGIN'),
(2, 'LOGOUT'),
(3, 'ADD_ATHLETE'),
(4, 'UPDATE_ATHLETE'),
(5, 'DELETE_ATHLETE'),
(6, 'ADD_COMPETITION'),
(7, 'UPDATE_COMPETITION'),
(8, 'ADD_TIMETABLE'),
(9, 'ADD_APPLICATION_FORM');

-- --------------------------------------------------------

--
-- Table structure for table `time_table`
--

CREATE TABLE `time_table` (
  `DisciplineID` int(11) NOT NULL,
  `CompetitionID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `MaintenanceDate` date DEFAULT NULL,
  `MaintenanceTime` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `Firstname` varchar(255) NOT NULL,
  `Lastname` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `Firstname`, `Lastname`, `Username`, `Password`) VALUES
(1, 'Pera', 'Peric', 'pera', 'pera');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `application_form`
--
ALTER TABLE `application_form`
  ADD PRIMARY KEY (`AthleteID`,`CompetitionID`,`DisciplineID`),
  ADD KEY `CompetitionID` (`CompetitionID`),
  ADD KEY `DisciplineID` (`DisciplineID`);

--
-- Indexes for table `athlete`
--
ALTER TABLE `athlete`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `competition`
--
ALTER TABLE `competition`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD KEY `TypeID` (`TypeID`);

--
-- Indexes for table `competition_discipline`
--
ALTER TABLE `competition_discipline`
  ADD PRIMARY KEY (`CompetitionID`,`DisciplineID`);

--
-- Indexes for table `competition_type`
--
ALTER TABLE `competition_type`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `discipline`
--
ALTER TABLE `discipline`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD KEY `CategoryID` (`CategoryID`);

--
-- Indexes for table `discipline_category`
--
ALTER TABLE `discipline_category`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `operationId` (`operationId`);

--
-- Indexes for table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `time_table`
--
ALTER TABLE `time_table`
  ADD PRIMARY KEY (`DisciplineID`,`CompetitionID`,`Name`) USING BTREE,
  ADD KEY `CompetitionID` (`CompetitionID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `athlete`
--
ALTER TABLE `athlete`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `competition`
--
ALTER TABLE `competition`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `competition_type`
--
ALTER TABLE `competition_type`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `discipline`
--
ALTER TABLE `discipline`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `discipline_category`
--
ALTER TABLE `discipline_category`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `operation`
--
ALTER TABLE `operation`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `application_form`
--
ALTER TABLE `application_form`
  ADD CONSTRAINT `application_form_ibfk_1` FOREIGN KEY (`AthleteID`) REFERENCES `athlete` (`ID`),
  ADD CONSTRAINT `application_form_ibfk_2` FOREIGN KEY (`CompetitionID`) REFERENCES `competition` (`ID`),
  ADD CONSTRAINT `application_form_ibfk_3` FOREIGN KEY (`DisciplineID`) REFERENCES `discipline` (`ID`);

--
-- Constraints for table `competition`
--
ALTER TABLE `competition`
  ADD CONSTRAINT `competition_ibfk_1` FOREIGN KEY (`TypeID`) REFERENCES `competition_type` (`ID`);

--
-- Constraints for table `discipline`
--
ALTER TABLE `discipline`
  ADD CONSTRAINT `discipline_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `discipline_category` (`ID`);

--
-- Constraints for table `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`operationId`) REFERENCES `operation` (`ID`);

--
-- Constraints for table `time_table`
--
ALTER TABLE `time_table`
  ADD CONSTRAINT `time_table_ibfk_1` FOREIGN KEY (`DisciplineID`) REFERENCES `discipline` (`ID`),
  ADD CONSTRAINT `time_table_ibfk_2` FOREIGN KEY (`CompetitionID`) REFERENCES `competition` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
