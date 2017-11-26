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

import beans.Account;
import beans.Cliente;
import dao.AccountDAO;

/**
 * Servlet implementation class ListAccountsServlet
 */
@WebServlet("/ListAccountsServlet")
public class ListAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAccountsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("loginok.jsp");

		String sw = (String) request.getAttribute("sw");
		System.out.println("SW" + sw);

		switch (sw) {

		/// GETACCOUNTS ///
		case "getaccounts":
			try {
				List<Account> accounts = AccountDAO
						.getAccounts(((Cliente) request.getSession().getAttribute("clientSession")).getDni());
				HttpSession session = request.getSession();
				session.setAttribute("accounts", accounts);
				request.getRequestDispatcher("detalleCuenta.jsp").include(request, response);
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
		System.out.println(request.getParameter("iban"));
		System.out.println("::::: SW AS PARAM: " + request.getParameter("sw"));
		String sw = request.getParameter("sw");
		((HttpServletRequest) request).setAttribute("sw", sw);
		/// --
		System.out.println("::::: SW AS ATTR: " + request.getAttribute("sw"));
		sw = (String) request.getAttribute("sw"); // parameter para String, atribute para Objects
		System.out.println("SW: " + sw);

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
				System.out.println("::::: IS ACCOUNT INSERTED? " + AccountDAO.insertAccount(account));
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
				System.out.println("::::: IS ACCOUNT DELETED? "+AccountDAO.deleteAccount(account2));
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