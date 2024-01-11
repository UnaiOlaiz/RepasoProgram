package C2EstructurasDeDatos;

public class Persona implements Comparable<Persona>{
	private String nombre;
	private int edad;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Persona(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
	}
	@Override
	public int compareTo(Persona otraPersona) {
		// son iguales -> 1
		// no son iguales -> 0
		
//		// Orden ascendente
//		int comparacionPorEdad = Integer.compare(this.edad, otraPersona.getEdad());
//		if (comparacionPorEdad !=0) { // En el caso que sean iguales
//			return comparacionPorEdad;
//		}
//		return this.nombre.compareTo(otraPersona.getNombre());
		
		// Orden descendente
		int comparacinPorEdad = Integer.compare(otraPersona.getEdad(), this.edad);
		if (comparacinPorEdad != 0) { // Checkeamos si no son desiguales
			return comparacinPorEdad;
		}
		return otraPersona.getNombre().compareTo(this.nombre);
	}
	
	
}
