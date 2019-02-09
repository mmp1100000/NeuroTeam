-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: localhost    Database: neuroteam
-- ------------------------------------------------------
-- Server version	8.0.13

create database neuroteam;
use neuroteam;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Medico`
--

DROP TABLE IF EXISTS `Medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Medico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `licencia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medico`
--

LOCK TABLES `Medico` WRITE;
/*!40000 ALTER TABLE `Medico` DISABLE KEYS */;
INSERT INTO `Medico` VALUES (1,'Juan','Lopez Gutierrez','2930485'),(2,'Felipe','Gomez Maldonado','3940567'),(3,'Antonio','Gonzalez Godoy','6958493'),(4,'Jose Antonio','Lopez Wilzinki','2940573'),(5,'Javier','Casado','3940598'),(6,'Jaime','Parry','2948567'),(7,'Andres','Rovira Coronado','2934956');
/*!40000 ALTER TABLE `Medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `archivo_medico`
--

DROP TABLE IF EXISTS `archivo_medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `archivo_medico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `documento` varchar(45) DEFAULT NULL,
  `Prueba_medica_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Archivo_medico_Prueba_medica1_idx` (`Prueba_medica_id`),
  CONSTRAINT `fk_Archivo_medico_Prueba_medica1` FOREIGN KEY (`Prueba_medica_id`) REFERENCES `prueba_medica` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archivo_medico`
--

LOCK TABLES `archivo_medico` WRITE;
/*!40000 ALTER TABLE `archivo_medico` DISABLE KEYS */;
/*!40000 ALTER TABLE `archivo_medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `informe`
--

DROP TABLE IF EXISTS `informe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `informe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sintomas` varchar(45) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `diagnostico` varchar(45) DEFAULT NULL,
  `medico_id` int(11) NOT NULL,
  `paciente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Informe_Medico_has_Paciente1_idx` (`medico_id`,`paciente_id`),
  CONSTRAINT `fk_Informe_Medico_has_Paciente1` FOREIGN KEY (`medico_id`, `paciente_id`) REFERENCES `medico_has_paciente` (`medico_id`, `paciente_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informe`
--

LOCK TABLES `informe` WRITE;
/*!40000 ALTER TABLE `informe` DISABLE KEYS */;
INSERT INTO `informe` VALUES (1,'...','2018-05-01 00:00:00','Huntington',1,1),(2,'...','2015-04-09 00:00:00','Epilepsia',4,8),(3,'...','2018-10-15 00:00:00','Alzheimer',2,3),(4,'...','2017-12-01 00:00:00','Parkinson',2,4),(5,'...','2016-05-01 00:00:00','Alzheimer',3,5),(6,'...','2017-02-14 00:00:00','Parkinson',3,6),(7,'...','2018-02-12 00:00:00','Huntington',4,7),(8,'...','2015-04-09 00:00:00','Epilepsia',4,8), (9, 'crisis epiléptica, mareos, dificultad para hablar','30-10-2018','Epilepsia',5,9), (10, 'crisis epiléptica, dificultad para hablar, rigidez muscular','23-04-2017','Epilepsia',5,10), (11, 'Fiebre alta, Dolor de cabeza intenso, Rigidez de la nuca, Vómitos bruscos, Somnolencia','02-06-2018','Meningitis',6,11), (12,'Fiebre alta, Manchas de color rojo-púrpura en la piel','25-01-2016','Meningitis',6,12), (13,'Súbita confusión, dificultad para hablar o entender','14-12-2015','Derrame cerebral',6,13), (14,'Súbita confusión, dificultad para hablar o entender','12-08-2017','Derrame cerebral',7,14), (15,'convulsiones','11-11-2015','Tumor cerebral',7,15), (16,'convulsiones','29-01-2018','Tumor cerebral',7,16);
/*!40000 ALTER TABLE `informe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medico_has_paciente`
--

DROP TABLE IF EXISTS `medico_has_paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `medico_has_paciente` (
  `Medico_id` int(11) NOT NULL,
  `Paciente_id` int(11) NOT NULL,
  PRIMARY KEY (`Medico_id`,`Paciente_id`),
  KEY `fk_Medico_has_Paciente_Paciente1_idx` (`Paciente_id`),
  KEY `fk_Medico_has_Paciente_Medico_idx` (`Medico_id`),
  CONSTRAINT `fk_Medico_has_Paciente_Medico` FOREIGN KEY (`Medico_id`) REFERENCES `Medico` (`id`),
  CONSTRAINT `fk_Medico_has_Paciente_Paciente1` FOREIGN KEY (`Paciente_id`) REFERENCES `paciente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medico_has_paciente`
--

LOCK TABLES `medico_has_paciente` WRITE;
/*!40000 ALTER TABLE `medico_has_paciente` DISABLE KEYS */;
INSERT INTO `medico_has_paciente` VALUES (1,1),(1,2),(2,3),(2,4),(3,5),(3,6),(4,7),(4,8),(5,9),(5,10),(6,11),(6,12),(6,13),(7,14),(7,15),(7,16);
/*!40000 ALTER TABLE `medico_has_paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `paciente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  `nss` varchar(45) DEFAULT NULL,
  `genero` varchar(10) DEFAULT NULL,
  `altura` double DEFAULT NULL,
  `peso` double DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (1,'Juan','Palomo García','100200300123','Hombre',1.74,89.25,'1964-10-01'),(2,'Pedro','González Caballero','120200990183','Hombre',1.71,73.75,'1997-01-20'),(3,'Lourdes','García Maldonado','100200300123','Mujer',1.74,89.25,'1964-10-01'),(4,'Juana','Palomo García','200245630123','Mujer',1.68,61,'1979-09-04'),(5,'Sofía María','Romero Díaz','992020035321','Mujer',1.84,81.25,'1964-03-02'),(6,'Patricia','Palomo García','23054037013','Mujer',1.67,57.24,'1990-08-19'),(7,'Eduardo','Montosa Fernández','032133256744','Hombre',1.72,73.25,'1994-02-01'),(8,'Narciso','López Ramírez','123123675649','Hombre',1.68,80.35,'1975-10-11'),(9,'María del Mar','Beltrán Sánchez','100200300123','Hombre',1.74,89.25,'1964-10-01'),(10,'Carmen','Aguilera García','104400300123','Mujer',1.58,58.25,'1934-07-29'),(11,'María del Pilar','Ruiz Roca','583729487591','Mujer',1.57,70.21,'1927-03-23'),(12,'Fernanda','Martínez Soria','100200378453','Mujer',1.67,60.15,'1946-05-16'),(13,'José Manuel','Suárez del Río','123166579100','Hombre',1.74,71.25,'2003-06-20'),(14,'Clara','García Cano','187785426712','Mujer',1.74,59.25,'1954-12-01'),(15,'Julia','Ramos Gómez','235465768312','Mujer',1.39,38.9,'2009-01-27'),(16,'Juana','Martos Quesada','100200344513','Mujer',1.64,54.33,'1969-04-15');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prueba_medica`
--

DROP TABLE IF EXISTS `prueba_medica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prueba_medica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` varchar(45) DEFAULT NULL,
  `conclusion` varchar(45) DEFAULT NULL,
  `Informe_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Prueba_medica_Informe1_idx` (`Informe_id`),
  CONSTRAINT `fk_Prueba_medica_Informe1` FOREIGN KEY (`Informe_id`) REFERENCES `informe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prueba_medica`
--

LOCK TABLES `prueba_medica` WRITE;
/*!40000 ALTER TABLE `prueba_medica` DISABLE KEYS */;
/*!40000 ALTER TABLE `prueba_medica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tratamiento`
--

DROP TABLE IF EXISTS `tratamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tratamiento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_ini` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `posologia` varchar(45) DEFAULT NULL,
  `Informe_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Tratamiento_Informe1_idx` (`Informe_id`),
  CONSTRAINT `fk_Tratamiento_Informe1` FOREIGN KEY (`Informe_id`) REFERENCES `informe` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tratamiento`
--

LOCK TABLES `tratamiento` WRITE;
/*!40000 ALTER TABLE `tratamiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `tratamiento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-03 13:53:29
