-- MySQL dump 10.13  Distrib 5.7.11, for osx10.9 (x86_64)
--
-- Host: localhost    Database: project_eva
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) NOT NULL DEFAULT '',
  `study_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `study_id` (`study_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`study_id`) REFERENCES `study` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'BINT2020','DIS',1),(2,'BINT2021','ITF',1),(3,'BINT2022','VØS2',1),(4,'BINT2023','MAK',1),(5,'BBLS0101','INTC',2),(6,'BBAL0202','DLS',2),(7,'BBTS','ITP',3),(8,'BBNT','DLT',3),(9,'BBST','MOB',4),(10,'BBMS','FBGM',4);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_attendant`
--

DROP TABLE IF EXISTS `course_attendant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_attendant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_attendant`
--

LOCK TABLES `course_attendant` WRITE;
/*!40000 ALTER TABLE `course_attendant` DISABLE KEYS */;
INSERT INTO `course_attendant` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,2,1),(6,2,2),(7,2,3),(8,2,4),(9,3,5),(10,3,6),(11,4,5),(12,4,6),(13,5,5),(14,5,6),(15,6,7),(16,6,8),(17,7,7),(18,7,8),(19,11,1),(20,12,1),(21,13,1),(22,14,2),(23,15,2);
/*!40000 ALTER TABLE `course_attendant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `location` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_LECTURE_COURSE_idx` (`course_id`),
  CONSTRAINT `FK_LECTURE_COURSE` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1,1,'LA','Lecture','2016-10-02 10:00:00','2016-10-02 11:30:00',''),(2,2,'XB','Exercise','2016-10-02 11:40:00','2016-10-02 13:20:00',''),(3,3,'LA','Lecture','2016-10-01 12:30:00','2016-10-01 14:00:00',''),(4,1,'XB','Excercise','2016-10-01 11:00:00','2016-10-01 12:30:00',''),(5,1,'LA','Lecture','2016-10-03 10:00:00','2016-10-01 11:30:00',''),(6,2,'LA','Lecture','2016-10-03 13:30:00','2016-10-03 15:10:00',''),(7,2,'LA','Lecture','2016-10-04 08:00:00','2016-10-04 09:50:00',''),(8,3,'XB','Exercise','2016-10-04 10:00:00','2016-10-04 11:30:00','');
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lecture_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `comment` varchar(500) DEFAULT '',
  `comment_is_deleted` bit(1) NOT NULL,
  `cbs_mail` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `lecture_id` (`lecture_id`),
  KEY `cbs_mail` (`cbs_mail`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`cbs_mail`) REFERENCES `user` (`cbs_mail`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,3,'','\0','ab1cd@student.cbs.dk'),(2,1,4,'','\0','ab2cd@student.cbs.dk'),(3,1,5,'Fed time','\0','ab4cd@student.cbs.dk'),(4,1,1,'ubrugelig time','\0','ab5cd@student.cbs.dk'),(5,1,5,'rigtig godt lærer','\0','ab3cd@student.cbs.dk'),(6,2,1,'dårlig time','\0','te1bo@student.cbs.dk'),(7,2,1,'Læreren er til grin','','te1bo@student.cbs.dk'),(8,2,2,'mangler forståelse','\0','te1bo@student.cbs.dk'),(9,2,2,'','\0','te1bo@student.cbs.dk'),(10,2,2,'','\0','te1bo@student.cbs.dk'),(11,3,5,'super time','\0','te1bo@student.cbs.dk'),(12,3,4,'rigtig godt','\0','te1bo@student.cbs.dk'),(13,3,5,'','\0','te1bo@student.cbs.dk'),(14,3,5,'','\0','te1bo@student.cbs.dk'),(15,3,1,'fik ikke noget ud af det','\0','te1bo@student.cbs.dk');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `study` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (1,'HA(it.)'),(2,'BA(im.)'),(3,'HA(mat.)'),(4,'HA(jur.)'),(5,'HA(psyk.)'),(6,'HA(kom.)'),(7,'BALJO'),(8,'BALKO'),(9,'BAPPO'),(10,'BASPO'),(11,'BASPU'),(12,'BBLCO'),(13,'BBLCU'),(14,'BBLCV'),(15,'BBLFO'),(16,'BBLSO'),(17,'BBLTO'),(18,'BEOKO'),(19,'BEOKU'),(20,'BEOKV'),(21,'BEUBO'),(22,'BEUBU'),(23,'BEUBV'),(24,'BEUEO'),(25,'BEUFO'),(26,'BEUSO'),(27,'BEUTO'),(28,'BFILO'),(29,'BFILU'),(30,'BFILV'),(31,'BHAAO'),(32,'BHAAU'),(33,'BHAAV'),(34,'BHAEO'),(35,'BHAUO'),(36,'BIMEO'),(37,'BIMFO'),(38,'BIMFU'),(39,'BIMKO'),(40,'BIMKU'),(41,'BIMKV'),(42,'BIMSO'),(43,'BIMSU'),(44,'BIMTO'),(45,'BIMTU'),(46,'BINBO'),(47,'BINBU'),(48,'BINBV'),(49,'BINMO'),(50,'BINMU'),(51,'BINTO'),(52,'BINTU'),(53,'BINTV'),(54,'BISHO'),(55,'BISHU'),(56,'BISHV'),(57,'BIVKU'),(58,'BIVTU'),(59,'BJURO'),(60,'BJURU'),(61,'BJURV'),(62,'BKOMO'),(63,'BKOMU'),(64,'BKOMV'),(65,'BMATO'),(66,'BMATU'),(67,'BMATV'),(68,'BPOLO'),(69,'BPOLU'),(70,'BPROO'),(71,'BPROU'),(72,'BPROV'),(73,'BPSYO'),(74,'BPSYU'),(75,'BPSYV'),(76,'BSACO'),(77,'BSEMO'),(78,'BSEMU'),(79,'BSEMV'),(80,'BSOCO'),(81,'BSOCU'),(82,'BSOCV'),(83,'BSSIO'),(84,'BSTHO'),(85,'CAEFO'),(86,'CAEFU'),(87,'CASCO'),(88,'CAUHO'),(89,'CBCMO'),(90,'CBIOO'),(91,'CCBDO'),(92,'CCBLV'),(93,'CCDCO'),(94,'CCMAO'),(95,'CCMAV'),(96,'CCMIU'),(97,'CCMIV'),(98,'CCMVU'),(99,'CCMVV'),(100,'CEBUO'),(101,'CEBUV'),(102,'CEMFO'),(103,'CFIRO'),(104,'CFIVO'),(105,'CFSMO'),(106,'CFSMU'),(107,'CHRMO'),(108,'CHRMU'),(109,'CIBCV'),(110,'CIBSO'),(111,'CICOO'),(112,'CIHCO'),(113,'CIMMO'),(114,'CINTO'),(115,'CINTV'),(116,'CJURO'),(117,'CJURV'),(118,'CKOMO'),(119,'CKOMU'),(120,'CKOMV'),(121,'CMATO'),(122,'CMATV'),(123,'CMIBO'),(124,'CMLMV'),(125,'COECO'),(126,'COECV'),(127,'CPHIO'),(128,'CPHIU'),(129,'CPHIV'),(130,'CPOLO'),(131,'CPOLU'),(132,'CPSYO'),(133,'CPSYV'),(134,'CSCBO'),(135,'CSCEO'),(136,'CSHRO'),(137,'CSIEO'),(138,'CSMCO'),(139,'CSOCU'),(140,'CSOCV'),(141,'CSOLO'),(142,'CSOLU'),(143,'CSPKO'),(144,'CSSMO'),(145,'D1FMO'),(146,'D1FMU'),(147,'D1FMV'),(148,'DFINO'),(149,'DFINV'),(150,'DFIRO'),(151,'DFIRU'),(152,'DFIRV'),(153,'DIMAO'),(154,'DINBO'),(155,'DINBV'),(156,'DMAMO'),(157,'DMAMV'),(158,'DOPLO'),(159,'DOPLV'),(160,'DORGO'),(161,'DORGV'),(162,'DREGO'),(163,'DREGV'),(164,'DSCMO'),(165,'DSCMV'),(166,'MMASO'),(167,'MMASV'),(168,'MMMDO'),(169,'MMPAO'),(170,'MMPAU'),(171,'MMPAV'),(172,'MMPGO'),(173,'MMPGV');
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cbs_mail` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `type` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cbs_mail` (`cbs_mail`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'te1bo@student.cbs.dk','1','student'),(2,'ab1cd@student.cbs.dk','1','student'),(3,'ab2cd@student.cbs.dk','1','student'),(4,'ab3cd@student.cbs.dk','1','student'),(5,'ab4cd@student.cbs.dk','1','student'),(6,'ab5cd@student.cbs.dk','1','student'),(7,'ab6cd@student.cbs.dk','1','student'),(8,'ab7cd@student.cbs.dk','1','student'),(9,'ab8cd@student.cbs.dk','1','student'),(10,'ab9cd@student.cbs.dk','1','student'),(11,'jbh.itm@cbs.dk','1','teacher'),(12,'ht.itm@cbs.dk','1','teacher'),(13,'kt.itm@cbs.dk','1','teacher'),(14,'ihf.itm@cbs.dk','1','teacher'),(15,'jss.itm@cbs.dk','1','teacher'),(16,'admin@admin.cbs.dk','1','admin'),(17,'jas.om@cbs.dk','1','teacher'),(18,'phh.om@cbs.dk','1','teacher');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-19 13:25:22
