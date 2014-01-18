package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("Cfop")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Cfop extends SimpleTable {
	public void setCodigo(String codigo) {
		setString("codigo", codigo);
	}

	public String getCodigo() {
		return getString("codigo");
	}

}
