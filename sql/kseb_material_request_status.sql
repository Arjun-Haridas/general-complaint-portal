-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: kseb
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `material_request_status`
--

DROP TABLE IF EXISTS `material_request_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_request_status` (
  `material_request_status_id` int NOT NULL AUTO_INCREMENT,
  `material_request_id` int NOT NULL,
  `material_request_status_updated_date` datetime(6) DEFAULT NULL,
  `material_request_status` varchar(250) DEFAULT NULL,
  `material_request_status_updated_by` varchar(60) DEFAULT NULL,
  `material_request_status_details` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`material_request_status_id`),
  UNIQUE KEY `material_request_status_id_UNIQUE` (`material_request_status_id`),
  KEY `material_request_id_fk_idx` (`material_request_id`),
  CONSTRAINT `material_request_id_fk` FOREIGN KEY (`material_request_id`) REFERENCES `material_request` (`material_request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_request_status`
--

LOCK TABLES `material_request_status` WRITE;
/*!40000 ALTER TABLE `material_request_status` DISABLE KEYS */;
INSERT INTO `material_request_status` VALUES (8,13,'2024-03-26 18:30:00.000000','MDM Approval Waiting','Aravind','Waiting for approval from material depo manager');
/*!40000 ALTER TABLE `material_request_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-27 19:04:05
