package br.com.fabriciocs.erp.view.config.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.javalite.activejdbc.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OpenTransactionInView implements Filter {
	private final Logger logger = LoggerFactory
			.getLogger(OpenTransactionInView.class);
	@Autowired
	private DataSource dataSource;

	public OpenTransactionInView() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		long before = System.currentTimeMillis();
		try {
			Base.open(dataSource);
			Base.openTransaction();

			logger.info(request.toString());
			chain.doFilter(request, response);

			Base.commitTransaction();
		} catch (IOException e) {
			Base.rollbackTransaction();
			throw e;
		} catch (ServletException e) {
			Base.rollbackTransaction();
			throw e;
		} finally {

			Base.close();
		}
		logger.info("Processing took: " + (System.currentTimeMillis() - before)
				+ " milliseconds");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
