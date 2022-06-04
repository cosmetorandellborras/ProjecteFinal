import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetDatosCli
 */
@WebServlet("/GetDatosCli")
public class GetDatosCli extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet GetDatosCli
	 * Recibe el dni del cliente y llama al método getDatosCliente
	 * Envia un string en formato JSON con los datos del cliente
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("dni");
		
		String json = MyTask.getDatosCliente(dni);
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(json);
	}

}
