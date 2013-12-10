package br.com.fabriciocs.erp.view.controller;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Configuracao;

@Controller
@RequestMapping("/config")
public class ConfigCtrl {
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody Collection<Configuracao> getConfiguracoes() {
		return Configuracao.findAll();
	}
}
