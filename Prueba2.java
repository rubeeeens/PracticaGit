package elahorcadodaw_rubensalido_albertovidal;

import java.util.Scanner;
import java.util.Random;

public class ElAhorcadoDAW_RubenSalido_AlbertoVidal {

    static Scanner datos = new Scanner(System.in);
    static Random random = new Random();
    static String[] palabras = {
        "abecedario", "baloncesto", "caminante", "dinosaurio", "elefante",
        "fantasia", "guitarra", "huracan", "iglu", "jirafa",
        "kiwi", "leon", "melodia", "naranja", "oasis",
        "paraguas", "quimera", "resplandor", "sabado", "telescopio",
        "umbrales", "vibracion", "waffle", "xilofono", "yoga",
        "zigzag", "angel", "buho", "cefiro", "debil",
        "ebano", "fosforo", "genesis", "habitat", "icaro",
        "jubilo", "karate", "limite", "magico", "nubil",
        "opera", "pajaro", "quejate", "rapido", "solido",
        "tandem", "ultimo", "vortice", "western", "xenofobia",
        "yogur", "zanahoria", "arbol", "bolido", "codigo",
        "datil", "epoca", "futbol", "geiser", "horrido",
        "indigo", "jubilo", "karate", "limite", "magico",
        "nubil", "opera", "pajaro", "quejate", "rapido",
        "solido", "tandem", "ultimo", "vortice", "western",
        "xenofobia", "yogur", "zanahoria", "arbol", "bolido",
        "codigo", "datil", "epoca", "futbol", "geiser",
        "horrido", "indigo", "jubilo", "karate", "limite"
    };
    static String palabraSecreta;
    static char[] palabraOculta;
    static int vidas = 6;
    static char[] letrasFalladas;

    static String linea1 = "______";
    static String linea2 = "||   |";
    static String linea3 = "||";
    static String linea4 = "=========";

    static String vida1 = "||   O";
    static String vida2 = "||   |";
    static String vida3 = "||  /|";
    static String vida4 = "||  /|\\";
    static String vida5 = "||  / ";
    static String vida6 = "||  / \\";

    public static void inicializarPalabraOculta() {
        palabraOculta = new char[palabraSecreta.length()];
        for (int i = 0; i < palabraOculta.length; i++) {
            palabraOculta[i] = '_';
        }
    }

    public static void imprimirPalabraOculta() {
        for (char letra : palabraOculta) {
            System.out.print(letra + " ");
        }
        System.out.println();
    }

    public static void dibujarAhorcado() {
        System.out.println(linea1);
        System.out.println(linea2);

        switch (vidas) {
            case 6 -> {
                System.out.println(linea3);
                System.out.println(linea3);
                System.out.println(linea3);
                System.out.println(linea3);
            }
            case 5 -> {
                System.out.println(vida1);
                System.out.println(linea3);
                System.out.println(linea3);
                System.out.println(linea3);
            }
            case 4 -> {
                System.out.println(vida1);
                System.out.println(vida2);
                System.out.println(linea3);
                System.out.println(linea3);
            }
            case 3 -> {
                System.out.println(vida1);
                System.out.println(vida3);
                System.out.println(linea3);
                System.out.println(linea3);
            }
            case 2 -> {
                System.out.println(vida1);
                System.out.println(vida4);
                System.out.println(linea3);
                System.out.println(linea3);
            }
            case 1 -> {
                System.out.println(vida1);
                System.out.println(vida4);
                System.out.println(vida5);
                System.out.println(linea3);
            }
            case 0 -> {
                System.out.println(vida1);
                System.out.println(vida4);
                System.out.println(vida6);
                System.out.println(linea3);
            }
            default -> {
                System.out.println("Has perdido todas las vidas!");
            }
        }

        System.out.println(linea4);
    }

    public static void comprobarLetra(char letraPedida) {
        boolean letraEncontrada = false;

        // Verificar si la letra ya fue fallada antes
        if (letrasFalladas == null || !letraYaFallada(letraPedida)) {
            for (int i = 0; i < palabraSecreta.length(); i++) {
                if (letraPedida == palabraSecreta.charAt(i)) {
                    palabraOculta[i] = letraPedida;
                    letraEncontrada = true;
                }
            }
            if (!letraEncontrada) {
                guardarLetraFallada(letraPedida);
                vidas--;
            }
        } else {
            System.out.println("Ya has fallado la letra " + letraPedida + ". Intenta con otra letra.");
        }
    }

    public static boolean letraYaFallada(char letra) {
        if (letrasFalladas != null) {
            for (char letraFallada : letrasFalladas) {
                if (letra == letraFallada) {
                    return true; 
                }
            }
        }
        return false; 
    }

    public static void guardarLetraFallada(char letra) {
        if (letrasFalladas == null) {
            letrasFalladas = new char[1];
            letrasFalladas[0] = letra;
        } else {
            char[] nuevoArray = new char[letrasFalladas.length + 1];
            System.arraycopy(letrasFalladas, 0, nuevoArray, 0, letrasFalladas.length);
            nuevoArray[letrasFalladas.length] = letra;
            letrasFalladas = nuevoArray;
        }
    }

    public static void mostrarLetrasFalladas(char[] letrasFalladas) {
        if (letrasFalladas != null && letrasFalladas.length > 0) {
            System.out.print("Letras falladas: ");
            for (char letra : letrasFalladas) {
                System.out.print(letra + " ");
            }
            System.out.println();
        }
    }

    public static void contadores(int vidas) {
        System.out.println("Vidas restantes: " + vidas);
    }

    public static void resolverPalabra() {
        System.out.print("Intenta adivinar la palabra completa: ");
        String intento = datos.next();

        if (intento.equalsIgnoreCase(palabraSecreta)) {
            System.out.println("Has adivinado la palabra!");
        } else {
            System.out.println("Palabra incorrecta! Pierdes todas las vidas.");
            vidas = 0;
        }
    }

    public static void seleccionarPalabraAleatoria() {
        int indicePalabra = random.nextInt(palabras.length);
        palabraSecreta = palabras[indicePalabra];
    }

    public static void jugarPartida() {
        vidas = 6;
        letrasFalladas = null;
        seleccionarPalabraAleatoria();
        inicializarPalabraOculta();

        while (vidas > 0 && String.valueOf(palabraOculta).contains("_")) {
            System.out.println("-------------------");
            dibujarAhorcado(); 

            mostrarLetrasFalladas(letrasFalladas);
            imprimirPalabraOculta(); 
            System.out.println("Introduce una letra para jugar");
            System.out.println("o escribe Resolver para resolver la palabra: ");
            String opcionResolver = datos.next().toUpperCase(); 

            if (opcionResolver.equals("resolver")) {
                resolverPalabra();
                break;
            } else if (opcionResolver.length() == 1) {
                char letraPedida = opcionResolver.charAt(0);
                comprobarLetra(letraPedida);
                contadores(vidas);
            } else {
                System.out.println("Entrada no válida. Por favor, introduce una letra o escribe 'Resolver'.");
            }
            System.out.println("-------------------");

        }

        System.out.println();
        dibujarAhorcado();

        if (vidas == 0) {
            System.out.println("Has perdido! La palabra correcta era: " + palabraSecreta);
        } else {
            System.out.println("Felicidades! Has adivinado la palabra: " + palabraSecreta);
        }
    }

    public static void jugarAlMejorDe3() {
        int partidasGanadas = 0;
        int partidasJugadas = 0;

        do {
            jugarPartida();
            partidasJugadas++;

            if (vidas > 0) {
                partidasGanadas++;
                System.out.println("Has ganado la partida " + partidasJugadas + "!");
            } else {
                System.out.println("Has perdido la partida " + partidasJugadas + ".");
            }

            System.out.println("Llevas " + partidasGanadas + " de " + partidasJugadas + " partidas ganadas.");

            if (partidasGanadas < 3) {
                System.out.print("¿Deseas jugar otra partida? (s/n): ");
            }
        } while (datos.next().equalsIgnoreCase("s") && partidasGanadas < 3);

        if (partidasGanadas == 3) {
            System.out.println("Felicidades! Has ganado al mejor de 3 partidas.");
        } else {
            System.out.println("Gracias por jugar! Hasta luego!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Bienvenido al juego del Ahorcado!");
        dibujarAhorcado();
        do {
            seleccionarPalabraAleatoria();
System.out.println(palabraSecreta);
            System.out.println("Elige el modo de juego:");
            System.out.println("1. Clásico");
            System.out.println("2. Al mejor de 3");
            System.out.print("Selecciona el modo (1 o 2): ");

            int modoJuego = datos.nextInt();

            switch (modoJuego) {
                case 1 ->
                    jugarPartida();
                case 2 ->
                    jugarAlMejorDe3();
                default ->
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }

            System.out.print("¿Deseas jugar de nuevo? (s/n): ");
        } while (datos.next().equalsIgnoreCase("s"));

        System.out.println("Gracias por jugar! Hasta luego!");
    }
}
