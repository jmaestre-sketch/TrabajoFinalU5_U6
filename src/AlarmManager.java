import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class AlarmManager {
    private List<Alarm> alarms;
    // NUEVO ATRIBUTO: Estado global del modo vacaciones
    private boolean isVacationModeActive;

    // Constructor actualizado
    public AlarmManager() {
        this.alarms = new ArrayList<>();
        this.isVacationModeActive = false; // Por defecto, no estamos de vacaciones
    }

    // NUEVO MÉTODO: Activar/Desactivar modo vacaciones
    public void toggleVacationMode() {
        this.isVacationModeActive = !this.isVacationModeActive;
        System.out.println("[VACACIONES] El Modo Vacaciones ahora está " 
                + (isVacationModeActive ? "ACTIVADO (Alarmas suspendidas)" : "DESACTIVADO (Alarmas normales)"));
    }

    // --- MÉTODOS DE GESTIÓN DE ALARMAS ---

    public void addAlarm(Alarm newAlarm) {
        for (Alarm a : alarms) {
            if (a.getTime().equals(newAlarm.getTime()) && a.getRepeatDays().equals(newAlarm.getRepeatDays())) {
                System.out.println("[ERROR] Ya existe una alarma programada exactamente a las " + newAlarm.getTime() + " para esos días.");
                return;
            }
        }
        alarms.add(newAlarm);
        System.out.println("[MANAGER] Alarma '" + newAlarm.getLabel() + "' añadida correctamente.");
    }

    public boolean deleteAlarm(String id) {
        for (Alarm a : alarms) {
            if (a.getId().equals(id)) {
                alarms.remove(a);
                System.out.println("[MANAGER] Alarma '" + a.getLabel() + "' eliminada del sistema.");
                return true;
            }
        }
        return false;
    }

    public List<Alarm> getActiveAlarms() {
        List<Alarm> activeList = new ArrayList<>();
        for (Alarm a : alarms) {
            if (a.isActive()) {
                activeList.add(a);
            }
        }
        return activeList;
    }

    // MÉTODO MODIFICADO: Ahora comprueba si estamos de vacaciones antes de hacer sonar nada
    public void checkAndTriggerAlarms(LocalTime currentTime, DayOfWeek currentDay) {
        System.out.println("\n[RELOJ] Comprobando alarmas para las " + currentTime + " del día " + currentDay + "...");
        
        // REGLA DE NEGOCIO AVANZADA: Si el modo vacaciones está activo, se interrumpe el flujo
        if (isVacationModeActive) {
            System.out.println("[VACACIONES] Zzz... El modo vacaciones está activo. Se ignoran todas las alarmas.");
            return; 
        }

        List<Alarm> activeAlarms = getActiveAlarms();
        for (Alarm a : activeAlarms) {
            boolean sameHour = a.getTime().getHour() == currentTime.getHour();
            boolean sameMinute = a.getTime().getMinute() == currentTime.getMinute();
            boolean shouldSoundToday = a.getRepeatDays().isEmpty() || a.getRepeatDays().contains(currentDay);

            if (sameHour && sameMinute && shouldSoundToday) {
                a.trigger();
            }
        }
    }

    public void listAllAlarms() {
        System.out.println("\n=== LISTA DE ALARMAS EN EL SISTEMA ===");
        // Añadimos información del modo vacaciones al listado
        System.out.println("ESTADO MODO VACACIONES: " + (isVacationModeActive ? "ACTIVO 🏖️" : "INACTIVO 👁️"));
        if (alarms.isEmpty()) {
            System.out.println("No hay alarmas configuradas.");
            return;
        }
        for (Alarm a : alarms) {
            System.out.println("- [" + (a.isActive() ? "ACTIVA" : "INACTIVA") + "] " 
                    + a.getLabel() + " a las " + a.getTime() 
                    + " | Días: " + (a.getRepeatDays().isEmpty() ? "Todos" : a.getRepeatDays()));
        }
        System.out.println("=======================================\n");
    }

    // Getter para el Main
    public boolean isVacationModeActive() {
        return isVacationModeActive;
    }
}