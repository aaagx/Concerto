-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: concerto
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `message_content` varchar(100) NOT NULL DEFAULT '进入项目成功' COMMENT '进入项目成功\\失败  \n用户管理的项目有新的申请\n......',
  `message_status` int(11) NOT NULL DEFAULT '0' COMMENT '0：未读\n1：已读',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '"即邀请码"',
  `project_name` varchar(45) NOT NULL,
  `project_description` varchar(45) DEFAULT NULL,
  `project_start_time` date NOT NULL,
  `project_end_time` date NOT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_task`
--

DROP TABLE IF EXISTS `project_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_task` (
  `project_task_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  PRIMARY KEY (`project_task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_task`
--

LOCK TABLES `project_task` WRITE;
/*!40000 ALTER TABLE `project_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_content` varchar(45) NOT NULL,
  `tag_color` varchar(45) NOT NULL DEFAULT '#f7f7f7',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL,
  `task_status` int(11) NOT NULL DEFAULT '0' COMMENT '完成状态\n0：未完成\n1：已完成',
  `task_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号\n',
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_comment`
--

DROP TABLE IF EXISTS `task_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_comment` (
  `task_comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `comment_content` varchar(200) NOT NULL DEFAULT '倒是说点啥啊',
  `comment_time` datetime NOT NULL,
  `task_comment_user_id` int(11) NOT NULL,
  PRIMARY KEY (`task_comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_comment`
--

LOCK TABLES `task_comment` WRITE;
/*!40000 ALTER TABLE `task_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_participant_operation`
--

DROP TABLE IF EXISTS `task_participant_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_participant_operation` (
  `task_participant_operation_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `task_version` int(11) NOT NULL DEFAULT '0',
  `task_operation_type` int(11) NOT NULL DEFAULT '1' COMMENT '1：增加 \n-1： 删除',
  `participant_id` int(11) NOT NULL COMMENT '参与者id',
  PRIMARY KEY (`task_participant_operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_participant_operation`
--

LOCK TABLES `task_participant_operation` WRITE;
/*!40000 ALTER TABLE `task_participant_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_participant_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_subtask`
--

DROP TABLE IF EXISTS `task_subtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_subtask` (
  `task_subtask_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `subtask_id` int(11) NOT NULL,
  PRIMARY KEY (`task_subtask_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_subtask`
--

LOCK TABLES `task_subtask` WRITE;
/*!40000 ALTER TABLE `task_subtask` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_subtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_tag`
--

DROP TABLE IF EXISTS `task_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_tag` (
  `task_tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`task_tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_tag`
--

LOCK TABLES `task_tag` WRITE;
/*!40000 ALTER TABLE `task_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_tag_operation`
--

DROP TABLE IF EXISTS `task_tag_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_tag_operation` (
  `task_tag_operation_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `task_version` int(11) NOT NULL DEFAULT '0',
  `task_operation_type` int(11) NOT NULL DEFAULT '1' COMMENT '1：增加 \n-1： 删除',
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`task_tag_operation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_tag_operation`
--

LOCK TABLES `task_tag_operation` WRITE;
/*!40000 ALTER TABLE `task_tag_operation` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_tag_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_user`
--

DROP TABLE IF EXISTS `task_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_user` (
  `task_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`task_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_user`
--

LOCK TABLES `task_user` WRITE;
/*!40000 ALTER TABLE `task_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_version`
--

DROP TABLE IF EXISTS `task_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task_version` (
  `task_version_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `task_version` int(11) NOT NULL DEFAULT '0' COMMENT '版本号',
  `task_version_description` varchar(100) DEFAULT NULL COMMENT '提交修改的时候生成，描述任务修改信息',
  `task_title` varchar(45) NOT NULL,
  `task_type` varchar(45) NOT NULL DEFAULT '0' COMMENT '0：任务 \n1：子任务 \n2：里程碑',
  `task_priority` varchar(45) NOT NULL DEFAULT '0' COMMENT '0：普通\n1：有点紧急又不太紧急 \n2：紧急 \n\n',
  `task_start_time` date NOT NULL,
  `task_end_time` date NOT NULL,
  `task_version_modify_time` datetime NOT NULL COMMENT '修改时间',
  `task_version_modify_user_id` int(11) NOT NULL COMMENT '修改人id',
  PRIMARY KEY (`task_version_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_version`
--

LOCK TABLES `task_version` WRITE;
/*!40000 ALTER TABLE `task_version` DISABLE KEYS */;
/*!40000 ALTER TABLE `task_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_phone` varchar(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_salt` varchar(45) DEFAULT NULL,
  `user_introducton` varchar(100) DEFAULT NULL COMMENT '''个人介绍''',
  `user_token` varchar(45) DEFAULT NULL COMMENT '''全局唯一 xx+user_id 加密''',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_phone_UNIQUE` (`user_phone`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_advice`
--

DROP TABLE IF EXISTS `user_advice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_advice` (
  `user_advice_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `advice_content` varchar(200) NOT NULL DEFAULT '倒是说点啥啊2.0',
  PRIMARY KEY (`user_advice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_advice`
--

LOCK TABLES `user_advice` WRITE;
/*!40000 ALTER TABLE `user_advice` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_advice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_project`
--

DROP TABLE IF EXISTS `user_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_project` (
  `user_project_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `user_role` int(11) NOT NULL DEFAULT '0' COMMENT '加入项目 未通过准入则为候选人\n\n0：成员 \n1：管理者 \n2：候选人',
  PRIMARY KEY (`user_project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_project`
--

LOCK TABLES `user_project` WRITE;
/*!40000 ALTER TABLE `user_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_task`
--

DROP TABLE IF EXISTS `user_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_task` (
  `user_task_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  PRIMARY KEY (`user_task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_task`
--

LOCK TABLES `user_task` WRITE;
/*!40000 ALTER TABLE `user_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'concerto'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-22 18:09:28
