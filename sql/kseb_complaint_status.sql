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
-- Table structure for table `complaint_status`
--

DROP TABLE IF EXISTS `complaint_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint_status` (
  `complaint_status_id` int NOT NULL AUTO_INCREMENT,
  `complaint_id` int NOT NULL,
  `complaint_status_updated_date` datetime(6) DEFAULT NULL,
  `complaint_status` varchar(45) DEFAULT NULL,
  `complaint_status_updated_by` int NOT NULL,
  `complaint_status_details` varchar(45) DEFAULT NULL,
  `selected_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`complaint_status_id`),
  UNIQUE KEY `complaint_status_id_UNIQUE` (`complaint_status_id`),
  KEY `complaint_id_fk_idx` (`complaint_id`),
  CONSTRAINT `complaint_id_fk` FOREIGN KEY (`complaint_id`) REFERENCES `complaint` (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint_status`
--

LOCK TABLES `complaint_status` WRITE;
/*!40000 ALTER TABLE `complaint_status` DISABLE KEYS */;
INSERT INTO `complaint_status` VALUES (28,38,'2024-03-25 18:30:00.000000','PENDING',1,'Complaint Pending',NULL),(29,39,'2024-03-25 18:30:00.000000','PENDING',1,'Complaint Pending',NULL),(31,41,'2024-03-26 18:30:00.000000','PENDING',1,'Complaint Pending',NULL),(33,43,'2024-03-26 18:30:00.000000','PENDING',1,'Complaint Pending',NULL);
/*!40000 ALTER TABLE `complaint_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-27 19:03:57
