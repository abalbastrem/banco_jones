package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import beans.Account;
import beans.Cliente;
import dao.AccountDAO;

/**
 * Servlet implementation class ListAccountsServlet
 */
@WebServlet("/ListAccountsServlet")
public class ListAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(InitServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAccountsServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("loginok.jsp");
		String sw = request.getParameter("sw");
//		String sw = (String) request.getAttribute("sw");
		logger.info("::::: SW: " + sw);

		switch (sw) {

		/// GETACCOUNTS ///
		case "getaccounts":
			try {
				List<Account> accounts = AccountDAO
						.getAccounts(((Cliente) request.getSession().getAttribute("clientSession")).getDni());
				HttpSession session = request.getSession();
				session.setAttribute("accounts", accounts);
				logger.info("::::: SERVLET ACCOUNTS: "+accounts);
				logger.info("::::: B4 REQUESTDISP ");
				request.getRequestDispatcher("detalleCuenta.jsp").include(request, response);
				logger.info("::::: AFTER REQUESTDISP ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		
		/// DELETEACCOUNT ///
		case "deleteaccount":
			try {
				Account account = new Account();
				account.setIban(request.getParameter("iban"));
				String dni = ((Cliente) request.getSession().getAttribute("clientSession")).getDni();
				account.setCliente(dni);
				System.out.println("::::: ACCOUNT TO DELETE: "+account);
				AccountDAO.deleteAccount(account);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/// Changes the SW from parameter to attribute to remain consistent
		String sw = request.getParameter("sw");
		((HttpServletRequest) request).setAttribute("sw", sw);
		sw = (String) request.getAttribute("sw"); // parameter para String, atribute para Objects
		
		logger.info("::::: SW: " + sw);

		switch (sw) {

		/// INSERT ACCOUNT ///
		case "insertaccount":
			Cliente c = ((Cliente) request.getSession().getAttribute("clientSession"));
			System.out.println("C " + c);
			Account account = new Account();
			account.setIban(request.getParameter("iban"));
			account.setSaldo(0L);
			account.setCliente(c.getDni());
			try {
				logger.info("::::: IS ACCOUNT INSERTED? " + AccountDAO.insertAccount(account));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				HttpSession session = request.getSession();
				session.setAttribute("sw", "getaccounts");
				request.getRequestDispatcher("detalleCuenta.jsp").forward(request, response);
			}
			break;

		/// DELETE ACCOUNT ///
		case "deleteaccount":
			Cliente c2 = ((Cliente) request.getSession().getAttribute("clientSession"));
			Account account2 = new Account();
			account2.setCliente(request.getParameter("dni"));
			account2.setIban(request.getParameter("iban"));
			account2.setCliente(c2.getDni());
			try {
				logger.info("::::: IS ACCOUNT DELETED? "+AccountDAO.deleteAccount(account2));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				HttpSession session = request.getSession();
				session.setAttribute("sw", "getaccounts");
				request.getRequestDispatcher("detalleCuenta.jsp").forward(request, response);
			}
			break;
		}
	}

}