package br.com.fabriciocs.erp.view.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Credencial;

@Controller
@RequestMapping(value = "/credencial")
public class CredencialCtrl {

	@Autowired
	private DataSource dataSource;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/{login}/{email}", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
	public @ResponseBody
	Credencial searchByLoginAndEmail(@PathVariable("login") String login,
			@PathVariable("email") String email) {
		return Credencial.findFirst("login = ? and email = ?", login, email);
	}

	@RequestMapping(method = { RequestMethod.POST })
	public @ResponseBody
	Credencial save(@RequestBody Credencial credencial) {
		credencial.saveIt();
		return credencial;
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public @ResponseBody
	Credencial delete(@PathVariable("id") Long id) {
		Credencial credencial = Credencial.findById(id);
		credencial.delete();
		return credencial;
	}
}
