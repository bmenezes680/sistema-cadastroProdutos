create database sistemaProdutos;

CREATE TABLE produtos(
	id SERIAL PRIMARY KEY,
	nome varchar(100) NOT NULL,
	preco numeric(10,2) NOT NULL,
	quantidade int NOT NULL
);