CREATE DATABASE  IF NOT EXISTS `customer` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;
USE `customer`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: customer
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orderhistory`
--

DROP TABLE IF EXISTS `orderhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `orderhistory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Date` datetime NOT NULL,
  `location` varchar(45) NOT NULL,
  `food_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `orderby_id` varchar(255) NOT NULL,
  `cost` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `food_id_idx` (`food_id`),
  CONSTRAINT `FKq6j7unotbnd5jtufwsttrdwn4` FOREIGN KEY (`food_id`) REFERENCES `product` (`id`),
  CONSTRAINT `food_id` FOREIGN KEY (`food_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderhistory`
--

LOCK TABLES `orderhistory` WRITE;
/*!40000 ALTER TABLE `orderhistory` DISABLE KEYS */;
INSERT INTO `orderhistory` VALUES (1,'2019-01-23 16:21:14','roopena',5,1,'customer@gmail.com',129),(2,'2019-01-23 16:31:25','Silk board',1,2,'lokesh.yadav@sapphireims.com',440),(3,'2019-01-24 14:50:12','Silk board',7,3,'customer@gmail.com',840),(4,'2019-01-25 12:38:19','Roopena',7,5,'customer@gmail.com',2225),(5,'2019-01-25 12:59:10','roopena',7,2,'customer@gmail.com',890),(6,'2019-01-25 13:03:24','roopena',4,3,'customer@gmail.com',960),(7,'2019-01-25 14:41:20','roopena',4,2,'customer@gmail.com',640),(8,'2019-01-25 15:07:19','Silkboard',4,2,'lk.syadav59@gmail.com',640),(9,'2019-01-28 13:03:59','Roopena',4,2,'customer@gmail.com',640.6),(10,'2019-01-28 16:51:14','Silk board',2,3,'customer@gmail.com',1320.72),(11,'2019-01-28 17:49:41','Jay nagar',6,2,'customer@gmail.com',604),(12,'2019-01-29 13:03:35','Silk Board',6,1,'lokesh.yadav@sapphireims.com',302),(13,'2019-01-29 13:12:42','Silk board',7,2,'lk.syadav59@gmail.com',890),(14,'2019-01-29 16:17:24','Jay nagra',6,2,'customer@gmail.com',604),(15,'2019-01-30 11:26:35','roopena',4,2,'lokeshyadav5949@gamil.com',640.6);
/*!40000 ALTER TABLE `orderhistory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-31 12:18:58
