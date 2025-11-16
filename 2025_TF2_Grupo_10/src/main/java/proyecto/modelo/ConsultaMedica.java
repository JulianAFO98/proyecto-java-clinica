package proyecto.modelo;

import java.util.Date;
import proyecto.modelo.interfaces.IMedico;
import proyecto.modelo.paciente.Paciente;

/**
 * Clase que representa una consulta medica en el sistema. Contiene informacion
 * sobre el paciente, el medico y la fecha de la consulta.
 */
public class ConsultaMedica {
	private Paciente paciente;
	private IMedico medico;
	private Date fechaConsulta;

	/**
	 * Constructor de la clase ConsultaMedica. * @param paciente Objeto de tipo
	 * paciente que representa al paciente.
	 * 
	 * @param medico        Objeto de tipo IMedico que representa al medico.
	 * @param fechaConsulta Objeto de tipo Date que representa la fecha de la
	 *                      consulta
	 */
	public ConsultaMedica(Paciente paciente, IMedico medico, Date fechaConsulta) {
		assert paciente != null : "El paciente de la consulta no puede ser nulo.";
		assert medico != null : "El medico de la consulta no puede ser nulo.";

		this.paciente = paciente;
		this.medico = medico;
		this.fechaConsulta = fechaConsulta;

		assert this.paciente == paciente : "El paciente no fue asignado correctamente.";
		assert this.medico == medico : "El medico no fue asignado correctamente.";
	}

	public Paciente getPaciente() {
		assert paciente != null : "El paciente de la consulta no debe ser nulo en ningun momento.";
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		assert paciente != null : "El paciente a asignar no puede ser nulo.";
		this.paciente = paciente;
	}

	public IMedico getMedico() {
		assert medico != null : "El medico de la consulta no debe ser nulo en ningun momento.";
		return medico;
	}

	public void setMedico(IMedico medico) {
		assert medico != null : "El medico a asignar no puede ser nulo.";
		this.medico = medico;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		assert fechaConsulta == null || fechaConsulta.compareTo(new Date()) <= 0
				: "La fecha de consulta no puede ser una fecha futura.";

		this.fechaConsulta = fechaConsulta;
	}

	/**
	 * Devuelve una representacion en cadena de la consulta medica.
	 */
	@Override
	public String toString() {
		assert paciente != null && medico != null
				: "No se puede llamar a toString si el paciente o el medico son nulos.";

		return "ConsultaMedica {\n" + "  Paciente: " + (paciente != null ? paciente.toString() : "N/A") + ",\n"
				+ "  Medico: " + (medico != null ? medico.toString() : "N/A") + ",\n" + "  Fecha de Consulta: "
				+ (fechaConsulta != null ? fechaConsulta.toString() : "N/A") + "\n" + "}";
	}

}