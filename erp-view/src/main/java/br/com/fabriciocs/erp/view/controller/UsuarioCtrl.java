package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Credencial;
import br.com.fabriciocs.erp.model.entity.Usuario;
import br.com.fabriciocs.infra.email.service.EmailService;

@RequestMapping("usuario")
@Controller
public class UsuarioCtrl extends genericCtrl<Usuario> {

	@Autowired
	private EmailService emailService;

	public UsuarioCtrl() {
		super(Usuario.class);
	}

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "cast(id as char) like ? or nome like ? or cpf like ? or cast(dataExpiracao as char) like ?";
		Object[] params = new Object[] { search, search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	Usuario salvar(@RequestBody Usuario instance) {
		Credencial credencial = instance.getCredencial();
		String senha = DigestUtils.md5DigestAsHex(credencial.getSenha().getBytes());
		credencial.setSenha(senha);
		credencial.saveIt();
		instance.saveIt();
		emailService.sendEmail("Usuário Cadastrado", "Sennha : "+senha,null,new String[]{credencial.getEmail()});
		return instance;
	}
}
