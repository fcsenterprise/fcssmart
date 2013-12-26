package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;

import org.javalite.activejdbc.Paginator;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.fabriciocs.erp.model.entity.Credencial;
import br.com.fabriciocs.erp.model.entity.Permissao;
import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/permissao")
public class PermissaoCtrl {

	public PermissaoCtrl() {
	}

	protected Entry<String, Object[]> getParams(String search) {
		String query = " (cast(id as char) like ? or cast(editar as char) like ? or cast(ler as char) like ? or cast(criar as char) like ? or cast(remover as char) like ?) ";
		Object[] params = new Object[] { search, search, search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}
	@PreAuthorize("hasAnyRole('USUARIO_READ') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/table/{credencialId}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	PaginationDataTableResult<Permissao> getDataTablePage(
			@PathVariable("credencialId") Long id,
			@RequestParam("iDisplayStart") Integer displayStart,
			@RequestParam("iDisplayLength") Integer displayLength,
			@RequestParam("iColumns") Integer columns,
			@RequestParam("sSearch") String search,
			@RequestParam("bRegex") boolean regex,
			@RequestParam(value = "iSortCol_0", required = false) Integer sortCol,
			@RequestParam(value = "iSortingCols", required = false) Integer sortCols,
			@RequestParam(value = "sSortDir_0", required = false) String sortDir,
			@RequestParam("sEcho") String echo) {
		search = '%' + search + '%';
		Entry<String, Object[]> params = getParams(search);
		List<Object> asList = new ArrayList<Object>(Arrays.asList(params.getValue()));
		asList.add(id);
		
		Paginator paginator = new Paginator(Permissao.class, displayLength,
				params.getKey() + " and credencial = ? ", asList.toArray());
		if (sortCols > 0) {
			paginator.orderBy(1 + sortCol + " " + sortDir);
		}
		List<Permissao> data = paginator
				.getPage((displayStart / displayLength) + 1);

		PaginationDataTableResult<Permissao> pagination = new PaginationDataTableResult<Permissao>(
				paginator.getCount(), paginator.getCount(), echo, data);
		return pagination;

	}
	@PreAuthorize("hasAnyRole('USUARIO_READ') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/table/",produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	PaginationDataTableResult<Permissao> getDataTablePage(
			@RequestParam("iDisplayStart") Integer displayStart,
			@RequestParam("iDisplayLength") Integer displayLength,
			@RequestParam("iColumns") Integer columns,
			@RequestParam("sSearch") String search,
			@RequestParam("bRegex") boolean regex,
			@RequestParam(value = "iSortCol_0", required = false) Integer sortCol,
			@RequestParam(value = "iSortingCols", required = false) Integer sortCols,
			@RequestParam(value = "sSortDir_0", required = false) String sortDir,
			@RequestParam("sEcho") String echo) {
		search = '%' + search + '%';
		Entry<String, Object[]> params = getParams(search);
		Paginator paginator = new Paginator(Permissao.class, displayLength,
				params.getKey(), params.getValue());
		if (sortCols > 0) {
			paginator.orderBy(1 + sortCol + " " + sortDir);
		}
		List<Permissao> data = paginator
				.getPage((displayStart / displayLength) + 1);

		PaginationDataTableResult<Permissao> pagination = new PaginationDataTableResult<Permissao>(
				paginator.getCount(), paginator.getCount(), echo, data);
		return pagination;

	}
	@PreAuthorize("hasAnyRole('USUARIO_READ') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	Permissao getById(@PathVariable("id") Long id) {
		return Permissao.findById(id);
	}
	@PreAuthorize("hasAnyRole('USUARIO_DELETE') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.DELETE })
	public @ResponseBody
	String remove(@PathVariable("id") Long id) {
		Permissao instance = getById(id);

		if (instance != null) {
			instance.delete();
			return "Success";
		}
		throw new RuntimeException("Objeto não encontrado!");
	}
	@PreAuthorize("hasAnyRole('USUARIO_CREATE','USUARIO_UPDATE') and hasPermission(#this, 'ADMIN')")
	@RequestMapping(value = "/{credencialId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	Permissao salvar(@PathVariable("credencialId") Long id,
			@RequestBody Permissao instance) {
		instance.setCredencial(Credencial.<Credencial> findById(id));
		instance.save();
		return instance;
	}

	Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String handleException(Exception e) {
		log.error("", e);
		return e.getLocalizedMessage();
	}
}
