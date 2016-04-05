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
  `tipoes` varchar(30) DEFAULT NULL,
  `rolagem` bit(1) DEFAULT NULL,
  `capacidade` varchar(255) DEFAULT NULL,
  `categoria_monitor` varchar(255) DEFAULT NULL,
  `dvi` bit(1) DEFAULT NULL,
  `polegadas` varchar(255) DEFAULT NULL,
  `especificacoes` varchar(255) DEFAULT NULL,
  `computador_id_computador` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_recurso`),
  KEY `FK_ml0qc1bxgyraqusvnrun8wnst` (`computador_id_computador`),
  CONSTRAINT `FK_ml0qc1bxgyraqusvnrun8wnst` FOREIGN KEY (`computador_id_computador`) REFERENCES `computador` (`id_computador`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recurso`
--

LOCK TABLES `recurso` WRITE;
/*!40000 ALTER TABLE `recurso` DISABLE KEYS */;
INSERT INTO `recurso` VALUES ('Teclado',1,NULL,'467102','Chipset','101920','com_defeito_para','Preto','','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('Teclado',2,NULL,'02007-486-4055914-11116','Microsoft','101920','funcionando','Preto','\0','0',NULL,NULL,NULL,NULL,NULL,NULL,2),('Monitor',3,NULL,'19IEL','Philips','102024','funcionando',NULL,NULL,NULL,NULL,NULL,'primario','\0','19',NULL,1),('Monitor',4,NULL,'FLATRON W1934S','LG','102024','funcionando',NULL,NULL,NULL,NULL,NULL,'secundario','\0','19',NULL,1),('Mouse',5,NULL,'06A010044560','Não tem','102024','funcionando','Prata',NULL,'1','',NULL,NULL,NULL,NULL,NULL,1),('Teclado',6,NULL,'081329019032008384','KB2203-2BK','102024','funcionando','Preto','','0',NULL,NULL,NULL,NULL,NULL,NULL,1),('Monitor',7,NULL,'LCD Monitor E2050 SOAN','AOC','101920','funcionando',NULL,NULL,NULL,NULL,NULL,'primario','\0','19',NULL,2),('Mouse',8,NULL,'MO-K133U','Chipset','101920','funcionando','Preto',NULL,'0','',NULL,NULL,NULL,NULL,NULL,2),('Midia',9,NULL,'HD Externo','Seagate','101969','funcionando',NULL,NULL,'0',NULL,'1T',NULL,NULL,NULL,NULL,NULL),('Outros',10,NULL,'Retro Projetor','LG','102090','funcionando',NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,'Nenhum',NULL),('CaixaDeSom',11,NULL,'Não tem','Não tem','10.95.1.22','funcionando','Preto',NULL,'outros',NULL,NULL,NULL,NULL,NULL,NULL,2);
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

-- Dump completed on 2016-04-05  9:17:07
