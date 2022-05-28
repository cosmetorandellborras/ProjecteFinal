import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginCli
 */
@WebServlet("/LoginCli")
public class LoginCli extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = new String();
		String pass = new String();
		dni = request.getParameter("dni");
		pass = request.getParameter("pass");
		
		int num;
		
		num = DataBase.comprobarLoginCliente(dni, pass);
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(num));
		
		
	}

}
