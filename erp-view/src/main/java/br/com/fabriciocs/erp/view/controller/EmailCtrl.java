package br.com.fabriciocs.erp.view.controller;

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
			pg.setId(pair.getKey());
			pg.setValue(pair.getValue());
			pg.setEmpresa(empresa);
			pg.saveIt();
			pg.reset();
		}
		return "foi!";
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
