-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hostel
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `prn` varchar(45) NOT NULL,
  `FName` varchar(45) NOT NULL,
  `LName` varchar(45) NOT NULL,
  `Year` int NOT NULL,
  `roomNumber` varchar(45) NOT NULL,
  `contactNumber` varchar(45) NOT NULL,
  `paymentDue` tinyint(1) DEFAULT NULL,
  `paymentAmount` float DEFAULT NULL,
  `departureDate` date DEFAULT NULL,
  `arrivalDate` date DEFAULT NULL,
  `leaveReason` varchar(180) DEFAULT NULL,
  `leaveApproved` tinyint(1) DEFAULT NULL,
  `requestHousekeeping` tinyint(1) DEFAULT NULL,
  `feedback` varchar(225) DEFAULT NULL,
  `roomChange` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`prn`),
  UNIQUE KEY `contactNumber_UNIQUE` (`contactNumber`),
  UNIQUE KEY `prn_UNIQUE` (`prn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('19070122005','Abhijeet','Singh',2019,'A507','9089081800',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('19070122006','Abhinav','Mishra',2019,'B420','1234567890',0,0,'2021-05-24','2021-05-26','medical emergency',1,NULL,NULL,NULL),('19070122010','Akshaj','Tammewar',2019,'A507','9000765871',0,0,'2021-05-25','2021-05-26','Medical',0,NULL,'Electrical Problems',NULL),('190701220120','Amaan','Mithani',2020,'A505','1231231800',1,10000,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('19070122014','Akshay','Jaiswal',2021,'D506','1237894560',0,0,'2021-05-23','2021-05-28','Going home',1,1,NULL,1),('19070122015','Aman','Shaikh',2019,'E102','6969426801',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('19070122019','Anant','Sinha',2019,'F403','4206941012',0,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-01 20:51:37
