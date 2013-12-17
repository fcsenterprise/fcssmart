package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fabriciocs.erp.model.entity.Empresa;

@RequestMapping("empresa")
@Controller
public class EmpresaCtrl extends genericCtrl<Empresa> {

	public EmpresaCtrl() {
		super(Empresa.class);
	}

	// private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "id::text like ? or cnpjCei like ? or razaoSocial like ? or nomeFantasia like ? or inscricaoEstadual like ?";
		Object[] params = new Object[] { search, search, search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}
}
