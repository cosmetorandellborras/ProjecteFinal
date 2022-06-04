import java.util.Scanner;
/**
 * Clase Administrador
 * Subclase de Usuario
 * @author cosmetorandell
 *
 */
public class Administrador extends Usuario {
	/**
	 * Constructor vacio
	 */
	public Administrador() {
		super();
		
	}
	/**
	 * Constructor con todos los parámetros
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param contrasena
	 * @param dni
	 */
	public Administrador(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni) {
		super(nombre, apellido, telefono, correo, contrasena, dni);
		
	}
	/**
	 * Método registrarUsuario
	 * Este método llama al método insertarUsuario e insertará el administrador en la base de datos
	 * @return num retorna un numero que identificará si la operación se ha completado satisfactoriamente o no
	 */
	@Override
	public int registrarUsuario() {
		int num = 1;
		DataBase.insertarUsuario(this);
		return num;
	}

	@Override
	public void mostrarDatos() {
		
	}
	/**
	 * Método loginAdministrador
	 * Pide los datos por consola y comprueba si coinciden con un administrador en la base de datos
	 * @return loginCorrecto false si es un correcto, true si es correcto
	 */
	public static boolean loginAdministrador() {
		Scanner teclat = new Scanner(System.in);
		String dni = new String();
		String pass = new String();
		boolean loginCorrecto = false;
		System.out.println("Introduce el DNI del administrador");
		dni = teclat.nextLine();
		System.out.println("Introduce la contraseña");
		pass = teclat.nextLine();
		int num = DataBase.comprobarLoginAdministrador(dni, pass);
		if (num == 1) {
			loginCorrecto = true;
		}
		return loginCorrecto;
	}
}
