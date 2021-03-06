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
-- Table structure for table `computador`
--

DROP TABLE IF EXISTS `computador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computador` (
  `id_computador` int(11) NOT NULL AUTO_INCREMENT,
  `data_compra` date DEFAULT NULL,
  `data_formatacao` date DEFAULT NULL,
  `patrimonio` varchar(45) DEFAULT NULL,
  `sistema` varchar(45) DEFAULT NULL,
  `arquitetura` varchar(45) DEFAULT NULL,
  `n_entradas_usb` int(11) DEFAULT NULL,
  `placa_wifi` varchar(45) DEFAULT NULL,
  `versao_java` varchar(45) DEFAULT NULL,
  `data_backup` date DEFAULT NULL,
  `dns_preferencial` varchar(45) DEFAULT NULL,
  `mascara` varchar(45) DEFAULT NULL,
  `gateway` varchar(45) DEFAULT NULL,
  `dns_alternativo` varchar(45) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `id_impressao` varchar(255) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `usuario_id_usuario` int(11) DEFAULT NULL,
  `memoria` int(11) DEFAULT NULL,
  `processador` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_computador`),
  UNIQUE KEY `usuario_id_usuario_UNIQUE` (`usuario_id_usuario`),
  KEY `fk_computador_usuario1_idx` (`usuario_id_usuario`),
  CONSTRAINT `fk_computador_usuario1` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computador`
--

LOCK TABLES `computador` WRITE;
/*!40000 ALTER TABLE `computador` DISABLE KEYS */;
INSERT INTO `computador` VALUES (2,'2014-06-10','2016-01-11','CTB-102024','v7','x64Bits',NULL,NULL,'8','2015-10-13','dnspPadrao','mascaraPadrao','gatewayPadrao','dnsaPadrao','10.95.1.3','1500','3',2,6,'Intel Core i5');
/*!40000 ALTER TABLE `computador` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-03 16:30:09
