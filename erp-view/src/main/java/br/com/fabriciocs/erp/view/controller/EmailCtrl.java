package br.com.fabriciocs.erp.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Credencial;
import br.com.fabriciocs.erp.model.entity.Email;
import br.com.fabriciocs.erp.model.entity.Empresa;
import br.com.fabriciocs.infra.email.service.EmailService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/email")
public class EmailCtrl {
	@Autowired
	private EmailService emailService;

	@PreAuthorize("hasAnyRole('EMAIL_CREATE','ADMIN') or hasPermission(#this, 'ADMIN')")
	@RequestMapping(consumes = "application/json", method = { RequestMethod.POST })
	public @ResponseBody
	String saveConfig(@RequestBody Email email) {
		
		Empresa empresa = Credencial.getCredencialLogged().getEmpresa();
		return empresa.configureEmail(email);
	}

	@PreAuthorize("hasAnyRole('EMAIL_UPDATE','ADMIN') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/load", method = { RequestMethod.GET }, produces = { "application/json" })
	public @ResponseBody
	String loadConfig() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(new Email().load());
	}
}
