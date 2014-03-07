package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table("OpEntregasFutAntecipadas")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class OpEntregaFutAntec extends SimpleTable {

}
