package br.com.fabriciocs.erp.view.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.sql.DataSource;

import org.javalite.activejdbc.Base;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.fabriciocs.erp.model.entity.Credencial;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/app/spring.xml")
public class CredencialTest {
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private DataSource dataSource;

//	private Log logger = SLF4JLogFactory.getLog(CredencialTest.class);

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
		Base.open(dataSource);
	}

	public void tearDown() {
		Base.rollbackTransaction();
		Base.close();
	}

	@Test
	public void successWhenSearchByLoginAndEmail() throws Exception {
//		Credencial credencial = Credencial.findFirst("login = ? and email = ? ", "fabricio", "Email");
//		String result = new ObjectMapper().writeValueAsString(credencial);
//		this.mockMvc.perform(get("/credencial/fabricio/Email").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isOk())
//				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//				.andExpect(content().string(result));
	}
}
