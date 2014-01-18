package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("Cofins")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Cofins extends Model {
	public void setPercInterno(Double percInterno) {
		setDouble("percInterno", percInterno);
	}

	public Double getPercInterno() {
		return getDouble("percInterno");
	}

	public void setPercExterno(Double percExterno) {
		setDouble("percExterno", percExterno);
	}

	public Double getPercExterno() {
		return getDouble("percExterno");
	}

	public void setPercAnterior(Double percAnterior) {
		setDouble("percAnterior", percAnterior);
	}

	public Double getPercAnterior() {
		return getDouble("percAnterior");
	}

	public void setCodTributacao(CodigoTributacao codTributacao) {
		setParent(codTributacao);
	}

	public CodigoTributacao getCodTributacao() {
		return parent(CodigoTributacao.class);
	}

	public void setPercRetencao(Double percRetencao) {
		setDouble("percRetencao", percRetencao);
	}

	public Double getPercRetencao() {
		return getDouble("percRetencao");
	}

	public void setDescZfm(Double descZfm) {
		setDouble("descZfm", descZfm);
	}

	public Double getDescZfm() {
		return getDouble("descZfm");
	}

}
