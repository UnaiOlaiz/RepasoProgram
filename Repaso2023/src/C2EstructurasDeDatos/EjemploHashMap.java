package C2EstructurasDeDatos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EjemploHashMap {

    // Supongamos que tienes una lista de reparaciones y un mapa por especialista
    private List<String> reparaciones = new ArrayList<>(); // Suponiendo que cada elemento es una descripción simple
    private Map<String, List<String>> reparacionesPorEspecialista = new HashMap<>();

    // Método para agregar algunas reparaciones de ejemplo
    public void agregarReparacionesDeEjemplo() {
        reparaciones.add("Reparación de motor");
        reparaciones.add("Reparación de frenos");
        reparaciones.add("Reparación de transmisión");
    }

    // Método para recargar el mapa
    public void recargarMapa() {
        this.reparacionesPorEspecialista.clear(); // Limpiamos el mapa existente

        for (String descripcionReparacion : reparaciones) {
            String especialista = obtenerEspecialista(descripcionReparacion); // Suponiendo una lógica simple para obtener el especialista

            // Si el mapa no contiene al especialista, agregamos una nueva lista para ese especialista
            if (!reparacionesPorEspecialista.containsKey(especialista)) {
                reparacionesPorEspecialista.put(especialista, new ArrayList<>());
            }

            // Agregamos la descripción de la reparación a la lista correspondiente del especialista en el mapa
            this.reparacionesPorEspecialista.get(especialista).add(descripcionReparacion);
        }
    }

    // Método simple para obtener un especialista basado en la descripción de la reparación (para el ejemplo)
    private String obtenerEspecialista(String descripcionReparacion) {
        // Suponiendo una lógica simple: si contiene "motor", el especialista es "Mecánico"; si contiene "frenos", es "Especialista en Frenos"; de lo contrario, es "Generalista"
        if (descripcionReparacion.contains("motor")) {
            return "Mecánico";
        } else if (descripcionReparacion.contains("frenos")) {
            return "Especialista en Frenos";
        } else {
            return "Generalista";
        }
    }

    // Método para mostrar el mapa recargado (solo para propósitos de prueba)
    public void mostrarMapaRecargado() {
        System.out.println("Mapa recargado:");
        for (Map.Entry<String, List<String>> entry : reparacionesPorEspecialista.entrySet()) {
            String especialista = entry.getKey();
            List<String> listaReparaciones = entry.getValue();
            System.out.println("Especialista: " + especialista + ", Reparaciones: " + listaReparaciones);
        }
    }

    public static void main(String[] args) {
        EjemploHashMap tuClase = new EjemploHashMap();
        tuClase.agregarReparacionesDeEjemplo();
        tuClase.recargarMapa();
        tuClase.mostrarMapaRecargado();
    }
}
