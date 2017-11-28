package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Cliente;
import beans.Transaction;
import dao.TransaccionesDAO;

/**
 * Servlet implementation class TransactionsServlet
 */
@WebServlet("/TransactionsServlet")
public class TransactionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionsServlet() {
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
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		long amount = Long.parseLong( request.getParameter("amount") );
		Cliente c = (Cliente) request.getSession().getAttribute("clientSession");
		
		try {
			System.out.println("::::: IS TRANSFER EXECUTED? " + TransaccionesDAO.realizaTransaccion(origin, destination, amount, c));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HttpSession session = request.getSession();
			session.setAttribute("sw", "listtransactions");
			request.getRequestDispatcher("listaTransacciones.jsp").forward(request, response);
		}
		
	}

}
