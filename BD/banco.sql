DROP DATABASE IF EXISTS `banco`;
CREATE DATABASE `banco`;
USE `banco`;
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `username` varchar(20) NOT NULL,
  `password` varchar(25) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `telefono` varchar(30) NOT NULL,
  `cuentas` int(2) NOT NULL,
  `email` varchar(40) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `sesion` tinyint(1) NOT NULL,
  `block` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `usuario` VALUES ('Alex14','123456','Miguel Alejandro','Nava Salazar','5535497898',1,'alexpelusin@gmail.com','Insurgentes #16 san pedro chiconcuac',0,0),('Bruce','Mimosoraton','Fernando','Corona Jimenez','5555898907',0,'Macbofin@gmail.com','los fresnos #34 cuautitlan estado de mexico',0,0),('Jergito','Perro123$','Jorge Armando','Rodriguez Gonzalez','5510089887',2,'jorgerupiita@gmail.com','Raza #94 ciudad de mexico ',0,0),('Jorge3','Perro123$','Jorge Armando','Rodriguez','5543472584',0,'jor.luni.rodri@gmail.com','Calle 9',0,0);

DROP TABLE IF EXISTS `registro`;
CREATE TABLE `registro` (
  `numerodecuenta` int(10) NOT NULL,
  `nip` varchar(4) NOT NULL,
  `uso` tinyint(1) NOT NULL,
  `saldo` float NOT NULL,
  PRIMARY KEY (`numerodecuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `registro` VALUES (361200,'1435',0,7500),(362300,'1435',0,10500),(363100,'1435',0,7892),(367800,'1435',1,4500),(368200,'1435',1,8500),(368700,'1435',1,50000),(368900,'1435',1,3500);

DROP TABLE IF EXISTS `cuenta`;

CREATE TABLE `cuenta` (
  `numerodecuenta` int(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `nip` varchar(4) NOT NULL,
  `saldo` float NOT NULL,
  PRIMARY KEY (`numerodecuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `cuenta` VALUES (367800,'Alex14','1435',4500),(368700,'Jergito','1435',2500),(368900,'Jergito','1435',3500);

DROP TABLE IF EXISTS `movimientos`;

CREATE TABLE `movimientos` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `numerodecuenta` int(10) NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `monto` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
