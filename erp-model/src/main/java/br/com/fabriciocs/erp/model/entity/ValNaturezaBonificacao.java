package br.com.fabriciocs.erp.model.entity;

import org.javalite.activejdbc.annotations.Table;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
@Table("ValNaturezaBonificacao")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new" })
public class ValNaturezaBonificacao extends SimpleTable {

}
