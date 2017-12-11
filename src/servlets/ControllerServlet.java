package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(InitServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String sw = request.getParameter("sw");
			logger.info("ACTION: " + sw);
			switch (sw) {

			case "logout":

			case "getaccounts":
				request.getRequestDispatcher("/ListAccountsServlet").include(request, response);
				break;

			case "deleteaccount":
				request.getRequestDispatcher("/ListAccountsServlet").include(request, response);
				break;

			case "gettransactions":
				request.getRequestDispatcher("/TransactionsServlet").include(request, response);
				break;
				
			case "downloadtransactions":
				request.getRequestDispatcher("/DownloadServlet").include(request, response);
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String sw = request.getParameter("sw");
			logger.info("ACTION: " + sw);
			switch (sw) {

			case "login":
				request.getRequestDispatcher("/LoginServlet").include(request, response);
				break;

			case "registry":
				request.getRequestDispatcher("/RegistryServlet").include(request, response);
				break;

			case "update":
				request.getRequestDispatcher("/UpdateServlet").include(request, response);
				break;

			case "insertaccount":
				request.getRequestDispatcher("/ListAccountsServlet").include(request, response);
				break;

			case "newtransaction":
				request.getRequestDispatcher("/TransactionsServlet").include(request, response);
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
