package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;

public abstract class SimpleTable extends Model {
	public void setDescricao(String descricao) {
		setString("descricao", descricao);
	}

	public String getDescricao() {
		return getString("descricao");
	}
}

