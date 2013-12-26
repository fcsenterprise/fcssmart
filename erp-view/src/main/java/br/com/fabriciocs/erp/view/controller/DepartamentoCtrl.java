package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fabriciocs.erp.model.entity.Departamento;

@RequestMapping("departamento")
@Controller
public class DepartamentoCtrl extends GenericCtrl<Departamento> {

	public DepartamentoCtrl() {
		super(Departamento.class);
	}

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "cast(id as char) like ? or nome like ? or descricao like ?";
		Object[] params = new Object[] { search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}
}
