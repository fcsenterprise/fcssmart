package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.javalite.activejdbc.LazyList;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fabriciocs.erp.model.entity.Menu;

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
	List<Hierarquical<Menu>> getTopLevel() {
		List<Menu> menus = Menu.where("menuPai is null").orderBy("id asc");
		return populateMenus(menus);

	}

	private List<Hierarquical<Menu>> populateMenus(List<Menu> menus) {
		List<Hierarquical<Menu>> list = new ArrayList<Hierarquical<Menu>>();
		for (Menu menu : menus) {
			LazyList<Menu> subMenus = menu.getAll(Menu.class);
			list.add(new Hierarquical<Menu>(menu, subMenus));
			populateMenus(subMenus);
		}
		return list;
	}

	@RequestMapping(value = "/all/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	List<Menu> getChildren(@PathVariable("id") Long id) {
		Menu menu = Menu.findById(id);
		return menu.get(Menu.class, "true").orderBy("id asc");
	}
}

class Hierarquical<T> {
	T object;
	List<Hierarquical<T>> children;

	public Hierarquical(T object, List<T> children) {
		super();
		convert(object, children);
	}

	@JsonInclude(Include.NON_EMPTY)
	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	@JsonInclude(Include.NON_EMPTY)
	public List<Hierarquical<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Hierarquical<T>> children) {
		this.children = children;
	}

	public void convert(T object, List<T> children) {
		this.object = object;
		if (children == null) {
			return;
		}
		if (this.children == null) {
			this.children = new ArrayList<Hierarquical<T>>();
		}
		for (T t : children) {
			this.children.add(new Hierarquical<T>(t, null));
		}
	}

}
