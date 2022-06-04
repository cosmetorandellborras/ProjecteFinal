import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FiltrarAnuncios
 */
@WebServlet("/FiltrarAnuncios")
public class FiltrarAnuncios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet FiltrarAnuncios
	 * Recibe los parámetros para filtrar un anuncio y llama al método filtrarAnuncio
	 * Envia como respuesta un string con una tabla con todos los servicios
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String trabajo = new String();
		String zona = new String();
		String dispo = new String();
		String tabla = new String();
		
		trabajo = request.getParameter("trabajo");
		zona = request.getParameter("zona");
		dispo = request.getParameter("dispo");
		
		tabla = DataBase.filtrarAnuncio(trabajo, zona, dispo);
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(tabla);
	}

}
