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
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico` (
  `categoria` varchar(31) NOT NULL,
  `id_servico` int(11) NOT NULL AUTO_INCREMENT,
  `data_encerramento` date DEFAULT NULL,
  `data_ocorrencia` date DEFAULT NULL,
  `descricao_problema` varchar(255) DEFAULT NULL,
  `descricao_solucao` varchar(255) DEFAULT NULL,
  `protocolo` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `alteracao_usuario` bit(1) DEFAULT NULL,
  `conta` varchar(255) DEFAULT NULL,
  `criacao_usuario` bit(1) DEFAULT NULL,
  `descricao_pronta` varchar(255) DEFAULT NULL,
  `exclusao_usuario` bit(1) DEFAULT NULL,
  `mudanca_plano` bit(1) DEFAULT NULL,
  `troca_senha` bit(1) DEFAULT NULL,
  `recolhimento_equipamento` bit(1) DEFAULT NULL,
  `recurso_recolhido` varchar(255) DEFAULT NULL,
  `recurso_substituto` varchar(255) DEFAULT NULL,
  `troca_recursos` bit(1) DEFAULT NULL,
  `data_visita` date DEFAULT NULL,
  `nome_tecnico` varchar(255) DEFAULT NULL,
  `protocolo_servico` varchar(255) DEFAULT NULL,
  `ramais_trocados` varchar(255) DEFAULT NULL,
  `troca_ramal` bit(1) DEFAULT NULL,
  `visita_oi` bit(1) DEFAULT NULL,
  `ipantigo` varchar(255) DEFAULT NULL,
  `ipnovo` varchar(255) DEFAULT NULL,
  `data_rds` date DEFAULT NULL,
  `numero_rds` varchar(255) DEFAULT NULL,
  `proxy` bit(1) DEFAULT NULL,
  `rds_aberto` bit(1) DEFAULT NULL,
  `trocaip` bit(1) DEFAULT NULL,
  `senha_expirada` bit(1) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `atendente` int(11) DEFAULT NULL,
  `usuario_id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_servico`),
  KEY `FK_3aygkfbshj29r75fqjlsqbkww` (`atendente`),
  KEY `FK_h4ow4gexehnotshwjsbfkatgd` (`usuario_id_usuario`),
  CONSTRAINT `FK_3aygkfbshj29r75fqjlsqbkww` FOREIGN KEY (`atendente`) REFERENCES `usuario` (`id_usuario`),
  CONSTRAINT `FK_h4ow4gexehnotshwjsbfkatgd` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES ('Manutenção',6,'2016-03-28','2016-03-28','O teclado do usuário apresenta problema no adaptador','O recurso defeituoso foi trocado','CTBM201603-1','fechado',NULL,NULL,NULL,'desc1',NULL,NULL,NULL,'\0','Teclado chipset preto ps2 467102','Teclado Microsoft USB 02007-486-4055914-11116','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,3),('Rede',7,'2016-03-28','2016-03-28','Senha do usuário expirou','Troca de senha de rede','CTBR201603-1','fechado','\0',NULL,'\0',NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','Kulhavy',5,4),('Rede',8,'2016-03-28','2016-03-28','Senha de rede expirada','Troca de senha','CTBR201603-2','fechado','\0',NULL,'\0',NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','Nuno',5,6),('Manutenção',9,NULL,'2016-03-28','Descrição aqui','Solução aprcial: passado o antivirus na máquina','CTBM201603-2','em_andamento',NULL,NULL,NULL,'desc3',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,9),('Rede',11,'2016-03-28','2016-03-28','O usuário precisou acessar uma máquina(10.95.1.47, Máquina de Rita) que estava no domínio e não tinha a senha do usuário pertencente(CTS\\Rita de Cassia)','Alterar a senha do usuário local Nelma(10.95.1.47, Máquina de rita) para ctb','CTBR201603-3','fechado','\0',NULL,'\0',NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','Nelma(Usuario Local)',1,12);
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
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
