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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String trabajo = new String();
		String trabajo2 = new String();
		String zona = new String();
		String dispo = new String();
		String dispo2 = new String();
		String precioHora = new String();
		String precioHora2 = new String();
		String trabajador = new String();
		int insertado;
		
		trabajador = request.getParameter("dni_trabajador");
		trabajo = request.getParameter("trabajo");
		trabajo2 = trabajo.toLowerCase();
		trabajo2 = trabajo2.replace(" ","");
		zona = request.getParameter("zona");
		dispo = request.getParameter("dispo");
		dispo2 = dispo.toLowerCase();
		dispo2 = dispo2.replace(" ","");
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
		
		insertado = MyTask.regAnuncio(trabajador,zona,trabajo2,dispo2,Float.parseFloat(precioHora2),trabajo,dispo);
		response.addHeader("Access-Control-Allow-Origin","*");
		response.getWriter().append(String.valueOf(insertado));
	
	}

}
