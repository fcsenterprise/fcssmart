package br.com.fabriciocs.erp.model.entity;

import java.util.List;
import java.util.Map;

public class Email {
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

	public Email load() {
		List<ParametroGlobal> param = ParametroGlobal.findAll();
		setConfig(ParametroGlobal.getAsMap(param));
		return this;
	}
}