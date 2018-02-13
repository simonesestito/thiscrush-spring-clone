CREATE DATABASE thiscrush;
USE thiscrush;

CREATE USER 'spring' IDENTIFIED BY 'secure-password';
GRANT SELECT, INSERT, DELETE, UPDATE ON thiscrush.* TO 'spring'@'localhost';

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `date` bigint NOT NULL,
  `addressee_id` int(11) NOT NULL,
  `secret` tinyint(4) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
);
