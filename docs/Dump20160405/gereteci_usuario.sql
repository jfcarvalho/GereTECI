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
  `setor_id_setor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FK_kpnu7wgyvbfc5hb25hwx73jlf` (`computador_id_computador`),
  KEY `FK_q4rjtcg2q1l6wlcau6rwj3es1` (`setor_id_setor`),
  CONSTRAINT `FK_kpnu7wgyvbfc5hb25hwx73jlf` FOREIGN KEY (`computador_id_computador`) REFERENCES `computador` (`id_computador`),
  CONSTRAINT `FK_q4rjtcg2q1l6wlcau6rwj3es1` FOREIGN KEY (`setor_id_setor`) REFERENCES `setor` (`id_setor`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Assessor Administrativo II','jfcarvalho@ctb.ba.gov.br','34400238-0','ADMINISTRADOR','Jean Carvalho','3612-1210','Jean',NULL,1),(2,'Subcoordenador de Tecnologia da Informação','romeuoj@ctb.ba.gov.br','34400018-4','ADMINISTRADOR','Romeu Oliveira','3612-1210','Romeu',NULL,1),(3,'Assessora Administrativo II','emineide.franca@ctb.ba.gov.br','34400151-2','USUARIOPADRAO','Maria Emineide Bezerra','3612-1201','Neide',NULL,4),(4,'Subcoordenador de Controle e Gerenciamento','josecardoso.kulhavy@ctb.ba.gov.br','34400136-8','USUARIOPADRAO','José Cardoso Kulhavy','3612-1237','Kulhavy',NULL,2),(5,'Estagiário','suporte.ctb1210@ctb.ba.gov','34400233-0','USUARIOTECI','Ariana Souza Silva','3612-1210','EstagiarioTECI',NULL,1),(6,'Assistente Operacional','Não tem','3440098-6','USUARIOPADRAO','Nuno César Mattos','3612-1212','Nuno',NULL,3),(7,'Subcoordenador de Gestão de Pessoas','uellington.silva@ctb.ba.gov.br','3440088-3','USUARIOPADRAO','Uellington Batista das neves','3612-1212','Uellington',NULL,3),(8,'Alguma coisa','joseamerico.santos@ctb.ba.gov.br','34400133-4','USUARIOPADRAO','José Amérco da Costa Santos','3612-1229','Americo',NULL,5),(9,'Subcoordenadora','1111','34400152-0','USUARIOPADRAO','Silvia Lopes','111','Silvia Lopes',NULL,6),(10,'Diretora de Estações','keila.lomanto@ctb.ba.gov.br','34400137-6','USUARIOPADRAO','Keila Contreiras Lomanto','3612-12','Keila',NULL,6),(11,'Diretor Administrativo e Financeiro','george.reboucas@ctb.ba.gov.br','34400200-5','USUARIOPADRAO','George Bittencourt Rebouças','3612-1201','Não tem',NULL,4),(12,'Assessora Técnica','karin.kunze@ctb.ba.go.br','34400205-5','USUARIOPADRAO','Karin Kunze','3612-12','Karin',NULL,7),(13,'Subcoordenador de Patrimônio','sergio.luiz@ctb.ba.gov.br','34400098-0','USUARIOPADRAO','Sérgio Luiz','3612-1222','Sergio',NULL,8),(14,'Estagiário','suporte.ctb1210@ctb.ba.gov.br','123','USUARIOTECI','Uiran Passos','3612-1210','estagiarioTECI',NULL,1),(15,'Assistente Técnico','raimundodejesus.filho@ctb.ba.gov.br','34400086-7','USUARIOPADRAO','Raimundo de Jesus','3612-1209','Raimundo',NULL,3),(16,'Arquiteto Conder','Não tem','Externo(Conder)','USUARIOPADRAO','Mário Bestetti','3612-1213','Mario',NULL,9),(17,'Assessor Técnico','gabriel.cova@ctb.ba.gov.br','34400195.2','USUARIOPADRAO','Gabriel Shultz Cova','3612-1206','Não tem',NULL,10),(18,'Subcoordenador de Controle e Gestão Financeira','marcelogomes@ctb.ba.gov.br','34400147-3','USUARIOPADRAO','Marcelo Gomes dos Santos','3612-1219','Marcelo',NULL,11),(19,'Coordenador Administrativo','sergio.silva7@ctb.ba.gov.br','34400049-3','USUARIOPADRAO','Sérgio Murilo Lima da Silva','3612-1221','Sergio Murilo',NULL,12),(20,'Assistente Operacional','edilene.ferreira@ctb.ba.gov.br','34400084-1','USUARIOPADRAO','Edilene Alves Ferreria','2105-2752','Não tem',NULL,13),(21,'Subcoordenador de Contabilidade','joaocasal.silva@ctb.ba.gov.br','34400129-5','USUARIOPADRAO','João Casal da Silva','3612-1218','Joao Casal',NULL,14),(22,'SUbcoordenadoria de Convênios e Contratos','anaclaudia.couto@ctb.ba.gov.br','34400104-1','USUARIOPADRAO','Ana Claudia Martins de  Souza Couto','3612-1205','Ana Claudia',NULL,15),(23,'Assistente de Segurança','edimar.queiroz@ctb.ba.gov.br','34400007-9','USUARIOTECI','Edimar Queiroz Santos','3612-1210','Edimar',NULL,1);
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

-- Dump completed on 2016-04-05  9:17:08
