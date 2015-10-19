package controleDeAcesso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiltroDeControleDeAcesso implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private List<String> papeis;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String requestURI = req.getRequestURI();
		HttpServletResponse res = (HttpServletResponse) response;
		
		String admin = "admin";
		String cliente = "cliente";
		papeis = new ArrayList<>();
		papeis.add(admin);
		papeis.add(cliente);
		
		if (requestURI.endsWith("Login.jsp")) {
			if(session.getAttribute("usuario") != null) {
				if(session.getAttribute("papel") == cliente) {
					res.sendRedirect(req.getContextPath() + "/cliente/Home.jsp");
				}
				if(session.getAttribute("papel") == admin) {
					res.sendRedirect(req.getContextPath() + "/admin/Home.jsp");
				}
			}
			else {
				chain.doFilter(request, response);
			}
		} else {
			if(session.getAttribute("usuario") != null) {
				if(session.getAttribute("papel") == cliente && requestURI.contains("/admin/")) {
					res.sendRedirect(req.getContextPath() + "/cliente/Home.jsp");
				}
				if(session.getAttribute("papel") == cliente && requestURI.endsWith("cadastroCliente.jsp")) {
					res.sendRedirect(req.getContextPath() + "/cliente/Home.jsp");
				}
				chain.doFilter(new PapelDoUsuarioSolicitante(req), response);
			}
			else {
				if(requestURI.endsWith("cadastroCliente.jsp")) {
					chain.doFilter(new PapelDoUsuarioSolicitante(req), response);
				}
				else 
					res.sendRedirect(req.getContextPath() + "/public/Login.jsp");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
