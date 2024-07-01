\c postgres;
drop database cinemadb;
create database cinemadb;
\c cinemadb;

CREATE TABLE films (
    id_film SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    realisateur VARCHAR(100),
    annee_sortie INTEGER,
    genre VARCHAR(100)
);

CREATE TABLE seances (
    id_seance SERIAL PRIMARY KEY,
    id_film INTEGER REFERENCES films(id_film),
    date_seance DATE,
    heure_debut TIME,
    heure_fin TIME,
    salle VARCHAR(50)
);

CREATE TABLE reservations (
    id_reservation SERIAL PRIMARY KEY,
    id_seance INTEGER REFERENCES seances(id_seance),
    id_client INTEGER REFERENCES clients(id_client),
    nombre_tickets INTEGER
);

CREATE TABLE clients (
    id_client SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telephone VARCHAR(20),
    mdp VARCHAR(100)
);


-- Insertion dans la table films
INSERT INTO films (titre, realisateur, annee_sortie, genre) VALUES
('Inception', 'Christopher Nolan', 2010, 'Science Fiction'),
('The Godfather', 'Francis Ford Coppola', 1972, 'Crime'),
('The Dark Knight', 'Christopher Nolan', 2008, 'Action');

-- Insertion dans la table seances
INSERT INTO seances (id_film, date_seance, heure_debut, heure_fin, salle) VALUES
(1, '2024-07-01', '18:00:00', '20:30:00', 'Salle 1'),
(2, '2024-07-02', '20:00:00', '23:00:00', 'Salle 2'),
(3, '2024-07-03', '16:00:00', '18:45:00', 'Salle 3');

-- Insertion dans la table clients
INSERT INTO clients (nom, prenom, email, telephone, mdp) VALUES
('Dupont', 'Jean', 'jean.dupont@example.com', '0123456789', 'password123'),
('Martin', 'Marie', 'marie.martin@example.com', '0987654321', 'password456');

-- Insertion dans la table reservations
INSERT INTO reservations (id_seance, id_client, nombre_tickets) VALUES
(1, 1, 2),
(2, 2, 3),
(3, 1, 1);
