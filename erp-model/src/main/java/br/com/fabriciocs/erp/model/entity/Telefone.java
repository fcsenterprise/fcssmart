package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table("Telefones")
@Repository
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Telefone extends Model {
	public void setDdd(String ddd) {
		setString("ddd", ddd);
	}

	public String getDdd() {
		return getString("ddd");
	}

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
