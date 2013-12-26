package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Table("Enderecos")
@Repository
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Endereco extends Model {
	@JsonInclude(Include.NON_EMPTY)
	public void setDescricao(String descricao) {
		setString("descricao", descricao);
	}

	public String getDescricao() {
		return getString("descricao");
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setCep(String cep) {
		setString("cep", cep);
	}

	public String getCep() {
		return getString("cep");
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setRua(String rua) {
		setString("rua", rua);
	}

	public String getRua() {
		return getString("rua");
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setNumero(String numero) {
		setString("numero", numero);
	}

	public String getNumero() {
		return getString("numero");
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setComplemento(String complemento) {
		setString("complemento", complemento);
	}

	public String getComplemento() {
		return getString("complemento");
	}

	public String getBairro() {
		return getString("bairro");
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setBairro(String bairro) {
		setString("bairro", bairro);
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setCidade(String cidade) {
		setString("cidade", cidade);
	}

	public String getCidade() {
		return getString("cidade");
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setEstado(String estado) {
		setString("estado", estado);
	}

	public String getEstado() {
		return getString("estado");
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setReferencia(String referencia) {
		setString("referencia", referencia);
	}

	public String getReferencia() {
		return getString("referencia");
	}
}
