package proyecto.modelo;
import java.util.Date;

import proyecto.modelo.interfaces.IMedico;
import proyecto.modelo.paciente.Paciente;

public class ConsultaMedica {
    private Paciente paciente;
    private IMedico medico;
    private Date fechaConsulta;
    
    
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
    
    @Override
    public String toString() {
        return "ConsultaMedica [paciente=" + paciente + ", medico=" + medico + ", fechaConsulta=" + fechaConsulta + "]";
    }

    
    

}
