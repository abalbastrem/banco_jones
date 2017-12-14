package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Servlet implementation class MultiServlet
 */
@WebServlet("/MultiServlet")
public class MultiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(InitServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MultiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println(request.getLocale() + "<<<<<");
	
		
		request.getRequestDispatcher("index2.jsp").include(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {	 System.out.println("-------------------------");
	        String filename = "log4j2.xml";
	        response.setContentType("application/plain-text");
	        response.setHeader("Content-Disposition","attachment;filename="+"prueba");

	        File file = new File(filename);
	        FileInputStream fileIn = new FileInputStream(file);
	        OutputStream out = response.getOutputStream();

	        byte[] outputByte = new byte[(int)file.length()];

	        while(fileIn.read(outputByte, 0, (int)file.length()) != -1)
	        {
	        	out.write(outputByte, 0, (int)file.length());
	        }
	     }catch (Exception e) {
	    	 System.out.println("-------------------------");
		}
	}

}
