package br.com.fabriciocs.erp.model.entity;

import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Repository
@Table("menus")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
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

	@JsonInclude(Include.NON_NULL)
	public void setMenu(Menu menu) {
		if (menu.getId() != null) {
			setParent(menu);
		}
	}
	@JsonBackReference
	public Menu getMenu() {
		return parent(Menu.class);
	}

	@JsonManagedReference
	public List<Menu> getSubMenus() {
		return getAll(Menu.class);
	}
}
