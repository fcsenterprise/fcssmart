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

	@RequestMapping(consumes = "application/json", method = { RequestMethod.POST })
	public @ResponseBody
	String saveConfig(@RequestBody Email email) {
		log.info(email.toString());
		Empresa empresa = Empresa.findFirst(" true ");
		for (Map.Entry<String, Object> pair : email.config.entrySet()) {
			ParametroGlobal.create("name", pair.getKey(), "value",
					pair.getValue(), "empresa", empresa.getId()).saveIt();
		}
		empresa.saveIt();
		return "foi!";
	}

	@RequestMapping(value = "/load", method = { RequestMethod.GET }, produces = { "application/json" })
	public @ResponseBody
	String loadConfig() throws JsonProcessingException {
		Email email = new Email();
		List<ParametroGlobal> param = ParametroGlobal.findAll();
		email.setConfig(ParametroGlobal.getAsMap(param));
		return new ObjectMapper().writeValueAsString(param);
	}

	public static class Email {
		private Map<String, Object> config;

		public Map<String, Object> getConfig() {
			return config;
		}

		public void setConfig(Map<String, Object> config) {
			this.config = config;
		}

		@Override
		public String toString() {
			return "Email [config=" + config + "]";
		}

	}
}
