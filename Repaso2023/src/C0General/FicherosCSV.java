package C0General;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

public class FicherosCSV {

	public static class Persona implements Serializable {
		private String nombre;
		private int edad;
		private String direccion;
		@Override
		public String toString() {
		    return nombre + "," + edad + "," + direccion;
		}
		public Persona(String nombre, int edad, String direccion) {
			super();
			this.nombre = nombre;
			this.edad = edad;
			this.direccion = direccion;
		}
	}
	
	// Guardar objeto en fichero CSV
	public static void guardarObjetoCSV(String rutaArchivo, Persona persona) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(rutaArchivo)) {
			writer.println(persona.toString());
		}
	}
	
	// Cargar objeto desde fichero CSV
	public static Persona cargarObjetoCSV(String rutaArchivo) throws IOException {
		String linea;
		try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
			linea = reader.readLine();
		}
		
		
		// También se puede hacer mediante la clase Scanner
//		Scanner sc = new Scanner(new FileReader( "archivo.csv"));
//		String linea;
//		while(sc.hasNextLine()) {
//			linea = sc.nextLine();
//			String [] partes = linea.split(";");
//			String nombre = partes[0];
//			int edad = Integer.parseInt(partes[1]);
//			String direccion = partes[2];
//			...
//		}
		
		if (linea == null) {
	        throw new IOException("El archivo está vacío o no contiene datos válidos.");
		}
		
		String[] partes = linea.split(",");
		if (partes.length < 3) { // El 3 es la cantidad de atributos de la clase
	        throw new IOException("El formato del archivo CSV no es válido.");
		}
		String nombre = partes[0];
		int edad = Integer.parseInt(partes[1]);
		String direccion = partes[2];
		return new Persona(nombre, edad, direccion);
		
		
	}
	
	public static void main(String[] args) throws IOException {
		Persona persona = new Persona("Juan", 30, "Calle Falsa 123");
		guardarObjetoCSV("personas.csv", persona);
		System.out.println("Guardado en el fichero CSV exitosamente");
		
		Persona personaCargada = cargarObjetoCSV("personas.csv");
		System.out.println("Objeto cargado desde CSV: " + persona);
	}
}
