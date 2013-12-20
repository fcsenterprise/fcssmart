package br.com.fabriciocs.erp.model.entity;

import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table("Empresas")
@BelongsToParents({
		@BelongsTo(parent = Telefone.class, foreignKeyName = "telefone"),
		@BelongsTo(parent = Endereco.class, foreignKeyName = "endereco") })
@Repository
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Empresa extends Model {

	public Integer getId() {
		return getInteger(getIdName());
	}

	public String getCnpj() {
		return getString("cnpj");
	}

	public void setCnpj(String cnpj) {
		setString("cnpj", cnpj);
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

	public void setTelefone(Telefone telefone) {
		if (telefone.isFrozen() || telefone.getId() == null) {
			telefone.save();
		}
		setParent(telefone);
	}
	
	public void setCodigoIbge(String codigoIbge){
		setString("codigoIbge", codigoIbge);
	}
	
	public String getCodigoIbge(){
		return getString("codigoIbge");
	}

	public Telefone getTelefone() {
		return parent(Telefone.class);
	}

	public Endereco getEndereco() {
		return parent(Endereco.class);
	}

	public void setEndereco(Endereco endereco) {
		if (endereco.isFrozen() || endereco.getId() == null) {
			endereco.save();
		}
		setParent(endereco);
	}

	public String getInscricaoEstadual() {
		return getString("inscricaoEstadual");
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		setString("inscricaoEstadual", inscricaoEstadual);
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		setString("inscricaoMunicipal", inscricaoMunicipal);
	}

	public String getInscricaoMunicipal() {
		return getString("inscricaoMunicipal");
	}

	public void addDepartamento(Departamento departamento) {
		add(departamento);
	}

	public void setCodigo(Integer codigo) {
		setInteger("codigo", codigo);
	}

	public Integer getCodigo() {
		return getInteger("codigo");
	}

	public void setCodigoNire(String codigoNire) {
		setString("codigoNire", codigoNire);
	}

	public String getCodigoNire() {
		return getString("codigoNire");
	}

	@JsonIgnore
	public List<Departamento> getDepartamentos() {
		return getAll(Departamento.class);
	}

	@Override
	public boolean delete() {
		Endereco end = parent(Endereco.class);
		Telefone tel = parent(Telefone.class);
		boolean ret = super.delete();
		if (ret) {
			end.delete();
			tel.delete();
		}
		return ret;
	}

}
