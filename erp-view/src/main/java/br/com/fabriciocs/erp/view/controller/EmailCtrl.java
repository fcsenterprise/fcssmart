package br.com.fabriciocs.erp.view.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.fabriciocs.infra.email.service.EmailService;

@Controller
@RequestMapping("/email")
public class EmailCtrl {
	@Autowired
	private EmailService emailService;

	@RequestMapping(consumes = "application/json", method = { RequestMethod.POST })
	public void saveConfig(@RequestBody Email email) {
		emailService.preparar(email.getConfig()).sendEmail("email de testet",
				"Parece que essa merda funciona", null,
				"fabriciodacunhasantos@gmail.com",
				new String[] { "fabricio.csantos@pcinformatica.com.br" });
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
