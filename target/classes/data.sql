--insert funcionario
INSERT INTO funcionario(id, nome, datavoto, email, senha) VALUES (1, 'root', '2020-01-27', 'root@db.com', '$2a$10$4pzie9/I3boCLV7LRvs3.e4C95QKTIF63PFBJqQwfbItFPvhedCW2');
INSERT INTO funcionario(id, nome, datavoto, email, senha) VALUES (2, 'ellen', '2020-01-27', 'ellenl@db.com','132456');
INSERT INTO funcionario(id, nome, datavoto, email, senha) VALUES (3, 'carla', '2020-01-27', 'carlac@db.com','123456');

--Insert some restaurante
INSERT INTO restaurante(id, nome, voto, elegivel, dataeleito) VALUES (1, 'Macadamia', 2, 1, '2020-01-27');
INSERT INTO restaurante(id, nome, voto, elegivel, dataeleito) VALUES (2, 'Mcdonalds', 3, 1, '2020-01-27');

--insert voto
INSERT INTO VOTO(id, idfuncionario, idrestaurante, datavoto) VALUES (1, 1, 1, '2020-02-04');
INSERT INTO VOTO(id, idfuncionario, idrestaurante, datavoto) VALUES (2, 2, 1, '2020-02-04');



