package proyecto.modelo;
import java.util.Date;

import proyecto.modelo.interfaces.IMedico;
import proyecto.modelo.paciente.Paciente;

/**
 * Clase que representa una consulta médica en el sistema.
 * Contiene información sobre el paciente, el médico y la fecha de la consulta.
 */
public class ConsultaMedica {
    private Paciente paciente;
    private IMedico medico;
    private Date fechaConsulta;
    
    /**
     * Constructor de la clase ConsultaMedica.
     * @param paciente
     * @param medico
     * @param fechaConsulta
     */
    public ConsultaMedica(Paciente paciente, IMedico medico, Date fechaConsulta) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaConsulta = fechaConsulta;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public IMedico getMedico() {
        return medico;
    }
    public void setMedico(IMedico medico) {
        this.medico = medico;
    }
    public Date getFechaConsulta() {
        return fechaConsulta;
    }
    public void setFechaConsulta(Date fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
    
    /**
     * Devuelve una representación en cadena de la consulta médica.
     */
    @Override
    public String toString() {
        return "ConsultaMedica [paciente=" + paciente + ", medico=" + medico + ", fechaConsulta=" + fechaConsulta + "]";
    }

    
    

}
