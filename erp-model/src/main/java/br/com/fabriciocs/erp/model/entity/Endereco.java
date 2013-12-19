package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
@Table("Enderecos")
public class Endereco extends Model{

}
