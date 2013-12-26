package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Credencial;
import br.com.fabriciocs.erp.model.entity.Empresa;
import br.com.fabriciocs.erp.model.entity.Usuario;
import br.com.fabriciocs.infra.email.service.EmailService;

@RequestMapping("/usuario")
@Controller
public class UsuarioCtrl extends GenericCtrl<Usuario> {

	private static final int PASSWORD_SIZE = 8;

	@Autowired
	private EmailService emailService;

	public UsuarioCtrl() {
		super(Usuario.class);
	}

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "cast(id as char) like ? or nome like ? or cpf like ? ";
		Object[] params = new Object[] { search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}

	@PreAuthorize("hasAnyRole('USUARIO_CREATE','USUARIO_UPDATE','ADMIN') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	Usuario salvar(@RequestBody Usuario instance) {
		log.info("#######################################");
		log.info(instance.getCredencial().toString());
		log.info("#######################################");
		if (instance.getId() == null) {
			instance = novaSenha(instance);
		}

		instance.saveIt();
		return instance;

	}

	@PreAuthorize("hasAnyRole('USUARIO_UPDATE','ADMIN') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/novaSenha", consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	Usuario novaSenha(@RequestBody Usuario instance) {
		String senha = generateRandomString();
		log.info(senha);
		Credencial credencial = instance.getCredencial();
		String novaSenha = new MessageDigestPasswordEncoder("sha-256")
				.encodePassword(senha, null);
		log.info(senha);
		credencial.setSenha(novaSenha);
		emailService.sendEmail("Nova Senha", "Sennha : " + senha, null,
				new String[] { credencial.getEmail() });
		credencial.saveIt();
		return instance;
	}

	@PreAuthorize("hasAnyRole('EMPRESA_READ', 'ADMIN') or hasPermission(#this, 'EMPRESA_RED,ADMIN')")
	@RequestMapping(value = "/selecionarEmpresa", consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	String selecionarEmpresa(@RequestBody Empresa empresa) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Credencial cred = (Credencial) auth.getPrincipal();
		cred.setEmpresa(empresa);
		return "Empresa Selecionada!";
	}

	@PreAuthorize("hasAnyRole('USUARIO_UPDATE','ADMIN') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/alterarSenha", consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	String alterarSenha(@RequestBody AlterarSenhaHelper helper) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		Credencial credencial = Credencial.findFirst("login = ?", name);
		if (helper.getNewPass().equals(helper.getConfirmNew())) {
			MessageDigestPasswordEncoder encoder = new MessageDigestPasswordEncoder(
					"sha-256");
			if (encoder.isPasswordValid(credencial.getPassword(),
					helper.getCurrent(), null)) {
				credencial.setSenha(encoder.encodePassword(helper.getNewPass(),
						null));
				emailService.sendEmail("Senha Alterada",
						"Sennha : " + helper.getNewPass(), null,
						new String[] { credencial.getEmail() });
				credencial.saveIt();
			} else {
				throw new RuntimeException("Senha Atual incorreta!!!");
			}
		} else {
			throw new RuntimeException("Senhas São diferentes!!!");
		}
		return "Alterado com sucesso";
	}

	private String generateRandomString() {
		String text = "ABCDEFGHIJKLMNOPQRSTUVXZabcdefghijklmnopqrstuvxz1234567890";
		String senha = "";
		for (int i = 0; i < PASSWORD_SIZE; i++) {
			int pos = new Random().nextInt(text.length());
			senha += text.toCharArray()[pos];
		}
		return senha;
	}
}
