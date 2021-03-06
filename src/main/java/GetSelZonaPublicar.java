import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSelZonaPublicar
 */
@WebServlet("/GetSelZonaPublicar")
public class GetSelZonaPublicar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet GetSelZonaPublicar
	 * Envia un string con un select html con las distintas zonas
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultat = "";
		resultat = DataBase.getZonaPublicar();
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(resultat);
	}

}
