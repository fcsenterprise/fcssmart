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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Repository
@Table("Credenciais")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new",
		"username", "password", "enabled", "authorities", "metamodelLocal",
		"parents", "cachedParent", "accountNonExpired", "accountNonLocked",
		"credentialsNonExpired" })
public class Credencial extends Model implements UserDetails {

	public Credencial() {
	}

	public String getLogin() {
		return (String) get("login");
	}

	public void setLogin(String login) {
		set("login", login);
	}

	@JsonIgnore
	public String getSenha() {
		return getString("senha");
	}

	@JsonProperty("senha")
	public void setSenha(String senha) {
		// senha = DigestUtils.md5DigestAsHex(senha.getBytes());
		set("senha", senha);
	}

	public String getEmail() {
		return (String) get("email");
	}

	public void setEmail(String email) {
		set("email", email);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
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
