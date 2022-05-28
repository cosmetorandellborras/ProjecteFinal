import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSelDispoPublicar
 */
@WebServlet("/GetSelDispoPublicar")
public class GetSelDispoPublicar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat = "";
		resultat = DataBase.getDisponibilidadPublicar();
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(resultat);
	}

}
