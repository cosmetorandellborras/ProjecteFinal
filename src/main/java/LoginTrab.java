import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginTrab
 */
@WebServlet("/LoginTrab")
public class LoginTrab extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet LoginTrab
	 * Recibe los datos para llamar al método comprobarLoginTrabajador
	 * Envia un numero que indica si la operación se ha llevado a cabo con étixo o no
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = new String();
		String pass = new String();
		dni = request.getParameter("dni");
		pass = request.getParameter("pass");
		
		int num;
		
		num = DataBase.comprobarLoginTrabajador(dni, pass);
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(num));
	}

}
