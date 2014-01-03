-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.5-10.0.7-MariaDB - mariadb.org binary distribution
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 gamestore 的数据库结构
DROP DATABASE IF EXISTS `gamestore`;
CREATE DATABASE IF NOT EXISTS `gamestore` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gamestore`;


-- 导出  表 gamestore.company 结构
CREATE TABLE IF NOT EXISTS `company` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='game producer';

-- 正在导出表  gamestore.company 的数据：~4 rows (大约)
DELETE FROM `company`;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id`, `name`) VALUES
	(1, 'EA'),
	(2, 'ActiveVision'),
	(3, 'North RockStar'),
	(4, 'TakeTwo');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


-- 导出  表 gamestore.game 结构
CREATE TABLE IF NOT EXISTS `game` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` int(10) unsigned DEFAULT '0',
  `release_date` date DEFAULT NULL,
  `company_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_game_company` (`company_id`),
  CONSTRAINT `FK_game_company` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 正在导出表  gamestore.game 的数据：~7 rows (大约)
DELETE FROM `game`;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` (`id`, `name`, `type`, `release_date`, `company_id`) VALUES
	(1, 'BattleField 4', NULL, NULL, 1),
	(2, 'Call of Duty 10', NULL, NULL, 2),
	(3, 'FIFA14', NULL, NULL, 1),
	(4, 'GTA5', NULL, NULL, 3),
	(5, 'AC4', 0, '2014-01-03', 2),
	(6, 'WE2014', 7, '2014-01-03', 4),
	(7, 'Civilization', 6, '2014-01-03', 4);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
