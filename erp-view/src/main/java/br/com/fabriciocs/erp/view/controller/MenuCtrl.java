package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Menu;

@RequestMapping("menu")
@Controller
public class MenuCtrl extends genericCtrl<Menu> {

	public MenuCtrl() {
		super(Menu.class);
	}

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "id::text like ? or nome like ? or url like ?";
		Object[] params = new Object[] { search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}
	
	@RequestMapping(value="/all",produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	List<Menu> getTopLevel() {
		return Menu.where("menuPai is null").orderBy("id asc");
	}
	
	@RequestMapping(value="/all/{id}",produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	List<Menu> getChildren(@PathVariable("id") Long id) {
		Menu menu = Menu.findById(id);
		return menu.get(Menu.class,"true").orderBy("id asc");
	}
}
