package br.com.fabriciocs.erp.view.config.web.filter;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	@PostConstruct
	public void afterPropertiesSet() {
		setRedirectStrategy(new DefaultRedirectStrategy());
	}

	protected class NoRedirectStrategy implements RedirectStrategy {

		@Override
		public void sendRedirect(HttpServletRequest request,
				HttpServletResponse response, String url) throws IOException {
			response.setHeader("content-type", "application/json");
			response.setHeader("accept", "application/json");
			response.getWriter().write("{logado:true}");
		}

	}

}
