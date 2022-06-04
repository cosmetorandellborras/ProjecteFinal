import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetDatosTrab
 */
@WebServlet("/GetDatosTrab")
public class GetDatosTrab extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet GetDatosTrab
	 * Recibe el dni del trabajador y llama al método getDatosTrabajador
	 * Envia un string en formato JSON con los datos del trabajador
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		
		String json = MyTask.getDatosTrabajador(dni);
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(json);
	}

}
