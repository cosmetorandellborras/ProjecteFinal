import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ActualizarTrab
 */
@WebServlet("/ActualizarTrab")
public class ActualizarTrab extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet ActualizarTrab
	 * Recibe los datos del trabajador y llama al método modificarDatosTrabajador
	 * Envia como respuesta un numero que identificará si la operación se ha completado satisfactoriamente o no
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		int telefono = Integer.parseInt(request.getParameter("telefono"));
		String correo = request.getParameter("correo");
		String pass = request.getParameter("contrasena");
		String dni = request.getParameter("dni");
		int edad = Integer.parseInt(request.getParameter("edad"));
		
		int num = MyTask.modificarDatosTrabajador(dni,nombre,apellido,edad,telefono,correo,pass);
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(num));
	}

}
