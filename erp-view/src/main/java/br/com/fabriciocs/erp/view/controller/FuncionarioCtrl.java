package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fabriciocs.erp.model.entity.Funcionario;

@RequestMapping("funcionario")
@Controller
public class FuncionarioCtrl extends genericCtrl<Funcionario> {

	public FuncionarioCtrl() {
		super(Funcionario.class);
	}

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "id::text like ? or nome like ? or cpf like ? or dataNascimento::text like ?";
		Object[] params = new Object[] { search, search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}
}
