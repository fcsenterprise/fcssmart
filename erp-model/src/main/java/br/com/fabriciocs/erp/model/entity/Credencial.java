package br.com.fabriciocs.erp.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Repository
@Table("Credenciais")
@JsonIgnoreProperties({ "frozen", "valid", "idName", "longId", "new",
		"username", "password", "enabled", "authorities", "metamodelLocal",
		"parents", "cachedParent", "accountNonExpired", "accountNonLocked",
		"credentialsNonExpired", "senha" })
public class Credencial extends Model implements UserDetails {

	private transient Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getLogin() {
		return getString("login");
	}

	public void setLogin(String login) {
		setString("login", login);
	}

	public void setAdmin(Boolean admin) {
		setBoolean("admin", admin);
	}

	public Boolean getAdmin() {
		return getBoolean("admin");
	}

	@JsonIgnore
	public String getSenha() {
		return getString("senha");
	}

	@JsonIgnore
	public void setSenha(String senha) {
		setString("senha", senha);
	}

	public String getEmail() {
		return getString("email");
	}

	public void setEmail(String email) {
		setString("email", email);
	}

	public void setDataExpiracao(Date dataExpiracao) {
		setDate("dataExpiracao", dataExpiracao);
	}

	public Date getDataExpiracao() {
		return getDate("dataExpiracao");
	}

	public void setBloqueado(Boolean bloqueado) {
		setBoolean("bloqueado", bloqueado);
	}

	public Boolean getBloqueado() {
		return getBoolean("bloqueado");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Permissao> permissoes = Permissao.find("credencial = ?",
				getLongId());
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (Permissao permissao : permissoes) {
			String url = permissao.getMenu().getUrl().replace("#/", "");
			if (permissao.getLer()) {
				list.add(new SimpleGrantedAuthority(url.toUpperCase() + "_READ"));
			}
			if (permissao.getEditar()) {
				list.add(new SimpleGrantedAuthority(url.toUpperCase() + "_EDIT"));
			}
			if (permissao.getCriar()) {
				list.add(new SimpleGrantedAuthority(url.toUpperCase()
						+ "_CREATE"));
			}
			if (permissao.getRemover()) {
				list.add(new SimpleGrantedAuthority(url.toUpperCase()
						+ "_DELETE"));
			}
			
		}
		if(getAdmin()){
			list.add(new SimpleGrantedAuthority("ADMIN"));
		}
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
		return getDataExpiracao() == null;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !getBloqueado();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return getDataExpiracao() == null;
	}

	@Override
	public boolean isEnabled() {
		Date dataExpiracao = getDataExpiracao();

		boolean expired = dataExpiracao == null
				|| dataExpiracao.before(new Date());
		boolean locked = getBloqueado();

		return (!expired && !locked) || getAdmin();
	}

}
