import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SISTEMA DE DESPERTADOR INTELIGENTE ===\n");

        // 1. Inicializar el Gestor de Alarmas
        AlarmManager manager = new AlarmManager();

        // 2. Crear perfiles de sonido
        SoundProfile radarSound = new SoundProfile("Radar (Clásico)", 8);
        SoundProfile zenSound = new SoundProfile("Pájaros Zen", 5);

        // 3. Crear un par de alarmas
        // Alarma de trabajo (Lunes a Viernes a las 07:00)
        Alarm workAlarm = new Alarm(
            "Despertar para trabajar",
            LocalTime.of(7, 0),
            Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
            radarSound
        );

        // Alarma de fin de semana (Sábados a las 09:30)
        Alarm weekendAlarm = new Alarm(
            "Ir al gimnasio",
            LocalTime.of(9, 30),
            Arrays.asList(DayOfWeek.SATURDAY),
            zenSound
        );

        // 4. Añadir las alarmas al gestor
        manager.addAlarm(workAlarm);
        manager.addAlarm(weekendAlarm);

        // 5. Mostrar todas las alarmas guardadas
        manager.listAllAlarms();

        // 6. --- SIMULACIÓN 1: Llega la hora de trabajar ---
        System.out.println("--- SIMULANDO: Lunes a las 07:00 ---");
        LocalTime simulatedTime = LocalTime.of(7, 0);
        DayOfWeek simulatedDay = DayOfWeek.MONDAY;
        
        // El reloj comprueba si hay alarmas para esa hora
        manager.checkAndTriggerAlarms(simulatedTime, simulatedDay);

        // 7. --- INTERACCIÓN: El usuario pospone la alarma ---
        System.out.println("\n--- ACCIÓN: Usuario pospone 10 minutos ---");
        workAlarm.snooze(10);

        // 8. --- SIMULACIÓN 2: Pasan 10 minutos ---
        System.out.println("\n--- SIMULANDO: Lunes a las 07:10 ---");
        simulatedTime = LocalTime.of(7, 10);
        
        manager.checkAndTriggerAlarms(simulatedTime, simulatedDay);

        // 9. --- INTERACCIÓN: El usuario la apaga definitivamente ---
        System.out.println("\n--- ACCIÓN: Usuario detiene la alarma ---");
        workAlarm.stop();

        System.out.println("\n=== FIN DE LA SIMULACIÓN BASE ===");


        // --- SIMULACIÓN MODO VACACIONES ---
        System.out.println("\n--- ACCIÓN: El usuario se va de vacaciones ---");
        manager.toggleVacationMode(); // Lo activamos

        System.out.println("\n--- SIMULANDO: Siguiente alarma en vacaciones ---");
        // Intentamos simular la alarma de los lunes otra vez
        manager.checkAndTriggerAlarms(LocalTime.of(7, 0), DayOfWeek.MONDAY); 
        
        System.out.println("\n--- ACCIÓN: El usuario vuelve de vacaciones ---");
        manager.toggleVacationMode(); // Lo desactivamos

    }
}

