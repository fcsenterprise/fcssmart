package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("Ipi")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Ipi extends Model {
	public void seTipoBase(TipoBase tipoBase) {
		setParent(tipoBase);
	}

	public TipoBase getTipoBase() {
		return parent(TipoBase.class);
	}

	public void setIncluiFreteBs(Boolean incluFreteBs) {
		setBoolean("incluiFreteBs", incluFreteBs);
	}

	public Boolean getIncluiFreteBs() {
		return getBoolean("incluiFreteBs");
	}

	public void setCodVinculacao(CodVinculacao codVinculacao) {
		setParent(codVinculacao);
	}

	public CodVinculacao getCodVinculacao() {
		return parent(CodVinculacao.class);
	}

	public void setCodTributacao(CodigoTributacao codTributacao) {
		setParent(codTributacao);
	}

	public CodigoTributacao getCodTributacao() {
		return parent(CodigoTributacao.class);
	}

	public void setPercReducao(Double percReducao) {
		setDouble("percReducao", percReducao);
	}

	public Double getPercReducao() {
		return getDouble("percReducao");
	}

	public void setImpOutDanfe(Boolean impOutDanfe) {
		setBoolean("impOutDanfe", impOutDanfe);
	}

	public Boolean getImpOutDanfe() {
		return getBoolean("impOutDanfe");
	}

	public void setIncBaseIcms(Boolean incBaseIcms) {
		setBoolean("incBaseIcms", incBaseIcms);
	}

	public Boolean getIncBaseIcms() {
		return getBoolean("incBaseIcms");
	}

	public void setIncIcmsOut(Boolean incIcmsOut) {
		setBoolean("incIcmsOut", incIcmsOut);
	}

	public Boolean getIncIcmsOut() {
		return getBoolean("incIcmsOut");
	}

	public void setIncOutTot(Boolean incOutTot) {
		setBoolean("incOutTot", incOutTot);
	}

	public Boolean getIncOutTot() {
		return getBoolean("incOutTot");
	}

	public void setIncOutBsSubs(Boolean incOutBsSubs) {
		setBoolean("incOutBsSubs", incOutBsSubs);
	}

	public Boolean getIncOutBsSubs() {
		return getBoolean("incOutBsSubs");
	}

	public void setEscrituracaoFrete(Boolean escrituracaoFrete) {
		setBoolean("escrituracaoFrete", escrituracaoFrete);
	}

	public Boolean getEscrituracaoFrete() {
		return getBoolean("escrituracaoFrete");
	}

	public void setEstorna(Boolean estorna) {
		setBoolean("estorna", estorna);
	}

	public Boolean getEstorna() {
		return getBoolean("estorna");
	}

	public void setImune(Boolean imune) {
		setBoolean("imune", imune);
	}

	public Boolean getImune() {
		return getBoolean("imune");
	}

	public void setNaoTrib(Boolean naoTrib) {
		setBoolean("naoTrib", naoTrib);
	}

	public Boolean getNaoTrib() {
		return getBoolean("naoTrib");
	}

}
