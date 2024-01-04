package C0General;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CargarYGuardarFicherosBinarios {
	
	static class Persona implements Serializable {
		private String nombre;
		private int edad;
		
		@Override
		public String toString() {
			return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
		}

		public Persona(String nombre, int edad) {
			super();
			this.nombre = nombre;
			this.edad = edad;
		}
		
		
	}
	
	// Guardar un objeto binario
	public static void guardarFicheroBinario(String rutaArchivo, Persona persona) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo));
			oos.writeObject(persona);
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	// Cargar objeto binario
    public static Persona cargarFicheroBinario(String rutaArchivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Persona) ois.readObject();
        }
    }
    
    public static void main(String[] args) {
		try {
			Persona persona = new Persona("Juan", 30);
			guardarFicheroBinario("persona.bin", persona);
			System.out.println("Objeto guardado exitosamente.");
			
			Persona persona2 = cargarFicheroBinario("persona.bin");
			System.out.println("Objeto cargado: " + persona2);
		}catch (IOException | ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
