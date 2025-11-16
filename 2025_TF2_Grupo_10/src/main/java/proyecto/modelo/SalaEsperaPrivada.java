package proyecto.modelo;

import proyecto.modelo.paciente.Paciente;
/**
 * Clase donde el paciente que entra se queda en espera de forma individual,
 * para entrar debe competir en prioridad con el paciente anterior
 */
public class SalaEsperaPrivada {
    
    private Paciente paciente;

    public SalaEsperaPrivada(Paciente paciente) {
        this.paciente = paciente;
    }
    
    /**
     * 
     * @return Obtiene el paciente actual de la sala de espera.
     */
    public Paciente getPaciente() {
        return paciente;
    }
    /**
     * Establece un nuevo paciente en la sala de espera.
     * 
     * @param paciente el nuevo paciente a asignar
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
}
