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
  `Phone` int(10) unsigned NOT NULL,
  `DateOfBirth` varchar(20) NOT NULL,
  `Home` tinyint(1) NOT NULL,
  `Job` varchar(45) NOT NULL,
  `Salary` int(10) unsigned NOT NULL,
  `GName` varchar(45) default NULL,
  `GJob` varchar(45) default NULL,
  `GSalary` int(10) unsigned default NULL,
  `Relationship` varchar(45) default NULL,
  `GAddress` varchar(45) default NULL,
  `GPhone` int(10) unsigned default NULL,
  `GNRC` varchar(45) default NULL,
  PRIMARY KEY  USING BTREE (`ClientID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `client`
--

/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`ClientID`,`Name`,`NRC`,`Address`,`Phone`,`DateOfBirth`,`Home`,`Job`,`Salary`,`GName`,`GJob`,`GSalary`,`Relationship`,`GAddress`,`GPhone`,`GNRC`) VALUES 
 ('CL-0000001','Kaung Myat Thet','12DAGARA(N)016314','Home',99608569,'27-AUGUST-1996',0,'Programmer',300000,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 ('CL-0000002','Yin Hnin','12DAGARA(N)016341','MyHome',91234567,'21-JULY-1998',0,'HR',600000,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;


--
-- Definition of table `clientdetails`
--

DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `ClientID` varchar(10) NOT NULL,
  `LoanRequestID` varchar(10) NOT NULL,
  `RequestDate` datetime NOT NULL,
  KEY `FK_clientdetails_1` (`ClientID`),
  KEY `FK_clientdetails_2` (`LoanRequestID`),
  CONSTRAINT `FK_clientdetails_1` FOREIGN KEY (`ClientID`) REFERENCES `client` (`ClientID`),
  CONSTRAINT `FK_clientdetails_2` FOREIGN KEY (`LoanRequestID`) REFERENCES `loanrequest` (`LoanRequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clientdetails`
--

/*!40000 ALTER TABLE `clientdetails` DISABLE KEYS */;
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
/*!40000 ALTER TABLE `clientgroup` ENABLE KEYS */;


--
-- Definition of table `groupdetails`
--

DROP TABLE IF EXISTS `groupdetails`;
CREATE TABLE `groupdetails` (
  `GroupID` varchar(10) NOT NULL,
  `LoanRequestID` varchar(10) NOT NULL,
  `RequestDate` datetime NOT NULL,
  KEY `FK_GroupDetails_1` (`GroupID`),
  KEY `FK_GroupDetails_2` (`LoanRequestID`),
  CONSTRAINT `FK_GroupDetails_1` FOREIGN KEY (`GroupID`) REFERENCES `clientgroup` (`GroupID`),
  CONSTRAINT `FK_GroupDetails_2` FOREIGN KEY (`LoanRequestID`) REFERENCES `loanrequest` (`LoanRequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `groupdetails`
--

/*!40000 ALTER TABLE `groupdetails` DISABLE KEYS */;
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
  PRIMARY KEY  (`LoanRequestID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loanrequest`
--

/*!40000 ALTER TABLE `loanrequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `loanrequest` ENABLE KEYS */;


--
-- Definition of table `loansetting`
--

DROP TABLE IF EXISTS `loansetting`;
CREATE TABLE `loansetting` (
  `ID` varchar(10) NOT NULL default '',
  `Duration` int(11) default NULL,
  `ServiceRate` float NOT NULL,
  `InterestRate` float NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loansetting`
--

/*!40000 ALTER TABLE `loansetting` DISABLE KEYS */;
/*!40000 ALTER TABLE `loansetting` ENABLE KEYS */;


--
-- Definition of table `nrc`
--

DROP TABLE IF EXISTS `nrc`;
CREATE TABLE `nrc` (
  `No` int(10) unsigned NOT NULL auto_increment,
  `Number` int(10) unsigned NOT NULL,
  `Code` varchar(10) NOT NULL,
  PRIMARY KEY  (`No`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nrc`
--

/*!40000 ALTER TABLE `nrc` DISABLE KEYS */;
INSERT INTO `nrc` (`No`,`Number`,`Code`) VALUES 
 (1,12,'DAGARA');
/*!40000 ALTER TABLE `nrc` ENABLE KEYS */;


--
-- Definition of table `officer`
--

DROP TABLE IF EXISTS `officer`;
CREATE TABLE `officer` (
  `OfficerID` varchar(10) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Phone` int(10) unsigned NOT NULL,
  `NRC` varchar(45) NOT NULL,
  PRIMARY KEY  (`OfficerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `officer`
--

/*!40000 ALTER TABLE `officer` DISABLE KEYS */;
/*!40000 ALTER TABLE `officer` ENABLE KEYS */;


--
-- Definition of table `repayment`
--

DROP TABLE IF EXISTS `repayment`;
CREATE TABLE `repayment` (
  `RepaymentID` varchar(10) NOT NULL,
  `LoanRequestID` varchar(10) NOT NULL,
  `PaymentDate` datetime NOT NULL,
  `Amount` int(10) unsigned NOT NULL,
  `MonthNo` int(10) unsigned NOT NULL COMMENT 'Number of Month repay by cllient',
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
