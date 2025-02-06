package Wordle;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainWordle {
    public static void main(String[] args) {
        ArrayList<String> palabras = new ArrayList<>();
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in); // No puedo cerrar el scanner o falla
        System.out.println("Elige sobre qué tema te gustaría adivinar la palabra: ");
        System.out.println("1 - Programación");
        System.out.println("2 - Videojuegos");
        System.out.print("Opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        String archivo;
        switch (opcion) {
            case 1 -> archivo = "Palabras.txt"; // Archivo de programación
            case 2 -> archivo = "Palabras2.txt"; // Archivo de videojuegos
            default -> {
                System.out.println("Opción inválida. Se usará el archivo por defecto (Programación).");
                archivo = "Palabras.txt";
            }
        }

        
        String ruta = "/Users/hagakure/Documents/DAM/Asignaturas/Programación/TrabajoEnfoque/Wordle/Wordle/" + archivo;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String ch;
            while ((ch = br.readLine()) != null) {
                palabras.add(ch.trim());
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        if (palabras.isEmpty()) {
            System.out.println("Error: El archivo está vacío.");
            return;
        }

        // Convertimos el ArrayList a un Array de Strings
        String[] fileWords = palabras.toArray(String[]::new);

        // Pasamos el array a la clase WordleGame
        WordleGame game = new WordleGame(fileWords);

        // Iniciar el juego
        game.start();
    }
}
