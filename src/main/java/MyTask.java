
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
	public static void regAnuncio(String dniTrabajador, String zona, String trabajo, String disponibilidad,
			float precioHora) {
		Anuncio nuevo = new Anuncio(dniTrabajador,Zona.valueOf(zona),Trabajo.valueOf(trabajo),Disponibilidad.valueOf(disponibilidad),precioHora);
		DataBase.insertarAnuncio(nuevo);
	}
}
