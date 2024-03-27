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
-- Table structure for table `staff_personal`
--

DROP TABLE IF EXISTS `staff_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_personal` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `staff_firstname` varchar(60) DEFAULT NULL,
  `staff_lastname` varchar(60) DEFAULT NULL,
  `staff_address` varchar(250) DEFAULT NULL,
  `staff_phone` varchar(60) DEFAULT NULL,
  `staff_email` varchar(60) DEFAULT NULL,
  `staff_username` varchar(60) NOT NULL,
  `staff_password` varchar(60) NOT NULL,
  `staff_desig_id` int NOT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `staff_id_UNIQUE` (`staff_id`),
  UNIQUE KEY `staff_username_UNIQUE` (`staff_username`),
  UNIQUE KEY `staff_email_UNIQUE` (`staff_email`),
  UNIQUE KEY `staff_phone_UNIQUE` (`staff_phone`),
  KEY `staff_desig_id_fk_idx` (`staff_desig_id`),
  CONSTRAINT `staff_desig_id_fk` FOREIGN KEY (`staff_desig_id`) REFERENCES `designation` (`design_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_personal`
--

LOCK TABLES `staff_personal` WRITE;
/*!40000 ALTER TABLE `staff_personal` DISABLE KEYS */;
INSERT INTO `staff_personal` VALUES (1,'Arjun','Haridas','Marayil House','9847528465','arjunharidas066@gmail.com','arjun','arjun',1),(2,'Aravind','Shai','Cherukarathekethil','1234567890','shaay@maanthadicom','shaay','shaay',2),(3,'test','tsts','hhagahbh ','1544524','gvahy','tstft','hsg',3),(4,'Akshay','P Shibu','14th Mile Pambady Kottayam','5678904321','akshaypshibu@gmail.com','akshay','akshay',2),(5,'sameena','nissar','ksdoii','2345678901','sameena@gmailcom','sameena','sameena',2),(6,'Mini','Vijayan','Areechirayil','9999333344','mini@gmail.com','mini','mini',3),(7,'Hari','Krishnan','khoda','2134567899','klclds','Hari','Hari',4);
/*!40000 ALTER TABLE `staff_personal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-27 19:04:01
