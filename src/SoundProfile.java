

public class SoundProfile {
    // Atributos privados (Encapsulación obligatoria)
    private String toneName;
    private int volumeLevel;

    // Constructor para inicializar el perfil de sonido
    public SoundProfile(String toneName, int volumeLevel) {
        this.toneName = toneName;
        setVolumeLevel(volumeLevel); // Usamos el setter para validar el volumen
    }

    // Método para simular que el sonido se está reproduciendo
    public void play() {
        System.out.println("[SONIDO] Reproduciendo tono: '" + toneName + "' al volumen: " + volumeLevel + "/10");
    }

    // Getters y Setters con reglas de negocio simples
    public String getToneName() {
        return toneName;
    }

    public void setToneName(String toneName) {
        this.toneName = toneName;
    }

    public int getVolumeLevel() {
        return volumeLevel;
    }

    public void setVolumeLevel(int volumeLevel) {
        // Regla de negocio: El volumen debe estar entre 1 y 10
        if (volumeLevel < 1) {
            this.volumeLevel = 1;
        } else if (volumeLevel > 10) {
            this.volumeLevel = 10;
        } else {
            this.volumeLevel = volumeLevel;
        }
    }
}