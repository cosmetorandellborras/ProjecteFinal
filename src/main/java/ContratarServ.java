import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.ParseException;

/**
 * Servlet implementation class ContratarServ
 */
@WebServlet("/ContratarServ")
public class ContratarServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Servlet ContratarServ
	 * Recibe los datos del servicio a contratar y llama al método contServ
	 * Envia como respuesta un numero que identificará si la operación se ha completado satisfactoriamente o no
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String dnicliente = request.getParameter("dnicliente");
			String anuncio = request.getParameter("anuncio");
			String direccion = request.getParameter("direccion");
			
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			
			LocalDate fechaInicio = LocalDate.parse(request.getParameter("fechainicio"),formateador);
			LocalDate fechaFin = LocalDate.parse(request.getParameter("fechafin"),formateador);
			
			String horaInicio = request.getParameter("horainicio");
			String horaFin = request.getParameter("horafin");

			
			int num = MyTask.contServicio(dnicliente, anuncio, direccion, fechaInicio, fechaFin, horaInicio, horaFin);
			response.addHeader("Access-Control-Allow-Origin","*");
			response.getWriter().append(String.valueOf(num));
		
	}

}
