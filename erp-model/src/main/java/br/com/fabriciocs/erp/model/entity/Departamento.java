package br.com.fabriciocs.erp.model.entity;

import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("Departamentos")
@BelongsTo(parent = Empresa.class, foreignKeyName = "empresa")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Departamento extends Model {
	public Integer getId() {
		return getInteger(getIdName());
	}

	public String getNome() {
		return getString("nome");
	}

	public void setNome(String nome) {
		setString("nome", nome);
	}

	public String getDescricao() {
		return getString("descricao");
	}

	public void setDescricao(String descricao) {
		setString("descricao", descricao);
	}

	public void setEmpresa(Empresa empresa) {
		empresa.saveIt();
		setParent(empresa);
	}

	public Empresa getEmpresa() {
		return parent(Empresa.class);
	}

	public void addFuncionario(Usuario funcionario) {
		funcionario.saveIt();
		add(funcionario);
	}

	@JsonIgnore
	public List<Usuario> getFuncionarios() {
		return getAll(Usuario.class);
	}
}
