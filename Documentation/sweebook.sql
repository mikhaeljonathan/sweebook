-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 25, 2020 at 06:01 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sweebook`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` char(36) NOT NULL,
  `genre_id` char(36) NOT NULL,
  `title` varchar(255) NOT NULL,
  `isbn` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `genre_id`, `title`, `isbn`, `quantity`) VALUES
('f0946035-25e7-4f08-849b-0e6e470ea57d', '683b87f5-e918-4786-a1f7-9d2adffc46e6', 'Steve Jobs', '9781451648539', 70),
('98f911e6-bf22-441b-9bf0-5674221f088f', '33648981-249b-4f6e-bd1a-212924a67da8', 'Subtle Art of Not Giving a F*ck', '9780062641540', 98),
('4ea42d99-bc94-4fb6-acec-c3f40d9413f5', '2ee18ab5-2d6f-48ff-ab10-83d3ccb18f24', 'How to Win Friends', '9780091906351', 44),
('08eaec65-dac0-4e80-af2c-f2c59b1e6633', '114d0692-1139-4799-9b84-a0ab9949009e', 'Rich Dad Poor Dad', '9781543626612', 113),
('cafad92e-8260-4f54-a79a-6ecf39f537db', 'e169538b-7665-4251-afa4-49361265aaee', 'Introduction to Algorithms', '9780262033848', 98),
('389fef70-6381-45c0-922e-b57196bc63c9', '3f30c71e-a573-4aae-9944-8bb9531b31bc', 'A Brief History of Time', '9780553052435', 24),
('a5d3e9e8-af6c-4abe-b4c9-c63d904a739f', 'c38016d6-191c-453b-af46-8b54cbf0b1b4', 'The Power of Positive Thinking', '9780743234801', 97),
('354890a6-91ef-4d09-97fb-da8b6330ce52', '5be5b1d0-940c-4241-a48a-0eecd3de0539', 'Atomic Habits', '9780735211292', 99),
('129fc0f1-6961-4724-a390-4ce1b5dc9418', 'c824c1b3-aa42-4799-95fd-0c21fc41ebf1', 'Oxford Dictionary', '9780199571123', 22),
('0bf1629a-80c1-4ca4-b95d-eb0812437fb5', 'e900f1df-29ca-4598-91be-252d9d1591c4', 'The Purpose Driven Life', '9786028537599', 38),
('4640a884-c0b8-44ef-b273-bbb5e14ff0cb', 'c38016d6-191c-453b-af46-8b54cbf0b1b4', 'Sapiens', '9780099590088', 99),
('2566baa7-75ee-4ce7-8930-5194b3fd9542', 'c38016d6-191c-453b-af46-8b54cbf0b1b4', 'Homo Deus', '9780062464316', 100),
('721faa1c-4e82-4a56-b712-e0781fb75129', 'c38016d6-191c-453b-af46-8b54cbf0b1b4', 'Factfulness', '9781250107817', 100),
('55af868b-aff0-46bb-b0ae-52adaeedadb4', '5be5b1d0-940c-4241-a48a-0eecd3de0539', 'Who Moved My Cheese', '9780091883768', 11),
('f893223b-86e3-4264-858c-52f76354dbe2', '114d0692-1139-4799-9b84-a0ab9949009e', 'The Intelligent Investor', '9786025336324', 100);

-- --------------------------------------------------------

--
-- Table structure for table `borrows`
--

CREATE TABLE `borrows` (
  `id` char(36) NOT NULL,
  `member_id` char(36) NOT NULL,
  `status` varchar(50) NOT NULL,
  `borrow_timestamp` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borrows`
--

INSERT INTO `borrows` (`id`, `member_id`, `status`, `borrow_timestamp`) VALUES
('5daf800a-d36d-4a10-af50-9855b444673a', '32d31632-834e-442c-9611-c985ee92c7ca', 'Accepted', '2019-11-25 02:17:02'),
('9d0a0d56-acc6-41f0-93db-bb40f6c04c59', '2c271231-e678-4cda-b424-95f93942e7c8', 'Accepted', '2019-11-25 02:17:02'),
('ebce7648-0ccb-4f54-88bd-fe5de9e87e04', '2c271231-e678-4cda-b424-95f93942e7c8', 'Accepted', '2019-11-25 02:17:02'),
('cd1cfb73-1d05-4851-adb0-74766e818bf2', '2c271231-e678-4cda-b424-95f93942e7c8', 'Pending', '2019-12-25 02:17:02'),
('4fa5c537-3952-4061-985a-d4e65ca6bf57', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Accepted', '2019-12-25 02:17:02'),
('dbba9bcd-ae2e-40f8-8045-8291a971ff4d', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Accepted', '2019-12-25 02:17:02'),
('3e9e86d9-d38f-4d21-8e6d-e4efea834ec7', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Accepted', '2020-02-25 02:17:02'),
('debe00b3-0c31-44d1-b3f8-d2f48ccdddb4', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Accepted', '2020-03-25 02:17:02'),
('b4664e61-811e-4162-b9c2-6d67a6bdf178', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Accepted', '2020-04-25 02:17:02'),
('888c8d5e-9e17-4ed4-8423-665d8811e0f5', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Pending', '2020-05-25 02:17:02'),
('d66cee16-9498-41e7-81e9-5cc774c97538', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Pending', '2020-06-25 02:17:02'),
('f7b8d47f-b39a-4804-874e-a324ff90ebb6', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Pending', '2020-07-25 02:17:02'),
('61aa789c-2506-4d61-a0db-2c6610f7a5d0', 'ca7cada4-f054-465a-96df-dbaffba41e1e', 'Pending', '2020-08-25 02:22:31');

-- --------------------------------------------------------

--
-- Table structure for table `borrow_items`
--

CREATE TABLE `borrow_items` (
  `borrow_id` char(36) NOT NULL,
  `book_id` char(36) NOT NULL,
  `return_timestamp` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `borrow_items`
--

INSERT INTO `borrow_items` (`borrow_id`, `book_id`, `return_timestamp`) VALUES
('5daf800a-d36d-4a10-af50-9855b444673a', '4ea42d99-bc94-4fb6-acec-c3f40d9413f5', '2020-08-25 02:25:59'),
('5daf800a-d36d-4a10-af50-9855b444673a', 'f0946035-25e7-4f08-849b-0e6e470ea57d', '1990-01-01 05:00:00'),
('5daf800a-d36d-4a10-af50-9855b444673a', '08eaec65-dac0-4e80-af2c-f2c59b1e6633', '2020-08-25 02:26:01'),
('5daf800a-d36d-4a10-af50-9855b444673a', 'a5d3e9e8-af6c-4abe-b4c9-c63d904a739f', '1990-01-01 05:00:00'),
('5daf800a-d36d-4a10-af50-9855b444673a', '98f911e6-bf22-441b-9bf0-5674221f088f', '2020-08-25 02:26:10'),
('5daf800a-d36d-4a10-af50-9855b444673a', 'cafad92e-8260-4f54-a79a-6ecf39f537db', '2020-08-25 02:26:11'),
('5daf800a-d36d-4a10-af50-9855b444673a', '354890a6-91ef-4d09-97fb-da8b6330ce52', '2020-08-25 02:26:04'),
('5daf800a-d36d-4a10-af50-9855b444673a', '0bf1629a-80c1-4ca4-b95d-eb0812437fb5', '1990-01-01 05:00:00'),
('5daf800a-d36d-4a10-af50-9855b444673a', '129fc0f1-6961-4724-a390-4ce1b5dc9418', '2020-08-25 02:26:13'),
('5daf800a-d36d-4a10-af50-9855b444673a', '4640a884-c0b8-44ef-b273-bbb5e14ff0cb', '1990-01-01 05:00:00'),
('9d0a0d56-acc6-41f0-93db-bb40f6c04c59', 'f0946035-25e7-4f08-849b-0e6e470ea57d', '2020-08-25 02:47:10'),
('9d0a0d56-acc6-41f0-93db-bb40f6c04c59', '98f911e6-bf22-441b-9bf0-5674221f088f', '2020-08-25 02:47:12'),
('ebce7648-0ccb-4f54-88bd-fe5de9e87e04', '4ea42d99-bc94-4fb6-acec-c3f40d9413f5', '1990-01-01 05:00:00'),
('ebce7648-0ccb-4f54-88bd-fe5de9e87e04', '08eaec65-dac0-4e80-af2c-f2c59b1e6633', '1990-01-01 05:00:00'),
('cd1cfb73-1d05-4851-adb0-74766e818bf2', 'a5d3e9e8-af6c-4abe-b4c9-c63d904a739f', '1990-01-01 05:00:00'),
('cd1cfb73-1d05-4851-adb0-74766e818bf2', 'cafad92e-8260-4f54-a79a-6ecf39f537db', '1990-01-01 05:00:00'),
('cd1cfb73-1d05-4851-adb0-74766e818bf2', '389fef70-6381-45c0-922e-b57196bc63c9', '1990-01-01 05:00:00'),
('4fa5c537-3952-4061-985a-d4e65ca6bf57', 'f0946035-25e7-4f08-849b-0e6e470ea57d', '2020-08-25 02:47:48'),
('dbba9bcd-ae2e-40f8-8045-8291a971ff4d', '98f911e6-bf22-441b-9bf0-5674221f088f', '2020-08-25 02:47:52'),
('3e9e86d9-d38f-4d21-8e6d-e4efea834ec7', '4ea42d99-bc94-4fb6-acec-c3f40d9413f5', '1990-01-01 05:00:00'),
('debe00b3-0c31-44d1-b3f8-d2f48ccdddb4', '08eaec65-dac0-4e80-af2c-f2c59b1e6633', '1990-01-01 05:00:00'),
('b4664e61-811e-4162-b9c2-6d67a6bdf178', 'cafad92e-8260-4f54-a79a-6ecf39f537db', '1990-01-01 05:00:00'),
('888c8d5e-9e17-4ed4-8423-665d8811e0f5', '389fef70-6381-45c0-922e-b57196bc63c9', '1990-01-01 05:00:00'),
('d66cee16-9498-41e7-81e9-5cc774c97538', 'a5d3e9e8-af6c-4abe-b4c9-c63d904a739f', '1990-01-01 05:00:00'),
('f7b8d47f-b39a-4804-874e-a324ff90ebb6', '354890a6-91ef-4d09-97fb-da8b6330ce52', '1990-01-01 05:00:00'),
('61aa789c-2506-4d61-a0db-2c6610f7a5d0', '129fc0f1-6961-4724-a390-4ce1b5dc9418', '1990-01-01 05:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `user_id` char(36) NOT NULL,
  `salary` bigint(20) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`user_id`, `salary`, `status`) VALUES
('b209320f-943e-46ff-8da8-de5cbfdcd1af', 812887, 'Active'),
('2bf72eaa-68e1-4461-a424-b9154c317b3c', 10000, 'Active'),
('3080c06f-8741-454a-bc4f-036a8a3bfc56', 10000, 'Active'),
('5fae4a09-e580-4700-871a-956c29090607', 10000, 'Active'),
('3ada97f9-3da8-4034-a62b-aa95f1ceb0d3', 1001, 'Fired'),
('2103fd0c-49a6-4ab5-bc1a-10bc45c9b7c6', 10000, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `genres`
--

CREATE TABLE `genres` (
  `id` char(36) NOT NULL,
  `type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `genres`
--

INSERT INTO `genres` (`id`, `type`) VALUES
('2ee18ab5-2d6f-48ff-ab10-83d3ccb18f24', 'Action and Adventure'),
('683b87f5-e918-4786-a1f7-9d2adffc46e6', 'Biographies'),
('049acce0-e2ac-427d-93d9-2b0c8a2752b7', 'Comic'),
('a12c77a2-82d8-428f-8224-c9bca655f802', 'Cookbooks'),
('812ad8e7-8b2a-487f-a9de-9167feda37d9', 'Horror'),
('33648981-249b-4f6e-bd1a-212924a67da8', 'Self Help'),
('c38016d6-191c-453b-af46-8b54cbf0b1b4', 'Non-Fiction'),
('114d0692-1139-4799-9b84-a0ab9949009e', 'Business'),
('5be5b1d0-940c-4241-a48a-0eecd3de0539', 'Psychology'),
('3f30c71e-a573-4aae-9944-8bb9531b31bc', 'Science'),
('e900f1df-29ca-4598-91be-252d9d1591c4', 'Religion'),
('f582c6a3-9d1d-4d98-92fa-7607cccffe38', 'Imported'),
('c824c1b3-aa42-4799-95fd-0c21fc41ebf1', 'Dictionary'),
('f44034b4-32ee-46bb-8949-de3525274230', 'Map'),
('e169538b-7665-4251-afa4-49361265aaee', 'Computer Science');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` char(36) NOT NULL,
  `address` text NOT NULL,
  `member_since` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`user_id`, `address`, `member_since`) VALUES
('ccf8f721-a92a-4aad-a1d5-4824565fc11c', 'Dummy Address', '2020-07-27 06:15:06'),
('ca7cada4-f054-465a-96df-dbaffba41e1e', 'Surabaya', '2020-08-25 00:53:25'),
('32d31632-834e-442c-9611-c985ee92c7ca', 'Semarang', '2020-08-25 00:55:10'),
('2c271231-e678-4cda-b424-95f93942e7c8', 'Pontianak', '2020-08-25 00:57:42');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` char(36) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
('0acac7fa-59b7-4555-8201-34d1a03f9fb8', 'Administrator'),
('d9390fe5-de7a-428e-b0e6-a1c08e39b913', 'Human Capital'),
('64da7812-7198-4543-8449-863bbf5bd374', 'Manager'),
('e90dd78e-5ace-4fb4-afef-58d950997757', 'Membership'),
('5a7634a6-c065-4482-9359-f24bbb10627c', 'Purchasing');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` char(36) NOT NULL,
  `role_id` char(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `role_id`, `name`, `username`, `password`, `gender`) VALUES
('b209320f-943e-46ff-8da8-de5cbfdcd1af', '64da7812-7198-4543-8449-863bbf5bd374', 'Manager', 'manager', '1a8565a9dc72048ba03b4156be3e569f22771f23', 'Male'),
('ccf8f721-a92a-4aad-a1d5-4824565fc11c', 'e90dd78e-5ace-4fb4-afef-58d950997757', 'Member', 'member', '6467baa3b187373e3931422e2a8ef22f3e447d77', 'Male'),
('ca7cada4-f054-465a-96df-dbaffba41e1e', 'e90dd78e-5ace-4fb4-afef-58d950997757', 'Mikhael Jonathan', 'mkhljnthn', '0f95c0f484c2f275ac6625d4cf22015c263aa2e7', 'Male'),
('32d31632-834e-442c-9611-c985ee92c7ca', 'e90dd78e-5ace-4fb4-afef-58d950997757', 'Agivrian Triputra', 'agvrn', '3d785f5e66a12c66189e3bd9123fc449312a10c3', 'Male'),
('2c271231-e678-4cda-b424-95f93942e7c8', 'e90dd78e-5ace-4fb4-afef-58d950997757', 'Anthony Wijaya', 'anthon', '8eb0900fb3ffa851392ca2298668cd1d725640f6', 'Male'),
('2bf72eaa-68e1-4461-a424-b9154c317b3c', '5a7634a6-c065-4482-9359-f24bbb10627c', 'Purchasing', 'purchasing', '0e0730d60c07bff650576eb44f31e96809633aaa', 'Male'),
('3080c06f-8741-454a-bc4f-036a8a3bfc56', 'd9390fe5-de7a-428e-b0e6-a1c08e39b913', 'Human Capital', 'hc', '978c9f3f747bbf4e088d49ebc3ffbb4df95cbbd9', 'Male'),
('5fae4a09-e580-4700-871a-956c29090607', '0acac7fa-59b7-4555-8201-34d1a03f9fb8', 'Administrator', 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'Male'),
('3ada97f9-3da8-4034-a62b-aa95f1ceb0d3', '5a7634a6-c065-4482-9359-f24bbb10627c', 'Dummy Employee', 'dummyemployee', '940d7597b37b470a82fdc49c9a583a4b9376cefe', 'Male'),
('2103fd0c-49a6-4ab5-bc1a-10bc45c9b7c6', 'd9390fe5-de7a-428e-b0e6-a1c08e39b913', 'Pending Employee', 'pendingemployee', 'c4e9355a23158f84f6b2353b00af206803925903', 'Male');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
