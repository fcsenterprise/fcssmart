package br.com.fabriciocs.erp.view.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.javalite.activejdbc.Base;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.fabriciocs.erp.model.entity.Credencial;
import br.com.fabriciocs.erp.model.entity.Empresa;
import br.com.fabriciocs.erp.model.entity.Endereco;
import br.com.fabriciocs.erp.model.entity.Menu;
import br.com.fabriciocs.erp.model.entity.Telefone;
import br.com.fabriciocs.erp.model.entity.Usuario;

public class AppStartupCtrl {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void configure() {
		Base.open(dataSource);
		configureEmpresa();
		configureUser();
		configureMenus();
		Base.close();
	}

	private void configureMenus() {
		List<Menu> menus = Menu.findAll();
		if (menus == null || menus.isEmpty()) {
			long id = 0;
			long idSupPai = id;
			long idPai = id;
			idPai = Menu.<Menu> createIt("id", null, "nome", "Gerenciador",
					"menuPai", null, "url", null, "icone", "fa fa-windows")
					.getLongId();
			Menu.<Menu> createIt("nome", "Empresas", "menuPai", idPai, "url",
					"#/empresa", "icone", "icon- fa-building-o");
			Menu.<Menu> createIt("nome", "Usuários", "menuPai", idPai, "url",
					"#/usuario", "icone", "icon-fa fa-user");
			Menu.<Menu> createIt("nome", "Conf. Email", "menuPai", idPai,
					"url", "#/email", "icone", "fa fa-envelope");

			idSupPai = Menu.<Menu> createIt("nome", "Módulos", "menuPai", null,
					"url", null, "icone", "fa fa-book").getLongId();
			idPai = Menu.<Menu> createIt("nome", "Ativo Fixo", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Compras", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			long curPai = Menu.<Menu> createIt("nome", "Manutenção", "menuPai",
					idPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Departamentos", "menuPai", curPai,
					"url", "#/departamento", "icone", "fa fa-group");

			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Vendas", "menuPai", idSupPai,
					"url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Estoque/Custo", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Faturamento", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Financeiro", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Gestão Pessoal", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Livros Fiscais", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "P.C.P.", "menuPai", idSupPai,
					"url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Controle de Lojas",
					"menuPai", idSupPai, "url", null, "icone", "fa fa-folder")
					.getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Call Center", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Ponto Eletrônico", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Funcionários", "menuPai",
					idSupPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

			idPai = Menu.<Menu> createIt("nome", "Relatórios", "menuPai",
					idPai, "url", null, "icone", "fa fa-folder").getLongId();

			Menu.<Menu> createIt("nome", "Manutenção", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Movimentação", "menuPai", idPai,
					"url", null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Consultas", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");
			Menu.<Menu> createIt("nome", "Relatórios", "menuPai", idPai, "url",
					null, "icone", "fa fa-folder");

		}
	}
	private void configureEmpresa(){
		if(Empresa.count() == 0){
			Long endereco = Endereco.createIt().getLongId();
			Long telefone = Telefone.createIt("ddd","064","numero","93332233").getLongId();
			Empresa.createIt("cnpj","11234567891011","razaoSocial","Empresa Default","nomeFantasia","Empresa Default","inscricaoEstadual","123456789","codigo","001","codigoNire","00001","telefone",telefone,"endereco",endereco,"inscricaoMunicipal","1234567890","codigoIbge","123456");
		}
	}

	private void configureUser() {
		Credencial admin = Credencial.findFirst(" login = ? ", "admin");
		if (admin == null) {
			Credencial credencial = Credencial
					.createIt(
							"login",
							"admin",
							"email",
							"fabriciodacunhasantos@gmail.com",
							"senha",
							"8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
							"admin", true,"bloqueado",false);
			Usuario.createIt("nome", "administrador", "cpf", "02623805105",
					"credencial", credencial.getLongId());
		}
	}
}
