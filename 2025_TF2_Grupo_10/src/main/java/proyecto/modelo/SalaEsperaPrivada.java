package proyecto.modelo;

import proyecto.modelo.paciente.Paciente;
/**
 * Clase donde el paciente que entra se queda en espera de forma individual,
 * para entrar debe competir en prioridad con el paciente anterior
 */
public class SalaEsperaPrivada {
    
    private Paciente paciente;

    public SalaEsperaPrivada(Paciente paciente) {
        // Se permite que la sala se inicialice con paciente = null (sala vacia).
        this.paciente = paciente;
        
        // Postcondicion: el paciente interno debe ser el mismo que el pasado.
        assert this.paciente == paciente : "El paciente no se asigno correctamente al constructor.";
    }
    
    /**
     * * @return Obtiene el paciente actual de la sala de espera.
     */
    public Paciente getPaciente() {
        return paciente;
    }
    
    /**
     * Establece un nuevo paciente en la sala de espera.
     * * @param paciente el nuevo paciente a asignar
     */
    public void setPaciente(Paciente paciente) {
        // Precondicion: Se permite paciente = null para vaciar la sala.
        this.paciente = paciente;
        
        // Postcondicion: el paciente interno debe ser el mismo que el asignado.
        assert this.paciente == paciente : "El paciente no se asigno correctamente a la sala.";
    }
}