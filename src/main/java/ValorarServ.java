import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ValorarServ
 */
@WebServlet("/ValorarServ")
public class ValorarServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet ValorarServ
	 * Recibe el id del contrato, la valoracion y el comentario
	 * Llama al método valorarServ y actualiza la valoracion del servicio
	 * Envia un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		int valoracion = Integer.parseInt(request.getParameter("valoracion"));
		String comentario = request.getParameter("comentario");
		
		int num = DataBase.valorarServ(id, valoracion, comentario);
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(num));
	}

}
