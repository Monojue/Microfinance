-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.45-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema micro
--

CREATE DATABASE IF NOT EXISTS micro;
USE micro;

--
-- Definition of table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `ClientID` varchar(10) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `NRC` varchar(45) default NULL,
  `Address` varchar(100) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `DateOfBirth` varchar(20) NOT NULL,
  `Home` tinyint(1) NOT NULL,
  `Job` varchar(45) NOT NULL,
  `Salary` int(10) unsigned NOT NULL,
  `GName` varchar(45) default NULL,
  `GJob` varchar(45) default NULL,
  `GSalary` int(10) unsigned default NULL,
  `Relationship` varchar(45) default NULL,
  `GAddress` varchar(45) default NULL,
  `GPhone` varchar(45) default NULL,
  `GNRC` varchar(45) default NULL,
  PRIMARY KEY  USING BTREE (`ClientID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`ClientID`,`Name`,`NRC`,`Address`,`Phone`,`DateOfBirth`,`Home`,`Job`,`Salary`,`GName`,`GJob`,`GSalary`,`Relationship`,`GAddress`,`GPhone`,`GNRC`) VALUES 
 ('CL-0000001','Kaung Myat Thet','1/-AhGaYa-(N)-012345','Home|Yangon|Yangon','0124578965','2-MARCH-1967',0,'Programmer',300000,'adfd','adfdf',600000,'adfda','dfda|dafdaf|adfdsa','91234567','3/-KaDaNa-N-123121'),
 ('CL-0000002','Nyi Lin Htin','3/-BaAhNa-(N)-012345','No.89,Home Road|Yangon|Yangon','112345678','4-MARCH-1989',1,'Programmar',2000000,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 ('CL-0000003','Kaung Si Thu','4/-HaKhaNa-(N)-012457','Nani Road ,Notrh Dagon|Yangon|Yangon','124589887','3-MARCH-1989',0,'Programmar',2045289,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 ('CL-0000004','Aung Myat Thu','3/-BaAhNa-(N)-012458','Lol Road, east Dagon|Yangon|Yangon','101245799','5-APRIL-1992',0,'Programmar',45454254,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 ('CL-0000005','Khaing Su Mon','4/-HaKhaNa-(N)-012458','U sein Kyi Road, |Yangon|Yangon','124589670','4-MAY-1970',1,'Tester',8025698,'fdafd','fadf',200000,'adf','No.89, DamaThiri Road, East Dagon|Yango|nYang','97845784','1/-AhGaYa-N-012457'),
 ('CL-0000006','Thinzar Shwe Ye','4/-MaTaNa-(N)-012458','Shwe Shwe Pyi Road|Yangon|Yangon','124589688','4-MAY-1975',1,'Tester',8000000,'fdafdaf','dgfsdg',800000,'adfda','efdasfd|afdaf|dafd','91234578','5/-AhRaTa-N-012457');
INSERT INTO `client` (`ClientID`,`Name`,`NRC`,`Address`,`Phone`,`DateOfBirth`,`Home`,`Job`,`Salary`,`GName`,`GJob`,`GSalary`,`Relationship`,`GAddress`,`GPhone`,`GNRC`) VALUES 
 ('CL-0000007','Khin Ohmar','4/-HaKhaNa-(N)-124589','kitty Road|Yangon|Yangon','095847785','5-APRIL-1984',1,'adfdsafd',500000,'Kaugn','Proa',500000,'Father','No.89, DamaThiri Road, East Dagon|Yangon|Yad','94512785','1/-AhGaYa-N-124578'),
 ('CL-0000008','Testing Client','4/-HaKhaNa-(N)-124588','No.89, DamaThiri Road, East Dagon|Yangon|Yangon','09568974589','5-APRIL-1996',1,'Programmar',500000,'Guarantor','Merchant',500000,'father','No.89, DamaThiri Road|Yangon|Yangon','945781254','1/-AhGaYa-N-124578');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;


--
-- Definition of table `clientdetails`
--

DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `ClientID` varchar(10) NOT NULL,
  `LoanRequestID` varchar(10) NOT NULL,
  `RequestDate` varchar(11) NOT NULL,
  `DueDate` varchar(11) default NULL,
  KEY `FK_clientdetails_1` (`ClientID`),
  KEY `FK_clientdetails_2` (`LoanRequestID`),
  CONSTRAINT `FK_clientdetails_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`),
  CONSTRAINT `FK_clientdetails_2` FOREIGN KEY (`LoanRequestID`) REFERENCES `loanrequest` (`LoanRequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clientdetails`
--

/*!40000 ALTER TABLE `clientdetails` DISABLE KEYS */;
INSERT INTO `clientdetails` (`ClientID`,`LoanRequestID`,`RequestDate`,`DueDate`) VALUES 
 ('CL-0000007','LR-0000001','2020-Sep-30',NULL),
 ('CL-0000008','LR-0000001','2020-Sep-30',NULL);
/*!40000 ALTER TABLE `clientdetails` ENABLE KEYS */;


--
-- Definition of table `clientgroup`
--

DROP TABLE IF EXISTS `clientgroup`;
CREATE TABLE `clientgroup` (
  `GroupID` varchar(10) NOT NULL,
  `Leader` varchar(10) NOT NULL,
  `Member_1` varchar(10) NOT NULL,
  `Member_2` varchar(10) NOT NULL,
  `Member_3` varchar(10) NOT NULL,
  `Member_4` varchar(10) NOT NULL,
  `leaderName` varchar(45) NOT NULL,
  `M1Name` varchar(45) NOT NULL,
  `M2Name` varchar(45) NOT NULL,
  `M3Name` varchar(45) NOT NULL,
  `M4Name` varchar(45) NOT NULL,
  PRIMARY KEY  (`GroupID`),
  KEY `FK_Group_1` USING BTREE (`Leader`),
  KEY `FK_Group_2` USING BTREE (`Member_1`),
  KEY `FK_Group_3` USING BTREE (`Member_2`),
  KEY `FK_Group_4` USING BTREE (`Member_3`),
  KEY `FK_Group_5` USING BTREE (`Member_4`),
  CONSTRAINT `FK_Group_1` FOREIGN KEY (`Leader`) REFERENCES `client` (`ClientID`),
  CONSTRAINT `FK_Group_2` FOREIGN KEY (`Member_1`) REFERENCES `client` (`ClientID`),
  CONSTRAINT `FK_Group_3` FOREIGN KEY (`Member_2`) REFERENCES `client` (`ClientID`),
  CONSTRAINT `FK_Group_4` FOREIGN KEY (`Member_3`) REFERENCES `client` (`ClientID`),
  CONSTRAINT `FK_Group_5` FOREIGN KEY (`Member_4`) REFERENCES `client` (`ClientID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clientgroup`
--

/*!40000 ALTER TABLE `clientgroup` DISABLE KEYS */;
INSERT INTO `clientgroup` (`GroupID`,`Leader`,`Member_1`,`Member_2`,`Member_3`,`Member_4`,`leaderName`,`M1Name`,`M2Name`,`M3Name`,`M4Name`) VALUES 
 ('GP-0000001','CL-0000001','CL-0000002','CL-0000003','CL-0000004','CL-0000006','Kaung Myat Thet','Nyi Lin Htin','Kaung Si Thu','Aung Myat Thu','Thinzar Shwe Ye');
/*!40000 ALTER TABLE `clientgroup` ENABLE KEYS */;


--
-- Definition of table `groupdetails`
--

DROP TABLE IF EXISTS `groupdetails`;
CREATE TABLE `groupdetails` (
  `GroupID` varchar(10) NOT NULL,
  `LoanRequestID` varchar(10) NOT NULL,
  `RequestDate` varchar(15) NOT NULL,
  `DueDate` varchar(15) default NULL,
  KEY `FK_GroupDetails_1` (`GroupID`),
  KEY `FK_GroupDetails_2` (`LoanRequestID`),
  CONSTRAINT `FK_GroupDetails_1` FOREIGN KEY (`GroupID`) REFERENCES `clientgroup` (`GroupID`),
  CONSTRAINT `FK_GroupDetails_2` FOREIGN KEY (`LoanRequestID`) REFERENCES `loanrequest` (`LoanRequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupdetails`
--

/*!40000 ALTER TABLE `groupdetails` DISABLE KEYS */;
INSERT INTO `groupdetails` (`GroupID`,`LoanRequestID`,`RequestDate`,`DueDate`) VALUES 
 ('GP-0000001','LR-0000002','2020-Sep-30',NULL);
/*!40000 ALTER TABLE `groupdetails` ENABLE KEYS */;


--
-- Definition of table `loanrequest`
--

DROP TABLE IF EXISTS `loanrequest`;
CREATE TABLE `loanrequest` (
  `LoanRequestID` varchar(10) NOT NULL,
  `LoanType` varchar(10) default NULL,
  `Amount` int(10) unsigned NOT NULL,
  `Duration` int(10) unsigned NOT NULL,
  `InterestRate` int(10) unsigned NOT NULL,
  `PayDay` datetime default NULL,
  `Approved` tinyint(1) default NULL,
  `Remark` varchar(100) default NULL,
  PRIMARY KEY  (`LoanRequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loanrequest`
--

/*!40000 ALTER TABLE `loanrequest` DISABLE KEYS */;
INSERT INTO `loanrequest` (`LoanRequestID`,`LoanType`,`Amount`,`Duration`,`InterestRate`,`PayDay`,`Approved`,`Remark`) VALUES 
 ('LR-0000001','Individual',100000,6,2,NULL,NULL,NULL),
 ('LR-0000002','Group',100000,6,2,NULL,NULL,NULL);
/*!40000 ALTER TABLE `loanrequest` ENABLE KEYS */;


--
-- Definition of table `loansetting`
--

DROP TABLE IF EXISTS `loansetting`;
CREATE TABLE `loansetting` (
  `ID` varchar(10) NOT NULL default '',
  `MinAmount` int(10) unsigned NOT NULL,
  `MaxAmount` int(10) unsigned NOT NULL,
  `MinDuration` int(10) unsigned NOT NULL,
  `MaxDuration` int(10) unsigned NOT NULL,
  `AmountInterval` int(10) unsigned NOT NULL,
  `DurationInterval` int(10) unsigned NOT NULL,
  `InterestRate` float NOT NULL,
  `ServiceRate` float NOT NULL,
  `Date` varchar(45) NOT NULL,
  `Type` varchar(10) NOT NULL,
  `OfficerID` varchar(10) NOT NULL,
  PRIMARY KEY  USING BTREE (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loansetting`
--

/*!40000 ALTER TABLE `loansetting` DISABLE KEYS */;
INSERT INTO `loansetting` (`ID`,`MinAmount`,`MaxAmount`,`MinDuration`,`MaxDuration`,`AmountInterval`,`DurationInterval`,`InterestRate`,`ServiceRate`,`Date`,`Type`,`OfficerID`) VALUES 
 ('Ls-0000001',100000,1000000,6,24,100000,3,2.33,1,'2020-09-10','Individual','OF-0000001'),
 ('Ls-0000002',100000,1000000,6,24,100000,3,2.33,1,'2020-09-10','Group','OF-0000001'),
 ('Ls-0000003',100000,1000000,3,24,100000,3,2.33,1,'2020-09-10','Individual','OF-0000001'),
 ('Ls-0000004',100000,1000000,6,24,100000,3,2.33,1,'2020-09-10','Individual','OF-0000001');
/*!40000 ALTER TABLE `loansetting` ENABLE KEYS */;


--
-- Definition of table `nrc`
--

DROP TABLE IF EXISTS `nrc`;
CREATE TABLE `nrc` (
  `No` int(10) unsigned NOT NULL default '0',
  `Number` int(10) unsigned NOT NULL,
  `Code` varchar(10) NOT NULL,
  PRIMARY KEY  (`No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nrc`
--

/*!40000 ALTER TABLE `nrc` DISABLE KEYS */;
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (1,1,'KhaLaPha'),
 (2,1,'KhaBaDa'),
 (3,1,'SaPaBa'),
 (4,1,'NaMaNa'),
 (5,1,'PaNaDa'),
 (6,1,'PaTaAh'),
 (7,1,'MaKhaBa'),
 (8,1,'BaMaNa'),
 (9,1,'MaSaNa'),
 (10,1,'MaMaNa'),
 (11,1,'LaGaNa'),
 (12,1,'DaPhaYa'),
 (13,1,'RaKaNa'),
 (14,1,'KhaPhaNa'),
 (15,1,'SaLaNa'),
 (16,1,'TaNaNa'),
 (17,1,'MaKaNa'),
 (18,1,'SaBaNa'),
 (19,1,'WaMaNa'),
 (20,1,'AhGaYa'),
 (21,1,'PhaKaNa'),
 (22,1,'KaMaNa'),
 (23,1,'MaKaTa'),
 (24,1,'MaNyaNa'),
 (25,1,'HaPaNa'),
 (26,2,'LaKaNa'),
 (27,2,'DaMaSa'),
 (28,2,'PhaRaSa'),
 (29,2,'RaTaNa'),
 (30,2,'BaLaKha'),
 (31,2,'RaThaNa'),
 (32,2,'PhaSaNa'),
 (33,2,'MaSaNa'),
 (34,3,'BaAhNa'),
 (35,3,'PaKaNa'),
 (36,3,'LaBaNa'),
 (37,3,'ThaTaKa'),
 (38,3,'ThaTaNa'),
 (39,3,'PhaPaNa'),
 (40,3,'KaMaMa'),
 (41,3,'MaWaTa'),
 (42,3,'KaKaRa'),
 (43,3,'KaSaKa'),
 (44,3,'BaThaSa'),
 (45,3,'KaDaNa'),
 (46,4,'HaKhaNa'),
 (47,4,'HtaTaLa'),
 (48,4,'PhaLaNa'),
 (49,4,'RaKhaDa'),
 (50,4,'TaTaNa'),
 (51,4,'TaZaNa'),
 (52,4,'KaKhaNa'),
 (53,4,'MaTaNa'),
 (54,4,'MaTaPa');
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (55,4,'RaZaNa'),
 (56,4,'KaPaLa'),
 (57,4,'PaLaWa'),
 (58,5,'SaKaNa'),
 (59,5,'MaMaTa'),
 (60,5,'MaMaNa'),
 (61,5,'RaBaNa'),
 (62,5,'KhaOuNa'),
 (63,5,'WaLaNa'),
 (64,5,'KaBaLa'),
 (65,5,'KaLaNa'),
 (66,5,'RaOuNa'),
 (67,5,'DaPaYa'),
 (68,5,'TaSaNa'),
 (69,5,'MaRaNa'),
 (70,5,'BaTaLa'),
 (71,5,'AhRaTa'),
 (72,5,'KhaOuTa'),
 (73,5,'KaThaNa'),
 (74,5,'AhTaNa'),
 (75,5,'HtaKhaNa'),
 (76,5,'BaMaNa'),
 (77,5,'KaLaTa'),
 (78,5,'WaThaNa'),
 (79,5,'PaLaBa'),
 (80,5,'KaLaHta'),
 (81,5,'KaLaWa'),
 (82,5,'MaKaNa'),
 (83,5,'TaMaNa'),
 (84,5,'KhaPaNa'),
 (85,5,'MaThaNa'),
 (86,5,'MaLaNa'),
 (87,5,'PhaPaNa'),
 (88,5,'KhaTaNa'),
 (89,5,'HaMaLa'),
 (90,5,'LaRaNa'),
 (91,5,'LaHaNa'),
 (92,5,'NaYaNa'),
 (93,5,'PaSaNa'),
 (94,5,'YaMaPa'),
 (95,5,'SaLaKa'),
 (96,5,'PaLaNa'),
 (97,5,'KaNaNa'),
 (98,6,'HtaWaNa'),
 (99,6,'MaTaNa'),
 (100,6,'LaLaNa'),
 (101,6,'ThaRaKha'),
 (102,6,'RaPhaNa'),
 (103,6,'KaLaAr'),
 (104,6,'MaMaNa'),
 (105,6,'KaSaNa'),
 (106,6,'PaLaNa'),
 (107,6,'PaLaTa');
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (108,6,'TaNaTha'),
 (109,6,'KaThaNa'),
 (110,6,'KhaMaKa'),
 (111,6,'BaPaNa'),
 (112,6,'KaRaRa'),
 (113,6,'PaKaDa'),
 (114,7,'PaKhaNa'),
 (115,7,'ThaNaPa'),
 (116,7,'KaWaNa'),
 (117,7,'WaMaNa'),
 (118,7,'NyaLaPa'),
 (119,7,'KaTaKha'),
 (120,7,'DaOuNa'),
 (121,7,'RaKaNa'),
 (122,7,'TaNgaNa'),
 (123,7,'RaTaRa'),
 (124,7,'KaKaNa'),
 (125,7,'PhaMaNa'),
 (126,7,'AhTaNa'),
 (127,7,'HtaTaPa'),
 (128,7,'PaMaNa'),
 (129,7,'PaKhaTa'),
 (130,7,'PaTaNa'),
 (131,7,'PaTaTa'),
 (132,7,'ThaKaNa'),
 (133,7,'RaTaNa'),
 (134,7,'ThaWaTa'),
 (135,7,'LaPaTa'),
 (136,7,'MaLaNa'),
 (137,7,'AhPhaNa'),
 (138,7,'ZaKaNa'),
 (139,7,'NaTaLa'),
 (140,7,'MaNyaNa'),
 (141,7,'KaPaKa'),
 (142,8,'MaKaNa'),
 (143,8,'RaNaKha'),
 (144,8,'KhaMaNa'),
 (145,8,'TaTaKa'),
 (146,8,'MaThaNa'),
 (147,8,'NaMaNa'),
 (148,8,'MaBaNa'),
 (149,8,'PaPhaNa'),
 (150,8,'NgaPhaNa'),
 (151,8,'SaLaNa'),
 (152,8,'SaTaRa'),
 (153,8,'ThaRaNa'),
 (154,8,'MaLaNa'),
 (155,8,'MaTaNa'),
 (156,8,'KaMaNa'),
 (157,8,'AhLaNa');
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (158,8,'MaHtaNa'),
 (159,8,'SaPaWa'),
 (160,8,'PaKhaKa'),
 (161,8,'RaSaKa'),
 (162,8,'MaMaNa'),
 (163,8,'PaMaNa'),
 (164,8,'SaPhaNa'),
 (165,8,'GaGaNa'),
 (166,8,'HtaLaNa'),
 (167,8,'SaMaNa'),
 (168,8,'KaHtaNa'),
 (169,9,'AhMaZa'),
 (170,9,'KhaAhZa'),
 (171,9,'MaHaMa'),
 (172,9,'KhaMaSa'),
 (173,9,'PaKaKha'),
 (174,9,'AhMaRa'),
 (175,9,'PaThaKa'),
 (176,9,'PaOuLa'),
 (177,9,'MaMaNa'),
 (178,9,'MaTaRa'),
 (179,9,'SaKaNa'),
 (180,9,'MaKaNa'),
 (181,9,'ThaPaKa'),
 (182,9,'TaKaTa'),
 (183,9,'KaSaNa'),
 (184,9,'SaKaTa'),
 (185,9,'MaThaNa'),
 (186,9,'TaTaOu'),
 (187,9,'MaKhaNa'),
 (188,9,'TaThaNa'),
 (189,9,'NaHtaKa'),
 (190,9,'KaPaTa'),
 (191,9,'NgaZaNa'),
 (192,9,'NyaOuNa'),
 (193,9,'NgaThaRa'),
 (194,9,'RaMaTha'),
 (195,9,'PaBaNa'),
 (196,9,'MaHtaLa'),
 (197,9,'MaLaNa'),
 (198,9,'ThaSaNa'),
 (199,9,'WaTaNa'),
 (200,9,'MaNaTa'),
 (201,9,'MaNaMa'),
 (202,9,'MaRaTa'),
 (203,9,'MaRaMa'),
 (204,10,'MaLaMa'),
 (205,10,'KaMaRa'),
 (206,10,'KhaSaNa'),
 (207,10,'ThaPhaRa');
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (208,10,'MaDaNa'),
 (209,10,'RaMaNa'),
 (210,10,'LaMaNa'),
 (211,10,'ThaHtaNa'),
 (212,10,'PaMaNa'),
 (213,10,'KaHtaNa'),
 (214,10,'BaLaNa'),
 (215,11,'SaTaNa'),
 (216,11,'PaNaKa'),
 (217,11,'PaTaNa'),
 (218,11,'RaThaTa'),
 (219,11,'MaOuNa'),
 (220,11,'KaTaNa'),
 (221,11,'MaPaNa'),
 (222,11,'MaPaTa'),
 (223,11,'MaTaNa'),
 (224,11,'TaPaWa'),
 (225,11,'BaThaTa'),
 (226,11,'KaPhaNa'),
 (227,11,'MaAhNa'),
 (228,11,'RaBaNa'),
 (229,11,'AhMaNa'),
 (230,11,'ThaTaNa'),
 (231,11,'TaKaNa'),
 (232,11,'MaArTa'),
 (233,11,'GaMaNa'),
 (234,11,'KaTaLa'),
 (235,12,'AhSaNa'),
 (236,12,'MaGaDa'),
 (237,12,'MaBaNa'),
 (238,12,'LaKaNa'),
 (239,12,'TaKaNa'),
 (240,12,'HtaTaPa'),
 (241,12,'RaPaTha'),
 (242,12,'LaThaYa'),
 (243,12,'ThaGaKa'),
 (244,12,'RaKaNa'),
 (245,12,'OuKaTa'),
 (246,12,'OuKaMa'),
 (247,12,'ThaKaTa'),
 (248,12,'DaPaNa'),
 (249,12,'TaMaNa'),
 (250,12,'PaZaTa'),
 (251,12,'BaTaHta'),
 (252,12,'DaGaTa'),
 (253,12,'DaGaMa'),
 (254,12,'DaGaRa'),
 (255,12,'DaGaSa');
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (256,12,'MaGaTa'),
 (257,12,'ThaLaNa'),
 (258,12,'KaTaNa'),
 (259,12,'TaTaTa'),
 (260,12,'ThaKhaNa'),
 (261,12,'KhaRaNa'),
 (262,12,'TaTaNa'),
 (263,12,'KaMaNa'),
 (264,12,'KaKhaKa'),
 (265,12,'DaLaNa'),
 (266,12,'SaKaTa'),
 (267,12,'KaKaKa'),
 (268,12,'KaTaTa'),
 (269,12,'PaBaTa'),
 (270,12,'LaMaTa'),
 (271,12,'LaThaNa'),
 (272,12,'AhLaNa'),
 (273,12,'KaMaTa'),
 (274,12,'SaKhaNa'),
 (275,12,'LaMaNa'),
 (276,12,'KaMaRa'),
 (277,12,'MaRaKa'),
 (278,12,'DaGaNa'),
 (279,12,'BaHaNa'),
 (280,12,'SaKaNa'),
 (281,13,'KaLaNa'),
 (282,13,'NyaRaNa'),
 (283,13,'TaKaNa'),
 (284,13,'KaTaLa'),
 (285,13,'PhaKhaNa'),
 (286,13,'RaSaNa'),
 (287,13,'PaTaYa'),
 (288,13,'RaNgaNa'),
 (289,13,'SaSaNa'),
 (290,13,'PaLaNa'),
 (291,13,'NaTaRa'),
 (292,13,'HaPaNa'),
 (293,13,'MaNaNa'),
 (294,13,'KaTaTa'),
 (295,13,'MaPaNa'),
 (296,13,'MaMaNa'),
 (297,13,'LaKhaTa'),
 (298,13,'HaMaNa'),
 (299,13,'MaNaTa'),
 (300,13,'KaThaNa'),
 (301,13,'KaHaNa'),
 (302,13,'NaSaNa'),
 (303,13,'MaKaNa');
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (304,13,'MaRaNa'),
 (305,13,'LaLaNa'),
 (306,13,'PaLaTa'),
 (307,13,'LaKhaNa'),
 (308,13,'KaMaNa'),
 (309,13,'NaMaTa'),
 (310,13,'NaKhaNa'),
 (311,13,'MaBaNa'),
 (312,13,'MaMaTa'),
 (313,13,'ThaPaNa'),
 (314,13,'KaLaTa'),
 (315,13,'KaKaNa'),
 (316,13,'LaKaNa'),
 (317,13,'KhaRaHa'),
 (318,13,'NaSahNa'),
 (319,13,'MaTaTa'),
 (320,13,'KaKhaNa'),
 (321,13,'TaMaNya'),
 (322,13,'NaKhaTa'),
 (323,13,'MaSaTa'),
 (324,13,'MaKaTa'),
 (325,13,'MaHaRa'),
 (326,13,'TaYaNa'),
 (327,13,'MaRaTa'),
 (328,13,'LaRaNa'),
 (329,13,'ThaNaNa'),
 (330,13,'NaPhaNa'),
 (331,13,'PaWaNa'),
 (332,13,'MaMaHta'),
 (333,13,'MaMaHta'),
 (334,13,'HaPaTa'),
 (335,13,'NaTaNa'),
 (336,13,'KaTaNa'),
 (337,13,'MaKhaNa'),
 (338,13,'MaYaNa'),
 (339,13,'MaPaHta'),
 (340,13,'MaLaNa'),
 (341,13,'TaKhaLa'),
 (342,13,'KaLaHta'),
 (343,13,'TaLaNa'),
 (344,13,'MaSaNa'),
 (345,13,'MaKhaTa'),
 (346,13,'MaTaNa'),
 (347,13,'MaPaKa'),
 (348,13,'MaPaTa'),
 (349,13,'TaTaNa'),
 (350,13,'MaPhaNa'),
 (351,13,'MaYaTa');
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (352,13,'MaYaHta'),
 (353,14,'KaKaHta'),
 (354,14,'KaKaNa'),
 (355,14,'KaPaNa'),
 (356,14,'NgaPaTa'),
 (357,14,'PaThaNa'),
 (358,14,'PaThaRa'),
 (359,14,'NgaSahNa'),
 (360,14,'RaThaRa'),
 (361,14,'NgaRaKa'),
 (362,14,'HaKaKa'),
 (363,14,'RaKaNa'),
 (364,14,'ThaPaNa'),
 (365,14,'KaLaNa'),
 (366,14,'DaDaRa'),
 (367,14,'PhaPaNa'),
 (368,14,'ArMaTa'),
 (369,14,'BaKaLa'),
 (370,14,'DaNaPha'),
 (371,14,'PaTaNa'),
 (372,14,'MaAhPa'),
 (373,14,'MaMaNa'),
 (374,14,'PaSaLa'),
 (375,14,'WaKhaMa'),
 (376,14,'AhMaNa'),
 (377,14,'MaLaKa'),
 (378,14,'LaPaTa'),
 (379,14,'KaKhaNa'),
 (380,14,'ZaLaNa'),
 (381,14,'MaAhNa'),
 (382,14,'LaMaNa'),
 (383,14,'HaThaTa'),
 (384,14,'AhGaPa'),
 (385,9,'TaKaNa'),
 (386,9,'ZaYaTha'),
 (387,9,'OuTaTha'),
 (388,9,'PaBaTha'),
 (389,9,'PaMaNa'),
 (390,9,'LaWaNa'),
 (391,9,'ZaBaTha'),
 (392,9,'DaNaTha');
/*!40000 ALTER TABLE `nrc` ENABLE KEYS */;


--
-- Definition of table `officer`
--

DROP TABLE IF EXISTS `officer`;
CREATE TABLE `officer` (
  `OfficerID` varchar(10) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Phone` varchar(45) NOT NULL,
  `NRC` varchar(45) NOT NULL,
  `Role` varchar(10) NOT NULL,
  `UserName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY  (`OfficerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `officer`
--

/*!40000 ALTER TABLE `officer` DISABLE KEYS */;
INSERT INTO `officer` (`OfficerID`,`Name`,`Address`,`Phone`,`NRC`,`Role`,`UserName`,`Password`) VALUES 
 ('OF-0000001','Kaung Myat Thet','Home','912345','12/-DaGaRa-(N)-016314','Manager','Kaung','1234'),
 ('OF-0000003','Myint Saint','Home','12345678','12/-DaGaRa-(N)-016314','Staff','User','123'),
 ('OF-0000004','adfda','fadfa','25478968','4/-HaKhaNa-(N)-124589','Staff','hjhj','01245');
/*!40000 ALTER TABLE `officer` ENABLE KEYS */;


--
-- Definition of table `repayment`
--

DROP TABLE IF EXISTS `repayment`;
CREATE TABLE `repayment` (
  `RepaymentID` varchar(10) NOT NULL,
  `LoanRequestID` varchar(10) NOT NULL,
  `PaymentDate` varchar(15) NOT NULL,
  `Amount` int(10) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (`RepaymentID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `repayment`
--

/*!40000 ALTER TABLE `repayment` DISABLE KEYS */;
/*!40000 ALTER TABLE `repayment` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
