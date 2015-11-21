package controleDeAcesso;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class PapelDoUsuarioSolicitante extends HttpServletRequestWrapper{

	public PapelDoUsuarioSolicitante(HttpServletRequest request) {
		super(request);
	}
	 
	@Override
	public boolean isUserInRole(String papel) {
		HttpSession session = this.getSession();
        if (session.getAttribute("usuario") != null && session.getAttribute("papel").equals(papel))
            return true;
        else 
        	return false;
	}
}
