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
-- Table structure for table `work_allocation`
--

DROP TABLE IF EXISTS `work_allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_allocation` (
  `work_alloc_id` int NOT NULL AUTO_INCREMENT,
  `complaint_id` int DEFAULT NULL,
  `staff_id` int DEFAULT NULL,
  `work_alloc_date` datetime(6) DEFAULT NULL,
  `complaint` varchar(255) DEFAULT NULL,
  `staff_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`work_alloc_id`),
  UNIQUE KEY `work_allocation_id_UNIQUE` (`work_alloc_id`),
  KEY `complaint_id_fk_idx` (`complaint_id`),
  KEY `staff_id_fk_idx` (`staff_id`),
  CONSTRAINT `staff_id_fk` FOREIGN KEY (`staff_id`) REFERENCES `staff_personal` (`staff_id`),
  CONSTRAINT `work_complaint_id_fk` FOREIGN KEY (`complaint_id`) REFERENCES `complaint` (`complaint_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_allocation`
--

LOCK TABLES `work_allocation` WRITE;
/*!40000 ALTER TABLE `work_allocation` DISABLE KEYS */;
INSERT INTO `work_allocation` VALUES (8,38,2,'2024-03-26 18:30:00.000000',NULL,NULL),(9,39,4,'2024-03-26 18:30:00.000000',NULL,NULL),(10,41,5,'2024-03-26 18:30:00.000000',NULL,NULL);
/*!40000 ALTER TABLE `work_allocation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-27 19:03:59
