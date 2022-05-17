
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
	//Constructores
	public Trabajador() {
		super();
		
	}

	public Trabajador(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni,
			Integer edad) {
		super(nombre, apellido, telefono, correo, contrasena, dni);
		this.setEdad(edad);
	}
	//Metodos
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
	@Override
	public void modificarDatos(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni, Integer edad) {
		
	}

	public void publicarAnuncio(String[] zona, String trabajo, String disponibilidad, Float precio_hora) {
		
	}

	public String listarSolicitudes() {
		String texto = new String();
		return texto;
	}

	public void aceptarServicio(Contratacion contratacion) {
		
	}

	
	
	
	
}
