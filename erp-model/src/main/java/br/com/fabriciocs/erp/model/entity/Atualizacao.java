package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
@Table("Atualizacoes")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Atualizacao extends Model {
	public Boolean getCalculoAutom() {
		return getBoolean("calculoAutom");
	}

	public void setCalculoAutom(Boolean calculoAutom) {
		setBoolean("calculoAutom", calculoAutom);
	}

	public Boolean getImpressaAutom() {
		return getBoolean("impressaAutom");
	}

	public void setImpressaAutom(Boolean impressaAutom) {
		setBoolean("impressaAutom", impressaAutom);
	}

	public Boolean getBaixarEstoque() {
		return getBoolean("baixarEstoque");
	}

	public void setBaixarEstoque(Boolean baixarEstoque) {
		setBoolean("baixarEstoque", baixarEstoque);
	}

	public Boolean getCtrlEstoqueAutom() {
		return getBoolean("ctrlEstoqueAutom");
	}

	public void setCtrlEstoqueAutom(Boolean ctrlEstoqueAutom) {
		setBoolean("ctrlEstoqueAutom", ctrlEstoqueAutom);
	}

	public Boolean getGerarDuplicata() {
		return getBoolean("gerarDuplicata");
	}

	public void setGerarDuplicata(Boolean gerarDuplicata) {
		setBoolean("gerarDuplicata", gerarDuplicata);
	}

	public Boolean getCrAutom() {
		return getBoolean("crAutom");
	}

	public void setCrAutom(Boolean crAutom) {
		setBoolean("crAutom", crAutom);
	}

	public Boolean getGerarObrigFiscal() {
		return getBoolean("gerarObrigFiscal");
	}

	public void setGerarObrigFiscal(Boolean gerarObrigFiscal) {
		setBoolean("gerarObrigFiscal", gerarObrigFiscal);
	}

	public Boolean getAtualizarCotas() {
		return getBoolean("atualizarCotas");
	}

	public void setAtualizarCotas(Boolean atualizarCotas) {
		setBoolean("atualizarCotas", atualizarCotas);
	}

	public Boolean getNfsNTribCiap() {
		return getBoolean("nfsNTribCiap");
	}

	public void setNfsNTribCiap(Boolean nfsNTribCiap) {
		setBoolean("nfsNTribCiap", nfsNTribCiap);
	}

	public Boolean getNfsTribCiap() {
		return getBoolean("nfsTribCiap");
	}

	public void setNfsTribCiap(Boolean nfsTribCiap) {
		setBoolean("nfsTribCiap", nfsTribCiap);
	}

	public Boolean getGerarContabilizacao() {
		return getBoolean("gerarContabilizacao");
	}

	public void setGerarContabilizacao(Boolean gerarContabilizacao) {
		setBoolean("gerarContabilizacao", gerarContabilizacao);
	}

	public Boolean getContabilizacaoAutom() {
		return getBoolean("contabilizacaoAutom");
	}

	public void setContabilizacaoAutom(Boolean contabilizacaoAutom) {
		setBoolean("contabilizacaoAutom", contabilizacaoAutom);
	}

	public Boolean getAtualizarEstatistica() {
		return getBoolean("atualizarEstatistica");
	}

	public void setAtualizarEstatistica(Boolean atualizarEstatistica) {
		setBoolean("atualizarEstatistica", atualizarEstatistica);
	}

	public Boolean getEstatisticaAutom() {
		return getBoolean("estatisticaAutom");
	}

	public void setEstatisticaAutom(Boolean estatisticaAutom) {
		setBoolean("estatisticaAutom", estatisticaAutom);
	}

	public void setOpEntregaFutAntec(OpEntregaFutAntec opEntregaFutAntec) {
		setParent(opEntregaFutAntec);
	}

	public OpEntregaFutAntec getOpEntregaFutAntec() {
		return parent(OpEntregaFutAntec.class);
	}

}
