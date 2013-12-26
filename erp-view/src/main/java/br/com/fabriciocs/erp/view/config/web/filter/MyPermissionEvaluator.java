package br.com.fabriciocs.erp.view.config.web.filter;

import java.io.Serializable;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import br.com.fabriciocs.erp.model.entity.Credencial;

public class MyPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication,
			Object targetDomainObject, Object permission) {
		Credencial cred = (Credencial) authentication.getPrincipal();
		if(cred.getEmpresa() == null && !permission.toString().contains("EMPRESA")){
			MethodSecurityExpressionOperations eval = (MethodSecurityExpressionOperations) targetDomainObject;
			return false;
		}
		if (cred.getAdmin()) {
			return true;
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
