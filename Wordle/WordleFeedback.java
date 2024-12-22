package Wordle;

public class WordleFeedback {

    private static final int WORD_LENGTH = 5;

    // Códigos ANSI para colores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m"; // Verde
    public static final String ANSI_YELLOW = "\u001B[33m"; // Amarillo
    public static final String ANSI_RED = "\u001B[31m"; // Rojo

    // Método auxiliar para aplicar color a una letra
    private static String applyColor(String letter, String color) {
        return color + letter + ANSI_RESET; // Añade color y reinicia el formato
    }

    // Genera la palabra con colores como feedback
    public static String feedBackString(String guess, String secretWord) {
        StringBuilder feedback = new StringBuilder();
        boolean[] processedSecret = new boolean[WORD_LENGTH]; // Controla las letras ya procesadas en la palabra secreta
    
        // Crear un array para la salida inicial (para que luego podamos modificarlo en las posiciones necesarias)
        String[] output = new String[WORD_LENGTH];
    
        // Primera pasada: Identificar letras en la posición correcta (verde)
        for (int i = 0; i < WORD_LENGTH; i++) {
            char currentChar = guess.charAt(i);
            if (currentChar == secretWord.charAt(i)) {
                output[i] = applyColor(String.valueOf(currentChar), ANSI_GREEN); // Posición correcta (verde)
                processedSecret[i] = true; // Marcar la letra como procesada
            } else {
                output[i] = null; // Marcador temporal para procesar más adelante
            }
        }
    
        // Segunda pasada: Identificar letras presentes en posiciones incorrectas (amarillo) o ausentes (rojo)
        for (int i = 0; i < WORD_LENGTH; i++) {
            if (output[i] != null) {
                continue; // Si ya está verde, saltar
            }
    
            char currentChar = guess.charAt(i);
            boolean found = false;
    
            for (int j = 0; j < WORD_LENGTH; j++) {
                // Comprobar si la letra está en otra posición y no ha sido procesada
                if (!processedSecret[j] && currentChar == secretWord.charAt(j)) {
                    output[i] = applyColor(String.valueOf(currentChar), ANSI_YELLOW); // Posición incorrecta (amarillo)
                    processedSecret[j] = true; // Marcar como procesada
                    found = true;
                    break;
                }
            }
    
            if (!found) {
                output[i] = applyColor(String.valueOf(currentChar), ANSI_RED); // No está en la palabra (rojo)
            }
        }
    
        // Construir el feedback final
        for (String letter : output) {
            feedback.append(letter);
        }
    
        return feedback.toString();
    }
    

}
