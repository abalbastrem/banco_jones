package servlets;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(name="InitServlet", urlPatterns="/InitServlet",loadOnStartup=1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.setProperty("log4j.configurationFile", "resources/log4j2.xml");
        System.out.println("____________________________________________________");
        //LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
        //File file = new File("resources/log4j2.xml");
        //context.setConfigLocation(file.toURI());
    }
}
