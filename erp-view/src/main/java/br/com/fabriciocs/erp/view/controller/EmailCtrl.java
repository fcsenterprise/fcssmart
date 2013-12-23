package br.com.fabriciocs.erp.view.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Empresa;
import br.com.fabriciocs.erp.model.entity.ParametroGlobal;
import br.com.fabriciocs.infra.email.service.EmailService;
import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/email")
public class EmailCtrl {
	@Autowired
	private EmailService emailService;

	private Logger log = (Logger) LoggerFactory.getLogger(getClass());
	@Autowired
	private ParametroGlobal pg;

	@RequestMapping(consumes = "application/json", method = { RequestMethod.POST })
	public @ResponseBody
	String saveConfig(@RequestBody Email email) {
		log.info(email.toString());
		Empresa empresa = Empresa.findFirst(" true ");
		for (Map.Entry<String, String> pair : email.config.entrySet()) {
			ParametroGlobal.create("name", pair.getKey(), "value", pair.getValue(), "empresa", empresa.getId()).saveIt();
		}
		empresa.saveIt();
		return "foi!";
	}
	
	@RequestMapping(value="/load",consumes = "application/json", method = { RequestMethod.GET })
	public @ResponseBody
	Email loadConfig() {
		Email email = new Email();
		List<ParametroGlobal> param   = ParametroGlobal.findAll();
		email.setConfig(ParametroGlobal.getAsMap(param));
		return email;
	}
	

	public static class Email {
		Map<String, String> config;

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
