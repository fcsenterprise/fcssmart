package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table("CodTributacoes")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class CodVinculacao extends SimpleTable {

}
