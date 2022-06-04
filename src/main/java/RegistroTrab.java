import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistroTrab
 */
@WebServlet("/RegistroTrab")
public class RegistroTrab extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet RegistroTrab
	 * Recibe los datos del trabajador y llama al método regTrabajador
	 * Envia un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = new String();
		String apellido = new String();
		String telefono = new String();
		String edad = new String();
		String correo = new String();
		String contraseña = new String();
		String dni = new String();
		
		nombre = request.getParameter("nombre");
		apellido = request.getParameter("apellido");
		telefono = request.getParameter("telefono");
		edad = request.getParameter("edad");
		correo = request.getParameter("correo");
		contraseña = request.getParameter("contraseña");
		dni = request.getParameter("dni");
		int num = MyTask.regTrabajador(nombre, apellido, Integer.parseInt(telefono), correo, contraseña, dni, Integer.parseInt(edad));
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(num));
	}

}
