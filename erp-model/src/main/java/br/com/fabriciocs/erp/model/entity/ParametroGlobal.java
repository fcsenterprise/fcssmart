package br.com.fabriciocs.erp.model.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Repository
@Table("ParametrosGlobais")
@BelongsTo(parent = Empresa.class, foreignKeyName = "empresa")
public class ParametroGlobal extends Model {

	public String getName() {
		return getString("name");
	}

	public void setName(String name) {
		setString("name", name);
	}

	public String getValue() {
		return getString("value");
	}

	public void setValue(String value) {
		setString("value", value);
	}

	public Empresa getEmpresa() {
		return parent(Empresa.class);
	}

	public void setEmpresa(Empresa empresa) {
		setParent(empresa);
	}

	@JsonIgnore
	public void saveFromMap(Map<String, String> config, Empresa empresa) {

	}

	@JsonIgnore
	public static Map<String, Object> getAsMap(List<ParametroGlobal> pg) {
		Map<String, Object> map = new LinkedHashMap<String, Object>(0);
		for (ParametroGlobal parametroGlobal : pg) {
			map.put(parametroGlobal.getName(), parametroGlobal.getValue());
		}
		return map;
	}
}
