import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

public class DataBase {

	public static boolean comprobarAltaUsuario(Usuario usuario) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		boolean trobat = true;
		if (usuario instanceof Cliente) {
			query = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = '" + ((Cliente) usuario).getDni() + "'";
		}
		if (usuario instanceof Trabajador) {
			query = "SELECT * FROM TRABAJADOR WHERE ID_TRABAJADOR = '" + ((Trabajador) usuario).getDni() + "'";
		}
		if (usuario instanceof Administrador) {
			query = "SELECT * FROM ADMINISTRADOR WHERE ID_ADMINISTRADOR = '" + ((Administrador) usuario).getDni() + "'";
		}
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
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
	 * metodo insertarCliente inserta un cliente en la base de datos
	 * 
	 * @param cliente
	 */
	public static void insertarUsuario(Usuario usuario) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		if (usuario instanceof Cliente) {
			query = "INSERT INTO CLIENTE VALUES ('" + usuario.getDni() + "','" + usuario.getNombre() + "','"
					+ usuario.getApellido() + "'," + usuario.getTelefono() + ",'" + usuario.getCorreo() + "','"
					+ usuario.getContrasena() + "')";
		} else if (usuario instanceof Trabajador) {
			query = "INSERT INTO TRABAJADOR VALUES ('" + usuario.getDni() + "','" + usuario.getNombre() + "','"
					+ usuario.getApellido() + "'," + ((Trabajador) usuario).getEdad() + "," + usuario.getTelefono()
					+ ",'" + usuario.getCorreo() + "','" + usuario.getContrasena() + "')";
		} else if (usuario instanceof Administrador) {
			query = "INSERT INTO ADMINISTRADOR VALUES ('" + usuario.getDni() + "','" + usuario.getNombre() + "','"
					+ usuario.getApellido() + "'," + usuario.getTelefono() + ",'" + usuario.getCorreo() + "','"
					+ usuario.getContrasena() + "')";
		}

		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
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
		String query = "SELECT NOMBRE,APELLIDO,TELEFONO,CORREO,CONTRASENA FROM CLIENTE WHERE ID_CLIENTE = '"
				+ cliente.getDni() + "'";
		String texto = "";

		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			Cliente jCliente = new Cliente();
			jCliente.setNombre(rs.getString("NOMBRE"));
			jCliente.setApellido(rs.getString("APELLIDO"));
			jCliente.setTelefono(rs.getInt("TELEFONO"));
			jCliente.setCorreo(rs.getString("CORREO"));
			jCliente.setContrasena(rs.getString("CONTRASEÃ‘A"));
<<<<<<< Updated upstream

=======
			
>>>>>>> Stashed changes
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return texto;
	}

	public static void actualizarCliente(Cliente cliente, String nombre, String apellido, Integer telefono,
			String correo, String contrasena) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "UPDATE CLIENTE SET NOMBRE = '" + nombre + "', APELLIDO = '" + apellido + "', TELEFONO = "
				+ telefono + ",CORREO = '" + correo + "', CONTRASENA = '" + contrasena + "' WHERE CLIENT_ID = '"
				+ cliente.getDni() + "'";

		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			st.executeUpdate(query);
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getZona() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"zona\"><option value=\"todos\">Todos</option>";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT NOMBRE FROM ZONA ORDER BY 1";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				resultat = resultat + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	public static String getDisponibilidad() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"disponibilidad\"><option value=\"todos\">Todos</option>";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT INTERVALO FROM DISPONIBILIDAD ORDER BY 1";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				resultat = resultat + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	public static String getTrabajo() {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String resultat = "<select id=\"trabajo\"><option value=\"todos\">Todos</option>";
		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			String query = "SELECT DISTINCT TIPO FROM TRABAJO ORDER BY 1";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				resultat = resultat + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(1) + "</option>";
			}
			resultat = resultat + "</select>";
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	public static String filtrarAnuncio(String tipo, String zona, String dispo) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		String resultado = "<table class=\"anuncios\">\n"
				+ "        <tr><th>ID Anuncio</th><th>Tipo de trabajo</th><th>Zona</th><th>Trabajador</th><th>Disponibilidad</th><th>Precio hora</th><th>ValoraciÃ³n</th><th>Seleccionar</th></tr>";
		try {
			if (tipo.equals("todos") && zona.equals("todos") && dispo.equals("todos")) {
<<<<<<< Updated upstream
				query = "SELECT a1.ID_ANUNCIO,\n" + "tr1.TIPO tipo_anuncio,\n" + "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n" + "d1.INTERVALO,\n" + "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n" + "FROM TRABAJADOR t1\n"
=======
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
>>>>>>> Stashed changes
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
<<<<<<< Updated upstream
			} else if (!tipo.equals("todos") && !zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n" + "tr1.TIPO tipo_anuncio,\n" + "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n" + "d1.INTERVALO,\n" + "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n" + "FROM TRABAJADOR t1\n"
=======
			}
			else if (!tipo.equals("todos") && !zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
>>>>>>> Stashed changes
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n" + "where TR1.TIPO = '"
						+ tipo + "' AND Z1.NOMBRE = '" + zona + "' AND D1.INTERVALO = '" + dispo + "'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
<<<<<<< Updated upstream
			} else if (tipo.equals("todos") && !zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n" + "tr1.TIPO tipo_anuncio,\n" + "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n" + "d1.INTERVALO,\n" + "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n" + "FROM TRABAJADOR t1\n"
=======
			}
			else if (tipo.equals("todos") && !zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
>>>>>>> Stashed changes
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where Z1.NOMBRE = '" + zona + "' AND D1.INTERVALO = '" + dispo + "'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
<<<<<<< Updated upstream
			} else if (tipo.equals("todos") && zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n" + "tr1.TIPO tipo_anuncio,\n" + "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n" + "d1.INTERVALO,\n" + "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n" + "FROM TRABAJADOR t1\n"
=======
			}
			else if (tipo.equals("todos") && zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
>>>>>>> Stashed changes
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where D1.INTERVALO = '" + dispo + "'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
<<<<<<< Updated upstream
			} else if (!tipo.equals("todos") && !zona.equals("todos") && dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n" + "tr1.TIPO tipo_anuncio,\n" + "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n" + "d1.INTERVALO,\n" + "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n" + "FROM TRABAJADOR t1\n"
=======
			}
			else if (!tipo.equals("todos") && !zona.equals("todos") && dispo.equals("todos")){
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
>>>>>>> Stashed changes
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n" + "where tr1.TIPO = '"
						+ tipo + "' AND z1.NOMBRE = '" + zona + "'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
<<<<<<< Updated upstream
			} else if (!tipo.equals("todos") && zona.equals("todos") && dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n" + "tr1.TIPO tipo_anuncio,\n" + "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n" + "d1.INTERVALO,\n" + "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n" + "FROM TRABAJADOR t1\n"
=======
			}
			else if (!tipo.equals("todos") && zona.equals("todos") && dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
>>>>>>> Stashed changes
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n" + "where tr1.TIPO = '"
						+ tipo + "'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
<<<<<<< Updated upstream
			} else if (!tipo.equals("todos") && zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n" + "tr1.TIPO tipo_anuncio,\n" + "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n" + "d1.INTERVALO,\n" + "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n" + "FROM TRABAJADOR t1\n"
=======
			}
			else if (!tipo.equals("todos") && zona.equals("todos") && !dispo.equals("todos")) {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
>>>>>>> Stashed changes
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n" + "where tr1.TIPO = '"
						+ tipo + "' AND d1.INTERVALO = '" + dispo + "'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
<<<<<<< Updated upstream
			} else {
				query = "SELECT a1.ID_ANUNCIO,\n" + "tr1.TIPO tipo_anuncio,\n" + "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n" + "d1.INTERVALO,\n" + "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n" + "FROM TRABAJADOR t1\n"
=======
			}
			else {
				query = "SELECT a1.ID_ANUNCIO,\n"
						+ "tr1.TIPO tipo_anuncio,\n"
						+ "z1.NOMBRE zona,\n"
						+ "t1.NOMBRE trabajador,\n"
						+ "d1.INTERVALO,\n"
						+ "a1.PRECIO_HORA, \n"
						+ "rpad('â­�',trunc(max((SELECT avg(c2.VALORACION) FROM TRABAJADOR t2\n"
						+ "join ANUNCIO a2 on t2.id_trabajador=a2.id_trabajador\n"
						+ "join CONTRATACION c2 on a2.id_anuncio=c2.id_anuncio\n"
						+ "where a1.id_trabajador=a2.id_trabajador))),'â­�') puntuacion \n"
						+ "FROM TRABAJADOR t1\n"
>>>>>>> Stashed changes
						+ "join ANUNCIO a1 on t1.id_trabajador=a1.id_trabajador\n"
						+ "join CONTRATACION c1 on a1.id_anuncio=c1.id_anuncio\n"
						+ "join TRABAJO tr1 on a1.id_trabajo=tr1.id_trabajo\n"
						+ "join zona z1 on a1.id_zona=z1.ID_ZONA\n"
						+ "join DISPONIBILIDAD d1 on a1.ID_DISPONIBILIDAD=d1.ID_DISPONIBILIDAD\n"
						+ "where z1.NOMBRE = '" + zona + "'\n"
						+ "group by a1.ID_ANUNCIO,tr1.TIPO,z1.NOMBRE,t1.NOMBRE ,d1.INTERVALO,a1.PRECIO_HORA\n"
						+ "order by puntuacion desc";
			}
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				resultado = resultado + "<tr><td>" + String.valueOf(rs.getInt(1)) + "</td><td>" + rs.getString(2)
						+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5)
						+ "</td><td>" + String.valueOf(rs.getFloat(6)) + "</td><td>" + rs.getString(7)
						+ "</td><td><button onclick=\"solicitar(" + String.valueOf(rs.getInt(1))
						+ ")\">Solicitar</button></td></tr>";
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultado = resultado + "</table>";
		return resultado;
	}

	/**
	 * Metodo ejecutar query
	 * 
	 * @param query
	 * @return
	 */
	public static void ejecutarSelect(String query) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";

		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int buscarUltimoIdAnuncio() {
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

			} else {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public static void insertarAnuncio(Anuncio anuncio) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		int id_trabajo;
		int id_disponibilidad;
		int id_zona;

		try {
			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			PreparedStatement st1 = con.prepareStatement("SELECT ID_TRABAJO FROM TRABAJO WHERE TIPO = ?");
			st1.setString(1, String.valueOf(anuncio.getTrabajo()));
			ResultSet rs = st1.executeQuery();
			id_trabajo = rs.getInt(1);
			PreparedStatement st2 = con
					.prepareStatement("SELECT ID_DISPONIBILIDAD FROM DISPONIBILIDAD WHERE INTERVALO = ?");
			st2.setString(1, String.valueOf(anuncio.getDisponibilidad()));
			rs = st2.executeQuery();
			id_disponibilidad = rs.getInt(1);
			PreparedStatement st3 = con.prepareStatement("SELECT ID_ZONA FROM ZONA WHERE NOMBRE = ?");
			st3.setString(1, String.valueOf(anuncio.getZona()));
			rs = st3.executeQuery();
			id_zona = rs.getInt(1);

			PreparedStatement st4 = con.prepareStatement("INSERT INTO ANUNCIO VALUES (?,?,?,?,?,?)");
			st4.setInt(1, anuncio.getId());
			st4.setInt(2, id_trabajo);
			st4.setString(3, anuncio.getDniTrabajador());
			st4.setInt(4, id_disponibilidad);
			st4.setInt(5, id_zona);
			st4.setFloat(6, anuncio.getPrecioHora());
			st4.executeUpdate();
			st1.close();
			st2.close();
			st3.close();
			st4.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("No se ha podido contactar con la base de datos");
		}
	}
	public static String mostrarServicioCli(String id) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		String resultado = "<table class=\"servicios\">\n"
				+ "        <tr><th>ID Anuncio</th><th>Tipo de trabajo</th><th>Zona</th><th>Trabajador</th><th>Disponibilidad</th><th>Precio hora</th><th>ValoraciÃ³n</th><th>Seleccionar</th></tr>";
		try {
			query = "SELECT ID_ANUNCIO, ID_TRABAJADOR, FECHA_CONTRATACION, FECHA_INICIO, FECHA_FIN, INTERVALO_HORARIO, DIRECCION_CONTRATO, PRECIO, ESTADO FROM CONTRATACION C JOIN ANUNCIO A ON C.ID_ANUNCIO = A.ID_ANUNCIO WHERE C.ID_CLIENTE = '"
					+ id + "'";

			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				if(rs.getString(9).equals("Finalizado")) {		
					resultado = resultado + "<tr><td>" + String.valueOf(rs.getInt(1)) + "</td><td>" + rs.getString(2)
						+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5)
						+ "</td><td>" + String.valueOf(rs.getFloat(6)) + "</td><td>" + rs.getString(7)
						+ "</td><td><button onclick=\"valorar(" + String.valueOf(rs.getInt(1))
						+ ")\">Solicitar</button></td></tr>";
				} else {
					resultado = resultado + "<tr><td>" + String.valueOf(rs.getInt(1)) + "</td><td>" + rs.getString(2)
					+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5)
					+ "</td><td>" + String.valueOf(rs.getFloat(6)) + "</td><td>" + rs.getString(7)
					+ "</td></tr>";
				}
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static String mostrarServicioTrab(String id) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		String resultado = "<table class=\"servicios\">\n"
				+ "        <tr><th>ID Anuncio</th><th>Tipo de trabajo</th><th>Zona</th><th>Trabajador</th><th>Disponibilidad</th><th>Precio hora</th><th>ValoraciÃ³n</th><th>Seleccionar</th></tr>";
		try {
			query = "SELECT ID_ANUNCIO, ID_CLIENTE, FECHA_CONTRATACION, FECHA_INICIO, FECHA_FIN, INTERVALO_HORARIO, DIRECCION_CONTRATO, PRECIO, ESTADO FROM CONTRATACION C JOIN ANUNCIO A ON C.ID_ANUNCIO = A.ID_ANUNCIO WHERE A.ID_TRABAJADOR = '"
					+ id + "'";

			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				if(rs.getString(9).equals("Pendiente")) {		
					resultado = resultado + "<tr><td>" + String.valueOf(rs.getInt(1)) + "</td><td>" + rs.getString(2)
						+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5)
						+ "</td><td>" + String.valueOf(rs.getFloat(6)) + "</td><td>" + rs.getString(7)
						+ "</td><td><button onclick=\"actualizarServ(" + String.valueOf(rs.getInt(1))
						+ "," + String.valueOf(rs.getString(2)) + ","+ String.valueOf(rs.getString(3))+")\">Aceptar</button></td></tr>";//Le metemos aqui el string aceptar como parametro?
				} else {
					resultado = resultado + "<tr><td>" + String.valueOf(rs.getInt(1)) + "</td><td>" + rs.getString(2)
					+ "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5)
					+ "</td><td>" + String.valueOf(rs.getFloat(6)) + "</td><td>" + rs.getString(7)
					+ "</td></tr>";
				}
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
	
	public static void actualizarServ(String id1, String id2, String id3) {
		String url = "jdbc:oracle:thin:@localhost:1521/ORCLCDB.localdomain";
		String user = "C##COSME";
		String pass = "1234";
		String query = "";
		try {
			query = "UPDATE CONTRATACION\r\n"
					+ "SET ESTADO = \r\n"
					+ "WHERE ID_ANUNCIO =  AND ID_CLIENTE =  AND FECHA_CONTRATACION=  ;\r\n"
					+ "";

			DriverManager.registerDriver(new OracleDriver());
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			st.executeUpdate(query);
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
