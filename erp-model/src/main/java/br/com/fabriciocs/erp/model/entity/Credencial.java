package br.com.fabriciocs.erp.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
@Table("Credenciais")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new",
		"username", "password", "enabled", "authorities", "metamodelLocal",
		"parents", "cachedParent", "accountNonExpired", "accountNonLocked",
		"credentialsNonExpired" })
public class Credencial extends Model implements UserDetails {

	public transient String confirmacaoSenha;

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getLogin() {
		return getString("login");
	}

	public void setLogin(String login) {
		setString("login", login);
	}

	public String getSenha() {
		return getString("senha");
	}

	public void setSenha(String senha) {
		setString("senha", senha);
	}

	public String getEmail() {
		return getString("email");
	}

	public void setEmail(String email) {
		setString("email", email);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_ADMIN");
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(ga);
		return list;
	}
	@Override
	public String getPassword() {
		return getSenha();
	}

	@Override
	public String getUsername() {
		return getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
