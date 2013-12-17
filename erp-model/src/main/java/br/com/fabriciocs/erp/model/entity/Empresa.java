package br.com.fabriciocs.erp.model.entity;

import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table("empresas")
@Repository
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Empresa extends Model {

	public Integer getId() {
		return getInteger(getIdName());
	}

	public String getCnpjCei() {
		return getString("cnpjCei");
	}

	public void setCnpjCei(String cnpjCei) {
		setString("cnpjCei", cnpjCei);
	}

	public String getRazaoSocial() {
		return getString("razaoSocial");
	}

	public void setRazaoSocial(String razaoSocial) {
		setString("razaoSocial", razaoSocial);
	}

	public String getNomeFantasia() {
		return getString("nomeFantasia");
	}

	public void setNomeFantasia(String nomeFantasia) {
		setString("nomeFantasia", nomeFantasia);
	}

	public String getInscricaoEstadual() {
		return getString("inscricaoEstadual");
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		setString("inscricaoEstadual", inscricaoEstadual);
	}

	public void addDepartamento(Departamento departamento) {
		add(departamento);
	}

	@JsonIgnore
	public List<Departamento> getDepartamentos() {
		return getAll(Departamento.class);
	}

}
