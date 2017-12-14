package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.GsonBuilder;

import beans.Transaction;
import dao.TransactionDAO;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(InitServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter outputStream = null;
		try {
			List<Transaction> transactions = TransactionDAO.listaTransacciones( request.getParameter("iban") );
			logger.info("::::: TRANSACTIONS json: "+transactions);
			response.setContentType("application/plain-text");
			response.setHeader("Content-Disposition", "attachment;filename=transacciones.json");
			outputStream = response.getWriter();
			logger.info("creating json");
			outputStream.write(new GsonBuilder().setPrettyPrinting().create().toJson(transactions));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				outputStream.close();
				response.sendRedirect("listaTransacciones");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
