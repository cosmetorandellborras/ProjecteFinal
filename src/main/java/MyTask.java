import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
/**
 * Clase MyTask
 * Esta clase auxiliar hace de nexo entre las distintas clases para ejecutar los métodos
 * También tiene un main para interactuar con el programa a través de consola
 * @author cosmetorandell
 *
 */
public class MyTask {
	/**
	 * Método regCliente
	 * Recibe por parámetro los datos del cliente
	 * Crea un nuevo cliente y llama al método registrarUsuario
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param contraseña
	 * @param dni
	 * @return num retorna un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
	public static int regCliente(String nombre, String apellido, int telefono, String correo,String contraseña, String dni){
		Cliente nuevo = new Cliente(nombre,apellido,telefono,correo,contraseña,dni);
		int num = nuevo.registrarUsuario();
		return num;
	}
	/**
	 * Método regTrabajador
	 * Recibe por parámetro los datos del trabajador
	 * Crea un nuevo trabajador y llama al método registrarUsuario
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param contraseña
	 * @param dni
	 * @param edad
	 * @return num retorna un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
	public static int regTrabajador(String nombre, String apellido, int telefono, String correo,String contraseña, String dni, int edad ){
		Trabajador nuevo = new Trabajador(nombre,apellido,telefono,correo,contraseña,dni,edad);
		int num = nuevo.registrarUsuario();
		return num;
	}
	/**
	 * Método regAnuncio
	 * Recibe por parámetro los datos del anuncio
	 * Crea un nuevo anuncio y llama al método publicarAnuncio
	 * @param dniTrabajador
	 * @param zona
	 * @param trabajo
	 * @param disponibilidad
	 * @param precioHora
	 * @param trabajodb
	 * @param dispodb
	 * @return num retorna un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
	public static int regAnuncio(String dniTrabajador, String zona, String trabajo, String disponibilidad,
			float precioHora, String trabajodb,String dispodb) {
		Anuncio nuevo = new Anuncio(dniTrabajador,Zona.valueOf(zona),Trabajo.valueOf(trabajo),Disponibilidad.valueOf(disponibilidad),precioHora);
		int num = nuevo.publicarAnuncio(trabajodb, dispodb);
		return num;
	}
	/**
	 * Método contServicio
	 * Recibe por parámetro los datos para realizar la contratacion
	 * Crea una nueva contratacion con todos los datos introducidos y llama al método inContrato
	 * @param dniCliente
	 * @param idAnuncio
	 * @param direccionCliente
	 * @param fechaInicio
	 * @param fechaFin
	 * @param horaInicio
	 * @param horaFin
	 * @return num retorna un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
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
	/**
	 * Método getDatosCliente
	 * Recibe por parámetro el dni de un cliente, crea un cliente nuevo, llama al metodo getCliente que creara el cliente con los datos de su dni
	 * Crea un objeto gson, después un string que tendra en formato JSON los datos del cliente
	 * @param dni
	 * @return datos retorna los datos del cliente en formato JSON
	 */
	public static String getDatosCliente(String dni) {
		Cliente cliente = DataBase.getCliente(dni);
		Gson gson = new Gson();
		String datos = gson.toJson(cliente);
		return datos;
	}
	/**
	 * Método getDatosTrabajador
	 * Recibe por parámetro el dni de un trabajador, crea un trabajador nuevo, llama al metodo getTrabajador que creara el trabajador con los datos de su dni
	 * Crea un objeto gson, después un string que tendra en formato JSON los datos del trabajador
	 * @param dni
	 * @return datos retorna los datos del trabajador en formato JSON
	 */
	public static String getDatosTrabajador(String dni) {
		Trabajador trabajador = DataBase.getTrabajador(dni);
		Gson gson = new Gson();
		String datos = gson.toJson(trabajador);
		return datos;
	}
	/**
	 * Método modificarDatosCliente
	 * Recibe por parámetro los datos del cliente
	 * Crea un nuevo cliente con los datos recibidos por parámetro y llama al método actualizarDatos
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param pass
	 * @return num retorna un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
	public static int modificarDatosCliente(String dni, String nombre, String apellido, int telefono, String correo, String pass) {
		Cliente cliente = new Cliente(nombre,apellido,telefono,correo,pass,dni);
		int num = cliente.actualizarDatos();
		return num;
	}
	/**
	 * Método modificarDatosTrabajador
	 * Recibe por parámetro los datos del trabajador
	 * Crea un nuevo trabajador con los datos recibidos por parámetro y llama al método actualizarDatos
	 * @param dni
	 * @param nombre
	 * @param apellido
	 * @param edad
	 * @param telefono
	 * @param correo
	 * @param pass
	 * @return num retorna un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
	public static int modificarDatosTrabajador(String dni,String nombre, String apellido, int edad, int telefono, String correo, String pass) {
		Trabajador trabajador = new Trabajador(nombre,apellido,telefono,correo,pass,dni,edad);
		int num = trabajador.actualizarDatos();
		return num;
	}
	/**
	 * Método consultarErrores
	 * Lee el archivo logErrores.txt e imprime por consola el contenido del fichero
	 */
	public static void consultarErrores () {
		String linea = new String();
		try {
			BufferedReader in = new BufferedReader(new FileReader("./logErrores.txt"));
			while ((linea = in.readLine()) != null) {
				System.out.println(linea);
			}
			if (in != null) {
				in.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Método main
	 * Este método contiene el menu del administrador, todo por consola
	 * Primera opción un login administrador que le permitirá acceder al resto de opciones
	 * Ejecutar sentencia SQL
	 * Consultar log de errores
	 * Crear un nuevo administrador
	 */
	public static void main (String [] args) {
		Scanner teclat = new Scanner(System.in);
		int opcion;
		int opcion_admin;
		boolean salir = false;
		boolean salirAdmin = false;
		boolean login = false;
		while(!salir) {
			System.out.println("---------MENU SERVICIOS HOGAR---------");
			System.out.println("Opcion 1 - Login administrador");
			System.out.println("Opcion 2 - Salir");
			opcion = Integer.parseInt(teclat.nextLine());
			switch (opcion) {
			case 1: login = Administrador.loginAdministrador();
			if (login) {
				System.out.println("---------MENU SERVICIOS HOGAR---------");
				System.out.println("Opcion 1. Ejecutar sentencia SQL (BAJO SU RESPONSABILIDAD)");
				System.out.println("Opcion 2. Consultar log de errores");
				System.out.println("Opcion 3. Introducir administrador");
				opcion_admin = Integer.parseInt(teclat.nextLine());
				switch (opcion_admin) {
				case 1: System.out.println("Introduzca la sentencia SQL");
				DataBase.ejecutarSelect(teclat.nextLine()); break;
				case 2: consultarErrores(); break;
				case 3: Administrador admin = new Administrador();
				System.out.println("Introduce el dni del administrador");
				admin.setDni(teclat.nextLine());
				System.out.println("Introduce el nombre del administrador");
				admin.setNombre(teclat.nextLine());
				System.out.println("Introduce el apellido del administrador");
				admin.setApellido(teclat.nextLine());
				System.out.println("Introduce el teléfono del administrador");
				admin.setTelefono(Integer.parseInt(teclat.nextLine()));
				System.out.println("Introduce el correo del administrador");
				admin.setCorreo(teclat.nextLine());
				System.out.println("Introduce la contraseña del administrador");
				admin.setContrasena(teclat.nextLine());
				int num = admin.registrarUsuario();
				if (num == 1) {
					System.out.println("Se ha registrado con éxito un nuevo administrador");
				} break;
				default: System.out.println("No has seleccionado ninguna opción correcta");
				}
			}
			else {
				System.out.println("Login incorrecto");
			}
				;break;
			case 2: salir = true; break;
			default: System.out.print("No has seleccionado ninguna opción correcta");
			}
		}
		
	}
}
