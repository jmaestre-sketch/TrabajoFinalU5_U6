import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DE DESPERTADOR INTELIGENTE ===\n");

        AlarmManager manager = new AlarmManager();
        SoundProfile radarSound = new SoundProfile("Radar (Clásico)", 8);

        // Alarma de prueba para el lunes a las 07:00
        Alarm testAlarm = new Alarm(
            "Despertador Diario",
            LocalTime.of(7, 0),
            Arrays.asList(DayOfWeek.MONDAY),
            radarSound
        );
        manager.addAlarm(testAlarm);

        // --- SIMULACIÓN DE MAÑANA COMPLETA ---
        System.out.println("\n--- [07:00] La alarma suena por primera vez ---");
        manager.checkAndTriggerAlarms(LocalTime.of(7, 0), DayOfWeek.MONDAY);

        System.out.println("\n--- [07:00] Acción: El usuario pulsa Snooze ---");
        testAlarm.snooze(5); // Primer snooze

        System.out.println("\n--- [07:05] Acción: El usuario vuelve a pulsar Snooze ---");
        testAlarm.snooze(5); // Segundo snooze (¡Ya van 2!)

        System.out.println("\n--- [07:10] La alarma vuelve a sonar ---");
        manager.checkAndTriggerAlarms(LocalTime.of(7, 10), DayOfWeek.MONDAY);

        System.out.println("\n--- [07:10] Acción: El usuario intenta apagarla ---");
        testAlarm.stop(); // Esto pedirá resolver el reto matemático y mostrará el reporte de sueño
    }
}
