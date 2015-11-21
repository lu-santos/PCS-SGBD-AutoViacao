package controleDeAcesso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class InterceptorDeActions extends AbstractInterceptor{

	private List<String> allowedRoles = new ArrayList<String>();
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		 Map<String, Object> session = invocation.getInvocationContext().getSession();
		 
		 HttpServletRequest request = ServletActionContext.getRequest();
		 
		 if(session.get("usuario") == null || session.get("papel") == null
				 || !isAllowed(new PapelDoUsuarioSolicitante(request), invocation.getAction())) {
			 return "acessoRecusado";
		 } else {
			 return invocation.invoke();
		 }
	}
	
	public void setAllowedRoles(String roles) {
		this.allowedRoles = stringToList(roles);
	}
	
	protected List<String> stringToList(String val) {
		if (val != null) {
			String[] list = val.split("[ ]*,[ ]*");
		  	return Arrays.asList(list);
		 } else {
		  return Collections.EMPTY_LIST;
		 }
	}
	
	protected boolean isAllowed(HttpServletRequest request, Object action) {
		boolean result = false;
		if (allowedRoles.size() > 0) {
			for (String role : allowedRoles) {
				if (request.isUserInRole(role)) {
					result = true;
					break;
				}
			}
		} 
		return result;
	}

}
