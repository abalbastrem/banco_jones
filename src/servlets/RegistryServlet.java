package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import dao.RegistryDAO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/RegistryServlet")
public class RegistryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(InitServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		String name = request.getParameter("name");
		String surnames = request.getParameter("surnames");
		String pass = request.getParameter("pass");
		String dob = request.getParameter("dob");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		RegistryDAO.registry(dni, name, surnames, pass, dob, sex, address, phone);
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60);
		String encodeURL = response.encodeRedirectURL("login.jsp");
		response.sendRedirect(encodeURL);
		request.getRequestDispatcher("login.jsp").include(request,response);
	}

}
