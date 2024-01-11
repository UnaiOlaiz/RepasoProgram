package C2EstructurasDeDatos;

import java.util.Comparator;
import java.util.TreeSet;

public class EjemploTreeSet {
	
	public static void main(String[] args) {
		
		// MÉTODO COMPARABLE / COMPARETO orden ascendente
		// El treeSet ya te ordena los objetos según el parametro metido
		// en la clase dentro del método compareTo()
		TreeSet<Persona> personas = new TreeSet<>();
		
		personas.add(new Persona("Juan", 25));
        personas.add(new Persona("Maria", 30));
        personas.add(new Persona("Carlos", 20));
        personas.add(new Persona("Unai", 20));
        personas.add(new Persona("Luisa", 28));
        
        for (Persona persona : personas) {
        	System.out.println(persona);
        }
        
        // MÉTODO COMPARATOR ASCENDENTE
        Comparator<Persona> comparador = Comparator.comparingInt(Persona::getEdad)
        		.thenComparing(Persona::getNombre);
        
        // MÉTODO COMPARATOR DESCENDENTE
        Comparator<Persona> comparador2 = Comparator
        		.comparingInt(Persona::getEdad).reversed()
        		.thenComparing(Persona::getNombre).reversed();
        
//        TreeSet<Persona> personas2 = new TreeSet<>(comparador);
        TreeSet<Persona> personas2 = new TreeSet<>(comparador2 );
        
        personas2.add(new Persona("Juan", 25));
        personas2.add(new Persona("Maria", 30));
        personas2.add(new Persona("Carlos", 20));
        personas2.add(new Persona("Unai", 20));
        personas2.add(new Persona("Luisa", 28));
        
        for (Persona persona : personas2) {
        	System.out.println(persona);
        }
	}
    
    

}
