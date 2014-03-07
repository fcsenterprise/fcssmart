package br.com.fabriciocs.erp.view.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.sql.DataSource;

import org.javalite.activejdbc.Base;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/config/app/spring.xml")
public class CredencialTest {
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private DataSource dataSource;

	private MockMvc mockMvc;

	// @Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
		if (!Base.hasConnection()) {
			Base.open(dataSource);
		}

	}

	public void tearDown() {
		Base.rollbackTransaction();
		Base.close();
	}

	@Test
	public void successWhenSearchByLoginAndEmail() throws Exception {
	}
}
