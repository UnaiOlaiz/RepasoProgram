package C2EstructurasDeDatos;

import java.util.HashSet;

public class EjemploHashSet {
	public static void main(String[] args) {
		HashSet<Producto> productos = new HashSet<>();
		
		productos.add(new Producto("Laptop", 1000));
	    productos.add(new Producto("Teléfono", 500));
	    productos.add(new Producto("Tablet", 300));
	    
	    // Producto duplicado
	    Producto prodDuplicado = new Producto("Laptop", 1000);
        productos.add(prodDuplicado);  // Este producto no se agregará, ya que los HashSet no permiten duplicados basados en la implementación de hashCode y equals

        for (Producto producto : productos) {
        	System.out.println(producto);
        }
        
        System.out.println(productos.contains(new Producto("Teléfono", 500)));
	}
	

}
