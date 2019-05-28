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
-- Table structure for table `tx_history`
--

DROP TABLE IF EXISTS `tx_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `tx_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(244) NOT NULL,
  `amount` double NOT NULL DEFAULT '0',
  `date` datetime NOT NULL,
  `wall_id` int(11) NOT NULL,
  `card_no` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoybo33ufk7s6p38w5ba37qpvc` (`wall_id`),
  CONSTRAINT `FKoybo33ufk7s6p38w5ba37qpvc` FOREIGN KEY (`wall_id`) REFERENCES `wallet` (`id`),
  CONSTRAINT `wall_id` FOREIGN KEY (`wall_id`) REFERENCES `wallet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_history`
--

LOCK TABLES `tx_history` WRITE;
/*!40000 ALTER TABLE `tx_history` DISABLE KEYS */;
INSERT INTO `tx_history` VALUES (1,'customer@gmail.com',1000,'2019-01-25 14:40:25',1,1234567890123456),(2,'customer@gmail.com',640,'2019-01-25 14:41:19',1,0),(3,'lk.syadav59@gmail.com',1000,'2019-01-25 15:06:31',6,1234565434567898),(4,'lk.syadav59@gmail.com',640,'2019-01-25 15:07:19',6,0),(5,'customer@gmail.com',500,'2019-01-25 16:35:43',1,1234567890123456),(6,'customer@gmail.com',1000,'2019-01-28 10:07:45',1,1224587812365478),(7,'customer@gmail.com',640.6,'2019-01-28 13:03:59',1,0),(8,'customer@gmail.com',1023.48,'2019-01-28 16:37:52',1,1555565885665261),(9,'customer@gmail.com',1320.72,'2019-01-28 16:51:14',1,0),(10,'customer@gmail.com',604,'2019-01-28 17:49:41',1,0),(11,'lokesh.yadav@sapphireims.com',500,'2019-01-28 18:01:30',5,5515848212323123),(12,'lokesh.yadav@sapphireims.com',302,'2019-01-29 13:03:35',5,0),(13,'lk.syadav59@gmail.com',1000,'2019-01-29 13:08:51',6,4545445212454455),(14,'lk.syadav59@gmail.com',890,'2019-01-29 13:12:42',6,0),(15,'customer@gmail.com',1000,'2019-01-29 14:20:20',1,1221584552122158),(16,'customer@gmail.com',604,'2019-01-29 16:17:24',1,0),(17,'lokeshyadav5949@gamil.com',1000,'2019-01-30 11:19:17',7,1234567901234563),(18,'lokeshyadav5949@gamil.com',640.6,'2019-01-30 11:26:34',7,0);
/*!40000 ALTER TABLE `tx_history` ENABLE KEYS */;
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
