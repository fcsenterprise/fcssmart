package br.com.fabriciocs.erp.view.config.web.filter;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.fabriciocs.erp.model.entity.Credencial;

@Repository
public class MyUserDetailsImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return Credencial.<Credencial> findFirst("login = ?", username);
	}

}
