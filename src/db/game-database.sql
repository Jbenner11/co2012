DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS properties;

SET SQL_SAFE_UPDATES = 0;

CREATE TABLE game (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL UNIQUE,
    PRIMARY KEY (id));

CREATE TABLE player (
    id INT NOT NULL AUTO_INCREMENT,
    piece VARCHAR(30) NOT NULL,
    square INT NOT NULL,
    game INT NOT NULL,
    money INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY(game) REFERENCES game(id) ON DELETE CASCADE);
    
CREATE TABLE properties (
    id INT NOT NULL AUTO_INCREMENT,
    player VARCHAR(30) NOT NULL,
    property VARCHAR(30) NOT NULL,
    location INT NOT NULL,
    cost INT NOT NULL,
    rent INT NOT NULL,
    multiplier INT NOT NULL,
    mortgage INT NOT NULL,
    PRIMARY KEY (id));