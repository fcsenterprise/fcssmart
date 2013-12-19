package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@RequestMapping("menu")
@Controller
public class MenuCtrl extends genericCtrl<Menu> {

	public MenuCtrl() {
		super(Menu.class);
	}

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "cast(id as char) like ? or nome like ? or url like ?";
		Object[] params = new Object[] { search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}

	@RequestMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	List<HierarquicalMenu> getTopLevel() {
		List<Menu> menus = Menu.where("menuPai is null").orderBy("id asc");
		List<HierarquicalMenu> finalValue = new ArrayList<HierarquicalMenu>();

		for (Menu menu : menus) {
			finalValue.add(new HierarquicalMenu(menu, menu.getAll(Menu.class)));
		}
		return finalValue;
	}

	@RequestMapping(value = "/all/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	List<Menu> getChildren(@PathVariable("id") Long id) {
		Menu menu = Menu.findById(id);
		return menu.get(Menu.class, "true").orderBy("id asc");
	}
}

class HierarquicalMenu {
	Menu object;
	List<HierarquicalMenu> children;

	public HierarquicalMenu(Menu object, List<Menu> children) {
		convert(object, children);
	}

	@JsonInclude(Include.NON_EMPTY)
	public Menu getObject() {
		return object;
	}

	public void setObject(Menu object) {
		this.object = object;
	}

	@JsonInclude(Include.NON_EMPTY)
	public List<HierarquicalMenu> getChildren() {
		return children;
	}

	public void setChildren(List<HierarquicalMenu> children) {
		this.children = children;
	}

	public HierarquicalMenu convert(Menu object, List<Menu> children) {
		this.object = object;
		if (children == null) {
			return this;
		}
		if (this.children == null) {
			this.children = new ArrayList<HierarquicalMenu>();
		}
		for (Menu t : children) {
			this.children.add(new HierarquicalMenu(t, t.getAll(Menu.class)));
		}
		return this;
	}

}
