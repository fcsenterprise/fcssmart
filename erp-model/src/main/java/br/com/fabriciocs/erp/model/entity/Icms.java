package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("Icms")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Icms extends Model {
	public void setCodigoTributacao(CodigoTributacao codigoTributacao) {
		setParent(codigoTributacao);
	}

	public CodigoTributacao getCodigoTributacao() {
		return parent(CodigoTributacao.class);
	}

	public void setAliquota(Double aliquota) {
		setDouble("aliquota", aliquota);
	}

	public Double getAliquota() {
		return getDouble("aliquota");
	}

	public void setAliquotaComplementar(Double aliquota) {
		setDouble("aliquotaComplementar", aliquota);
	}

	public Double getAliquotaComplementar() {
		return getDouble("aliquotaComplementar");
	}

	public void setBaseIcms(BaseIcms baseIcms) {
		setParent(baseIcms);
	}

	public BaseIcms getBaseIcms() {
		return parent(BaseIcms.class);
	}

	public void setTipoBaseIcms(TipoBase tipoBaseIcms) {
		setParent(tipoBaseIcms);
	}

	public TipoBase getTipoBaseIcms() {
		return parent(TipoBase.class);
	}

	public void setExtornaIcms(Boolean extornaIcms) {
		setBoolean("extornaIcms", extornaIcms);
	}

	public Boolean getExtornaIcms() {
		return getBoolean("extornaIcms");
	}

	public void setPercDescontoIcms(Double percDescontoIcms) {
		setDouble("percDescontoIcms", percDescontoIcms);
	}

	public Double getPercDescontoIcms() {
		return getDouble("percDescontoIcms");
	}

	public void setPercDescZonaFranca(Double percDescZonaFranca) {
		setDouble("percDescZonaFranca", percDescZonaFranca);
	}

	public Double getPercDescZonaFranca() {
		return getDouble("percDescZonaFranca");
	}

	public void setPercReducaoIcms(Double percReducaoIcms) {
		setDouble("percReducaoIcms", percReducaoIcms);
	}

	public Double getPercReducaoIcms() {
		return getDouble("percReducaoIcms");
	}

	public void setDestReducao(DestReducao destReducao) {
		setParent(destReducao);
	}

	public DestReducao getDestReducao() {
		return parent(DestReducao.class);
	}

	public void setSubsTributaria(Boolean subsTributaria) {
		setBoolean("subsTributaria", subsTributaria);
	}

	public Boolean getSubsTributaria() {
		return getBoolean("subsTributaria");
	}

	public void setPercIcmsSubsTrib(Double percIcmsSubsTrib) {
		setDouble("percIcmsSubsTrib", percIcmsSubsTrib);
	}

	public Double getPercIcmsSubsTrib() {
		return getDouble("percIcmsSubsTrib");
	}

	public void setItIcmsCobrSt(Boolean itIcmsCobrSt) {
		setBoolean("itIcmsCobrSt", itIcmsCobrSt);
	}

	public Boolean getItIcmsCobrSt() {
		return getBoolean("itIcmsCobrSt");
	}

	public void setIcmsOutVlrSt(Boolean icmsOutVlrSt) {
		setBoolean("icmsOutVlrSt", icmsOutVlrSt);
	}

	public Boolean getIcmsOutVlrSt() {
		return getBoolean("icmsOutVlrSt");
	}

	public void setGerarCreditoSt(Boolean gerarCreditoSt) {
		setBoolean("gerarCreditoSt", gerarCreditoSt);

	}

	public Boolean getGerarCreditoSt() {
		return getBoolean("gerarCreditoSt");
	}

	public Boolean getDiminuiStFrete() {
		return getBoolean("diminuiStFrete");
	}

	public void setDiminuiStFrete(Boolean diminuiStFrete) {
		setBoolean("diminuiStFrete", diminuiStFrete);
	}

	public Boolean getConsumidorFinal() {
		return getBoolean("consumidorFinal");
	}

	public void setConsumidorFinal(Boolean consumidorFinal) {
		setBoolean("consumidorFinal", consumidorFinal);
	}

	public Boolean getItIcmsSuspenso() {
		return getBoolean("itIcmsSuspenso");
	}

	public void setItIcmsSuspenso(Boolean itIcmsSuspenso) {
		setBoolean("itIcmsSuspenso", itIcmsSuspenso);
	}

	public Boolean getNaoTributada() {
		return getBoolean("naoTributada");
	}

	public void setNaoTributada(Boolean naoTributada) {
		setBoolean("naoTributada", naoTributada);
	}

	public Boolean getContSubsAntecipada() {
		return getBoolean("contSubsAntecipada");
	}

	public void setContSubsAntecipada(Boolean contSubsAntecipada) {
		setBoolean("contSubsAntecipada", contSubsAntecipada);
	}

	public Boolean getIcmsSubsTribAntecipada() {
		return getBoolean("icmsSubsTribAntecipada");
	}

	public void setIcmsSubsTribAntecipada(Boolean icmsSubsTribAntecipada) {
		setBoolean("icmsSubsTribAntecipada", icmsSubsTribAntecipada);
	}

	public Boolean getCedSubsTribAntecipada() {
		return getBoolean("cedSubsTribAntecipada");
	}

	public void setCedSubsTribAntecipada(Boolean cedSubsTribAntecipada) {
		setBoolean("cedSubsTribAntecipada", cedSubsTribAntecipada);
	}

	public Boolean getIcmsStRepassar() {
		return getBoolean("icmsStRepassar");
	}

	public void setIcmsStRepassar(Boolean icmsStRepassar) {
		setBoolean("icmsStRepassar", icmsStRepassar);
	}

	public Boolean getIcmsStComplementar() {
		return getBoolean("icmsStComplementar");
	}

	public void setIcmsStComplementar(Boolean icmsStComplementar) {
		setBoolean("icmsStComplementar", icmsStComplementar);
	}

}
