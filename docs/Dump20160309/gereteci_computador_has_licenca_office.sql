-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gereteci
-- ------------------------------------------------------
-- Server version	5.7.9-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `computador_has_licenca_office`
--

DROP TABLE IF EXISTS `computador_has_licenca_office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computador_has_licenca_office` (
  `computador_id_computador` int(11) NOT NULL,
  `licenca_office_id_licencaoffice` int(11) NOT NULL,
  PRIMARY KEY (`computador_id_computador`,`licenca_office_id_licencaoffice`),
  KEY `fk_computador_has_licenca_office_licenca_office1_idx` (`licenca_office_id_licencaoffice`),
  KEY `fk_computador_has_licenca_office_computador1_idx` (`computador_id_computador`),
  CONSTRAINT `fk_computador_has_licenca_office_computador1` FOREIGN KEY (`computador_id_computador`) REFERENCES `computador` (`id_computador`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_computador_has_licenca_office_licenca_office1` FOREIGN KEY (`licenca_office_id_licencaoffice`) REFERENCES `licenca_office` (`id_licencaoffice`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computador_has_licenca_office`
--

LOCK TABLES `computador_has_licenca_office` WRITE;
/*!40000 ALTER TABLE `computador_has_licenca_office` DISABLE KEYS */;
/*!40000 ALTER TABLE `computador_has_licenca_office` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-09 16:39:48
