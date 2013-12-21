DROP TABLE IF EXISTS Enderecos
;
DROP TABLE IF EXISTS Configuracoes
;
DROP TABLE IF EXISTS ParametrosGlobais
;
DROP TABLE IF EXISTS Menus
;
DROP TABLE IF EXISTS Permissoes
;
DROP TABLE IF EXISTS Usuarios
;
DROP TABLE IF EXISTS Departamentos
;
DROP TABLE IF EXISTS Empresas
;
DROP TABLE IF EXISTS Credenciais
;
DROP TABLE IF EXISTS Telefones
;



CREATE TABLE Enderecos
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(200),
	cep VARCHAR(11),
	rua VARCHAR(200),
	numero VARCHAR(10),
	complemento VARCHAR(200),
	bairro VARCHAR(50),
	cidade VARCHAR(200),
	estado VARCHAR(50),
	referencia VARCHAR(255),
	PRIMARY KEY (id)
) 
;


CREATE TABLE Configuracoes
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	empresa integer NOT NULL,
	configuracaoGlobal integer,
	PRIMARY KEY (id)
) 
;


CREATE TABLE ParametrosGlobais
(
	id VARCHAR(200) NOT NULL,
	value varchar(200) NOT NULL,
	configuracao INTEGER NOT NULL,
	PRIMARY KEY (id)
) 
;


CREATE TABLE Menus
(
	id integer NOT NULL AUTO_INCREMENT,
	nome varchar(20) NOT NULL,
	menuPai integer,
	url varchar(200),
	icone varchar(100),
	PRIMARY KEY (id)
) 
;


CREATE TABLE Permissoes
(
	id integer NOT NULL AUTO_INCREMENT,
	menu INTEGER NOT NULL,
	editar boolean,
	ler boolean,
	criar boolean,
	remover boolean,
	credencial INTEGER NOT NULL,
	empresa INTEGER NOT NULL,
	PRIMARY KEY (id)
) 
;


CREATE TABLE Usuarios
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	nome varchar(200) NOT NULL,
	cpf VARCHAR(11),
	credencial integer NOT NULL,
	dataExpiracao DATE,
	PRIMARY KEY (Id)
) 
;


CREATE TABLE Departamentos
(
	id integer NOT NULL AUTO_INCREMENT,
	empresa integer NOT NULL,
	nome varchar(100) NOT NULL,
	descricao varchar(200),
	PRIMARY KEY (id)
) 
;


CREATE TABLE Empresas
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	cnpj VARCHAR(14) NOT NULL,
	razaoSocial VARCHAR(100) NOT NULL,
	nomeFantasia VARCHAR(200) NOT NULL,
	inscricaoEstadual VARCHAR(50) NOT NULL,
	codigo INTEGER NOT NULL,
	codigoNire VARCHAR(5) NOT NULL,
	telefone INTEGER NOT NULL,
	endereco INTEGER NOT NULL,
	inscricaoMunicipal VARCHAR(200),
	codigoIbge VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
) 
;


CREATE TABLE Credenciais
(
	id integer NOT NULL AUTO_INCREMENT,
	login varchar(30) NOT NULL,
	senha varchar(50) NOT NULL,
	email varchar(50),
	PRIMARY KEY (id)
) 
;


CREATE TABLE Telefones
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	ddd VARCHAR(5) NOT NULL,
	numero VARCHAR(20) NOT NULL,
	descricao VARCHAR(200),
	PRIMARY KEY (id)
) 
;
