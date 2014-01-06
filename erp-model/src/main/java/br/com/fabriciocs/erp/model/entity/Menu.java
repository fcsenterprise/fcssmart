package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
@Transactional
@Table("Menus")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" , "menu"})
@BelongsTo(parent = Menu.class, foreignKeyName = "menuPai")
public class Menu extends Model {
	public void setNome(String nome) {
		setString("nome", nome);
	}

	public String getNome() {
		return getString("nome");
	}

	public void setUrl(String url) {
		set("url", url);
	}

	public String getUrl() {
		return getString("url");
	}

	public void setIcone(String icone) {
		setString("icone", icone);
	}

	public String getIcone() {
		return getString("icone");
	}

	public void addSubMenu(Menu subMenu) {
		add(subMenu);
	}

	public void setMenu(Menu menu) {
		menu.saveIt();
		setParent(menu);
	}

	public Menu getMenu() {
		return parent(Menu.class);
	}
	
}
