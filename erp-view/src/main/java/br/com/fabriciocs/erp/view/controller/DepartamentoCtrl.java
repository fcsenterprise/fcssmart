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

import br.com.fabriciocs.erp.model.entity.Departamento;
import ch.qos.logback.classic.Logger;

@RequestMapping("departamento")
@Controller
public class DepartamentoCtrl {

	private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@RequestMapping(value = "/{pageSize}/{pageNumber}/{orderFields}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	PaginationResult<Departamento> getPage(
			@PathVariable("pageSize") int pageSize,
			@PathVariable("pageNumber") int pageNumber,
			@PathVariable("orderFields") String orderFields) {
		Paginator paginator = new Paginator(Departamento.class, pageSize, "*")
				.orderBy(orderFields);
		List<Departamento> departamentos = paginator.getPage(pageNumber);
		PaginationResult<Departamento> empresaPagination = new PaginationResult<Departamento>(
				paginator.getCount(), departamentos, paginator.pageCount());
		log.info(empresaPagination.toString());
		return empresaPagination;

	}

	@RequestMapping(value = "/{nome}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	Departamento getDepartamento(@PathVariable("nome") String nome) {
		log.info("buscando por Nome: " + nome);
		return Departamento.findFirst("nome = ?", nome);
	}

	@RequestMapping(value = "/{nome}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.DELETE })
	public @ResponseBody
	Errors delDepartamento(@PathVariable("nome") String nome) {
		Departamento departamento = Departamento.findFirst("nome = ?", nome);

		if (departamento != null) {
			try {
				departamento.delete();
			} catch (Exception e) {
				log.error("Erro ao excluir", e);
			}
			return departamento.errors();
		}
		return new Errors();

	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	Errors salvar(@RequestBody Departamento departamento) {
		try {
			departamento.save();
		} catch (Exception e) {
			log.error("Erro ao salvar", e);
		}
		return departamento.errors();
	}
}
