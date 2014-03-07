package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table("CanaisVenda")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class CanalVenda extends SimpleTable {

}
