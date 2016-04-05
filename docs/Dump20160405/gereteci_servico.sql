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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES ('Manutenção',6,'2016-03-28','2016-03-28','O teclado do usuário apresenta problema no adaptador','O recurso defeituoso foi trocado','CTBM201603-1','fechado',NULL,NULL,NULL,'desc1',NULL,NULL,NULL,'\0','Teclado chipset preto ps2 467102','Teclado Microsoft USB 02007-486-4055914-11116','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,3),('Rede',7,'2016-03-28','2016-03-28','Senha do usuário expirou','Troca de senha de rede','CTBR201603-1','fechado','\0',NULL,'\0','0','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','Kulhavy',5,4),('Rede',8,'2016-03-28','2016-03-28','Senha de rede expirada','Troca de senha','CTBR201603-2','fechado','\0',NULL,'\0',NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','Nuno',5,6),('Manutenção',9,NULL,'2016-03-28','Descrição aqui','Solução aprcial: passado o antivirus na máquina','CTBM201603-2','fechado',NULL,NULL,NULL,'desc1',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,9),('Rede',11,'2016-03-28','2016-03-28','O usuário precisou acessar uma máquina(10.95.1.47, Máquina de Rita) que estava no domínio e não tinha a senha do usuário pertencente(CTS\\Rita de Cassia)','Alterar a senha do usuário local Nelma(10.95.1.47, Máquina de rita) para ctb','CTBR201603-3','fechado','\0',NULL,'\0',NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','Nelma(Usuario Local)',1,12),('Manutenção',12,'2016-03-29','2016-03-29','SIMPAS','Novo download','CTBM201603-3','fechado',NULL,NULL,NULL,'desc2',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14,13),('Rede',13,'2016-03-29','2016-03-29','Troca de senha','Trocado a senha do usuário','CTBM201603-14','fechado','\0',NULL,'\0',NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','Raimundo Manoel',5,15),('Manutenção',14,'2016-03-29','2016-03-29','Instalação Codec','Download , Instalação','CTBM201603-4','fechado',NULL,NULL,NULL,'desc1',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14,16),('Email',15,'2016-03-29','2016-03-29','O usuário precisou alterar a senha do expresso para alterar a senha do outlook','Alterar a senha do expresso atraves do Expresso Admin','CTBM201603-13','fechado','\0','raimundo.jesusfilho@ctb.ba.gov.br','\0','desc1','\0','\0','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,15),('Rede',16,'2016-03-29','2016-03-29','O usuário não consegue autenticar a conta no dominio CTS\\Raimundo','Criação de uma conta local DIPLAN01\\DIPLAN','CTBM201603-12','fechado','\0',NULL,'\0',NULL,'\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'\0','DIPLAN01\\DIPLAN',1,17),('Email',17,'2016-04-01','2016-03-30','Solicitação de ciração de conta de email para Eliene Borges Pereira','Aberta a RDS para criação da conta\r\n\r\nA conta foi aberta em 01/04/2016','CTBE201604-11','fechado','\0','elianeborges@ctb.ba.gov.br','','desc4','\0','\0','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,18),('Manutenção',18,'2016-03-31','2016-03-31','SIMPAS','Atualização para a versão mais recente do programa','CTBM201603-5','fechado',NULL,NULL,NULL,'desc2',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,19),('Manutenção',19,'2016-03-30','2016-03-30','Trocar Sistema Operacional de Windows 10 para Windows 7','Retroceder para Windows 7','CTBM201603-6','fechado',NULL,NULL,NULL,'desc1',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,20),('Manutenção',20,'2016-03-31','2016-03-31','O Internet Explorer não está acessando o site da caixa corretamente','Até o momento, regressar o navegador para IE9 e restaurar o sistema','CTBM201603-7','em_andamento',NULL,NULL,NULL,'desc1',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,21),('Manutenção',24,'2016-03-31','2016-03-31','O usuário solicitou instalação do Google Earth','Instalado o software na máquina','CTBM201603-8','fechado',NULL,NULL,NULL,'desc4',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,16),('Manutenção',25,'2016-03-31','2016-03-31','Solicitaram a instalação de uma antena de wifi','A antena foi instalada','CTBM201603-9','fechado',NULL,NULL,NULL,'desc1',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14,16),('Manutenção',27,'2016-04-01','2016-04-01','Instalação IrfanView','Software Instalado','CTBM201603-10','fechado',NULL,NULL,NULL,'desc4',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14,16),('Manutenção',28,'2016-04-01','2016-04-01','Cripagem de cabo RJ-45 fêmea na Sala de Reunião','Serviço manual: Os cabos foram cripados','CTBM201604-11','fechado',NULL,NULL,NULL,'desc1',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL),('Manutenção',29,NULL,'2016-03-23','Backup e formatação do computador do usuário','01/04/2016 -  Usuário reagendou o serviço para 08/04','CTBM201604-12','aberto',NULL,NULL,NULL,'desc4',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14,22),('Manutenção',30,NULL,'2016-04-01','Solicitação de uma nova maquina.\r\n- O usuário quer um computador para estagiário.','Iremos esperar a compra dos novos computadores para efetuar a troca.','CTBM201604-13','aberto',NULL,NULL,NULL,'desc5',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,19),('Manutenção',31,NULL,'2016-04-01','Usuário solicitação de uma nova maquina para portaria.\r\n - O  computador atual está apresentando problemas.','Iremos esperar a compra dos novos computadores para efetuar a troca.','CTBM201604-16','aberto',NULL,NULL,NULL,'desc5',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,14,23),('Manutenção',46,'2016-04-04','2016-04-04','Atualização do SIMPAS','O software foi atualizado para a versão mais recente do programa','CTBM201604-16','fechado',NULL,NULL,NULL,'desc2',NULL,NULL,NULL,'\0','','','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,5,13);
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

-- Dump completed on 2016-04-05  9:17:08
