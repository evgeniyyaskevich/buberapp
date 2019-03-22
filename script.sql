CREATE DATABASE buber;

USE buber;

CREATE TABLE User (
	user_name VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL, 
    user_id INT NOT NULL AUTO_INCREMENT,
    bonus INT DEFAULT 0,
    level_access ENUM ("CLIENT", "DRIVER", "ADMIN") DEFAULT "CLIENT",
    PRIMARY KEY (user_id)
);


CREATE TABLE Car (
	car_id INT NOT NULL AUTO_INCREMENT,
    driver_id INT DEFAULT NULL,
	car_brand VARCHAR(10) NOT NULL,
    car_type ENUM ("UNIVERSAL", "MINIVAN", "ELITE") DEFAULT "UNIVERSAL",
    child_seat BOOLEAN DEFAULT false,
    PRIMARY KEY (car_id),
    FOREIGN KEY (driver_id) REFERENCES User (user_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Application (
	application_id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    destination VARCHAR(20) NOT NULL,
    price INT NOT NULL,
    application_time DATETIME NOT NULL DEFAULT NOW(),
    state ENUM ("WAITING", "ACCEPTED", "CANCELED"),
    car_type VARCHAR(15) NOT NULL,
    child_seat BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (application_id),
    FOREIGN KEY (client_id)  REFERENCES User (user_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);



CREATE TABLE Destination (
	destination_id INT NOT NULL AUTO_INCREMENT,
	destination_name VARCHAR(20) NOT NULL,
    south_coord INT NOT NULL,
    north_coord INT NOT NULL,
    PRIMARY KEY (destination_id)
);


CREATE TABLE Black_list (
	user_id INT NOT NULL UNIQUE,
    reason VARCHAR(50),
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES User (user_id)
		ON UPDATE CASCADE
        ON DELETE CASCADE
);

# Igor, Tatyan, Ekaterina, Elizaveta password = 123
INSERT INTO User (user_name, password, level_access)
VALUES ("Evgeniy", "1000:7966ac41265e7f8f70d62eda8177cb8b:d0080ddaf2593ca0cca23c78cdc4eeacfdd0a3db7b4a71fe56ee89a64d8df82e9344646d3b2037c717f637a10f8b49177f9df7514b61195624ea5981c7c3356f", 
				"ADMIN"),
                ("admin", "1000:b3c70e542aadd64027f53f250cb65b6c:43d0501937d4707651c90b3cc21de08b535641f618b45e5aab89bff62a38d2ee50ef6f4918c201865865a620646f7bbe1c362224b38b22d51f731d592acdefde",
                "ADMIN"),
		("Igor", "1000:aab5873267db96d8890bb69a6308bdcb:bb6eb3c838dd0125a208f36a628dd67a196cf1c3735c4bcb4c11b5b67a30f8a151342462697fe67ebade2f708e4b7d85991274f7471d06b2e774312b6cb1c7f2",
				"DRIVER"),
        ("Tatyana", "1000:aab5873267db96d8890bb69a6308bdcb:bb6eb3c838dd0125a208f36a628dd67a196cf1c3735c4bcb4c11b5b67a30f8a151342462697fe67ebade2f708e4b7d85991274f7471d06b2e774312b6cb1c7f2",
				"CLIENT");
INSERT INTO User (user_name, password, level_access)
VALUES ("GENA", "1000:7966ac41265e7f8f70d62eda8177cb8b:d0080ddaf2593ca0cca23c78cdc4eeacfdd0a3db7b4a71fe56ee89a64d8df82e9344646d3b2037c717f637a10f8b49177f9df7514b61195624ea5981c7c3356f",
"ADMIN");

BEGIN;
INSERT INTO User (user_name, password, level_access)
VALUES ("Ekaterina", "1000:aab5873267db96d8890bb69a6308bdcb:bb6eb3c838dd0125a208f36a628dd67a196cf1c3735c4bcb4c11b5b67a30f8a151342462697fe67ebade2f708e4b7d85991274f7471d06b2e774312b6cb1c7f2",
				"DRIVER");
INSERT INTO Car (car_brand, driver_id, car_type) 
VALUES ("Peugeout", last_insert_id(), "MINIVAN");
COMMIT;


BEGIN;
INSERT INTO User (user_name, password, level_access)
VALUES ("driver", "1000:a0939358951708289b6fdad6430481ee:b3812713a5adc86bc57f01c692d8a8351f4668ad333391e30b475f8b2009cb5b46b863f61ae2b0135752b4a1ce7408ab339d80be9a2e32e6531fa5433104b7e3",
				"DRIVER");
INSERT INTO Car (car_brand, driver_id, car_type) 
VALUES ("Peugeout", last_insert_id(), "UNIVERSAL");
COMMIT;

		


BEGIN;
INSERT INTO User (user_name, password, level_access)
VALUES ("Elizaveta", "1000:aab5873267db96d8890bb69a6308bdcb:bb6eb3c838dd0125a208f36a628dd67a196cf1c3735c4bcb4c11b5b67a30f8a151342462697fe67ebade2f708e4b7d85991274f7471d06b2e774312b6cb1c7f2",
		"CLIENT");
INSERT INTO Application (client_id, destination, price, state, car_type) 
VALUES (last_insert_id(), "Renaissance Hotel", 123, "waiting", "MINIVAN");
COMMIT;

INSERT INTO Destination (destination_name, south_coord, north_coord)
VALUES ("Renaissance Hotel", 15, 35),
		("BSU Main Building", 10, 12),
        ("BSU Domitory #6", 100, 13),
        ("Gorky Park", 200, 133),
        ("Minsk Center", 300, 250);


INSERT INTO Black_list (user_id, reason)
	VALUES (6, "She is a dreamer.");

SHOW DATABASES;
SHOW TABLES;
SELECT * FROM User;
SELECT * FROM Car;
SELECT * FROM Application;
SELECT * FROM Black_list;

DROP DATABASE buber;
DROP TABLE User;
DROP TABLE Car;
DROP TABLE Application;
DROP TABLE Black_list;
DROP TABLE Destination;