import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class RegistroCli
 */
@WebServlet("/RegistroCli")
public class RegistroCli extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = new String();
		String apellido = new String();
		String telefono = new String();
		String correo = new String();
		String contraseña = new String();
		String dni = new String();
		nombre = request.getParameter("nombre");
		apellido = request.getParameter("apellido");
		telefono = request.getParameter("telefono");
		correo = request.getParameter("correo");
		contraseña = request.getParameter("contraseña");
		dni = request.getParameter("dni");
		int num = MyTask.regCliente(nombre, apellido, Integer.parseInt(telefono), correo, contraseña, dni);
		System.out.println(num);
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(num));
	}

}
