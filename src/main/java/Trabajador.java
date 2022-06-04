/**
 * Clase Trabajador
 * Subclase de Usuario
 * @author cosmetorandell
 *
 */
public class Trabajador extends Usuario{
	//Atributo
	private Integer edad;
	
	//Getters i setters
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	/**
	 * Constructor vacio
	 */
	public Trabajador() {
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
	 * @param edad
	 */
	public Trabajador(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni,
			Integer edad) {
		super(nombre, apellido, telefono, correo, contrasena, dni);
		this.setEdad(edad);
	}
	//Metodos
	/**
	 * Método registrarUsuario
	 * Llama al método comprobarAltaUsuario para comprobar si ya esta dado de alta el trabajador y en caso de que no lo este
	 * llama al método insertarUsuario que inserta el nuevo trabajador en la base de datos
	 * @return num retorna un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
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
	@Override
	public void mostrarDatos() {
		
	}
	/**
	 * Método actualizarDatos
	 * Llama al método actualizarDatosUsuario y actualiza los datos del trabajador
	 * @return num retorna un numero para verificar que la operación se ha llevado a cabo de manera satisfactoria
	 */
	public int actualizarDatos() {
		int num = DataBase.actualizarDatosUsuario(this);
		return num;
	}

	
}
