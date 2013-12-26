package br.com.fabriciocs.erp.view.controller;

import br.com.fabriciocs.erp.model.entity.Empresa;
import br.com.fabriciocs.erp.model.entity.Permissao;

public class PermissaoView {
	private Empresa empresa;
	private String menu;
	private Permissao permissao;
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	
	
}
