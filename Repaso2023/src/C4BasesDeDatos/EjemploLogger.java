package C4BasesDeDatos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EjemploLogger {
	
	private static Logger logger;
	private static Connection connection;
	private static Statement statement;
	private static ResultSet resultSet;
	
	public static void main(String[] args) {
		try {
			logger = Logger.getLogger("EjemploLogger");
			logger.addHandler(new FileHandler("EjemploLogger.xml"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String sent = "";
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:test2.db");
			statement = connection.createStatement();
			sent = "create table Usuario(nick STRING, pass STRING )";
			logger.log(Level.INFO, "BD: " + sent);
			statement.executeUpdate(sent);
		} catch (ClassNotFoundException | SQLException e) {}
		sent = "select * from Usuario where nick = 'admin'";
		logger.log(Level.INFO, "BD: " + sent);
		try {
			resultSet = statement.executeQuery(sent);
			if (!resultSet.next()) {
				sent = "insert into Usuario (nick, pass) values ('admin', 'admin')";
				logger.log(Level.INFO, "BD: " + sent);
				statement.executeQuery(sent);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editarUsuarios();
		
//		statement.close();
//		connection.close();
//		resultSet.close();
//	
		
	}
	
	private static JTextField tfUsuario = new JTextField( "", 10 );
	private static JTextField tfPassword = new JTextField( "", 10 );
	private static DefaultTableModel modelo;
	private static JTable tabla;
	private static void editarUsuarios() {
//		modelo.addColumn(new String[] {"nick", "pass"}); 
		// Vamos a trabajar con vectores
		Vector<String> cabeceras = new Vector<String>();
		cabeceras.add("nick");
		cabeceras.add("pass");
		modelo = new DefaultTableModel(
				new Vector<Vector<Object>>(), // Datos de la jtable (vector de vectores)
				cabeceras // Cabeceras de la tabla
		);
		tabla = new JTable(modelo);
		// Ventana rápida
		JFrame ventana = new JFrame("Añadir usuarios");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panelSuperior = new JPanel();
		JPanel panel = new JPanel();
		panel.add(new JLabel("Nick: ")); panel.add(tfUsuario);
		panel.add(new JLabel("Pass: ")); panel.add(tfPassword);
		panelSuperior.add(panel, BorderLayout.NORTH);
		panel = new JPanel();
		JButton botonAnyadir = new JButton("Añadir usuario");
		panel.add(botonAnyadir);
		JButton botonBorrar = new JButton("Borrar nick");
		panel.add(botonBorrar);
		JButton botonModificar = new JButton("Modificar pass");
		panel.add(botonModificar);
		panelSuperior.add(panel, BorderLayout.SOUTH);
		ventana.add(panelSuperior, BorderLayout.NORTH);
		ventana.add(new JScrollPane(tabla), BorderLayout.CENTER);
		ventana.pack();
		ventana.setLocation(0, 0);
		ventana.setVisible(true);
		
		//Listeners de los botones
		botonAnyadir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfUsuario.getText().trim().isEmpty() && !tfPassword.getText().trim().isEmpty()) {
					String sent = "";
					try {
						sent = "select * from Usuario where nick = '" + tfUsuario.getText() + "'";
						logger.log(Level.INFO, "BD: " + sent);
						resultSet = statement.executeQuery(sent);
						if (!resultSet.next()) {
							sent = "insert into Usuario (nick, pass) values ('" + tfUsuario.getText() + "', '" + tfPassword.getText() + "')";
							logger.log(Level.INFO, "BD: " + sent);
							int val = statement.executeUpdate(sent); // Devuelve el número de líneas afectadas por la condición
							if (val != 1) {
								JOptionPane.showMessageDialog(ventana, "Error en inserción");
							}
						} else {
							JOptionPane.showMessageDialog(ventana, "Usuario " + tfUsuario.getText() + " ya existe");
						}
					} catch (SQLException e1) {
						System.out.println("Último comando: " + sent);
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(ventana, "Debes rellenar los dos campos");
				}
				actualizaTabla(); // Actualizar la tabla en la BD
			}
		});
		
		botonBorrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!tfUsuario.getText().trim().isEmpty() && !tfPassword.getText().trim().isEmpty()) {
					String sent = "";
					try {
						sent = "delete from Usuario where nick = '" + secu(tfUsuario.getText()) + "'";
						logger.log(Level.INFO, "BD: " + sent);
						statement.executeUpdate(sent);
					} catch (SQLException e1) {
						System.out.println("Último comando: " + sent);
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(ventana, "Debes rellenar los dos campos");
				}
				actualizaTabla();
			}
		});
		
		botonModificar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// update Usuario set pass = 'valor1' where nick = 'valor2'
				String sent = "update Usuario set pass = '" + secu(tfPassword.getText()) +
						"' where nick = '" + secu(tfUsuario.getText()) + "'";
				logger.log(Level.INFO, "BD: " + sent);
				try {
					statement.executeUpdate(sent);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				actualizaTabla();
				
			}
		});
		
		// listener de la ventana en sí
		ventana.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e2) {
					// TODO: handle exception
				}
			}
			
		});
		actualizaTabla();
		
	}
	
	private static void actualizaTabla() {
		String comando = "";
		try {
			while (modelo.getRowCount() > 0) modelo.removeRow(0); // Vacía el modelo para volverlo a cargar de la bd
			comando = "select * from Usuario";
			logger.log(Level.INFO, "BD: " + comando);
			resultSet = statement.executeQuery(comando);
			while (resultSet.next()) {
				String nick = resultSet.getString("nick");
				String pass = resultSet.getString("pass");
				Vector<String> fila = new Vector<>();
				fila.add(nick); fila.add(pass);
				modelo.addRow(fila);
			}
			tabla.repaint();
		} catch (SQLException e) {
			System.out.println("Último comando: " + comando);
			e.printStackTrace();
		}
	}
	
	private static String secu(String sqlIncial) {
		return sqlIncial.replaceAll("'", "''");
	}

}
