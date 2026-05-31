import java.util.Random;
import java.util.Scanner;

public class MathChallenge {
    
    // Método que genera el reto y devuelve true si se acierta, o false si se falla
    public boolean solveChallenge() {
        Random rand = new Random();
        // Generamos dos números aleatorios del 1 al 10
        int num1 = rand.nextInt(10) + 1;
        int num2 = rand.nextInt(10) + 1;
        int expectedAnswer = num1 + num2;

        System.out.println("\n[RETO MATEMÁTICO] Para apagar la alarma, resuelve la siguiente suma:");
        System.out.print("¿Cuánto es " + num1 + " + " + num2 + "? -> ");

        // Usamos Scanner para leer lo que el usuario escribe en la consola
        Scanner scanner = new Scanner(System.in);
        
        try {
            int userAnswer = scanner.nextInt();
            
            if (userAnswer == expectedAnswer) {
                System.out.println("[ÉXITO] ¡Respuesta correcta! Cerebro activado.");
                return true;
            } else {
                System.out.println("[FALLO] Respuesta incorrecta. Vuelve a la cama o inténtalo de nuevo.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("[ERROR] No has introducido un número válido.");
            return false;
        }
    }
}