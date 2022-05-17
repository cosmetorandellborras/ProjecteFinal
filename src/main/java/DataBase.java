import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

public class DataBase {
	
	public static boolean comprobarAltaUsuario (Usuario usuario) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		boolean trobat = true;
		if (usuario instanceof Cliente) {
			query = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = '"+((Cliente) usuario).getDni()+"'";
		}
		if (usuario instanceof Trabajador) {
			query = "SELECT * FROM TRABAJADOR WHERE ID_TRABAJADOR = '"+((Trabajador) usuario).getDni()+"'";
		}
		if (usuario instanceof Administrador) {
			query = "SELECT * FROM ADMINISTRADOR WHERE ID_ADMINISTRADOR = '"+((Administrador) usuario).getDni()+"'";
		}
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next() == false) {
				trobat = false;	
				
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trobat;
	}
	/**
	 * metodo insertarCliente
	 * inserta un cliente en la base de datos
	 * @param cliente
	 */
	public static void insertarUsuario(Usuario usuario) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		if (usuario instanceof Cliente) {
			query = "INSERT INTO CLIENTE VALUES ('"+usuario.getDni()+"','"+usuario.getNombre()+"','"+usuario.getApellido()+"',"+usuario.getTelefono()+",'"+usuario.getCorreo()+"','"+usuario.getContrasena()+"')";		
			}
		else if (usuario instanceof Trabajador) {
			query = "INSERT INTO TRABAJADOR VALUES ('"+usuario.getDni()+"','"+usuario.getNombre()+"','"+usuario.getApellido()+"',"+((Trabajador) usuario).getEdad()+","+usuario.getTelefono()+",'"+usuario.getCorreo()+"','"+usuario.getContrasena()+"')";
		}
		else if (usuario instanceof Administrador) {
			query = "INSERT INTO ADMINISTRADOR VALUES ('"+usuario.getDni()+"','"+usuario.getNombre()+"','"+usuario.getApellido()+"',"+usuario.getTelefono()+",'"+usuario.getCorreo()+"','"+usuario.getContrasena()+"')";
		}

				
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			st.executeUpdate(query);
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido contactar con la base de datos");
		}
	}
	public static String mostrarDatosCliente(Cliente cliente) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "SELECT NOMBRE,APELLIDO,TELEFONO,CORREO,CONTRASENA FROM CLIENTE WHERE ID_CLIENTE = '"+cliente.getDni()+"'";
		String texto = "";
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			Cliente jCliente = new Cliente();
			jCliente.setNombre(rs.getString("NOMBRE"));
			jCliente.setApellido(rs.getString("APELLIDO"));
			jCliente.setTelefono(rs.getInt("TELEFONO"));
			jCliente.setCorreo(rs.getString("CORREO"));
			jCliente.setContrasena(rs.getString("CONTRASEÑA"));
			
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return texto;
	}
	public static void actualizarCliente(Cliente cliente,String nombre, String apellido, Integer telefono, String correo, String contrasena) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "UPDATE CLIENTE SET NOMBRE = '"+nombre+"', APELLIDO = '"+apellido+"', TELEFONO = "+telefono+",CORREO = '"+correo+"', CONTRASENA = '"+contrasena+"' WHERE CLIENT_ID = '"+cliente.getDni()+"'";
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			st.executeUpdate(query);
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo ejecutar query
	 * @param query
	 * @return
	 */
	public static void ejecutarSelect(String query) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int buscarUltimoIdAnuncio(){
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "SELECT MAX(ID_ANUNCIO) FROM ANUNCIO";
		int num = 0;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next() == false) {
				num = 1;	
				
			}
			else {
				num = rs.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
}
