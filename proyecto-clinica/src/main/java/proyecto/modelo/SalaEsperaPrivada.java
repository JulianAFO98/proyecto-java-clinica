package proyecto.modelo;

import proyecto.modelo.paciente.Paciente;

public class SalaEsperaPrivada {
    private Paciente paciente;
    public SalaEsperaPrivada(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
}
