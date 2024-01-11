package C4BasesDeDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EjemploPreparedStatement {
	
	private static Connection connection;
	private static String comando;
	private static Logger logger;
	private static Statement statement;
	
	public static void main(String[] args) {
		try {
			logger = Logger.getLogger("EjemploPreparedStatement");
			logger.addHandler(new FileHandler("EjemploPreparedStatement.xml"));
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.exit(0);
		}
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:testPrepared.db");
			logger.log(Level.INFO, "Conexión establecida.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			comando = "create table if not exists Usuarios (id INT AUTO_INCREMENT primary key, nombre varchar(50), apellido varchar(50));";
			statement.executeUpdate(comando);
			logger.log(Level.INFO, "Tabla creada : " + comando); 
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Último comando: " + comando);
		}
		
		/*
		 * A partir de ahora vamos a implementar la clase PreparedStatment
		 * que ayuda mucho al evitar problemas con los diferentes tipos de 
		 * atributos al ser insertados en sentencias SQL
		 */
		comando = "insert into usuarios (id, nombre, apellido) values (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(comando);
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, "Unai");
			preparedStatement.setString(2, "Olaizola");
			
			int filas = preparedStatement.executeUpdate();
			
			if (filas > 0) {
				logger.log(Level.INFO, "Usuario insertado correctamente: " + comando);
			} else {
				logger.log(Level.WARNING, "No se pudo ejecutar el comando correctamente, " + comando);
			}
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error en el último comando: " + comando);
		}
		
		try {
			comando = "select id, nombre, apellido from usuarios";
			PreparedStatement preparedStatement = connection.prepareStatement(comando);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				
				System.out.println(id + "-" + nombre + "-" + apellido);
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error al ejecutar el comando: " + comando);
		}
	}

}
