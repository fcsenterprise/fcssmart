
INSERT INTO `Menus` (`id`, `nome`, `menuPai`, `url`, `icone`) VALUES
(1, 'Gerenciador', NULL, NULL, 'fa fa-windows'),
(2, 'Módulos', NULL, NULL, 'fa fa-book'),
(5, 'Ativo Fixo', 2, NULL, 'fa fa-folder'),
(6, 'Compras', 2, NULL, 'fa fa-folder'),
(7, 'Estoque/Custo', 2, NULL, 'fa fa-folder'),
(8, 'Faturamento', 2, NULL, 'fa fa-folder'),
(9, 'Financeiro', 2, NULL, 'fa fa-folder'),
(10, 'Gestão Pessoal', 2, NULL, 'fa fa-folder'),
(11, 'Livros Fiscais', 2, NULL, 'fa fa-folder'),
(12, 'P.C.P.', 2, NULL, 'fa fa-folder'),
(13, 'Controle de lojas', 2, NULL, 'fa fa-folder'),
(14, 'Call Center', 2, NULL, 'fa fa-folder'),
(15, 'Ponto Eletrônico', 2, NULL, 'fa fa-folder'),
(16, 'Funcionários', 2, NULL, 'fa fa-folder'),
(17, 'Manutenção', 5, NULL, 'fa fa-folder'),
(18, 'Movimentação', 5, NULL, 'fa fa-folder'),
(19, 'Consultas', 5, NULL, 'fa fa-folder'),
(20, 'Relatórios', 5, NULL, 'fa fa-folder'),
(21, 'Manutenção', 6, NULL, 'fa fa-folder'),
(22, 'Movimentação', 6, NULL, 'fa fa-folder'),
(23, 'Consultas', 6, NULL, 'fa fa-folder'),
(24, 'Relatórios', 6, NULL, 'fa fa-folder'),
(25, 'Manutenção', 7, NULL, 'fa fa-folder'),
(26, 'Movimentação', 7, NULL, 'fa fa-folder'),
(27, 'Consultas', 7, NULL, 'fa fa-folder'),
(28, 'Relatórios', 7, NULL, 'fa fa-folder'),
(29, 'Manutenção', 8, NULL, 'fa fa-folder'),
(30, 'Movimentação', 8, NULL, 'fa fa-folder'),
(31, 'Consultas', 8, NULL, 'fa fa-folder'),
(32, 'Relatórios', 8, NULL, 'fa fa-folder'),
(33, 'Manutenção', 9, NULL, 'fa fa-folder'),
(34, 'Movimentação', 9, NULL, 'fa fa-folder'),
(35, 'Consultas', 9, NULL, 'fa fa-folder'),
(36, 'Relatórios', 9, NULL, 'fa fa-folder'),
(37, 'Manutenção', 10, NULL, 'fa fa-folder'),
(38, 'Movimentação', 10, NULL, 'fa fa-folder'),
(39, 'Consultas', 10, NULL, 'fa fa-folder'),
(40, 'Relatórios', 10, NULL, 'fa fa-folder'),
(41, 'Manutenção', 11, NULL, 'fa fa-folder'),
(42, 'Movimentação', 11, NULL, 'fa fa-folder'),
(43, 'Consultas', 11, NULL, 'fa fa-folder'),
(44, 'Relatórios', 11, NULL, 'fa fa-folder'),
(45, 'Manutenção', 12, NULL, 'fa fa-folder'),
(46, 'Movimentação', 12, NULL, 'fa fa-folder'),
(47, 'Consultas', 12, NULL, 'fa fa-folder'),
(48, 'Relatórios', 12, NULL, 'fa fa-folder'),
(49, 'Manutenção', 13, NULL, 'fa fa-folder'),
(50, 'Movimentação', 13, NULL, 'fa fa-folder'),
(51, 'Consultas', 13, NULL, 'fa fa-folder'),
(52, 'Relatórios', 13, NULL, 'fa fa-folder'),
(53, 'Manutenção', 14, NULL, 'fa fa-folder'),
(54, 'Movimentação', 14, NULL, 'fa fa-folder'),
(55, 'Consultas', 14, NULL, 'fa fa-folder'),
(56, 'Relatórios', 14, NULL, 'fa fa-folder'),
(57, 'Manutenção', 15, NULL, 'fa fa-folder'),
(58, 'Movimentação', 15, NULL, 'fa fa-folder'),
(59, 'Consultas', 15, NULL, 'fa fa-folder'),
(60, 'Relatórios', 15, NULL, 'fa fa-folder'),
(61, 'Manutenção', 16, NULL, 'fa fa-folder'),
(62, 'Movimentação', 16, NULL, 'fa fa-folder'),
(63, 'Consultas', 16, NULL, 'fa fa-folder'),
(64, 'Relatórios', 16, NULL, 'fa fa-folder'),
(65, 'Empresas', 1, '#/empresa', 'icon- fa-building-o'),
(66, 'Menus', 1, '#/menu', 'fa icon-th-list'),
(67, 'Usuarios', 1, '#/usuario', 'icon-fa fa-user'),
(68, 'Departamentos', 21, '#/departamento', 'fa fa-group'),
(69, 'Vendas', 2, NULL, 'fa fa-folder'),
(70, 'Manutenção', 69, NULL, 'fa fa-folder');

insert into Credenciais (id,login, senha, email) values(1,'admin', '21232f297a57a5a743894a0e4a801fc3', 'fabriciodacunhasantos@gmail.com');
insert into Usuarios (nome, cpf, credencial) values ('fabricio', '02623805105', 1);
