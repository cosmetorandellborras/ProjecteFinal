import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActualizarServ
 */
@WebServlet("/ActualizarServ")
public class ActualizarServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet ActualizarServ
	 * Recibe la id del contrato y el estado a actualizar, después llama al método actualizarServ
	 * Envia como respuesta un numero que identificará si la operación se ha completado satisfactoriamente o no
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String estado = request.getParameter("estado");
		
		int num = DataBase.actualizarServ(id, estado);
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(num));
	}

}