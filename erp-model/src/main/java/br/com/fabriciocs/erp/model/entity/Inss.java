package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
@Table("Inss")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Inss extends Model {
	public void setPercInss(Double percInss) {
		setDouble("percInss", percInss);
	}

	public Double getPercInss() {
		return getDouble("percInss");
	}

	public void setPercSat(Double percSat) {
		setDouble("percSat", percSat);
	}

	public Double getPercSat() {
		return getDouble("percSat");
	}

	public void setPercSenar(Double percSenar) {
		setDouble("percSenar", percSenar);
	}

	public Double getPercSenar() {
		return getDouble("percSenar");
	}

}
