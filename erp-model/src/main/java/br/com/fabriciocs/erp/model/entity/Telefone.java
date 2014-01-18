package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Table("Telefones")

@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Telefone extends Model {
	public void setDdd(String ddd) {
		setString("ddd", ddd);
	}

	public String getDdd() {
		return getString("ddd");
	}

	@JsonInclude(Include.NON_EMPTY)
	public void setDescricao(String descricao) {
		setString("descricao", descricao);
	}

	public String getDescricao() {
		return getString("descricao");
	}

	public void setNumero(String numero) {
		setString("numero", numero);
	}

	public String getNumero() {
		return getString("numero");
	}

}
