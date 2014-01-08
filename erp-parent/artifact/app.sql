DROP TABLE IF EXISTS EspecieDocFiscal CASCADE
;
DROP TABLE IF EXISTS CodVinculacoes CASCADE
;
DROP TABLE IF EXISTS Mensagens CASCADE
;
DROP TABLE IF EXISTS CanaisVenda CASCADE
;
DROP TABLE IF EXISTS Mercados CASCADE
;
DROP TABLE IF EXISTS Cofins CASCADE
;
DROP TABLE IF EXISTS PIS CASCADE
;
DROP TABLE IF EXISTS ImpostoImportacao CASCADE
;
DROP TABLE IF EXISTS Inss CASCADE
;
DROP TABLE IF EXISTS BasesIcms CASCADE
;
DROP TABLE IF EXISTS destReducao CASCADE
;
DROP TABLE IF EXISTS Icms CASCADE
;
DROP TABLE IF EXISTS Iss CASCADE
;
DROP TABLE IF EXISTS Ipi CASCADE
;
DROP TABLE IF EXISTS CodTributacoes CASCADE
;
DROP TABLE IF EXISTS TiposBase CASCADE
;
DROP TABLE IF EXISTS OpEntregasFutAntecipadas CASCADE
;
DROP TABLE IF EXISTS Atualizacoes CASCADE
;
DROP TABLE IF EXISTS AdicionaisNaturezas CASCADE
;
DROP TABLE IF EXISTS TiposNaturezas CASCADE
;
DROP TABLE IF EXISTS DocumentosFiscais CASCADE
;
DROP TABLE IF EXISTS NaturezasOperacoes CASCADE
;
DROP TABLE IF EXISTS Cfop CASCADE
;
DROP TABLE IF EXISTS Enderecos CASCADE
;
DROP TABLE IF EXISTS ParametrosGlobais CASCADE
;
DROP TABLE IF EXISTS Menus CASCADE
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

CREATE TABLE EspecieDocFiscal
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_EspecieDocFiscal_descricao(descricao)

) 
;


CREATE TABLE CodVinculacoes
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_CodVinculacoes_descricao(descricao)

) 
;


CREATE TABLE Mensagens
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_Mensagens_descricao(descricao)

) 
;


CREATE TABLE CanaisVenda
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_CanaisVenda_descricao(descricao)

) 
;


CREATE TABLE Mercados
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_Mercados_descricao(descricao)

) 
;


CREATE TABLE Cofins
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	percInterno DECIMAL(10,10) NULL,
	percExterno DECIMAL(10,10) NULL,
	percAnterior DECIMAL(10,10) NULL,
	codTributacao INTEGER NOT NULL,
	percRetencao DECIMAL(10,10) NULL,
	descZFM DECIMAL(10,10) NULL,
	PRIMARY KEY (id),
	KEY (codTributacao)

) 
;


CREATE TABLE PIS
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	percInterno DECIMAL(10,10) NULL,
	percExterno DECIMAL(10,10) NULL,
	codTributacao INTEGER NOT NULL,
	percRetencao DECIMAL(10,10) NULL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE ImpostoImportacao
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_ImpostoImportacao_descricao(descricao)

) 
;


CREATE TABLE Inss
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	percInss DECIMAL(10,10) NULL,
	percSat DECIMAL(10,10) NULL,
	percSenar DECIMAL(10,10) NULL,
	PRIMARY KEY (id)

) 
;


CREATE TABLE BasesIcms
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_BasesIcms_descricao(descricao)

) 
;


CREATE TABLE destReducao
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_destReducao_descricao(descricao)

) 
;


CREATE TABLE Icms
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	codTributacao INTEGER NOT NULL,
	aliquota DECIMAL(10,10) NOT NULL,
	aliquotaComplementar DECIMAL(10,10) NULL,
	baseIcms INTEGER NOT NULL,
	tipoBaseIcms INTEGER NOT NULL,
	extornaIcms BOOL NOT NULL,
	percDescontoIcms DECIMAL(10,10) NULL,
	percDescZonaFranca DECIMAL(10,10) NULL,
	percReducaoIcms DECIMAL(10,10) NULL,
	destReducao INTEGER NOT NULL,
	situacaoTributaria BOOL NOT NULL,
	percIcmsSubsTrib DECIMAL(10,10) NULL,
	itIcmsCobrST BOOL NOT NULL,
	icmsOutVlrST BOOL NOT NULL,
	gerarCreditoST BOOL NOT NULL,
	diminuiSTFrete BOOL NOT NULL,
	consumidorFinal BOOL NOT NULL,
	itIcmsSuspenso BOOL NOT NULL,
	itIcmsDiferido BOOL NOT NULL,
	naoTributada BOOL NOT NULL,
	contSubsAntecipada BOOL NOT NULL,
	icmsSubsTribAntecipada BOOL NOT NULL,
	cedSubsTribAntecipada BOOL NOT NULL,
	icmsStRepassar BOOL NOT NULL,
	icmsStComplementar BOOL NOT NULL,
	PRIMARY KEY (id),
	KEY (baseIcms),
	KEY (codTributacao),
	KEY (destReducao)

) 
;


CREATE TABLE Iss
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	tipoBase INTEGER NOT NULL,
	codTributacao INTEGER NULL,
	reducaoIss DECIMAL(10,10) NULL,
	retemIssFonte BOOL NOT NULL,
	naturezaVinculada BOOL NULL,
	retemIrFonte BOOL NOT NULL,
	irrf DECIMAL(10,10) NOT NULL,
	consideraIcmsOutNfe BOOL NOT NULL,
	PRIMARY KEY (id),
	KEY (codTributacao),
	KEY (tipoBase)

) 
;


CREATE TABLE Ipi
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	tipoBase INTEGER NOT NULL,
	incluiFreteBs BOOL NOT NULL,
	codVinculacao INTEGER NOT NULL,
	codTributacao INTEGER NOT NULL,
	percReducao DECIMAL(10,10) NULL,
	impOutDanfe BOOL NOT NULL,
	incBaseIcms BOOL NOT NULL,
	incIcmsOut BOOL NOT NULL,
	incOutTot BOOL NOT NULL,
	incOutBsSubs BOOL NOT NULL,
	escrituracaoFrete BOOL NOT NULL,
	estorna BOOL NOT NULL,
	imune BOOL NOT NULL,
	naoTrib BOOL NOT NULL,
	PRIMARY KEY (id),
	KEY (codTributacao),
	KEY (codVinculacao),
	KEY (tipoBase)

) 
;


CREATE TABLE CodTributacoes
(
	id INTEGER NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	UNIQUE UQ_CodTributacoes_descricao(descricao),
	UNIQUE UQ_CodTributacoes_id(id)

) 
;


CREATE TABLE TiposBase
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_TiposBase_descricao(descricao)

) 
;


CREATE TABLE OpEntregasFutAntecipadas
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_OpEntregasFutAntecipadas_descricao(descricao)

) 
;


CREATE TABLE Atualizacoes
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	calculoAutom BOOL NOT NULL,
	impressaAutom BOOL NOT NULL,
	baixarEstoque BOOL NOT NULL,
	ctrlEstoqueAutom BOOL NOT NULL,
	gerarDuplicata BOOL NOT NULL,
	crAutom BOOL NOT NULL,
	gerarObrigFiscal BOOL NOT NULL,
	atualizarCotas BOOL NOT NULL,
	nfsNTribCiap BOOL NOT NULL,
	nfsTribCiap BOOL NOT NULL,
	gerarContabilizacao BOOL NOT NULL,
	contabilizacaoAutom BOOL NOT NULL,
	atualizarEstatistica BOOL NOT NULL,
	estatisticaAutom BOOL NOT NULL,
	opEntregaFutAntec INTEGER NULL,
	PRIMARY KEY (id),
	KEY (opEntregaFutAntec)

) 
;


CREATE TABLE AdicionaisNaturezas
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	operacaoTransferencia BOOL NOT NULL,
	naturezaComplementar INTEGER NOT NULL,
	gerarNotaFaturamento BOOL NOT NULL,
	notaRateio BOOL NOT NULL,
	notaPropria BOOL NOT NULL,
	notaComercio BOOL NOT NULL,
	compraVendaAtivo BOOL NOT NULL,
	geraFichaAutomatico BOOL NOT NULL,
	inicioCreditoAutomatico BOOL NOT NULL,
	vendaAmbulante BOOL NOT NULL,
	gerarDevValor BOOL NOT NULL,
	opTriangular BOOL NOT NULL,
	drawback BOOL NOT NULL,
	memExportacao BOOL NOT NULL,
	opTerceiros BOOL NOT NULL,
	tpOpTerceiro INTEGER NOT NULL,
	tpDevolucaoConsig INTEGER NOT NULL,
	alterarValorItTerceiros BOOL NOT NULL,
	vvItTerceiros DECIMAL(10,10) NULL,
	tpCompraVenda INTEGER NULL,
	naturezaBonificacao BOOL NOT NULL,
	ValNaturezaBonificacao INTEGER NULL,
	PRIMARY KEY (id),
	KEY (naturezaComplementar)

) 
;


CREATE TABLE TiposNaturezas
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_TiposNaturezas_descricao(descricao)

) 
;


CREATE TABLE DocumentosFiscais
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	especie INTEGER NOT NULL,
	modelo VARCHAR(2) NOT NULL,
	mensagem INTEGER NOT NULL,
	modeloCupomFiscal VARCHAR(2) NULL,
	PRIMARY KEY (id),
	KEY (especie),
	KEY (mensagem)

) 
;


CREATE TABLE NaturezasOperacoes
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	tipo INTEGER NOT NULL,
	cfop INTEGER NOT NULL,
	mercado INTEGER NOT NULL,
	canalVenda INTEGER NULL,
	mensagem INTEGER NULL,
	documentoFiscal INTEGER NOT NULL,
	ativa BOOL NOT NULL,
	adicional INTEGER NOT NULL,
	atualizacao INTEGER NOT NULL,
	narrativa TEXT NULL,
	retemInssNaFonte BOOL NOT NULL,
	suspensaoIi BOOL NULL,
	percRetencaoCsll DECIMAL(10,10) NULL,
	incIpiBase BOOL NOT NULL,
	incIpiOutBase BOOL NOT NULL,
	incIpiBaseRet BOOL NOT NULL,
	incIpiOutBaseRet BOOL NOT NULL,
	deduzDescZfm BOOL NOT NULL,
	incIcmsStBaseRet BOOL NOT NULL,
	incIcmsStBaseIrRet BOOL NOT NULL,
	considIcmsNfeEntFat BOOL NOT NULL,
	icmsIncideBaseIcms BOOL NOT NULL,
	icmsIncideTnf BOOL NOT NULL,
	considIcmsNfeEntRec BOOL NOT NULL,
	considPisNfeEntFat BOOL NOT NULL,
	considCofinsNfeEntFat BOOL NOT NULL,
	ipi INTEGER NOT NULL,
	cofins INTEGER NOT NULL,
	iss INTEGER NOT NULL,
	pis INTEGER NOT NULL,
	inss INTEGER NOT NULL,
	icms INTEGER NOT NULL,
	impostoImportacao INTEGER NOT NULL,
	PRIMARY KEY (id),
	KEY (adicional),
	KEY (atualizacao),
	KEY (canalVenda),
	KEY (cfop),
	KEY (cofins),
	KEY (documentoFiscal),
	KEY (icms),
	KEY (impostoImportacao),
	KEY (inss),
	KEY (ipi),
	KEY (iss),
	KEY (mensagem),
	KEY (mercado),
	KEY (pis),
	KEY (tipo)

) 
;


CREATE TABLE Cfop
(
	codigo VARCHAR(5) NOT NULL,
	descricao VARCHAR(255) NOT NULL,
	id INTEGER NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	UNIQUE UQ_Cfop_descricao(descricao)

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


CREATE TABLE ParametrosGlobais
(
	id INTEGER NOT NULL AUTO_INCREMENT,
	value varchar(200) NOT NULL,
	empresa INTEGER NOT NULL,
	name VARCHAR(200) NOT NULL,
	PRIMARY KEY (id),
	UNIQUE UQ_ParametrosGlobais_name(name),
	KEY (empresa)

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
	UNIQUE UQ_Menus_nome(nome, menuPai),
	KEY (menuPai)

) 
;


CREATE TABLE Permissoes
(
	id integer NOT NULL AUTO_INCREMENT,
	menu INTEGER NOT NULL,
	editar boolean NULL,
	ler boolean NULL,
	criar boolean NULL,
	remover boolean NULL,
	credencial INTEGER NOT NULL,
	empresa INTEGER NOT NULL,
	PRIMARY KEY (id),
	KEY (credencial),
	KEY (empresa),
	KEY (menu)

) 
;


CREATE TABLE Usuarios
(
	Id INTEGER NOT NULL AUTO_INCREMENT,
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
	codigo VARCHAR(50) NOT NULL,
	codigoNire VARCHAR(5) NOT NULL,
	telefone INTEGER NOT NULL,
	endereco INTEGER NOT NULL,
	inscricaoMunicipal VARCHAR(200) NULL,
	codigoIbge VARCHAR(50) NOT NULL,
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
	senha VARCHAR(200) NOT NULL,
	email varchar(50) NULL,
	admin BOOL NOT NULL DEFAULT false,
	dataExpiracao DATE NULL,
	bloqueado BOOL NOT NULL,
	PRIMARY KEY (id),
	UNIQUE uq_cred_login(login, email)

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





ALTER TABLE Cofins ADD CONSTRAINT FK_Cofins_CodTributacoes 
	FOREIGN KEY (codTributacao) REFERENCES CodTributacoes (id)
;

ALTER TABLE Icms ADD CONSTRAINT FK_Icms_BasesIcms 
	FOREIGN KEY (baseIcms) REFERENCES BasesIcms (id)
;

ALTER TABLE Icms ADD CONSTRAINT FK_Icms_CodTributacoes 
	FOREIGN KEY (codTributacao) REFERENCES CodTributacoes (id)
;

ALTER TABLE Icms ADD CONSTRAINT FK_Icms_destReducao 
	FOREIGN KEY (destReducao) REFERENCES destReducao (id)
;

ALTER TABLE Iss ADD CONSTRAINT FK_Iss_CodTributacoes 
	FOREIGN KEY (codTributacao) REFERENCES CodTributacoes (id)
;

ALTER TABLE Iss ADD CONSTRAINT FK_Iss_TiposBase 
	FOREIGN KEY (tipoBase) REFERENCES TiposBase (id)
;

ALTER TABLE Ipi ADD CONSTRAINT FK_Ipi_CodTributacoes 
	FOREIGN KEY (codTributacao) REFERENCES CodTributacoes (id)
;

ALTER TABLE Ipi ADD CONSTRAINT FK_Ipi_CodVinculacoes 
	FOREIGN KEY (codVinculacao) REFERENCES CodVinculacoes (id)
;

ALTER TABLE Ipi ADD CONSTRAINT FK_Ipi_TiposBase 
	FOREIGN KEY (tipoBase) REFERENCES TiposBase (id)
;

ALTER TABLE Atualizacoes ADD CONSTRAINT FK_Atualizacoes_OpEntregasFutAntecipadas 
	FOREIGN KEY (opEntregaFutAntec) REFERENCES OpEntregasFutAntecipadas (id)
;

ALTER TABLE AdicionaisNaturezas ADD CONSTRAINT FK_AdicionaisNaturezas_NaturezasOperacoes 
	FOREIGN KEY (naturezaComplementar) REFERENCES NaturezasOperacoes (id)
;

ALTER TABLE DocumentosFiscais ADD CONSTRAINT FK_DocumentosFiscais_EspecieDocFiscal 
	FOREIGN KEY (especie) REFERENCES EspecieDocFiscal (id)
;

ALTER TABLE DocumentosFiscais ADD CONSTRAINT FK_DocumentosFiscais_Mensagens 
	FOREIGN KEY (mensagem) REFERENCES Mensagens (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_AdicionaisNaturezas 
	FOREIGN KEY (adicional) REFERENCES AdicionaisNaturezas (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Atualizacoes 
	FOREIGN KEY (atualizacao) REFERENCES Atualizacoes (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_CanaisVenda 
	FOREIGN KEY (canalVenda) REFERENCES CanaisVenda (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Cfop 
	FOREIGN KEY (cfop) REFERENCES Cfop (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Cofins 
	FOREIGN KEY (cofins) REFERENCES Cofins (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_DocumentosFiscais 
	FOREIGN KEY (documentoFiscal) REFERENCES DocumentosFiscais (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Icms 
	FOREIGN KEY (icms) REFERENCES Icms (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_ImpostoImportacao 
	FOREIGN KEY (impostoImportacao) REFERENCES ImpostoImportacao (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Inss 
	FOREIGN KEY (inss) REFERENCES Inss (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Ipi 
	FOREIGN KEY (ipi) REFERENCES Ipi (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Iss 
	FOREIGN KEY (iss) REFERENCES Iss (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Mensagens 
	FOREIGN KEY (mensagem) REFERENCES Mensagens (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_Mercados 
	FOREIGN KEY (mercado) REFERENCES Mercados (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_PIS 
	FOREIGN KEY (pis) REFERENCES PIS (id)
;

ALTER TABLE NaturezasOperacoes ADD CONSTRAINT FK_NaturezasOperacoes_TiposNaturezas 
	FOREIGN KEY (tipo) REFERENCES TiposNaturezas (id)
;

ALTER TABLE ParametrosGlobais ADD CONSTRAINT FK_ParametrosGlobais_Empresas 
	FOREIGN KEY (empresa) REFERENCES Empresas (id)
;

ALTER TABLE Menus ADD CONSTRAINT FK_Menus_Menus 
	FOREIGN KEY (menuPai) REFERENCES Menus (id)
;

ALTER TABLE Permissoes ADD CONSTRAINT FK_Permissoes_Credenciais 
	FOREIGN KEY (credencial) REFERENCES Credenciais (id)
;

ALTER TABLE Permissoes ADD CONSTRAINT FK_Permissoes_Empresas 
	FOREIGN KEY (empresa) REFERENCES Empresas (id)
;

ALTER TABLE Permissoes ADD CONSTRAINT FK_Permissoes_Menus 
	FOREIGN KEY (menu) REFERENCES Menus (id)
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
