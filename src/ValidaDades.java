

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidaDades
 */
@WebServlet("/ValidadDades")
public class ValidaDades extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidaDades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String fecha = request.getParameter("fecha");
		String dni = request.getParameter("dni");
		String letra = (request.getParameter("letra")!=null)?request.getParameter("letra").toUpperCase():"";
		String email = request.getParameter("email");
		
		
		
		// /validar dades amb Postgresql
		
		Vector<String> errors = new Vector<String>();
		
		if (!validaDNI(dni)) {
			errors.add("el dni no es válido");
		}
		if (!validaNom(nombre)) {
			errors.add("el dni no es válido");
		}
		if (!validaFecha(fecha)) {
			errors.add("la fecha no es válida");
		}
		if (!validaEmail(email)) {
			errors.add("el email no es válido");
		}
		
		if (errors.size() == 0) {
			request.getRequestDispatcher("/resumen.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/hola.jsp").forward(request, response);
		}
	}
	
		
		// 	response.getWriter().append("Served at: ").append(request.getContextPath())

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public boolean validaDNI(String dni) {
		String vdni = dni.toUpperCase();
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int numerico = Integer.parseInt(vdni.substring(0, 7));
		char letra = vdni.charAt(8);
		int resto = numerico % 23;
		if (letra == letras.charAt(resto)) {
			return true;
		}
		return false;
	}
	
	public boolean validaNom(String nom) {
		if (nom.matches("[a-zA-Z][ a-zA-Z]*")) {
			return true;
		}
		return false;
	}
	
	public boolean validaFecha(String fecha) {
		boolean valida = false;
		if (fecha.charAt(2) == '/' && fecha.charAt(5) =='/') {
			valida = true;
		}
		return valida;
	}
	
	public boolean validaEmail(String email) {
		if (!email.contains("@")) {
			return false;
		}
		if (!email.contains(".")) {
			return false;
		}
		return true;
	}
}
