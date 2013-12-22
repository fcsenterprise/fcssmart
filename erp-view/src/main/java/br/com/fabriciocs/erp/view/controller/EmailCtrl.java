package br.com.fabriciocs.erp.view.controller;

import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/email")
public class EmailCtrl {
	private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@SuppressWarnings("unchecked")
	@RequestMapping(consumes = "application/json", method = { RequestMethod.POST })
	public @ResponseBody
	String saveConfig(@RequestBody String config) {
		Map<String, Object> map = new ObjectMapper().convertValue(config, Map.class);
		
		log.info(map.toString());
		return "veio";
	}
}
