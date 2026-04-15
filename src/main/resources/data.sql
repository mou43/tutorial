select 1 from dual;
INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('Cliente 1');
INSERT INTO client(name) VALUES ('Cliente 2');
INSERT INTO client(name) VALUES ('Cliente 3');
INSERT INTO client(name) VALUES ('Cliente 4');
INSERT INTO client(name) VALUES ('Cliente 5');
INSERT INTO client(name) VALUES ('Cliente 6');

INSERT INTO rental(game_id, client_id, rentalDate, returnDate) VALUES (1,2,'2026-02-15', '2026-02-20');
INSERT INTO rental(game_id, client_id, rentalDate, returnDate) VALUES (2,2,'2026-02-15', '2026-02-20');
INSERT INTO rental(game_id, client_id, rentalDate, returnDate) VALUES (5,1,'2026-03-22', '2026-03-26');
INSERT INTO rental(game_id, client_id, rentalDate, returnDate) VALUES (4,5,'2026-04-06', '2026-04-09');
INSERT INTO rental(game_id, client_id, rentalDate, returnDate) VALUES (2,4,'2026-04-10', '2026-04-13');
INSERT INTO rental(game_id, client_id, rentalDate, returnDate) VALUES (3,3,'2026-04-11', '2026-04-14');
