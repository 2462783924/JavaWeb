-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: school
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `no` varchar(10) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `major` varchar(10) DEFAULT NULL,
  `sclass` varchar(10) DEFAULT NULL,
  `pic` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1001,'01','小明','信工','外包','1.jpg'),(1002,'02','小红','信工','外包','2.jpg'),(1003,'03','张三','药学','西药','3.jpg'),(1004,'04','王小东','药学','中药','1.jpg'),(1005,'05','李明华','中文','国语','2.jpg'),(1006,'06','学生1','m1','s1','3.jpg'),(1007,'07','学生2','m2','s2','1.jpg'),(1008,'08','学生3','m3','s3','2.jpg'),(1009,'09','学生4','m4','s4','3.jpg'),(11111,'123','学生111','信工','计算机','屏幕截图_20221202_205209.png');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stuinfo`
--

DROP TABLE IF EXISTS `stuinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stuinfo` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NICK` varchar(20) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `STUNO` varchar(10) DEFAULT NULL,
  `STUNAME` varchar(20) DEFAULT NULL,
  `SEX` varchar(5) DEFAULT NULL,
  `AGE` varchar(5) DEFAULT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `PIC` varchar(45) DEFAULT NULL,
  `INTPODUCE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NICK_UNIQUE` (`NICK`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stuinfo`
--

LOCK TABLES `stuinfo` WRITE;
/*!40000 ALTER TABLE `stuinfo` DISABLE KEYS */;
INSERT INTO `stuinfo` VALUES (1,'test1','student1','1001','小明','男','18','111111','111111@qq.com','1.jpg','学生1'),(2,'test2','Sstudent2!','1002','小红','女','16','18622222222','222222@qq.com','4.jpg','学生2'),(3,'test3','student3','1003','小东','男','17','333333','333333@qq.com','3.jpg','学生3'),(4,'admin','21232f297a57a5a743894a0e4a801fc3','0000','管理员','男','20','123456','admin@vip.com','4.jpg','管理员用户'),(5,'test10','!Sstudent2','1005','学生5','男','16','18655555555','555555@qq.com','4.jpg','学生5');
/*!40000 ALTER TABLE `stuinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'school'
--

--
-- Dumping routines for database 'school'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 15:02:19
