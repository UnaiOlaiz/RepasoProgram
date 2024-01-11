package C4BasesDeDatos;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
	public Logger logger;
	FileHandler fileHandler;
	
	public Log(String nombreArchivo) throws IOException {
		File f = new File(nombreArchivo);
		if (!f.exists()) {
			f.createNewFile();
		}
		fileHandler = new FileHandler(nombreArchivo, true);
		logger = Logger.getLogger("test");
		logger.addHandler(fileHandler);
		SimpleFormatter formatter= new SimpleFormatter();
		fileHandler.setFormatter(formatter);
	}

}
