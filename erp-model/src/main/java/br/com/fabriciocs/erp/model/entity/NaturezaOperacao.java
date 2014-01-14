package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
@Table("NaturezasOperacoes")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class NaturezaOperacao extends Model {
	public Boolean getAtiva() {
		return getBoolean("ativa");
	}

	public void setAtiva(Boolean ativa) {
		setBoolean("ativa", ativa);
	}

	public Boolean getRetemInssNaFonte() {
		return getBoolean("retemInssNaFonte");
	}

	public void setRetemInssNaFonte(Boolean retemInssNaFonte) {
		setBoolean("retemInssNaFonte", retemInssNaFonte);
	}

	public Boolean getSuspensaoIi() {
		return getBoolean("suspensaoIi");
	}

	public void setSuspensaoIi(Boolean suspensaoIi) {
		setBoolean("suspensaoIi", suspensaoIi);
	}

	public Boolean getIncIpiBase() {
		return getBoolean("incIpiBase");
	}

	public void setIncIpiBase(Boolean incIpiBase) {
		setBoolean("incIpiBase", incIpiBase);
	}

	public Boolean getIncIpiOutBase() {
		return getBoolean("incIpiOutBase");
	}

	public void setIncIpiOutBase(Boolean incIpiOutBase) {
		setBoolean("incIpiOutBase", incIpiOutBase);
	}

	public Boolean getIncIpiBaseRet() {
		return getBoolean("incIpiBaseRet");
	}

	public void setIncIpiBaseRet(Boolean incIpiBaseRet) {
		setBoolean("incIpiBaseRet", incIpiBaseRet);
	}

	public Boolean getIncIpiOutBaseRet() {
		return getBoolean("incIpiOutBaseRet");
	}

	public void setIncIpiOutBaseRet(Boolean incIpiOutBaseRet) {
		setBoolean("incIpiOutBaseRet", incIpiOutBaseRet);
	}

	public Boolean getDeduzDescZfm() {
		return getBoolean("deduzDescZfm");
	}

	public void setDeduzDescZfm(Boolean deduzDescZfm) {
		setBoolean("deduzDescZfm", deduzDescZfm);
	}

	public Boolean getIncIcmsStBaseRet() {
		return getBoolean("incIcmsStBaseRet");
	}

	public void setIncIcmsStBaseRet(Boolean incIcmsStBaseRet) {
		setBoolean("incIcmsStBaseRet", incIcmsStBaseRet);
	}

	public Boolean getIncIcmsStBaseIrRet() {
		return getBoolean("incIcmsStBaseIrRet");
	}

	public void setIncIcmsStBaseIrRet(Boolean incIcmsStBaseIrRet) {
		setBoolean("incIcmsStBaseIrRet", incIcmsStBaseIrRet);
	}

	public Boolean getConsidIcmsNfeEntFat() {
		return getBoolean("considIcmsNfeEntFat");
	}

	public void setConsidIcmsNfeEntFat(Boolean considIcmsNfeEntFat) {
		setBoolean("considIcmsNfeEntFat", considIcmsNfeEntFat);
	}

	public Boolean getIcmsIncideBaseIcms() {
		return getBoolean("icmsIncideBaseIcms");
	}

	public void setIcmsIncideBaseIcms(Boolean icmsIncideBaseIcms) {
		setBoolean("icmsIncideBaseIcms", icmsIncideBaseIcms);
	}

	public Boolean getIcmsIncideTnf() {
		return getBoolean("icmsIncideTnf");
	}

	public void setIcmsIncideTnf(Boolean icmsIncideTnf) {
		setBoolean("icmsIncideTnf", icmsIncideTnf);
	}

	public Boolean getConsidIcmsNfeEntRec() {
		return getBoolean("considIcmsNfeEntRec");
	}

	public void setConsidIcmsNfeEntRec(Boolean considIcmsNfeEntRec) {
		setBoolean("considIcmsNfeEntRec", considIcmsNfeEntRec);
	}

	public Boolean getConsidPisNfeEntFat() {
		return getBoolean("considPisNfeEntFat");
	}

	public void setConsidPisNfeEntFat(Boolean considPisNfeEntFat) {
		setBoolean("considPisNfeEntFat", considPisNfeEntFat);
	}

	public Boolean getConsidCofinsNfeEntFat() {
		return getBoolean("considCofinsNfeEntFat");
	}

	public void setConsidCofinsNfeEntFat(Boolean considCofinsNfeEntFat) {
		setBoolean("considCofinsNfeEntFat", considCofinsNfeEntFat);
	}

	public void setTipo(TipoBase tipo) {
		setParent(tipo);
	}

	public TipoBase getTipo() {
		return parent(TipoBase.class);
	}

	public void setCfop(Cfop cfop) {
		setParent(cfop);
	}

	public Cfop getCfop() {
		return parent(Cfop.class);
	}

	public void setMercado(Mercado mercado) {
		setParent(mercado);
	}

	public Mercado getMercado() {
		return parent(Mercado.class);
	}

	public void setCanalVenda(CanalVenda canalVenda) {
		setParent(canalVenda);
	}

	public CanalVenda getCanalVenda() {
		return parent(CanalVenda.class);
	}

	public void setMensagem(Mensagem mensagem) {
		setParent(mensagem);
	}

	public Mensagem getMensagem() {
		return parent(Mensagem.class);
	}

	public void setDocumentoFiscal(DocumentoFiscal documentoFiscal) {
		setParent(documentoFiscal);
	}

	public DocumentoFiscal getDocumentoFiscal() {
		return parent(DocumentoFiscal.class);
	}

	public void setAdicional(AdicionalNatureza adicional) {
		setParent(adicional);
	}

	public AdicionalNatureza getAdicional() {
		return parent(AdicionalNatureza.class);
	}

	public void setAtualizacao(Atualizacao atualizacao) {
		setParent(atualizacao);
	}

	public Atualizacao getAtualizacao() {
		return parent(Atualizacao.class);
	}

	public void setNarrativa(String narrativa) {
		setString("narrativa", narrativa);
	}

	public String getNarrativa() {
		return getString("narrativa");
	}

	public void setPercRetencaoCsll(Double percRetencaoCsll) {
		setDouble("percRetencaoCsll", percRetencaoCsll);
	}

	public Double getPercRetencaoCsll() {
		return getDouble("percRetencaoCsll");
	}

	public void setIpi(Ipi ipi) {
		setParent(ipi);
	}

	public Ipi getIpi() {
		return parent(Ipi.class);
	}

	public void setCofins(Cofins cofins) {
		setParent(cofins);
	}

	public Cofins getCofins() {
		return parent(Cofins.class);
	}

	public void setIss(Iss iss) {
		setParent(iss);
	}

	public Iss getIss() {
		return parent(Iss.class);
	}

	public void setPis(Pis pis) {
		setParent(pis);
	}

	public Pis getPis() {
		return parent(Pis.class);
	}

	public void setInss(Inss inss) {
		setParent(inss);
	}

	public Inss getInss() {
		return parent(Inss.class);
	}

	public void setIcms(Icms icms) {
		setParent(icms);
	}

	public Icms getIcms() {
		return parent(Icms.class);
	}

	public void setImpostoImportacao(ImpostoImportacao impostoImportacao) {
		setParent(impostoImportacao);
	}

	public ImpostoImportacao getImpostoImportacao() {
		return parent(ImpostoImportacao.class);
	}

}
