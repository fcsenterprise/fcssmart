package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@BelongsToParents(value = { @BelongsTo(parent = Credencial.class, foreignKeyName = "credencial") })
@Table("Usuarios")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Usuario extends Model {

	public Integer getId() {
		return getInteger(getIdName());
	}

	public void setNome(String nome) {
		setString("nome", nome);
	}

	public String getNome() {
		return getString("nome");
	}

	public void setCpf(String cpf) {
		setString("cpf", cpf);
	}

	public String getCpf() {
		return getString("cpf");
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
}
