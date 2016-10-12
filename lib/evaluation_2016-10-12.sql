# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.7.11)
# Database: evaluation
# Generation Time: 2016-10-12 09:17:57 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table course
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bint` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;

INSERT INTO `course` (`id`, `bint`, `name`)
VALUES
	(1,'bint.1234','Distribuerede Systemer'),
	(2,'bint.5678','VØS 9000'),
	(3,'bint.91011','Makro');

/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table lecture
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lecture`;

CREATE TABLE `lecture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_LECTURE_COURSE_idx` (`course_id`),
  CONSTRAINT `FK_LECTURE_COURSE` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;

INSERT INTO `lecture` (`id`, `date`, `course_id`)
VALUES
	(1,'2016-09-28 10:07:22',1),
	(2,'2016-09-28 10:32:58',2),
	(3,'2016-09-28 10:33:04',2),
	(4,'2016-09-28 10:33:09',3);

/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table review
# ------------------------------------------------------------

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` int(11) NOT NULL,
  `comment` varchar(600) DEFAULT NULL,
  `lecture_id` int(11) NOT NULL,
  `cbs_mail` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_REVIEW_LECTURE_idx` (`lecture_id`),
  KEY `FK_REVIEW_USER` (`cbs_mail`),
  CONSTRAINT `FK_REVIEW_LECTURE` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_REVIEW_USER` FOREIGN KEY (`cbs_mail`) REFERENCES `user` (`cbs_mail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;

INSERT INTO `review` (`id`, `rating`, `comment`, `lecture_id`, `cbs_mail`)
VALUES
	(3,3,'Kæft det var feeed',1,'test@student.cbs.dk'),
	(4,5,'mega nice',1,'test@student.cbs.dk'),
	(5,1,'Hvad var det lige Billes job var?',2,'testmail@student.cbs.dk');

/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `cbs_mail` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cbsMail` (`cbs_mail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `password`, `type`, `cbs_mail`)
VALUES
	(1,'1234','admin','kafr15ae@student.cbs.dk'),
	(2,'pass','student','test@student.cbs.dk'),
	(3,'pass','student','testmail@student.cbs.dk');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table usercourse
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usercourse`;

CREATE TABLE `usercourse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userID` (`user_id`),
  KEY `courseID` (`course_id`),
  CONSTRAINT `courseID` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `usercourse` WRITE;
/*!40000 ALTER TABLE `usercourse` DISABLE KEYS */;

INSERT INTO `usercourse` (`id`, `user_id`, `course_id`)
VALUES
	(1,1,1),
	(4,1,2),
	(5,2,1),
	(6,2,2),
	(7,2,3);

/*!40000 ALTER TABLE `usercourse` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
