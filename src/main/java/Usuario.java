
public abstract class Usuario {
	//Aributos
	private String nombre;
	private String apellido;
	private Integer telefono; 
	private String correo;
	private String contrasena; 
	private String dni;
	
	//Getters i setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	//Constructor completo
	public Usuario(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setTelefono(telefono);
		this.setCorreo(correo);
		this.setContrasena(contrasena);
		this.setDni(dni);
	}
	//Constructor vacio
	public Usuario() {
		this(null,null,null,null,null,null);
	}
	//Métodos
	public abstract int registrarUsuario();
	
	public abstract void mostrarDatos();
	
	public void modificarDatos(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni) {
		
	}

	public static boolean iniciarSesion(String correo, String contraseña){
		boolean correcte = false;
		return correcte;
	}

	public static String filtrarAnuncios(String trabajo, String zona, String disponibilidad) {
		String texte = "";
		return texte;
	}
	public void registrarUsuario(String nombre, String apellido, Integer telefono, String correo, String contrasena,
			String dni, Integer edad) {
		
	}
	public void modificarDatos(String nombre, String apellido, Integer telefono, String correo, String contrasena,
			String dni, Integer edad) {
		
	}


}
