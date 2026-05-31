# ⏰ Sistema de Despertador Inteligente (Java)

## 📝 Descripción del Proyecto
Este proyecto es una simulación de consola de un "Despertador Inteligente" desarrollado en **Java**. El sistema permite gestionar múltiples alarmas, configurar perfiles de sonido, y cuenta con funcionalidades avanzadas como un "Modo Vacaciones" global, un sistema de estadísticas de sueño, y un reto matemático anti-sueño para asegurar que el usuario se despierta.

## 🚀 Tecnologías y Herramientas
* **Lenguaje:** Java puro (sin frameworks).
* **Control de Versiones:** Git y GitHub.
* **IDE:** Visual Studio Code.
* **Diseño:** Mermaid (UML y Casos de Uso) integrado nativamente en Markdown.

---

## ⚙️ Guía de Ejecución
Para ejecutar este proyecto en tu entorno local:
1. Clona el repositorio: `git clone <URL_DE_TU_REPO>`
2. Abre la carpeta del proyecto en Visual Studio Code.
3. Navega al archivo `src/Main.java`.
4. Ejecuta el archivo utilizando el botón "Run" de tu IDE o compila mediante consola:
   `javac src/*.java` y luego `java src.Main`
5. Sigue las instrucciones por consola para interactuar con el reto matemático.

![Captura de la Ejecución Exitosa](assets/ejecucion.png)

---

## 📐 Diseño y Arquitectura

### 1. Diagrama de Casos de Uso
El siguiente diagrama muestra las interacciones principales del usuario con el sistema:

```mermaid
flowchart LR
    Usuario((Usuario))
    
    Crear([Crear/Añadir Alarma])
    Posponer([Posponer Alarma])
    Detener([Detener Alarma])
    Vacaciones([Activar Modo Vacaciones])
    
    Reto([Resolver Reto Matemático])
    Informe([Generar Informe de Sueño])

    Usuario --> Crear
    Usuario --> Posponer
    Usuario --> Detener
    Usuario --> Vacaciones

    Detener -.->|include| Reto
    Detener -.->|include| Informe
    ## 🧠 Reflexiones y Toma de Decisiones

### 1. Decisiones de Diseño y SOLID
* **Encapsulación estricta:** Todos los atributos de las clases (`Alarm`, `SoundProfile`, etc.) son privados. El estado solo se modifica a través de métodos controlados (ej. `snooze()` o `toggleVacationMode()`).
* **Principio de Responsabilidad Única (SRP):** La clase `Alarm` no sabe cómo reproducir un sonido ni cómo generar operaciones matemáticas. Delega el sonido a `SoundProfile` y la validación de la suma a `MathChallenge`.
* **Manejo de duplicados y consistencia:** El gestor `AlarmManager` implementa reglas de negocio para impedir que se añadan dos alarmas exactas en el mismo día y hora, previniendo conflictos.

### 2. Dificultades Encontradas
Durante el desarrollo, la mayor dificultad técnica no fue el código Java en sí, sino el manejo de la terminal integrada de Visual Studio Code y la configuración de Git en Windows. El IDE abría la paleta de búsqueda de archivos en lugar del asistente de fusión de ramas (Merge). Esto se resolvió ejecutando los comandos de fusión de manera manual a través de la terminal `Git Bash` (`git merge feature/...`), lo que permitió resolver los bloqueos visuales del entorno y garantizó un control absoluto del historial de commits.

### 3. Uso de IA y Validación
He utilizado Inteligencia Artificial (Gemini) como un "tutor técnico" y compañero de programación.
* **Uso:** Me ayudó a estructurar la arquitectura del código, a redactar la lógica de negocio paso a paso garantizando el cumplimiento estricto de los requisitos, y a diagnosticar los problemas de Git en la consola de Windows.
* **Validación:** No se ha realizado un copiado ciego. He validado manualmente cada funcionalidad ejecutando simulaciones exhaustivas en el archivo `Main.java`, interactuando por consola con el código de `MathChallenge` para forzar aciertos y fallos, comprobando la acumulación de estadísticas tras varios `snooze` seguidos y confirmando en la terminal que los merges se realizaban de forma limpia.

---

## ⚖️ Autoevaluación

Basándome en la rúbrica proporcionada por el profesor, propongo la siguiente calificación:

| Criterio | Porcentaje | Autoevaluación | Justificación |
| :--- | :---: | :---: | :--- |
| **Paso 1: Configuración Git** | 10% | 10% | Repositorio remoto creado con la estructura exigida (.gitignore, README, ramas). |
| **Paso 2: Análisis y UML** | 15% | 15% | Diagramas de Clases y Casos de Uso integrados con Mermaid y explicados. |
| **Paso 3: Lógica Base Java** | 30% | 30% | Clases `Alarm`, `SoundProfile` y `AlarmManager` operativas, sin errores, con encapsulación. |
| **Paso 4: Funcionalidades Avanzadas** | 25% | 25% | Implementadas 3 funciones en ramas separadas: Modo Vacaciones, Reto Matemático y Perfil de Sueño. |
| **Paso 5/6: Documentación y Reflexión**| 20% | 20% | README estructurado con reflexión técnica honesta, detalles sobre el diseño, uso validado de IA y capturas adjuntas. |
| **TOTAL** | **100%** | **100% / 10** | Se cumplen todos los requisitos funcionales, técnicos y organizativos del enunciado de manera profesional. |