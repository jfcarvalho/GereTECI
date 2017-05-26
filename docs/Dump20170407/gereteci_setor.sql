-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: gereteci
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `setor`
--

DROP TABLE IF EXISTS `setor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setor` (
  `id_setor` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `sigla` varchar(100) DEFAULT NULL,
  `responsavel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_setor`),
  KEY `FK_qk8cnhu36pmnqouwiswi1uv8f` (`responsavel`),
  CONSTRAINT `FK_qk8cnhu36pmnqouwiswi1uv8f` FOREIGN KEY (`responsavel`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setor`
--

LOCK TABLES `setor` WRITE;
/*!40000 ALTER TABLE `setor` DISABLE KEYS */;
INSERT INTO `setor` VALUES (1,'Tecnologia da Informação','TECI',2),(2,'Subcoordenadoria de Controle e Gerenciamento ','POPE',4),(3,'Subcoordenadoria de Gestão de Pessoas','GESP',7),(4,'Diretoria Administrativa e Financeiro','DIRAF',11),(5,'Via Permanente','VIA',8),(6,'Diretoria das Estações','DIRES',10),(7,'Coordenadoria Financeira','COFIN',12),(8,'Subcoordenadoria de Patrimonios','SPAT',13),(9,'Info Centro','Info Centro',16),(10,'Diretoria de Planejamento','DIPLAN',42),(11,'Subcoordenadoria de Controle Execução Financeira','COEF',18),(12,'Coordenadoria Administrativa','COADM',64),(13,'CIPA','CIPA',20),(14,'Subcoordenadoria de Contabilidade','CONT',21),(15,'Coordenadoria de Convênio e Contratos','CONV ',22),(16,'Portaria','Portaria',28),(17,'Assesoria Técnica','ASTEC',29),(18,'Assesoria Jurídica','ASJUR',32),(19,' Coordenadoria de Operação','COOPE',33),(20,'Coordenadoria de Manutenção','COMAN',36),(21,'Tração','TRAÇÃO',37),(22,'Subcoordenadoria de Material Rodante e Oficina','MARO',38),(23,'Diretoria de Operaçao e Manutenção','DIROM',45),(24,'Gestão de Contratos','QUAS',46),(25,'Diretoria da Presidência','DIPRE',47),(26,'Sistema Fixos','SISF',51),(27,'Coordenadoria de Segurança','COSEG',52),(28,'Subcoordenadoria de Faturamento e Cobrança','FATC',56),(29,'Coordenadoria de Gestão Pessoal','CODGE',62),(30,'Diretoria de Obras','DIROB',NULL);
/*!40000 ALTER TABLE `setor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-07 16:34:44
