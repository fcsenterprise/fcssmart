package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fabriciocs.erp.model.entity.Usuario;

@RequestMapping("usuario")
@Controller
public class UsuarioCtrl extends genericCtrl<Usuario> {

	public UsuarioCtrl() {
		super(Usuario.class);
	}

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "cast(id as char) like ? or nome like ? or cpf like ? or cast(dataNascimento as char) like ?";
		Object[] params = new Object[] { search, search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}
}
