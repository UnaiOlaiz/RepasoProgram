package C4BasesDeDatos;

import java.sql.*;

public class ProbandoBD {
	
	
	public static void main(String[] args) {
		try {
			// Cargamos el driver
			Class.forName("org.sqlite.JDBC"); // el .jar tiene que estar instalado en mi caso el 3.27.2.1.jar
		} catch (ClassNotFoundException e) {
			System.exit(0);
		}
		// Establecer la conexión con la base de datos
		Connection conexion = null;
		String sent = "";
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:test.db"); // nombre de la bd este caso test.db, poner .db no .bd como si fuese en español
			System.out.println("Conexion establecida correctamente");
			// Para que el .db aparezca en el proyecto F5 -> refresh
			// Empezamos a crear sentencias
			Statement statement = conexion.createStatement();
			
			// Primeras sentencias, para meter datos -> executeUpdate()
			sent = "create table if not exists prueba (id integer, nombre string)";
			System.out.println(sent);
			statement.executeUpdate(sent);
			
			sent = "insert into prueba values(1, 'andoni')";
			System.out.println(sent);
			statement.executeUpdate(sent);
			
			sent = "insert into prueba values(2, 'elena')";
			System.out.println(sent);
			statement.executeUpdate(sent);
			
			// Primeras selects, para sacar datos -> ResultSet rs = statement.executeQuery(sent)
			sent = "select * from prueba where nombre = 'elena'";
			System.out.println(sent);
			ResultSet rs = statement.executeQuery(sent);
			int fila = 0;
			while(rs.next()) { // Mientras tenga filas que seleccionar
				int valor = rs.getInt("id"); // nombre de la columna
				String nombre = rs.getString("nombre");
				fila++;
				System.out.println( fila + valor + nombre );
			}
			
			// Al cerrar Java se tienen que cerrar estos tres:
			rs.close();
			statement.close();
			conexion.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.print("Error al ejecutar: " + sent);
		}
	}

}
