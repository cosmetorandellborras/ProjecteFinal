import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertarAnuncio
 */
@WebServlet("/InsertarAnuncio")
public class InsertarAnuncio extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String trabajo = new String();
		String zona = new String();
		String dispo = new String();
		String precioHora = new String();
		String precioHora2 = new String();
		String trabajador = new String();
		
		trabajo = request.getParameter("trabajo");
		zona = request.getParameter("zona");
		dispo = request.getParameter("dispo");
		precioHora = request.getParameter("precioHora");
		precioHora2 = "";
		for (int i=0; i<precioHora.length();i++) {
			if (precioHora.charAt(i) == ',') {
				precioHora2 = precioHora2 + '.';
			}
			else {
				precioHora2 = precioHora2 + precioHora.charAt(i);
			}
		}
		
		MyTask.regAnuncio(trabajo,zona,dispo,trabajador,Float.parseFloat(precioHora2));
		//response.addHeader("Access-Control-Allow-Origin","*");
		//response.getWriter().append();
	}

}
