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
-- Table structure for table `recurso`
--

DROP TABLE IF EXISTS `recurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recurso` (
  `tipo_recurso` varchar(31) NOT NULL,
  `id_recurso` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` int(11) DEFAULT NULL,
  `descricao` varchar(100) DEFAULT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `patrimonio` varchar(100) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `cor` varchar(255) DEFAULT NULL,
  `funcoes` bit(1) DEFAULT NULL,
  `tipo_teclado` varchar(255) DEFAULT NULL,
  `rolagem` bit(1) DEFAULT NULL,
  `tipo_mouse` varchar(255) DEFAULT NULL,
  `capacidade` varchar(255) DEFAULT NULL,
  `tipo_midia` varchar(255) DEFAULT NULL,
  `dvi` bit(1) DEFAULT NULL,
  `polegadas` varchar(255) DEFAULT NULL,
  `tipo_cs` varchar(255) DEFAULT NULL,
  `computador_id_computador` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_recurso`),
  KEY `FK_ml0qc1bxgyraqusvnrun8wnst` (`computador_id_computador`),
  CONSTRAINT `FK_ml0qc1bxgyraqusvnrun8wnst` FOREIGN KEY (`computador_id_computador`) REFERENCES `computador` (`id_computador`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
INSERT INTO `recurso` VALUES ('Monitor',19,NULL,'lklklkl','lllkkllk','7788999','funcionando',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','lklklklk',NULL,27),('Mouse',20,NULL,'asasasa','55444','887744','funcionando','Preto',NULL,NULL,'\0','sasasasa',NULL,NULL,NULL,NULL,NULL,27),('Teclado',21,NULL,'aqaqaqaq','qqqaaq','888555','funcionando','aqaqaqaq','\0','aqaqaqaq',NULL,NULL,NULL,NULL,NULL,NULL,NULL,27),('CaixaDeSom',22,NULL,'141414','141414','411114','funcionando','14141414',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'141414',NULL),('Monitor',23,NULL,'Blabla','Philips','102024','funcionando',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','17',NULL,NULL),('Teclado',24,NULL,'blabla','KB2203','102024','funcionando','Preto','\0','USB',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('CaixaDeSom',25,NULL,'sasasa','sasasa','assasa','funcionando','sasasa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'sasasa',27);
/*!40000 ALTER TABLE `recurso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-18 16:51:02
