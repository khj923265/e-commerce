-- MariaDB dump 10.19-11.0.2-MariaDB, for osx10.18 (arm64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	10.11.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `my_topic_users`
--

DROP TABLE IF EXISTS `my_topic_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `my_topic_users` (
  `id` int(11) NOT NULL,
  `user_id` text DEFAULT NULL,
  `pwd` text DEFAULT NULL,
  `name` text DEFAULT NULL,
  `created_at` datetime(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_topic_users`
--

LOCK TABLES `my_topic_users` WRITE;
/*!40000 ALTER TABLE `my_topic_users` DISABLE KEYS */;
INSERT INTO `my_topic_users` VALUES
(1,'user1','test1111','User name','2023-05-09 16:36:21.000'),
(2,'admin','admin1111','administrator','2023-05-09 16:38:27.000'),
(3,'user2','user2222','user2','2023-05-09 16:53:44.000'),
(4,'user3','user3333','user3','2023-05-09 16:53:57.000');
/*!40000 ALTER TABLE `my_topic_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `product_id` varchar(20) NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `qty` int(11) DEFAULT 0,
  `unit_price` int(11) DEFAULT 0,
  `total_price` int(11) DEFAULT 0,
  `created_at` datetime DEFAULT current_timestamp(),
  `create_at` datetime(6) NOT NULL DEFAULT current_timestamp(6),
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES
(1,'38cda569-b28d-4de7-8106-7626f8c1ae90','CATALOG-001','2b2e6417-fdc6-49f5-b0d1-eecdd04dffe6',0,1500,15000,'2023-05-26 16:26:37','2023-05-26 16:26:37.945486',10),
(2,'38cda569-b28d-4de7-8106-7626f8c1ae90','CATALOG-001','1f0c68da-9d23-43c0-ba7a-fa2c116ed238',0,1500,15000,'2023-06-02 16:15:59','2023-06-02 16:15:59.999727',10),
(3,'38cda569-b28d-4de7-8106-7626f8c1ae90','CATALOG-001','71936616-b3e5-43f2-a2a4-345782204eda',0,1500,21000,'2023-06-02 16:16:38','2023-06-02 16:16:38.032357',14),
(4,'38cda569-b28d-4de7-8106-7626f8c1ae90','CATALOG-001','12990e27-6e8c-42b6-a065-761c446aa05f',0,1500,25500,'2023-06-02 16:18:43','2023-06-02 16:18:43.525104',17),
(5,'38cda569-b28d-4de7-8106-7626f8c1ae90','CATALOG-001','cb8189e5-b6ca-4092-9d72-135b2e3b87d7',0,1500,25500,'2023-06-02 16:18:53','2023-06-02 16:18:53.165274',17),
(6,'38cda569-b28d-4de7-8106-7626f8c1ae90','CATALOG-001','819f503a-cf23-4db0-b6ff-fd7bf3c2c6d9',0,1500,25500,'2023-06-02 16:18:53','2023-06-02 16:18:53.925269',17),
(7,'d0c2ff0a-59a4-449b-8b15-64e1ef800049','CATALOG-001','db9a8bd3-2046-4286-88da-f379b668d7f6',0,1500,25500,'2023-07-04 21:18:52','2023-07-04 21:18:52.034596',17),
(8,'cb7738bc-964f-42ba-afaa-fffe8a46850c','CATALOG-001','2d3f5fc4-befc-4524-af56-0193a88f3b5b',0,1500,25500,'2023-07-05 16:21:22','2023-07-05 16:21:22.558697',17),
(9,'cb7738bc-964f-42ba-afaa-fffe8a46850c','CATALOG-002','950cf323-3b31-43ec-a9e1-107cf33d2948',0,2500,12500,'2023-07-05 16:21:55','2023-07-05 16:21:55.072524',5),
(10,'cb7738bc-964f-42ba-afaa-fffe8a46850c','CATALOG-002','0c46b907-549c-40c5-a2a9-0ee1c78f961c',0,2500,12500,'2023-07-05 16:32:48','2023-07-05 16:32:48.255762',5);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'user1','test1111','User name','2023-05-09 16:36:21'),
(2,'admin','admin1111','administrator','2023-05-09 16:38:27'),
(3,'user2','user2222','user2','2023-05-09 16:53:44'),
(4,'user3','user3333','user3','2023-05-09 16:53:57');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-09 22:32:31
