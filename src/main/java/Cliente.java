import java.util.Date;
/**
 * Clase Cliente
 * Subclase de Usuario
 * @author cosmetorandell
 *
 */

public class Cliente extends Usuario{
	/**
	 * Constructor vacio
	 */
	public Cliente() {
		super();
		
	}
	/**
	 * Constructor completo
	 * @param nombre
	 * @param apellido
	 * @param telefono
	 * @param correo
	 * @param contrasena
	 * @param dni
	 */
	public Cliente(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni) {
		super(nombre, apellido, telefono, correo, contrasena, dni);
		
	}
	/**
	 * Método registarUsuario
	 * Llama al método comprobarAltaUsuario, verifica que no esta dado de alta y si no lo esta, llama al método insertarUsuario y inserta los datos del cliente
	 * @return num retorna un numero que identificará si la operación se ha completado satisfactoriamente o no
	 */
	@Override
	public int registrarUsuario() {
		int num = -1;
		boolean trobat = DataBase.comprobarAltaUsuario(this);
		if (!trobat) {
			num = 0;
			DataBase.insertarUsuario(this);
		}
		return num;
	}
	/**
	 * Método mostrarDatos
	 * Llama al metodo mostrarDatosCliente
	 */
	@Override
	public void mostrarDatos() {
		DataBase.mostrarDatosCliente(this);
	}
	/**
	 * Método actualizarDatos
	 * Llama al método actualizarDatosUsuario
	 * @return num retorna un numero que identificará si la operación se ha completado satisfactoriamente o no
	 */
	public int actualizarDatos() {
		int num = DataBase.actualizarDatosUsuario(this);
		return num;
	}
	
}
