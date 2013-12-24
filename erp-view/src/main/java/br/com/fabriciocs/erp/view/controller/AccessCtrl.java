package br.com.fabriciocs.erp.view.controller;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/access")
public class AccessCtrl {

	private Logger log =  (Logger) LoggerFactory.getLogger(getClass());
	@RequestMapping("/denied")
	public @ResponseBody String denied(HttpRequest request) {
		log.info(request.toString());
		return "Acesso Negado para: " + request.getURI();
	}
	@RequestMapping("/failure")
	@ResponseStatus(HttpStatus.BAD_REQUEST)	
	public @ResponseBody String failure(HttpRequest request) {
		log.info(request.toString());
		return "Erro de login: " + request.getURI();
	}
}
