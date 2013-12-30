package br.com.fabriciocs.erp.view.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Credencial;
import br.com.fabriciocs.erp.model.entity.Empresa;
import br.com.fabriciocs.erp.model.entity.ParametroGlobal;
import br.com.fabriciocs.infra.email.service.EmailService;
import ch.qos.logback.classic.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/email")
public class EmailCtrl {
	@Autowired
	private EmailService emailService;

	private Logger log = (Logger) LoggerFactory.getLogger(getClass());
	@Autowired
	private ParametroGlobal pg;

	@PreAuthorize("hasAnyRole('EMAIL_CREATE','ADMIN') or hasPermission(#this, 'ADMIN')")
	@RequestMapping(consumes = "application/json", method = { RequestMethod.POST })
	public @ResponseBody
	String saveConfig(@RequestBody Email email) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Credencial cred = (Credencial) auth.getPrincipal();
		Empresa empresa = cred.getEmpresa();
		for (Map.Entry<String, String> pair : email.config.entrySet()) {
			ParametroGlobal param = ParametroGlobal.findFirst("name = ?",
					pair.getKey());
			if (param == null) {
				param = new ParametroGlobal();
				param.setEmpresa(empresa);
			}
			param.setName(pair.getKey());
			param.setValue(pair.getValue());
			param.saveIt();
		}
		empresa.saveIt();
		return "foi!";
	}

	@PreAuthorize("hasAnyRole('EMAIL_UPDATE','ADMIN') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/load", method = { RequestMethod.GET }, produces = { "application/json" })
	public @ResponseBody
	String loadConfig() throws JsonProcessingException {
		Email email = new Email();
		List<ParametroGlobal> param = ParametroGlobal.findAll();
		email.setConfig(ParametroGlobal.getAsMap(param));
		return new ObjectMapper().writeValueAsString(email);
	}

	public static class Email {
		private Map<String, String> config;

		public Map<String, String> getConfig() {
			return config;
		}

		public void setConfig(Map<String, String> config) {
			this.config = config;
		}

		@Override
		public String toString() {
			return "Email [config=" + config + "]";
		}

	}
}
