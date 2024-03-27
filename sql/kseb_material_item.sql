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
-- Table structure for table `material_item`
--

DROP TABLE IF EXISTS `material_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material_item` (
  `material_item_id` int NOT NULL AUTO_INCREMENT,
  `material_brand_id` int DEFAULT NULL,
  `material_type_id` int DEFAULT NULL,
  `material_item_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`material_item_id`),
  UNIQUE KEY `material_item_id_UNIQUE` (`material_item_id`),
  KEY `material_brand_id_idx` (`material_brand_id`),
  KEY `material_type_id_idx` (`material_type_id`),
  CONSTRAINT `material_brand_id` FOREIGN KEY (`material_brand_id`) REFERENCES `material_brand` (`material_brand_id`),
  CONSTRAINT `material_type_id` FOREIGN KEY (`material_type_id`) REFERENCES `material_type` (`material_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_item`
--

LOCK TABLES `material_item` WRITE;
/*!40000 ALTER TABLE `material_item` DISABLE KEYS */;
INSERT INTO `material_item` VALUES (1,1,1,'TMT Steelbar'),(2,2,2,'Concrete Post'),(3,3,3,'Polycab Wires');
/*!40000 ALTER TABLE `material_item` ENABLE KEYS */;
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
