

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import beans.Cliente;

/**
 * Servlet Filter implementation class RedirectFilter
 */
@WebFilter()
// urlPatterns={"/detalleCuenta.jsp"}
public class RedirectFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RedirectFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Cliente c = ((Cliente) ((HttpServletRequest) request).getSession().getAttribute("clientSession"));
		System.out.println(":::::C: "+c);
		
		String path = ((HttpServletRequest)request).getRequestURI();
		if (path.equalsIgnoreCase("/banco_jones/detalleCuenta.jsp")) {
			((HttpServletRequest)request).setAttribute("sw", "getaccounts");
			request.getRequestDispatcher("/ListAccountsServlet").include(request, response);
		}
		return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
