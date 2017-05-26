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
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `FK_kpnu7wgyvbfc5hb25hwx73jlf` (`computador_id_computador`),
  KEY `FK_q4rjtcg2q1l6wlcau6rwj3es1` (`setor_id_setor`),
  CONSTRAINT `FK_kpnu7wgyvbfc5hb25hwx73jlf` FOREIGN KEY (`computador_id_computador`) REFERENCES `computador` (`id_computador`),
  CONSTRAINT `FK_q4rjtcg2q1l6wlcau6rwj3es1` FOREIGN KEY (`setor_id_setor`) REFERENCES `setor` (`id_setor`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Assessor Administrativo II','jfcarvalho@ctb.ba.gov.br','34400238-0','ADMINISTRADOR','Jean Carvalho','3612-1210','Jean',NULL,1,'$2a$10$KO3HAQHe6tyyeIHTyEzjGuPpDgSLd1O/86sFR0uG/iUg0PYGhQDdy'),(2,'Subcoordenador de Tecnologia da Informação','romeuoj@ctb.ba.gov.br','34400018-4','USUARIOTECI','Romeu Oliveira','3612-1210','Romeu',NULL,1,'$2a$10$KO3HAQHe6tyyeIHTyEzjGuPpDgSLd1O/86sFR0uG/iUg0PYGhQDdy'),(3,'Assessora Administrativo II','emineide.franca@ctb.ba.gov.br','34400151-2','USUARIOPADRAO','Maria Emineide Bezerra','3612-1201','Neide',NULL,4,NULL),(4,'Subcoordenador de Controle e Gerenciamento','josecardoso.kulhavy@ctb.ba.gov.br','34400136-8','USUARIOPADRAO','José Cardoso Kulhavy','3612-1237','Kulhavy',NULL,2,NULL),(5,'Estagiário','carnica@ctb.ba.gov.br','34400233-0','USUARIOPADRAO','Ariana Souza Silva','3612-1210','EstagiarioTECI',NULL,1,NULL),(6,'Assistente Operacional','Não tem','3440098-6','USUARIOPADRAO','Nuno César Mattos','3612-1212','Nuno',NULL,3,NULL),(7,'Subcoordenador de Gestão de Pessoas','uellington.silva@ctb.ba.gov.br','3440088-3','USUARIOPADRAO','Uellington Batista das neves','3612-1212','Uellington',NULL,3,NULL),(8,'Alguma coisa','joseamerico.santos@ctb.ba.gov.br','34400133-4','USUARIOPADRAO','José Américo da Costa Santos','3612-1229','Americo',NULL,5,NULL),(9,'Subcoordenadora','1111','34400152-0','USUARIOPADRAO','Silvia Lopes de Lima','111','Silvia Lopes',NULL,6,NULL),(10,'Diretora de Estações','keila.lomanto@ctb.ba.gov.br','34400137-6','USUARIOPADRAO','Keila Contreiras Lomanto','3612-12','Keila',NULL,6,NULL),(11,'Diretor Administrativo e Financeiro','george.reboucas@ctb.ba.gov.br','34400200-5','USUARIOPADRAO','George Bittencourt Rebouças','3612-1201','Não tem',NULL,4,NULL),(12,'Assessora Técnica','karin.kunze@ctb.ba.go.br','34400205-5','USUARIOPADRAO','Karin Barbosa Kunze','3612-12','Karin',NULL,7,NULL),(13,'Subcoordenador de Patrimônio','sergio.luiz@ctb.ba.gov.br','34400098-0','USUARIOPADRAO','Sergio Luiz Mendonça da Silva','3612-1222','Sergio',NULL,8,NULL),(14,'Estagiário','suporte.ctb1210@ctb.ba.gov.br','123','USUARIOTECI','Uiran Passos','3612-1210','estagiarioTECI',NULL,1,'$2a$10$8Qrz5FBMkgBmxOyvxJrTTeAeka6Yw3WfuIRkrupMLL4I0wkyD32xi'),(15,'Assistente Técnico','raimundodejesus.filho@ctb.ba.gov.br','34400086-7','USUARIOPADRAO','Raimundo Manoel de Jesus Filho','3612-1209','Raimundo',NULL,3,NULL),(16,'Arquiteto Conder','Não tem','Externo(Conder)','USUARIOPADRAO','Mário Bestetti','3612-1213','Mario',NULL,9,NULL),(17,'Assessor Técnico','gabriel.cova@ctb.ba.gov.br','34400195.2','USUARIOPADRAO','Gabriel Shultz Cova','3612-1206','Não tem',NULL,10,NULL),(18,'Subcoordenador de Controle e Gestão Financeira','marcelogomes@ctb.ba.gov.br','34400147-3','USUARIOPADRAO','Marcelo Gomes dos Santos','3612-1219','Marcelo',NULL,11,NULL),(19,'Coordenador Administrativo','sergio.silva7@ctb.ba.gov.br','34400049-3','USUARIOPADRAO','Sérgio Murilo Lima da Silva','3612-1221','Sergio Murilo',NULL,12,NULL),(20,'Assistente Operacional','edilene.ferreira@ctb.ba.gov.br','34400084-1','USUARIOPADRAO','Edilene Alves Ferreria','2105-2752','Não tem',NULL,13,NULL),(21,'Subcoordenador de Contabilidade','joaocasal.silva@ctb.ba.gov.br','34400129-5','USUARIOPADRAO','João Casal da Silva','3612-1218','Joao Casal',NULL,14,NULL),(22,'SUbcoordenadoria de Convênios e Contratos','anaclaudia.couto@ctb.ba.gov.br','34400104-1','USUARIOPADRAO','Ana Claudia Martins de  Souza Couto','3612-1205','Ana Claudia',NULL,15,NULL),(23,'Assistente de Segurança','edimar.queiroz@ctb.ba.gov.br','34400007-9','USUARIOTECI','Edimar Queiroz Santos','3612-1210','Edimar',NULL,1,NULL),(25,'Assessor Administrativo II','katia.caribe@ctb.ba.gov.br','34400019-2','USUARIOPADRAO','Katia Vieira Reboucas Caribe','3612-1219','Katia',NULL,11,NULL),(26,'Assistente Operacional','eliene.ferreira3@ctb.ba.gov.br','34400078-6','USUARIOPADRAO','Eliane Pereira Borges','3612-1219','Eliene',NULL,11,NULL),(27,'Manutenção','suporte','00000000-0','USUARIOTECI','Serviços TECI','3612-1210','Null',NULL,1,NULL),(28,'Segurança','sem email','Externo (Dinamo)','USUARIOPADRAO','Sandra da Silva Costa','3612-1220','sem login',NULL,16,NULL),(29,'assessora tecnica','sinara.ribeiro@ctb.ba.gov.br','34400153-8','USUARIOPADRAO','Sinara Maria Assunção Ribeiro','3612-1215','Sinara',NULL,17,NULL),(30,'Coordenadora do Bem Estar do Usuário','maristela.silveira@ctb.ba.gov.br','34400173-2','USUARIOPADRAO','Maristela Dantas Silveira','3612-1242','Maristela',NULL,6,NULL),(31,'assessora técnica Juridica','michelipessoa@ctb.ba.gov.br','34400224-1','USUARIOPADRAO','Micheli Souza Ribeira','3612-1207','Micheli',NULL,18,NULL),(32,'assessor Juridica chefe','edevilton@ctb.ba.gov.br','34400225-9','USUARIOPADRAO','Edevilton Santos e Santos','3612-1207','Edevilton',NULL,18,NULL),(33,'Coordenador de Operação','al.mello@ctb.ba.gov.br','34400042-7','USUARIOPADRAO','Al Mello','3612-1254','All Mello',NULL,19,NULL),(34,'Assistente Operacional','rafaela.viana@ctb.ba.gov.br','34400089-1','USUARIOPADRAO','Rafaela Camargo Viana','3612-1211','Rafaela',NULL,3,NULL),(35,'Assessor Administrativo','veralucia.souza3@ctb.ba.gov.br','34400154-6','USUARIOPADRAO','Vera Lucia Fernandes de Souza','3612-1247','Vera',NULL,6,NULL),(36,'Coordenador de Manutenção','carlos.bastos@ctb.ba.gov.br','34400092-2','USUARIOPADRAO','Carlos Antonio Araujo Bastos','3612-1227','Carlos Bastos',NULL,20,NULL),(37,'Maquinista','adson.silva@ctb.ba.gov.br','34400033-8','USUARIOPADRAO','Adson Viana da Silva','3612-1234','Adson Viana',NULL,21,NULL),(38,'Subcoordenador de Material Rodante e Oficina','Sem email','34400030-4','USUARIOPADRAO','Marco Antonio Ramos Barreto','3612-1232','Sem login',NULL,22,NULL),(39,'Assistente Operacional','Sem E-mail','34400001-1','USUARIOPADRAO','Rita de Cassia Lima Teles de Souza','3612-1216','Rita de Cassia',NULL,7,NULL),(40,'Jovem Aprendiz','Sem E-mail','34400229-1','USUARIOPADRAO','Jovem Aprendiz - COFIN','3612-1216','estagiariocofin',NULL,7,NULL),(41,'Assessor técnico','cristiano.araujo@ctb.ba.gov.br','34400127-9','USUARIOPADRAO','Cristiano Almeida Araujo','3612-1207','Cristiano',NULL,18,NULL),(42,'Diretor de Planejameto','raimundo.filgueiras@ctb.ba.gov.br','34400190-2','USUARIOPADRAO','Raimundo Mattos Filgueiras','3612-1206','Raimundo',NULL,10,NULL),(43,'Assessora Administrativa','ruamapereira@ctb.ba.gov.br','34400212-8','USUARIOPADRAO','Ruama Pereira','3612-1207','Ruama',NULL,18,NULL),(44,'Assessor Administrativo II','renatadesa@ctb.ba.gov.br','34400150-4','USUARIOPADRAO','Renata Sá Santos','3616-4990','Sem login',NULL,25,NULL),(45,'Diretor de Operaçao e Manutenção','hidelson.menezes@ctb.ba.gov.br','34400126-1','USUARIOPADRAO','Hidelson Ribeira Menezes Ferreira','3612-1203/1204','Hidelson',6,23,NULL),(46,'Subcoordenador de Gestão de Contratos','anderson.araujo@ctb.ba.gov.br','34400196-0','USUARIOPADRAO','Anderson Souza','3612-1217','Anderson',NULL,24,NULL),(47,'Diretor Presidente','eduardo.copello@ctb.ba.gov.br','34400189-7','USUARIOPADRAO','José Eduardo Ribeiro Copelllo','3616-4990','Não tem',NULL,25,NULL),(48,'Assistente Operacional','debora.santos4@ctb.ba.gov.br','34400077-8','USUARIOPADRAO','Debora Araujo dos Santos','3612-1238','debora.santos',NULL,2,NULL),(49,'Assistente Operacional','não tem','34400076-0','USUARIOPADRAO','Nata Lima do Nascimento','3612-1238','Natan',NULL,2,NULL),(50,'Assistente Operacional','nadir.mendonca@ctb.ba.gov.br','34400156-2','USUARIOPADRAO','Nadir de Assis de Mendoça','3612-128','Nadir',NULL,26,NULL),(51,'Assistente','edivan.silva@ctb.ba.gov.br','34400013-4','USUARIOPADRAO','Edivan Barbosa Da Silva','3612-1228','Edivan Barbosa',NULL,26,NULL),(52,'Coordenador de Segurança','jurandir.franco@ctb.ba.gov.br','34400097-2','USUARIOPADRAO','Jurandir Santos Franco','3612-1244','Sem login',NULL,27,NULL),(53,'Assistente','ouvidoria.ctb@ctb.ba.gov.br','34400002-9','USUARIOPADRAO','Emilia Maria Costa Moura','3612-1247','Emilia',NULL,6,NULL),(54,'Assessora Administrativo II','antonia.andrade@ctb.ba.gov.br','34400109-1','USUARIOPADRAO','Antonia Andrade Souza','3612-1203/1204','Antonia Andrade',NULL,23,NULL),(55,'Estagiário','Não informado','Nenhum','USUARIOPADRAO','Estagiáio DIRES','3612-1247','estagiaridires',NULL,6,NULL),(56,'Encarregado','Sem E-mail','34400022-3','USUARIOPADRAO','Jarbas de Almeida Dorea','3612-1248','Jarbas Dorea',NULL,28,NULL),(58,'Sem cargo','Sem','34400020-7','USUARIOPADRAO','Maria de Fatima Nascimento do Sá','361212423','Fátima',NULL,8,NULL),(59,'Assessor Jurídico','ddamasceno.chaves@ctb.ba.gov.br','34400138-4','USUARIOPADRAO','Denival Damasceno ','3116-4992','Não tem',NULL,18,NULL),(60,'Assessor Técnico Chefe','leonardo.barreiros@ctb.ba.gov.br','34400201-3','USUARIOPADRAO','Leonardo Barreiros da Silva','3116-4991','Leonardo',NULL,17,NULL),(61,'Estagiaria','lais.fonseca@ctb.ba.gov.br','34400261-5','USUARIOTECI','Laís Rezende Fonseca','3612-1210','TECI',NULL,1,'$2a$10$KO3HAQHe6tyyeIHTyEzjGuPpDgSLd1O/86sFR0uG/iUg0PYGhQDdy'),(62,'Coordenadoria de Gestão','avelar.argolo@ctb.ba.gov.br','000000','USUARIOPADRAO','Avelar Argolo','3612-1209','Avelar',NULL,29,NULL),(63,'Assessor Técnico','andre.cury@ctb.ba.gov.br','Externo','USUARIOPADRAO','André Cury ','3116-4993','André',NULL,30,NULL),(64,'Coordenadora Administrativa','andreaf.rodrigues@ctb.ba.gov.br','A providenciar','USUARIOPADRAO','Andréa Rodrigues','3612-1222','Andrea',NULL,12,NULL),(65,'Assessor Administrativo II','prfreitas@ctb.ba.gov.br','34400243-7','USUARIOPADRAO','Paulo Freitas','1243*','Paulo freitas',NULL,4,NULL),(66,'Assessor Técnico','lisley.brito@ctb.ba.gov.br','0000000000000','USUARIOPADRAO','Lisley de Aragão Brito','3612-1206','Não tem',NULL,10,NULL),(67,'Assessor Técnico','danilo.cafe@ctb.ba.gov.br','0000','USUARIOPADRAO','Danilo Café','3612-1227','Danilo',NULL,20,NULL),(68,'Estagiario(a)','null','°???','USUARIOPADRAO','Estagiário(a) Juridico','3612-1207','null',NULL,18,NULL);
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

-- Dump completed on 2017-04-07 16:34:44
