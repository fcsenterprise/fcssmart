package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("DocumentosFiscais")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class DocumentoFiscal extends Model {
	public void setEspecie(EspecieDocFiscal especie) {
		setParent(especie);
	}

	public EspecieDocFiscal getEspecie() {
		return parent(EspecieDocFiscal.class);
	}

	public void setModelo(String modelo) {
		setString("modelo", modelo);
	}

	public String getModelo() {
		return getString("modelo");
	}

	public void setMensagem(Mensagem mensagem) {
		setParent(mensagem);
	}

	public Mensagem getMensagem() {
		return parent(Mensagem.class);
	}

	public void setModeloCupomFiscal(String modeloCupomFiscal) {
		setString("modeloCupomFiscal", modeloCupomFiscal);
	}

	public String getModeloCupomFiscal() {
		return getString("modeloCupomFiscal");
	}

}
