import java.time.LocalTime;
import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public class Alarm {
    // Atributos privados (Encapsulación estricta)
    private String id;
    private String label;
    private LocalTime time;
    private boolean isActive;
    private List<DayOfWeek> repeatDays; // Lista de días de la semana que se repite
    private SoundProfile soundProfile;
    private int snoozeCount;            // Para registrar estadísticas de uso futuro
    private MathChallenge mathChallenge;


    // Constructor completo
    public Alarm(String label, LocalTime time, List<DayOfWeek> repeatDays, SoundProfile soundProfile) {
        this.id = UUID.randomUUID().toString(); // Genera un ID único automáticamente
        this.label = label;
        this.time = time;
        this.repeatDays = repeatDays;
        this.soundProfile = soundProfile;
        this.isActive = true;                   // Por defecto, una alarma nace activa
        this.snoozeCount = 0;
        this.mathChallenge = new MathChallenge(); // Cada alarma tiene su propio reto
    }

    // --- MÉTODOS DE NEGOCIO OBLIGATORIOS ---

    // Activa o desactiva la alarma (Toggle)
    public void toggleActive() {
        this.isActive = !this.isActive;
        System.out.println("[ALARMA] '" + label + "' ahora está " + (isActive ? "ACTIVADA" : "DESACTIVADA"));
    }

    // Posponer alarma (Snooze)
    public void snooze(int minutes) {
        if (!isActive) return;
        
        // Sumamos los minutos a la hora actual de la alarma
        this.time = this.time.plusMinutes(minutes);
        this.snoozeCount++; // Registramos que el usuario ha pospuesto la alarma
        System.out.println("[SNOOZE] Alarma '" + label + "' pospuesta " + minutes + " minutos. Nueva hora: " + time);
    }

    // Detener alarma
    // Detener alarma con validación del reto matemático
// Detener alarma con validación del reto matemático e informe de sueño
    public void stop() {
        System.out.println("[STOP] Intentando detener la alarma '" + label + "'...");
        boolean solved = mathChallenge.solveChallenge();

        if (solved) {
            System.out.println("[ALARMA] '" + label + "' detenida definitivamente.");
            
            // NUEVO: Mostramos las estadísticas de sueño acumuladas justo al apagarla
            generateSleepReport();

            if (repeatDays == null || repeatDays.isEmpty()) {
                this.isActive = false;
                System.out.println("[ALARMA] '" + label + "' se ha desactivado automáticamente.");
            }
        } else {
            System.out.println("[ALARMA] ¡No pudiste detenerla! La alarma sigue sonando...");
            this.trigger();
        }
    }

    // Simula que la alarma se dispara en el sistema
    public void trigger() {
        if (isActive) {
            System.out.println("\n!!! ¡RING RING RING! !!!");
            System.out.println("Alarma: " + label + " [" + time + "]");
            soundProfile.play(); // Delegación de responsabilidad a SoundProfile
        }
    }

    // --- GETTERS Y SETTERS (Para mantener la encapsulación) ---
    
    public String getId() { return id; }
    
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { this.isActive = active; }

    public List<DayOfWeek> getRepeatDays() { return repeatDays; }
    public void setRepeatDays(List<DayOfWeek> repeatDays) { this.repeatDays = repeatDays; }

    public SoundProfile getSoundProfile() { return soundProfile; }
    public void setSoundProfile(SoundProfile soundProfile) { this.soundProfile = soundProfile; }

    public int getSnoozeCount() { return snoozeCount; }


    // NUEVO MÉTODO: Genera el perfil de sueño basado en las estadísticas de snooze
    public void generateSleepReport() {
        System.out.println("\n=============================================");
        System.out.println("📊 INFORME DE PERFIL DE SUEÑO - ALARMA: '" + label + "'");
        System.out.println("=============================================");
        System.out.println("-> Veces que pospusiste la alarma: " + snoozeCount);
        System.out.print("-> Diagnóstico de tu perfil: ");

        // Lógica analítica según el comportamiento del usuario
        if (snoozeCount == 0) {
            System.out.println("⚡ ¡ESPARTANO! Te has levantado a la primera. Perfil: Madrugador Pro.");
        } else if (snoozeCount >= 1 && snoozeCount <= 2) {
            System.out.println("☕ DURMIENTE ESTÁNDAR. Un par de minutos más de descanso, pero cumples.");
        } else {
            System.out.println("💤 MARMOTA PROFESIONAL. Tienes un problema serio con las sábanas. ¡Espabila!");
        }
        System.out.println("=============================================\n");
    }
}