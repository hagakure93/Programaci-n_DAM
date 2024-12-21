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
        StringBuilder feedback = new StringBuilder(); // Usa StringBuilder para construir la palabra coloreada
        boolean[] processedSecret = new boolean[WORD_LENGTH]; // Controla las letras ya procesadas
        
        // Iterar solo una vez para verificar las posiciones exactas y las letras presentes
        for (int i = 0; i < WORD_LENGTH; i++) {
            char currentChar = guess.charAt(i);
    
            // Verificar si la letra está en la misma posición (verde)
            if (currentChar == secretWord.charAt(i)) {
                feedback.append(applyColor(String.valueOf(currentChar), ANSI_GREEN));
                processedSecret[i] = true; // Marca la letra como procesada
            }
            // Si no está en la misma posición, pero está en alguna otra (amarillo)
            else if (!processedSecret[i]) {
                boolean found = false;
                // Buscar la letra en otras posiciones de la palabra secreta
                for (int j = 0; j < WORD_LENGTH; j++) {
                    if (!processedSecret[j] && currentChar == secretWord.charAt(j)) {
                        feedback.append(applyColor(String.valueOf(currentChar), ANSI_YELLOW));
                        processedSecret[j] = true; // Marca la letra como procesada
                        found = true;
                        break;
                    }
                }
    
                // Si no se encuentra la letra en ninguna otra posición (rojo)
                if (!found) {
                    feedback.append(applyColor(String.valueOf(currentChar), ANSI_RED));
                }
            }
        }
    
        return feedback.toString();
    }
    
    

}
