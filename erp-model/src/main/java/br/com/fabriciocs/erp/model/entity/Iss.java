package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("Iss")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Iss extends Model {
	public void setTipoBase(TipoBase tipoBase) {
		setParent(tipoBase);
	}

	public TipoBase getTipoBase() {
		return parent(TipoBase.class);
	}

	public void setCodTributacao(CodigoTributacao codTributacao) {
		setParent(codTributacao);
	}

	public CodigoTributacao getCodTributacao() {
		return parent(CodigoTributacao.class);
	}

	public void setReducaoIss(Double reducaoIss) {
		setDouble("reducaoIss", reducaoIss);
	}

	public Double getReducaoIss() {
		return getDouble("reducaoIss");
	}

	public void setRetemIssFonte(Boolean retemIssFonte) {
		setBoolean("retemIssFonte", retemIssFonte);
	}

	public Boolean getRetemIssFonte() {
		return getBoolean("retemIssFonte");
	}

	public void setNaturezaVinculada(Boolean naturezaVinculada) {
		setBoolean("naturezaVinculada", naturezaVinculada);
	}

	public Boolean getNaturezaVinculada() {
		return getBoolean("naturezaVinculada");
	}

	public void setRetemIrFonte(Boolean retemIrFonte) {
		setBoolean("retemIrFonte", retemIrFonte);
	}

	public Boolean getRetemIrFonte() {
		return getBoolean("retemIrFonte");
	}

	public void setIrrf(Boolean irrf) {
		setBoolean("irrf", irrf);
	}

	public Boolean getIrrf() {
		return getBoolean("irrf");
	}

	public void setConsideraIcmsOutNfe(Boolean consideraIcmsOutNfe) {
		setBoolean("consideraIcmsOutNfe", consideraIcmsOutNfe);
	}

	public Boolean getConsideraIcmsOutNfe() {
		return getBoolean("consideraIcmsOutNfe");
	}

}
