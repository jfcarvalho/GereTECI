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
  `atualizar_java` bit(1) NOT NULL,
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
  `id_recurso_caixa` int(11) DEFAULT NULL,
  `id_recurso_monitor` int(11) DEFAULT NULL,
  `id_recurso_monitor_sec` int(11) DEFAULT NULL,
  `id_recurso_mouse` int(11) DEFAULT NULL,
  `id_recurso_teclado` int(11) DEFAULT NULL,
  `usuario_id_usuario` int(11) DEFAULT NULL,
  `usuario_sec` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_computador`),
  KEY `FK_k7q3cv2n4smeupu8oyw9e590g` (`id_recurso_caixa`),
  KEY `FK_june6lqmj4p0p1ybi1w2tb4gt` (`id_recurso_monitor`),
  KEY `FK_eg1a39ki5317lldfo9og0jdjv` (`id_recurso_monitor_sec`),
  KEY `FK_hgtxnvbusflx2tgjbfqeecfsd` (`id_recurso_mouse`),
  KEY `FK_hrx7c6ogm9byotoynttmoxowf` (`id_recurso_teclado`),
  KEY `FK_20x4oligsv7tgo5jvwu0k5y9y` (`usuario_id_usuario`),
  KEY `FK_ipe084xrhglunx5xq2rtxc5x3` (`usuario_sec`),
  CONSTRAINT `FK_20x4oligsv7tgo5jvwu0k5y9y` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FK_eg1a39ki5317lldfo9og0jdjv` FOREIGN KEY (`id_recurso_monitor_sec`) REFERENCES `recurso` (`id_recurso`),
  CONSTRAINT `FK_hgtxnvbusflx2tgjbfqeecfsd` FOREIGN KEY (`id_recurso_mouse`) REFERENCES `recurso` (`id_recurso`),
  CONSTRAINT `FK_hrx7c6ogm9byotoynttmoxowf` FOREIGN KEY (`id_recurso_teclado`) REFERENCES `recurso` (`id_recurso`),
  CONSTRAINT `FK_ipe084xrhglunx5xq2rtxc5x3` FOREIGN KEY (`usuario_sec`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FK_june6lqmj4p0p1ybi1w2tb4gt` FOREIGN KEY (`id_recurso_monitor`) REFERENCES `recurso` (`id_recurso`),
  CONSTRAINT `FK_k7q3cv2n4smeupu8oyw9e590g` FOREIGN KEY (`id_recurso_caixa`) REFERENCES `recurso` (`id_recurso`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computador`
--

LOCK TABLES `computador` WRITE;
/*!40000 ALTER TABLE `computador` DISABLE KEYS */;
INSERT INTO `computador` VALUES (1,'x64Bits','\0',NULL,NULL,NULL,'dnsaPadrao','dnspPadrao','gatewayPadrao','1500','10.95.1.13','mascaraPadrao','m4g','102024','Core i5','v7','funcionando','8.75',NULL,3,4,5,6,1,NULL),(2,'x32Bits','\0',NULL,NULL,NULL,'dnsaPadrao','dnspPadrao','gatewayPadrao','058','10.95.1.22','mascaraPadrao','m4g','101920','Core i3','v7','funcionando','8.25',11,7,NULL,8,2,3,NULL);
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

-- Dump completed on 2016-03-28 15:33:28
