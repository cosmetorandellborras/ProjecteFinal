import java.util.Date;


public class Cliente extends Usuario{
	//Constructores
	public Cliente() {
		super();
		
	}

	public Cliente(String nombre, String apellido, Integer telefono, String correo, String contrasena, String dni) {
		super(nombre, apellido, telefono, correo, contrasena, dni);
		
	}
	//Metodos
	public static void solicitarServicio(Anuncio anuncio, String direccion, Date fecha_inicio, Date fecha_fin, String intervalo_horas) {
		
	}

	public static void valorarServicio(Contratacion contratacion, int valoracion, String comentario) {
		
	}
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
		DataBase.mostrarDatosCliente(this);
	}
	public int actualizarDatos() {
		int num = DataBase.actualizarDatosUsuario(this);
		return num;
	}
	
}
