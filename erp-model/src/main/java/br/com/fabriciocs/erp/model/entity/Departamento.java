package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Repository
@Table("Departamentos")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class Departamento extends Model{

	public String getNome(){
		return getString("nome");
	}
	
	public void setNome(String nome){
		setString("nome", nome);
	}
	
	public String getDescricao(){
		return getString("descricao");
	}
	
	public void setDescricao(String descricao){
		setString("descricao", descricao);
	}
	
	public void setEmpresa(Empresa empresa){
		setParent(empresa);
	}
	
	public Empresa getEmpresa(){
		return  parent(Empresa.class);
	}
}
