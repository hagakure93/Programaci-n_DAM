package Wordle;

import java.util.Random;
import java.util.Scanner;

public final class WordleGame {

    int MAX_TRIES = 6;
    String[] fileWords;
    String secretWord;
    int RemainingAttemps;
    String[] triesHistory;
    // Hacer que el Array sea un fileReader
    

    public WordleGame(String[] fileWords) { // Acuérdate de cambiar la entrada por un filereader
        this.fileWords = fileWords;
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

                // Leer la palabra
                String palabra = entrada.nextLine();

                // Validar la palabra
                String result = getUserInput(palabra);

                if (result.equals("La palabra debe tener 5 letras. Inténtalo de nuevo.")) {
                    System.out.println(result);
                    continue; // Si la palabra no es válida, solicita un nuevo intento
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
                    return; // Termina el juego
                }
            }

            if (RemainingAttemps == 0) {
                System.out.println("Has perdido. La palabra secreta era: " + secretWord);
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
        for (int i = 0; i < triesHistory.length; i++) {
            if (triesHistory[i] != null) {
                System.out.println((i + 1) + ". " + triesHistory[i]); // Para que no empeice en 0 y se vea más bonito
            }
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
