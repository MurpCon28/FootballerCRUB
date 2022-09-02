-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2019 at 11:56 AM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ca1`
--

-- --------------------------------------------------------

--
-- Table structure for table `footballer`
--

CREATE TABLE `footballer` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `team` varchar(150) NOT NULL,
  `position` varchar(100) NOT NULL,
  `goal` int(11) NOT NULL,
  `assists` int(11) NOT NULL,
  `skills` varchar(250) NOT NULL,
  `salary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `footballer`
--

INSERT INTO `footballer` (`id`, `name`, `team`, `position`, `goal`, `assists`, `skills`, `salary`) VALUES
(1, 'Buka Uka', 'Royal Palce', 'Defender', 3, 5, 'Right ', 30000),
(2, 'Vince', 'Dublin', 'Defender', 2, 3, 'Left', 44444444),
(3, 'Terry', 'Chelsea', 'Left Forward', 13, 26, 'Left Foot', 20000000),
(4, 'Mario', 'Liverpool', 'Mid Field', 2, 4, 'Left Free-Kicks', 40000),
(5, 'Kelly', 'Manchester City', 'Goalkeeper', 1, 3, 'Saves', 50000),
(6, 'Henry', 'Manchester City', 'Forward', 6, 10, 'Throws', 40000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `footballer`
--
ALTER TABLE `footballer`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `footballer`
--
ALTER TABLE `footballer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
