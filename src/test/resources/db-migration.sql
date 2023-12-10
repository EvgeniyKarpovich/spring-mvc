create table if not exists singers
(
    id
    serial
    PRIMARY
    KEY,
    name
    varchar
(
    64
) NOT NULL,
    CONSTRAINT ct_singer_name UNIQUE
(
    name
)
    );

create table if not exists authors
(
    id
    serial
    PRIMARY
    KEY,
    name
    varchar
(
    64
) NOT NULL,
    CONSTRAINT ct_author_name UNIQUE
(
    name
)
    );

create table if not exists songs
(
    id
    serial
    PRIMARY
    KEY,
    name
    varchar
(
    64
) NOT NULL,
    singer_id int references singers
(
    id
) not null,
    CONSTRAINT ct_song_name UNIQUE
(
    name
)
    );

create table if not exists songs_author
(
    song_id BIGINT REFERENCES songs
(
    id
),
    author_id BIGINT REFERENCES authors
(
    id
),
    PRIMARY KEY
(
    song_id,
    author_id
)
    );

INSERT INTO AUTHORS (name)
VALUES ('Evgeniy Karpovich'),
       ('Artsiom Shpak'),
       ('Anna Karenina');

INSERT INTO SINGERS (name)
VALUES ('50 CENT'),
       ('Eminem'),
       ('Muse');

INSERT INTO SONGS (name, singer_id)
VALUES ('THE BEST SONG', '2'),
       ('Concrats', '1'),
       ('Baby Cold Baby', '3');

INSERT INTO songs_author (song_id, author_id)
VALUES ('1', '1'),
       ('2', '3'),
       ('2', '2');