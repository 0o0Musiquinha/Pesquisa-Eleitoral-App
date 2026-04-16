DROP DATABASE IF EXISTS p1_eleitores;
CREATE DATABASE p1_eleitores;
USE p1_eleitores;


CREATE TABLE usuario(
	usu_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	usu_email VARCHAR(60),
	usu_senha VARCHAR(20),
	usu_tipo INT
);

CREATE TABLE eleitor(
	ele_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   ele_nome VARCHAR(60),
   ele_celular VARCHAR(200),
   ele_dataHora DATETIME,
   ele_latitude DOUBLE,
   ele_longitude DOUBLE
   
);

CREATE TABLE candidato(
	can_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	can_nome VARCHAR(60),
	can_partido VARCHAR(20),
	can_imagem VARCHAR(30),
	can_qtdVotos INT,
	can_tipo INT
	
);

CREATE TABLE problema(
	pro_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pro_nome VARCHAR(30)
);


INSERT INTO usuario VALUES
(NULL, "roberto@gmail.com", "robertinho", 0),
(NULL, "claudia@gmail.com", "admin", 1);

INSERT INTO candidato VALUES
(NULL, "CLeber", "PSOL", "urnachan", 0, 0),
(NULL, "teste1", "PT", "urnachan", 0, 0),
(NULL, "teste2", "PL", "urnachan", 0, 0),
(NULL, "teste3", "PCDB", "urnachan", 0, 0),
(NULL, "teste4", "MISSAO", "urnachan", 0, 0),
(NULL, "Branco", "", "sem_imagem", 0, 1),
(NULL, "Nulo", "", "sem_imagem", 0, 1),
(NULL, "Não sei", "", "sem_imagem", 0, 1);


SELECT can_id, can_nome, can_partido, can_imagem, can_qtdVotos, can_tipo FROM candidato WHERE can_nome = "teste1";






