package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.Map.Entry;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Empresa;

@RequestMapping("empresa")
@Controller
public class EmpresaCtrl extends GenericCtrl<Empresa> {

	public EmpresaCtrl() {
		super(Empresa.class);
	}

	// private Logger log = (Logger) LoggerFactory.getLogger(getClass());

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "cast(id as char) like ? or cnpj like ? or razaoSocial like ? or nomeFantasia like ? or inscricaoEstadual like ?";
		Object[] params = new Object[] { search, search, search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}

	@PreAuthorize("hasPermission(#this,'READ,ADMIN,EMPRESA')")
	@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	PaginationDataTableResult<Empresa> getDataTablePage(
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
		return super.getDataTablePage(displayStart, displayLength, columns,
				search, regex, sortCol, sortCols, sortDir, echo);

	}

}
