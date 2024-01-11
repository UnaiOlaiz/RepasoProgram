package C4BasesDeDatos;

import java.util.logging.Level;

public class LogAriketa {
	
	public static void main(String[] args) {
		try {
			Log miLog = new Log("log.txt");
			
			miLog.logger.setLevel(Level.WARNING);
			miLog.logger.info("Info msg");
			miLog.logger.warning("Warning msg");
			miLog.logger.severe("Severe msg");
		} catch (Exception e) {
		}
	}

}
