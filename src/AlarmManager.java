import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class AlarmManager {
    // Atributo privado: La lista que almacena todas las alarmas en memoria
    private List<Alarm> alarms;

    // Constructor
    public AlarmManager() {
        this.alarms = new ArrayList<>();
    }

    // --- MÉTODOS DE GESTIÓN DE ALARMAS ---

    // Añadir una alarma evitando duplicados exactos
    public void addAlarm(Alarm newAlarm) {
        // Regla de negocio para evitar duplicados: No permitir misma hora en los mismos días
        for (Alarm a : alarms) {
            if (a.getTime().equals(newAlarm.getTime()) && a.getRepeatDays().equals(newAlarm.getRepeatDays())) {
                System.out.println("[ERROR] Ya existe una alarma programada exactamente a las " + newAlarm.getTime() + " para esos días.");
                return;
            }
        }
        alarms.add(newAlarm);
        System.out.println("[MANAGER] Alarma '" + newAlarm.getLabel() + "' añadida correctamente.");
    }

    // Eliminar una alarma mediante su ID único
    public boolean deleteAlarm(String id) {
        for (Alarm a : alarms) {
            if (a.getId().equals(id)) {
                alarms.remove(a);
                System.out.println("[MANAGER] Alarma '" + a.getLabel() + "' eliminada del sistema.");
                return true;
            }
        }
        System.out.println("[MANAGER] No se encontró ninguna alarma con el ID especificado.");
        return false;
    }

    // --- MÉTODOS DE SIMULACIÓN Y COMPROBACIÓN ---

    // Consulta y devuelve solo las alarmas que están activas actualmente
    public List<Alarm> getActiveAlarms() {
        List<Alarm> activeList = new ArrayList<>();
        for (Alarm a : alarms) {
            if (a.isActive()) {
                activeList.add(a);
            }
        }
        return activeList;
    }

    // Comprueba qué alarmas deben sonar en un momento específico (Hora y Día)
    public void checkAndTriggerAlarms(LocalTime currentTime, DayOfWeek currentDay) {
        System.out.println("\n[RELOJ] Comprobando alarmas para las " + currentTime + " del día " + currentDay + "...");
        
        List<Alarm> activeAlarms = getActiveAlarms();
        
        for (Alarm a : activeAlarms) {
            // Comprobamos si coincide la hora (ignorando los segundos)
            boolean sameHour = a.getTime().getHour() == currentTime.getHour();
            boolean sameMinute = a.getTime().getMinute() == currentTime.getMinute();
            
            // Comprobamos si la alarma debe sonar hoy (si es diaria o si el día de hoy está en su lista)
            boolean shouldSoundToday = a.getRepeatDays().isEmpty() || a.getRepeatDays().contains(currentDay);

            if (sameHour && sameMinute && shouldSoundToday) {
                // Si dos alarmas coinciden al mismo tiempo, el bucle las procesa y dispara secuencialmente
                a.trigger();
            }
        }
    }

    // Listar todas las alarmas en el sistema (Para depuración)
    public void listAllAlarms() {
        System.out.println("\n=== LISTA DE ALARMAS EN EL SISTEMA ===");
        if (alarms.isEmpty()) {
            System.out.println("No hay alarmas configuradas.");
            return;
        }
        for (Alarm a : alarms) {
            System.out.println("- [" + (a.isActive() ? "ACTIVA" : "INACTIVA") + "] " 
                    + a.getLabel() + " a las " + a.getTime() 
                    + " | Días: " + (a.getRepeatDays().isEmpty() ? "Todos" : a.getRepeatDays())
                    + " | ID: " + a.getId());
        }
        System.out.println("=======================================\n");
    }
}
