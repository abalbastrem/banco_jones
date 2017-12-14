package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import beans.Cliente;

import org.apache.logging.log4j.LogManager;

import dao.UpdateDAO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(InitServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("creating updated client object...");
		String dni = ((Cliente) request.getSession().getAttribute("clientSession")).getDni();
		String name = request.getParameter("name");
		String surnames = request.getParameter("surnames");
		String dob = request.getParameter("dob");
		String sex = request.getParameter("sex");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		UpdateDAO.update(dni, name, surnames, dob, sex, address, phone);
		
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60);
		String encodeURL = response.encodeRedirectURL("loginok.jsp");
		response.sendRedirect(encodeURL);
		request.getRequestDispatcher("loginok.jsp").include(request,response);
	}

}