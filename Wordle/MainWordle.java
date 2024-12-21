package Wordle;

public class MainWordle {
    public static void main(String[] args) {
        // Crear una instancia de WordleGame con las palabras del archivo
        String[] fileWords = {
            "pieza", "cinco", "cesar", "tabla", "raton", "pizza"
        };

        WordleGame game = new WordleGame(fileWords);

        // Iniciar el juego
        game.start();
    }
}

// Me queda añadir colores, crear el fileReader cuando sepa hacerlo y si puedo añadir bordes