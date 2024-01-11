package C4BasesDeDatos;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class EjemploLog {

    // Obtener el logger para esta clase
    private static final Logger LOGGER = Logger.getLogger(EjemploLog.class.getName());

    public static void main(String[] args) {
        // Cargar configuración de logging
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("logging.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error cargando la configuración de logging", e);
        }

        Connection connection = null;
        try {
            // Establecer conexión a la base de datos (por ejemplo, SQLite)
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            LOGGER.info("Conexión establecida correctamente.");

            // Simular una operación de consulta
            // Aquí simplemente imprimimos un mensaje, pero podrías registrar detalles de la consulta real
            LOGGER.info("Ejecutando consulta SQL: SELECT * FROM usuarios");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al establecer la conexión o al ejecutar la consulta", e);
        } finally {
            // Cerrar la conexión
            if (connection != null) {
                try {
                    connection.close();
                    LOGGER.info("Conexión cerrada correctamente.");
                } catch (SQLException e) {
                    LOGGER.log(Level.SEVERE, "Error al cerrar la conexión", e);
                }
            }
        }
    }
}
