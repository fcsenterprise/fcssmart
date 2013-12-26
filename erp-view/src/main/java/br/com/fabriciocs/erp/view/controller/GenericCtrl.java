package br.com.fabriciocs.erp.view.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map.Entry;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.Paginator;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ch.qos.logback.classic.Logger;

public abstract class GenericCtrl<T extends Model> {

	private Class<T> clazz;

	public GenericCtrl(Class<T> clazz) {
		this.clazz = clazz;
	}

	protected abstract Entry<String, Object[]> getParams(String search);

	@PreAuthorize("hasPermission(#this,'READ,ADMIN')")
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	PaginationDataTableResult<T> getDataTablePage(
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
		Paginator paginator = new Paginator(clazz, displayLength,
				params.getKey(), params.getValue());
		if (sortCols > 0) {
			paginator.orderBy(1 + sortCol + " " + sortDir);
		}
		List<T> data = paginator.getPage((displayStart / displayLength) + 1);

		PaginationDataTableResult<T> pagination = new PaginationDataTableResult<T>(
				paginator.getCount(), paginator.getCount(), echo, data);
		return pagination;

	}

	@PreAuthorize("hasPermission(#entity,'READ,ADMIN')")
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	T getById(@PathVariable("id") Long id) {
		Method method;
		try {
			method = clazz.getMethod("findById", Object.class);
			return (T) method.invoke(null, id);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@PreAuthorize("hasPermission(#entity,'DELETE,ADMIN')")
	@RequestMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.DELETE })
	public @ResponseBody
	String remove(@PathVariable("id") Long id) {
		T instance = getById(id);

		if (instance != null) {
			instance.delete();
			return "Success";
		}
		throw new RuntimeException("Objeto não encontrado!");
	}

	@PreAuthorize("hasPermission(#entity,'CREATE, UPDATE, ADMIN')")
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.POST })
	public @ResponseBody
	T salvar(@RequestBody T instance) {
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
