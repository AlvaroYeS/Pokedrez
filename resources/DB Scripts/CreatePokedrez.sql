CREATE DATABASE IF NOT EXISTS Pokedrez;

USE Pokedrez;

DROP TABLE pokemons;
CREATE TABLE pokemons (
	idPokemon INT PRIMARY KEY,
    namePokemon VARCHAR(20),
    type1 VARCHAR(20),
    type2 VARCHAR(20),
    hp INT,
    ad INT,
    ap INT,
    armor INT,
    mr INT,
    haste DOUBLE,
    mana INT,
    imagenTienda VARCHAR(20),
    imagenFrente VARCHAR(20),
    imagenTrasera VARCHAR(20),
    tier INT,
    evo bool
);