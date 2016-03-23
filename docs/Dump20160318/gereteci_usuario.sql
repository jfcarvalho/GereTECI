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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `matricula` varchar(100) DEFAULT NULL,
  `nivel_acesso` varchar(255) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `telefone` varchar(100) DEFAULT NULL,
  `usuario_rede` varchar(100) DEFAULT NULL,
  `computador_id_computador` int(11) DEFAULT NULL,
  `servico` int(11) DEFAULT NULL,
  `setor_id_setor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FK_kpnu7wgyvbfc5hb25hwx73jlf` (`computador_id_computador`),
  KEY `FK_ag4gg68mcxb98n87d5g7qh4b1` (`servico`),
  KEY `FK_q4rjtcg2q1l6wlcau6rwj3es1` (`setor_id_setor`),
  CONSTRAINT `FK_ag4gg68mcxb98n87d5g7qh4b1` FOREIGN KEY (`servico`) REFERENCES `servico` (`id_servico`),
  CONSTRAINT `FK_kpnu7wgyvbfc5hb25hwx73jlf` FOREIGN KEY (`computador_id_computador`) REFERENCES `computador` (`id_computador`),
  CONSTRAINT `FK_q4rjtcg2q1l6wlcau6rwj3es1` FOREIGN KEY (`setor_id_setor`) REFERENCES `setor` (`id_setor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,NULL,NULL,NULL,'ADMINISTRADOR','Super Admin',NULL,NULL,NULL,NULL,1),(2,'Assessor Administrativo II','jfcarvalho@ctb.ba.gov.br','123456','ADMINISTRADOR','Jean Carvalho','(71)3612-1210','Jean',NULL,NULL,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
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
