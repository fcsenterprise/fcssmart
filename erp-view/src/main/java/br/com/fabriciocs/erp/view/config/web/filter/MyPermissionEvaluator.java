package br.com.fabriciocs.erp.view.config.web.filter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import br.com.fabriciocs.erp.model.entity.Credencial;

public class MyPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		Credencial cred = (Credencial) authentication.getPrincipal();
		if (cred.getEmpresa() == null
				&& !permission.toString().contains("EMPRESA")) {
			MethodSecurityExpressionOperations eval = (MethodSecurityExpressionOperations) targetDomainObject;
			return false;
		}
		if (cred.getAdmin()) {
			return true;
		}

		List<String> permissions = Arrays.asList(permission.toString().split(
				","));
		Collection<? extends GrantedAuthority> authorities = cred
				.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (permissions.contains(grantedAuthority.getAuthority())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication,
			Serializable targetId, String targetType, Object permission) {
		Credencial cred = Credencial.findFirst("login = ?",
				authentication.getName());
		if (cred.getAdmin()) {
			return true;
		}
		return false;
	}

}
