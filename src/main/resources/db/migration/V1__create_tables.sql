CREATE TABLE artist (
                        id SERIAL PRIMARY KEY,
                        nome VARCHAR(200) NOT NULL
);

CREATE TABLE album (
                       id SERIAL PRIMARY KEY,
                       titulo VARCHAR(200) NOT NULL,
                       capa_path VARCHAR(255)
);

CREATE TABLE artist_album (
                              artist_id INTEGER REFERENCES artist(id),
                              album_id INTEGER REFERENCES album(id),
                              PRIMARY KEY (artist_id, album_id)
);
