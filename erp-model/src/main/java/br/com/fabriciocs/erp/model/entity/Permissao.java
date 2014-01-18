package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("Permissoes")
@BelongsToParents({
		@BelongsTo(foreignKeyName = "credencial", parent = Credencial.class),
		@BelongsTo(foreignKeyName = "menu", parent = Menu.class),
		@BelongsTo(foreignKeyName = "empresa", parent = Empresa.class) })
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Permissao extends Model {
	public void setMenu(Menu menu) {
		setParent(menu);
	}

	public Menu getMenu() {
		return parent(Menu.class);
	}

	public void setEmpresa(Empresa empresa) {
		empresa.saveIt();
		setParent(empresa);
	}

	public Empresa getEmpresa() {
		return parent(Empresa.class);
	}

	public void setCredencial(Credencial credencial) {
		if (credencial.getId() != null) {
			if (credencial.getSenha() == null
					|| credencial.getSenha().isEmpty()) {
				credencial.setSenha(Credencial.<Credencial> findById(
						credencial.getId()).getSenha());
			}
		} else {
			credencial.setSenha("temp");
		}
		credencial.saveIt();
		setParent(credencial);
	}

	public Credencial getCredencial() {
		return parent(Credencial.class);
	}

	public void setEditar(Boolean editar) {
		setBoolean("editar", editar);
	}

	public Boolean getEditar() {
		return getBoolean("editar");
	}

	public void setCriar(Boolean value) {
		setBoolean("criar", value);
	}

	public Boolean getCriar() {
		return getBoolean("criar");
	}

	public void setRemover(Boolean excluir) {
		setBoolean("remover", excluir);
	}

	public Boolean getRemover() {
		return getBoolean("remover");
	}

	public void setLer(Boolean ler) {
		setBoolean("ler", ler);
	}

	public Boolean getLer() {
		return getBoolean("ler");
	}
}
