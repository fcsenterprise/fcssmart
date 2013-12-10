package br.com.fabriciocs.erp.view.controller;

import java.util.List;

import org.javalite.activejdbc.Errors;
import org.javalite.activejdbc.Paginator;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Empresa;
import ch.qos.logback.classic.Logger;

@RequestMapping("empresa")
@Controller
public class EmpresaCtrl {

	private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/{pageSize}/{pageNumber}/{orderFields}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	PaginationResult<Empresa> getPage(@PathVariable("pageSize") int pageSize,
			@PathVariable("pageNumber") int pageNumber,
			@PathVariable("orderFields") String orderFields) {
		Paginator paginator = new Paginator(Empresa.class, pageSize, "*")
				.orderBy(orderFields);
		List<Empresa> empresas = paginator.getPage(pageNumber);
		PaginationResult<Empresa> empresaPagination = new PaginationResult<Empresa>(
				paginator.getCount(), empresas, paginator.pageCount());
		log.info(empresaPagination.toString());
		return empresaPagination;

	}

	@RequestMapping(value = "/{cnpjCei}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	Empresa getEmpresa(@PathVariable("cnpjCei") String cnpjCei) {
		log.info("buscando por cnpj/cei: " + cnpjCei);
		return Empresa.findFirst("cnpjCei = ?", cnpjCei);
	}

	@RequestMapping(value = "/{cnpjCei}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.DELETE })
	public @ResponseBody
	Errors delEmpresa(@PathVariable("cnpjCei") String cnpjCei) {
		Empresa empresa = Empresa.findFirst("cnpjCei = ?", cnpjCei);

		if (empresa != null) {
			try {
				empresa.delete();
			} catch (Exception e) {
				log.error("Erro ao excluir", e);
			}
			return empresa.errors();
		}
		return new Errors();

	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	Errors salvar(@RequestBody Empresa empresa) {
		try {
			empresa.save();
		} catch (Exception e) {
			log.error("Erro ao salvar", e);
		}
		return empresa.errors();
	}

}
