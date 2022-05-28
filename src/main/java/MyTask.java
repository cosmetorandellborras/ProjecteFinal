import java.time.Duration;
import java.time.LocalDate;

import com.google.gson.Gson;

public class MyTask {
	
	public static int regCliente(String nombre, String apellido, int telefono, String correo,String contraseña, String dni){
		Cliente nuevo = new Cliente(nombre,apellido,telefono,correo,contraseña,dni);
		int num = nuevo.registrarUsuario();
		return num;
	}
	public static int regTrabajador(String nombre, String apellido, int telefono, String correo,String contraseña, String dni, int edad ){
		Trabajador nuevo = new Trabajador(nombre,apellido,telefono,correo,contraseña,dni,edad);
		int num = nuevo.registrarUsuario();
		return num;
	}
	public static int regAnuncio(String dniTrabajador, String zona, String trabajo, String disponibilidad,
			float precioHora, String trabajodb,String dispodb) {
		Anuncio nuevo = new Anuncio(dniTrabajador,Zona.valueOf(zona),Trabajo.valueOf(trabajo),Disponibilidad.valueOf(disponibilidad),precioHora);
		int num = nuevo.publicarAnuncio(trabajodb, dispodb);
		return num;
	}
	public static int contServicio(String dniCliente, String idAnuncio,String direccionCliente,LocalDate fechaInicio, LocalDate fechaFin,String horaInicio,String horaFin) {
		Anuncio anuncio = DataBase.getAnuncio(idAnuncio);
		String intervalo = horaInicio+"-"+horaFin;
		LocalDate fechaContrato = LocalDate.now();
		Duration diff = Duration.between(fechaInicio.atStartOfDay(), fechaFin.atStartOfDay());
		long diffDias = diff.toDays()+1;
		int dias = (int) diffDias;
		int h1 = Integer.parseInt(horaInicio);
		int h2 = Integer.parseInt(horaFin);
		
		float precio = (h2-h1)*dias*anuncio.getPrecioHora();
		
		Contratacion nueva = new Contratacion(dniCliente,anuncio,direccionCliente,fechaInicio,fechaFin,intervalo,fechaContrato,Estado.pendiente,precio);
		int num = nueva.inContrato();
		return num;
	}
	public static String getDatosCliente(String dni) {
		Cliente cliente = DataBase.getCliente(dni);
		Gson gson = new Gson();
		String datos = gson.toJson(cliente);
		return datos;
	}
	public static String getDatosTrabajador(String dni) {
		Trabajador trabajador = DataBase.getTrabajador(dni);
		Gson gson = new Gson();
		String datos = gson.toJson(trabajador);
		return datos;
	}
	public static int modificarDatosCliente(String dni, String nombre, String apellido, int telefono, String correo, String pass) {
		Cliente cliente = new Cliente(nombre,apellido,telefono,correo,pass,dni);
		int num = cliente.actualizarDatos();
		return num;
	}
	public static int modificarDatosTrabajador(String dni,String nombre, String apellido, int edad, int telefono, String correo, String pass) {
		Trabajador trabajador = new Trabajador(nombre,apellido,telefono,correo,pass,dni,edad);
		int num = trabajador.actualizarDatos();
		return num;
	}
}
