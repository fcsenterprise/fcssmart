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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fabriciocs.erp.model.entity.Credencial;
import br.com.fabriciocs.erp.model.entity.Menu;
import br.com.fabriciocs.erp.model.entity.Permissao;
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

	@PreAuthorize("hasAnyRole('MENU_READ','ADMIN') and hasPermission(#this, 'ADMIN')")
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
		List<Menu> children = menu.getAll(Menu.class);
		if (children != null && !children.isEmpty()) {
			for (Menu child : children) {
				if (canAdd(child, credId)) {
					return true;
				}
			}
			return false;
		} else {
			Credencial cred = Credencial.findById(credId);
			String url = menu.getUrl();
			return ((url != null && !url.isEmpty()) && (Permissao.findFirst(
					"menu = ? and credencial = ?", menu.getId(), credId) != null) || cred.getAdmin()) ;
		}
	}
	@PreAuthorize("hasAnyRole('MENU_READ','ADMIN') and hasPermission(#this, 'ADMIN')")
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

}
