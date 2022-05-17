
public class Administrador extends Usuario {
	//Constructores
	public Administrador() {
		super();
		
	}

	public Administrador(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni) {
		super(nombre, apellido, telefono, correo, contrasena, dni);
		
	}

	@Override
	public int registrarUsuario() {
		int num = -1;
		DataBase.insertarAdministrador(this);
		return num;
	}
	public String ejecutarSelect(String query) {
		String resultat = "";
		return resultat;
	}

	@Override
	public void mostrarDatos() {
		
	}
}
