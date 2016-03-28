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
  `arquitetura` varchar(255) DEFAULT NULL,
  `data_backup` date DEFAULT NULL,
  `data_compra` date DEFAULT NULL,
  `data_formatacao` date DEFAULT NULL,
  `dns_alternativo` varchar(255) DEFAULT NULL,
  `dns_preferencial` varchar(255) DEFAULT NULL,
  `gateway` varchar(255) DEFAULT NULL,
  `id_impressao` varchar(100) DEFAULT NULL,
  `ip` varchar(100) DEFAULT NULL,
  `mascara` varchar(255) DEFAULT NULL,
  `memoria` varchar(255) DEFAULT NULL,
  `patrimonio` varchar(100) DEFAULT NULL,
  `processador` varchar(100) DEFAULT NULL,
  `sistema` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `versao_java` varchar(100) DEFAULT NULL,
  `usuario_id_usuario` int(11) DEFAULT NULL,
  `id_recurso_teclado` int(11) DEFAULT NULL,
  `id_recurso_monitor` int(11) DEFAULT NULL,
  `id_recurso` int(11) DEFAULT NULL,
  `id_recurso_mouse` int(11) DEFAULT NULL,
  `id_recurso_caixa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_computador`),
  KEY `FK_20x4oligsv7tgo5jvwu0k5y9y` (`usuario_id_usuario`),
  KEY `FK_hrx7c6ogm9byotoynttmoxowf` (`id_recurso_teclado`),
  KEY `FK_june6lqmj4p0p1ybi1w2tb4gt` (`id_recurso_monitor`),
  KEY `FK_d3ottvtqk9nnmmopb5m4et8fa` (`id_recurso`),
  KEY `FK_hgtxnvbusflx2tgjbfqeecfsd` (`id_recurso_mouse`),
  KEY `FK_k7q3cv2n4smeupu8oyw9e590g` (`id_recurso_caixa`),
  CONSTRAINT `FK_20x4oligsv7tgo5jvwu0k5y9y` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FK_d3ottvtqk9nnmmopb5m4et8fa` FOREIGN KEY (`id_recurso`) REFERENCES `recurso` (`id_recurso`),
  CONSTRAINT `FK_hgtxnvbusflx2tgjbfqeecfsd` FOREIGN KEY (`id_recurso_mouse`) REFERENCES `recurso` (`id_recurso`),
  CONSTRAINT `FK_hrx7c6ogm9byotoynttmoxowf` FOREIGN KEY (`id_recurso_teclado`) REFERENCES `recurso` (`id_recurso`),
  CONSTRAINT `FK_june6lqmj4p0p1ybi1w2tb4gt` FOREIGN KEY (`id_recurso_monitor`) REFERENCES `recurso` (`id_recurso`),
  CONSTRAINT `FK_k7q3cv2n4smeupu8oyw9e590g` FOREIGN KEY (`id_recurso_caixa`) REFERENCES `recurso` (`id_recurso`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computador`
--

LOCK TABLES `computador` WRITE;
/*!40000 ALTER TABLE `computador` DISABLE KEYS */;
INSERT INTO `computador` VALUES (27,'x64Bits','2016-03-16','2016-03-16','2016-03-16','dnsaPadrao','dnspPadrao','gatewayPadrao','888888','888888888','mascaraPadrao','m512','88888888','88888888','v7','funcionando','88',1,21,19,NULL,20,25);
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

-- Dump completed on 2016-03-18 16:51:03
