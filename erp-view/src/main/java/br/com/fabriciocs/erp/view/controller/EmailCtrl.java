package br.com.fabriciocs.erp.view.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/email")
public class EmailCtrl {
	private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@RequestMapping(consumes = "application/json", method = { RequestMethod.POST })
	public @ResponseBody
	String saveConfig(@RequestBody String config) {
		log.debug(config);
		return "veio";
	}
}
