INSERT INTO PLATFORM (PlatformName, MaxRes, MaxPlayers, Format) VALUES 
('PC', '3840x2160', 1, 'Digital'),
('PlayStation 5', '3840x2160', 4, 'Disc'),
('Xbox Series X', '3840x2160', 4, 'Disc'),
('Nintendo Switch', '1920x1080', 8, 'Cartridge'),
('Steam Deck', '1280x800', 1, 'Digital'),
('Xbox Series S', '2560x1440', 4, 'Disc');

INSERT INTO PUBLISHER (PublisherName, Country) VALUES
('Riot Games', 'United States'),
('Mojang Studios', 'United States'),
('Epic Games', 'United States'),
('FromSoftware', 'Japan'),
('Blizzard Entertainment', 'United States'),
('Square Enix', 'Japan'),
('Naughty Dog', 'United States'),
('Ubisoft', 'United States'),
('Gearbox Software', 'United States'),
('BioWare', 'United States');

INSERT INTO VIDEO_GAME (GameTitle, Price, NumPlayers, Genre, ESRB_Rating, PublisherName) VALUES 
('Valorant', 0.00, 10, 'FPS', 'Teen', 'Riot Games'),
('Minecraft', 26.95, 30, 'Sandbox', 'Everyone', 'Mojang Studios'),
('Fortnite', 0.00, 100, 'Battle Royale', 'Teen', 'Epic Games'),
('Elden Ring', 59.99, 1, 'RPG', 'Mature', 'FromSoftware'),
('Overwatch 2', 0.00, 10, 'FPS', 'Teen', 'Blizzard Entertainment'),
('Final Fantasy XIV', 19.99, 99, 'MMORPG', 'Teen', 'Square Enix');

INSERT INTO REVIEW (ReviewID, Author, ReviewText, Rating, GameTitle) VALUES 
(1, 'Alex Roe', 'Thrilling and captivating gameplay.', 5,'Minecraft'),
(2, 'Sam Taylor', 'Great graphics, weak gameplay.', 3, 'Elden Ring'),
(3, 'Emma White', 'Immersive story, very engaging.', 4, 'Fortnite'),
(4, 'Daniel Green', 'Lots of bugs, needs improvement.', 2, 'Final Fantasy XIV'),
(5, 'Olivia Harris', 'Exemplary game of the year.', 5, 'Valorant'),
(6, 'Lucas Martinez', 'Okay game, overpriced though.', 3, 'Overwatch 2');

INSERT INTO VOICE_ACTOR (ActorName) VALUES
('Ethan Chase'),
('Sofia Rivera'),
('Mia Zhang'),
('Liam Patel'),
('Oliver Kim'),
('Ava Murphy');

INSERT INTO WORKS_FOR (ActorName, PublisherName, GamesWorkedCount) VALUES 
('Ethan Chase', 'Square Enix', 4),
('Sofia Rivera', 'Epic Games', 5),
('Mia Zhang', 'Riot Games', 3),
('Liam Patel', 'Square Enix', 2),
('Oliver Kim', 'FromSoftware', 6),
('Ava Murphy', 'Blizzard Entertainment', 7);

INSERT INTO APPEARS_IN (GameTitle, ActorName) VALUES 
('Final Fantasy XIV', 'Ethan Chase'),
('Fortnite', 'Sofia Rivera'),
('Elden Ring', 'Oliver Kim'),
('Overwatch 2', 'Ava Murphy'),
('Valorant', 'Mia Zhang'),
('Final Fantasy XIV', 'Liam Patel');

INSERT INTO PLAYED_ON (GameTitle, PlatformName) VALUES
('Valorant', 'PC'),
('Minecraft', 'PC'),
('Minecraft', 'Xbox Series X'),
('Minecraft', 'Nintendo Switch'),
('Minecraft', 'Xbox Series S'),
('Fortnite', 'PC'),
('Fortnite', 'Xbox Series X'),
('Fortnite', 'Nintendo Switch'),
('Fortnite', 'Xbox Series S'),
('Fortnite', 'PlayStation 5'),
('Elden Ring', 'PC'),
('Elden Ring', 'PlayStation 5'),
('Elden Ring', 'Xbox Series X'),
('Elden Ring', 'Steam Deck'),
('Elden Ring', 'Xbox Series S'),
('Overwatch 2', 'PC'),
('Overwatch 2', 'PlayStation 5'),
('Overwatch 2', 'Xbox Series X'),
('Overwatch 2', 'Nintendo Switch'),
('Overwatch 2', 'Steam Deck'),
('Overwatch 2', 'Xbox Series S'),
('Final Fantasy XIV', 'PC'),
('Final Fantasy XIV', 'PlayStation 5'),
('Final Fantasy XIV', 'Steam Deck');