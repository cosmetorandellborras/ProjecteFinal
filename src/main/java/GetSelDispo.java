import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSelDispo
 */
@WebServlet("/GetSelDispo")
public class GetSelDispo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet GetSelDispo
	 * Envia un string con un select html con las distintas disponibilidades
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat = "";
		resultat = DataBase.getDisponibilidad();
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(resultat);
	}

}
