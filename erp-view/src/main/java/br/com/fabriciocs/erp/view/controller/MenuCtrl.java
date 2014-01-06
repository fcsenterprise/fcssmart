package br.com.fabriciocs.erp.view.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Credencial;
import br.com.fabriciocs.erp.model.entity.Menu;
import br.com.fabriciocs.erp.view.controller.MenuCtrl.AddMenuEval;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@RequestMapping("/menu")
@Controller
public class MenuCtrl extends GenericCtrl<Menu> {

	public MenuCtrl() {
		super(Menu.class);
	}

	@Override
	protected Entry<String, Object[]> getParams(String search) {
		String query = "cast(id as char) like ? or nome like ? or url like ?";
		Object[] params = new Object[] { search, search, search };
		return new AbstractMap.SimpleEntry<String, Object[]>(query, params);
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	List<HierarquicalMenu> getTopLevel() {
		List<Menu> menus = Menu.where("menuPai is null").orderBy("id asc");
		List<HierarquicalMenu> finalValue = new ArrayList<HierarquicalMenu>();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		final Credencial credencial = Credencial.findFirst("login = ?", name);
		for (Menu menu : menus) {
			if (canAdd(menu, credencial.getLongId())) {
				finalValue.add(new HierarquicalMenu(menu, menu
						.getAll(Menu.class), new AddMenuEval() {

					@Override
					public boolean canAdd(Menu menu) {
						return MenuCtrl.this.canAdd(menu,
								credencial.getLongId());
					}
				}));
			}
		}
		return finalValue;
	}

	public boolean canAdd(Menu menu, Long credId) {
		List<Menu> children = Menu
				.findBySQL(
						new StringBuilder("SELECT Menus.*")
								.append("from Menus  ")
								.append("left join Permissoes on Menus.id = Permissoes.menu ")
								.append("left join Credenciais  ")
								.append("on Permissoes.credencial = Credenciais.id ")
								.append("where Menus.menuPai = ?  and ( ")
								.append("( select admin from Credenciais where id = ?) = true ")
								.append("or Credenciais.id = ? "
										+ ") order by Menus.id").toString(),
						menu.getId(), credId, credId);
		if (children != null && !children.isEmpty()) {
			for (Menu child : children) {
				if (canAdd(child, credId)) {
					return true;
				}
			}
			return false;
		} else {
			String url = menu.getUrl();
			return StringUtils.hasText(url);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/all/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = { RequestMethod.GET })
	public @ResponseBody
	List<Menu> getChildren(@PathVariable("id") Long id) {
		Menu menu = Menu.findById(id);
		return menu.get(Menu.class, "true").orderBy("id asc");
	}

	public List<Menu> getMenuPorPermissoes() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		Credencial credencial = Credencial.findFirst("login = ?", name);

		return Menu
				.findBySQL(
						"select Menus.* from Permissoes inner join Menus on Permissoes.menu = Menus.id where credencial = ?",
						credencial.getId());

	}

	public interface AddMenuEval {
		public boolean canAdd(Menu menu);
	}
}

class HierarquicalMenu {
	Menu object;
	List<HierarquicalMenu> children;

	public HierarquicalMenu(Menu object, List<Menu> children,
			AddMenuEval evaluator) {
		convert(object, children, evaluator);
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

	public HierarquicalMenu convert(Menu object, List<Menu> children,
			AddMenuEval evaluator) {
		this.object = object;
		if (children == null) {
			return this;
		}
		if (this.children == null) {
			this.children = new ArrayList<HierarquicalMenu>();
		}

		for (Menu t : children) {
			boolean canAdd = true;
			if (evaluator != null) {
				canAdd = evaluator.canAdd(t);
			}
			if (canAdd) {
				this.children.add(new HierarquicalMenu(t, t.getAll(Menu.class),
						evaluator));
			}
		}
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HierarquicalMenu other = (HierarquicalMenu) obj;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		return true;
	}

}
