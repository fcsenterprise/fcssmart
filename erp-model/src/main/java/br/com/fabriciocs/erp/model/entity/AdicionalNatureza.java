package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
@Table("AdicionaisNaturezas")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class AdicionalNatureza extends Model {

	public void setOperacaoTransferencia(Boolean operacaoTransferencia) {
		setBoolean("operacaoTransferencia", operacaoTransferencia);
	}

	public Boolean getOperacaoTransferencia() {
		return getBoolean("operacaoTransferencia");
	}

	public void setNaturezaComplementar(NaturezaOperacao naturezaOperacao) {
		setParent(naturezaOperacao);
	}

	public NaturezaOperacao getNaturezaComplementar() {
		return parent(NaturezaOperacao.class);
	}

	public ValNaturezaBonificacao getValNaturezaBonificacao() {
		return parent(ValNaturezaBonificacao.class);
	}

	public void setValNaturezaBonificacao(ValNaturezaBonificacao valNaturezaBonificacao) {
		setParent(valNaturezaBonificacao);
	}

	public Boolean getNaturezaBonificacao() {
		return getBoolean("naturezaBonificacao");
	}

	public void setNaturezaBonificacao(Boolean naturezaBonificacao) {
		setDouble("naturezaBonificacao", naturezaBonificacao);
	}

	public Double getPercReducaoIcms() {
		return getDouble("percReducaoIcms");
	}

	public void setTipoCompraVenda(TipoCompraVenda tipoCompraVenda) {
		setParent(tipoCompraVenda);
	}

	public TipoCompraVenda getTipoCompraVenda() {
		return parent(TipoCompraVenda.class);
	}

	public void setVvItTerceiros(Double vvItTerceiros) {
		setDouble("vvItTerceiros", vvItTerceiros);
	}

	public Double getVvItTerceiros() {
		return getDouble("vvItTerceiros");
	}

	public void setAlterarValorItTerceiros(Boolean alterarValorItTerceiros) {
		setBoolean("alterarValorItTerceiros", alterarValorItTerceiros);
	}

	public Boolean getAlterarValorItTerceiros() {
		return getBoolean("alterarValorItTerceiros");
	}

	public TipoDevolucaoConsignado getTpDevolucaoConsignado() {
		return parent(TipoDevolucaoConsignado.class);
	}

	public void setTpDevolucaoConsignado(TipoDevolucaoConsignado tipoDevolucaoConsignado) {
		setParent(tipoDevolucaoConsignado);
	}

	public TipoOperacaoTerceiros getTpOpTerceiros() {
		return parent(TipoOperacaoTerceiros.class);
	}

	public void setTpOpTerceiros(TipoOperacaoTerceiros tipoOperacaoTerceiros) {
		setParent(tipoOperacaoTerceiros);
	}

	public void setOpTerceiros(Boolean opTerceiros) {
		setBoolean("opTerceiros", opTerceiros);
	}

	public Boolean getOpTerceiros() {
		return getBoolean("opTerceiros");
	}

	public void setMemExportacao(Boolean memExportacao) {
		setBoolean("memExportacao", memExportacao);

	}

	public Boolean getMemExportacao() {
		return getBoolean("memExportacao");
	}

	public Boolean getDrawBack() {
		return getBoolean("drawBack");
	}

	public void setDrawBack(Boolean drawBack) {
		setBoolean("drawBack", drawBack);
	}

	public Boolean getOpTriangular() {
		return getBoolean("opTriangular");
	}

	public void setOpTriangular(Boolean opTriangular) {
		setBoolean("opTriangular", opTriangular);
	}

	public Boolean getGerarDevValor() {
		return getBoolean("gerarDevValor");
	}

	public void setGerarDevValor(Boolean gerarDevValor) {
		setBoolean("gerarDevValor", gerarDevValor);
	}

	public Boolean getVendaAmbulante() {
		return getBoolean("vendaAmbulante");
	}

	public void setVendaAmbulante(Boolean vendaAmbulante) {
		setBoolean("vendaAmbulante", vendaAmbulante);
	}

	public Boolean getInicioCreditoAutomatico() {
		return getBoolean("inicioCreditoAutomatico");
	}

	public void setInicioCreditoAutomatico(Boolean inicioCreditoAutomatico) {
		setBoolean("inicioCreditoAutomatico", inicioCreditoAutomatico);
	}

	public Boolean getGeraFichaAutomatico() {
		return getBoolean("geraFichaAutomatico");
	}

	public void setGeraFichaAutomatico(Boolean geraFichaAutomatico) {
		setBoolean("geraFichaAutomatico", geraFichaAutomatico);
	}

	public Boolean getCompraVendaAtivo() {
		return getBoolean("compraVendaAtivo");
	}

	public void setCompraVendaAtivo(Boolean compraVendaAtivo) {
		setBoolean("cedSubsTribAntecipada", compraVendaAtivo);
	}

	public Boolean getNotaRateio() {
		return getBoolean("notaRateio");
	}

	public void setNotaRateio(Boolean notaRateio) {
		setBoolean("notaRateio", notaRateio);
	}

	public Boolean getNotaPropria() {
		return getBoolean("notaPropria");
	}

	public void setNotaPropria(Boolean notaPropria) {
		setBoolean("notaRateio", notaPropria);
	}

	public Boolean getNotaComercio() {
		return getBoolean("notaComercio");
	}

	public void setNotaComercio(Boolean notaComercio) {
		setBoolean("notaRateio", notaComercio);
	}

	public Boolean getGerarNotaFaturamento() {
		return getBoolean("gerarNotaFaturamento");
	}

	public void setGerarNotaFaturamento(Boolean gerarNotaFaturamento) {
		setBoolean("gerarNotaFaturamento", gerarNotaFaturamento);
	}

}
