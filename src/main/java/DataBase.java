import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import oracle.jdbc.OracleDriver;
/**
 * Clase DataBase
 * Contiene todos los métodos relacionados con la entrada y salida de datos de la base de datos
 * @author cosmetorandell
 */
public class DataBase {
	/**
	 * Método getCliente
	 * Este método busca un cliente dentro de la base de datos y nos devuelve un objeto de la clase cliente
	 * @param dni
	 * @return Cliente 
	 */
	public static Cliente getCliente(String dni) {
		Cliente cliente = new Cliente();
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			PreparedStatement st = con.prepareStatement("SELECT NOMBRE, APELLIDO, TELEFONO, CORREO, CONTRASENA FROM CLIENTE WHERE ID_CLIENTE = ?");
			st.setString(1, dni);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				cliente.setNombre(rs.getString(1));;
				cliente.setApellido(rs.getString(2));
				cliente.setTelefono(rs.getInt(3));
				cliente.setCorreo(rs.getString(4)); 
				cliente.setContrasena(rs.getString(5));
				cliente.setDni(dni);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return cliente;
	}
	/**
	 * Método getTrabajador
	 * Este método busca un trabajador dentro de la base de datos y nos devuelve un objeto de la clase trabajador
	 * @param dni
	 * @return Trabajador
	 */
	public static Trabajador getTrabajador(String dni) {
		Trabajador trabajador = new Trabajador();
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			PreparedStatement st = con.prepareStatement("SELECT NOMBRE, APELLIDO, EDAD, TELEFONO, CORREO, CONTRASENA FROM TRABAJADOR WHERE ID_TRABAJADOR = ?");
			st.setString(1, dni);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				trabajador.setNombre(rs.getString(1));
				trabajador.setApellido(rs.getString(2));
				trabajador.setEdad(rs.getInt(3));
				trabajador.setTelefono(rs.getInt(4));
				trabajador.setCorreo(rs.getString(5)); 
				trabajador.setContrasena(rs.getString(6));
				trabajador.setDni(dni);
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return trabajador;
	}
	/**
	 * Método getAnuncio
	 * Este método busca un anuncio dentro de la base de datos y nos devuelve un objeto de la clase anuncio
	 * @param id
	 * @return Anuncio
	 */
	public static Anuncio getAnuncio(String id) {
		Anuncio anuncio = new Anuncio(Integer.valueOf(id));
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			PreparedStatement st = con.prepareStatement("select a.ID_TRABAJADOR,z.NOMBRE,t.TIPO,d.INTERVALO,a.PRECIO_HORA from ANUNCIO a\n"
					+ "join zona z on a.id_zona=z.ID_ZONA\n"
					+ "join TRABAJO t on a.id_trabajo=t.ID_TRABAJO\n"
					+ "join DISPONIBILIDAD d on a.id_disponibilidad=d.ID_DISPONIBILIDAD WHERE A.ID_ANUNCIO = ?");
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				anuncio.setIdRecuperarAnuncio(Integer.parseInt(id));
				anuncio.setDniTrabajador(rs.getString(1));
				anuncio.setZona(Zona.valueOf(rs.getString(2).toLowerCase().replace(" ", "")));
				anuncio.setTrabajo(Trabajo.valueOf(rs.getString(3).toLowerCase().replace(" ", "")));
				anuncio.setDisponibilidad(Disponibilidad.valueOf(rs.getString(4).toLowerCase().replace(" ", "")));
				anuncio.setPrecioHora(rs.getFloat(5));
			}
			rs.close();
			st.close();
			con.close();
			return anuncio;
		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return anuncio;
	}
	/**
	 * Método insertarContratación
	 * Recibe por parametro un objeto de la clase contratacion e inserta sus datos en la base de datos
	 * @param contratacion
	 * @return int retorna un numero para verificar si se han insertado los datos correctamente
	 */
	public static int insertarContratacion (Contratacion contratacion) {
		int insertado = 0;
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			
			PreparedStatement st = con.prepareStatement("INSERT INTO CONTRATACION (ID_CONTRATACION,ID_ANUNCIO,ID_CLIENTE,FECHA_CONTRATACION,FECHA_FIN,FECHA_INICIO,INTERVALO_HORARIO,DIRECCION_CONTRATO,ESTADO,PRECIO) VALUES (?,?,?,?,?,?,?,?,?,?)");
			st.setInt(1, contratacion.getId());
			st.setInt(2, contratacion.getAnuncio().getId());
			st.setString(3, contratacion.getDniCliente());
			st.setDate(4, java.sql.Date.valueOf(contratacion.getFechaContrato()));
			st.setDate(5, java.sql.Date.valueOf(contratacion.getFechaFin()));
			st.setDate(6, java.sql.Date.valueOf(contratacion.getFechaInicio()));
			st.setString(7, contratacion.getIntervaloHoras());
			st.setString(8, contratacion.getDireccion());
			st.setString(9, String.valueOf(contratacion.getEstado()));
			st.setFloat(10, contratacion.getPrecio());
			st.executeUpdate();
			insertado = 1;
			st.close();
			con.close();
		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return insertado;
	}
	/**
	 * Método comprobarAltaUsuario
	 * Este método recibe un objeto de la clase usuario y comprueba si ya esta registrado en la base de datos
	 * @param usuario
	 * @return boolean devuelve un boleano para saber si existe o no el registro en la base de datos
	 */
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
			imprimirError(e);
			e.printStackTrace();
		}
		return trobat;
	}
	/**
	 * Método insertarUsuario
	 * Este método recibe por parámetro un objeto de la clase Usuario e introduce sus datos en la base de datos
	 * mediante un casting insertara los valores del usuario en la tabla correspondiente
	 * @param usuario
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
			imprimirError(e);
			e.printStackTrace();
		}
	}
	/**
	 * Método mostrarDatosCliente
	 * Selecciona todos los datos del cliente que recibe por parámetro
	 * @param cliente
	 * @return
	 */
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
			imprimirError(e);
			e.printStackTrace();
		}
		return texto;
	}
	/**
	 * Método actualizarDatosUsuario
	 * Recibe un objeto de la clase Usuario por parámetro y mediante un casting
	 * hace un update de la tabla correspondiente de la base de datos
	 * @param usuario
	 * @return int retorna un numero para saber si la actualización se ha realizado con éxito
	 */
	public static int actualizarDatosUsuario(Usuario usuario) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		int updated = 0;
		if (usuario instanceof Cliente) {
			query = "UPDATE CLIENTE SET NOMBRE = '"+usuario.getNombre()+"', APELLIDO = '"+usuario.getApellido()+"', TELEFONO = "+usuario.getTelefono()+",CORREO = '"+usuario.getCorreo()+"', CONTRASENA = '"+usuario.getContrasena()+"' WHERE ID_CLIENTE = '"+usuario.getDni()+"'";
		}
		else if (usuario instanceof Trabajador) {
			query = "UPDATE TRABAJADOR SET NOMBRE = '"+usuario.getNombre()+"', APELLIDO = '"+usuario.getApellido()+"', TELEFONO = "+usuario.getTelefono()+",CORREO = '"+usuario.getCorreo()+"', CONTRASENA = '"+usuario.getContrasena()+"', EDAD = "+((Trabajador) usuario).getEdad()+" WHERE ID_TRABAJADOR = '"+usuario.getDni()+"'";
		}
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			st.executeUpdate(query);
			updated = 1;
			st.close();
			con.close();
		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return updated;
	}
	/**
	 * Método getZona
	 * Este metodo selecciona las diferentes zonas y nos retorna un select html con las diferentes zonas y un campo "Todos" para seleccionar todas las zonas
	 * @return resultat nos devuelve un string con un select que contendrá el nombre de las diferentes zonas
	 */
	public static String getZona() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"zona\"><option value=\"todos\">Todos</option>";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT NOMBRE FROM ZONA ORDER BY 1";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				resultat = resultat + "<option value=\""+rs.getString(1)+"\">"+rs.getString(1)+"</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return resultat;
	}
	/**
	 * Método getZonaPublicar
	 * Este metodo selecciona las diferentes zonas y nos retorna un select html con las diferentes zonas
	 * @return resultat nos devuelve un string con un select que contendrá el nombre de las diferentes zonas
	 */
	public static String getZonaPublicar() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"zona\">";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT NOMBRE FROM ZONA ORDER BY 1";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				resultat = resultat + "<option value=\""+rs.getString(1)+"\">"+rs.getString(1)+"</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return resultat;
	}
	/**
	 * Método getDisponibilidad
	 * Este metodo selecciona las diferentes disponibilidades y nos retorna un select html con las diferentes disponibilidades y una opcion "Todas"
	 * @return resultat nos devuelve un string con un select que contendrá el nombre de las diferentes disponibilidades
	 */
	public static String getDisponibilidad() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"disponibilidad\"><option value=\"todos\">Todos</option>";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT INTERVALO FROM DISPONIBILIDAD ORDER BY 1";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				resultat = resultat + "<option value=\""+rs.getString(1)+"\">"+rs.getString(1)+"</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return resultat;
	}
	/**
	 * Método getDisponibilidadPublicar
	 * Este metodo selecciona las diferentes disponibilidades y nos retorna un select html con las diferentes disponibilidades
	 * @return resultat nos devuelve un string con un select que contendrá el nombre de las diferentes disponibilidades
	 */
	public static String getDisponibilidadPublicar() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"disponibilidad\">";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT INTERVALO FROM DISPONIBILIDAD ORDER BY 1";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				resultat = resultat + "<option value=\""+rs.getString(1)+"\">"+rs.getString(1)+"</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return resultat;
	}
	/**
	 * Método getTrabajo
	 * Este metodo selecciona los diferentes trabajos y nos retorna un select html con los diferentes trabajos y una opción "Todos"
	 * @return resultat nos devuelve un string con un select que contendrá el nombre de los diferentes trabajos
	 */
	public static String getTrabajo() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"trabajo\"><option value=\"todos\">Todos</option>";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT TIPO FROM TRABAJO ORDER BY 1";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				resultat = resultat + "<option value=\""+rs.getString(1)+"\">"+rs.getString(1)+"</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return resultat;
	}
	/**
	 * Método getTrabajo
	 * Este metodo selecciona los diferentes trabajos y nos retorna un select html con los diferentes trabajos
	 * @return resultat nos devuelve un string con un select que contendrá el nombre de los diferentes trabajos
	 */
	public static String getTrabajoPublicar() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"trabajo\">";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT TIPO FROM TRABAJO ORDER BY 1";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				resultat = resultat + "<option value=\""+rs.getString(1)+"\">"+rs.getString(1)+"</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return resultat;
	}
	/**
	 * Método filtrarAnuncio
	 * Este metodo recibe los filtros corresponientes y ejecuta un select con los servicios filtrados
	 * posteriormente crea un string con una tabla con los servicios seleccionados y lo retorna
	 * @param tipo
	 * @param zona
	 * @param dispo
	 * @return resultado
	 */
	public static String filtrarAnuncio(String tipo, String zona, String dispo) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		String resultado = "<table class=\"anuncios\">\n"
				+ "        <tr><th>ID Anuncio</th><th>Tipo de trabajo</th><th>Zona</th><th>Trabajador</th><th>Disponibilidad</th><th>Precio hora</th><th>Valoración</th><th>Seleccionar</th></tr>";
		try {
			if (tipo.equals("todos") && zona.equals("todos") && dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "NVL(rpad('⭐',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'⭐'),'Sin valoración') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "left join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "left join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "left join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "left join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			else if (!tipo.equals("todos") && !zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "NVL(rpad('⭐',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'⭐'),'Sin valoración') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "left join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "left join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "left join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "left join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where TR1.TIPO = '"+tipo+"' AND Z1.NOMBRE = '"+zona+"' AND D1.INTERVALO = '"+dispo+"'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			else if (tipo.equals("todos") && !zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "NVL(rpad('⭐',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'⭐'),'Sin valoración') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "left join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "left join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "left join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "left join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where Z1.NOMBRE = '"+zona+"' AND D1.INTERVALO = '"+dispo+"'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			else if (tipo.equals("todos") && zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "NVL(rpad('⭐',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'⭐'),'Sin valoración') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "left join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "left join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "left join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "left join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where D1.INTERVALO = '"+dispo+"'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			else if (!tipo.equals("todos") && !zona.equals("todos") && dispo.equals("todos")){
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "NVL(rpad('⭐',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'⭐'),'Sin valoración') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "left join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "left join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "left join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "left join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where tr1.TIPO = '"+tipo+"' AND z1.NOMBRE = '"+zona+"'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			else if (!tipo.equals("todos") && zona.equals("todos") && dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "NVL(rpad('⭐',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'⭐'),'Sin valoración') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "left join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "left join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "left join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "left join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where tr1.TIPO = '"+tipo+"'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			else if (!tipo.equals("todos") && zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "NVL(rpad('⭐',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'⭐'),'Sin valoración') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "left join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "left join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "left join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "left join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where tr1.TIPO = '"+tipo+"' AND d1.INTERVALO = '"+dispo+"'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			else {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "NVL(rpad('⭐',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'⭐'),'Sin valoración') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "left join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "left join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "left join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "left join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where z1.NOMBRE = '"+zona+"'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
				while(rs.next()) {
					resultado = resultado+"<tr><td>"+String.valueOf(rs.getInt(1))+
							"</td><td>"+rs.getString(2)+
							"</td><td>"+rs.getString(3)+
							"</td><td>"+rs.getString(4)+
							"</td><td>"+rs.getString(5)+
							"</td><td>"+String.valueOf(rs.getFloat(6))+
							"</td><td>"+rs.getString(7)+
							"</td><td><button onclick=\"solicitar("+String.valueOf(rs.getInt(1))+")\">Solicitar</button></td></tr>";
				}
				rs.close();
				st.close();
				con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			imprimirError(e);
		}
		resultado = resultado + "</table>";
		return resultado;
	}
	/**
	 * Metodo ejecutarSelect
	 * Ejecuta la consulta que recibe por parametro e imprime el resultado por consola
	 * @param query
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
			ResultSetMetaData rsmd = rs.getMetaData();
			int numColumnas = rsmd.getColumnCount();
			while(rs.next()) {
				for(int i = 1; i<=numColumnas;i++) {
					if (i > 1) System.out.print(" | ");
					String valorColumna = rs.getString(i);
					System.out.print(rsmd.getColumnName(i)+": "+valorColumna);
				}
				System.out.println("");
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			imprimirError(e);
		}
	}
	/**
	 * Método buscarUltimoIdAnuncio
	 * Busca en la base de datos el último id creado de un anuncio y retorna el valor
	 * @return num
	 */
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
			imprimirError(e);
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * Método buscarUltimoIdContrato
	 * Busca en la base de datos el ultimo id de contrato creado y lo retorna
	 * @return num
	 */
	public static int buscarUltimoIdContrato(){
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "SELECT MAX(ID_CONTRATACION) FROM CONTRATACION";
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
			imprimirError(e);
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * Método comprobarAnuncioDuplicado
	 * Busca en la base de datos un anuncio con las características que recibe por parámetro
	 * Si no lo encuentra retorna false, si lo encuentra retorna true
	 * @param anuncio
	 * @param tipo
	 * @param dispo
	 * @return trobat
	 */
	public static boolean comprobarAnuncioDuplicado(Anuncio anuncio, String tipo, String dispo) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		int id_trabajo = -1;
		int id_disponibilidad = -1;
		int id_zona = -1;	
		boolean trobat = true;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement st1 = con.prepareStatement("SELECT ID_TRABAJO FROM TRABAJO WHERE TIPO = ?");
			st1.setString(1, tipo);
			ResultSet rs1 = st1.executeQuery();
			while (rs1.next()) {
				id_trabajo = rs1.getInt(1);
			}
			PreparedStatement st2 = con.prepareStatement("SELECT ID_DISPONIBILIDAD FROM DISPONIBILIDAD WHERE INTERVALO = ?");
			st2.setString(1, dispo);
			ResultSet rs2 = st2.executeQuery();
			while (rs2.next()) {
				id_disponibilidad = rs2.getInt(1);
			}
			PreparedStatement st3 = con.prepareStatement("SELECT ID_ZONA FROM ZONA WHERE NOMBRE = ?");
			st3.setString(1, String.valueOf(anuncio.getZona()));
			ResultSet rs3 = st3.executeQuery();
			while(rs3.next()) {
				id_zona = rs3.getInt(1);
			}
			PreparedStatement st4 = con.prepareStatement("SELECT * FROM ANUNCIO WHERE ID_TRABAJO = ? AND ID_TRABAJADOR = ? AND ID_DISPONIBILIDAD = ? AND ID_ZONA = ? AND PRECIO_HORA = ?");
			st4.setInt(1, id_trabajo);
			st4.setString(2, anuncio.getDniTrabajador());
			st4.setInt(3, id_disponibilidad);
			st4.setInt(4, id_zona);
			st4.setFloat(5, anuncio.getPrecioHora());
			ResultSet rs4 = st4.executeQuery();
			if (rs4.next() == false) {
				trobat = false;	
				
			}
			con.close();
			st1.close();
			st2.close();
			st3.close();
			st4.close();
			rs1.close();
			rs2.close();
			rs3.close();
			rs4.close();
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return trobat;
	}
	/**
	 * Método insertarAnuncio
	 * Inserta un anuncio en la base de datos con los datos que recibe por parámetro
	 * @param anuncio
	 * @param tipo
	 * @param dispo
	 * @return insertado retorna un numero que indicá si el proceso se ha completado correctamente o no
	 */
	public static int insertarAnuncio(Anuncio anuncio,String tipo,String dispo) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		int id_trabajo = -1;
		int id_disponibilidad = -1;
		int id_zona = -1;	
		int insertado = 1;
		try {
			
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url,user,pass);
			
			PreparedStatement st1 = con.prepareStatement("SELECT ID_TRABAJO FROM TRABAJO WHERE TIPO = ?");
			st1.setString(1, tipo);
			ResultSet rs = st1.executeQuery();
			while (rs.next()) {
				id_trabajo = rs.getInt(1);
			}
			
			PreparedStatement st2 = con.prepareStatement("SELECT ID_DISPONIBILIDAD FROM DISPONIBILIDAD WHERE INTERVALO = ?");
			st2.setString(1, dispo);
			ResultSet rs1 = st2.executeQuery();
			while (rs1.next()) {
				id_disponibilidad = rs1.getInt(1);
			}
			
			PreparedStatement st3 = con.prepareStatement("SELECT ID_ZONA FROM ZONA WHERE NOMBRE = ?");
			st3.setString(1, String.valueOf(anuncio.getZona()));
			ResultSet rs2 = st3.executeQuery();
			while (rs2.next()) {
				id_zona = rs2.getInt(1);
			}
			
			PreparedStatement st4 = con.prepareStatement("INSERT INTO ANUNCIO VALUES (?,?,?,?,?,?)");
			st4.setInt(1, anuncio.getId());
			st4.setInt(2, id_trabajo);
			st4.setString(3, anuncio.getDniTrabajador());
			st4.setInt(4, id_disponibilidad);
			st4.setInt(5, id_zona);
			st4.setFloat(6, anuncio.getPrecioHora());
			st4.executeUpdate();
			insertado = 0;
			st1.close();
			st2.close();
			st3.close();
			st4.close();
			rs.close();
			rs1.close();
			rs2.close();
			con.close();
		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return insertado;
	}
	/**
	 * Método comprobarLoginAdministrador
	 * Busca en la base de datos un administrador que coincida con los datos introducidos por parámetro
	 * @param dni
	 * @param passAdmin
	 * @return num retorna un valor que indica si los datos por parametro coinciden con los de la base de datos
	 */
	public static int comprobarLoginAdministrador(String dni, String passAdmin) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		int num = -1;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement st1 = con.prepareStatement("SELECT CONTRASENA FROM ADMINISTRADOR WHERE ID_ADMINISTRADOR =?");
			st1.setString(1, dni);
			ResultSet rs = st1.executeQuery();
			
			if (rs.next() == false) {
				num = -1;	
				
			}
			else {
				if (rs.getString(1).equals(passAdmin)){
					num = 1;
				}
				else {
					num = 0;
				}
			}
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * Método comprobarLoginCliente
	 * Busca en la base de datos un cliente que coincida con los datos introducidos por parámetro
	 * @param dni
	 * @param passCli
	 * @return num retorna un valor que indica si los datos por parametro coinciden con los de la base de datos
	 */
	public static int comprobarLoginCliente(String dni, String passCli) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		int num = -1;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement st1 = con.prepareStatement("SELECT CONTRASENA FROM CLIENTE WHERE ID_CLIENTE =?");
			st1.setString(1, dni);
			ResultSet rs = st1.executeQuery();
			
			if (rs.next() == false) {
				num = -1;	
				
			}
			else {
				if (rs.getString(1).equals(passCli)){
					num = 1;
				}
				else {
					num = 0;
				}
			}
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * Método comprobarLoginTrabajador
	 * Busca en la base de datos un trabajador que coincida con los datos introducidos por parámetro
	 * @param dni
	 * @param passTra
	 * @return num retorna un valor que indica si los datos por parametro coinciden con los de la base de datos
	 */
	public static int comprobarLoginTrabajador(String dni, String passTra) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		int num = -1;
		
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement st1 = con.prepareStatement("SELECT CONTRASENA FROM TRABAJADOR WHERE ID_TRABAJADOR =?");
			st1.setString(1, dni);
			ResultSet rs = st1.executeQuery();
			
			if (rs.next() == false) {
				num = -1;	
				
			}
			else {
				if (rs.getString(1).equals(passTra)){
					num = 1;
				}
				else {
					num = 0;
				}
			}
		}catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * Método mostrarServiciosCli
	 * Busca en la base de datos todos los servicios contratados por el cliente con la id introducida por parámetro
	 * Si el estado del servicio es "aceptado" mostrara un botón para poder valorar el servicio, en caso contrario
	 * no mostrara dicho botón
	 * @param id
	 * @return resultado retorna una string con una tabla html con todos los servicios del cliente
	 */
	public static String mostrarServicioCli(String id) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		String resultado = "<table class=\"servicios\">"
				+ "<tr><th>ID Contrato</th><th>Trabajador</th><th>Fecha Contrato</th><th>Inicio</th><th>Final</th><th>Intervalo horario</th><th>Dirección</th><th>Comentario</th><th>Valoración</th><th>Estado</th><th>Precio</th></tr>";
		try {
			query = "select c.ID_CONTRATACION,t.NOMBRE,to_char(c.FECHA_CONTRATACION,'dd-mm-yyyy'),to_char(c.FECHA_INICIO,'dd-mm-yyyy'),to_char(c.FECHA_FIN,'dd-mm-yyyy'),c.INTERVALO_HORARIO,c.DIRECCION_CONTRATO,NVL(c.COMENTARIO_VALORACION,'Sin comentario'),c.VALORACION,c.ESTADO,c.PRECIO from CONTRATACION c JOIN\n"
					+ "ANUNCIO a on c.id_anuncio=a.ID_ANUNCIO\n"
					+ "join TRABAJADOR t on a.ID_TRABAJADOR=t.ID_TRABAJADOR\n"
					+ "WHERE c.ID_CLIENTE = '"
					+ id + "'";

			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				if (rs.getString(10).equals("aceptado")) {
					resultado = resultado + "<tr><td>" + String.valueOf(rs.getInt(1)) + "</td><td>" + rs.getString(2)
							+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>"
							+ rs.getString(5)
							+ "</td><td>" + rs.getString(6) + "</td><td>" + rs.getString(7) + "</td><td>"
							+ rs.getString(8) + "</td><td>" + String.valueOf(rs.getFloat(9)) + "</td><td>" + rs.getString(10) + "</td><td>" + String.valueOf(rs.getFloat(11))
							+ "</td><td><button onclick=\"valorarServ(" + String.valueOf(rs.getInt(1))
							+ ")\">Valorar</button></td></tr>";
				} else {
					resultado = resultado + "<tr><td>" + String.valueOf(rs.getInt(1)) + "</td><td>" + rs.getString(2)
					+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>"
					+ rs.getString(5)
					+ "</td><td>" + rs.getString(6) + "</td><td>" + rs.getString(7) + "</td><td>"
					+ rs.getString(8) + "</td><td>" + String.valueOf(rs.getFloat(9)) + "</td><td>" + rs.getString(10) + "</td><td>" + String.valueOf(rs.getFloat(11))
					+ "</td></tr>";
				}
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		resultado = resultado + "</table>";
		return resultado;
	}
	/**
	 * Método mostrarServiciosTrab
	 * Busca en la base de datos todos los servicios pendientes del trabajador con la id introducida por parámetro
	 * Si el estado del servicio es "pendiente" mostrara un botón para poder aceptar el servicio o para rechazar el servicio, en caso contrario
	 * no mostrara dichos botones
	 * @param id
	 * @return resultado retorna una string con una tabla html con todos los servicios pendientes del trabajador
	 */
	public static String mostrarServicioTrab(String id) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		String resultado = "<table class=\"servicios_trabajador\">\n"
				+ "        <tr>\n"
				+ "            <th>ID Contrato</th>\n"
				+ "            <th>Tipo trabajo</th>\n"
				+ "            <th>Cliente</th>\n"
				+ "            <th>Teléfono</th>\n"
				+ "            <th>Dirección</th>\n"
				+ "            <th>Fecha contrato</th>\n"
				+ "            <th>Fecha inicio</th>\n"
				+ "            <th>Fecha fin</th>\n"
				+ "            <th>Intervalo horario</th>\n"
				+ "            <th>Valoración</th>\n"
				+ "            <th>Comentario</th>\n"
				+ "            <th>Estado</th>\n"
				+ "            <th>Precio</th>\n"
				+ "        </tr>";
		try {
			query = "select c.ID_CONTRATACION,t.TIPO,cl.NOMBRE,cl.TELEFONO,c.DIRECCION_CONTRATO,to_char(c.FECHA_CONTRATACION,'dd-mm-yyyy'),to_char(c.FECHA_INICIO,'dd-mm-yyyy'),to_char(c.FECHA_FIN,'dd-mm-yyyy'),c.INTERVALO_HORARIO,c.VALORACION,NVL(c.COMENTARIO_VALORACION,'Sin comentario'),c.ESTADO,c.PRECIO from CONTRATACION c\n"
					+ "join ANUNCIO a on c.id_anuncio=a.ID_ANUNCIO\n"
					+ "join CLIENTE cl on c.id_cliente=cl.id_cliente\n"
					+ "join TRABAJO t on a.ID_TRABAJO=t.ID_TRABAJO\n"
					+ "WHERE a.id_trabajador = '"
					+ id + "'";

			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				if(rs.getString(12).equals("pendiente")) {		
					resultado = resultado + "<tr>\n"
							+ "            <td>"+String.valueOf(rs.getInt(1))+"</td>\n"
							+ "            <td>"+rs.getString(2)+"</td>\n"
							+ "            <td>"+rs.getString(3)+"</td>\n"
							+ "            <td>"+String.valueOf(rs.getInt(4))+"</td>\n"
							+ "            <td>"+rs.getString(5)+"</td>\n"
							+ "            <td>"+rs.getString(6)+"</td>\n"
							+ "            <td>"+rs.getString(7)+"</td>\n"
							+ "            <td>"+rs.getString(8)+"</td>\n"
							+ "            <td>"+rs.getString(9)+"</td>\n"
							+ "            <td>"+String.valueOf(rs.getFloat(10))+"</td>\n"
							+ "            <td>"+rs.getString(11)+"</td>\n"
							+ "            <td>"+rs.getString(12)+"</td>\n"
							+ "            <td>"+String.valueOf(rs.getFloat(13))+"</td>\n"
							+ "            <td><button onclick=\"actualizarServ("+String.valueOf(rs.getInt(1))+",'aceptado')\">Aceptar</button></td>\n"
							+ "            <td><button onclick=\"actualizarServ("+String.valueOf(rs.getInt(1))+",'rechazado')\">Rechazar</button></td>\n"
							+ "        </tr>";
				} else {
					resultado = resultado + "<tr>\n"
							+ "            <td>"+String.valueOf(rs.getInt(1))+"</td>\n"
							+ "            <td>"+rs.getString(2)+"</td>\n"
							+ "            <td>"+rs.getString(3)+"</td>\n"
							+ "            <td>"+String.valueOf(rs.getInt(4))+"</td>\n"
							+ "            <td>"+rs.getString(5)+"</td>\n"
							+ "            <td>"+rs.getNString(6)+"</td>\n"
							+ "            <td>"+rs.getString(7)+"</td>\n"
							+ "            <td>"+rs.getString(8)+"</td>\n"
							+ "            <td>"+rs.getString(9)+"</td>\n"
							+ "            <td>"+String.valueOf(rs.getFloat(10))+"</td>\n"
							+ "            <td>"+rs.getString(11)+"</td>\n"
							+ "            <td>"+rs.getString(12)+"</td>\n"
							+ "            <td>"+String.valueOf(rs.getFloat(13))+"</td>\n"
							+ "        </tr>";
				}
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		resultado = resultado + "</table>";
		return resultado;
	}
	/**
	 * Método actualizarServ
	 * Busca en la base de datos un servicio con id que recibe por parámetro y actualiza su estado con el valor que recibe por parámetro
	 * @param id
	 * @param estado
	 * @return num retorna un numero con un valor para comprobar si la actualización se ha realizado correctamente
	 */
	public static int actualizarServ(String id, String estado) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		int num = 0;
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement st = con.prepareStatement("UPDATE CONTRATACION SET ESTADO = ? WHERE ID_CONTRATACION = ?");
			st.setString(1, estado);
			st.setInt(2, Integer.parseInt(id));
			st.executeUpdate();
			if (estado.equals("aceptado")) {
				num = 1;
			}
			else if (estado.equals("rechazado")) {
				num = -1;
			}
			st.close();
			con.close();

		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * Método valorarServ
	 * Busca en la base de datos un contrato con la id que recibe por parámetro y actualiza su valoración y comentario por los valores que recibe por parámetro
	 * @param id
	 * @param valoracion
	 * @param comentario
	 * @return num retorna un numero para verificar si se ha realizado correctamente la actualización
	 */
	public static int valorarServ(String id, int valoracion, String comentario) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		int num = 0;
		try {
			query = "UPDATE CONTRATACION "
					+ "SET VALORACION = " + (float) valoracion + ", COMENTARIO_VALORACION = '" + comentario + "'"
					+ " WHERE ID_CONTRATACION = " + Integer.parseInt(id);

			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			st.executeUpdate(query);
			num = 1;
			st.close();
			con.close();

		} catch (SQLException e) {
			imprimirError(e);
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * Método imprimirError
	 * Escribe en un fichero "logErrores.txt", todos los errores de tipo SQLException que ocurran en los métodos anteriores
	 * @param e recibe la excepcion por parámetro
	 */
	public static void imprimirError(SQLException e){
        StackTraceElement[] stkTrace = e.getStackTrace();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try {
        	BufferedWriter outError = new BufferedWriter(new FileWriter("./logErrores.txt",true));
            for (int i = 0; i < stkTrace.length; i++) {
                outError.write(
                        "Error Index " + i + " TimeStamp " + timestamp + " StackTrace " + stkTrace[i].toString());
                outError.write('\n');
            }
            if (outError != null) {
                outError.close();
            }
        } catch (IOException e1) {
			e1.printStackTrace();
		} 
    }
}

