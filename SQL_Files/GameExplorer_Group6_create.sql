DROP DATABASE IF EXISTS `GameExplorer`;
CREATE DATABASE `GameExplorer`;
USE `GameExplorer`;
CREATE TABLE PLATFORM (
    PlatformName VARCHAR(50) NOT NULL,
    MaxRes VARCHAR(50),
    MaxPlayers INT,
    Format VARCHAR(50) NOT NULL,
    PRIMARY KEY(PlatformName)
);

CREATE TABLE PUBLISHER (
    PublisherName VARCHAR(50) NOT NULL,
    Country VARCHAR(50),
    PRIMARY KEY(PublisherName)
);

CREATE TABLE VIDEO_GAME (
    GameTitle VARCHAR(50) NOT NULL,
    Price FLOAT,
    NumPlayers INT,
    Genre VARCHAR(50) NOT NULL,
    ESRB_Rating VARCHAR(50) NOT NULL,
    PublisherName VARCHAR(50) NOT NULL,
    PRIMARY KEY(GameTitle),
    FOREIGN KEY (PublisherName) REFERENCES PUBLISHER(PublisherName)
			ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE REVIEW (
    ReviewID INT  NOT NULL,
    Author VARCHAR(50),
    ReviewText VARCHAR(255),
    Rating INT,
    GameTitle VARCHAR(50) NOT NULL,
    PRIMARY KEY(ReviewID),
    FOREIGN KEY (GameTitle) REFERENCES VIDEO_GAME(GameTitle)
			ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE VOICE_ACTOR (
    ActorName VARCHAR(50) NOT NULL,
    PRIMARY KEY(ActorName)
);

CREATE TABLE WORKS_FOR (
    ActorName VARCHAR(50) NOT NULL,
    PublisherName VARCHAR(50) NOT NULL,
    GamesWorkedCount INT,
    PRIMARY KEY(ActorName, PublisherName),
    FOREIGN KEY (ActorName) REFERENCES VOICE_ACTOR(ActorName)
             ON DELETE CASCADE  ON UPDATE CASCADE,
    FOREIGN KEY (PublisherName) REFERENCES PUBLISHER(PublisherName)
             ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE APPEARS_IN (
    GameTitle VARCHAR(50) NOT NULL,
    ActorName VARCHAR(50) NOT NULL,
    PRIMARY KEY(GameTitle, ActorName),
    FOREIGN KEY (GameTitle) REFERENCES VIDEO_GAME(GameTitle)
             ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ActorName) REFERENCES VOICE_ACTOR(ActorName)
             ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PLAYED_ON (
    GameTitle VARCHAR(50) NOT NULL,
    PlatformName VARCHAR(50) NOT NULL,
    PRIMARY KEY(GameTitle, PlatformName),
    FOREIGN KEY (GameTitle) REFERENCES VIDEO_GAME(GameTitle)
             ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PlatformName) REFERENCES PLATFORM(PlatformName)
             ON DELETE CASCADE ON UPDATE CASCADE
);