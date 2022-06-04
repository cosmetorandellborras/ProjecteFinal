import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MostrarServicioTrab
 */
@WebServlet("/MostrarServicioTrab")
public class MostrarServicioTrab extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet MostrarServicioTrab
	 * Recibe el id del trabajador y llama al método mostrarServicioTrab
	 * Envia un string con una tabla html con los servicios del trabajador
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String tabla = DataBase.mostrarServicioTrab(id);
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(tabla);
	}

}
