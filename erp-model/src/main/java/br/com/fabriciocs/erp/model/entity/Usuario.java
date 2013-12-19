package br.com.fabriciocs.erp.model.entity;

import java.util.Date;
import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
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

	public void setDataNascimento(Date dataNascimento) {
		setDate("dataNascimento", dataNascimento);
	}

	public Date getDataNascimento() {
		return getDate("dataNascimento");
	}

	public List<Endereco> getEnderecos() {
		return getAll(Endereco.class);
	}

	public void addEndereco(Endereco endereco) {
		add(endereco);
	}

	public void setCredencial(Credencial credencial) {
		if (credencial.isFrozen() || credencial.getId() == null) {
			credencial.saveIt();
		}
		setParent(credencial);
	}

	public Credencial getCredencial() {
		return parent(Credencial.class);
	}
}