import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActualizarCli
 */
@WebServlet("/ActualizarCli")
public class ActualizarCli extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet ActualizarCli
	 * Recibe todos los datos de cliente y llama al método modificarDatosCliente
	 * Envia como respuesta un numero que identificará si la operación se ha completado satisfactoriamente o no
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String telefono = request.getParameter("telefono");
		String correo = request.getParameter("correo");
		String pass = request.getParameter("contrasena");
		String dni = request.getParameter("dni");
		
		int num = MyTask.modificarDatosCliente(dni,nombre,apellido,Integer.parseInt(telefono),correo,pass);
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(num));
	}

}
