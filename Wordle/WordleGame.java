package Wordle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public final class WordleGame {

    int MAX_TRIES = 6;
    String secretWord;
    int RemainingAttemps;
    String[] triesHistory;

    public WordleGame(String[] fileWords) {
        this.secretWord = selectRandomWord(fileWords);
        this.RemainingAttemps = MAX_TRIES;
        this.triesHistory = new String[MAX_TRIES];

    }

    public void start() {
        try (Scanner entrada = new Scanner(System.in)) {
            System.out.println("Comienza el juego!");

            while (RemainingAttemps > 0) {
                System.out.println("Tienes " + RemainingAttemps + " intentos restantes.");
                System.out.println("Introduce tu intento (una palabra de 5 letras):");

                String palabra = entrada.nextLine();

                String result = getUserInput(palabra);

                if (result.equals("La palabra debe tener 5 letras. Inténtalo de nuevo.")) {
                    System.out.println(result);
                    continue;
                }

                // Almacenar el intento en el array
                triesHistory[MAX_TRIES - RemainingAttemps] = palabra;
                RemainingAttemps--;

                // Mostrar el feedback coloreado
                String feedback = WordleFeedback.feedBackString(palabra, secretWord);
                System.out.println(feedback);

                // Comprobar si el jugador ha adivinado la palabra secreta
                if (palabra.equals(secretWord)) {
                    System.out.println("¡Felicidades! Has adivinado la palabra secreta.");
                    showTriesHistory();
                    return; // Termina el juego
                }
            }

            if (RemainingAttemps == 0) {
                System.out.println("Has perdido. La palabra secreta era: " + secretWord);
                showTriesHistory();
            }
        }
    }

    public String getUserInput(String palabra) {
        if (palabra.length() == 5) {
            return palabra; // Si la palabra tiene 5 letras, es válida, la devuelve.
        } else {
            return "La palabra debe tener 5 letras. Inténtalo de nuevo."; // Si no es válida, retorna el mensaje de
                                                                          // error.
        }
    }

    public void showTriesHistory() {
        System.out.println("Historial de intentos:");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("historial_wordle.txt"))) {
            for (int i = 0; i < triesHistory.length; i++) {
                if (triesHistory[i] != null) {
                    String intento = (i + 1) + ". " + triesHistory[i];
                    System.out.println(intento);
                    writer.write(intento);
                    writer.newLine(); // Salto de línea en el archivo
                }
            }
            System.out.println("Historial guardado en 'historial_wordle.txt'.");
        } catch (IOException e) {
            System.out.println("Error al guardar el historial en el archivo.");
        }
    }

    public String selectRandomWord(String[] fileWords) {
        if (fileWords == null || fileWords.length == 0) {
            throw new IllegalArgumentException("El array de palabras no puede estar vacío.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(fileWords.length); // .
        return fileWords[randomIndex];
    }

}
