package C0General;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CargarYGuardarFicherosDeTexto {

    // Método para guardar un archivo de texto
    public static void saveTextFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    // Método para cargar un archivo de texto
    public static String loadTextFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static void main(String[] args) {
        try {
            String textContent = "Hola, este es un ejemplo de contenido para guardar en un archivo.";
            saveTextFile("texto.txt", textContent);
            System.out.println("Archivo guardado exitosamente.");

            String loadedContent = loadTextFile("texto.txt");
            System.out.println("Contenido cargado: \n" + loadedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
