DROP TABLE IF EXISTS `PROFILE_TO_SOCIAL_PROFILES`;

CREATE TABLE `PROFILE_TO_SOCIAL_PROFILES` (
  `PROFILE_ID` varchar(50),
  `PRIMARY_CONNECTION` varchar(100)
) ;

DROP TABLE IF EXISTS `PROFILES`;

CREATE TABLE `PROFILES` (
  `PROFILE_ID` varchar(50),
  PRIMARY KEY (`PROFILE_ID`)
) ;

DROP TABLE IF EXISTS `SOCIAL_PERSON_CONNECTIONS`;

CREATE TABLE `SOCIAL_PERSON_CONNECTIONS` (
  `PRIMARY_CONNECTION` varchar(100),
  `CONNECTION` varchar(100),
  PRIMARY KEY (`PRIMARY_CONNECTION`,`CONNECTION`)
) ;

DROP TABLE IF EXISTS `SOCIAL_PERSON_PROFILES`;

CREATE TABLE `SOCIAL_PERSON_PROFILES` (
  `PRIMARY_CONNECTION` varchar(100),
  `FIRST_NAME` varchar(256),
  `LAST_NAME` varchar(256),
  `PROFILE_URL` varchar(256),
  `PROFILE_IMAGE` varchar(256),
  `BIRTH_DAY` int(11),
  `BIRTH_MONTH` int(11),
  `BIRTH_YEAR` int(11),
  `GENDER` varchar(1),
  PRIMARY KEY (`PRIMARY_CONNECTION`)
) ;

DROP TABLE IF EXISTS `USER_TO_CONNECTIONS`;

CREATE TABLE `USER_TO_CONNECTIONS` (
  `USER_ID` varchar(50),
  `PROFILE_ID` varchar(50),
  PRIMARY KEY (`USER_ID`,`PROFILE_ID`)
) ;

DROP TABLE IF EXISTS `USERS`;

CREATE TABLE `USERS` (
  `USER_ID` varchar(50),
  PRIMARY KEY (`USER_ID`)
) ;

DROP TABLE IF EXISTS PROVIDER_CONFIGURATIONS;

CREATE TABLE PROVIDER_CONFIGURATIONS (
  `PROVIDER_NAME` varchar(255),
  PRIMARY KEY (`PROVIDER_NAME`)
) ;

DROP TABLE IF EXISTS `UTILS_NAME_VOCABULARY`;

CREATE TABLE `UTILS_NAME_VOCABULARY` (
  `WORD` varchar(255),
  `TRANSLATION` varchar(255),
  `PROVIDER` varchar(50),
  PRIMARY KEY (`WORD`,`PROVIDER`)
) ;


DROP TABLE IF EXISTS `UTILS_SOUND_MATCHES`;

CREATE TABLE `UTILS_SOUND_MATCHES` (
  `WORD` varchar(255),
  `PRESENTATION` varchar(255),
  `ALGORITHM` varchar(50),
  PRIMARY KEY (`WORD`,`ALGORITHM`)
) ;

DROP TABLE IF EXISTS `UTILS_SYNONYMS`;

CREATE TABLE `UTILS_SYNONYMS` (
  `WORD` varchar(255),
  `SEQUENCE` varchar(255),
  PRIMARY KEY (`WORD`)
) ;

DROP TABLE IF EXISTS PROVIDER_CONFIGURATIONS;

CREATE TABLE PROVIDER_CONFIGURATIONS (
  PROVIDER_NAME varchar(255),
  SIGN_IN_URL varchar(512),
  SIGN_UP_URL varchar(512),
  POST_SIGN_IN_URL varchar(512),
  PRIMARY KEY (PROVIDER_NAME)
);

DROP TABLE IF EXISTS PROVIDER_MERGE_CONFIGURATIONS;

CREATE TABLE PROVIDER_MERGE_CONFIGURATIONS (
  PROVIDER_NAME varchar(255),
  PROVIDER_ID varchar(255),
  PRIORITY int(11),
  PRIMARY KEY (PROVIDER_NAME, PROVIDER_ID)
);

DROP TABLE IF EXISTS PROVIDER_CALLBACK_CONFIGURATIONS;

CREATE TABLE PROVIDER_CALLBACK_CONFIGURATIONS (
  PROVIDER_NAME varchar(255),
  EVENT varchar(255),
  CALLBACK_URL varchar(512),
  PRIMARY KEY (PROVIDER_NAME, EVENT)
);