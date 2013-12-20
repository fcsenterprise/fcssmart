package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table("Enderecos")
@Repository
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Endereco extends Model {
	public void setDescricao(String descricao) {
		setString("descricao", descricao);
	}

	public String getDescricao() {
		return getString("descricao");
	}

	public void setCep(String cep) {
		setString("cep", cep);
	}

	public String getCep() {
		return getString("cep");
	}

	public void setRua(String rua) {
		setString("rua", rua);
	}

	public String getRua() {
		return getString("rua");
	}

	public void setNumero(String numero) {
		setString("numero", numero);
	}

	public String getNumero() {
		return getString("numero");
	}

	public void setComplemento(String complemento) {
		setString("complemento", complemento);
	}

	public String getComplemento() {
		return getString("complemento");
	}

	public String getBairro() {
		return getString("bairro");
	}

	public void setBairro(String bairro) {
		setString("bairro", bairro);
	}

	public void setCidade(String cidade) {
		setString("cidade", cidade);
	}

	public String getCidade() {
		return getString("cidade");
	}

	public void setEstado(String estado) {
		setString("estado", estado);
	}

	public String getEstado() {
		return getString("estado");
	}

	public void setReferencia(String referencia) {
		setString("referencia", referencia);
	}

	public String getReferencia() {
		return getString("referencia");
	}
}
