import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MostrarServicioCli
 */
@WebServlet("/MostrarServicioCli")
public class MostrarServicioCli extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * Servlet MostrarServicioCli
	 * Recibe el id del cliente y llama al método mostrarServicioCli
	 * Envia un string con una tabla html con los servicios del cliente
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String id = request.getParameter("id");
		String tabla = new String();
		
		tabla = DataBase.mostrarServicioCli(id);
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(tabla);
	}

}
