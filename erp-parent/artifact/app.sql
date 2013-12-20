DROP TABLE IF EXISTS UsuariosEnderecos CASCADE
;
DROP TABLE IF EXISTS Enderecos CASCADE
;
DROP TABLE IF EXISTS Configuracoes CASCADE
;
DROP TABLE IF EXISTS ParametrosGlobais CASCADE
;
DROP TABLE IF EXISTS CredenciaisPapeis CASCADE
;
DROP TABLE IF EXISTS Menus CASCADE
;
DROP TABLE IF EXISTS Funcionalidades CASCADE
;
DROP TABLE IF EXISTS Papeis CASCADE
;
DROP TABLE IF EXISTS Permissoes CASCADE
;
DROP TABLE IF EXISTS Usuarios CASCADE
;
DROP TABLE IF EXISTS Departamentos CASCADE
;
DROP TABLE IF EXISTS Empresas CASCADE
;
DROP TABLE IF EXISTS Credenciais CASCADE
;
DROP TABLE IF EXISTS Telefones CASCADE
;

CREATE TABLE UsuariosEnderecos
(
	usuario INTEGER NULL,
	endereco INTEGER NOT NULL,
	id INTEGER NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	UNIQUE UQ_UsuariosEnderecos_usuario(usuario),
	UNIQUE UQ_funcionario_empresa(endereco),
	KEY (usuario),
	KEY (endereco)

) 
;


CREATE TABLE Enderecos
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(200) NULL,
	cep VARCHAR(11) NULL,
	rua VARCHAR(200) NULL,
	numero VARCHAR(10) NULL,
	complemento VARCHAR(200) NULL,
	bairro VARCHAR(50) NULL,
	cidade VARCHAR(200) NULL,
	estado VARCHAR(50) NULL,
	referencia VARCHAR(255) NULL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE Configuracoes
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	empresa integer NOT NULL,
	configuracaoGlobal integer NULL,
	PRIMARY KEY (id),
	KEY (empresa)

) 
;


CREATE TABLE ParametrosGlobais
(
	id VARCHAR(200) NOT NULL,
	value varchar(200) NOT NULL,
	configuracao INTEGER NOT NULL,
	PRIMARY KEY (id),
	KEY (configuracao)

) 
;


CREATE TABLE CredenciaisPapeis
(
	credencial integer NOT NULL,
	papel integer NOT NULL,
	PRIMARY KEY (credencial, papel),
	KEY (credencial),
	KEY (papel)

) 
;


CREATE TABLE Menus
(
	id integer NOT NULL AUTO_INCREMENT,
	nome varchar(20) NOT NULL,
	menuPai integer NULL,
	url varchar(200) NULL,
	icone varchar(100) NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_Menus_nome(nome),
	KEY (menuPai)

) 
;


CREATE TABLE Funcionalidades
(
	id integer NOT NULL AUTO_INCREMENT,
	nome varchar(100) NOT NULL,
	url varchar(200) NOT NULL,
	menu integer NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_Funcionalidades_nome(nome),
	UNIQUE UQ_Funcionalidades_url(url)

) 
;


CREATE TABLE Papeis
(
	id integer NOT NULL AUTO_INCREMENT,
	nome varchar(50) NOT NULL,
	descricao varchar(200) NULL,
	administrador boolean NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_Papeis_nome(nome)

) 
;


CREATE TABLE Permissoes
(
	id integer NOT NULL AUTO_INCREMENT,
	funcionalidade integer NOT NULL,
	editar boolean NULL,
	ler boolean NULL,
	criar boolean NULL,
	remover boolean NULL,
	papel integer NOT NULL,
	PRIMARY KEY (id),
	KEY (funcionalidade),
	KEY (papel)

) 
;


CREATE TABLE Usuarios
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
	empresa INTEGER NOT NULL,
	nome varchar(200) NOT NULL,
	cpf VARCHAR(11) NULL,
	credencial integer NOT NULL,
	PRIMARY KEY (Id),
	UNIQUE UQ_Usuarios_cpf(cpf),
	KEY (credencial)

) 
;


CREATE TABLE Departamentos
(
	id integer NOT NULL AUTO_INCREMENT,
	empresa integer NOT NULL,
	nome varchar(100) NOT NULL,
	descricao varchar(200) NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_Departamentos_nome(nome),
	KEY (empresa)

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
	inscricaoMunicipal VARCHAR(200) NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_Empresas_codigo(codigo),
	UNIQUE UQ_Empresas_codigoNire(codigoNire),
	KEY (endereco),
	KEY (telefone)

) 
;


CREATE TABLE Credenciais
(
	id integer NOT NULL AUTO_INCREMENT,
	login varchar(30) NOT NULL,
	senha varchar(50) NOT NULL,
	email varchar(50) NULL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE Telefones
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	ddd VARCHAR(5) NOT NULL,
	numero VARCHAR(20) NOT NULL,
	descricao VARCHAR(200) NULL,
	PRIMARY KEY (id)

) 
;





ALTER TABLE UsuariosEnderecos ADD CONSTRAINT FK_UsuariosEnderecos_Enderecos 
	FOREIGN KEY (endereco) REFERENCES Enderecos (id)
;

ALTER TABLE Configuracoes ADD CONSTRAINT FK_Configuracoes_Empresas 
	FOREIGN KEY (empresa) REFERENCES Empresas (id)
;

ALTER TABLE ParametrosGlobais ADD CONSTRAINT FK_ParametrosGlobais_Configuracoes 
	FOREIGN KEY (configuracao) REFERENCES Configuracoes (id)
;

ALTER TABLE CredenciaisPapeis ADD CONSTRAINT FK_CredenciaisPapeis_Credenciais 
	FOREIGN KEY (credencial) REFERENCES Credenciais (id)
;

ALTER TABLE CredenciaisPapeis ADD CONSTRAINT FK_CredenciaisPapeis_Papeis 
	FOREIGN KEY (papel) REFERENCES Papeis (id)
;

ALTER TABLE Menus ADD CONSTRAINT FK_Menus_Menus 
	FOREIGN KEY (menuPai) REFERENCES Menus (id)
;

ALTER TABLE Permissoes ADD CONSTRAINT FK_Permissoes_Funcionalidades 
	FOREIGN KEY (funcionalidade) REFERENCES Funcionalidades (id)
;

ALTER TABLE Permissoes ADD CONSTRAINT FK_Permissoes_Papeis 
	FOREIGN KEY (papel) REFERENCES Papeis (id)
;

ALTER TABLE Usuarios ADD CONSTRAINT FK_Usuarios_Credenciais 
	FOREIGN KEY (credencial) REFERENCES Credenciais (id)
;

ALTER TABLE Departamentos ADD CONSTRAINT FK_Departamentos_Empresas 
	FOREIGN KEY (empresa) REFERENCES Empresas (id)
;

ALTER TABLE Empresas ADD CONSTRAINT FK_Empresas_Enderecos 
	FOREIGN KEY (endereco) REFERENCES Enderecos (id)
;

ALTER TABLE Empresas ADD CONSTRAINT FK_Empresas_Telefones 
	FOREIGN KEY (telefone) REFERENCES Telefones (id)
;
